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

import org.wapache.openapi.v3.models.examples.Example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22mediatypeobject%22%3E%3C/a%3Emedia-type-object" class="md-header-anchor"></a><a name="mediaTypeObject"></a><span>Media Type Object</span></h4>
 * <p><span>Each Media Type Object provides schema and examples for the media type identified by its key.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="mediaTypeSchema"></a><span>schema</span></td><td style='text-align:center;' ><a href='#'><span>Schema Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a></td><td><span>The schema defining the type used for the request body.</span></td></tr><tr><td><a name="mediaTypeExample"></a><span>example</span></td><td style='text-align:center;' ><span>Any</span></td><td><span>Example of the media type.  The example object SHOULD be in the correct format as specified by the media type.  The </span><code>example</code><span> field is mutually exclusive of the </span><code>examples</code><span> field.  Furthermore, if referencing a </span><code>schema</code><span> which contains an example, the </span><code>example</code><span> value SHALL </span><em><span>override</span></em><span> the example provided by the schema.</span></td></tr><tr><td><a name="mediaTypeExamples"></a><span>examples</span></td><td style='text-align:center;' ><span>Map[ </span><code>string</code><span>, </span><a href='#'><span>Example Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>Examples of the media type.  Each example object SHOULD  match the media type and specified schema if present.  The </span><code>examples</code><span> field is mutually exclusive of the </span><code>example</code><span> field.  Furthermore, if referencing a </span><code>schema</code><span> which contains an example, the </span><code>examples</code><span> value SHALL </span><em><span>override</span></em><span> the example provided by the schema.</span></td></tr><tr><td><a name="mediaTypeEncoding"></a><span>encoding</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Encoding Object</span></a><span>]</span></td><td><span>A map between a property name and its encoding information. The key, being the property name, MUST exist in the schema as a property. The encoding object SHALL only apply to </span><code>requestBody</code><span> objects when the media type is </span><code>multipart</code><span> or </span><code>application/x-www-form-urlencoded</code><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="media-type-examples" class="md-header-anchor"></a><span>Media Type Examples</span></h5>
 * <pre><code class='language-javascript' lang='javascript'>{
 *   &quot;application/json&quot;: {
 *     &quot;schema&quot;: {
 *          &quot;$ref&quot;: &quot;#/components/schemas/Pet&quot;
 *     },
 *     &quot;examples&quot;: {
 *       &quot;cat&quot; : {
 *         &quot;summary&quot;: &quot;An example of a cat&quot;,
 *         &quot;value&quot;:
 *           {
 *             &quot;name&quot;: &quot;Fluffy&quot;,
 *             &quot;petType&quot;: &quot;Cat&quot;,
 *             &quot;color&quot;: &quot;White&quot;,
 *             &quot;gender&quot;: &quot;male&quot;,
 *             &quot;breed&quot;: &quot;Persian&quot;
 *           }
 *       },
 *       &quot;dog&quot;: {
 *         &quot;summary&quot;: &quot;An example of a dog with a cat&#39;s name&quot;,
 *         &quot;value&quot; :  {
 *           &quot;name&quot;: &quot;Puma&quot;,
 *           &quot;petType&quot;: &quot;Dog&quot;,
 *           &quot;color&quot;: &quot;Black&quot;,
 *           &quot;gender&quot;: &quot;Female&quot;,
 *           &quot;breed&quot;: &quot;Mixed&quot;
 *         },
 *       &quot;frog&quot;: {
 *           &quot;$ref&quot;: &quot;#/components/examples/frog-example&quot;
 *         }
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>application/json:
 *   schema:
 *     $ref: &quot;#/components/schemas/Pet&quot;
 *   examples:
 *     cat:
 *       summary: An example of a cat
 *       value:
 *         name: Fluffy
 *         petType: Cat
 *         color: White
 *         gender: male
 *         breed: Persian
 *     dog:
 *       summary: An example of a dog with a cat&#39;s name
 *       value:
 *         name: Puma
 *         petType: Dog
 *         color: Black
 *         gender: Female
 *         breed: Mixed
 *     frog:
 *       $ref: &quot;#/components/examples/frog-example&quot;
 * </code></pre>
 * <h5><a name="considerations-for-file-uploads" class="md-header-anchor"></a><span>Considerations for File Uploads</span></h5>
 * <p><span>In contrast with the 2.0 specification, </span><code>file</code><span> input/output content in OpenAPI is described with the same semantics as any other schema type. Specifically:</span></p>
 * <pre><code class='language-yaml' lang='yaml'># content transferred with base64 encoding
 * schema:
 *   type: string
 *   format: base64
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'># content transferred in binary (octet-stream):
 * schema:
 *   type: string
 *   format: binary
 * </code></pre>
 * <p><span>These examples apply to either input payloads of file uploads or response payloads.</span></p>
 * <p><span>A </span><code>requestBody</code><span> for submitting a file in a </span><code>POST</code><span> operation may look like the following example:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>requestBody:
 *   content:
 *     application/octet-stream:
 *       # any media type is accepted, functionally equivalent to `*\/*`
 *       schema:
 *         # a binary file of any type
 *         type: string
 *         format: binary
 * </code></pre>
 * <p><span>In addition, specific media types MAY be specified:</span></p>
 * <pre><code class='language-yaml' lang='yaml'># multiple, specific media types may be specified:
 * requestBody:
 *   content:
 *       # a binary file of type png or jpeg
 *     &#39;image/jpeg&#39;:
 *       schema:
 *         type: string
 *         format: binary
 *     &#39;image/png&#39;:
 *       schema:
 *         type: string
 *         format: binary
 * </code></pre>
 * <p><span>To upload multiple files, a </span><code>multipart</code><span> media type MUST be used:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>requestBody:
 *   content:
 *     multipart/form-data:
 *       schema:
 *         properties:
 *           # The property name &#39;file&#39; will be used for all files.
 *           file:
 *             type: array
 *             items:
 *               type: string
 *               format: binary
 *
 * </code></pre>
 * <h5><a name="support-for-x-www-form-urlencoded-request-bodies" class="md-header-anchor"></a><span>Support for x-www-form-urlencoded Request Bodies</span></h5>
 * <p><span>To submit content using form url encoding via </span><a href='https://tools.ietf.org/html/rfc1866'><span>RFC1866</span></a><span>, the following</span>
 * <span>definition may be used:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>requestBody:
 *   content:
 *     application/x-www-form-urlencoded:
 *       schema:
 *         type: object
 *         properties:
 *           id:
 *             type: string
 *             format: uuid
 *           address:
 *             # complex types are stringified to support RFC 1866
 *             type: object
 *             properties: {}
 * </code></pre>
 * <p><span>In this example, the contents in the </span><code>requestBody</code><span> MUST be stringified per </span><a href='https://tools.ietf.org/html/rfc1866/'><span>RFC1866</span></a><span> when passed to the server.  In addition, the </span><code>address</code><span> field complex object will be stringified.</span></p>
 * <p><span>When passing complex objects in the </span><code>application/x-www-form-urlencoded</code><span> content type, the default serialization strategy of such properties is described in the </span><a href='#'><code>Encoding Object</code></a><span>&#39;s </span><a href='#'><code>style</code></a><span> property as </span><code>form</code><span>.</span></p>
 * <h5><a name="special-considerations-for-%60multipart%60-content" class="md-header-anchor"></a><span>Special Considerations for </span><code>multipart</code><span> Content</span></h5>
 * <p><span>It is common to use </span><code>multipart/form-data</code><span> as a </span><code>Content-Type</code><span> when transferring request bodies to operations.  In contrast to 2.0, a </span><code>schema</code><span> is REQUIRED to define the input parameters to the operation when using </span><code>multipart</code><span> content.  This supports complex structures as well as supporting mechanisms for multiple file uploads.</span></p>
 * <p><span>When passing in </span><code>multipart</code><span> types, boundaries MAY be used to separate sections of the content being transferred — thus, the following default </span><code>Content-Type</code><span>s are defined for </span><code>multipart</code><span>:</span></p>
 * <ul>
 * <li><span>If the property is a primitive, or an array of primitive values, the default Content-Type is </span><code>text/plain</code></li>
 * <li><span>If the property is complex, or an array of complex values, the default Content-Type is </span><code>application/json</code></li>
 * <li><span>If the property is a </span><code>type: string</code><span> with </span><code>format: binary</code><span> or </span><code>format: base64</code><span> (aka a file object), the default Content-Type is </span><code>application/octet-stream</code></li>
 *
 * </ul>
 * <p><span>Examples:</span></p>
 * <pre><code class='language-yaml' lang='yaml'>requestBody:
 *   content:
 *     multipart/form-data:
 *       schema:
 *         type: object
 *         properties:
 *           id:
 *             type: string
 *             format: uuid
 *           address:
 *             # default Content-Type for objects is `application/json`
 *             type: object
 *             properties: {}
 *           profileImage:
 *             # default Content-Type for string/binary is `application/octet-stream`
 *             type: string
 *             format: binary
 *           children:
 *             # default Content-Type for arrays is based on the `inner` type (text/plain here)
 *             type: array
 *             items:
 *               type: string
 *           addresses:
 *             # default Content-Type for arrays is based on the `inner` type (object shown, so `application/json` in this example)
 *             type: array
 *             items:
 *               type: &#39;#/components/schemas/Address&#39;
 * </code></pre>
 * <p><span>An </span><code>encoding</code><span> attribute is introduced to give you control over the serialization of parts of </span><code>multipart</code><span> request bodies.  This attribute is </span><em><span>only</span></em><span> applicable to </span><code>multipart</code><span> and </span><code>application/x-www-form-urlencoded</code><span> request bodies.</span></p>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#mediaTypeObject"
 */

public class MediaType {
    private Schema schema = null;
    private Map<String, Example> examples = null;
    private Object example = null;
    private Map<String, Encoding> encoding = null;
    private Map<String, Object> extensions = null;

    /**
     * returns the schema property from a MediaType instance.
     *
     * @return Schema schema
     **/

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public MediaType schema(Schema schema) {
        this.schema = schema;
        return this;
    }

    /**
     * returns the examples property from a MediaType instance.
     *
     * @return Map&lt;String, Example&gt; examples
     **/

    public Map<String, Example> getExamples() {
        return examples;
    }

    public void setExamples(Map<String, Example> examples) {
        this.examples = examples;
    }

    public MediaType examples(Map<String, Example> examples) {
        this.examples = examples;
        return this;
    }

    public MediaType addExamples(String key, Example examplesItem) {
        if (this.examples == null) {
            this.examples = new LinkedHashMap<>();
        }
        this.examples.put(key, examplesItem);
        return this;
    }

    /**
     * returns the example property from a MediaType instance.
     *
     * @return String example
     **/

    public Object getExample() {
        return example;
    }

    public void setExample(Object example) {
        this.example = example;
    }

    public MediaType example(Object example) {
        this.example = example;
        return this;
    }

    /**
     * returns the encoding property from a MediaType instance.
     *
     * @return Encoding encoding
     **/

    public Map<String, Encoding> getEncoding() {
        return encoding;
    }

    public void setEncoding(Map<String, Encoding> encoding) {
        this.encoding = encoding;
    }

    public MediaType encoding(Map<String, Encoding> encoding) {
        this.encoding = encoding;
        return this;
    }

    public MediaType addEncoding(String key, Encoding encodingItem) {
        if (this.encoding == null) {
            this.encoding = new LinkedHashMap<>();
        }
        this.encoding.put(key, encodingItem);
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
        MediaType mediaType = (MediaType) o;
        return Objects.equals(this.schema, mediaType.schema) &&
                Objects.equals(this.examples, mediaType.examples) &&
                Objects.equals(this.example, mediaType.example) &&
                Objects.equals(this.encoding, mediaType.encoding) &&
                Objects.equals(this.extensions, mediaType.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schema, examples, example, encoding, extensions);
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

    public MediaType extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MediaType {\n");

        sb.append("    schema: ").append(toIndentedString(schema)).append("\n");
        sb.append("    examples: ").append(toIndentedString(examples)).append("\n");
        sb.append("    example: ").append(toIndentedString(example)).append("\n");
        sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
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

