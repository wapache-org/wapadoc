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

package org.wapache.openapi.v3.models.security;

import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22oauthflowsobject%22%3E%3C/a%3Eoauth-flows-object" class="md-header-anchor"></a><a name="oauthFlowsObject"></a><span>OAuth Flows Object</span></h4>
 * <p><span>Allows configuration of the supported OAuth Flows.</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="oauthFlowsImplicit"></a><span>implicit</span></td><td style='text-align:center;' ><a href='#'><span>OAuth Flow Object</span></a></td><td><span>Configuration for the OAuth Implicit flow</span></td></tr><tr><td><a name="oauthFlowsPassword"></a><span>password</span></td><td style='text-align:center;' ><a href='#'><span>OAuth Flow Object</span></a></td><td><span>Configuration for the OAuth Resource Owner Password flow</span></td></tr><tr><td><a name="oauthFlowsClientCredentials"></a><span>clientCredentials</span></td><td style='text-align:center;' ><a href='#'><span>OAuth Flow Object</span></a></td><td><span>Configuration for the OAuth Client Credentials flow.  Previously called </span><code>application</code><span> in OpenAPI 2.0.</span></td></tr><tr><td><a name="oauthFlowsAuthorizationCode"></a><span>authorizationCode</span></td><td style='text-align:center;' ><a href='#'><span>OAuth Flow Object</span></a></td><td><span>Configuration for the OAuth Authorization Code flow.  Previously called </span><code>accessCode</code><span> in OpenAPI 2.0.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h4><a name="%3Ca-name=%22oauthflowobject%22%3E%3C/a%3Eoauth-flow-object" class="md-header-anchor"></a><a name="oauthFlowObject"></a><span>OAuth Flow Object</span></h4>
 * <p><span>Configuration details for a supported OAuth Flow</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Applies To</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="oauthFlowAuthorizationUrl"></a><span>authorizationUrl</span></td><td style='text-align:center;' ><code>string</code></td><td><code>oauth2</code><span> (</span><code>&quot;implicit&quot;</code><span>, </span><code>&quot;authorizationCode&quot;</code><span>)</span></td><td><strong><span>REQUIRED</span></strong><span>. The authorization URL to be used for this flow. This MUST be in the form of a URL.</span></td></tr><tr><td><a name="oauthFlowTokenUrl"></a><span>tokenUrl</span></td><td style='text-align:center;' ><code>string</code></td><td><code>oauth2</code><span> (</span><code>&quot;password&quot;</code><span>, </span><code>&quot;clientCredentials&quot;</code><span>, </span><code>&quot;authorizationCode&quot;</code><span>)</span></td><td><strong><span>REQUIRED</span></strong><span>. The token URL to be used for this flow. This MUST be in the form of a URL.</span></td></tr><tr><td><a name="oauthFlowRefreshUrl"></a><span>refreshUrl</span></td><td style='text-align:center;' ><code>string</code></td><td><code>oauth2</code></td><td><span>The URL to be used for obtaining refresh tokens. This MUST be in the form of a URL.</span></td></tr><tr><td><a name="oauthFlowScopes"></a><span>scopes</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><code>string</code><span>]</span></td><td><code>oauth2</code></td><td><strong><span>REQUIRED</span></strong><span>. The available scopes for the OAuth2 security scheme. A map between the scope name and a short description for it.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h5><a name="oauth-flow-object-examples" class="md-header-anchor"></a><span>OAuth Flow Object Examples</span></h5>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;oauth2&quot;,
 *   &quot;flows&quot;: {
 *     &quot;implicit&quot;: {
 *       &quot;authorizationUrl&quot;: &quot;https://example.com/api/oauth/dialog&quot;,
 *       &quot;scopes&quot;: {
 *         &quot;write:pets&quot;: &quot;modify pets in your account&quot;,
 *         &quot;read:pets&quot;: &quot;read your pets&quot;
 *       }
 *     },
 *     &quot;authorizationCode&quot;: {
 *       &quot;authorizationUrl&quot;: &quot;https://example.com/api/oauth/dialog&quot;,
 *       &quot;tokenUrl&quot;: &quot;https://example.com/api/oauth/token&quot;,
 *       &quot;scopes&quot;: {
 *         &quot;write:pets&quot;: &quot;modify pets in your account&quot;,
 *         &quot;read:pets&quot;: &quot;read your pets&quot;
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: oauth2
 * flows:
 *   implicit:
 *     authorizationUrl: https://example.com/api/oauth/dialog
 *     scopes:
 *       write:pets: modify pets in your account
 *       read:pets: read your pets
 *   authorizationCode:
 *     authorizationUrl: https://example.com/api/oauth/dialog
 *     tokenUrl: https://example.com/api/oauth/token
 *     scopes:
 *       write:pets: modify pets in your account
 *       read:pets: read your pets
 * </code></pre>
 *
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#oauthFlowsObject"
 */

public class OAuthFlow {
    private String authorizationUrl = null;
    private String tokenUrl = null;
    private String refreshUrl = null;
    private Scopes scopes = null;
    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the authorizationUrl property from a OAuthFlow instance.
     *
     * @return String authorizationUrl
     **/

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public OAuthFlow authorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }

    /**
     * returns the tokenUrl property from a OAuthFlow instance.
     *
     * @return String tokenUrl
     **/

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public OAuthFlow tokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }

    /**
     * returns the refreshUrl property from a OAuthFlow instance.
     *
     * @return String refreshUrl
     **/

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public OAuthFlow refreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
        return this;
    }

    /**
     * returns the scopes property from a OAuthFlow instance.
     *
     * @return Scopes scopes
     **/

    public Scopes getScopes() {
        return scopes;
    }

    public void setScopes(Scopes scopes) {
        this.scopes = scopes;
    }

    public OAuthFlow scopes(Scopes scopes) {
        this.scopes = scopes;
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
        OAuthFlow oauthFlow = (OAuthFlow) o;
        return Objects.equals(this.authorizationUrl, oauthFlow.authorizationUrl) &&
                Objects.equals(this.tokenUrl, oauthFlow.tokenUrl) &&
                Objects.equals(this.refreshUrl, oauthFlow.refreshUrl) &&
                Objects.equals(this.scopes, oauthFlow.scopes) &&
                Objects.equals(this.extensions, oauthFlow.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizationUrl, tokenUrl, refreshUrl, scopes, extensions);
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

    public OAuthFlow extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OAuthFlow {\n");

        sb.append("    authorizationUrl: ").append(toIndentedString(authorizationUrl)).append("\n");
        sb.append("    tokenUrl: ").append(toIndentedString(tokenUrl)).append("\n");
        sb.append("    refreshUrl: ").append(toIndentedString(refreshUrl)).append("\n");
        sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
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

