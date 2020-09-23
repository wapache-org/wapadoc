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

import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.servers.Server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <h4><a name="%3Ca-name=%22pathitemobject%22%3E%3C/a%3Epath-item-object" class="md-header-anchor"></a><a name="pathItemObject"></a><span>Path Item Object</span></h4>
 * <p><span>Describes the operations available on a single path.</span>
 * <span>A Path Item MAY be empty, due to </span><a href='#'><span>ACL constraints</span></a><span>.</span>
 * <span>The path itself is still exposed to the documentation viewer but they will not know which operations and parameters are available.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="pathItemRef"></a><span>$ref</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Allows for an external definition of this path item. The referenced structure MUST be in the format of a </span><a href='#'><span>Path Item Object</span></a><span>. If there are conflicts between the referenced definition and this Path Item&#39;s definition, the behavior is </span><em><span>undefined</span></em><span>.</span></td></tr><tr><td><a name="pathItemSummary"></a><span>summary</span></td><td style='text-align:center;' ><code>string</code></td><td><span>An optional, string summary, intended to apply to all operations in this path.</span></td></tr><tr><td><a name="pathItemDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>An optional, string description, intended to apply to all operations in this path. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="pathItemGet"></a><span>get</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a GET operation on this path.</span></td></tr><tr><td><a name="pathItemPut"></a><span>put</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a PUT operation on this path.</span></td></tr><tr><td><a name="pathItemPost"></a><span>post</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a POST operation on this path.</span></td></tr><tr><td><a name="pathItemDelete"></a><span>delete</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a DELETE operation on this path.</span></td></tr><tr><td><a name="pathItemOptions"></a><span>options</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a OPTIONS operation on this path.</span></td></tr><tr><td><a name="pathItemHead"></a><span>head</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a HEAD operation on this path.</span></td></tr><tr><td><a name="pathItemPatch"></a><span>patch</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a PATCH operation on this path.</span></td></tr><tr><td><a name="pathItemTrace"></a><span>trace</span></td><td style='text-align:center;' ><a href='#'><span>Operation Object</span></a></td><td><span>A definition of a TRACE operation on this path.</span></td></tr><tr><td><a name="pathItemServers"></a><span>servers</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Server Object</span></a><span>]</span></td><td><span>An alternative </span><code>server</code><span> array to service all operations in this path.</span></td></tr><tr><td><a name="pathItemParameters"></a><span>parameters</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Parameter Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>A list of parameters that are applicable for all the operations described under this path. These parameters can be overridden at the operation level, but cannot be removed there. The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a </span><a href='#'><span>name</span></a><span> and </span><a href='#'><span>location</span></a><span>. The list can use the </span><a href='#'><span>Reference Object</span></a><span> to link to parameters that are defined at the </span><a href='#'><span>OpenAPI Object&#39;s components/parameters</span></a><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="path-item-object-example" class="md-header-anchor"></a><span>Path Item Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;get&quot;: {
 *     &quot;description&quot;: &quot;Returns pets based on ID&quot;,
 *     &quot;summary&quot;: &quot;Find pets by ID&quot;,
 *     &quot;operationId&quot;: &quot;getPetsById&quot;,
 *     &quot;responses&quot;: {
 *       &quot;200&quot;: {
 *         &quot;description&quot;: &quot;pet response&quot;,
 *         &quot;content&quot;: {
 *           &quot;*\/*&quot;:{
 *             &quot;schema&quot;:{
 *             &quot;type&quot;:&quot;array&quot;,
 *             &quot;items&quot;:{
 *             &quot;$ref&quot;:&quot;#/components/schemas/Pet&quot;
 *             }
 *             }
 *             }
 *             }
 *             },
 *             &quot;default&quot;:{
 *             &quot;description&quot;:&quot;error payload&quot;,
 *             &quot;content&quot;:{
 *             &quot;text/html&quot;:{
 *             &quot;schema&quot;:{
 *             &quot;$ref&quot;:&quot;#/components/schemas/ErrorModel&quot;
 *             }
 *             }
 *             }
 *             }
 *             }
 *             },
 *             &quot;parameters&quot;:[
 *             {
 *             &quot;name&quot;:&quot;id&quot;,
 *             &quot;in&quot;:&quot;path&quot;,
 *             &quot;description&quot;:&quot;ID of pet to use&quot;,
 *             &quot;required&quot;:true,
 *             &quot;schema&quot;:{
 *             &quot;type&quot;:&quot;array&quot;,
 *             &quot;items&quot;:{
 *             &quot;type&quot;:&quot;string&quot;
 *             }
 *             },
 *             &quot;style&quot;:&quot;simple&quot;
 *             }
 *             ]
 *             }
 *             </code></pre>
 *             <pre><code class='language-yaml'lang='yaml'>get:
 *             description:Returns pets based on ID
 *             summary:Find pets by ID
 *             operationId:getPetsById
 *             responses:
 *             &#39;200&#39;:
 *             description:pet response
 *             content:
 *             &#39;*\/*&#39; :
 *           schema:
 *             type: array
 *             items:
 *               $ref: &#39;#/components/schemas/Pet&#39;
 *     default:
 *       description: error payload
 *       content:
 *         &#39;text/html&#39;:
 *           schema:
 *             $ref: &#39;#/components/schemas/ErrorModel&#39;
 * parameters:
 * - name: id
 *   in: path
 *   description: ID of pet to use
 *   required: true
 *   schema:
 *     type: array
 *     style: simple
 *     items:
 *       type: string
 * </code></pre>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#pathItemObject"
 */

public class PathItem {
    private String summary = null;
    private String description = null;
    private Operation get = null;
    private Operation put = null;
    private Operation post = null;
    private Operation delete = null;
    private Operation options = null;
    private Operation head = null;
    private Operation patch = null;
    private Operation trace = null;
    private List<Server> servers = null;
    private List<Parameter> parameters = null;
    private String $ref = null;
    private Map<String, Object> extensions = null;

    /**
     * returns the summary property from a PathItem instance.
     *
     * @return String summary
     **/

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PathItem summary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * returns the description property from a PathItem instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PathItem description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the get property from a PathItem instance.
     *
     * @return Operation get
     **/

    public Operation getGet() {
        return get;
    }

    public void setGet(Operation get) {
        this.get = get;
    }

    public PathItem get(Operation get) {
        this.get = get;
        return this;
    }

    /**
     * returns the put property from a PathItem instance.
     *
     * @return Operation put
     **/

    public Operation getPut() {
        return put;
    }

    public void setPut(Operation put) {
        this.put = put;
    }

    public PathItem put(Operation put) {
        this.put = put;
        return this;
    }

    /**
     * returns the post property from a PathItem instance.
     *
     * @return Operation post
     **/

    public Operation getPost() {
        return post;
    }

    public void setPost(Operation post) {
        this.post = post;
    }

    public PathItem post(Operation post) {
        this.post = post;
        return this;
    }

    /**
     * returns the delete property from a PathItem instance.
     *
     * @return Operation delete
     **/

    public Operation getDelete() {
        return delete;
    }

    public void setDelete(Operation delete) {
        this.delete = delete;
    }

    public PathItem delete(Operation delete) {
        this.delete = delete;
        return this;
    }

    /**
     * returns the options property from a PathItem instance.
     *
     * @return Operation options
     **/

    public Operation getOptions() {
        return options;
    }

    public void setOptions(Operation options) {
        this.options = options;
    }

    public PathItem options(Operation options) {
        this.options = options;
        return this;
    }

    /**
     * returns the head property from a PathItem instance.
     *
     * @return Operation head
     **/

    public Operation getHead() {
        return head;
    }

    public void setHead(Operation head) {
        this.head = head;
    }

    public PathItem head(Operation head) {
        this.head = head;
        return this;
    }

    /**
     * returns the patch property from a PathItem instance.
     *
     * @return Operation patch
     **/

    public Operation getPatch() {
        return patch;
    }

    public void setPatch(Operation patch) {
        this.patch = patch;
    }

    public PathItem patch(Operation patch) {
        this.patch = patch;
        return this;
    }

    /**
     * returns the trace property from a PathItem instance.
     *
     * @return Operation trace
     **/

    public Operation getTrace() {
        return trace;
    }

    public void setTrace(Operation trace) {
        this.trace = trace;
    }

    public PathItem trace(Operation trace) {
        this.trace = trace;
        return this;
    }

    public List<Operation> readOperations() {
        List<Operation> allOperations = new ArrayList<>();
        if (this.get != null) {
            allOperations.add(this.get);
        }
        if (this.put != null) {
            allOperations.add(this.put);
        }
        if (this.head != null) {
            allOperations.add(this.head);
        }
        if (this.post != null) {
            allOperations.add(this.post);
        }
        if (this.delete != null) {
            allOperations.add(this.delete);
        }
        if (this.patch != null) {
            allOperations.add(this.patch);
        }
        if (this.options != null) {
            allOperations.add(this.options);
        }
        if (this.trace != null) {
            allOperations.add(this.trace);
        }

        return allOperations;
    }

    public void operation(HttpMethod method, Operation operation) {
        switch (method) {
            case PATCH:
                this.patch = operation;
                break;
            case POST:
                this.post = operation;
                break;
            case PUT:
                this.put = operation;
                break;
            case GET:
                this.get = operation;
                break;
            case OPTIONS:
                this.options = operation;
                break;
            case TRACE:
                this.trace = operation;
                break;
            case HEAD:
                this.head = operation;
                break;
            case DELETE:
                this.delete = operation;
                break;
            default:
        }
    }

    public enum HttpMethod {
        POST,
        GET,
        PUT,
        PATCH,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE
    }

    public Map<HttpMethod, Operation> readOperationsMap() {
        Map<HttpMethod, Operation> result = new LinkedHashMap<>();

        if (this.get != null) {
            result.put(HttpMethod.GET, this.get);
        }
        if (this.put != null) {
            result.put(HttpMethod.PUT, this.put);
        }
        if (this.post != null) {
            result.put(HttpMethod.POST, this.post);
        }
        if (this.delete != null) {
            result.put(HttpMethod.DELETE, this.delete);
        }
        if (this.patch != null) {
            result.put(HttpMethod.PATCH, this.patch);
        }
        if (this.head != null) {
            result.put(HttpMethod.HEAD, this.head);
        }
        if (this.options != null) {
            result.put(HttpMethod.OPTIONS, this.options);
        }
        if (this.trace != null) {
            result.put(HttpMethod.TRACE, this.trace);
        }

        return result;
    }

    /**
     * returns the servers property from a PathItem instance.
     *
     * @return List&lt;Server&gt; servers
     **/

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public PathItem servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }

    public PathItem addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
        return this;
    }

    /**
     * returns the parameters property from a PathItem instance.
     *
     * @return List&lt;Parameter&gt; parameters
     **/

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public PathItem parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public PathItem addParametersItem(Parameter parametersItem) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parametersItem);
        return this;
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

    public PathItem extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    /**
     * returns the ref property from a PathItem instance.
     *
     * @return String ref
     **/
    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    public PathItem $ref(String $ref) {
        set$ref($ref);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PathItem)) {
            return false;
        }

        PathItem pathItem = (PathItem) o;

        if (summary != null ? !summary.equals(pathItem.summary) : pathItem.summary != null) {
            return false;
        }
        if (description != null ? !description.equals(pathItem.description) : pathItem.description != null) {
            return false;
        }
        if (get != null ? !get.equals(pathItem.get) : pathItem.get != null) {
            return false;
        }
        if (put != null ? !put.equals(pathItem.put) : pathItem.put != null) {
            return false;
        }
        if (post != null ? !post.equals(pathItem.post) : pathItem.post != null) {
            return false;
        }
        if (delete != null ? !delete.equals(pathItem.delete) : pathItem.delete != null) {
            return false;
        }
        if (options != null ? !options.equals(pathItem.options) : pathItem.options != null) {
            return false;
        }
        if (head != null ? !head.equals(pathItem.head) : pathItem.head != null) {
            return false;
        }
        if (patch != null ? !patch.equals(pathItem.patch) : pathItem.patch != null) {
            return false;
        }
        if (trace != null ? !trace.equals(pathItem.trace) : pathItem.trace != null) {
            return false;
        }
        if (servers != null ? !servers.equals(pathItem.servers) : pathItem.servers != null) {
            return false;
        }
        if (parameters != null ? !parameters.equals(pathItem.parameters) : pathItem.parameters != null) {
            return false;
        }
        if ($ref != null ? !$ref.equals(pathItem.$ref) : pathItem.$ref != null) {
            return false;
        }
        return extensions != null ? extensions.equals(pathItem.extensions) : pathItem.extensions == null;

    }

    @Override
    public int hashCode() {
        int result = summary != null ? summary.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (get != null ? get.hashCode() : 0);
        result = 31 * result + (put != null ? put.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (delete != null ? delete.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (patch != null ? patch.hashCode() : 0);
        result = 31 * result + (trace != null ? trace.hashCode() : 0);
        result = 31 * result + (servers != null ? servers.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + ($ref != null ? $ref.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PathItem {\n");
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    get: ").append(toIndentedString(get)).append("\n");
        sb.append("    put: ").append(toIndentedString(put)).append("\n");
        sb.append("    post: ").append(toIndentedString(post)).append("\n");
        sb.append("    delete: ").append(toIndentedString(delete)).append("\n");
        sb.append("    options: ").append(toIndentedString(options)).append("\n");
        sb.append("    head: ").append(toIndentedString(head)).append("\n");
        sb.append("    patch: ").append(toIndentedString(patch)).append("\n");
        sb.append("    trace: ").append(toIndentedString(trace)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("    $ref: ").append(toIndentedString($ref)).append("\n");
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

