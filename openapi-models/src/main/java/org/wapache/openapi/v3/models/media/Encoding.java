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

import org.wapache.openapi.v3.models.headers.Header;

import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22encodingobject%22%3E%3C/a%3Eencoding-object" class="md-header-anchor"></a><a name="encodingObject"></a><span>Encoding Object</span></h4>
 * <p><span>A single encoding definition applied to a single schema property.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="encodingContentType"></a><span>contentType</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The Content-Type for encoding a specific property. Default value depends on the property type: for </span><code>string</code><span> with </span><code>format</code><span> being </span><code>binary</code><span> – </span><code>application/octet-stream</code><span>; for other primitive types – </span><code>text/plain</code><span>; for </span><code>object</code><span> - </span><code>application/json</code><span>; for </span><code>array</code><span> – the default is defined based on the inner type. The value can be a specific media type (e.g. </span><code>application/json</code><span>), a wildcard media type (e.g. </span><code>image/*</code><span>), or a comma-separated list of the two types.</span></td></tr><tr><td><a name="encodingHeaders"></a><span>headers</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><a href='#'><span>Header Object</span></a><span> </span><span>|</span><span> </span><a href='#'><span>Reference Object</span></a><span>]</span></td><td><span>A map allowing additional information to be provided as headers, for example </span><code>Content-Disposition</code><span>.  </span><code>Content-Type</code><span> is described separately and SHALL be ignored in this section. This property SHALL be ignored if the request body media type is not a </span><code>multipart</code><span>.</span></td></tr><tr><td><a name="encodingStyle"></a><span>style</span></td><td style='text-align:center;' ><code>string</code></td><td><span>Describes how a specific property value will be serialized depending on its type.  See </span><a href='#'><span>Parameter Object</span></a><span> for details on the </span><a href='#'><code>style</code></a><span> property. The behavior follows the same values as </span><code>query</code><span> parameters, including default values. This property SHALL be ignored if the request body media type is not </span><code>application/x-www-form-urlencoded</code><span>.</span></td></tr><tr><td><a name="encodingExplode"></a><span>explode</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>When this is true, property values of type </span><code>array</code><span> or </span><code>object</code><span> generate separate parameters for each value of the array, or key-value-pair of the map.  For other types of properties this property has no effect. When </span><a href='#'><code>style</code></a><span> is </span><code>form</code><span>, the default value is </span><code>true</code><span>. For all other styles, the default value is </span><code>false</code><span>. This property SHALL be ignored if the request body media type is not </span><code>application/x-www-form-urlencoded</code><span>.</span></td></tr><tr><td><a name="encodingAllowReserved"></a><span>allowReserved</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Determines whether the parameter value SHOULD allow reserved characters, as defined by </span><a href='https://tools.ietf.org/html/rfc3986#section-2.2'><span>RFC3986</span></a><span> </span><code>:/?#[]@!$&amp;&#39;()*+,;=</code><span> to be included without percent-encoding. The default value is </span><code>false</code><span>. This property SHALL be ignored if the request body media type is not </span><code>application/x-www-form-urlencoded</code><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="encoding-object-example" class="md-header-anchor"></a><span>Encoding Object Example</span></h5>
 * <pre><code class='language-yaml' lang='yaml'>requestBody:
 *   content:
 *     multipart/mixed:
 *       schema:
 *         type: object
 *         properties:
 *           id:
 *             # default is text/plain
 *             type: string
 *             format: uuid
 *           address:
 *             # default is application/json
 *             type: object
 *             properties: {}
 *           historyMetadata:
 *             # need to declare XML format!
 *             description: metadata in XML format
 *             type: object
 *             properties: {}
 *           profileImage:
 *             # default is application/octet-stream, need to declare an image type only!
 *             type: string
 *             format: binary
 *       encoding:
 *         historyMetadata:
 *           # require XML Content-Type in utf-8 encoding
 *           contentType: application/xml; charset=utf-8
 *         profileImage:
 *           # only accept png/jpeg
 *           contentType: image/png, image/jpeg
 *           headers:
 *             X-Rate-Limit-Limit:
 *               description: The number of allowed requests in the current period
 *               schema:
 *                 type: integer
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#encodingObject"
 */

public class Encoding {
    private String contentType;
    private Map<String, Header> headers;
    private StyleEnum style;
    private Boolean explode;
    private Boolean allowReserved;
    private Map<String, Object> extensions = null;

    public enum StyleEnum {
        FORM("form"),
        SPACE_DELIMITED("spaceDelimited"),
        PIPE_DELIMITED("pipeDelimited"),
        DEEP_OBJECT("deepObject");

        private String value;

        StyleEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StyleEnum fromString(String value) {
            for (StyleEnum e : values()) {
                if (e.value.equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    public Encoding() {
    }

    public Encoding contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Encoding headers(Map<String, Header> headers) {
        this.headers = headers;
        return this;
    }

    public Map<String, Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Header> headers) {
        this.headers = headers;
    }

    public Encoding style(StyleEnum style) {
        this.style = style;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public Encoding explode(Boolean explode) {
        this.explode = explode;
        return this;
    }

    public Boolean getExplode() {
        return explode;
    }

    public void setExplode(Boolean explode) {
        this.explode = explode;
    }

    public Encoding allowReserved(Boolean allowReserved) {
        this.allowReserved = allowReserved;
        return this;
    }

    public Boolean getAllowReserved() {
        return allowReserved;
    }

    public void setAllowReserved(Boolean allowReserved) {
        this.allowReserved = allowReserved;
    }

    public Map<String, Object> getExtensions() {
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

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public Encoding extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
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
        Encoding encoding = (Encoding) o;
        return Objects.equals(this.contentType, encoding.contentType) &&
                Objects.equals(this.headers, encoding.headers) &&
                Objects.equals(this.style, encoding.style) &&
                Objects.equals(this.explode, encoding.explode) &&
                Objects.equals(this.extensions, encoding.extensions) &&
                Objects.equals(this.allowReserved, encoding.allowReserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, headers, style, explode, allowReserved, extensions);
    }

    @Override
    public String toString() {
        return "Encoding{" +
                "contentType='" + contentType + '\'' +
                ", headers=" + headers +
                ", style='" + style + '\'' +
                ", explode=" + explode +
                ", allowReserved=" + allowReserved +
                ", extensions=" + extensions +
                '}';
    }
}
