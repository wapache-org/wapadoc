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

package org.wapache.openapi.v3.models.media;

import java.util.Objects;

/**
 *

 * <h4><a name="%3Ca-name=%22xmlobject%22%3E%3C/a%3Exml-object" class="md-header-anchor"></a><a name="xmlObject"></a><span>XML Object</span></h4>
 * <p><span>A metadata object that allows for more fine-tuned XML model definitions.</span></p>
 * <p><span>When using arrays, XML element names are </span><em><span>not</span></em><span> inferred (for singular/plural forms) and the </span><code>name</code><span> property SHOULD be used to add that information.</span>
 * <span>See examples for expected behavior.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="xmlName"></a><span>name</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Replaces the name of the element/attribute used for the described schema property. When defined within </span><code>items</code><span>, it will affect the name of the individual XML elements within the list. When defined alongside </span><code>type</code><span> being </span><code>array</code><span> (outside the </span><code>items</code><span>), it will affect the wrapping element and only if </span><code>wrapped</code><span> is </span><code>true</code><span>. If </span><code>wrapped</code><span> is </span><code>false</code><span>, it will be ignored.</span></td></tr><tr><td><a name="xmlNamespace"></a><span>namespace</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The URI of the namespace definition. Value MUST be in the form of an absolute URI.</span></td></tr><tr><td><a name="xmlPrefix"></a><span>prefix</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The prefix to be used for the </span><a href='#'><span>name</span></a><span>.</span></td></tr><tr><td><a name="xmlAttribute"></a><span>attribute</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Declares whether the property definition translates to an attribute instead of an element. Default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="xmlWrapped"></a><span>wrapped</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>MAY be used only for an array definition. Signifies whether the array is wrapped (for example, </span><code>&lt;books&gt;&lt;book/&gt;&lt;book/&gt;&lt;/books&gt;</code><span>) or unwrapped (</span><code>&lt;book/&gt;&lt;book/&gt;</code><span>). Default value is </span><code>false</code><span>. The definition takes effect only when defined alongside </span><code>type</code><span> being </span><code>array</code><span> (outside the </span><code>items</code><span>).</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="xml-object-examples" class="md-header-anchor"></a><span>XML Object Examples</span></h5>
 * <p><span>The examples of the XML object definitions are included inside a property definition of a </span><a href='#'><span>Schema Object</span></a><span> with a sample of the XML representation of it.</span></p>
 * <h6><a name="no-xml-element" class="md-header-anchor"></a><span>No XML Element</span></h6>
 * <p><span>Basic string property:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *     &quot;animals&quot;: {
 *         &quot;type&quot;: &quot;string&quot;
 *     }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: string
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animals&gt;...&lt;/animals&gt;
 * </code></pre>
 * <p><span>Basic string array property (</span><a href='#'><code>wrapped</code></a><span> is </span><code>false</code><span> by default):</span></p>
 * <pre><code class='language-json' lang='json'>{
 *     &quot;animals&quot;: {
 *         &quot;type&quot;: &quot;array&quot;,
 *         &quot;items&quot;: {
 *             &quot;type&quot;: &quot;string&quot;
 *         }
 *     }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animals&gt;...&lt;/animals&gt;
 * &lt;animals&gt;...&lt;/animals&gt;
 * &lt;animals&gt;...&lt;/animals&gt;
 * </code></pre>
 * <h6><a name="xml-name-replacement" class="md-header-anchor"></a><span>XML Name Replacement</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;string&quot;,
 *     &quot;xml&quot;: {
 *       &quot;name&quot;: &quot;animal&quot;
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: string
 *   xml:
 *     name: animal
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animal&gt;...&lt;/animal&gt;
 * </code></pre>
 * <h6><a name="xml-attribute,-prefix-and-namespace" class="md-header-anchor"></a><span>XML Attribute, Prefix and Namespace</span></h6>
 * <p><span>In this example, a full model definition is shown.</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;Person&quot;: {
 *     &quot;type&quot;: &quot;object&quot;,
 *     &quot;properties&quot;: {
 *       &quot;id&quot;: {
 *         &quot;type&quot;: &quot;integer&quot;,
 *         &quot;format&quot;: &quot;int32&quot;,
 *         &quot;xml&quot;: {
 *           &quot;attribute&quot;: true
 *         }
 *       },
 *       &quot;name&quot;: {
 *         &quot;type&quot;: &quot;string&quot;,
 *         &quot;xml&quot;: {
 *           &quot;namespace&quot;: &quot;http://example.com/schema/sample&quot;,
 *           &quot;prefix&quot;: &quot;sample&quot;
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>Person:
 *   type: object
 *   properties:
 *     id:
 *       type: integer
 *       format: int32
 *       xml:
 *         attribute: true
 *     name:
 *       type: string
 *       xml:
 *         namespace: http://example.com/schema/sample
 *         prefix: sample
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;Person id=&quot;123&quot;&gt;
 *     &lt;sample:name xmlns:sample=&quot;http://example.com/schema/sample&quot;&gt;example&lt;/sample:name&gt;
 * &lt;/Person&gt;
 * </code></pre>
 * <h6><a name="xml-arrays" class="md-header-anchor"></a><span>XML Arrays</span></h6>
 * <p><span>Changing the element names:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;,
 *       &quot;xml&quot;: {
 *         &quot;name&quot;: &quot;animal&quot;
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *     xml:
 *       name: animal
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animal&gt;value&lt;/animal&gt;
 * &lt;animal&gt;value&lt;/animal&gt;
 * </code></pre>
 * <p><span>The external </span><code>name</code><span> property has no effect on the XML:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;,
 *       &quot;xml&quot;: {
 *         &quot;name&quot;: &quot;animal&quot;
 *       }
 *     },
 *     &quot;xml&quot;: {
 *       &quot;name&quot;: &quot;aliens&quot;
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *     xml:
 *       name: animal
 *   xml:
 *     name: aliens
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animal&gt;value&lt;/animal&gt;
 * &lt;animal&gt;value&lt;/animal&gt;
 * </code></pre>
 * <p><span>Even when the array is wrapped, if a name is not explicitly defined, the same name will be used both internally and externally:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;
 *     },
 *     &quot;xml&quot;: {
 *       &quot;wrapped&quot;: true
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *   xml:
 *     wrapped: true
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animals&gt;
 *   &lt;animals&gt;value&lt;/animals&gt;
 *   &lt;animals&gt;value&lt;/animals&gt;
 * &lt;/animals&gt;
 * </code></pre>
 * <p><span>To overcome the naming problem in the example above, the following definition can be used:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;,
 *       &quot;xml&quot;: {
 *         &quot;name&quot;: &quot;animal&quot;
 *       }
 *     },
 *     &quot;xml&quot;: {
 *       &quot;wrapped&quot;: true
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *     xml:
 *       name: animal
 *   xml:
 *     wrapped: true
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;animals&gt;
 *   &lt;animal&gt;value&lt;/animal&gt;
 *   &lt;animal&gt;value&lt;/animal&gt;
 * &lt;/animals&gt;
 * </code></pre>
 * <p><span>Affecting both internal and external names:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;,
 *       &quot;xml&quot;: {
 *         &quot;name&quot;: &quot;animal&quot;
 *       }
 *     },
 *     &quot;xml&quot;: {
 *       &quot;name&quot;: &quot;aliens&quot;,
 *       &quot;wrapped&quot;: true
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *     xml:
 *       name: animal
 *   xml:
 *     name: aliens
 *     wrapped: true
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;aliens&gt;
 *   &lt;animal&gt;value&lt;/animal&gt;
 *   &lt;animal&gt;value&lt;/animal&gt;
 * &lt;/aliens&gt;
 * </code></pre>
 * <p><span>If we change the external element but not the internal ones:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;animals&quot;: {
 *     &quot;type&quot;: &quot;array&quot;,
 *     &quot;items&quot;: {
 *       &quot;type&quot;: &quot;string&quot;
 *     },
 *     &quot;xml&quot;: {
 *       &quot;name&quot;: &quot;aliens&quot;,
 *       &quot;wrapped&quot;: true
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>animals:
 *   type: array
 *   items:
 *     type: string
 *   xml:
 *     name: aliens
 *     wrapped: true
 * </code></pre>
 * <pre><code class='language-xml' lang='xml'>&lt;aliens&gt;
 *   &lt;aliens&gt;value&lt;/aliens&gt;
 *   &lt;aliens&gt;value&lt;/aliens&gt;
 * &lt;/aliens&gt;
 * </code></pre>
 * 
 * 
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#xmlObject"
 */

public class XML {
    /**
     * Replaces the name of the element/attribute used for the described schema property.
     * When defined within `items`, it will affect the name of the individual XML elements within the list.
     * When defined alongside `type` being `array` (outside the `items`), it will affect the wrapping element and only if `wrapped` is `true`.
     * If `wrapped` is `false`, it will be ignored.
     */
    private String name = null;
    /**
     * The URI of the namespace definition. Value MUST be in the form of an absolute URI.
     */
    private String namespace = null;
    /**
     * The prefix to be used for the [name](#xmlName).
     */
    private String prefix = null;
    /**
     * Declares whether the property definition translates to an attribute instead of an element. Default value is `false`.
     */
    private Boolean attribute = null;
    /**
     * MAY be used only for an array definition.
     * Signifies whether the array is wrapped (for example, `<books><book/><book/></books>`) or unwrapped (`<book/><book/>`).
     * Default value is `false`.
     * The definition takes effect only when defined alongside `type` being `array` (outside the `items`).
     */
    private Boolean wrapped = null;
    /**
     *
     */
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the name property from a XML instance.
     *
     * @return String name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XML name(String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the namespace property from a XML instance.
     *
     * @return String namespace
     **/

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public XML namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * returns the prefix property from a XML instance.
     *
     * @return String prefix
     **/

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public XML prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * returns the attribute property from a XML instance.
     *
     * @return Boolean attribute
     **/

    public Boolean getAttribute() {
        return attribute;
    }

    public void setAttribute(Boolean attribute) {
        this.attribute = attribute;
    }

    public XML attribute(Boolean attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * returns the wrapped property from a XML instance.
     *
     * @return Boolean wrapped
     **/

    public Boolean getWrapped() {
        return wrapped;
    }

    public void setWrapped(Boolean wrapped) {
        this.wrapped = wrapped;
    }

    public XML wrapped(Boolean wrapped) {
        this.wrapped = wrapped;
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
        XML XML = (XML) o;
        return Objects.equals(this.name, XML.name) &&
                Objects.equals(this.namespace, XML.namespace) &&
                Objects.equals(this.prefix, XML.prefix) &&
                Objects.equals(this.attribute, XML.attribute) &&
                Objects.equals(this.wrapped, XML.wrapped) &&
                Objects.equals(this.extensions, XML.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, namespace, prefix, attribute, wrapped, extensions);
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

    public XML extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class XML {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    namespace: ").append(toIndentedString(namespace)).append("\n");
        sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    wrapped: ").append(toIndentedString(wrapped)).append("\n");
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

