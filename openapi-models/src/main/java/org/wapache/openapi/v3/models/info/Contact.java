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

package org.wapache.openapi.v3.models.info;

import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22contactobject%22%3E%3C/a%3Econtact-object" class="md-header-anchor"></a><a name="contactObject"></a><span>Contact Object</span></h4>
 * <p><span>Contact information for the exposed API.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="contactName"></a><span>name</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The identifying name of the contact person/organization.</span></td></tr><tr><td><a name="contactUrl"></a><span>url</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The URL pointing to the contact information. MUST be in the format of a URL.</span></td></tr><tr><td><a name="contactEmail"></a><span>email</span></td><td style='text-align:center;' ><code>string</code></td><td><span>The email address of the contact person/organization. MUST be in the format of an email address.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="contact-object-example:" class="md-header-anchor"></a><span>Contact Object Example:</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;name&quot;: &quot;API Support&quot;,
 *   &quot;url&quot;: &quot;http://www.example.com/support&quot;,
 *   &quot;email&quot;: &quot;support@example.com&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>name: API Support
 * url: http://www.example.com/support
 * email: support@example.com
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#contactObject"
 */

public class Contact {
    private String name = null;
    private String url = null;
    private String email = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the name property from a Contact instance.
     *
     * @return String name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the url property from a Contact instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Contact url(String url) {
        this.url = url;
        return this;
    }

    /**
     * returns the email property from a Contact instance.
     *
     * @return String email
     **/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact email(String email) {
        this.email = email;
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
        Contact contact = (Contact) o;
        return Objects.equals(this.name, contact.name) &&
                Objects.equals(this.url, contact.url) &&
                Objects.equals(this.email, contact.email) &&
                Objects.equals(this.extensions, contact.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, email, extensions);
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

    public Contact extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Contact {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

