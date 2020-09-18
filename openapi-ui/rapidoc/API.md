

##  Attributes



### General 

| spec-url          | url of the OpenAPI spec to view                              | (empty) |
| ----------------- | ------------------------------------------------------------ | ------- |
| sort-tags         | Allowed: true \| false To list tags in alphabetic order,                otherwise tags will be ordered based on how it is specified under the tags section in the spec. | false   |
| sort-endpoints-by | Allowed: path \| method Sort endpoints within each tags by path or method | path    |
| heading-text      | Heading Text on top-left corner                              | (empty) |
| goto-path         | Initial location on the document(identified by method and path) where you want to go after the spec is loaded.                               goto-path should be in the form of {method}-{path}                               for instance you want to scrollTo  GET /user/login                 you should provide the location as  get-/user/login | (empty) |



### UI Colors and Fonts 

| theme         | Allowed: light \| dark                  Is the base theme, which is used for calculating colors for various UI components.               'theme', 'bg-color' and 'text-color' are the base attributes for generating a custom theme | light                                                        |
| ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| bg-color      | Hex color code for main background                           | Dark Theme   #333                   Light Theme   #fff       |
| text-color    | Hex color code for text                                      | Dark Theme   #bbb                   Light Theme   #444       |
| header-color  | Hex color code for the header's background                   | #444444                                                      |
| primary-color | Hex color code on various controls such as buttons, tabs     | #FF791A                                                      |
| regular-font  | Font Name(s) to be used for regular text                     | rapidoc, "Open Sans", BlinkMacSystemFont, "Segoe UI", Roboto, Arial, sans-serif |
| mono-font     | Font Name(s) to be used for mono-spaced text                 | Monaco, 'Andale Mono', 'Roboto Mono', 'Consolas' monospace   |
| font-size     | Allowed: default \| large  \| largest                   sets the relative font sizes for the entire document | default                                                      |



### Navigation bar settings (only applicable in read-mode)

| use-path-in-nav-bar  | Allowed: true \| false                set true to show API paths in the navigation bar instead of summary/description | false   |
| -------------------- | ------------------------------------------------------------ | ------- |
| nav-bg-color         | Navigation bar's background color                            |         |
| nav-bg-image         | URL of navigation bar's background image                     | (empty) |
| nav-bg-image-size    | navigation bar's background image size (same as css background-size property) allowed values are                 auto \| length \| cover \| contain \| initial \| inherit |         |
| nav-bg-image-repeat  | navigation bar's background image repeat (same as css background-repeat property) allowed values are                 repeat \| repeat-x \| repeat-y \| no-repeat \| initial \| inherit |         |
| nav-text-color       | Navigation bar's Text color                                  |         |
| nav-hover-bg-color   | background color of the navigation item on mouse-over        |         |
| nav-hover-text-color | text color of the navigation item on mouse-over              |         |
| nav-accent-color     | Current selected item indicator                              |         |
| nav-item-spacing     | Allowed: default \| compact \| relaxed                Controls navigation item spacing | default |



### UI Layout & Placement

| layout                      | Allowed: row \| column                 Layout helps in placement of request/response sections. In  column layout, request & response sections are placed one below the  other,                In row layout they are placed side by side.                This attribute is applicable only when the device width is  more than 768px and the render-style  is 'view'. | row   |
| --------------------------- | ------------------------------------------------------------ | ----- |
| render-style                | Allowed: read \| view -                determines display of api-docs. Currently there are two modes supported.                'read' - more suitable for reading and 'view' more friendly for quick exploring | view  |
| schema-style                | Allowed: tree \| table -                Two different ways to display object-schemas in the responses and request bodies | tree  |
| schema-expand-level         | Schemas are expanded by default, use this attribute to control how many levels in the schema should be expanded | 999   |
| schema-description-expanded | Allowed: true \| false -                Constraint and descriptions information of fields in the schema are collapsed to show only the first line.                Set it to true if you want them to fully expanded | false |
| default-schema-tab          | Allowed: model \| example -               The schemas are displayed in two tabs - Model and Example.                This option allows you to pick the default tab that you would like to be active | model |
| response-area-height        | Allowed: valid css height value such as 400px, 50%, 60vh etc -               Use this value to control the height of response textarea | 300px |



### Hide/Show Sections 

| show-info                           | show/hide the documents info section                Info section contains information about the spec, such as  the title and description of the spec, the version, terms of services  etc.               In certain situation you may not need to show this  section. For instance you are embedding this element inside a another  help document.                Chances are, the help doc may already have this info, in  that case you may want to hide this section. | true  |
| ----------------------------------- | ------------------------------------------------------------ | ----- |
| info-description-headings-in-navbar | Include headers from info -> description section to the Navigation bar (applies to read mode only)                               Will get the headers from the markdown in info -  description (h1 and h2) into the menu on the left (in read mode) along  with links to them.               This option allows users to add navigation bar items using  Markdown | false |
| show-components                     | show/hide the components section both in document and menu                               Will show the components section along with schemas,  responses, examples, requestBodies, headers, securitySchemes, links and  callbacks               Also will be shown in the menu on the left (in read mode) | false |
| show-header                         | show/hide the header.                 If you do not want your user to open any other api spec,  other than the current one, then set this attribute to false | true  |
| allow-authentication                | Authentication feature, allows the user to select one of  the authentication mechanism thats available in the spec.               It can be http-basic, http-bearer or api-key.                If you do not want your users to go through the  authentication process, instead want them to use a pre-generated api-key                 then you may hide authentication section by setting this  attribute to false               and provide the api-key details using various api-key-????  attributes. | true  |
| allow-spec-url-load                 | If set to 'false', user will not be able to load any spec url from the UI. | true  |
| allow-spec-file-load                | If set to 'false', user will not be able to load any spec file from the local drive.                This attribute is applicable only when the device width is more than 768px, else this feature is not available | true  |
| allow-search                        | If set to 'false', user will not be able to search APIs.     | true  |
| allow-try                           | 'TRY' feature allows you to make REST calls to the API server.                To disable this feature set it to false                Setting it to false will also hide API-Servers if specified in the spec | true  |
| allow-server-selection              | If set to 'false', user will not be able to see or select  API server (Server List will be hidden, however users will be able to  see the server url near the 'TRY' button, to know in advance where the  TRY will send the request).                The URL specified in the server-url attribute will be used  if set, else the first server in the API specification file will be  used. | true  |



### API Server

| api-key-name       | Name of the API key that will be send while trying out the APIs | Authorization |
| ------------------ | ------------------------------------------------------------ | ------------- |
| api-key-value      | Value of the API key that will be send while trying out the APIs.                This can also be provided/overwritten from UI. | (empty)       |
| api-key-location   | Allowed: header, query -               determines how you want to send the api-key. | header        |
| server-url         | OpenAPI spec has a provision for providing the server url.  The UI will list all the server URLs provided in the spec.                The user can then select one URL to which he or she  intends to send API calls while trying out the apis.                However, if you want to provide an API server of your own  which is not listed in the spec, you can use this property to provide  one.                It is helpful in the cases where the same spec is shared  between multiple environment say Dev and Test and each have their own  API server. | (empty)       |
| default-api-server | If you have multiple api-server listed in the spec, use  this attribute to select the default API server, where all the API calls  will goto.               This can be changed later from the UI | (empty)       |



##  Methods 

| Method         | Description                                                  |                                                              |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| loadSpec()     | To programmatically load spec. The method takes                                   either a string containing the url of the specs                    or a JSON object representing a valid spec | [ Example ](https://github.com/mrin9/RapiDoc/blob/master/docs/examples/example9.html) |
| scrollTo(path) | To programmatically scroll to a section (identified by combination method and path).                                path should be provided in the format of {method}-{path}               for instance you want to scrollTo GET /user/login                you should provide the location as  get-/user/login |                                                              |



##  Events

| Event       | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| spec-loaded | called after the spec is parsed, and rendered. Provides you with a object representing the spec.  `<rapi-doc    id = "doc"   spec-url = "https://petstore.swagger.io/v2/swagger.json" >  </rapi-doc> `    This is how you can listen to the event. `el = document.getElementById("doc"); el.addEventListener('spec-loaded', e => console.log(e.detail)); ` |



##  Slots

| Slot Name | Description                                                  |
| --------- | ------------------------------------------------------------ |
| (default) | any content placed inside `<rapi-doc>` tag, will be shown immediately under the header and above the info section |
| logo      | use this slot to replace the logo                            |
| header    | The contents of this slot will appear at the header after the spec-url input |
| footer    | The contents of this slot will appear at the bottom of the spec |
| nav-logo  | The contents of this slot will appear on Side navigation bar (only available in read-mode) |