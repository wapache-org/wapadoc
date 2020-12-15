import { LitElement, html, css } from 'lit-element';

export default class ApiDocument extends LitElement {
  /* eslint-disable indent */
  render() {
    return html`
    <div class='tags' tabindex="0">
      ${Array.isArray(this.value) && this.value.length > 0
        ? html`${this.value.map((v) => html`<span class='tag'> ${v} </span>`)}`
        : ''
      }
      <input type="text" class='editor' @paste="${this.afterPaste}" @keydown="${this.afterKeyDown}" placeholder="${this.placeholder}">
    </div>
  `;
  }
  /* eslint-enable indent */

  static get properties() {
    return {
      spec: { type: Object },
    };
  }

  static get styles() {
    return [css`
    body {
      line-height: 22px;
      font: 14px Helvetica Neue,Helvetica,PingFang SC,Tahoma,Arial,sans-serif;
    }

    ul, ol, li {
      list-style:none;
      padding-left: 12px;
    }

    ul {
      color: #fff;
      background-color: #2F4056;
    }

    li {
      color: #fff;
      line-height: 24px;
    }

    li a {
      color: #fff;
      height: 24px;
      line-height: 24px;
      text-decoration: none;
    }

    li a:link,
    li a:visited,
    li a:hover,
    li a:active {
      color: #fff;
    }

    table {
      margin: 10px 0;
      width: 100%;
      border-collapse: collapse;
      border-spacing: 0;
      background-color: #fff;
      color: #666;
    }

    table td {
      border: 1px solid black;
    }
    table tbody tr:hover,
    table thead tr {
      background-color: #f2f2f2;
    }
    table td, table th {
      position: relative;
      padding: 9px 15px;
      min-height: 20px;
      line-height: 20px;
      font-size: 14px;
    }
    table td,
    table th {
      border-width: 1px;
      border-style: solid;
      border-color: #e6e6e6;
    }

    table th {
      text-align: left;
      font-weight: 400;
    }

    #api-table-of-content
    ,#database-table-of-content
    {
      position: fixed;
      width: 25%;
      left: 0;
      top: 0;
      bottom: 0;
      overflow: auto;
      background-color: #2F4056;
    }

    #database-table-of-content
    {
      width: 300px;
    }

    #api-table-of-content::-webkit-scrollbar,
    #database-table-of-content::-webkit-scrollbar {/*滚动条整体样式*/
      width: 10px;     /*高宽分别对应横竖滚动条的尺寸*/
      height: 1px;
    }

    #api-table-of-content::-webkit-scrollbar-thumb,
    #database-table-of-content::-webkit-scrollbar-thumb {/*滚动条里面小方块
      border-radius: 10px;
      -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);*/
      background: #1F3056;
    }

    #api-table-of-content::-webkit-scrollbar-track,
    #database-table-of-content::-webkit-scrollbar-track {/*滚动条里面轨道
      -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
      border-radius: 10px;*/
      background: #2F4056;
    }


    #api-toc-search {
      position: fixed;
      left: 0;
      top: 0;
      width: 25%;
      height: 50px;
      background-color: #2F4056;
    }

    #database-table-of-content #api-toc-search
    {
      width: 300px;
    }

    #api-toc-search-input {
      position: fixed;
      left: 12px;
      top: 12px;
      width: 24%;
      height: 26px;
    }

    #database-table-of-content #api-toc-search-input
    {
      width: 276px;
    }

    #api-toc-root {
      margin: 50px 0;
      padding: 10px 0;
    }

    #api-document
    {
      margin-left: 25%;
    }

    #database-document
    {
      margin-left: 300px;
    }

    #api-header
    , #api-interface-groups
    , #api-models
    {
      width: 96%;
      margin: auto;
    }

    #database-header
    , #database-table-groups
    {
      margin: auto;
    }

    .database-table-group h2 {
      border-bottom: 1px solid #e6e6e6;
    }

    `];
  }

  _example(){

    const example = {
      header: {
        name: '',
        description: '',
      },
      groups: [{
        name: '',
        description: '',
        operations:[{ // 引用operations里的对象
          id: '',
          name: '',
          description: '',
          method: '',
          path: '',
          parameters: [{
            index: 1,
            name: '',
            location: '',
            type: '',
            format: '',
            model: '', // schema名称, 用于生成链接或者找到对应的结构定义
            required: '',
            description: '',
          }],
          responses: [{
            index: 1,
            code: '',
            headers: [{

            }],
            type: '',
            format: '',
            model: '', // schema名称, 用于生成链接或者找到对应的结构定义
            required: '',
            description: '',
          }]
        }]
      }],
      operations:[{
        id: '',
        name: '',
        description: '',
        method: '',
        path: '',
        parameters: [{
          index: 1,
          name: '',
          location: '',
          type: '',
          format: '',
          model: '', // schema名称, 用于生成链接或者找到对应的结构定义
          required: '',
          description: '',
        }],
        responses: [{
          index: 1,
          code: '',
          headers: [{

          }],
          type: '',
          format: '',
          model: '', // schema名称, 用于生成链接或者找到对应的结构定义
          required: '',
          description: '',
        }]
      }],
      models: [{
        index: 1,
        id:'', // 用于引用
        name: '',
        type: '',
        format: '',
        description: '',
        properties: [{
          index: 1,
          name: '',
          type: '',
          format: '',
          required: '',
          description: '',
        }]
      }]
    }
  }

  convert(){
    // 将OpenAPI的对象转换成模板需要的对象

    let doc = {
      name: this.resolvedSpec.info.title,
      description: this.resolvedSpec.info.description,
      groups: [],
      operations: [],
      models: []
    }

    let schemas = this.resolvedSpec.components.schemas;
    schemas?.map((schema) => {

    });

    const paths = {};
    const operationMap = {};
    let tags = this.resolvedSpec.tags;
    tags.map((tag) => {

      let group = {
        name: tag.summary,
        description: tag.description,
        operations: [],
      };

      tag.paths.map((path) => {

        const pid = path.method + path.path;
        paths[pid] = path;

        const oid = path.operationId;
        let operation = operationMap[oid];
        if(!operation){
          operation = {
            id: oid,
            name: path.summary,
            description: path.description,
            method: path.method,
            path: path.path,
            parameters: [],
            responses: []
          };
  
          // path.parameters
          // path.requestBody
  
          // path.responses

          operationMap[oid] = operation;
          doc.operations.push(operation);
        }
        group.operations.push(operation);
      });

      doc.groups.push(group);

    });

    return doc;
  }
}
// Register the element with the browser
customElements.define('api-document', Document);
