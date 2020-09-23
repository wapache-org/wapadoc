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

import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.links.Link;
import org.wapache.openapi.v3.models.media.Content;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22responseobject%22%3E%3C/a%3Eresponse-object" class="md-header-anchor"></a><a name="responseObject"></a><span>Response Object</span></h4>
 * <p><span>Describes a single response from an API Operation, including design-time, static </span>
 * <code>links</code><span> to operations based on the response.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="responseDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. A short description of the response. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="responseHeaders"></a><span>headers</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Header Object</span></a><span>  </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>Maps a header name to its definition. </span><a href='https://tools.ietf.org/html/rfc7230#page-22'><span>RFC7230</span></a><span> states header names are case insensitive. If a response header is defined with the name </span><code>&quot;Content-Type&quot;</code><span>, it SHALL be ignored.</span></td></tr><tr><td><a name="responseContent"></a><span>content</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Media Type Object</span></a><span>]</span></td><td><span>A map containing descriptions of potential response payloads. The key is a media type or </span><a href='https://tools.ietf.org/html/rfc7231#appendix-D'><span>media type range</span></a><span> and the value describes it.  For responses that match multiple keys, only the most specific key is applicable. e.g. text/plain overrides text/*</span></td></tr><tr><td><a name="responseLinks"></a><span>links</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Link Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>A map of operations links that can be followed from the response. The key of the map is a short name for the link, following the naming constraints of the names for </span><a href='#'><span>Component Objects</span></a><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="response-object-examples" class="md-header-anchor"></a><span>Response Object Examples</span></h5>
 * <p><span>Response of an array of a complex type:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;A complex object array response&quot;,
 *   &quot;content&quot;: {
 *     &quot;application/json&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;array&quot;,
 *         &quot;items&quot;: {
 *           &quot;$ref&quot;: &quot;#/components/schemas/VeryComplexType&quot;
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: A complex object array response
 * content:
 *   application/json:
 *     schema:
 *       type: array
 *       items:
 *         $ref: &#39;#/components/schemas/VeryComplexType&#39;
 * </code></pre>
 * <p><span>Response with a string type:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;A simple string response&quot;,
 *   &quot;content&quot;: {
 *     &quot;text/plain&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;string&quot;
 *       }
 *     }
 *   }
 *
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: A simple string response
 * content:
 *   text/plain:
 *     schema:
 *       type: string
 * </code></pre>
 * <p><span>Plain text response with headers:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;A simple string response&quot;,
 *   &quot;content&quot;: {
 *     &quot;text/plain&quot;: {
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;string&quot;
 *       }
 *     }
 *   },
 *   &quot;headers&quot;: {
 *     &quot;X-Rate-Limit-Limit&quot;: {
 *       &quot;description&quot;: &quot;The number of allowed requests in the current period&quot;,
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;integer&quot;
 *       }
 *     },
 *     &quot;X-Rate-Limit-Remaining&quot;: {
 *       &quot;description&quot;: &quot;The number of remaining requests in the current period&quot;,
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;integer&quot;
 *       }
 *     },
 *     &quot;X-Rate-Limit-Reset&quot;: {
 *       &quot;description&quot;: &quot;The number of seconds left in the current period&quot;,
 *       &quot;schema&quot;: {
 *         &quot;type&quot;: &quot;integer&quot;
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: A simple string response
 * content:
 *   text/plain:
 *     schema:
 *       type: string
 *     example: &#39;whoa!&#39;
 * headers:
 *   X-Rate-Limit-Limit:
 *     description: The number of allowed requests in the current period
 *     schema:
 *       type: integer
 *   X-Rate-Limit-Remaining:
 *     description: The number of remaining requests in the current period
 *     schema:
 *       type: integer
 *   X-Rate-Limit-Reset:
 *     description: The number of seconds left in the current period
 *     schema:
 *       type: integer
 * </code></pre>
 * <p><span>Response with no return value:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;object created&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: object created
 * </code></pre>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#responseObject"
 */

public class ApiResponse {
    private String description = null;
    private Map<String, Header> headers = null;
    private Content content = null;
    private Map<String, Link> links = null;
    private Map<String, Object> extensions = null;
    private String $ref = null;

    /**
     * returns the description property from a ApiResponse instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApiResponse description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the headers property from a ApiResponse instance.
     *
     * @return headers
     **/

    public Map<String, Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Header> headers) {
        this.headers = headers;
    }

    public ApiResponse headers(Map<String, Header> headers) {
        this.headers = headers;
        return this;
    }

    public ApiResponse addHeaderObject(String name, Header header) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(name, header);
        return this;
    }

    /**
     * returns the content property from a ApiResponse instance.
     *
     * @return Content content
     **/

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public ApiResponse content(Content content) {
        this.content = content;
        return this;
    }

    /**
     * returns the links property from a ApiResponse instance.
     *
     * @return Link links
     **/

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

    public ApiResponse link(String name, Link link) {
        if (this.links == null) {
            this.links = new LinkedHashMap<>();
        }
        this.links.put(name, link);
        return this;
    }

    /**
     * returns the $ref property from an ApiResponse instance.
     *
     * @return String $ref
     **/
    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/responses/" + $ref;
        }
        this.$ref = $ref;
    }

    public ApiResponse $ref(String $ref) {
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
        ApiResponse apiResponse = (ApiResponse) o;
        return Objects.equals(this.description, apiResponse.description) &&
                Objects.equals(this.headers, apiResponse.headers) &&
                Objects.equals(this.content, apiResponse.content) &&
                Objects.equals(this.links, apiResponse.links) &&
                Objects.equals(this.extensions, apiResponse.extensions) &&
                Objects.equals(this.$ref, apiResponse.$ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, headers, content, links, extensions, $ref);
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

    public ApiResponse extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiResponse {\n");

        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

