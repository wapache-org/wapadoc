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

package org.wapache.openapi.v3.models.examples;

/**
 * <h4><a name="%3Ca-name=%22exampleobject%22%3E%3C/a%3Eexample-object" class="md-header-anchor"></a><a name="exampleObject"></a><span>Example Object</span></h4>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="exampleSummary"></a><span>summary</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Short description for the example.</span></td></tr><tr><td><a name="exampleDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Long description for the example. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="exampleValue"></a><span>value</span></td><td style='text-align:center;' ><span>Any</span></td><td><span>Embedded literal example. The </span><code>value</code><span> field and </span><code>externalValue</code><span> field are mutually exclusive. To represent examples of media types that cannot naturally represented in JSON or YAML, use a string value to contain the example, escaping where necessary.</span></td></tr><tr><td><a name="exampleExternalValue"></a><span>externalValue</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A URL that points to the literal example. This provides the capability to reference examples that cannot easily be included in JSON or YAML documents.  The </span><code>value</code><span> field and </span><code>externalValue</code><span> field are mutually exclusive.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <p><span>In all cases, the example value is expected to be compatible with the type schema </span>
 * <span>of its associated value.  Tooling implementations MAY choose to </span>
 * <span>validate compatibility automatically, and reject the example value(s) if incompatible.</span></p>
 * <h5><a name="example-object-example" class="md-header-anchor"></a><span>Example Object Example</span></h5>
 * <pre><code class='language-yaml' lang='yaml'># in a model
 * schemas:
 *   properties:
 *     name:
 *       type: string
 *       examples:
 *         name:
 *           $ref: http://example.org/petapi-examples/openapi.json#/components/examples/name-example
 *
 * # in a request body:
 *   requestBody:
 *     content:
 *       &#39;application/json&#39;:
 *         schema:
 *           $ref: &#39;#/components/schemas/Address&#39;
 *         examples:
 *           foo:
 *             summary: A foo example
 *             value: {&quot;foo&quot;: &quot;bar&quot;}
 *           bar:
 *             summary: A bar example
 *             value: {&quot;bar&quot;: &quot;baz&quot;}
 *       &#39;application/xml&#39;:
 *         examples:
 *           xmlExample:
 *             summary: This is an example in XML
 *             externalValue: &#39;http://example.org/examples/address-example.xml&#39;
 *       &#39;text/plain&#39;:
 *         examples:
 *           textExample:
 *             summary: This is a text example
 *             externalValue: &#39;http://foo.bar/examples/address-example.txt&#39;
 *
 *
 * # in a parameter
 *   parameters:
 *     - name: &#39;zipCode&#39;
 *       in: &#39;query&#39;
 *       schema:
 *         type: &#39;string&#39;
 *         format: &#39;zip-code&#39;
 *         examples:
 *           zip-example:
 *             $ref: &#39;#/components/examples/zip-example&#39;
 *
 * # in a response
 *   responses:
 *     &#39;200&#39;:
 *       description: your car appointment has been booked
 *       content:
 *         application/json:
 *           schema:
 *             $ref: &#39;#/components/schemas/SuccessResponse&#39;
 *           examples:
 *             confirmation-success:
 *               $ref: &#39;#/components/examples/confirmation-success&#39;
 * </code></pre>
 */

public class Example {
    private String summary = null;
    private String description = null;
    private Object value = null;
    private String externalValue = null;
    private String $ref = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the summary property from a Example instance.
     *
     * @return String summary
     **/

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Example summary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * returns the description property from a Example instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Example description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the value property from a Example instance.
     *
     * @return Object value
     **/

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Example value(Object value) {
        this.value = value;
        return this;
    }

    /**
     * returns the externalValue property from a Example instance.
     *
     * @return String externalValue
     **/

    public String getExternalValue() {
        return externalValue;
    }

    public void setExternalValue(String externalValue) {
        this.externalValue = externalValue;
    }

    public Example externalValue(String externalValue) {
        this.externalValue = externalValue;
        return this;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/examples/" + $ref;
        }
        this.$ref = $ref;
    }

    public Example $ref(String $ref) {
        set$ref($ref);
        return this;
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

    public Example extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Example)) {
            return false;
        }

        Example example = (Example) o;

        if (summary != null ? !summary.equals(example.summary) : example.summary != null) {
            return false;
        }
        if (description != null ? !description.equals(example.description) : example.description != null) {
            return false;
        }
        if (value != null ? !value.equals(example.value) : example.value != null) {
            return false;
        }
        if (externalValue != null ? !externalValue.equals(example.externalValue) : example.externalValue != null) {
            return false;
        }
        if ($ref != null ? !$ref.equals(example.$ref) : example.$ref != null) {
            return false;
        }
        return extensions != null ? extensions.equals(example.extensions) : example.extensions == null;

    }

    @Override
    public int hashCode() {
        int result = summary != null ? summary.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (externalValue != null ? externalValue.hashCode() : 0);
        result = 31 * result + ($ref != null ? $ref.hashCode() : 0);
        result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Example {\n");

        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    externalValue: ").append(toIndentedString(externalValue)).append("\n");
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

