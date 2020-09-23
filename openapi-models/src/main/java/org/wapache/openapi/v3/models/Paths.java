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

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22pathsobject%22%3E%3C/a%3Epaths-object" class="md-header-anchor"></a><a name="pathsObject"></a><span>Paths Object</span></h4>
 * <p><span>Holds the relative paths to the individual endpoints and their operations.</span>
 * <span>The path is appended to the URL from the </span><a href='#'><code>Server Object</code></a><span> in order to construct the full URL.  The Paths MAY be empty, due to </span><a href='#'><span>ACL constraints</span></a><span>.</span></p>
 * <h5><a name="patterned-fields" class="md-header-anchor"></a><span>Patterned Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Pattern</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="pathsPath"></a><span>/{path}</span></td><td style='text-align:center;' ><a href='#'><span>Path Item Object</span></a></td><td><span>A relative path to an individual endpoint. The field name MUST begin with a slash. The path is </span><strong><span>appended</span></strong><span> (no relative URL resolution) to the expanded URL from the </span><a href='#'><code>Server Object</code></a><span>&#39;s </span><code>url</code><span> field in order to construct the full URL. </span><a href='#'><span>Path templating</span></a><span> is allowed. When matching URLs, concrete (non-templated) paths would be matched before their templated counterparts. Templated paths with the same hierarchy but different templated names MUST NOT exist as they are identical. In case of ambiguous matching, it&#39;s up to the tooling to decide which one to use.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="path-templating-matching" class="md-header-anchor"></a><span>Path Templating Matching</span></h5>
 * <p><span>Assuming the following paths, the concrete definition, </span><code>/pets/mine</code><span>, will be matched first if used:</span></p>
 * <pre><code>  /pets/{petId}
 *   /pets/mine
 * </code></pre>
 * <p><span>The following paths are considered identical and invalid:</span></p>
 * <pre><code>  /pets/{petId}
 *   /pets/{name}
 * </code></pre>
 * <p><span>The following may lead to ambiguous resolution:</span></p>
 * <pre><code>  /{entity}/me
 *   /books/{id}
 * </code></pre>
 * <h5><a name="paths-object-example" class="md-header-anchor"></a><span>Paths Object Example</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;/pets&quot;: {
 *     &quot;get&quot;: {
 *       &quot;description&quot;: &quot;Returns all pets from the system that the user has access to&quot;,
 *       &quot;responses&quot;: {
 *         &quot;200&quot;: {
 *           &quot;description&quot;: &quot;A list of pets.&quot;,
 *           &quot;content&quot;: {
 *             &quot;application/json&quot;: {
 *               &quot;schema&quot;: {
 *                 &quot;type&quot;: &quot;array&quot;,
 *                 &quot;items&quot;: {
 *                   &quot;$ref&quot;: &quot;#/components/schemas/pet&quot;
 *                 }
 *               }
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>/pets:
 *   get:
 *     description: Returns all pets from the system that the user has access to
 *     responses:
 *       &#39;200&#39;:
 *         description: A list of pets.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: &#39;#/components/schemas/pet&#39;
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#pathsObject"
 */

public class Paths extends LinkedHashMap<String, PathItem> {
    public Paths() {
    }

    private java.util.Map<String, Object> extensions = null;

    public Paths addPathItem(String name, PathItem item) {
        this.put(name, item);
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
        Paths paths = (Paths) o;
        return Objects.equals(this.extensions, paths.extensions) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extensions, super.hashCode());
    }

    public java.util.Map<String, Object> getExtensions() {
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

    public void setExtensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public Paths extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Paths {\n");
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

