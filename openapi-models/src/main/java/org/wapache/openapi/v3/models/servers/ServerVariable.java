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

package org.wapache.openapi.v3.models.servers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22servervariableobject%22%3E%3C/a%3Eserver-variable-object" class="md-header-anchor"></a><a name="serverVariableObject"></a><span>Server Variable Object</span></h4>
 * <p><span>An object representing a Server Variable for server URL template substitution.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="serverVariableEnum"></a><span>enum</span></td><td style='text-align:center;' ><span>[</span><code>string</code><span>]</span></td><td><span>An enumeration of string values to be used if the substitution options are from a limited set.</span></td></tr><tr><td><a name="serverVariableDefault"></a><span>default</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The default value to use for substitution, and to send, if an alternate value is </span><em><span>not</span></em><span> supplied. Unlike the </span><a href='#'><span>Schema Object&#39;s</span></a><span> </span><code>default</code><span>, this value MUST be provided by the consumer.</span></td></tr><tr><td><a name="serverVariableDescription"></a><span>description</span></td><td style='text-align:center;' ><code>string</code></td><td><span>An optional description for the server variable. </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#serverVariableObject"
 */

public class ServerVariable {
    private List<String> _enum = null;
    private String _default = null;
    private String description = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the _enum property from a ServerVariable instance.
     *
     * @return List&lt;String&gt; _enum
     **/

    public List<String> getEnum() {
        return _enum;
    }

    public void setEnum(List<String> _enum) {
        this._enum = _enum;
    }

    public ServerVariable _enum(List<String> _enum) {
        this._enum = _enum;
        return this;
    }

    public ServerVariable addEnumItem(String _enumItem) {
        if (this._enum == null) {
            this._enum = new ArrayList<>();
        }
        this._enum.add(_enumItem);
        return this;
    }

    /**
     * returns the _default property from a ServerVariable instance.
     *
     * @return String _default
     **/

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public ServerVariable _default(String _default) {
        this._default = _default;
        return this;
    }

    /**
     * returns the description property from a ServerVariable instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServerVariable description(String description) {
        this.description = description;
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
        ServerVariable serverVariable = (ServerVariable) o;
        return Objects.equals(this._enum, serverVariable._enum) &&
                Objects.equals(this._default, serverVariable._default) &&
                Objects.equals(this.description, serverVariable.description) &&
                Objects.equals(this.extensions, serverVariable.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_enum, _default, description, extensions);
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

    public ServerVariable extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServerVariable {\n");

        sb.append("    _enum: ").append(toIndentedString(_enum)).append("\n");
        sb.append("    _default: ").append(toIndentedString(_default)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

