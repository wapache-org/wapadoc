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

package org.wapache.openapi.v3.models.callbacks;

import org.wapache.openapi.v3.models.PathItem;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22callbackobject%22%3E%3C/a%3Ecallback-object" class="md-header-anchor"></a><a name="callbackObject"></a><span>Callback Object</span></h4>
 * <p><span>A map of possible out-of band callbacks related to the parent operation.</span>
 * <span>Each value in the map is a </span><a href='#'><span>Path Item Object</span></a><span> that describes a set of requests that may be initiated by the API provider and the expected responses.</span>
 * <span>The key value used to identify the callback object is an expression, evaluated at runtime, that identifies a URL to use for the callback operation.</span></p>
 * <h5><a name="patterned-fields" class="md-header-anchor"></a><span>Patterned Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Pattern</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="callbackExpression"></a><span>{expression}</span></td><td style='text-align:center;' ><a href='#'><span>Path Item Object</span></a></td><td><span>A Path Item Object used to define a callback request and expected responses.  A </span><a href='../examples/v3.0/callback-example.yaml'><span>complete example</span></a><span> is available.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="key-expression" class="md-header-anchor"></a><span>Key Expression</span></h5>
 * <p><span>The key that identifies the </span><a href='#'><span>Path Item Object</span></a><span> is a </span><a href='#'><span>runtime expression</span></a><span> that can be evaluated in the context of a runtime HTTP request/response to identify the URL to be used for the callback request.</span>
 * <span>A simple example might be </span><code>$request.body#/url</code><span>.</span>
 * <span>However, using a </span><a href='#'><span>runtime expression</span></a><span> the complete HTTP message can be accessed.</span>
 * <span>This includes accessing any part of a body that a JSON Pointer </span><a href='https://tools.ietf.org/html/rfc6901'><span>RFC6901</span></a><span> can reference. </span></p>
 * <p><span>For example, given the following HTTP request:</span></p>
 * <pre><code class='language-http' lang='http'>POST /subscribe/myevent?queryUrl=http://clientdomain.com/stillrunning HTTP/1.1
 * Host: example.org
 * Content-Type: application/json
 * Content-Length: 187
 *
 * {
 *   &quot;failedUrl&quot; : &quot;http://clientdomain.com/failed&quot;,
 *   &quot;successUrls&quot; : [
 *     &quot;http://clientdomain.com/fast&quot;,
 *     &quot;http://clientdomain.com/medium&quot;,
 *     &quot;http://clientdomain.com/slow&quot;
 *   ]
 * }
 *
 * 201 Created
 * Location: http://example.org/subscription/1
 * </code></pre>
 * <p><span>The following examples show how the various expressions evaluate, assuming the callback operation has a path parameter named </span><code>eventType</code><span> and a query parameter named </span><code>queryUrl</code><span>.</span></p>
 * <figure><table>
 * <thead>
 * <tr><th><span>Expression</span></th><th style='text-align:left;' ><span>Value</span></th></tr></thead>
 * <tbody><tr><td><span>$url</span></td><td style='text-align:left;' ><a href='http://example.org/subscribe/myevent?queryUrl=http://clientdomain.com/stillrunning' target='_blank' class='url'>http://example.org/subscribe/myevent?queryUrl=http://clientdomain.com/stillrunning</a></td></tr><tr><td><span>$method</span></td><td style='text-align:left;' ><span>POST</span></td></tr><tr><td><span>$request.path.eventType</span></td><td style='text-align:left;' ><span>myevent</span></td></tr><tr><td><span>$request.query.queryUrl</span></td><td style='text-align:left;' ><a href='http://clientdomain.com/stillrunning' target='_blank' class='url'>http://clientdomain.com/stillrunning</a></td></tr><tr><td><span>$request.header.content-Type</span></td><td style='text-align:left;' ><span>application/json</span></td></tr><tr><td><span>$request.body#/failedUrl</span></td><td style='text-align:left;' ><a href='http://clientdomain.com/stillrunning' target='_blank' class='url'>http://clientdomain.com/stillrunning</a></td></tr><tr><td><span>$request.body#/successUrls/2</span></td><td style='text-align:left;' ><a href='http://clientdomain.com/medium' target='_blank' class='url'>http://clientdomain.com/medium</a></td></tr><tr><td><span>$response.header.Location</span></td><td style='text-align:left;' ><a href='http://example.org/subscription/1' target='_blank' class='url'>http://example.org/subscription/1</a></td></tr></tbody>
 * </table></figure>
 * <h5><a name="callback-object-example" class="md-header-anchor"></a><span>Callback Object Example</span></h5>
 * <p><span>The following example shows a callback to the URL specified by the </span><code>id</code><span> and </span><code>email</code><span> property in the request body.</span></p>
 * <pre><code class='language-yaml' lang='yaml'>myWebhook:
 *   &#39;http://notificationServer.com?transactionId={$request.body#/id}&amp;email={$request.body#/email}&#39;:
 *     post:
 *       requestBody:
 *         description: Callback payload
 *         content:
 *           &#39;application/json&#39;:
 *             schema:
 *               $ref: &#39;#/components/schemas/SomePayload&#39;
 *       responses:
 *         &#39;200&#39;:
 *           description: webhook successfully processed and no retries will be performed
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#callbackObject"
 */

public class Callback extends LinkedHashMap<String, PathItem> {
    public Callback() {
    }

    private java.util.Map<String, Object> extensions = null;

    private String $ref = null;

    /**
     * @since 2.0.3
     */
    public String get$ref() {
        return $ref;
    }

    /**
     * @since 2.0.3
     */
    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/callbacks/" + $ref;
        }
        this.$ref = $ref;
    }

    /**
     * @since 2.0.3
     */
    public Callback $ref(String $ref) {
        set$ref($ref);
        return this;
    }

    public Callback addPathItem(String name, PathItem item) {
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
        Callback callback = (Callback) o;
        if ($ref != null ? !$ref.equals(callback.$ref) : callback.$ref != null) {
            return false;
        }
        return Objects.equals(this.extensions, callback.extensions) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extensions, $ref, super.hashCode());
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

    public Callback extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Callback {\n");
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

