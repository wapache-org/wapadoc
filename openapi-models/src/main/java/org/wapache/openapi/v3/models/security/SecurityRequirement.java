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

package org.wapache.openapi.v3.models.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22securityrequirementobject%22%3E%3C/a%3Esecurity-requirement-object" class="md-header-anchor"></a><a name="securityRequirementObject"></a><span>Security Requirement Object</span></h4>
 * <p><span>Lists the required security schemes to execute this operation.</span>
 * <span>The name used for each property MUST correspond to a security scheme declared in the </span><a href='#'><span>Security Schemes</span></a><span> under the </span><a href='#'><span>Components Object</span></a><span>.</span></p>
 * <p><span>Security Requirement Objects that contain multiple schemes require that all schemes MUST be satisfied for a request to be authorized.</span>
 * <span>This enables support for scenarios where multiple query parameters or HTTP headers are required to convey security information.</span></p>
 * <p><span>When a list of Security Requirement Objects is defined on the </span><a href='#'><span>Open API object</span></a><span> or </span><a href='#'><span>Operation Object</span></a><span>, only one of Security Requirement Objects in the list needs to be satisfied to authorize the request.  </span></p>
 * <h5><a name="patterned-fields" class="md-header-anchor"></a><span>Patterned Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Pattern</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="securityRequirementsName"></a><span>{name}</span></td><td style='text-align:center;' ><span>[</span><code>string</code><span>]</span></td><td><span>Each name MUST correspond to a security scheme which is declared in the </span><a href='#'><span>Security Schemes</span></a><span> under the </span><a href='#'><span>Components Object</span></a><span>. If the security scheme is of type </span><code>&quot;oauth2&quot;</code><span> or </span><code>&quot;openIdConnect&quot;</code><span>, then the value is a list of scope names required for the execution. For other security scheme types, the array MUST be empty.</span></td></tr></tbody>
 * </table></figure>
 * <h5><a name="security-requirement-object-examples" class="md-header-anchor"></a><span>Security Requirement Object Examples</span></h5>
 * <h6><a name="non-oauth2-security-requirement" class="md-header-anchor"></a><span>Non-OAuth2 Security Requirement</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;api_key&quot;: []
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>api_key: []
 * </code></pre>
 * <h6><a name="oauth2-security-requirement" class="md-header-anchor"></a><span>OAuth2 Security Requirement</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;petstore_auth&quot;: [
 *     &quot;write:pets&quot;,
 *     &quot;read:pets&quot;
 *   ]
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>petstore_auth:
 * - write:pets
 * - read:pets
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#securityRequirementObject"
 */

public class SecurityRequirement extends LinkedHashMap<String, List<String>> {
    public SecurityRequirement() {
    }

    public SecurityRequirement addList(String name, String item) {
        this.put(name, Arrays.asList(item));
        return this;
    }

    public SecurityRequirement addList(String name, List<String> item) {
        this.put(name, item);
        return this;
    }

    public SecurityRequirement addList(String name) {
        this.put(name, new ArrayList<>());
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
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SecurityRequirement {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

