/**
 * Copyright 2017 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wapache.openapi.v3.models.parameters;

import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.media.Content;
import org.wapache.openapi.v3.models.media.Schema;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22parameterobject%22%3E%3C/a%3Eparameter-object" class="md-header-anchor"></a><a name="parameterObject"></a><span>Parameter Object</span></h4>
 * <p><span>Describes a single operation parameter.</span></p>
 * <p><span>A unique parameter is defined by a combination of a </span><a href='#'><span>name</span></a><span> and </span><a href='#'><span>location</span></a><span>.</span></p>
 * <h5><a name="parameter-locations" class="md-header-anchor"></a><span>Parameter Locations</span></h5>
 * <p><span>There are four possible parameter locations specified by the </span><code>in</code><span> field:</span></p>
 * <ul>
 * <li><span>path - Used together with </span><a href='#'><span>Path Templating</span></a><span>, where the parameter value is actually part of the operation&#39;s URL. This does not include the host or base path of the API. For example, in </span><code>/items/{itemId}</code><span>, the path parameter is </span><code>itemId</code><span>.</span></li>
 * <li><span>query - Parameters that are appended to the URL. For example, in </span><code>/items?id=###</code><span>, the query parameter is </span><code>id</code><span>.</span></li>
 * <li><span>header - Custom headers that are expected as part of the request. Note that </span><a href='https://tools.ietf.org/html/rfc7230#page-22'><span>RFC7230</span></a><span> states header names are case insensitive.</span></li>
 * <li><span>cookie - Used to pass a specific cookie value to the API.</span></li>
 *
 * </ul>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="parameterName"></a><span>name</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The name of the parameter. Parameter names are </span><em><span>case sensitive</span></em><span>. </span><ul><li><span>If </span><a href='#'><code>in</code></a><span> is </span><code>&quot;path&quot;</code><span>, the </span><code>name</code><span> field MUST correspond to the associated path segment from the </span><a href='#'><span>path</span></a><span> field in the </span><a href='#'><span>Paths Object</span></a><span>. See </span><a href='#'><span>Path Templating</span></a><span> for further information.</span><li><span>If </span><a href='#'><code>in</code></a><span> is </span><code>&quot;header&quot;</code><span> and the </span><code>name</code><span> field is </span><code>&quot;Accept&quot;</code><span>, </span><code>&quot;Content-Type&quot;</code><span> or </span><code>&quot;Authorization&quot;</code><span>, the parameter definition SHALL be ignored.</span><li><span>For all other cases, the </span><code>name</code><span> corresponds to the parameter name used by the </span><a href='#'><code>in</code></a><span> property.</span></ul></td></tr><tr><td><a name="parameterIn"></a><span>in</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The location of the parameter. Possible values are &quot;query&quot;, &quot;header&quot;, &quot;path&quot; or &quot;cookie&quot;.</span></td></tr><tr><td><a name="parameterDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A brief description of the parameter. This could contain examples of use.  </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="parameterRequired"></a><span>required</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Determines whether this parameter is mandatory. If the </span><a href='#'><span>parameter location</span></a><span> is &quot;path&quot;, this property is </span><strong><span>REQUIRED</span></strong><span> and its value MUST be </span><code>true</code><span>. Otherwise, the property MAY be included and its default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="parameterDeprecated"></a><span> deprecated</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Specifies that a parameter is deprecated and SHOULD be transitioned out of usage.</span></td></tr><tr><td><a name="parameterAllowEmptyValue"></a><span> allowEmptyValue</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Sets the ability to pass empty-valued parameters. This is valid only for </span><code>query</code><span> parameters and allows sending a parameter with an empty value. Default value is </span><code>false</code><span>. If </span><a href='#'><code>style</code></a><span> is used, and if behavior is </span><code>n/a</code><span> (cannot be serialized), the value of </span><code>allowEmptyValue</code><span> SHALL be ignored.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>The rules for serialization of the parameter are specified in one of two ways.</span>
 * <span>For simpler scenarios, a </span><a href='#'><code>schema</code></a><span> and </span><a href='#'><code>style</code></a><span> can describe the structure and syntax of the parameter.</span></p>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="parameterStyle"></a><span>style</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Describes how the parameter value will be serialized depending on the type of the parameter value. Default values (based on value of </span><code>in</code><span>): for </span><code>query</code><span> - </span><code>form</code><span>; for </span><code>path</code><span> - </span><code>simple</code><span>; for </span><code>header</code><span> - </span><code>simple</code><span>; for </span><code>cookie</code><span> - </span><code>form</code><span>.</span></td></tr><tr><td><a name="parameterExplode"></a><span>explode</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>When this is true, parameter values of type </span><code>array</code><span> or </span><code>object</code><span> generate separate parameters for each value of the array or key-value pair of the map.  For other types of parameters this property has no effect. When </span><a href='#'><code>style</code></a><span> is </span><code>form</code><span>, the default value is </span><code>true</code><span>. For all other styles, the default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="parameterAllowReserved"></a><span>allowReserved</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Determines whether the parameter value SHOULD allow reserved characters, as defined by </span><a href='https://tools.ietf.org/html/rfc3986#section-2.2'><span>RFC3986</span></a><span> </span><code>:/?#[]@!$&amp;&#39;()*+,;=</code><span> to be included without percent-encoding. This property only applies to parameters with an </span><code>in</code><span> value of </span><code>query</code><span>. The default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="parameterSchema"></a><span>schema</span></td><td style='text-align:center;' ><a href='#'><span>Schema Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a></td><td><span>The schema defining the type used for the parameter.</span></td></tr><tr><td><a name="parameterExample"></a><span>example</span></td><td style='text-align:center;' ><span>Any</span></td><td><span>Example of the media type.  The example SHOULD match the specified schema and encoding properties if present.  The </span><code>example</code><span> field is mutually exclusive of the </span><code>examples</code><span> field.  Furthermore, if referencing a </span><code>schema</code><span> which contains an example, the </span><code>example</code><span> value SHALL </span><em><span>override</span></em><span> the example provided by the schema. To represent examples of media types that cannot naturally be represented in JSON or YAML, a string value can contain the example with escaping where necessary.</span></td></tr><tr><td><a name="parameterExamples"></a><span>examples</span></td><td style='text-align:center;' ><span>Map[ </span><code>string</code><span>, </span><a href='#'><span>Example Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>Examples of the media type.  Each example SHOULD contain a value in the correct format as specified in the parameter encoding.  The </span><code>examples</code><span> field is mutually exclusive of the </span><code>example</code><span> field.  Furthermore, if referencing a </span><code>schema</code><span> which contains an example, the </span><code>examples</code><span> value SHALL </span><em><span>override</span></em><span> the example provided by the schema.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>For more complex scenarios, the </span><a href='#'><code>content</code></a><span> property can define the media type and schema of the parameter.</span>
 * <span>A parameter MUST contain either a </span><code>schema</code><span> property, or a </span><code>content</code><span> property, but not both.</span>
 * <span>When </span><code>example</code><span> or </span><code>examples</code><span> are provided in conjunction with the </span><code>schema</code><span> object, the example MUST follow the prescribed serialization strategy for the parameter.</span></p>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="parameterContent"></a><span>content</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Media Type Object</span></a><span>]</span></td><td><span>A map containing the representations for the parameter. The key is the media type and the value describes it.  The map MUST only contain one entry.</span></td></tr></tbody>
 * </table></figure>
 * <h5><a name="style-values" class="md-header-anchor"></a><span>Style Values</span></h5>
 * <p><span>In order to support common ways of serializing simple parameters, a set of </span><code>style</code><span> values are defined.</span></p>
 * <p><code>style</code><span> | </span><a href='#'><code>type</code></a><span> |  </span><code>in</code><span> | Comments</span>
 * <span>----------- | ------ | -------- | --------</span>
 * <span>matrix |  </span><code>primitive</code><span>, </span><code>array</code><span>, </span><code>object</code><span> |  </span><code>path</code><span> | Path-style parameters defined by </span><a href='https://tools.ietf.org/html/rfc6570#section-3.2.7'><span>RFC6570</span></a><span> </span>
 * <span>label | </span><code>primitive</code><span>, </span><code>array</code><span>, </span><code>object</code><span> |  </span><code>path</code><span> | Label style parameters defined by </span><a href='https://tools.ietf.org/html/rfc6570#section-3.2.5'><span>RFC6570</span></a>
 * <span>form |  </span><code>primitive</code><span>, </span><code>array</code><span>, </span><code>object</code><span> |  </span><code>query</code><span>, </span><code>cookie</code><span> | Form style parameters defined by </span><a href='https://tools.ietf.org/html/rfc6570#section-3.2.8'><span>RFC6570</span></a><span>. This option replaces </span><code>collectionFormat</code><span> with a </span><code>csv</code><span> (when </span><code>explode</code><span> is false) or </span><code>multi</code><span> (when </span><code>explode</code><span> is true) value from OpenAPI 2.0.</span>
 * <span>simple | </span><code>array</code><span> | </span><code>path</code><span>, </span><code>header</code><span> | Simple style parameters defined by </span><a href='https://tools.ietf.org/html/rfc6570#section-3.2.2'><span>RFC6570</span></a><span>.  This option replaces </span><code>collectionFormat</code><span> with a </span><code>csv</code><span> value from OpenAPI 2.0.</span>
 * <span>spaceDelimited | </span><code>array</code><span> | </span><code>query</code><span> | Space separated array values. This option replaces </span><code>collectionFormat</code><span> equal to </span><code>ssv</code><span> from OpenAPI 2.0. </span>
 * <span>pipeDelimited | </span><code>array</code><span> | </span><code>query</code><span> | Pipe separated array values. This option replaces </span><code>collectionFormat</code><span> equal to </span><code>pipes</code><span> from OpenAPI 2.0.</span>
 * <span>deepObject | </span><code>object</code><span> | </span><code>query</code><span> | Provides a simple way of rendering nested objects using form parameters.</span></p>
 * <h5><a name="style-examples" class="md-header-anchor"></a><span>Style Examples</span></h5>
 * <p><span>Assume a parameter named </span><code>color</code><span> has one of the following values:</span></p>
 * <pre><code>   string -&gt; &quot;blue&quot;
 *    array -&gt; [&quot;blue&quot;,&quot;black&quot;,&quot;brown&quot;]
 *    object -&gt; { &quot;R&quot;: 100, &quot;G&quot;: 200, &quot;B&quot;: 150 }
 * </code></pre>
 * <p><span>The following table shows examples of rendering differences for each value.</span></p>
 * <p><a href='#'><code>style</code></a><span> | </span><code>explode</code><span> | </span><code>empty</code><span> | </span><code>string</code><span> | </span><code>array</code><span> | </span><code>object</code>
 * <span>----------- | ------ | -------- | -------- | --------|-------</span>
 * <span>matrix | false | ;color | ;color=blue | ;color=blue,black,brown | ;color=R,100,G,200,B,150</span>
 * <span>matrix | true | ;color | ;color=blue | ;color=blue;color=black;color=brown | ;R=100;G=200;B=150</span>
 * <span>label | false | .  | .blue |  .blue.black.brown | .R.100.G.200.B.150</span>
 * <span>label | true | . | .blue |  .blue.black.brown | .R=100.G=200.B=150</span>
 * <span>form | false | color= | color=blue | color=blue,black,brown | color=R,100,G,200,B,150</span>
 * <span>form | true | color= | color=blue | color=blue&amp;color=black&amp;color=brown | R=100&amp;G=200&amp;B=150</span>
 * <span>simple | false | n/a | blue | blue,black,brown | R,100,G,200,B,150</span>
 * <span>simple | true | n/a | blue | blue,black,brown | R=100,G=200,B=150</span>
 * <span>spaceDelimited | false | n/a | n/a | blue%20black%20brown | R%20100%20G%20200%20B%20150</span>
 * <span>pipeDelimited | false | n/a | n/a | blue</span><span>|</span><span>black</span><span>|</span><span>brown | R</span><span>|</span><span>100</span><span>|</span><span>G</span><span>|</span><span>200|G</span><span>|</span><span>150</span>
 * <span>deepObject | true | n/a | n/a | n/a | color[R]=100&amp;color[G]=200&amp;color[B]=150</span></p>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="parameter-object-examples" class="md-header-anchor"></a><span>Parameter Object Examples</span></h5>
 * <p><span>A header parameter with an array of 64 bit integer numbers:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;name&quot;: &quot;token&quot;,
 *   &quot;in&quot;: &quot;header&quot;,
 *   &quot;description&quot;: &quot;token to be passed as a header&quot;,
 *   &quot;required&quot;: true,
 *   &quot;schema&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;integer&quot;,
 *       &quot;format&quot;: &quot;int64&quot;
 *     }
 *   },
 *   &quot;style&quot;: &quot;simple&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>name: token
 * in: header
 * description: token to be passed as a header
 * required: true
 * schema:
 *   type: array
 *   items:
 *     type: integer
 *     format: int64
 * style: simple
 * </code></pre>
 * <p><span>A path parameter of a string value:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;name&quot;: &quot;username&quot;,
 *   &quot;in&quot;: &quot;path&quot;,
 *   &quot;description&quot;: &quot;username to fetch&quot;,
 *   &quot;required&quot;: true,
 *   &quot;schema&quot;: {
 *     &quot;type&quot;: &quot;string&quot;
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>name: username
 * in: path
 * description: username to fetch
 * required: true
 * schema:
 *   type: string
 * </code></pre>
 * <p><span>An optional query parameter of a string value, allowing multiple values by repeating the query parameter:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;name&quot;: &quot;id&quot;,
 *   &quot;in&quot;: &quot;query&quot;,
 *   &quot;description&quot;: &quot;ID of the object to fetch&quot;,
 *   &quot;required&quot;: false,
 *   &quot;schema&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;
 *     }
 *   },
 *   &quot;style&quot;: &quot;form&quot;,
 *   &quot;explode&quot;: true
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>name: id
 * in: query
 * description: ID of the object to fetch
 * required: false
 * schema:
 *   type: array
 *   items:
 *     type: string
 * style: form
 * explode: true
 * </code></pre>
 * <p><span>A free-form query parameter, allowing undefined parameters of a specific type:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;in&quot;: &quot;query&quot;,
 *   &quot;name&quot;: &quot;freeForm&quot;,
 *   &quot;schema&quot;: {
 *     &quot;type&quot;: &quot;object&quot;,
 *     &quot;additionalProperties&quot;: {
 *       &quot;type&quot;: &quot;integer&quot;
 *     },
 *   },
 *   &quot;style&quot;: &quot;form&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>in: query
 * name: freeForm
 * schema:
 *   type: object
 *   additionalProperties:
 *     type: integer
 * style: form
 * </code></pre>
 * <p><span>A complex parameter using </span><code>content</code><span> to define serialization:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;in&quot;: &quot;query&quot;,
 *   &quot;name&quot;: &quot;coordinates&quot;,
 *   &quot;content&quot;: {
 *     &quot;application/json&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;object&quot;,
 *         &quot;required&quot;: [
 *           &quot;lat&quot;,
 *           &quot;long&quot;
 *         ],
 *         &quot;properties&quot;: {
 *           &quot;lat&quot;: {
 *             &quot;type&quot;: &quot;number&quot;
 *           },
 *           &quot;long&quot;: {
 *             &quot;type&quot;: &quot;number&quot;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>in: query
 * name: coordinates
 * content:
 *   application/json:
 *     schema:
 *       type: object
 *       required:
 *         - lat
 *         - long
 *       properties:
 *         lat:
 *           type: number
 *         long:
 *           type: number
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#parameterObject"
 */

public class Parameter {

    private String name = null;
    private String in = null;
    private String description = null;
    private Boolean required = null;
    private Boolean deprecated = null;
    private Boolean allowEmptyValue = null;
    private String $ref = null;

    /**
     * Gets or Sets style
     */
    public enum StyleEnum {
        MATRIX("matrix"),
        LABEL("label"),
        FORM("form"),
        SIMPLE("simple"),
        SPACEDELIMITED("spaceDelimited"),
        PIPEDELIMITED("pipeDelimited"),
        DEEPOBJECT("deepObject");

        private String value;

        StyleEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private StyleEnum style = null;
    private Boolean explode = null;
    private Boolean allowReserved = null;
    private Schema schema = null;
    private Map<String, Example> examples = null;
    private Object example = null;
    private Content content = null;
    private Map<String, Object> extensions = null;

    /**
     * returns the name property from a Parameter instance.
     *
     * @return String name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter name(String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the in property from a Parameter instance.
     *
     * @return String in
     **/

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        if ("path".equals(in)) {
            this.required = true;
        }
        this.in = in;
    }

    public Parameter in(String in) {
        setIn(in);
        return this;
    }

    /**
     * returns the description property from a Parameter instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parameter description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the required property from a Parameter instance.
     *
     * @return Boolean required
     **/

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Parameter required(Boolean required) {
        this.required = required;
        return this;
    }

    /**
     * returns the deprecated property from a Parameter instance.
     *
     * @return Boolean deprecated
     **/

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Parameter deprecated(Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    /**
     * returns the allowEmptyValue property from a Parameter instance.
     *
     * @return Boolean allowEmptyValue
     **/

    public Boolean getAllowEmptyValue() {
        return allowEmptyValue;
    }

    public void setAllowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
    }

    public Parameter allowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    /**
     * returns the style property from a Parameter instance.
     *
     * @return StyleEnum style
     **/

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public Parameter style(StyleEnum style) {
        this.style = style;
        return this;
    }

    /**
     * returns the explode property from a Parameter instance.
     *
     * @return Boolean explode
     **/

    public Boolean getExplode() {
        return explode;
    }

    public void setExplode(Boolean explode) {
        this.explode = explode;
    }

    public Parameter explode(Boolean explode) {
        this.explode = explode;
        return this;
    }

    /**
     * returns the allowReserved property from a Parameter instance.
     *
     * @return Boolean allowReserved
     **/

    public Boolean getAllowReserved() {
        return allowReserved;
    }

    public void setAllowReserved(Boolean allowReserved) {
        this.allowReserved = allowReserved;
    }

    public Parameter allowReserved(Boolean allowReserved) {
        this.allowReserved = allowReserved;
        return this;
    }

    /**
     * returns the schema property from a Parameter instance.
     *
     * @return Schema schema
     **/

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public Parameter schema(Schema schema) {
        this.schema = schema;
        return this;
    }

    /**
     * returns the examples property from a Parameter instance.
     *
     * @return Map&lt;String, Example&gt; examples
     **/

    public Map<String, Example> getExamples() {
        return examples;
    }

    public void setExamples(Map<String, Example> examples) {
        this.examples = examples;
    }

    public Parameter examples(Map<String, Example> examples) {
        this.examples = examples;
        return this;
    }

    public Parameter addExample(String key, Example examplesItem) {
        if (this.examples == null) {
            this.examples = new LinkedHashMap<>();
        }
        this.examples.put(key, examplesItem);
        return this;
    }

    /**
     * returns the example property from a Parameter instance.
     *
     * @return String example
     **/

    public Object getExample() {
        return example;
    }

    public void setExample(Object example) {
        this.example = example;
    }

    public Parameter example(Object example) {
        this.example = example;
        return this;
    }

    /**
     * returns the content property from a Parameter instance.
     *
     * @return Content content
     **/

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Parameter content(Content content) {
        this.content = content;
        return this;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/parameters/" + $ref;
        }
        this.$ref = $ref;
    }

    public Parameter $ref(String $ref) {
        set$ref($ref);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return Objects.equals(this.name, parameter.name) &&
                Objects.equals(this.in, parameter.in) &&
                Objects.equals(this.description, parameter.description) &&
                Objects.equals(this.required, parameter.required) &&
                Objects.equals(this.deprecated, parameter.deprecated) &&
                Objects.equals(this.allowEmptyValue, parameter.allowEmptyValue) &&
                Objects.equals(this.style, parameter.style) &&
                Objects.equals(this.explode, parameter.explode) &&
                Objects.equals(this.allowReserved, parameter.allowReserved) &&
                Objects.equals(this.schema, parameter.schema) &&
                Objects.equals(this.examples, parameter.examples) &&
                Objects.equals(this.example, parameter.example) &&
                Objects.equals(this.content, parameter.content) &&
                Objects.equals(this.$ref, parameter.$ref) &&
                Objects.equals(this.extensions, parameter.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, in, description, required, deprecated, allowEmptyValue, style, explode, allowReserved, schema, examples, example, content, $ref, extensions);
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }

    public void addExtension(String name, Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public Parameter extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Parameter {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    in: ").append(toIndentedString(in)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    required: ").append(toIndentedString(required)).append("\n");
        sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
        sb.append("    allowEmptyValue: ").append(toIndentedString(allowEmptyValue)).append("\n");
        sb.append("    style: ").append(toIndentedString(style)).append("\n");
        sb.append("    explode: ").append(toIndentedString(explode)).append("\n");
        sb.append("    allowReserved: ").append(toIndentedString(allowReserved)).append("\n");
        sb.append("    schema: ").append(toIndentedString(schema)).append("\n");
        sb.append("    examples: ").append(toIndentedString(examples)).append("\n");
        sb.append("    example: ").append(toIndentedString(example)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    $ref: ").append(toIndentedString($ref)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    static String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

