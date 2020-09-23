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

package org.wapache.openapi.v3.models.responses;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22responsesobject%22%3E%3C/a%3Eresponses-object" class="md-header-anchor"></a><a name="responsesObject"></a><span>Responses Object</span></h4>
 * <p><span>A container for the expected responses of an operation.</span>
 * <span>The container maps a HTTP response code to the expected response.</span></p>
 * <p><span>The documentation is not necessarily expected to cover all possible HTTP response codes because they may not be known in advance.</span>
 * <span>However, documentation is expected to cover a successful operation response and any known errors.</span></p>
 * <p><span>The </span><code>default</code><span> MAY be used as a default response object for all HTTP codes </span>
 * <span>that are not covered individually by the specification.</span></p>
 * <p><span>The </span><code>Responses Object</code><span> MUST contain at least one response code, and it </span>
 * <span>SHOULD be the response for a successful operation call.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="responsesDefault"></a><span>default</span></td><td style='text-align:center;' ><a href='#'><span>Response Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a></td><td><span>The documentation of responses other than the ones declared for specific HTTP response codes. Use this field to cover undeclared responses. A </span><a href='#'><span>Reference Object</span></a><span> can link to a response that the </span><a href='#'><span>OpenAPI Object&#39;s components/responses</span></a><span> section defines.</span></td></tr></tbody>
 * </table></figure>
 * <h5><a name="patterned-fields" class="md-header-anchor"></a><span>Patterned Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Pattern</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="responsesCode"></a><a href='#'><span>HTTP Status Code</span></a></td><td style='text-align:center;' ><a href='#'><span>Response Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a></td><td><span>Any </span><a href='#'><span>HTTP status code</span></a><span> can be used as the property name, but only one property per code, to describe the expected response for that HTTP status code.  A </span><a href='#'><span>Reference Object</span></a><span> can link to a response that is defined in the </span><a href='#'><span>OpenAPI Object&#39;s components/responses</span></a><span> section. This field MUST be enclosed in quotation marks (for example, &quot;200&quot;) for compatibility between JSON and YAML. To define a range of response codes, this field MAY contain the uppercase wildcard character </span><code>X</code><span>. For example, </span><code>2XX</code><span> represents all response codes between </span><code>[200-299]</code><span>. The following range definitions are allowed: </span><code>1XX</code><span>, </span><code>2XX</code><span>, </span><code>3XX</code><span>, </span><code>4XX</code><span>, and </span><code>5XX</code><span>. If a response range is defined using an explicit code, the explicit code definition takes precedence over the range definition for that code.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="responses-object-example" class="md-header-anchor"></a><span>Responses Object Example</span></h5>
 * <p><span>A 200 response for a successful operation and a default response for others (implying an error):</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;200&quot;: {
 *     &quot;description&quot;: &quot;a pet to be returned&quot;,
 *     &quot;content&quot;: {
 *       &quot;application/json&quot;: {
 *         &quot;schema&quot;: {
 *           &quot;$ref&quot;: &quot;#/components/schemas/Pet&quot;
 *         }
 *       }
 *     }
 *   },
 *   &quot;default&quot;: {
 *     &quot;description&quot;: &quot;Unexpected error&quot;,
 *     &quot;content&quot;: {
 *       &quot;application/json&quot;: {
 *         &quot;schema&quot;: {
 *           &quot;$ref&quot;: &quot;#/components/schemas/ErrorModel&quot;
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>&#39;200&#39;:
 *   description: a pet to be returned
 *   content:
 *     application/json:
 *       schema:
 *         $ref: &#39;#/components/schemas/Pet&#39;
 * default:
 *   description: Unexpected error
 *   content:
 *     application/json:
 *       schema:
 *         $ref: &#39;#/components/schemas/ErrorModel&#39;
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#responsesObject"
 */

public class ApiResponses extends LinkedHashMap<String, ApiResponse> {

    public static final String DEFAULT = "default";

    private java.util.Map<String, Object> extensions = null;

    public ApiResponses addApiResponse(String name, ApiResponse item) {
        this.put(name, item);
        return this;
    }

    /**
     * returns the default property from a ApiResponses instance.
     *
     * @return ApiResponse _default
     **/

    public ApiResponse getDefault() {
        return this.get(DEFAULT);
    }

    public void setDefault(ApiResponse _default) {
        addApiResponse(DEFAULT, _default);
    }

    public ApiResponses _default(ApiResponse _default) {
        setDefault(_default);
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
            this.extensions = new LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public ApiResponses extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
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
        if (!super.equals(o)) {
            return false;
        }
        ApiResponses apiResponses = (ApiResponses) o;
        return Objects.equals(this.extensions, apiResponses.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extensions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiResponses {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

