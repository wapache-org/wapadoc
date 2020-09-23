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
import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.links.Link;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.parameters.RequestBody;
import org.wapache.openapi.v3.models.responses.ApiResponse;
import org.wapache.openapi.v3.models.security.SecurityScheme;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Components
 *<h4><a name="%3Ca-name=%22componentsobject%22%3E%3C/a%3Ecomponents-object" class="md-header-anchor"></a><a name="componentsObject"></a><span>Components Object</span></h4>
 * <p><span>Holds a set of reusable objects for different aspects of the OAS.</span>
 * <span>All objects defined within the components object will have no effect on the API unless they are explicitly referenced from properties outside the components object.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:left;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="componentsSchemas"></a><span> schemas</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Schema Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Schema Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsResponses"></a><span> responses</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Response Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Response Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsParameters"></a><span> parameters</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Parameter Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Parameter Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsExamples"></a><span> examples</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Example Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Example Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsRequestBodies"></a><span> requestBodies</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Request Body Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Request Body Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsHeaders"></a><span> headers</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Header Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Header Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsSecuritySchemes"></a><span> securitySchemes</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Security Scheme Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Security Scheme Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsLinks"></a><span> links</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Link Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Link Objects</span></a><span>.</span></td></tr><tr><td><a name="componentsCallbacks"></a><span> callbacks</span></td><td style='text-align:left;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Callback Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>An object to hold reusable </span><a href='#'><span>Callback Objects</span></a><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <p><span>All the fixed fields declared above are objects that MUST use keys that match the regular expression: </span><code>^[a-zA-Z0-9\.\-_]+$</code><span>.</span></p>
 * <p><span>Field Name Examples:</span></p>
 * <pre><code>User
 * User_1
 * User_Name
 * user-name
 * my.org.User
 * </code></pre>
 * <h5><a name="components-object-example" class="md-header-anchor"></a><span>Components Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>&quot;components&quot;: {
 *   &quot;schemas&quot;: {
 *     &quot;Category&quot;: {
 *       &quot;type&quot;: &quot;object&quot;,
 *       &quot;properties&quot;: {
 *         &quot;id&quot;: {
 *           &quot;type&quot;: &quot;integer&quot;,
 *           &quot;format&quot;: &quot;int64&quot;
 *         },
 *         &quot;name&quot;: {
 *           &quot;type&quot;: &quot;string&quot;
 *         }
 *       }
 *     },
 *     &quot;Tag&quot;: {
 *       &quot;type&quot;: &quot;object&quot;,
 *       &quot;properties&quot;: {
 *         &quot;id&quot;: {
 *           &quot;type&quot;: &quot;integer&quot;,
 *           &quot;format&quot;: &quot;int64&quot;
 *         },
 *         &quot;name&quot;: {
 *           &quot;type&quot;: &quot;string&quot;
 *         }
 *       }
 *     }
 *   },
 *   &quot;parameters&quot;: {
 *     &quot;skipParam&quot;: {
 *       &quot;name&quot;: &quot;skip&quot;,
 *       &quot;in&quot;: &quot;query&quot;,
 *       &quot;description&quot;: &quot;number of items to skip&quot;,
 *       &quot;required&quot;: true,
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;integer&quot;,
 *         &quot;format&quot;: &quot;int32&quot;
 *       }
 *     },
 *     &quot;limitParam&quot;: {
 *       &quot;name&quot;: &quot;limit&quot;,
 *       &quot;in&quot;: &quot;query&quot;,
 *       &quot;description&quot;: &quot;max records to return&quot;,
 *       &quot;required&quot;: true,
 *       &quot;schema&quot; : {
 *         &quot;type&quot;: &quot;integer&quot;,
 *         &quot;format&quot;: &quot;int32&quot;
 *       }
 *     }
 *   },
 *   &quot;responses&quot;: {
 *     &quot;NotFound&quot;: {
 *       &quot;description&quot;: &quot;Entity not found.&quot;
 *     },
 *     &quot;IllegalInput&quot;: {
 *       &quot;description&quot;: &quot;Illegal input for operation.&quot;
 *     },
 *     &quot;GeneralError&quot;: {
 *       &quot;description&quot;: &quot;General Error&quot;,
 *       &quot;content&quot;: {
 *         &quot;application/json&quot;: {
 *           &quot;schema&quot;: {
 *             &quot;$ref&quot;: &quot;#/components/schemas/GeneralError&quot;
 *           }
 *         }
 *       }
 *     }
 *   },
 *   &quot;securitySchemes&quot;: {
 *     &quot;api_key&quot;: {
 *       &quot;type&quot;: &quot;apiKey&quot;,
 *       &quot;name&quot;: &quot;api_key&quot;,
 *       &quot;in&quot;: &quot;header&quot;
 *     },
 *     &quot;petstore_auth&quot;: {
 *       &quot;type&quot;: &quot;oauth2&quot;,
 *       &quot;flows&quot;: {
 *         &quot;implicit&quot;: {
 *           &quot;authorizationUrl&quot;: &quot;http://example.org/api/oauth/dialog&quot;,
 *           &quot;scopes&quot;: {
 *             &quot;write:pets&quot;: &quot;modify pets in your account&quot;,
 *             &quot;read:pets&quot;: &quot;read your pets&quot;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>components:
 *   schemas:
 *     Category:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           format: int64
 *         name:
 *           type: string
 *     Tag:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           format: int64
 *         name:
 *           type: string
 *   parameters:
 *     skipParam:
 *       name: skip
 *       in: query
 *       description: number of items to skip
 *       required: true
 *       schema:
 *         type: integer
 *         format: int32
 *     limitParam:
 *       name: limit
 *       in: query
 *       description: max records to return
 *       required: true
 *       schema:
 *         type: integer
 *         format: int32
 *   responses:
 *     NotFound:
 *       description: Entity not found.
 *     IllegalInput:
 *       description: Illegal input for operation.
 *     GeneralError:
 *       description: General Error
 *       content:
 *         application/json:
 *           schema:
 *             $ref: &#39;#/components/schemas/GeneralError&#39;
 *   securitySchemes:
 *     api_key:
 *       type: apiKey
 *       name: api_key
 *       in: header
 *     petstore_auth:
 *       type: oauth2
 *       flows:
 *         implicit:
 *           authorizationUrl: http://example.org/api/oauth/dialog
 *           scopes:
 *             write:pets: modify pets in your account
 *             read:pets: read your pets
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#componentsObject"
 */

public class Components {
    /**
     * An object to hold reusable [Schema Objects](#schemaObject).
     */
    private Map<String, Schema> schemas = null;
    /**
     * An object to hold reusable [Response Objects](#responseObject).
     */
    private Map<String, ApiResponse> responses = null;
    /**
     * An object to hold reusable [Parameter Objects](#parameterObject).
     */
    private Map<String, Parameter> parameters = null;
    /**
     * An object to hold reusable [Example Objects](#exampleObject).
     */
    private Map<String, Example> examples = null;
    /**
     * An object to hold reusable [Request Body Objects](#requestBodyObject).
     */
    private Map<String, RequestBody> requestBodies = null;
    /**
     * An object to hold reusable [Header Objects](#headerObject).
     */
    private Map<String, Header> headers = null;
    /**
     * An object to hold reusable [Security Scheme Objects](#securitySchemeObject).
     */
    private Map<String, SecurityScheme> securitySchemes = null;
    /**
     * An object to hold reusable [Link Objects](#linkObject).
     */
    private Map<String, Link> links = null;
    /**
     * An object to hold reusable [Callback Objects](#callbackObject).
     */
    private Map<String, Callback> callbacks = null;
    /**
     * This object MAY be extended with [Specification Extensions](#specificationExtensions).
     */
    private Map<String, Object> extensions = null;

    /**
     * returns the schemas property from a Components instance.
     *
     * @return Map&lt;String, Schema&gt; schemas
     **/

    public Map<String, Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, Schema> schemas) {
        this.schemas = schemas;
    }

    public Components schemas(Map<String, Schema> schemas) {
        this.schemas = schemas;
        return this;
    }

    public Components addSchemas(String key, Schema schemasItem) {
        if (this.schemas == null) {
            this.schemas = new LinkedHashMap<>();
        }
        this.schemas.put(key, schemasItem);
        return this;
    }

    /**
     * returns the responses property from a Components instance.
     *
     * @return Map&lt;String, ApiResponse&gt; responses
     **/

    public Map<String, ApiResponse> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, ApiResponse> responses) {
        this.responses = responses;
    }

    public Components responses(Map<String, ApiResponse> responses) {
        this.responses = responses;
        return this;
    }

    public Components addResponses(String key, ApiResponse responsesItem) {
        if (this.responses == null) {
            this.responses = new LinkedHashMap<>();
        }
        this.responses.put(key, responsesItem);
        return this;
    }

    /**
     * returns the parameters property from a Components instance.
     *
     * @return Map&lt;String, Parameter&gt; parameters
     **/

    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }

    public Components parameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Components addParameters(String key, Parameter parametersItem) {
        if (this.parameters == null) {
            this.parameters = new LinkedHashMap<>();
        }
        this.parameters.put(key, parametersItem);
        return this;
    }

    /**
     * returns the examples property from a Components instance.
     *
     * @return Map&lt;String, Example&gt; examples
     **/

    public Map<String, Example> getExamples() {
        return examples;
    }

    public void setExamples(Map<String, Example> examples) {
        this.examples = examples;
    }

    public Components examples(Map<String, Example> examples) {
        this.examples = examples;
        return this;
    }

    public Components addExamples(String key, Example examplesItem) {
        if (this.examples == null) {
            this.examples = new LinkedHashMap<>();
        }
        this.examples.put(key, examplesItem);
        return this;
    }

    /**
     * returns the requestBodies property from a Components instance.
     *
     * @return Map&lt;String, RequestBody&gt; requestBodies
     **/

    public Map<String, RequestBody> getRequestBodies() {
        return requestBodies;
    }

    public void setRequestBodies(Map<String, RequestBody> requestBodies) {
        this.requestBodies = requestBodies;
    }

    public Components requestBodies(Map<String, RequestBody> requestBodies) {
        this.requestBodies = requestBodies;
        return this;
    }

    public Components addRequestBodies(String key, RequestBody requestBodiesItem) {
        if (this.requestBodies == null) {
            this.requestBodies = new LinkedHashMap<>();
        }
        this.requestBodies.put(key, requestBodiesItem);
        return this;
    }

    /**
     * returns the headers property from a Components instance.
     *
     * @return Map&lt;String, Header&gt; headers
     **/

    public Map<String, Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Header> headers) {
        this.headers = headers;
    }

    public Components headers(Map<String, Header> headers) {
        this.headers = headers;
        return this;
    }

    public Components addHeaders(String key, Header headersItem) {
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        this.headers.put(key, headersItem);
        return this;
    }

    /**
     * returns the securitySchemes property from a Components instance.
     *
     * @return Map&lt;String, SecurityScheme&gt; securitySchemes
     **/

    public Map<String, SecurityScheme> getSecuritySchemes() {
        return securitySchemes;
    }

    public void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
    }

    public Components securitySchemes(Map<String, SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
        return this;
    }

    public Components addSecuritySchemes(String key, SecurityScheme securitySchemesItem) {
        if (this.securitySchemes == null) {
            this.securitySchemes = new LinkedHashMap<>();
        }
        this.securitySchemes.put(key, securitySchemesItem);
        return this;
    }

    /**
     * returns the links property from a Components instance.
     *
     * @return Map&lt;String, Link&gt; links
     **/

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

    public Components links(Map<String, Link> links) {
        this.links = links;
        return this;
    }

    public Components addLinks(String key, Link linksItem) {
        if (this.links == null) {
            this.links = new LinkedHashMap<>();
        }
        this.links.put(key, linksItem);
        return this;
    }

    /**
     * returns the callbacks property from a Components instance.
     *
     * @return Map&lt;String, Callback&gt; callbacks
     **/

    public Map<String, Callback> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(Map<String, Callback> callbacks) {
        this.callbacks = callbacks;
    }

    public Components callbacks(Map<String, Callback> callbacks) {
        this.callbacks = callbacks;
        return this;
    }

    public Components addCallbacks(String key, Callback callbacksItem) {
        if (this.callbacks == null) {
            this.callbacks = new LinkedHashMap<>();
        }
        this.callbacks.put(key, callbacksItem);
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
        Components components = (Components) o;
        return Objects.equals(this.schemas, components.schemas) &&
                Objects.equals(this.responses, components.responses) &&
                Objects.equals(this.parameters, components.parameters) &&
                Objects.equals(this.examples, components.examples) &&
                Objects.equals(this.requestBodies, components.requestBodies) &&
                Objects.equals(this.headers, components.headers) &&
                Objects.equals(this.securitySchemes, components.securitySchemes) &&
                Objects.equals(this.links, components.links) &&
                Objects.equals(this.callbacks, components.callbacks) &&
                Objects.equals(this.extensions, components.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemas, responses, parameters, examples, requestBodies, headers, securitySchemes, links, callbacks, extensions);
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

    public Components extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Components {\n");

        sb.append("    schemas: ").append(toIndentedString(schemas)).append("\n");
        sb.append("    responses: ").append(toIndentedString(responses)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
        sb.append("    examples: ").append(toIndentedString(examples)).append("\n");
        sb.append("    requestBodies: ").append(toIndentedString(requestBodies)).append("\n");
        sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
        sb.append("    securitySchemes: ").append(toIndentedString(securitySchemes)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("    callbacks: ").append(toIndentedString(callbacks)).append("\n");
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

