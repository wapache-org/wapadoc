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

package org.wapache.openapi.v3.models.links;

import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.servers.Server;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h4><a name="%3Ca-name=%22linkobject%22%3E%3C/a%3Elink-object" class="md-header-anchor"></a><a name="linkObject"></a><span>Link Object</span></h4>
 * <p><span>The </span><code>Link object</code><span> represents a possible design-time link for a response.</span>
 * <span>The presence of a link does not guarantee the caller&#39;s ability to successfully invoke it, rather it provides a known relationship and traversal mechanism between responses and other operations.</span></p>
 * <p><span>Unlike </span><em><span>dynamic</span></em><span> links (i.e. links provided </span><strong><span>in</span></strong><span> the response payload), the OAS linking mechanism does not require link information in the runtime response.</span></p>
 * <p><span>For computing links, and providing instructions to execute them, a </span><a href='#'><span>runtime expression</span></a><span> is used for accessing values in an operation and using them as parameters while invoking the linked operation.  </span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="linkOperationRef"></a><span>operationRef</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A relative or absolute reference to an OAS operation. This field is mutually exclusive of the </span><code>operationId</code><span> field, and MUST point to an </span><a href='#'><span>Operation Object</span></a><span>. Relative </span><code>operationRef</code><span> values MAY be used to locate an existing </span><a href='#'><span>Operation Object</span></a><span> in the OpenAPI definition.</span></td></tr><tr><td><a name="linkOperationId"></a><span>operationId</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The name of an </span><em><span>existing</span></em><span>, resolvable OAS operation, as defined with a unique </span><code>operationId</code><span>.  This field is mutually exclusive of the </span><code>operationRef</code><span> field.</span></td></tr><tr><td><a name="linkParameters"></a><span>parameters</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, Any </span><span>|</span><span> </span><a href='#'><span>{expression}</span></a><span>]</span></td><td><span>A map representing parameters to pass to an operation as specified with </span><code>operationId</code><span> or identified via </span><code>operationRef</code><span>. The key is the parameter name to be used, whereas the value can be a constant or an expression to be evaluated and passed to the linked operation.  The parameter name can be qualified using the </span><a href='#'><span>parameter location</span></a><span> </span><code>[{in}.]{name}</code><span> for operations that use the same parameter name in different locations (e.g. path.id).</span></td></tr><tr><td><a name="linkRequestBody"></a><span>requestBody</span></td><td style='text-align:center;' ><span>Any </span><span>|</span><span> </span><a href='#'><span>{expression}</span></a></td><td><span>A literal value or </span><a href='#'><span>{expression}</span></a><span> to use as a request body when calling the target operation.</span></td></tr><tr><td><a name="linkDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A description of the link. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="linkServer"></a><span>server</span></td><td style='text-align:center;' ><a href='#'><span>Server Object</span></a></td><td><span>A server object to be used by the target operation.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <p><span>A linked operation MUST be identified using either an </span><code>operationRef</code><span> or </span><code>operationId</code><span>.</span>
 * <span>In the case of an </span><code>operationId</code><span>, it MUST be unique and resolved in the scope of the OAS document.</span>
 * <span>Because of the potential for name clashes, the </span><code>operationRef</code><span> syntax is preferred </span>
 * <span>for specifications with external references.</span></p>
 * <h5><a name="examples" class="md-header-anchor"></a><span>Examples</span></h5>
 * <p><span>Computing a link from a request operation where the </span><code>$request.path.id</code><span> is used to pass a request parameter to the linked operation.</span></p>
 * <pre><code class='language-yaml' lang='yaml'>paths:
 *   /users/{id}:
 *     parameters:
 *     - name: id
 *       in: path
 *       required: true
 *       description: the user identifier, as userId
 *       schema:
 *         type: string
 *     get:
 *       responses:
 *         &#39;200&#39;:
 *           description: the user being returned
 *           content:
 *             application/json:
 *               schema:
 *                 type: object
 *                 properties:
 *                   uuid: # the unique user id
 *                     type: string
 *                     format: uuid
 *           links:
 *             address:
 *               # the target link operationId
 *               operationId: getUserAddress
 *               parameters:
 *                 # get the `id` field from the request path parameter named `id`
 *                 userId: $request.path.id
 *   # the path item of the linked operation
 *   /users/{userid}/address:
 *     parameters:
 *     - name: userid
 *       in: path
 *       required: true
 *       description: the user identifier, as userId
 *       schema:
 *         type: string
 *     # linked operation
 *     get:
 *       operationId: getUserAddress
 *       responses:
 *         &#39;200&#39;:
 *           description: the user&#39;s address
 * </code></pre>
 * <p><span>When a runtime expression fails to evaluate, no parameter value is passed to the target operation.</span></p>
 * <p><span>Values from the response body can be used to drive a linked operation.</span></p>
 * <pre><code class='language-yaml' lang='yaml'>links:
 *   address:
 *     operationId: getUserAddressByUUID
 *     parameters:
 *       # get the `uuid` field from the `uuid` field in the response body
 *       userUuid: $response.body#/uuid
 * </code></pre>
 * <p><span>Clients follow all links at their discretion. </span>
 * <span>Neither permissions, nor the capability to make a successful call to that link, is guaranteed </span>
 * <span>solely by the existence of a relationship.</span></p>
 * <h5><a name="operationref-examples" class="md-header-anchor"></a><span>OperationRef Examples</span></h5>
 * <p><span>As references to </span><code>operationId</code><span> MAY NOT be possible (the </span><code>operationId</code><span> is an optional </span>
 * <span>value), references MAY also be made through a relative </span><code>operationRef</code><span>:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>links:
 *   UserRepositories:
 *     # returns array of &#39;#/components/schemas/repository&#39;
 *     operationRef: &#39;#/paths/~12.0~1repositories~1{username}/get&#39;
 *     parameters:
 *       username: $response.body#/username
 * </code></pre>
 * <p><span>or an absolute </span><code>operationRef</code><span>:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>links:
 *   UserRepositories:
 *     # returns array of &#39;#/components/schemas/repository&#39;
 *     operationRef: &#39;https://na2.gigantic-server.com/#/paths/~12.0~1repositories~1{username}/get&#39;
 *     parameters:
 *       username: $response.body#/username
 * </code></pre>
 * <p><span>Note that in the use of </span><code>operationRef</code><span>, the </span><em><span>escaped forward-slash</span></em><span> is necessary when </span>
 * <span>using JSON references.</span></p>
 * <h5><a name="%3Ca-name=%22runtimeexpression%22%3E%3C/a%3Eruntime-expressions" class="md-header-anchor"></a><a name="runtimeExpression"></a><span>Runtime Expressions</span></h5>
 * <p><span>Runtime expressions allow defining values based on information that will only be available within the HTTP message in an actual API call.</span>
 * <span>This mechanism is used by </span><a href='#'><span>Link Objects</span></a><span> and </span><a href='#'><span>Callback Objects</span></a><span>.</span></p>
 * <p><span>The runtime expression is defined by the following </span><a href='https://tools.ietf.org/html/rfc5234'><span>ABNF</span></a><span> syntax</span></p>
 * <pre><code>      expression = ( &quot;$url&quot; | &quot;$method&quot; | &quot;$statusCode&quot; | &quot;$request.&quot; source | &quot;$response.&quot; source )
 *       source = ( header-reference | query-reference | path-reference | body-reference )
 *       header-reference = &quot;header.&quot; token
 *       query-reference = &quot;query.&quot; name
 *       path-reference = &quot;path.&quot; name
 *       body-reference = &quot;body&quot; [&quot;#&quot; fragment]
 *       fragment = a JSON Pointer [RFC 6901](https://tools.ietf.org/html/rfc6901)
 *       name = *( char )
 *       char = as per RFC [7159](https://tools.ietf.org/html/rfc7159#section-7)
 *       token = as per RFC [7230](https://tools.ietf.org/html/rfc7230#section-3.2.6)
 * </code></pre>
 * <p><span>The </span><code>name</code><span> identifier is case-sensitive, whereas </span><code>token</code><span> is not. </span></p>
 * <p><span>The table below provides examples of runtime expressions and examples of their use in a value:</span></p>
 * <h5><a name="%3Ca-name=%22runtimeexpressionexamples%22%3E%3C/a%3Eexamples" class="md-header-anchor"></a><a name="runtimeExpressionExamples"></a><span>Examples</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Source Location</span></th><th style='text-align:left;' ><span>example expression</span></th><th style='text-align:left;' ><span>notes</span></th></tr></thead>
 * <tbody><tr><td><span>HTTP Method</span></td><td style='text-align:left;' ><code>$method</code></td><td style='text-align:left;' ><span>The allowable values for the </span><code>$method</code><span> will be those for the HTTP operation.</span></td></tr><tr><td><span>Requested media type</span></td><td style='text-align:left;' ><code>$request.header.accept</code></td><td style='text-align:left;' >&nbsp;</td></tr><tr><td><span>Request parameter</span></td><td style='text-align:left;' ><code>$request.path.id</code></td><td style='text-align:left;' ><span>Request parameters MUST be declared in the </span><code>parameters</code><span> section of the parent operation or they cannot be evaluated. This includes request headers.</span></td></tr><tr><td><span>Request body property</span></td><td style='text-align:left;' ><code>$request.body#/user/uuid</code></td><td style='text-align:left;' ><span>In operations which accept payloads, references may be made to portions of the </span><code>requestBody</code><span> or the entire body.</span></td></tr><tr><td><span>Request URL</span></td><td style='text-align:left;' ><code>$url</code></td><td style='text-align:left;' >&nbsp;</td></tr><tr><td><span>Response value</span></td><td style='text-align:left;' ><code>$response.body#/status</code></td><td style='text-align:left;' ><span>In operations which return payloads, references may be made to portions of the response body or the entire body.</span></td></tr><tr><td><span>Response header</span></td><td style='text-align:left;' ><code>$response.header.Server</code></td><td style='text-align:left;' ><span>Single header values only are available</span></td></tr></tbody>
 * </table></figure>
 * <p><span>Runtime expressions preserve the type of the referenced value.</span>
 * <span>Expressions can be embedded into string values by surrounding the expression with </span><code>{}</code><span> curly braces.</span></p>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#linkObject"
 */

public class Link {
    private String operationRef = null;
    private String operationId = null;
    private Map<String, String> parameters = null;
    private Object requestBody = null;
    private Map<String, Header> headers = null;
    private String description = null;
    private String $ref = null;
    private Map<String, Object> extensions = null;
    private Server server;

    /**
     * returns the server property from a Link instance.
     *
     * @return Server server
     **/

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Link server(Server server) {
        this.setServer(server);
        return this;
    }

    /**
     * returns the operationRef property from a Link instance.
     *
     * @return String operationRef
     **/

    public String getOperationRef() {
        return operationRef;
    }

    public void setOperationRef(String operationRef) {
        this.operationRef = operationRef;
    }

    public Link operationRef(String operationRef) {
        this.operationRef = operationRef;
        return this;
    }

    /**
     * returns the requestBody property from a Link instance.
     *
     * @return Object requestBody
     **/

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public Link requestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Link operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * returns the parameters property from a Link instance.
     *
     * @return LinkParameters parameters
     **/

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Link parameters(String name, String parameter) {
        if (this.parameters == null) {
            this.parameters = new LinkedHashMap<>();
        }
        this.parameters.put(name, parameter);

        return this;
    }

    /**
     * returns the headers property from a Link instance.
     *
     * @return Headers headers
     **/

    public Map<String, Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Header> headers) {
        this.headers = headers;
    }

    public Link headers(Map<String, Header> headers) {
        this.headers = headers;
        return this;
    }

    public Link addHeaderObject(String name, Header header) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(name, header);
        return this;
    }

    /**
     * returns the description property from a Link instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Link description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Link)) {
            return false;
        }

        Link link = (Link) o;

        if (operationRef != null ? !operationRef.equals(link.operationRef) : link.operationRef != null) {
            return false;
        }
        if (operationId != null ? !operationId.equals(link.operationId) : link.operationId != null) {
            return false;
        }
        if (parameters != null ? !parameters.equals(link.parameters) : link.parameters != null) {
            return false;
        }
        if (requestBody != null ? !requestBody.equals(link.requestBody) : link.requestBody != null) {
            return false;
        }
        if (headers != null ? !headers.equals(link.headers) : link.headers != null) {
            return false;
        }
        if (description != null ? !description.equals(link.description) : link.description != null) {
            return false;
        }
        if ($ref != null ? !$ref.equals(link.$ref) : link.$ref != null) {
            return false;
        }
        if (extensions != null ? !extensions.equals(link.extensions) : link.extensions != null) {
            return false;
        }
        return server != null ? server.equals(link.server) : link.server == null;

    }

    @Override
    public int hashCode() {
        int result = operationRef != null ? operationRef.hashCode() : 0;
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + (requestBody != null ? requestBody.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + ($ref != null ? $ref.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        result = 31 * result + (server != null ? server.hashCode() : 0);
        return result;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/links/" + $ref;
        }
        this.$ref = $ref;
    }

    public Link $ref(String $ref) {
        set$ref($ref);
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

    public Link extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Link {\n");

        sb.append("    operationRef: ").append(toIndentedString(operationRef)).append("\n");
        sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("    requestBody: ").append(toIndentedString(requestBody)).append("\n");
        sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

