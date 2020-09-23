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

package org.wapache.openapi.v3.models;

import org.wapache.openapi.v3.models.callbacks.Callback;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.parameters.RequestBody;
import org.wapache.openapi.v3.models.responses.ApiResponses;
import org.wapache.openapi.v3.models.security.SecurityRequirement;
import org.wapache.openapi.v3.models.servers.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22operationobject%22%3E%3C/a%3Eoperation-object" class="md-header-anchor"></a><a name="operationObject"></a><span>Operation Object</span></h4>
 * <p><span>Describes a single API operation on a path.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="operationTags"></a><span>tags</span></td><td style='text-align:center;' ><span>[</span><code>string</code><span>]</span></td><td><span>A list of tags for API documentation control. Tags can be used for logical grouping of operations by resources or any other qualifier.</span></td></tr><tr><td><a name="operationSummary"></a><span>summary</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A short summary of what the operation does.</span></td></tr><tr><td><a name="operationDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A verbose explanation of the operation behavior. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="operationExternalDocs"></a><span>externalDocs</span></td><td style='text-align:center;' ><a href='#'><span>External Documentation Object</span></a></td><td><span>Additional external documentation for this operation.</span></td></tr><tr><td><a name="operationId"></a><span>operationId</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Unique string used to identify the operation. The id MUST be unique among all operations described in the API. Tools and libraries MAY use the operationId to uniquely identify an operation, therefore, it is RECOMMENDED to follow common programming naming conventions.</span></td></tr><tr><td><a name="operationParameters"></a><span>parameters</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Parameter Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>A list of parameters that are applicable for this operation. If a parameter is already defined at the </span><a href='#'><span>Path Item</span></a><span>, the new definition will override it but can never remove it. The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a </span><a href='#'><span>name</span></a><span> and </span><a href='#'><span>location</span></a><span>. The list can use the </span><a href='#'><span>Reference Object</span></a><span> to link to parameters that are defined at the </span><a href='#'><span>OpenAPI Object&#39;s components/parameters</span></a><span>.</span></td></tr><tr><td><a name="operationRequestBody"></a><span>requestBody</span></td><td style='text-align:center;' ><a href='#'><span>Request Body Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a></td><td><span>The request body applicable for this operation.  The </span><code>requestBody</code><span> is only supported in HTTP methods where the HTTP 1.1 specification </span><a href='https://tools.ietf.org/html/rfc7231#section-4.3.1'><span>RFC7231</span></a><span> has explicitly defined semantics for request bodies.  In other cases where the HTTP spec is vague, </span><code>requestBody</code><span> SHALL be ignored by consumers.</span></td></tr><tr><td><a name="operationResponses"></a><span>responses</span></td><td style='text-align:center;' ><a href='#'><span>Responses Object</span></a></td><td><strong><span>REQUIRED</span></strong><span>. The list of possible responses as they are returned from executing this operation.</span></td></tr><tr><td><a name="operationCallbacks"></a><span>callbacks</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Callback Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>A map of possible out-of band callbacks related to the parent operation. The key is a unique identifier for the Callback Object. Each value in the map is a </span><a href='#'><span>Callback Object</span></a><span> that describes a request that may be initiated by the API provider and the expected responses. The key value used to identify the callback object is an expression, evaluated at runtime, that identifies a URL to use for the callback operation.</span></td></tr><tr><td><a name="operationDeprecated"></a><span>deprecated</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Declares this operation to be deprecated. Consumers SHOULD refrain from usage of the declared operation. Default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="operationSecurity"></a><span>security</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Security Requirement Object</span></a><span>]</span></td><td><span>A declaration of which security mechanisms can be used for this operation. The list of values includes alternative security requirement objects that can be used. Only one of the security requirement objects need to be satisfied to authorize a request. This definition overrides any declared top-level </span><a href='#'><code>security</code></a><span>. To remove a top-level security declaration, an empty array can be used.</span></td></tr><tr><td><a name="operationServers"></a><span>servers</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Server Object</span></a><span>]</span></td><td><span>An alternative </span><code>server</code><span> array to service this operation. If an alternative </span><code>server</code><span> object is specified at the Path Item Object or Root level, it will be overridden by this value.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="operation-object-example" class="md-header-anchor"></a><span>Operation Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;tags&quot;: [
 *     &quot;pet&quot;
 *   ],
 *   &quot;summary&quot;: &quot;Updates a pet in the store with form data&quot;,
 *   &quot;operationId&quot;: &quot;updatePetWithForm&quot;,
 *   &quot;parameters&quot;: [
 *     {
 *       &quot;name&quot;: &quot;petId&quot;,
 *       &quot;in&quot;: &quot;path&quot;,
 *       &quot;description&quot;: &quot;ID of pet that needs to be updated&quot;,
 *       &quot;required&quot;: true,
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;string&quot;
 *       }
 *     }
 *   ],
 *   &quot;requestBody&quot;: {
 *     &quot;content&quot;: {
 *       &quot;application/x-www-form-urlencoded&quot;: {
 *         &quot;schema&quot;: {
 *           &quot;type&quot;: &quot;object&quot;,
 *            &quot;properties&quot;: {
 *               &quot;name&quot;: {
 *                 &quot;description&quot;: &quot;Updated name of the pet&quot;,
 *                 &quot;type&quot;: &quot;string&quot;
 *               },
 *               &quot;status&quot;: {
 *                 &quot;description&quot;: &quot;Updated status of the pet&quot;,
 *                 &quot;type&quot;: &quot;string&quot;
 *              }
 *            },
 *         &quot;required&quot;: [&quot;status&quot;]
 *         }
 *       }
 *     }
 *   },
 *   &quot;responses&quot;: {
 *     &quot;200&quot;: {
 *       &quot;description&quot;: &quot;Pet updated.&quot;,
 *       &quot;content&quot;: {
 *         &quot;application/json&quot;: {},
 *         &quot;application/xml&quot;: {}
 *       }
 *     },
 *     &quot;405&quot;: {
 *       &quot;description&quot;: &quot;Invalid input&quot;,
 *       &quot;content&quot;: {
 *         &quot;application/json&quot;: {},
 *         &quot;application/xml&quot;: {}
 *       }
 *     }
 *   },
 *   &quot;security&quot;: [
 *     {
 *       &quot;petstore_auth&quot;: [
 *         &quot;write:pets&quot;,
 *         &quot;read:pets&quot;
 *       ]
 *     }
 *   ]
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>tags:
 * - pet
 * summary: Updates a pet in the store with form data
 * operationId: updatePetWithForm
 * parameters:
 * - name: petId
 *   in: path
 *   description: ID of pet that needs to be updated
 *   required: true
 *   schema:
 *     type: string
 * requestBody:
 *   content:
 *     &#39;application/x-www-form-urlencoded&#39;:
 *       schema:
 *        properties:
 *           name:
 *             description: Updated name of the pet
 *             type: string
 *           status:
 *             description: Updated status of the pet
 *             type: string
 *        required:
 *          - status
 * responses:
 *   &#39;200&#39;:
 *     description: Pet updated.
 *     content:
 *       &#39;application/json&#39;: {}
 *       &#39;application/xml&#39;: {}
 *   &#39;405&#39;:
 *     description: Invalid input
 *     content:
 *       &#39;application/json&#39;: {}
 *       &#39;application/xml&#39;: {}
 * security:
 * - petstore_auth:
 *   - write:pets
 *   - read:pets
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#operationObject"
 */

public class Operation {

    private List<String> tags = null;
    private String summary = null;
    private String description = null;
    private ExternalDocumentation externalDocs = null;
    private String operationId = null;
    private List<Parameter> parameters = null;
    private RequestBody requestBody = null;
    private ApiResponses responses = null;
    private Map<String, Callback> callbacks = null;
    private Boolean deprecated = null;
    private List<SecurityRequirement> security = null;
    private List<Server> servers = null;
    private Map<String, Object> extensions = null;

    /**
     * returns the tags property from a Operation instance.
     *
     * @return List&lt;String&gt; tags
     **/

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Operation tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Operation addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * returns the summary property from a Operation instance.
     *
     * @return String summary
     **/

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Operation summary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * returns the description property from a Operation instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Operation description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the externalDocs property from a Operation instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public Operation externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    /**
     * returns the operationId property from a Operation instance.
     *
     * @return String operationId
     **/

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Operation operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * returns the parameters property from a Operation instance.
     *
     * @return List&lt;Parameter&gt; parameters
     **/

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Operation parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Operation addParametersItem(Parameter parametersItem) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parametersItem);
        return this;
    }

    /**
     * returns the requestBody property from a Operation instance.
     *
     * @return RequestBody requestBody
     **/

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public Operation requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    /**
     * returns the responses property from a Operation instance.
     *
     * @return ApiResponses responses
     **/

    public ApiResponses getResponses() {
        return responses;
    }

    public void setResponses(ApiResponses responses) {
        this.responses = responses;
    }

    public Operation responses(ApiResponses responses) {
        this.responses = responses;
        return this;
    }

    /**
     * returns the callbacks property from a Operation instance.
     *
     * @return Callbacks callbacks
     **/

    public Map<String, Callback> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(Map<String, Callback> callbacks) {
        this.callbacks = callbacks;
    }

    public Operation callbacks(Map<String, Callback> callbacks) {
        this.callbacks = callbacks;
        return this;
    }

    /**
     * returns the deprecated property from a Operation instance.
     *
     * @return Boolean deprecated
     **/

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Operation deprecated(Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    /**
     * returns the security property from a Operation instance.
     *
     * @return List&lt;SecurityRequirement&gt; security
     **/

    public List<SecurityRequirement> getSecurity() {
        return security;
    }

    public void setSecurity(List<SecurityRequirement> security) {
        this.security = security;
    }

    public Operation security(List<SecurityRequirement> security) {
        this.security = security;
        return this;
    }

    public Operation addSecurityItem(SecurityRequirement securityItem) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityItem);
        return this;
    }

    /**
     * returns the servers property from a Operation instance.
     *
     * @return List&lt;Server&gt; servers
     **/

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Operation servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }

    public Operation addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
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
        Operation operation = (Operation) o;
        return Objects.equals(this.tags, operation.tags) &&
                Objects.equals(this.summary, operation.summary) &&
                Objects.equals(this.description, operation.description) &&
                Objects.equals(this.externalDocs, operation.externalDocs) &&
                Objects.equals(this.operationId, operation.operationId) &&
                Objects.equals(this.parameters, operation.parameters) &&
                Objects.equals(this.requestBody, operation.requestBody) &&
                Objects.equals(this.responses, operation.responses) &&
                Objects.equals(this.callbacks, operation.callbacks) &&
                Objects.equals(this.deprecated, operation.deprecated) &&
                Objects.equals(this.security, operation.security) &&
                Objects.equals(this.servers, operation.servers) &&
                Objects.equals(this.extensions, operation.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, summary, description, externalDocs, operationId, parameters, requestBody, responses, callbacks, deprecated, security, servers, extensions);
    }

    public Map<String, Object> getExtensions() {
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

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public Operation extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Operation {\n");

        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
        sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("    requestBody: ").append(toIndentedString(requestBody)).append("\n");
        sb.append("    responses: ").append(toIndentedString(responses)).append("\n");
        sb.append("    callbacks: ").append(toIndentedString(callbacks)).append("\n");
        sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
        sb.append("    security: ").append(toIndentedString(security)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
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

