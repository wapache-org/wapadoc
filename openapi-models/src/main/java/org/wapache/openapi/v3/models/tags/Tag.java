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

package org.wapache.openapi.v3.models.tags;

import org.wapache.openapi.v3.models.ExternalDocumentation;

import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22tagobject%22%3E%3C/a%3Etag-object" class="md-header-anchor"></a><a name="tagObject"></a><span>Tag Object</span></h4>
 * <p><span>Adds metadata to a single tag that is used by the </span><a href='#'><span>Operation Object</span></a><span>.</span>
 * <span>It is not mandatory to have a Tag Object per tag defined in the Operation Object instances.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="tagName"></a><span>name</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The name of the tag.</span></td></tr><tr><td><a name="tagDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A short description for the tag. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="tagExternalDocs"></a><span>externalDocs</span></td><td style='text-align:center;' ><a href='#'><span>External Documentation Object</span></a></td><td><span>Additional external documentation for this tag.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="tag-object-example" class="md-header-anchor"></a><span>Tag Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>{
 * 	&quot;name&quot;: &quot;pet&quot;,
 * 	&quot;description&quot;: &quot;Pets operations&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>name: pet
 * description: Pets operations
 * </code></pre>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#tagObject"
 */

public class Tag {
    private String name = null;
    private String description = null;
    private ExternalDocumentation externalDocs = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the name property from a Tag instance.
     *
     * @return String name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag name(String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the description property from a Tag instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tag description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the externalDocs property from a Tag instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public Tag externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
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
        Tag tag = (Tag) o;
        return Objects.equals(this.name, tag.name) &&
                Objects.equals(this.description, tag.description) &&
                Objects.equals(this.externalDocs, tag.externalDocs) &&
                Objects.equals(this.extensions, tag.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, externalDocs, extensions);
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

    public Tag extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Tag {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
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

