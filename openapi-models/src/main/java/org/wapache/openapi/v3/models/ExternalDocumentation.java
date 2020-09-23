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

import java.util.Objects;

/**
 *
 * <h4><a name="%3Ca-name=%22externaldocumentationobject%22%3E%3C/a%3Eexternal-documentation-object" class="md-header-anchor"></a><a name="externalDocumentationObject"></a><span>External Documentation Object</span></h4>
 * <p><span>Allows referencing an external resource for extended documentation.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="externalDocDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A short description of the target documentation. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="externalDocUrl"></a><span>url</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The URL for the target documentation. Value MUST be in the format of a URL.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="external-documentation-object-example" class="md-header-anchor"></a><span>External Documentation Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;description&quot;: &quot;Find more info here&quot;,
 *   &quot;url&quot;: &quot;https://example.com&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>description: Find more info here
 * url: https://example.com
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#externalDocumentationObject"
 */

public class ExternalDocumentation {
    private String description = null;
    private String url = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the description property from a ExternalDocumentation instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExternalDocumentation description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the url property from a ExternalDocumentation instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ExternalDocumentation url(String url) {
        this.url = url;
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
        ExternalDocumentation externalDocumentation = (ExternalDocumentation) o;
        return Objects.equals(this.description, externalDocumentation.description) &&
                Objects.equals(this.url, externalDocumentation.url) &&
                Objects.equals(this.extensions, externalDocumentation.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url, extensions);
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

    public ExternalDocumentation extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalDocumentation {\n");

        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

