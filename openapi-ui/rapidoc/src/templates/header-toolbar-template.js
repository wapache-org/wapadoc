import { html } from 'lit-element';

/* eslint-disable indent */
export default function headerTemplate() {
  return html`
<div id="header-toolbar-container" style="display: flex; flex-direction:row;margin-left:10px;">

  <div style="display: flex;justify-content: center; margin: 2px 2px">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">主题:</span>
    <button class='header-btn' @click='${this.setAttribute2}' key="theme" value="dark" >暗</button>
    <button class='header-btn' @click='${this.setAttribute2}' key="theme" value="light" >亮</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">布局:</span>
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='view' >列表</button>
    <!--<button class='header-btn' @click='${this.setAttribute2}' 'render-style' value='read')" >阅读</button>-->
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='focused' >导航</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">导航:</span>
    <button class='header-btn' @click='${this.toggleAttribute}' key='show-info' title="是否显示接口说明">说明</button>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-server-selection' title="是否显示服务器地址">服务器</button>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-authentication' title="是否显示认证授权">授权</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='path' title="仅显示路径">路</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='operation' title="仅显示操作ID">操</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='summary' title="仅显示操作描述">简</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='path,operation' title="显示操作ID和路径">路+操</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='path,summary' title="显示操作描述和路径">路+简</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='operation,summary' title="显示操作描述和操作ID">操+简</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='use-path-in-nav-bar' value='path,operation,summary' title="显示操作ID,操作描述和路径">路+操+简</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">内容:</span>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-try' title="Toggle Try mode">调试</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='endpoint-body-layout' value='row' >行</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='endpoint-body-layout' value='column' >列</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='schema-style' value='tree' >树</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='schema-style' value='table' >表</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0"">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">字体:</span>
    <button class='header-btn' @click='${this.setAttribute2}' key='font-size' value='default' >默认</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='font-size' value='large' >较大</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='font-size' value='largest' >最大</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0"">
  <div style="text-align: center; padding: 0 0 0 0; color:#47AFE8">其他:</div>
    <!--
    <button class='header-btn' onclick="this.toggleAttribute('show-header')" title="Toggle Header">&#x2b90;</button>
    <button class='header-btn' onclick="this.toggleAttribute('allow-spec-url-load')" title="Toggle Spec URL Load">&#x2b90;</button>
    -->
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-spec-file-load' title="是否允许加载本地文件">文件</button>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-search' title="是否允许搜索">搜索</button>
  </div>
</div>`;
  }
/* eslint-enable indent */
