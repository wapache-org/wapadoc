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

import org.wapache.openapi.v3.models.media.Content;

/**
 * <h4><a name="%3Ca-name=%22requestbodyobject%22%3E%3C/a%3Erequest-body-object" class="md-header-anchor"></a><a name="requestBodyObject"></a><span>Request Body Object</span></h4>
 * <p><span>Describes a single request body.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="requestBodyDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A brief description of the request body. This could contain examples of use.  </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="requestBodyContent"></a><span>content</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Media Type Object</span></a><span>]</span></td><td><strong><span>REQUIRED</span></strong><span>. The content of the request body. The key is a media type or </span><a href='https://tools.ietf.org/html/rfc7231#appendix-D'><span>media type range</span></a><span> and the value describes it.  For requests that match multiple keys, only the most specific key is applicable. e.g. text/plain overrides text/*</span></td></tr><tr><td><a name="requestBodyRequired"></a><span>required</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Determines if the request body is required in the request. Defaults to </span><code>false</code><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="request-body-examples" class="md-header-anchor"></a><span>Request Body Examples</span></h5>
 * <p><span>A request body with a referenced model definition.</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;user to add to the system&quot;,
 *   &quot;content&quot;: {
 *     &quot;application/json&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;$ref&quot;: &quot;#/components/schemas/User&quot;
 *       },
 *       &quot;examples&quot;: {
 *           &quot;user&quot; : {
 *             &quot;summary&quot;: &quot;User Example&quot;,
 *             &quot;externalValue&quot;: &quot;http://foo.bar/examples/user-example.json&quot;
 *           }
 *         }
 *     },
 *     &quot;application/xml&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;$ref&quot;: &quot;#/components/schemas/User&quot;
 *       },
 *       &quot;examples&quot;: {
 *           &quot;user&quot; : {
 *             &quot;summary&quot;: &quot;User example in XML&quot;,
 *             &quot;externalValue&quot;: &quot;http://foo.bar/examples/user-example.xml&quot;
 *           }
 *         }
 *     },
 *     &quot;text/plain&quot;: {
 *       &quot;examples&quot;: {
 *         &quot;user&quot; : {
 *             &quot;summary&quot;: &quot;User example in Plain text&quot;,
 *             &quot;externalValue&quot;: &quot;http://foo.bar/examples/user-example.txt&quot;
 *         }
 *       }
 *     },
 *     &quot;*\/*&quot;:{
 *       &quot;examples&quot;:{
 *       &quot;user&quot;:{
 *       &quot;summary&quot;:&quot;User example in other format&quot;,
 *       &quot;externalValue&quot;:&quot;http://foo.bar/examples/user-example.whatever&quot;
 *       }
 *       }
 *       }
 *       }
 *       }
 *       </code></pre>
 *       <pre><code class='language-yaml'lang='yaml'>description:user to add to the system
 *       content:
 *       &#39;application/json&#39;:
 *       schema:
 *       $ref:&#39;#/components/schemas/User&#39;
 *       examples:
 *       user:
 *       summary:User Example
 *       externalValue:&#39;http://foo.bar/examples/user-example.json&#39;
 *       &#39;application/xml&#39;:
 *       schema:
 *       $ref:&#39;#/components/schemas/User&#39;
 *       examples:
 *       user:
 *       summary:User Example in XML
 *       externalValue:&#39;http://foo.bar/examples/user-example.xml&#39;
 *       &#39;text/plain&#39;:
 *       examples:
 *       user:
 *       summary:User example in text plain format
 *       externalValue:&#39;http://foo.bar/examples/user-example.txt&#39;
 *       &#39;*\/*&#39;:
 *     examples:
 *       user:
 *         summary: User example in other format
 *         externalValue: &#39;http://foo.bar/examples/user-example.whatever&#39;
 * </code></pre>
 * <p><span>A body parameter that is an array of string values:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;user to add to the system&quot;,
 *   &quot;content&quot;: {
 *     &quot;text/plain&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;array&quot;,
 *         &quot;items&quot;: {
 *           &quot;type&quot;: &quot;string&quot;
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: user to add to the system
 * required: true
 * content:
 *   text/plain:
 *     schema:
 *       type: array
 *       items:
 *         type: string
 * </code></pre>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#requestBodyObject"
 */

public class RequestBody {
    private String description = null;
    private Content content = null;
    private Boolean required = null;
    private java.util.Map<String, Object> extensions = null;
    private String $ref = null;

    /**
     * returns the description property from a RequestBody instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestBody description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the content property from a RequestBody instance.
     *
     * @return Content content
     **/

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public RequestBody content(Content content) {
        this.content = content;
        return this;
    }

    /**
     * returns the required property from a RequestBody instance.
     *
     * @return Boolean required
     **/

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public RequestBody required(Boolean required) {
        this.required = required;
        return this;
    }

    public java.util.Map<String, Object> getExtensions() {
        return extensions;
    }

    public void addExtension(String name, Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new java.util.LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public RequestBody extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/requestBodies/" + $ref;
        }
        this.$ref = $ref;
    }

    public RequestBody $ref(String $ref) {
        set$ref($ref);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestBody)) {
            return false;
        }

        RequestBody that = (RequestBody) o;

        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        if (required != null ? !required.equals(that.required) : that.required != null) {
            return false;
        }
        if (extensions != null ? !extensions.equals(that.extensions) : that.extensions != null) {
            return false;
        }
        return $ref != null ? $ref.equals(that.$ref) : that.$ref == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (required != null ? required.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        result = 31 * result + ($ref != null ? $ref.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RequestBody {\n");

        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    required: ").append(toIndentedString(required)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

