https://www.cnblogs.com/jixiaohua/category/1602591.html

# LitElement(一)概述

[官网地址](https://lit-element.polymer-project.org/)
[Quick start](https://github.com/PolymerLabs/start-lit-element)

LitElement好像是对Polymer的简化，所以Polymer构建工具是必须的，
Polymer是个和前端三大框架同级的东西，不过不怎么火，[官网地址](https://polymer-zh.cn/3.0/start/)。

官网提供了使用[Rollup](https://rollupjs.org/guide/en)打包的例子，见[文档](https://lit-element.polymer-project.org/guide/use)和[示例项目](https://github.com/PolymerLabs/lit-element-build-rollup/blob/master/src/index.html)，这才是LitElement的正确使用方法。

可以发现Bable+Rollup打包后的项目非常的简洁，没有ploymer相关的东西，只是将依赖模块做了路径转换，并且可以用serve工具启动预览项目

它的核心是`lit-html`.
```js
import { LitElement, html } from 'lit-element';
import { until } from 'lit-html/directives/until.js';

const content = fetch('./content.txt').then(r => r.text());

html`${until(content, html`<span>Loading...</span>`)}`
```



# LitElement(二)模板编写基本语法

1、定义一个渲染模板

1.1 基本规则

要用LitElement 组件定义一个模板，必须为你的模板类写一个render方法：

```js

import { LitElement, html } from 'lit-element';

class MyElement extends LitElement {
    // 组件的 render 方法可以返回 lit-html 可以渲染的任何内容。
    // 通常，它返回单个 TemplateResult 对象（与 html 标记函数返回的类型相同）。
  render() {
      // 这个是ES6的模板字符串语法, html是一个LitElement父类定义的函数, 用于对解析后的模板字符串进行进一步处理, 然后返回.
    return html`<p>template content</p>`;
  }
}

```

1.2 动态更改模板内容

我们可以通过捕获加载消息作为属性，并根据事件设置属性来改变模板：update-properties.js

```js
import { LitElement, html } from 'lit-element';

/**
 * Use this pattern instead.
 */
class UpdateProperties extends LitElement {
  static get properties(){
    return {
      message: String
    };
  }
  constructor() {
    super();
    this.message = 'Loading';
    this.addEventListener('stuff-loaded', (e) => { this.message = e.detail } );
    this.loadStuff();
  }
  render() {
    return html`
      <p>${this.message}</p>
    `;
  }
  loadStuff() {
      // 模拟3秒后改变元素属性值，从而动态改变模板。
    setInterval(() => {
      let loaded = new CustomEvent('stuff-loaded', {
        detail: 'Loading complete.'
      });
      this.dispatchEvent(loaded);
    }, 3000);
  }
}

customElements.define('update-properties', UpdateProperties);
```

2、在模板中使用属性、循环和条件判断

```js
import { LitElement, html } from 'lit-element';

class MyElement extends LitElement {
    // 通过静态的 get properties() 函数指定属性的类型
  static get properties() {
    return {
      myString: { type: String },
      myArray: { type: Array },
      myBool: { type: Boolean }
    };
  }
  // 构造函数 constructor() 初始化属性的初始值
  constructor() {
    super();
    this.myString = 'Hello World';
    this.myArray = ['an','array','of','test','data'];
    this.myBool = true;
  }
  render() {
      // ${} 里边可以写任意的javascript语句, 还可以嵌套模板字符串
    return html`
      <p>${this.myString}</p>
      <ul>
        ${this.myArray.map(i => html`<li>${i}</li>`)} 
      </ul>
      ${this.myBool?
        html`<p>Render some HTML if myBool is true</p>`:
        html`<p>Render some other HTML if myBool is false</p>`}
    `;
  }
}

customElements.define('my-element', MyElement);
```

3、给模板元素绑定属性值

您可以插入JavaScript表达式作为HTML文本内容，基本属性，布尔属性，元素属性和事件处理器的占位符。

 * 绑定到正文 Text content: <p>${...}</p>
 * 绑定到基本属性 Attribute: <p id="${...}"></p>
 * 绑定到布尔类型属性 Boolean attribute: ?disabled="${...}"
 * 绑定到元素属性 Property: .value="${...}"
 * 绑定到事件处理程序 Event handler: @event="${...}"

```js
import { LitElement, html } from 'lit-element';

class MyElement extends LitElement {
  static get properties() {
    return {
      prop1: String,
      prop2: String,
      prop3: Boolean,
      prop4: String
    };
  }
  constructor() {
    super();
    this.prop1 = 'text binding';
    this.prop2 = 'mydiv';
    this.prop3 = true;
    this.prop4 = 'pie';
  }
  render() {
    return html`
      <!-- text binding -->
      <div>${this.prop1}</div>

      <!-- attribute binding -->
      <div id="${this.prop2}">attribute binding</div>

      <!-- boolean attribute binding -->
      <div>
        boolean attribute binding
        <input type="text" ?disabled="${this.prop3}"/>
      </div>

      <!-- property binding -->
      <div>
        property binding
        <input type="text" .value="${this.prop4}"/>
      </div>

      <!-- event handler binding -->
      <div>event handler binding
        <button @click="${this.clickHandler}">click</button>
      </div>
    `;
  }
  clickHandler(e) {
    console.log(e.target);
  }
}

customElements.define('my-element', MyElement);
```

4、使用slot占位符给模板元素渲染子节点

```js

render(){
  return html`
    <div>
      <slot></slot>
    </div>
  `;
}

// 命名slot
render(){
  return html`
    <div>
      <slot name="one"></slot>
    </div>
  `;
}

```

使用例子:

```html

<my-element>
  <p>Render me</p>
  <p>Me too</p>
  <p>Me three</p>
</my-element>


<my-element>
  <p slot="one">Include me in slot "one".</p>
</my-element>

```

5、多个模板组合成页面

您可以从其他LitElement模板组成新的LitElement模板。
在以下示例中，我们通过导入其他元素并在模板中使用它们来组成新的<my-page>模板：

my-article.js

```js
import { LitElement, html } from 'lit-element';

class MyArticle extends LitElement {
    render() {
        return html`
        <article>article</article>
        `;
    }
}
customElements.define('my-article', MyArticle);
```

my-header.js

```js
import { html, LitElement } from 'lit-element';

class MyHeader extends LitElement {
    render() {
        return html`
        ${this.headerTemplate}
      `;
    }
    get headerTemplate() {
        return html`<header>header</header>`;
    }
}
customElements.define('my-header', MyHeader);
```

my-footer.js

```js
import { LitElement, html } from 'lit-element';

class MyFooter extends LitElement {
    render() {
        return html`
        <footer>footer</footer>
        `;
    }
}
customElements.define('my-footer', MyFooter);
```

用以上三个子模版元素组成页面元素

my-page.js

```js
import { LitElement, html } from 'lit-element';

import './my-header.js';
import './my-article.js';
import './my-footer.js';

class MyPage extends LitElement {
    render() {
        return html`
        <my-header></my-header>
        <my-article></my-article>
        <my-footer></my-footer>
    `;
    }
}
customElements.define('my-page', MyPage);
```

然后配一个 模板元素整合文件，例如 

mian.js

```js
import './my-page.js';
```

在页面 my-page.html中引用这个文件

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lit-element code sample</title>
    <script src="main.js"></script>
</head>

<body>
    <my-page></my-page>
    <script src="webcomponents-loader.js"></script>
</body>

</html>
```

此时可能有 index.html, my-page.html两个页面， index.js, main.js两个脚本文件，
需要同时打包输出，修改rollup,config.js文件，以数组形式输出多个js文件

```js
import resolve from 'rollup-plugin-node-resolve';
import babel from 'rollup-plugin-babel';

export default [{
    input: ['src/index.js'],
    output: {
        file: 'build/index.js',
    format: 'es',
        sourcemap: true
    },
    plugins: [
    resolve(),
    babel()
  ],
},{
    input: ['src/main.js'],
      output: {
          file: 'build/main.js',
      format: 'es',
          sourcemap: true
      },
      plugins: [
      resolve(),
      babel()
    ]
}];
```

修改package.json中的html打包命令

```js
"scripts": {
    "copyindex": "cp src/*.html build",
    "copywc": "cp -r node_modules/@webcomponents/webcomponentsjs/bundles build && cp node_modules/@webcomponents/webcomponentsjs/webcomponents-loader.js build",
    "build": "rm -rf build && mkdir build && npm run copyindex && npm run copywc && rollup -c",
    "start": "serve build"
  },
```

就可以在build下生成两个html文件及其对应的js文件了，其他同理可得




# LitElement(三)Styles样式

1、为组件添加样式

```js
import { LitElement, css, html } from 'lit-element';

class MyElement extends LitElement {
    // 返回单个样式
  static get styles() {
    return css`
      div { color: red; }
    `;
  }
  // 返回多个样式
  static get styles() {
    return [ css`...`, css`...`];
  }
  render() { 
    return html`
      <div>I'm styled!</div> 
    `;
  }
}

```
如同`static get properties()`属性一样，这是另一个静态属性`static get styles()`，专门用来定义组件的样式属性，
css是如同html一样的另一个父类函数

您添加到组件中的样式使用阴影DOM限定范围，不会被用于其他节点。


2、在样式中使用表达式

就像在html内容中使用属性一样

```js
import { LitElement, html, css } from 'lit-element';

const mainColor = css`red`;

class MyElement extends LitElement {
  static get styles() {
    return css`
      div { color: ${mainColor} }
    `;
  }
  render() {
    return html`<div>Some content in a div</div>`;
  }
}

customElements.define('my-element', MyElement);

```

3、继承样式

可以从父类继承样式斌不过添加当自己的样式属性

父类：super-element.js

```js
import { LitElement, html, css } from 'lit-element';

export class SuperElement extends LitElement {
  static get styles() {
    return css`
      button { width: 200px; }
    `;
  } 

  render() {
    return html`
      <button>click</button>
    `;
  }
}

customElements.define('super-element', SuperElement);
```

子类：my-element.js

```js
import { css } from 'lit-element';
import { SuperElement } from './super-element.js';

class MyElement extends SuperElement {
  static get styles() {
    return [
      super.styles,
      css`button { color: red; }`
    ];
  } 
}

customElements.define('my-element', MyElement);

```

4、共享样式
您可以通过创建导出标签样式的模块来在组件之间共享样式：

button-styles.js


```js
import { css } from 'lit-element';

export const buttonStyles = css`
  .blue-button {
    color: white;
    background-color: blue;
  }
  .blue-button:disabled {
    background-color: grey;
  }`;

```
在其他组件中引用它

```js
import { buttonStyles } from './button-styles.js';

class MyElement extends LitElement {
  static get styles() {
    return [
      buttonStyles,
      css`
        :host { display: block;
          border: 1px solid black;
        }`
    ]
  }
  ...
}

```

5、Shadow DOM样式概述

略


# LitElement(四)属性

4、配置属性

4.1 properties 和 attributes的转换

虽然元素 properties 可以是任何类型，但是 attributes 始终是字符串。
这会影响非字符串属性的  observed attributes 和 reflected attributes：

要 observe 一个属性（set a property from an attribute），属性值必须由string类型转换为匹配的类型

要 reflect 一个属性（set an attribute from a property），属性值必须被转化为string

4.1.1 使用默认转换

在处理 String，Number， Boolean，Array, and Object  属性类型时，LitElement有一个默认的转换规则

要使用默认的转换规则，需要在你的属性声明中指明 type 选项

```js
// Use LitElement's default converter 
prop1: { type: String },
prop2: { type: Number },
prop3: { type: Boolean },
prop4: { type: Array },
prop5: { type: Object }

```

以下信息显示默认转换器如何处理每种类型的转换。

从 attribute 转换到 property ：

 * 对于Strings类型， 直接使用，无需转换
 * 对于Numbers类型，调用 Number(attributeValue) 构造函数将
 * 对于Boolean 类型，非null转化为true，null转化为 false
 * 对于Arrays 和 Objects，调用 JSON.parse(attributeValue)
 
从 property 转换到 attribute  ：

 * 对于Strings，如果 property 是
     * null，删除该 attribute
     * undefined，不做改变
     * 否则直接 attribute 作为 property 值
 * 对于Numbers，同上
 * 对于 Booleans，property 值
     * 为真则创建属性
     * 为假则删除属性
 * 对于 Objects and Arrays  ，
     * 如果为 null 或 undefined 则删除属性
     * 否则 JSON.stringify(propertyValue) 转换


4.1.2 配置自定义转换

您可以使用converter选项在属性声明中指定自定义属性转换器：

```js
prop1: { 
  converter: { 
    fromAttribute: (value, type) => { 
      // `value` is a string
      // Convert it to a value of type `type` and return it
    },
    toAttribute: (value, type) => { 
      // `value` is of type `type` 
      // Convert it to a string and return it
    }
  }
}

myProp: { 
  converter: (value, type) => { 
    // `value` is a string
    // Convert it to a value of type `type` and return it
  }
} 

```

4.1.3 配置观察属性

只要 observed attribute 发生更改，就会触发自定义元素API回调 attributeChangedCallback。
默认情况下，每当某个属性触发此回调时，LitElement就会使用属性的 fromAttribute函数从该attribute 设置property值。

默认情况下，LitElement为所有声明的属性创建一个相应的观察属性。被观察属性的名称是属性名称，小写：

```js


```

4.1.4 配置反射属性

您可以配置property ，以便每当property 更改时，其值都会反射到其 observed attribute 中。

```js
// Value of property "myProp" will reflect to attribute "myprop"
myProp: { reflect: true }

```
LitElement跟踪更新期间的反射状态。LitElement跟踪状态信息，以避免在属性与观察到的反射属性之间创建无限循环的变化。


4.3 配置属性更改
所有声明的属性都有一个函数hasChanged，只要设置了属性，就会调用该函数。

hasChanged比较属性的旧值和新值，并评估属性是否已更改。如果hasChanged返回true，则如果尚未安排元素更新，则LitElement将开始元素更新。有关更新如何工作的更多信息，请参见Element更新生命周期文档。

hasChanged returns true if newVal !== oldVal.
hasChanged returns false if both the new and old values are NaN.
要为属性定制hasChanged，请将其指定为属性选项，也就是自定义属性更改的比较规则，相当于Java重写equals方法

```js


```

LitElement(五)事件

```js


```


LitElement(六)生命周期

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```

```js


```
