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
 * <h4><a name="%3Ca-name=%22infoobject%22%3E%3C/a%3Einfo-object" class="md-header-anchor"></a><a name="infoObject"></a><span>Info Object</span></h4>
 * <p><span>The object provides metadata about the API.</span>
 * <span>The metadata MAY be used by the clients if needed, and MAY be presented in editing or documentation generation tools for convenience.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="infoTitle"></a><span>title</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The title of the application.</span></td></tr><tr><td><a name="infoDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A short description of the application. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr><tr><td><a name="infoTermsOfService"></a><span>termsOfService</span></td><td style='text-align:center;' ><code>string</code></td><td><span>A URL to the Terms of Service for the API. MUST be in the format of a URL.</span></td></tr><tr><td><a name="infoContact"></a><span>contact</span></td><td style='text-align:center;' ><a href='#'><span>Contact Object</span></a></td><td><span>The contact information for the exposed API.</span></td></tr><tr><td><a name="infoLicense"></a><span>license</span></td><td style='text-align:center;' ><a href='#'><span>License Object</span></a></td><td><span>The license information for the exposed API.</span></td></tr><tr><td><a name="infoVersion"></a><span>version</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The version of the OpenAPI document (which is distinct from the </span><a href='#'><span>OpenAPI Specification version</span></a><span> or the API implementation version).</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="info-object-example:" class="md-header-anchor"></a><span>Info Object Example:</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;title&quot;: &quot;Sample Pet Store App&quot;,
 *   &quot;description&quot;: &quot;This is a sample server for a pet store.&quot;,
 *   &quot;termsOfService&quot;: &quot;http://example.com/terms/&quot;,
 *   &quot;contact&quot;: {
 *     &quot;name&quot;: &quot;API Support&quot;,
 *     &quot;url&quot;: &quot;http://www.example.com/support&quot;,
 *     &quot;email&quot;: &quot;support@example.com&quot;
 *   },
 *   &quot;license&quot;: {
 *     &quot;name&quot;: &quot;Apache 2.0&quot;,
 *     &quot;url&quot;: &quot;https://www.apache.org/licenses/LICENSE-2.0.html&quot;
 *   },
 *   &quot;version&quot;: &quot;1.0.1&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>title: Sample Pet Store App
 * description: This is a sample server for a pet store.
 * termsOfService: http://example.com/terms/
 * contact:
 *   name: API Support
 *   url: http://www.example.com/support
 *   email: support@example.com
 * license:
 *   name: Apache 2.0
 *   url: https://www.apache.org/licenses/LICENSE-2.0.html
 * version: 1.0.1
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#infoObject"
 */

public class Info {
    private String title = null;
    private String description = null;
    private String termsOfService = null;
    private Contact contact = null;
    private License license = null;
    private String version = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the title property from a Info instance.
     *
     * @return String title
     **/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Info title(String title) {
        this.title = title;
        return this;
    }

    /**
     * returns the description property from a Info instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the termsOfService property from a Info instance.
     *
     * @return String termsOfService
     **/

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public Info termsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
        return this;
    }

    /**
     * returns the contact property from a Info instance.
     *
     * @return Contact contact
     **/

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Info contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    /**
     * returns the license property from a Info instance.
     *
     * @return License license
     **/

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Info license(License license) {
        this.license = license;
        return this;
    }

    /**
     * returns the version property from a Info instance.
     *
     * @return String version
     **/

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Info version(String version) {
        this.version = version;
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
        Info info = (Info) o;
        return Objects.equals(this.title, info.title) &&
                Objects.equals(this.description, info.description) &&
                Objects.equals(this.termsOfService, info.termsOfService) &&
                Objects.equals(this.contact, info.contact) &&
                Objects.equals(this.license, info.license) &&
                Objects.equals(this.version, info.version) &&
                Objects.equals(this.extensions, info.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, termsOfService, contact, license, version, extensions);
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

    public Info extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Info {\n");

        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    termsOfService: ").append(toIndentedString(termsOfService)).append("\n");
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
        sb.append("    license: ").append(toIndentedString(license)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

