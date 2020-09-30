import { LitElement, html, css } from 'lit-element';
import Prism from 'prismjs';

import { unsafeHTML } from 'lit-html/directives/unsafe-html';
import TableStyles from '@/styles/table-styles';
import FlexStyles from '@/styles/flex-styles';
import InputStyles from '@/styles/input-styles';
import FontStyles from '@/styles/font-styles';
import BorderStyles from '@/styles/border-styles';
import TabStyles from '@/styles/tab-styles';
import PrismStyles from '@/styles/prism-styles';
import { copyToClipboard } from '@/utils/common-utils';
import '@/components/json-tree';
import '@/components/schema-tree';
import '@/components/tag-input';

export default class ApiTry extends LitElement {
  constructor() {
    super();
    this.responseMessage = '';
    this.responseStatus = 'success';
    this.responseHeaders = '';
    this.responseText = '';
    this.responseUrl = '';
    this.curlSyntax = '';
    this.activeResponseTab = 'response'; // allowed values: response, headers, curl
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
    <div>
      ${this.allowTry === 'false' ? '' : this.apiCallTemplate()}
    </div>
    `;
  }

  apiResponseTabTemplate() {
    const responseFormat = this.responseHeaders.includes('json') ? 'json' : (this.responseHeaders.includes('html') || this.responseHeaders.includes('xml')) ? 'html' : '';
    return html`
      <div class="row" style="font-size:var(--font-size-small); margin:5px 0">
        <div class="response-message ${this.responseStatus}">响应头: ${this.responseMessage}</div>
        <div style="flex:1"></div>
        <button class="m-btn" style="padding: 6px 0px;width:60px" @click="${this.clearResponseData}">重置</button>
      </div>
      <div class="tab-panel col" style="border-width:0 0 1px 0;">
        <div id="tab_buttons" class="tab-buttons row" @click="${(e) => {
    if (e.target.classList.contains('tab-btn') === false) { return; }
    this.activeResponseTab = e.target.dataset.tab;
  }}">
          <button class="tab-btn ${this.activeResponseTab === 'response' ? 'active' : ''}" data-tab = 'response' > 响应</button>
          <button class="tab-btn ${this.activeResponseTab === 'headers' ? 'active' : ''}"  data-tab = 'headers' > 响应头</button>
          <button class="tab-btn ${this.activeResponseTab === 'curl' ? 'active' : ''}" data-tab = 'curl'>CURL</button>
        </div>
        ${this.responseIsBlob
    ? html`
            <div class="tab-content col" style="flex:1; display:${this.activeResponseTab === 'response' ? 'flex' : 'none'};">
              <button class="m-btn thin-border mar-top-8" style="width:135px" @click="${this.downloadResponseBlob}">下载</button>
              ${this.responseBlobType === 'view'
    ? html`<button class="m-btn thin-border mar-top-8" style="width:135px" @click="${this.viewResponseBlob}">查看 (新页签)</button>`
    : ''
}
            </div>`
    : html`
            <div class="tab-content col m-markdown" style="flex:1;display:${this.activeResponseTab === 'response' ? 'flex' : 'none'};" >
              <button class="toolbar-btn" style = "position:absolute; top:12px; right:2px" @click='${(e) => { copyToClipboard(this.responseText, e); }}'> 复制 </button>
              <pre style="white-space:pre; max-height:400px; overflow:auto">${responseFormat
    ? html`<code>${unsafeHTML(Prism.highlight(this.responseText, Prism.languages[responseFormat], responseFormat))}</code>`
    : `${this.responseText}`
}
              </pre>
            </div>`
}
        <div class="tab-content col m-markdown" style="flex:1;display:${this.activeResponseTab === 'headers' ? 'flex' : 'none'};" >
          <button  class="toolbar-btn" style = "position:absolute; top:12px; right:2px" @click='${(e) => { copyToClipboard(this.responseHeaders, e); }}'> 复制 </button>
          <pre style="white-space:pre"><code>${unsafeHTML(Prism.highlight(this.responseHeaders, Prism.languages.css, 'css'))}</code></pre>
        </div>
        <div class="tab-content col m-markdown" style="flex:1;display:${this.activeResponseTab === 'curl' ? 'flex' : 'none'};">
          <button  class="toolbar-btn" style = "position:absolute; top:12px; right:2px" @click='${(e) => { copyToClipboard(this.curlSyntax.replace(/\\$/, ''), e); }}'> 复制 </button>
          <pre style="white-space:pre"><code>${unsafeHTML(Prism.highlight(this.curlSyntax.trim().replace(/\\$/, ''), Prism.languages.shell, 'shell'))}</code></pre>
        </div>
      </div>`;
  }

  apiCallTemplate() {
    let selectServerDropdownHtml = '';

    if (this.servers && this.servers.length > 0) {
      selectServerDropdownHtml = html`
        <select style="min-width:100px;" @change='${(e) => { this.serverUrl = e.target.value; }}'>
          ${this.servers.map((v) => html`<option value = "${v.url}"> ${v.url} - ${v.description} </option>`)}
        </select>
      `;
    }
    const selectedServerHtml = html`
      <div style="display:flex; flex-direction:column;">
        ${selectServerDropdownHtml}
        ${this.serverUrl
    ? html`
            <div style="display:flex; align-items:baseline;">
              <div style="font-weight:bold; padding-right:5px;">服务器地址</div> 
              <span class = "gray-text"> ${this.serverUrl} </span>
            </div>
          `
    : ''
}
      </div>  
    `;

    return html`
    <div style="display:flex; align-items:flex-end; margin:16px 0; font-size:var(--font-size-small);">
      <div style="display:flex; flex-direction:column; margin:0; width:calc(100% - 60px);">
        <div style="display:flex; flex-direction:row; align-items:center; overflow:hidden;"> 
          ${selectedServerHtml}
        </div>
        <div style="display:flex;">
          <div style="font-weight:bold; padding-right:5px;">认证信息</div>
          ${this.api_keys != null && this.api_keys.length > 0
    ? html`<div style="color:var(--blue); overflow:hidden;"> 
                ${this.api_keys.length === 1
    ? `${this.api_keys[0].typeDisplay}' in ${this.api_keys[0].in}`
    : `${this.api_keys.length} API keys applied`
} 
              </div>`
    : html`<div style="color:var(--red)">没有认证信息</div>`
}
        </div>
      </div>
      <button class="m-btn primary" @click="${this.onTryClick}">试一试</button>
    </div>
    ${this.responseMessage === '' ? '' : this.apiResponseTabTemplate()}
    `;
  }
  /* eslint-enable indent */

  async onTryClick(e) {
    const me = this;
    const tryBtnEl = e.target;
    let fetchUrl;
    let curlUrl;
    let curl = '';
    let curlHeaders = '';
    let curlData = '';
    let curlForm = '';

    let requestPanelEl = e.target.closest('.request-panel');
    if (!requestPanelEl) {
      requestPanelEl = e.target.getRootNode().host.closest('.request-panel');
    }
    if (!requestPanelEl) {
      requestPanelEl = e.target.getRootNode().host.getRootNode().host.closest('div.req-resp-container').querySelector('api-request').shadowRoot.querySelector('.request-panel');
    }

    const pathParamEls = [...requestPanelEl.querySelectorAll("[data-ptype='path']")];
    const queryParamEls = [...requestPanelEl.querySelectorAll("[data-ptype='query']")];
    const queryParamObjTypeEls = [...requestPanelEl.querySelectorAll("[data-ptype='query-object']")];
    const headerParamEls = [...requestPanelEl.querySelectorAll("[data-ptype='header']")];
    const requestBodyContainerEl = requestPanelEl.querySelector('.request-body-container');

    fetchUrl = me.path;
    const fetchOptions = {
      method: this.method.toUpperCase(),
      headers: {},
    };
    // Generate URL using Path Params
    pathParamEls.map((el) => {
      fetchUrl = fetchUrl.replace(`{${el.dataset.pname}}`, encodeURIComponent(el.value));
    });

    // Query Params
    if (queryParamEls.length > 0) {
      const urlQueryParam = new URLSearchParams();
      queryParamEls.forEach((el) => {
        if (el.dataset.array === 'false') {
          if (el.value !== '') {
            urlQueryParam.append(el.dataset.pname, el.value);
          }
        } else {
          const paramSerializeStyle = el.dataset.paramSerializeStyle;
          const paramSerializeExplode = el.dataset.paramSerializeExplode;
          const vals = (el.value && Array.isArray(el.value)) ? el.value : [];
          if (paramSerializeStyle === 'spaceDelimited') {
            urlQueryParam.append(el.dataset.pname, vals.join(' '));
          } else if (paramSerializeStyle === 'pipeDelimited') {
            urlQueryParam.append(el.dataset.pname, vals.join('|'));
          } else {
            if (paramSerializeExplode === 'true') { // eslint-disable-line no-lonely-if
              vals.forEach((v) => { urlQueryParam.append(el.dataset.pname, v); });
            } else {
              urlQueryParam.append(el.dataset.pname, vals.join(','));
            }
          }
        }
      });
      fetchUrl = `${fetchUrl}?${urlQueryParam.toString()}`;
    }

    // Query Params (Dynamic - create from JSON)
    if (queryParamObjTypeEls.length > 0) {
      const urlDynQueryParam = new URLSearchParams();
      queryParamObjTypeEls.map((el) => {
        try {
          let queryParamObj = {};
          const paramSerializeStyle = el.dataset.paramSerializeStyle;
          const paramSerializeExplode = el.dataset.paramSerializeExplode;
          queryParamObj = Object.assign(queryParamObj, JSON.parse(el.value.replace(/\s+/g, ' ')));
          for (const key in queryParamObj) {
            if (typeof queryParamObj[key] === 'object') {
              if (Array.isArray(queryParamObj[key])) {
                if (paramSerializeStyle === 'spaceDelimited') {
                  urlDynQueryParam.append(key, queryParamObj[key].join(' '));
                } else if (paramSerializeStyle === 'pipeDelimited') {
                  urlDynQueryParam.append(key, queryParamObj[key].join('|'));
                } else {
                  if (paramSerializeExplode === 'true') { // eslint-disable-line no-lonely-if
                    queryParamObj[key].forEach((v) => {
                      urlDynQueryParam.append(key, v);
                    });
                  } else {
                    urlDynQueryParam.append(key, queryParamObj[key]);
                  }
                }
              }
            } else {
              urlDynQueryParam.append(key, queryParamObj[key]);
            }
          }
          fetchUrl = `${fetchUrl}?${urlDynQueryParam.toString()}`;
        } catch (err) {
          console.log('RapiDoc: unable to parse %s into object', el.value); // eslint-disable-line no-console
        }
      });
    }

    // Add authentication Query-Param if provided
    this.api_keys
      .filter((v) => (v.in === 'query'))
      .forEach((v) => {
        fetchUrl = `${fetchUrl}${fetchUrl.includes('?') ? '&' : '?'}${v.name}=${encodeURIComponent(v.finalKeyValue)}`;
      });

    // Final URL for API call
    fetchUrl = `${this.serverUrl.replace(/\/$/, '')}${fetchUrl}`;
    if (fetchUrl.startsWith('http') === false) {
      const url = new URL(fetchUrl, window.location.href);
      curlUrl = url.href;
    } else {
      curlUrl = fetchUrl;
    }
    curl = `curl -X ${this.method.toUpperCase()} "${curlUrl}" \\\n`;

    if (this.accept) {
      fetchOptions.headers.Accept = this.accept;
      curlHeaders += ` -H "Accept: ${this.accept}" \\\n`;
    }

    // Add Authentication Header if provided
    this.api_keys
      .filter((v) => (v.in === 'header'))
      .forEach((v) => {
        fetchOptions.headers[v.name] = v.finalKeyValue;
        curlHeaders += ` -H "${v.name}: ${v.finalKeyValue}" \\\n`;
      });

    // Add Header Params
    headerParamEls.map((el) => {
      if (el.value) {
        fetchOptions.headers[el.dataset.pname] = el.value;
        curlHeaders += ` -H "${el.dataset.pname}: ${el.value}" \\\n`;
      }
    });

    // Request Body Params
    if (requestBodyContainerEl) {
      const requestBodyType = requestBodyContainerEl.dataset.selectedRequestBodyType;
      if (requestBodyType.includes('form-urlencoded')) {
        // url-encoded Form Params (dynamic) - Parse JSON and generate Params
        const formUrlDynamicTextAreaEl = requestPanelEl.querySelector("[data-ptype='dynamic-form']");
        if (formUrlDynamicTextAreaEl) {
          const val = formUrlDynamicTextAreaEl.value;
          const formUrlDynParams = new URLSearchParams();
          let proceed = true;
          let tmpObj;
          if (val) {
            try {
              tmpObj = JSON.parse(val);
            } catch (err) {
              proceed = false;
              console.warn('RapiDoc: Invalid JSON provided', err); // eslint-disable-line no-console
            }
          } else {
            proceed = false;
          }
          if (proceed) {
            for (const prop in tmpObj) {
              formUrlDynParams.append(prop, JSON.stringify(tmpObj[prop]));
            }
            fetchOptions.body = formUrlDynParams;
            curlData = ` -d ${formUrlDynParams.toString()} \\\n`;
          }
        } else {
          // url-encoded Form Params (regular)
          const formUrlEls = [...requestPanelEl.querySelectorAll("[data-ptype='form-urlencode']")];
          const formUrlParams = new URLSearchParams();
          formUrlEls
            .filter((v) => (v.type !== 'file'))
            .forEach((el) => {
              if (el.dataset.array === 'false') {
                if (el.value) {
                  formUrlParams.append(el.dataset.pname, el.value);
                }
              } else {
                const vals = (el.value && Array.isArray(el.value)) ? el.value.join(',') : '';
                formUrlParams.append(el.dataset.pname, vals);
              }
            });
          fetchOptions.body = formUrlParams;
          curlData = ` -d ${formUrlParams.toString()} \\\n`;
        }
      } else if (requestBodyType.includes('form-data')) {
        const formDataParams = new FormData();
        const formDataEls = [...requestPanelEl.querySelectorAll("[data-ptype='form-data']")];
        formDataEls.forEach((el) => {
          if (el.dataset.array === 'false') {
            if (el.type === 'file' && el.files[0]) {
              formDataParams.append(el.dataset.pname, el.files[0], el.files[0].name);
              curlForm += ` -F "${el.dataset.pname}=@${el.files[0].name}" \\\n`;
            } else if (el.value) {
              formDataParams.append(el.dataset.pname, el.value);
              curlForm += ` -F "${el.dataset.pname}=${el.value}" \\\n`;
            }
          } else if (el.value && Array.isArray(el.value)) {
            el.value.forEach((v) => {
              curlForm = `${curlForm} -F "${el.dataset.pname}[]=${v}" \\\n`;
            });
            formDataParams.append(el.dataset.pname, el.value.join(','));
          }
        });
        fetchOptions.body = formDataParams;
      } else if (requestBodyType.includes('octet-stream')) {
        const bodyParamFileEl = requestPanelEl.querySelector('.request-body-param-file');
        if (bodyParamFileEl && bodyParamFileEl.files[0]) {
          fetchOptions.body = bodyParamFileEl.files[0];
          curlData = ` --data-binary @${bodyParamFileEl.files[0].name} \\\n`;
        }
      } else if (requestBodyType.includes('json') || requestBodyType.includes('xml') || requestBodyType.includes('text')) {
        const exampleTextAreaEl = requestPanelEl.querySelector('.request-body-param-user-input');
        if (exampleTextAreaEl && exampleTextAreaEl.value) {
          fetchOptions.body = exampleTextAreaEl.value;
          // curlData = ` -d ${JSON.stringify(exampleTextAreaEl.value.replace(/(\r\n|\n|\r)/gm, '')).replace(/\\"/g, "'")} \\ \n`;
          try {
            curlData = ` -d '${JSON.stringify(JSON.parse(exampleTextAreaEl.value))}' \\\n`;
          } catch (err) {
            curlData = ` -d '${exampleTextAreaEl.value.replace(/(\r\n|\n|\r)/gm, '')}' \\\n`;
          }
        }
      }
      // Common for all request-body
      if (!requestBodyType.includes('form-data')) {
        // For multipart/form-data dont set the content-type to allow creation of browser generated part boundaries
        fetchOptions.headers['Content-Type'] = requestBodyType;
      }
      curlHeaders += ` -H "Content-Type: ${requestBodyType}" \\\n`;
    }

    fetchOptions.headers['Cache-Control'] = 'no-cache';
    curlHeaders += ' -H "Cache-Control: no-cache" \\\n';

    me.responseUrl = '';
    me.responseHeaders = '';
    // me.responseText    = '';
    me.curlSyntax = '';
    me.responseStatus = 'success';
    me.responseIsBlob = false;

    me.respContentDisposition = '';
    if (me.responseBlobUrl) {
      URL.revokeObjectURL(me.responseBlobUrl);
      me.responseBlobUrl = '';
    }
    me.curlSyntax = `${curl}${curlHeaders}${curlData}${curlForm}`;
    try {
      tryBtnEl.disabled = true;
      // await wait(1000);
      const resp = await fetch(fetchUrl, fetchOptions);
      tryBtnEl.disabled = false;
      me.responseStatus = resp.ok ? 'success' : 'error';
      me.responseMessage = `${resp.status} ${resp.statusText}`;
      me.responseUrl = resp.url;
      resp.headers.forEach((hdrVal, hdr) => {
        me.responseHeaders = `${me.responseHeaders}${hdr.trim()}: ${hdrVal}\n`;
      });
      const contentType = resp.headers.get('content-type');
      if (contentType) {
        if (contentType.includes('json')) {
          resp.json().then((respObj) => {
            me.responseText = JSON.stringify(respObj, null, 2);
          });
        } else if (RegExp('7z|octet-stream|tar|zip').test(contentType)) {
          me.responseIsBlob = true;
          me.responseBlobType = 'download';
        } else if (RegExp('^audio|^image|pdf|^video').test(contentType)) {
          me.responseIsBlob = true;
          me.responseBlobType = 'view';
        } else {
          resp.text().then((respText) => {
            me.responseText = respText;
          });
        }
        if (me.responseIsBlob) {
          const contentDisposition = resp.headers.get('content-disposition');
          me.respContentDisposition = contentDisposition ? contentDisposition.split('filename=')[1] : 'filename';
          resp.blob().then((respBlob) => {
            me.responseBlobUrl = URL.createObjectURL(respBlob);
          });
        }
      } else {
        resp.text().then((respText) => {
          me.responseText = respText;
        });
      }
    } catch (err) {
      tryBtnEl.disabled = false;
      me.responseMessage = `${err.message} (CORS or Network Issue)`;
    }
  }

  downloadResponseBlob() {
    if (this.responseBlobUrl) {
      const a = document.createElement('a');
      document.body.appendChild(a);
      a.style = 'display: none';
      a.href = this.responseBlobUrl;
      a.download = this.respContentDisposition;
      a.click();
      a.remove();
    }
  }

  viewResponseBlob() {
    if (this.responseBlobUrl) {
      const a = document.createElement('a');
      document.body.appendChild(a);
      a.style = 'display: none';
      a.href = this.responseBlobUrl;
      a.target = '_blank';
      a.click();
      a.remove();
    }
  }

  clearResponseData() {
    this.responseUrl = '';
    this.responseHeaders = '';
    this.responseText = '';
    this.responseStatus = 'success';
    this.responseMessage = '';
    this.responseIsBlob = false;
    this.responseBlobType = '';
    this.respContentDisposition = '';
    if (this.responseBlobUrl) {
      URL.revokeObjectURL(this.responseBlobUrl);
      this.responseBlobUrl = '';
    }
  }
}

// Register the element with the browser
customElements.define('api-try', ApiTry);
