import { html } from 'lit-element';

const wordMode = true;

function convertSchemeToModel(context, schema) {
  const component = schema.component || {};
  const model = {
    id: schema.id,
    ref: `#/components/schemas/${schema.name}`,
    type: component.type,
    item: schema.items,
    format: schema.format || schema.items?.format || '',
    name: schema.name,
    title: schema.title || schema.name,
    description: schema.description,
    properties: [],
  };

  for (const name in component.properties || {}) {
    const property = component.properties[name];
    const propertyModel = {
      id: `${model.id}.${name}`,
      ref: `#/components/schemas/${model.id}.${name}`,
      type: property.type,
      item: property.items,
      format: property.format || property.items?.format || '',
      name,
      title: property.title || name,
      description: property.description,
    };

    // TODO 递归

    model.properties.push(propertyModel);
  }

  if (context) {
    ''.toString(); // TODO 记录引用, 不重复创建对象
  }

  return model;
}

function convertSpecToDocument() {
  // 将OpenAPI的对象转换成模板需要的对象
  if (!this.resolvedSpec) {
    return {
      title: '',
      description: '',
      groups: [],
      paths: [],
      operations: [],
      models: [],
    };
  }

  const doc = {
    title: this.resolvedSpec.info.title,
    description: this.resolvedSpec.info.description,
    groups: [],
    paths: [],
    operations: [],
    models: [],
  };

  const modelMap = {};
  this.resolvedSpec.components.map((component) => {
    if (component.name === 'Schemas') {
      (component.subComponents || []).map((schema) => {
        const model = convertSchemeToModel.call(this, doc, schema);
        doc.models.push(model);
        modelMap[model.ref] = model;
      });
    }
  });

  const pathMap = {};
  const operationMap = {};
  const tags = this.resolvedSpec.tags;
  tags.map((tag) => {
    const group = {
      id: tag.name,
      name: tag.name,
      title: tag.title,
      description: tag.description,
      paths: [],
      operations: [],
    };

    const groupPathMap = {};
    tag.paths.map((path) => {
      const pid = path.path;
      let docPath = pathMap[pid];
      if (!docPath) {
        docPath = {
          name: path.name,
          title: path.title,
          description: path.description,
          operations: [],
          groups: [],
        };
        pathMap[pid] = docPath;
        doc.paths.push(docPath);
      }
      docPath.groups.push(group);

      let groupPath = groupPathMap[pid];
      if (!groupPath) {
        groupPath = {
          name: path.name,
          title: path.title,
          description: path.description,
          operations: [],
        };
        groupPathMap[pid] = groupPath;
        group.paths.push(groupPath);
      }

      const oid = path.operationId;
      let operation = operationMap[oid];
      if (!operation) {
        operation = {
          id: oid,
          name: path.summary,
          description: path.description,
          method: path.method.toUpperCase(),
          path: path.path,
          parameters: [],
          responses: [],
          groups: [],
        };

        path.parameters.sort((a, b) => a.in.localeCompare(b.in)).map((parameter) => {
          const schema = parameter.schema || {};
          const location = parameter.in.toUpperCase();
          const param = {
            name: parameter.name,
            title: schema.title || schema.items?.title || '',
            type: schema.type,
            item: schema.items,
            format: schema.format || schema.items?.format || '',
            model: modelMap[schema.$$ref] || modelMap[schema.items?.$$ref],
            description: parameter.description || schema.items?.description || '',
            location,
            required: parameter.required,
          };

          operation.parameters.push(param);
        });

        if (path.requestBody) {
          const schema = path.requestBody.schema || {};
          const param = {
            name: path.requestBody.name || '{BODY}',
            title: schema.title || schema.items?.title || '请求消息体',
            type: schema.type,
            item: schema.items,
            format: schema.format || schema.items?.format || '',
            model: modelMap[schema.$$ref] || modelMap[schema.items?.$$ref],
            description: path.requestBody.description || schema.description || schema.items?.description || '',
            location: 'BODY',
            required: path.requestBody.required || schema.required,
          };
          operation.parameters.push(param);
        }

        for (const code in path.responses) {
          const response = path.responses[code];
          const medias = response.content || {
            '*/*': {
              schema: {
                name: response.name,
                title: response.title,
                description: response.description,
              },
            },
          };
          for (const media in medias) {
            const schema = medias[media].schema || {};
            const resp = {
              code: code === 'default' ? '' : code,
              name: schema.name || schema.items?.title || '',
              title: schema.title,
              media,
              type: schema.type,
              item: schema.items,
              model: modelMap[schema.$$ref] || modelMap[schema.items?.$$ref],
              format: schema.format || schema.items?.format || '',
              description: schema.description || schema.items?.description || response.description || '',
              required: schema.required,
            };
            operation.responses.push(resp);
          }
        }

        operation.responses.sort((a, b) => a.code.localeCompare(b.code));

        operationMap[oid] = operation;
        doc.operations.push(operation);
      }
      operation.groups.push(group);
      group.operations.push(operation);
      groupPath.operations.push(operation);
    });

    doc.groups.push(group);
  });

  return doc;
}

function tableOfContentTemplate() {
  return html`
  <h1 ><a onclick="this.getRootNode().getElementById('api-document-content-top').scrollIntoView({ behavior: 'auto', block: 'start' })">导航目录</a></h1>
  <ul>
    <li>
      <a onclick="this.getRootNode().getElementById('api-operation-groups').scrollIntoView({ behavior: 'auto', block: 'start' })" >服务接口定义</a>
      <ul>
        ${this.groups.map((group) => html`
        <li>
          <a onclick="this.getRootNode().getElementById('api-operation-group-${group.id}').scrollIntoView({ behavior: 'auto', block: 'start' })">${group.title || group.name}</a>
          <ul>
          ${group.operations.map((operation) => html`
            <li>
              <a onclick="this.getRootNode().getElementById('api-operation-${operation.id}').scrollIntoView({ behavior: 'auto', block: 'start' })">${operation.title || operation.name}</a>
            </li>
          `)}
          </ul>
        </li>
        `)}
      </ul>
    </li>
    <li>
      <a onclick="this.getRootNode().getElementById('api-models').scrollIntoView({ behavior: 'auto', block: 'start' })" >数据模型定义</a>
      <ul>
      ${this.models.map((model) => html`
        <li>
          <a onclick="this.getRootNode().getElementById('api-model-${model.id}').scrollIntoView({ behavior: 'auto', block: 'start' })" >${model.title || model.name}</a>
        </li>
      `)}
      </ul>
    </li>
  </ul>
  `;
}

function schemaPropertyTemplate(index) {
  return html`
    <tr class="api-schema-table-row">
        <td>${index}</td>
        <td>${this.name}${this.required ? html`<a style="color:red;"> * </a>` : ''}</td>
        <td>${this.title}</td>
        <td>${this.type}</td>
        <td>${this.format}</td>
        <td>${this.description}</td>
    </tr>
    `;
}

function schemaTemplate() {
  return html`
    <div class="api-model" id="api-model-${this.id}">
        <h3>${this.name}</h3>
        <p>${this.description}</p>
        <table class="api-model-table">
            <thead>
            <tr>
                <th class="width-4em">序号</th>
                <th>属性</th>
                <th>名称</th>
                <th>类型</th>
                <th>格式</th>
                <th>说明</th>
            </tr>
            </thead>
            <tbody>
            ${this.properties.map((property, index) => schemaPropertyTemplate.call(property, index))}
            </tbody>
        </table>
    </div>
    `;
}

function operationResponseTemplate(index) {
  return html`
    <tr class="api-operation-response-table-row">
      <td>${index}</td>
      <td>${this.code || 'DEFAULT'}</td>
      <td>${this.media}</td>
      <td>${this.type === 'array' ? `[${this.item?.type}]` : this.type}</td>
      <td>${this.format || (this.model ? this.model.name : '')}</td>
      <td>${this.description}</td>
    </tr>
    `;
}

function operationResponsesTemplate() {
  return html`
    <div class="api-operation-responses">
      <p style="font-weight:bold;">返回结果</p>
      <table class="api-operation-response-table">
        <thead>
        <tr>
          <th class="width-4em">序号</th>
          <th class="width-6em">状态码</th>
          <th>媒体</th>
          <th>类型</th>
          <th>格式</th>
          <th>说明</th>
        </tr>
        </thead>
        <tbody>
        ${this.responses.map((response, index) => operationResponseTemplate.call(response, index))}
        </tbody>
      </table>
    </div>
    `;
}

function operationParameterTemplate(index) {
  return wordMode ? html`
    <tr class="api-operation-parameter-table-row">
      <td>${index + 1}</td>
      <td>${this.title}</td>
      <td>${this.name}${this.required ? html`<a style="color:red;"> * </a>` : ''}</td>
      <td>${this.type === 'array' ? `[${this.item.type}]` : this.type}</td>
      <td>
        位置: ${this.location} ;<br/>
        格式: ${this.format || (this.model ? html`${this.model.name}` : '无')} ;<br/>
        说明: ${this.description || this.summary}
      </td>
    </tr>
  ` : html`
    <tr class="api-operation-parameter-table-row">
      <td>${index + 1}</td>
      <td>${this.location}</td>
      <td>${this.name}</td>
      <td>${this.title}</td>
      <td>${this.type === 'array' ? `[${this.item.type}]` : this.type}</td>
      <td>${this.format || (this.model ? html`<a href="#${this.model.id}">${this.model.name}</a>` : '')}</td>
      <td>${this.required}</td>
      <td>${this.description}</td>
    </tr>
    `;
}

function operationParametersTemplate() {
  return html`
    <div class="api-operation-parameters">
      <p style="font-weight:bold;">参数列表</p>
      ${this.parameters.length === 0 ? html`<p>无</p>` : html`
      <table class="api-operation-parameter-table">
        <thead>
        ${wordMode ? html`
        <tr>
            <th class="width-4em">序号</th>
            <th style="min-width:6em;">名称</th>
            <th style="min-width:3em;">参数名</th>
            <th>类型</th>
            <th>说明</th>
          </tr>
        ` : html`
        <tr>
            <th class="width-4em">序号</th>
            <th>位置</th>
            <th>参数</th>
            <th style="min-width:6em;">名称</th>
            <th>类型</th>
            <th>格式</th>
            <th>必填</th>
            <th>说明</th>
          </tr>
        `}
        </thead>
        <tbody>
        ${this.parameters.map((parameter, index) => operationParameterTemplate.call(parameter, index))}
        </tbody>
      </table>
      `}
    </div>
    `;
}

function operationInfoTemplate() {
  return html`
    <div class="api-operation-info">
      <p style="font-weight:bold;">基本信息</p>
      <table class="api-operation-info-table">
        <thead>
        <tr>
          <th class="header-column width-4em">属性</th>
          <th>取值</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td class="header-column width-4em">路径</td>
          <td>${this.method} ${this.path}</td>
        </tr>
        <tr>
          <td class="header-column width-4em">名称</td>
          <td>${this.name}</td>
        </tr>
        <tr>
          <td class="header-column width-4em">说明</td>
          <td>${this.description}</td>
        </tr>
        </tbody>
      </table>
    </div>
    `;
}

function operationTemplate() {
  return html`
    <div class="api-operation" id="api-operation-${this.id}">
      <h3>${this.name}</h3>
      ${operationInfoTemplate.call(this)}
      ${operationParametersTemplate.call(this)}
      ${operationResponsesTemplate.call(this)}
    </div>
    `;
}

function operationRowTemplate(index) {
  return html`
    <tr>
      <td>${index + 1}</td>
      <td>${this.method}</td>
      <td>${this.path}</td>
      <td>${this.name}</td>
      ${wordMode ? '' : html`
      <td>${this.summary || this.description}</td>
      `}
    </tr>
  `;
}

function operationGroupTemplate() {
  return html`
    <div class="api-operation-group" id="api-operation-group-${this.id}">
      <h2 class="api-operation-group-title">${this.title || this.name}</h2>
      <div class="api-operation-group-description">
        <p>${this.description}</p>
      </div>
      <div class="api-operation-group-operation-list">
        <table class="api-operation-list-table">
          <caption>${this.title || this.name}操作列表 - 共 ${this.paths.length} 个路径 ${this.operations.length} 个操作</caption>
          <thead>
          <tr>
            <th class="width-4em">序号</th>
            <th class="width-6em">操作</th>
            <th>路径</th>
            <th>名称</th>
          ${wordMode ? '' : html`
          <th>说明</th>
          `}
          </tr>
          </thead>
          <tbody>
          ${this.operations.map((operation, index) => operationRowTemplate.call(operation, index))}
          </tbody>
        </table>
      </div>
      <div class="api-operations">
      ${this.operations.map((operation) => operationTemplate.call(operation))}
      </div>
    </div>
    `;
}

function headerTemplate() {
  return html`
  <div id="api-header">
    <slot name="header"></slot>
    <h1 class="api-document-title">${this.title}</h1>
    <div class="api-document-description">
      <p>${this.description}</p>
    </div>
  </div>
  `;
}

function modelRowTemplate(index) {
  return html`
    <tr>
      <td>${index + 1}</td>
      <td>${this.name}</td>
      <td>${this.title}</td>
      <td>${this.summary || this.description}</td>
    </tr>
  `;
}

function operationListTemplate() {
  return html`
  <div class="api-document-operation-list">
    <table class="api-document-operation-list-table">
      <caption>${this.title || this.name}操作列表 - 共 ${this.paths.length} 个路径 ${this.operations.length} 个操作</caption>
      <thead>
      <tr>
        <th class="width-4em">序号</th>
        <th class="width-6em">操作</th>
        <th>路径</th>
        <th>名称</th>
        ${wordMode ? '' : html`
        <th>分组</th>
        <th>说明</th>
        `}
      </tr>
      </thead>
      <tbody>
      ${this.operations.map((operation, index) => html`
        <tr>
          <td>${index + 1}</td>
          <td>${operation.method}</td>
          <td>${operation.path}</td>
          <td>${operation.name}</td>
          ${wordMode ? '' : html`
          <td>${operation.groups.map((group, gIndex) => html`
            ${(gIndex === 0 ? '' : ' / ') + (group.title || group.name)}
          `)}
          </td>
          <td>${operation.summary || operation.description}</td>
          `}
        </tr>
      `)}
      </tbody>
    </table>
  </div>
  `;
}

/* eslint-disable indent */
export default function documentTemplate() {
  const doc = convertSpecToDocument.call(this);
  console.debug(doc); // eslint-disable-line no-console

  return html`
    ${this.loading === true ? html`<div class="loader"></div>` : ''}
    ${this.loadFailed === true ? html`<div style="text-align: center;margin: 16px;">加载接口文档失败</div>` : ''}
    ${this.resolvedSpec
    ? html`<div id="api-document">
    <div id="api-document-navbar" >
      ${tableOfContentTemplate.call(doc)}
    </div>
    <div id="api-document-content">
    <div id="api-document-content-top"></div>
    <div class="api-document-page paper-a4">
      ${headerTemplate.call(doc)}
      <div class="page-break"></div>
      <div id="api-operation-groups">
        <h1 id="api-operation-groups-title">服务接口定义</h1>
        <slot name="groups"></slot>
        ${operationListTemplate.call(doc)}
        ${doc.groups.map((group) => operationGroupTemplate.call(group))}
      </div>
      <div class="page-break"></div>
      <div id="api-models">
        <slot name="models"></slot>
        <h1 id="api-models-title">数据模型定义</h1>
        <div class="api-models-description"></div>
        <div class="api-models-list">
          <table class="api-models-list-table">
            <caption>数据模型列表</caption>
            <thead>
            <tr>
              <th class="width-4em">序号</th>
              <th class="width-6em">模型</th>
              <th>名称</th>
              <th>说明</th>
            </tr>
            </thead>
            <tbody>
            ${doc.models.map((model, index) => modelRowTemplate.call(model, index))}
            </tbody>
          </table>
        </div>
        ${doc.models.map((model) => schemaTemplate.call(model))}
      </div>
    </div>
    </div>
    </div>
    `
    : ''}
  `;
}
/* eslint-enable indent */
