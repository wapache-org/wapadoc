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

import org.wapache.openapi.v3.models.info.Info;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.security.SecurityRequirement;
import org.wapache.openapi.v3.models.security.SecurityScheme;
import org.wapache.openapi.v3.models.servers.Server;
import org.wapache.openapi.v3.models.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22oasobject%22%3E%3C/a%3Eopenapi-object" class="md-header-anchor"></a><a name="oasObject"></a><span>OpenAPI Object</span></h4>
 * <p><span>This is the root document object of the </span><a href='#'><span>OpenAPI document</span></a><span>.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="oasVersion"></a><span>openapi</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. This string MUST be the </span><a href='https://semver.org/spec/v2.0.0.html'><span>semantic version number</span></a><span> of the </span><a href='#versions'><span>OpenAPI Specification version</span></a><span> that the OpenAPI document uses. The </span><code>openapi</code><span> field SHOULD be used by tooling specifications and clients to interpret the OpenAPI document. This is </span><em><span>not</span></em><span> related to the API </span><a href='#'><code>info.version</code></a><span> string.</span></td></tr><tr><td><a name="oasInfo"></a><span>info</span></td><td style='text-align:center;' ><a href='#'><span>Info Object</span></a></td><td><strong><span>REQUIRED</span></strong><span>. Provides metadata about the API. The metadata MAY be used by tooling as required.</span></td></tr><tr><td><a name="oasServers"></a><span>servers</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Server Object</span></a><span>]</span></td><td><span>An array of Server Objects, which provide connectivity information to a target server. If the </span><code>servers</code><span> property is not provided, or is an empty array, the default value would be a </span><a href='#'><span>Server Object</span></a><span> with a </span><a href='#'><span>url</span></a><span> value of </span><code>/</code><span>.</span></td></tr><tr><td><a name="oasPaths"></a><span>paths</span></td><td style='text-align:center;' ><a href='#'><span>Paths Object</span></a></td><td><strong><span>REQUIRED</span></strong><span>. The available paths and operations for the API.</span></td></tr><tr><td><a name="oasComponents"></a><span>components</span></td><td style='text-align:center;' ><a href='#'><span>Components Object</span></a></td><td><span>An element to hold various schemas for the specification.</span></td></tr><tr><td><a name="oasSecurity"></a><span>security</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Security Requirement Object</span></a><span>]</span></td><td><span>A declaration of which security mechanisms can be used across the API. The list of values includes alternative security requirement objects that can be used. Only one of the security requirement objects need to be satisfied to authorize a request. Individual operations can override this definition.</span></td></tr><tr><td><a name="oasTags"></a><span>tags</span></td><td style='text-align:center;' ><span>[</span><a href='#'><span>Tag Object</span></a><span>]</span></td><td><span>A list of tags used by the specification with additional metadata. The order of the tags can be used to reflect on their order by the parsing tools. Not all tags that are used by the </span><a href='#'><span>Operation Object</span></a><span> must be declared. The tags that are not declared MAY be organized randomly or based on the tools&#39; logic. Each tag name in the list MUST be unique.</span></td></tr><tr><td><a name="oasExternalDocs"></a><span>externalDocs</span></td><td style='text-align:center;' ><a href='#'><span>External Documentation Object</span></a></td><td><span>Additional external documentation.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md"
 */

public class OpenAPI {
    private String openapi = "3.0.1";
    private Info info = null;
    private ExternalDocumentation externalDocs = null;
    private List<Server> servers = null;
    private List<SecurityRequirement> security = null;
    private List<Tag> tags = null;
    private Paths paths = null;
    private Components components = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the openapi property from a OpenAPI instance.
     *
     * @return String openapi
     **/

    public String getOpenapi() {
        return openapi;
    }

    public void setOpenapi(String openapi) {
        this.openapi = openapi;
    }

    public OpenAPI openapi(String openapi) {
        this.openapi = openapi;
        return this;
    }

    /**
     * returns the info property from a OpenAPI instance.
     *
     * @return Info info
     **/

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public OpenAPI info(Info info) {
        this.info = info;
        return this;
    }

    /**
     * returns the externalDocs property from a OpenAPI instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public OpenAPI externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    /**
     * Servers defined in the API
     *
     * @return List&lt;Server&gt; servers
     **/

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public OpenAPI servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }

    public OpenAPI addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
        return this;
    }

    /**
     * returns the security property from a OpenAPI instance.
     *
     * @return List&lt;SecurityRequirement&gt; security
     **/

    public List<SecurityRequirement> getSecurity() {
        return security;
    }

    public void setSecurity(List<SecurityRequirement> security) {
        this.security = security;
    }

    public OpenAPI security(List<SecurityRequirement> security) {
        this.security = security;
        return this;
    }

    public OpenAPI addSecurityItem(SecurityRequirement securityItem) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityItem);
        return this;
    }

    /**
     * returns the tags property from a OpenAPI instance.
     *
     * @return List&lt;Tag&gt; tags
     **/

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public OpenAPI tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public OpenAPI addTagsItem(Tag tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * returns the paths property from a OpenAPI instance.
     *
     * @return Paths paths
     **/

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    public OpenAPI paths(Paths paths) {
        this.paths = paths;
        return this;
    }

    /**
     * returns the components property from a OpenAPI instance.
     *
     * @return Components components
     **/

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public OpenAPI components(Components components) {
        this.components = components;
        return this;
    }

  /*
   * helpers
   */

    public OpenAPI path(String name, PathItem path) {
        if (this.paths == null) {
            this.paths = new Paths();
        }

        this.paths.addPathItem(name, path);
        return this;
    }

    public OpenAPI schema(String name, Schema schema) {
        if (components == null) {
            this.components = new Components();
        }
        components.addSchemas(name, schema);
        return this;
    }

    public OpenAPI schemaRequirement(String name, SecurityScheme securityScheme) {
        if (components == null) {
            this.components = new Components();
        }
        components.addSecuritySchemes(name, securityScheme);
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
        OpenAPI openAPI = (OpenAPI) o;
        return Objects.equals(this.openapi, openAPI.openapi) &&
                Objects.equals(this.info, openAPI.info) &&
                Objects.equals(this.externalDocs, openAPI.externalDocs) &&
                Objects.equals(this.servers, openAPI.servers) &&
                Objects.equals(this.security, openAPI.security) &&
                Objects.equals(this.tags, openAPI.tags) &&
                Objects.equals(this.paths, openAPI.paths) &&
                Objects.equals(this.components, openAPI.components) &&
                Objects.equals(this.extensions, openAPI.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openapi, info, externalDocs, servers, security, tags, paths, components, extensions);
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

    public OpenAPI extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OpenAPI {\n");

        sb.append("    openapi: ").append(toIndentedString(openapi)).append("\n");
        sb.append("    info: ").append(toIndentedString(info)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
        sb.append("    security: ").append(toIndentedString(security)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    paths: ").append(toIndentedString(paths)).append("\n");
        sb.append("    components: ").append(toIndentedString(components)).append("\n");
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

