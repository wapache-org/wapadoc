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

package org.wapache.openapi.v3.models.servers;

import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22serverobject%22%3E%3C/a%3Eserver-object" class="md-header-anchor"></a><a name="serverObject"></a><span>Server Object</span></h4>
 * <p><span>An object representing a Server.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="serverUrl"></a><span>url</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. A URL to the target host.  This URL supports Server Variables and MAY be relative, to indicate that the host location is relative to the location where the OpenAPI document is being served. Variable substitutions will be made when a variable is named in </span><code>{</code><span>brackets</span><code>}</code><span>.</span></td></tr><tr><td><a name="serverDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>An optional string describing the host designated by the URL. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="serverVariables"></a><span>variables</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Server Variable Object</span></a><span>]</span></td><td><span>A map between a variable name and its value.  The value is used for substitution in the server&#39;s URL template.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="server-object-example" class="md-header-anchor"></a><span>Server Object Example</span></h5>
 * <p><span>A single server would be described as:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;url&quot;: &quot;https://development.gigantic-server.com/v1&quot;,
 *   &quot;description&quot;: &quot;Development server&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>url: https://development.gigantic-server.com/v1
 * description: Development server
 * </code></pre>
 * <p><span>The following shows how multiple servers can be described, for example, at the OpenAPI Object&#39;s </span><a href='#'><code>servers</code></a><span>:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;servers&quot;: [
 *     {
 *       &quot;url&quot;: &quot;https://development.gigantic-server.com/v1&quot;,
 *       &quot;description&quot;: &quot;Development server&quot;
 *     },
 *     {
 *       &quot;url&quot;: &quot;https://staging.gigantic-server.com/v1&quot;,
 *       &quot;description&quot;: &quot;Staging server&quot;
 *     },
 *     {
 *       &quot;url&quot;: &quot;https://api.gigantic-server.com/v1&quot;,
 *       &quot;description&quot;: &quot;Production server&quot;
 *     }
 *   ]
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>servers:
 * - url: https://development.gigantic-server.com/v1
 *   description: Development server
 * - url: https://staging.gigantic-server.com/v1
 *   description: Staging server
 * - url: https://api.gigantic-server.com/v1
 *   description: Production server
 * </code></pre>
 * <p><span>The following shows how variables can be used for a server configuration:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;servers&quot;: [
 *     {
 *       &quot;url&quot;: &quot;https://{username}.gigantic-server.com:{port}/{basePath}&quot;,
 *       &quot;description&quot;: &quot;The production API server&quot;,
 *       &quot;variables&quot;: {
 *         &quot;username&quot;: {
 *           &quot;default&quot;: &quot;demo&quot;,
 *           &quot;description&quot;: &quot;this value is assigned by the service provider, in this example `gigantic-server.com`&quot;
 *         },
 *         &quot;port&quot;: {
 *           &quot;enum&quot;: [
 *             &quot;8443&quot;,
 *             &quot;443&quot;
 *           ],
 *           &quot;default&quot;: &quot;8443&quot;
 *         },
 *         &quot;basePath&quot;: {
 *           &quot;default&quot;: &quot;v2&quot;
 *         }
 *       }
 *     }
 *   ]
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>servers:
 * - url: https://{username}.gigantic-server.com:{port}/{basePath}
 *   description: The production API server
 *   variables:
 *     username:
 *       # note! no enum here means it is an open value
 *       default: demo
 *       description: this value is assigned by the service provider, in this example `gigantic-server.com`
 *     port:
 *       enum:
 *         - &#39;8443&#39;
 *         - &#39;443&#39;
 *       default: &#39;8443&#39;
 *     basePath:
 *       # open meaning there is the opportunity to use special base paths as assigned by the provider, default is `v2`
 *       default: v2
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#serverObject"
 */

public class Server {
    private String url = null;
    private String description = null;
    private ServerVariables variables = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the url property from a Server instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Server url(String url) {
        this.url = url;
        return this;
    }

    /**
     * returns the description property from a Server instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Server description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the variables property from a Server instance.
     *
     * @return ServerVariables variables
     **/

    public ServerVariables getVariables() {
        return variables;
    }

    public void setVariables(ServerVariables variables) {
        this.variables = variables;
    }

    public Server variables(ServerVariables variables) {
        this.variables = variables;
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
        Server server = (Server) o;
        return Objects.equals(this.url, server.url) &&
                Objects.equals(this.description, server.description) &&
                Objects.equals(this.variables, server.variables) &&
                Objects.equals(this.extensions, server.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, description, variables, extensions);
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

    public Server extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Server {\n");

        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
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

