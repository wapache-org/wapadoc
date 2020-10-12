import { LitElement, html, css } from 'lit-element';
import marked from 'marked';

import { unsafeHTML } from 'lit-html/directives/unsafe-html';
import { live } from 'lit-html/directives/live';
import TableStyles from '@/styles/table-styles';
import FlexStyles from '@/styles/flex-styles';
import InputStyles from '@/styles/input-styles';
import FontStyles from '@/styles/font-styles';
import BorderStyles from '@/styles/border-styles';
import TabStyles from '@/styles/tab-styles';
import PrismStyles from '@/styles/prism-styles';
import { schemaInObjectNotation, getTypeInfo, generateExample } from '@/utils/schema-utils';
import '@/components/json-tree';
import '@/components/schema-tree';
import '@/components/tag-input';

import '@/components/api-try';

export default class ApiRequest extends LitElement {
  constructor() {
    super();
    this.selectedRequestBodyType = '';
    this.selectedRequestBodyExample = '';
  }

  static get properties() {
    return {
      serverUrl: { type: String, attribute: 'server-url' },
      servers: { type: Array },
      method: { type: String },
      path: { type: String },
      parameters: { type: Array },
      request_body: { type: Object },
      api_keys: { type: Array },
      parser: { type: Object },
      accept: { type: String },
      callback: { type: String },
      responseMessage: { type: String, attribute: false },
      responseText: { type: String, attribute: false },
      responseHeaders: { type: String, attribute: false },
      responseStatus: { type: String, attribute: false },
      responseUrl: { type: String, attribute: false },
      allowTry: { type: String, attribute: 'allow-try' },
      renderStyle: { type: String, attribute: 'render-style' },
      schemaStyle: { type: String, attribute: 'schema-style' },
      activeSchemaTab: { type: String, attribute: 'active-schema-tab' },
      schemaExpandLevel: { type: Number, attribute: 'schema-expand-level' },
      schemaDescriptionExpanded: { type: String, attribute: 'schema-description-expanded' },
      activeResponseTab: { type: String }, // internal tracking of response-tab not exposed as a attribute
      selectedRequestBodyType: { type: String, attribute: 'selected-request-body-type' }, // internal tracking of selected request-body type
      selectedRequestBodyExample: { type: String, attribute: 'selected-request-body-example' }, // internal tracking of selected request-body example
    };
  }

  static get styles() {
    return [
      TableStyles,
      InputStyles,
      FontStyles,
      FlexStyles,
      BorderStyles,
      TabStyles,
      PrismStyles,
      css`
        .read-mode{
          margin-top:24px;
        }
        .focused-mode {
          padding-top:24px;
          border-top: 1px dashed var(--border-color);
        }
        .param-name,
        .param-type {
          margin: 1px 0;
          text-align: right;
          line-height: var(--font-size-small);
        }
        .param-name{
          color: var(--fg); 
          font-family: var(--font-mono);
        }
        .param-type{
          color: var(--light-fg); 
          font-family: var(--font-regular);
        }
        .param-constraint{
          min-width:100px;
        }
        .param-constraint:empty{
          display:none;
        }
        .top-gap{margin-top:24px;}

        .textarea {
          min-height:220px; 
          padding:5px;
          resize:vertical;
        }
        .example:first-child {
          margin-top: -9px;
        }

        .response-message{
          font-weight:bold;
          text-overflow: ellipsis;
        }
        .response-message.error {
          color:var(--red);
        }
        .response-message.success {
          color:var(--blue);
        }

        .file-input-container {
          align-items:flex-end;
        }
        .file-input-container .input-set:first-child .file-input-remove-btn{
          visibility:hidden;
        }

        .file-input-remove-btn{
          font-size:16px;
          color:var(--red);
          outline: none;
          border: none;
          background:none;
          cursor:pointer;
        }

        .v-tab-btn {
          font-size: var(--smal-font-size);
          height:24px; 
          border:none; 
          background:none; 
          opacity: 0.3;
          cursor: pointer;
          padding: 4px 8px;
        }
        .v-tab-btn.active {
          font-weight: bold;
          background: var(--bg);
          opacity: 1;
        }

        @media only screen and (min-width: 768px) {
          .textarea {
            padding:8px;
          }
        }
      `,
    ];
  }

  render() {
    return html`
    
    <div class="col regular-font request-try-panel ${this.renderStyle}-mode">
      ${this.allowTry === 'false' ? '' : html`<api-try
          class="try-panel"  
          method = "${this.method}"
          path = "${this.path}" 
          .parameters = "${this.parameters}" 
          .request_body = "${this.requestBody}"
          .api_keys = "${this.api_keys}"
          .servers = "${this.servers}" 
          server-url = "${this.serverUrl}" 
          allow-try = "${this.allowTry}"
          accept = "${this.accept}"
          render-style="${this.renderStyle}" 
          schema-style = "${this.schemaStyle}"
          active-schema-tab = "${this.activeSchemaTab}"
          schema-expand-level = "${this.schemaExpandLevel}"
          schema-description-expanded = "${this.schemaDescriptionExpanded}"
        ></api-try>`}
    </div>

    <div class="col regular-font request-panel ${'read focused'.includes(this.renderStyle) || this.callback === 'true' ? (this.renderStyle === 'focused' ? 'focused-mode' : 'read-mode') : 'view-mode'}">
      <div class=" ${this.callback === 'true' ? 'tiny-title' : 'req-res-title'} "> 
        ${this.callback === 'true' ? '回调请求' : '请求'}
      </div>
      <div>
        ${this.inputParametersTemplate('path')}
        ${this.inputParametersTemplate('query')}
        ${this.requestBodyTemplate()}
        ${this.inputParametersTemplate('header')}
        ${this.inputParametersTemplate('cookie')}
      </div>  
    </div>
    `;
  }

  updated(changedProperties) {
    // In focused mode after rendering the request component, update the text-areas(which contains examples) using
    // the original values from hidden textareas
    // This is done coz, user may update the dom by editing the textarea's and once the DOM is updated externally change detection wont happen, therefore update the values manually
    if (this.renderStyle === 'focused') {
      if (changedProperties.size === 1 && changedProperties.has('activeSchemaTab')) {
        // dont update example as only tabs is switched
      } else {
        const exampleTextAreaEls = [...this.shadowRoot.querySelectorAll('textarea[data-ptype="form-data"]')];
        exampleTextAreaEls.forEach((el) => {
          const origExampleEl = this.shadowRoot.querySelector(`textarea[data-pname='hidden-${el.dataset.pname}']`);
          if (origExampleEl) {
            el.value = origExampleEl.value;
          }
        });
      }
    }
  }

  getFieldTitle(param) {
    const schema = param.schema;
    if (schema) {
      if (schema.type === 'array' && schema.items) {
        return schema.items.title || '';
      }
      return schema.title || '';
    }
    return '';
  }

  getFieldDescription(param) {
    if (param.description) {
      return param.description;
    }

    const schema = param.schema;
    if (schema) {
      if (schema.type === 'array' && schema.items) {
        return schema.items.description || '';
      }
      return schema.description || '';
    }
    return '';
  }

  /* eslint-disable indent */
  inputParametersTemplate(paramType) {
    let title = '';
    const filteredParams = this.parameters ? this.parameters.filter((param) => param.in === paramType) : [];
    if (filteredParams.length === 0) {
      return '';
    }
    if (paramType === 'path') {
      title = '路径参数';
    } else if (paramType === 'query') {
      title = '查询参数';
    } else if (paramType === 'header') {
      title = '请求头';
    } else if (paramType === 'cookie') {
      title = 'COOKIES';
    }

    const tableRows = [];
    for (const param of filteredParams) {
      if (!param.schema) {
        continue;
      }
      const paramSchema = getTypeInfo(param.schema);
      let inputVal = '';
      let paramStyle = 'form';
      let paramExplode = true;
      if (paramType === 'query') {
        if (param.style && 'form spaceDelimited pipeDelimited'.includes(param.style)) {
          paramStyle = param.style;
        }
        if (typeof param.explode === 'boolean') {
          paramExplode = param.explode;
        }
      }

      param.example = typeof param.example === 'undefined' ? '' : `${param.example}`;
      if (param.example) {
        inputVal = paramSchema.type === 'array' ? [param.example] : `${param.example}`;
      } else if (paramSchema.example) {
        inputVal = paramSchema.type === 'array' ? [paramSchema.example] : `${paramSchema.example}`;
      } else if (param.examples && Object.values(param.examples).length > 0) {
        const firstExample = Object.values(param.examples)[0].value || '';
        inputVal = paramSchema.type === 'array' ? [firstExample] : firstExample;
      } else {
        inputVal = paramSchema.type === 'array'
          ? (paramSchema.default ? [paramSchema.default] : '')
          : (paramSchema.default ? paramSchema.default : '');
      }
      tableRows.push(html`
      <tr> 
        <td rowspan="${this.allowTry === 'true' || inputVal !== '' ? '1' : '2'}" style="width:160px; min-width:100px;">
          <div class="param-name">
            ${param.required ? html`<span style='color:var(--red)'>*</span>` : ''}${param.name}
          </div>
          <div class="param-type">
            ${paramSchema.type === 'array'
              ? `${paramSchema.arrayType}`
              : `${paramSchema.format ? paramSchema.format : paramSchema.type}`
            }
          </div>
        </td>  
        ${this.allowTry === 'true' || inputVal !== ''
          ? html`
            <td style="width:${paramSchema.type === 'array' || paramSchema.type === 'object' ? ('read focused'.includes(this.renderStyle) ? '300px' : '220px') : '160px'}; min-width:100px;">
              ${paramSchema.type === 'array'
                ? html`
                  <tag-input class="request-param" 
                    style = "width:160px; background:var(--input-bg);" 
                    data-ptype = "${paramType}"
                    data-pname = "${param.name}"
                    data-param-serialize-style = "${paramStyle}"
                    data-param-serialize-explode = "${paramExplode}"
                    data-array = "true"
                    placeholder= "add-multiple &#x2b90;"
                    .value = "${live(inputVal)}"
                  >
                  </tag-input>`
                : paramSchema.type === 'object'
                  ? html`
                    <textarea 
                      class = "textarea request-param"
                      data-ptype = "${paramType}-object"
                      data-pname = "${param.name}"
                      data-param-serialize-style = "${paramStyle}"
                      data-param-serialize-explode = "${paramExplode}"
                      spellcheck = "false"
                      style = "resize:vertical; width:100%; height: ${'read focused'.includes(this.renderStyle) ? '180px' : '120px'};"
                    >${inputVal}</textarea>`
                  : html`
                    <input type="${paramSchema.format === 'password' ? 'password' : 'text'}" spellcheck="false" style="width:100%" class="request-param" 
                      data-pname="${param.name}" 
                      data-ptype="${paramType}"  
                      data-array="false"
                      .value="${live(inputVal)}"
                    />`
                }
            </td>`
          : ''
        }
        <td colspan="${(this.allowTry === 'true' || inputVal !== '') ? '1' : '2'}">
          ${paramSchema.default || paramSchema.constrain || paramSchema.allowedValues
            ? html`
              <div class="param-constraint">
                ${paramSchema.default ? html`<span style="font-weight:bold">默认值: </span>${paramSchema.default}<br/>` : ''}
                ${paramSchema.constrain ? html`${paramSchema.constrain}<br/>` : ''}
                ${param.schema.enum
                  ? param.schema.enum.map((v, i) => html`
                    ${i > 0 ? ' | ' : ''}
                    <a style="cursor:pointer" data-enum="${v}" @click="${(e) => {
                      const inputEl = e.target.closest('table').querySelector(`input[data-pname="${param.name}"]`);
                      if (inputEl) {
                        inputEl.value = e.target.dataset.enum;
                      }
                    }}"> ${v} </a>`)
                  : ''
                }
              </div>`
            : ''
          }
        </td>  
      </tr>
      <tr>
        ${this.allowTry === 'true' || inputVal !== ''
        ? html`<td style="border:none;text-align: right;margin-top:0; padding:0 5px 8px 5px;">${this.getFieldTitle(param)}</td>`
        : ''
        }
        <td colspan="2" style="border:none; margin-top:0; padding:0 5px 8px 5px;"> 
          <span class="m-markdown-small">${unsafeHTML(marked(this.getFieldDescription(param)))}</span>
          ${(param.examples && Object.values(param.examples).length > 1)
            ? html`<span> Examples: 
              ${Object.entries(param.examples).map((ex, i, a) => html`
                <a style="cursor:pointer" data-example="${ex[1].value}" @click="${(e) => {
                  const inputEl = e.target.closest('table').querySelector(`input[data-pname="${param.name}"]`);
                  if (inputEl) {
                    inputEl.value = e.target.dataset.example;
                  }
                }}">
                  ${ex[1].summary || ex[1].value || ex[0]}
                </a> ${i <= (a.length - 2) ? '| ' : ''}`)
              }`
            : ''
          }
        </td>
      </tr>
    `);
    }

    return html`
    <div class="table-title top-gap">${title}</div>
    <div style="display:block; overflow-x:auto; max-width:100%;">
      <table class="m-table" style="width:100%; word-break:break-word;">
        ${tableRows}
      </table>
    </div>`;
  }

  // Request-Body Event Handlers
  onSelectExample(e) {
    this.selectedRequestBodyExample = e.target.value;
    const exampleDropdownEl = e.target;
    window.setTimeout((selectEl) => {
      const exampleTextareaEl = selectEl.closest('.example-panel').querySelector('.request-body-param');
      const userInputExampleTextareaEl = selectEl.closest('.example-panel').querySelector('.request-body-param-user-input');
      userInputExampleTextareaEl.value = exampleTextareaEl.value;
    }, 0, exampleDropdownEl);
  }

  onMimeTypeChange(e) {
    this.selectedRequestBodyType = e.target.value;
    const mimeDropdownEl = e.target;
    this.selectedRequestBodyExample = '';
    window.setTimeout((selectEl) => {
      const exampleTextareaEl = selectEl.closest('.request-body-container').querySelector('.request-body-param');
      if (exampleTextareaEl) {
        const userInputExampleTextareaEl = selectEl.closest('.request-body-container').querySelector('.request-body-param-user-input');
        userInputExampleTextareaEl.value = exampleTextareaEl.value;
      }
    }, 0, mimeDropdownEl);
  }

  requestBodyTemplate() {
    if (!this.request_body) {
      return '';
    }
    if (Object.keys(this.request_body).length === 0) {
      return '';
    }

    // Variable to store partial HTMLs
    let reqBodyTypeSelectorHtml = '';
    let reqBodyFileInputHtml = '';
    let reqBodyFormHtml = '';
    let reqBodySchemaHtml = '';
    let reqBodyExampleHtml = '';

    const requestBodyTypes = [];
    const content = this.request_body.content;

    for (const mimeType in content) {
      requestBodyTypes.push({
        mimeType,
        schema: content[mimeType].schema,
        example: content[mimeType].example,
        examples: content[mimeType].examples,
      });
      if (!this.selectedRequestBodyType) {
        this.selectedRequestBodyType = mimeType;
      }
    }

    // MIME Type selector
    reqBodyTypeSelectorHtml = requestBodyTypes.length === 1
      ? ''
      : html`
        <select style="min-width:100px; max-width:100%;  margin-bottom:-1px;" @change = '${(e) => this.onMimeTypeChange(e)}'>
          ${requestBodyTypes.map((reqBody) => html`
            <option value = '${reqBody.mimeType}' ?selected = '${reqBody.mimeType === this.selectedRequestBodyType}'>
              ${reqBody.mimeType}
            </option> `)
          }
        </select>
      `;

    // For Loop - Main
    requestBodyTypes.forEach((reqBody) => {
      let schemaAsObj;
      let reqBodyExamples = [];

      if (this.selectedRequestBodyType.includes('json') || this.selectedRequestBodyType.includes('xml') || this.selectedRequestBodyType.includes('text')) {
        // Generate Example
        if (reqBody.mimeType === this.selectedRequestBodyType) {
          reqBodyExamples = generateExample(
            reqBody.examples ? reqBody.examples : '',
            reqBody.example ? reqBody.example : '',
            reqBody.schema,
            reqBody.mimeType,
            false,
            'text',
          );
          if (!this.selectedRequestBodyExample) {
            this.selectedRequestBodyExample = (reqBodyExamples.length > 0 ? reqBodyExamples[0].exampleId : '');
          }
          reqBodyExampleHtml = html`
            ${reqBodyExampleHtml}
            <div class = 'example-panel border-top pad-top-8'>
              ${reqBodyExamples.length === 1
                ? ''
                : html`
                  <select style="min-width:100px; max-width:100%;  margin-bottom:-1px;" @change='${(e) => this.onSelectExample(e)}'>
                    ${reqBodyExamples.map((v) => html`<option value="${v.exampleId}" ?selected=${v.exampleId === this.selectedRequestBodyExample} > 
                      ${v.exampleSummary.length > 80 ? v.exampleId : v.exampleSummary ? v.exampleSummary : v.exampleId} 
                    </option>`)}
                  </select>
                `
              }
              ${reqBodyExamples
                .filter((v) => v.exampleId === this.selectedRequestBodyExample)
                .map((v) => html`
                <div class="example ${v.exampleId === this.selectedRequestBodyExample ? 'example-selected' : ''}" data-example = '${v.exampleId}'>
                  ${v.exampleSummary && v.exampleSummary.length > 80 ? html`<div style="padding: 4px 0"> ${v.exampleSummary} </div>` : ''}
                  ${v.exampleDescription ? html`<div class="m-markdown-small" style="padding: 4px 0"> ${unsafeHTML(marked(v.exampleDescription || ''))} </div>` : ''}
                  <!-- this textarea is for user to edit the example -->
                  <textarea 
                    class = "textarea request-body-param-user-input" 
                    spellcheck = "false"
                    data-ptype = "${reqBody.mimeType}" 
                    style="width:100%; resize:vertical;"
                  >${(v.exampleFormat === 'text' ? v.exampleValue : JSON.stringify(v.exampleValue, null, 2))}</textarea>
                  <!-- This textarea(hidden) is to store the original example value, this will remain unchanged when users switches from one example to another, its is used to populate the editable textarea -->
                  <textarea 
                    class = "textarea request-body-param ${reqBody.mimeType.substring(reqBody.mimeType.indexOf('/') + 1)}" 
                    spellcheck = "false"
                    data-ptype = "${reqBody.mimeType}" 
                    style="width:100%; resize:vertical; display:none"
                  >${(v.exampleFormat === 'text' ? v.exampleValue : JSON.stringify(v.exampleValue, null, 2))}</textarea>
                </div>  
              `)}

            </div>
          `;
        }
      } else if (this.selectedRequestBodyType.includes('form-urlencoded') || this.selectedRequestBodyType.includes('form-data')) {
        if (reqBody.mimeType === this.selectedRequestBodyType) {
          const ex = generateExample(
            reqBody.examples ? reqBody.examples : '',
            reqBody.example ? reqBody.example : '',
            reqBody.schema,
            reqBody.mimeType,
            false,
            'text',
          );
          if (reqBody.schema) {
            reqBodyFormHtml = this.formDataTemplate(reqBody.schema, reqBody.mimeType, (ex[0] ? ex[0].exampleValue : ''));
          }
        }
      } else if (this.selectedRequestBodyType.includes('octet-stream')) {
        if (reqBody.mimeType === this.selectedRequestBodyType) {
          reqBodyFileInputHtml = html`
            <div class = "small-font-size bold-text row">
              <input type="file" style="max-width:100%" class="request-body-param-file" data-ptype="${reqBody.mimeType}" spellcheck="false" />
            </div>  
          `;
        }
      }

      // Generate Schema
      if (reqBody.mimeType.includes('json') || reqBody.mimeType.includes('xml') || reqBody.mimeType.includes('text')) {
        schemaAsObj = schemaInObjectNotation(reqBody.schema, {});
        if (this.schemaStyle === 'table') {
          reqBodySchemaHtml = html`
            ${reqBodySchemaHtml}
            <schema-table
              class = '${reqBody.mimeType.substring(reqBody.mimeType.indexOf('/') + 1)}'
              style = 'display: ${this.selectedRequestBodyType === reqBody.mimeType ? 'block' : 'none'};'
              .data = '${schemaAsObj}'
              schema-expand-level = "${this.schemaExpandLevel}"
              schema-description-expanded = "${this.schemaDescriptionExpanded}"
            > </schema-table>
          `;
        } else if (this.schemaStyle === 'tree') {
          reqBodySchemaHtml = html`
            ${reqBodySchemaHtml}
            <schema-tree
              class = '${reqBody.mimeType.substring(reqBody.mimeType.indexOf('/') + 1)}'
              style = 'display: ${this.selectedRequestBodyType === reqBody.mimeType ? 'block' : 'none'};'
              .data = '${schemaAsObj}'
              schema-expand-level = "${this.schemaExpandLevel}"
              schema-description-expanded = "${this.schemaDescriptionExpanded}"
            > </schema-tree>
          `;
        }
      }
    });

    return html`
      <div class='request-body-container' data-selected-request-body-type="${this.selectedRequestBodyType}">
        <div class="table-title top-gap row">
          请求体格式 ${this.request_body.required ? html`<span class="mono-font" style='color:var(--red)'>*</span>` : ''} 
          <span style = "font-weight:normal; margin-left:5px"> ${this.selectedRequestBodyType}</span>
          <span style="flex:1"></span>
          ${reqBodyTypeSelectorHtml}
        </div>
        ${this.request_body.description ? html`<div class="m-markdown" style="margin-bottom:12px">${unsafeHTML(marked(this.request_body.description))}</div>` : ''}
        
        ${(this.selectedRequestBodyType.includes('json') || this.selectedRequestBodyType.includes('xml') || this.selectedRequestBodyType.includes('text'))
          ? html`
            <div class="tab-panel col" style="border-width:0 0 1px 0;">
              <div class="tab-buttons row" @click="${(e) => { if (e.target.tagName.toLowerCase() === 'button') { this.activeSchemaTab = e.target.dataset.tab; } }}">
                <button class="tab-btn ${this.activeSchemaTab === 'model' ? 'active' : ''}"   data-tab = 'model'  >模型</button>
                <button class="tab-btn ${this.activeSchemaTab === 'example' ? 'active' : ''}" data-tab = 'example'>示例 </button>
              </div>
              ${html`<div class="tab-content col" style="display: ${this.activeSchemaTab === 'model' ? 'block' : 'none'}"> ${reqBodySchemaHtml}</div>`}
              ${html`<div class="tab-content col" style="display: ${this.activeSchemaTab === 'model' ? 'none' : 'block'}"> ${reqBodyExampleHtml}</div>`}
            </div>`
          : html`  
            ${reqBodyFileInputHtml}
            ${reqBodyFormHtml}`
        }
      </div>  
    `;
  }

  formDataTemplate(schema, mimeType, exampleValue = '') {
    const formDataTableRows = [];
    if (schema.properties) {
      for (const fieldName in schema.properties) {
        const fieldSchema = schema.properties[fieldName];
        const fieldType = fieldSchema.type;
        const formdataPartSchema = schemaInObjectNotation(fieldSchema, {});
        const paramSchema = getTypeInfo(fieldSchema);
        const formdataPartExample = generateExample(
          '',
          fieldSchema.example ? fieldSchema.example : '',
          fieldSchema,
          'json',
          false,
          'text',
        );

        formDataTableRows.push(html`
        <tr> 
          <td rowspan="${this.allowTry === 'true' ? '1' : '2'}" style="width:160px; min-width:100px;">
            <div class="param-name">
              ${fieldSchema.required
                ? html`<span style='color:var(--red);'>*</span>${fieldName}`
                : html`${fieldName}`
              }
            </div>
            <div class="param-type">${paramSchema.type}</div>
          </td>  
          <td style="${fieldType === 'object' ? 'width:100%; padding:0;' : 'width:160px;'} min-width:100px;" colspan="${fieldType === 'object' ? 2 : 1}">
            ${fieldType === 'array'
              ? fieldSchema.items.format === 'binary'
                ? html`
                <div class="file-input-container col" style='align-items:flex-end;' @click="${(e) => this.onAddRemoveFileInput(e, fieldName, mimeType)}">
                  <div class='input-set row'>
                    <input 
                      type = 'file'
                      style = "width:200px" 
                      data-pname = "${fieldName}" 
                      data-ptype = "${mimeType.includes('form-urlencode') ? 'form-urlencode' : 'form-data'}"
                      data-array = "false" 
                      data-file-array = "true" 
                    />
                    <button class="file-input-remove-btn"> &#x2715; </button>
                  </div>  
                  <button class="m-btn primary file-input-add-btn" style="margin:2px 25px 0 0; padding:2px 6px;">添加</button>
                </div>  
                `
                : html`
                  <tag-input
                    style = "width:160px; background:var(--input-bg);" 
                    data-ptype = "${mimeType.includes('form-urlencode') ? 'form-urlencode' : 'form-data'}"
                    data-pname = "${fieldName}"
                    data-array = "true"
                    placeholder = "add-multiple &#x2b90;"
                  >
                  </tag-input>
                `
              : html`
                ${fieldType === 'object'
                  ? html`
                  <div class="tab-panel row" style="min-height:220px; border-left: 6px solid var(--light-border-color); align-items: stretch;">
                    <div style="width:24px; background-color:var(--light-border-color)">
                      <div class="row" style="flex-direction:row-reverse; width:160px; height:24px; transform:rotate(270deg) translateX(-160px); transform-origin:top left; display:block;" @click="${(e) => {
                        if (e.target.classList.contains('v-tab-btn')) {
                          const tab = e.target.dataset.tab;
                          if (tab) {
                            const tabPanelEl = e.target.closest('.tab-panel');
                            const selectedTabBtnEl = tabPanelEl.querySelector(`.v-tab-btn[data-tab="${tab}"]`);
                            const otherTabBtnEl = [...tabPanelEl.querySelectorAll(`.v-tab-btn:not([data-tab="${tab}"])`)];
                            const selectedTabContentEl = tabPanelEl.querySelector(`.tab-content[data-tab="${tab}"]`);
                            const otherTabContentEl = [...tabPanelEl.querySelectorAll(`.tab-content:not([data-tab="${tab}"])`)];
                            selectedTabBtnEl.classList.add('active');
                            selectedTabContentEl.style.display = 'block';
                            otherTabBtnEl.forEach((el) => { el.classList.remove('active'); });
                            otherTabContentEl.forEach((el) => { el.style.display = 'none'; });
                          }
                        }
                        if (e.target.tagName.toLowerCase() === 'button') { this.activeSchemaTab = e.target.dataset.tab; }
                      }}">
                        <button class="v-tab-btn ${this.activeSchemaTab === 'model' ? 'active' : ''}" data-tab = 'model'>模型</button>
                        <button class="v-tab-btn ${this.activeSchemaTab === 'example' ? 'active' : ''}" data-tab = 'example'>示例</button>
                      </div>
                    </div>  
                    ${html`
                      <div class="tab-content col" data-tab = 'model' style="display:${this.activeSchemaTab === 'model' ? 'block' : 'none'}; padding-left:5px; width:100%;"> 
                        <schema-tree
                          .data = '${formdataPartSchema}'
                          schema-expand-level = "${this.schemaExpandLevel}"
                          schema-description-expanded = "${this.schemaDescriptionExpanded}"
                        > </schema-tree>
                      </div>`
                    }
                    ${html`
                      <div class="tab-content col" data-tab = 'example' style="display:${this.activeSchemaTab === 'example' ? 'block' : 'none'}; padding-left:5px; width:100%"> 
                        <textarea 
                          class = "textarea"
                          style = "width:100%; border:none; resize:vertical;" 
                          data-array = "false" 
                          data-ptype = "${mimeType.includes('form-urlencode') ? 'form-urlencode' : 'form-data'}"
                          data-pname = "${fieldName}"
                          spellcheck = "false"
                        >${formdataPartExample[0].exampleValue}</textarea>
                        <!-- This textarea(hidden) is to store the original example value, in focused mode on navbar change it is used to update the example text -->
                        <textarea data-pname = "hidden-${fieldName}" data-ptype = "${mimeType.includes('form-urlencode') ? 'hidden-form-urlencode' : 'hidden-form-data'}" style="display:none">${formdataPartExample[0].exampleValue}</textarea>
                      </div>`
                    }
                  </div>`
                  : html`
                    ${this.allowTry === 'true' || fieldSchema.example
                      ? html`<input
                          .value = "${live(fieldSchema.example || '')}"
                          spellcheck = "false"
                          type = "${fieldSchema.format === 'binary' ? 'file' : fieldSchema.format === 'password' ? 'password' : 'text'}"
                          style = "width:200px"
                          data-ptype = "${mimeType.includes('form-urlencode') ? 'form-urlencode' : 'form-data'}"
                          data-pname = "${fieldName}"
                          data-array = "false"
                        />`
                      : ''
                    }
                    `
                  }`
              }
          </td>
          ${fieldType === 'object'
            ? ''
            : html`
              <td>
                <div class="param-constraint">
                  ${paramSchema.default || paramSchema.constrain || paramSchema.allowedValues
                    ? html`
                      <div class="param-constraint">
                        ${paramSchema.default ? html`<span style="font-weight:bold">默认值: </span>${paramSchema.default}<br/>` : ''}
                        ${paramSchema.constrain ? html`${paramSchema.constrain}<br/>` : ''}
                        ${paramSchema.allowedValues ? html`<span style="font-weight:bold">可选值: </span>${paramSchema.allowedValues}` : ''}
                      </div>`
                    : ''
                  }
                </div>
              </td>`
          }
        </tr>
        ${fieldType === 'object'
          ? ''
          : html`
            <tr>
              ${this.allowTry === 'true' ? html`<td style="border:none"> </td>` : ''}
              <td colspan="2" style="border:none; margin-top:0; padding:0 5px 8px 5px;"> 
                <span class="m-markdown-small">${unsafeHTML(marked(fieldSchema.description || ''))}</span>
              </td>
            </tr>
          `
        }`);
      }
      return html`
        <table style="width:100%;" class="m-table">
          ${formDataTableRows}
        </table>
      `;
    }

    return html`
      <textarea
        class = "textarea dynamic-form-param ${mimeType}" 
        spellcheck = "false"
        data-pname="dynamic-form" 
        data-ptype="${mimeType}"  
        style="width:100%"
      >${exampleValue}</textarea>
      ${schema.description ? html`<span class="m-markdown-small">${unsafeHTML(marked(schema.description))}</span>` : ''}
    `;
  }

  onAddRemoveFileInput(e, pname, ptype) {
    if (e.target.tagName.toLowerCase() !== 'button') {
      return;
    }

    if (e.target.classList.contains('file-input-remove-btn')) {
      // Remove File Input Set
      const el = e.target.closest('.input-set');
      el.remove();
      return;
    }
    const el = e.target.closest('.file-input-container');

    // Add File Input Set

    // Container
    const newInputContainerEl = document.createElement('div');
    newInputContainerEl.setAttribute('class', 'input-set row');

    // File Input
    const newInputEl = document.createElement('input');
    newInputEl.type = 'file';
    newInputEl.style = 'width:200px; margin-top:2px;';
    newInputEl.setAttribute('data-pname', pname);
    newInputEl.setAttribute('data-ptype', ptype.includes('form-urlencode') ? 'form-urlencode' : 'form-data');
    newInputEl.setAttribute('data-array', 'false');
    newInputEl.setAttribute('data-file-array', 'true');

    // Remover Button
    const newRemoveBtnEl = document.createElement('button');
    newRemoveBtnEl.setAttribute('class', 'file-input-remove-btn');
    newRemoveBtnEl.innerHTML = '&#x2715;';

    newInputContainerEl.appendChild(newInputEl);
    newInputContainerEl.appendChild(newRemoveBtnEl);
    el.insertBefore(newInputContainerEl, e.target);
    // el.appendChild(newInputContainerEl);
  }

  disconnectedCallback() {
    // Cleanup ObjectURL forthe blob data if this component created one
    if (this.responseBlobUrl) {
      URL.revokeObjectURL(this.responseBlobUrl);
      this.responseBlobUrl = '';
    }
    super.disconnectedCallback();
  }
}

// Register the element with the browser
customElements.define('api-request', ApiRequest);
