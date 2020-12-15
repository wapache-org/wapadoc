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
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='focused' >默认</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='view' >传统</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='read' >阅读</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='render-style' value='document' >文档</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">导航:</span>
    <button class='header-btn' @click='${this.toggleAttribute}' key='show-info' title="是否显示接口说明">说明</button>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-server-selection' title="是否显示服务器地址">服务器</button>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-authentication' title="是否显示认证授权">授权</button>
    <select class='header-btn' @change='${this.setAttribute3}' style="width:160px;" key='use-path-in-nav-bar'>
      <option value="summary">仅显示操作名称</option>
      <option value="path">仅显示路径</option>
      <option value="operation">仅显示操作ID</option>
      <option value="path,operation">显示路径和操作ID</option>
      <option value="path,summary">显示路径和操作名称</option>
      <option value="operation,summary">显示操作ID和名称</option>
      <option value="path,operation,summary">显示路径、操作ID和名称</option>
    </select>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">内容:</span>
    <button class='header-btn' @click='${this.toggleAttribute}' key='allow-try' title="Toggle Try mode">调试</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='endpoint-body-layout' value='row' >行</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='endpoint-body-layout' value='column' >列</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='schema-style' value='tree' >树</button>
    <button class='header-btn' @click='${this.setAttribute2}' key='schema-style' value='table' >表</button>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
    <span style="text-align: center; padding: 0 0 0 0; color:#47AFE8">字体:</span>
    <select class='header-btn' @change='${this.setAttribute3}' key='font-size'>
      <option value="summary">默认</option>
      <option value="path">较大</option>
      <option value="operation">最大</option>
    </select>
  </div>

  <div style="display: flex;justify-content: center; margin: 2px 0">
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
