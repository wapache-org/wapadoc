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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.wapache.openapi.v3.models.ExternalDocumentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <h4><a name="%3Ca-name=%22schemaobject%22%3E%3C/a%3Eschema-object" class="md-header-anchor"></a><a name="schemaObject"></a><span>Schema Object</span></h4>
 * <p><span>The Schema Object allows the definition of input and output data types.</span>
 * <span>These types can be objects, but also primitives and arrays.</span>
 * <span>This object is an extended subset of the </span><a href='http://json-schema.org/'><span>JSON Schema Specification Wright Draft 00</span></a><span>.</span></p>
 * <p><span>For more information about the properties, see </span><a href='https://tools.ietf.org/html/draft-wright-json-schema-00'><span>JSON Schema Core</span></a><span> and </span><a href='https://tools.ietf.org/html/draft-wright-json-schema-validation-00'><span>JSON Schema Validation</span></a><span>.</span>
 * <span>Unless stated otherwise, the property definitions follow the JSON Schema.</span></p>
 * <h5><a name="properties" class="md-header-anchor"></a><span>Properties </span></h5>
 * <p><span>The following properties are taken directly from the JSON Schema definition and follow the same specifications:</span></p>
 * <ul>
 * <li><span>title</span></li>
 * <li><span>multipleOf</span></li>
 * <li><span>maximum</span></li>
 * <li><span>exclusiveMaximum</span></li>
 * <li><span>minimum</span></li>
 * <li><span>exclusiveMinimum</span></li>
 * <li><span>maxLength</span></li>
 * <li><span>minLength</span></li>
 * <li><span>pattern (This string SHOULD be a valid regular expression, according to the </span><a href='https://www.ecma-international.org/ecma-262/5.1/#sec-7.8.5'><span>ECMA 262 regular expression</span></a><span> dialect)</span></li>
 * <li><span>maxItems</span></li>
 * <li><span>minItems</span></li>
 * <li><span>uniqueItems</span></li>
 * <li><span>maxProperties</span></li>
 * <li><span>minProperties</span></li>
 * <li><span>required</span></li>
 * <li><span>enum</span></li>
 *
 * </ul>
 * <p><span>The following properties are taken from the JSON Schema definition but their definitions were adjusted to the OpenAPI Specification. </span></p>
 * <ul>
 * <li><span>type - Value MUST be a string. Multiple types via an array are not supported.</span></li>
 * <li><span>allOf - Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema.</span></li>
 * <li><span>oneOf - Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema.</span></li>
 * <li><span>anyOf - Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema.</span></li>
 * <li><span>not - Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema.</span></li>
 * <li><span>items - Value MUST be an object and not an array. Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema. </span><code>items</code><span> MUST be present if the </span><code>type</code><span> is </span><code>array</code><span>.</span></li>
 * <li><span>properties - Property definitions MUST be a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema (inline or referenced).</span></li>
 * <li><span>additionalProperties - Value can be boolean or object. Inline or referenced schema MUST be of a </span><a href='#'><span>Schema Object</span></a><span> and not a standard JSON Schema.</span></li>
 * <li><span>description - </span><a href='http://spec.commonmark.org/'><span>CommonMark syntax</span></a><span> MAY be used for rich text representation.</span></li>
 * <li><span>format - See </span><a href='#'><span>Data Type Formats</span></a><span> for further details. While relying on JSON Schema&#39;s defined formats, the OAS offers a few additional predefined formats.</span></li>
 * <li><span>default - The default value represents what would be assumed by the consumer of the input as the value of the schema if one is not provided. Unlike JSON Schema, the value MUST conform to the defined type for the Schema Object defined at the same level. For example, if </span><code>type</code><span> is </span><code>string</code><span>, then </span><code>default</code><span> can be </span><code>&quot;foo&quot;</code><span> but cannot be </span><code>1</code><span>.</span></li>
 *
 * </ul>
 * <p><span>Alternatively, any time a Schema Object can be used, a </span><a href='#'><span>Reference Object</span></a><span> can be used in its place. This allows referencing definitions instead of defining them inline.</span></p>
 * <p><span>Additional properties defined by the JSON Schema specification that are not mentioned here are strictly unsupported.</span></p>
 * <p><span>Other than the JSON Schema subset fields, the following fields MAY be used for further schema documentation:</span></p>
 * <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 * <figure><table>
 * <thead>
 * <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 * <tbody><tr><td><a name="schemaNullable"></a><span>nullable</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Allows sending a </span><code>null</code><span> value for the defined schema. Default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="schemaDiscriminator"></a><span>discriminator</span></td><td style='text-align:center;' ><a href='#'><span>Discriminator Object</span></a></td><td><span>Adds support for polymorphism. The discriminator is an object name that is used to differentiate between other schemas which may satisfy the payload description. See </span><a href='#'><span>Composition and Inheritance</span></a><span> for more details.</span></td></tr><tr><td><a name="schemaReadOnly"></a><span>readOnly</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Relevant only for Schema </span><code>&quot;properties&quot;</code><span> definitions. Declares the property as &quot;read only&quot;. This means that it MAY be sent as part of a response but SHOULD NOT be sent as part of the request. If the property is marked as </span><code>readOnly</code><span> being </span><code>true</code><span> and is in the </span><code>required</code><span> list, the </span><code>required</code><span> will take effect on the response only. A property MUST NOT be marked as both </span><code>readOnly</code><span> and </span><code>writeOnly</code><span> being </span><code>true</code><span>. Default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="schemaWriteOnly"></a><span>writeOnly</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Relevant only for Schema </span><code>&quot;properties&quot;</code><span> definitions. Declares the property as &quot;write only&quot;. Therefore, it MAY be sent as part of a request but SHOULD NOT be sent as part of the response. If the property is marked as </span><code>writeOnly</code><span> being </span><code>true</code><span> and is in the </span><code>required</code><span> list, the </span><code>required</code><span> will take effect on the request only. A property MUST NOT be marked as both </span><code>readOnly</code><span> and </span><code>writeOnly</code><span> being </span><code>true</code><span>. Default value is </span><code>false</code><span>.</span></td></tr><tr><td><a name="schemaXml"></a><span>xml</span></td><td style='text-align:center;' ><a href='#'><span>XML Object</span></a></td><td><span>This MAY be used only on properties schemas. It has no effect on root schemas. Adds additional metadata to describe the XML representation of this property.</span></td></tr><tr><td><a name="schemaExternalDocs"></a><span>externalDocs</span></td><td style='text-align:center;' ><a href='#'><span>External Documentation Object</span></a></td><td><span>Additional external documentation for this schema.</span></td></tr><tr><td><a name="schemaExample"></a><span>example</span></td><td style='text-align:center;' ><span>Any</span></td><td><span>A free-form property to include an example of an instance for this schema. To represent examples that cannot be naturally represented in JSON or YAML, a string value can be used to contain the example with escaping where necessary.</span></td></tr><tr><td><a name="schemaDeprecated"></a><span> deprecated</span></td><td style='text-align:center;' ><code>boolean</code></td><td><span>Specifies that a schema is deprecated and SHOULD be transitioned out of usage. Default value is </span><code>false</code><span>.</span></td></tr></tbody>
 * </table></figure>
 * <p><span>This object MAY be extended with </span><a href='#'><span>Specification Extensions</span></a><span>.</span></p>
 * <h6><a name="%3Ca-name=%22schemacomposition%22%3E%3C/a%3Ecomposition-and-inheritance-(polymorphism)" class="md-header-anchor"></a><a name="schemaComposition"></a><span>Composition and Inheritance (Polymorphism)</span></h6>
 * <p><span>The OpenAPI Specification allows combining and extending model definitions using the </span><code>allOf</code><span> property of JSON Schema, in effect offering model composition.</span>
 * <code>allOf</code><span> takes an array of object definitions that are validated </span><em><span>independently</span></em><span> but together compose a single object. </span></p>
 * <p><span>While composition offers model extensibility, it does not imply a hierarchy between the models.</span>
 * <span>To support polymorphism, the OpenAPI Specification adds the </span><code>discriminator</code><span> field.</span>
 * <span>When used, the </span><code>discriminator</code><span> will be the name of the property that decides which schema definition validates the structure of the model.</span>
 * <span>As such, the </span><code>discriminator</code><span> field MUST be a required field.</span>
 * <span>There are two ways to define the value of a discriminator for an inheriting instance.</span></p>
 * <ul>
 * <li><span>Use the schema name.</span></li>
 * <li><span>Override the schema name by overriding the property with a new value. If a new value exists, this takes precedence over the schema name.</span>
 * <span>As such, inline schema definitions, which do not have a given id, </span><em><span>cannot</span></em><span> be used in polymorphism.</span></li>
 *
 * </ul>
 * <h6><a name="xml-modeling" class="md-header-anchor"></a><span>XML Modeling</span></h6>
 * <p><span>The </span><a href='#'><span>xml</span></a><span> property allows extra definitions when translating the JSON definition to XML.</span>
 * <span>The </span><a href='#'><span>XML Object</span></a><span> contains additional information about the available options.</span></p>
 * <h5><a name="schema-object-examples" class="md-header-anchor"></a><span>Schema Object Examples</span></h5>
 * <h6><a name="primitive-sample" class="md-header-anchor"></a><span>Primitive Sample</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;string&quot;,
 *   &quot;format&quot;: &quot;email&quot;
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: string
 * format: email
 * </code></pre>
 * <h6><a name="simple-model" class="md-header-anchor"></a><span>Simple Model</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;object&quot;,
 *   &quot;required&quot;: [
 *     &quot;name&quot;
 *   ],
 *   &quot;properties&quot;: {
 *     &quot;name&quot;: {
 *       &quot;type&quot;: &quot;string&quot;
 *     },
 *     &quot;address&quot;: {
 *       &quot;$ref&quot;: &quot;#/components/schemas/Address&quot;
 *     },
 *     &quot;age&quot;: {
 *       &quot;type&quot;: &quot;integer&quot;,
 *       &quot;format&quot;: &quot;int32&quot;,
 *       &quot;minimum&quot;: 0
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: object
 * required:
 * - name
 * properties:
 *   name:
 *     type: string
 *   address:
 *     $ref: &#39;#/components/schemas/Address&#39;
 *   age:
 *     type: integer
 *     format: int32
 *     minimum: 0
 * </code></pre>
 * <h6><a name="model-with-map/dictionary-properties" class="md-header-anchor"></a><span>Model with Map/Dictionary Properties</span></h6>
 * <p><span>For a simple string to string mapping:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;object&quot;,
 *   &quot;additionalProperties&quot;: {
 *     &quot;type&quot;: &quot;string&quot;
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: object
 * additionalProperties:
 *   type: string
 * </code></pre>
 * <p><span>For a string to model mapping:</span></p>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;object&quot;,
 *   &quot;additionalProperties&quot;: {
 *     &quot;$ref&quot;: &quot;#/components/schemas/ComplexModel&quot;
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: object
 * additionalProperties:
 *   $ref: &#39;#/components/schemas/ComplexModel&#39;
 * </code></pre>
 * <h6><a name="model-with-example" class="md-header-anchor"></a><span>Model with Example</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;type&quot;: &quot;object&quot;,
 *   &quot;properties&quot;: {
 *     &quot;id&quot;: {
 *       &quot;type&quot;: &quot;integer&quot;,
 *       &quot;format&quot;: &quot;int64&quot;
 *     },
 *     &quot;name&quot;: {
 *       &quot;type&quot;: &quot;string&quot;
 *     }
 *   },
 *   &quot;required&quot;: [
 *     &quot;name&quot;
 *   ],
 *   &quot;example&quot;: {
 *     &quot;name&quot;: &quot;Puma&quot;,
 *     &quot;id&quot;: 1
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>type: object
 * properties:
 *   id:
 *     type: integer
 *     format: int64
 *   name:
 *     type: string
 * required:
 * - name
 * example:
 *   name: Puma
 *   id: 1
 * </code></pre>
 * <h6><a name="models-with-composition" class="md-header-anchor"></a><span>Models with Composition</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;components&quot;: {
 *     &quot;schemas&quot;: {
 *       &quot;ErrorModel&quot;: {
 *         &quot;type&quot;: &quot;object&quot;,
 *         &quot;required&quot;: [
 *           &quot;message&quot;,
 *           &quot;code&quot;
 *         ],
 *         &quot;properties&quot;: {
 *           &quot;message&quot;: {
 *             &quot;type&quot;: &quot;string&quot;
 *           },
 *           &quot;code&quot;: {
 *             &quot;type&quot;: &quot;integer&quot;,
 *             &quot;minimum&quot;: 100,
 *             &quot;maximum&quot;: 600
 *           }
 *         }
 *       },
 *       &quot;ExtendedErrorModel&quot;: {
 *         &quot;allOf&quot;: [
 *           {
 *             &quot;$ref&quot;: &quot;#/components/schemas/ErrorModel&quot;
 *           },
 *           {
 *             &quot;type&quot;: &quot;object&quot;,
 *             &quot;required&quot;: [
 *               &quot;rootCause&quot;
 *             ],
 *             &quot;properties&quot;: {
 *               &quot;rootCause&quot;: {
 *                 &quot;type&quot;: &quot;string&quot;
 *               }
 *             }
 *           }
 *         ]
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>components:
 *   schemas:
 *     ErrorModel:
 *       type: object
 *       required:
 *       - message
 *       - code
 *       properties:
 *         message:
 *           type: string
 *         code:
 *           type: integer
 *           minimum: 100
 *           maximum: 600
 *     ExtendedErrorModel:
 *       allOf:
 *       - $ref: &#39;#/components/schemas/ErrorModel&#39;
 *       - type: object
 *         required:
 *         - rootCause
 *         properties:
 *           rootCause:
 *             type: string
 * </code></pre>
 * <h6><a name="models-with-polymorphism-support" class="md-header-anchor"></a><span>Models with Polymorphism Support</span></h6>
 * <pre><code class='language-json' lang='json'>{
 *   &quot;components&quot;: {
 *     &quot;schemas&quot;: {
 *       &quot;Pet&quot;: {
 *         &quot;type&quot;: &quot;object&quot;,
 *         &quot;discriminator&quot;: {
 *           &quot;propertyName&quot;: &quot;petType&quot;
 *         },
 *         &quot;properties&quot;: {
 *           &quot;name&quot;: {
 *             &quot;type&quot;: &quot;string&quot;
 *           },
 *           &quot;petType&quot;: {
 *             &quot;type&quot;: &quot;string&quot;
 *           }
 *         },
 *         &quot;required&quot;: [
 *           &quot;name&quot;,
 *           &quot;petType&quot;
 *         ]
 *       },
 *       &quot;Cat&quot;: {
 *         &quot;description&quot;: &quot;A representation of a cat. Note that `Cat` will be used as the discriminator value.&quot;,
 *         &quot;allOf&quot;: [
 *           {
 *             &quot;$ref&quot;: &quot;#/components/schemas/Pet&quot;
 *           },
 *           {
 *             &quot;type&quot;: &quot;object&quot;,
 *             &quot;properties&quot;: {
 *               &quot;huntingSkill&quot;: {
 *                 &quot;type&quot;: &quot;string&quot;,
 *                 &quot;description&quot;: &quot;The measured skill for hunting&quot;,
 *                 &quot;default&quot;: &quot;lazy&quot;,
 *                 &quot;enum&quot;: [
 *                   &quot;clueless&quot;,
 *                   &quot;lazy&quot;,
 *                   &quot;adventurous&quot;,
 *                   &quot;aggressive&quot;
 *                 ]
 *               }
 *             },
 *             &quot;required&quot;: [
 *               &quot;huntingSkill&quot;
 *             ]
 *           }
 *         ]
 *       },
 *       &quot;Dog&quot;: {
 *         &quot;description&quot;: &quot;A representation of a dog. Note that `Dog` will be used as the discriminator value.&quot;,
 *         &quot;allOf&quot;: [
 *           {
 *             &quot;$ref&quot;: &quot;#/components/schemas/Pet&quot;
 *           },
 *           {
 *             &quot;type&quot;: &quot;object&quot;,
 *             &quot;properties&quot;: {
 *               &quot;packSize&quot;: {
 *                 &quot;type&quot;: &quot;integer&quot;,
 *                 &quot;format&quot;: &quot;int32&quot;,
 *                 &quot;description&quot;: &quot;the size of the pack the dog is from&quot;,
 *                 &quot;default&quot;: 0,
 *                 &quot;minimum&quot;: 0
 *               }
 *             },
 *             &quot;required&quot;: [
 *               &quot;packSize&quot;
 *             ]
 *           }
 *         ]
 *       }
 *     }
 *   }
 * }
 * </code></pre>
 * <pre><code class='language-yaml' lang='yaml'>components:
 *   schemas:
 *     Pet:
 *       type: object
 *       discriminator:
 *         propertyName: petType
 *       properties:
 *         name:
 *           type: string
 *         petType:
 *           type: string
 *       required:
 *       - name
 *       - petType
 *     Cat:  ## &quot;Cat&quot; will be used as the discriminator value
 *       description: A representation of a cat
 *       allOf:
 *       - $ref: &#39;#/components/schemas/Pet&#39;
 *       - type: object
 *         properties:
 *           huntingSkill:
 *             type: string
 *             description: The measured skill for hunting
 *             enum:
 *             - clueless
 *             - lazy
 *             - adventurous
 *             - aggressive
 *         required:
 *         - huntingSkill
 *     Dog:  ## &quot;Dog&quot; will be used as the discriminator value
 *       description: A representation of a dog
 *       allOf:
 *       - $ref: &#39;#/components/schemas/Pet&#39;
 *       - type: object
 *         properties:
 *           packSize:
 *             type: integer
 *             format: int32
 *             description: the size of the pack the dog is from
 *             default: 0
 *             minimum: 0
 *         required:
 *         - packSize
 * </code></pre>
 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#schemaObject"
 */

public class Schema<T> {

    private String name;

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // The following properties are taken directly from the JSON Schema definition and follow the same specifications:

    /**
     * 6.1.  "title" and "description"
     *
     *    The value of both of these keywords MUST be a string.
     *
     *    Both of these keywords can be used to decorate a user interface with information about the data produced by this user interface.
     *    A title will preferrably be short, whereas a description will provide explanation about the purpose of the instance described by this schema.
     *
     * 这两个关键字的值必须是一个字符串。
     *
     * 这两个关键字都可以用来装饰用户界面，并带有以下内容: 关于该用户界面产生的数据的信息。
     * 标题最好简短，而说明将通过这个模式提供本实例目的的解释。
     *
     * @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-12"
     */
    private String title = null;
    /**
     *
     * 5.1.  multipleOf
     *
     *    The value of "multipleOf" MUST be a number, strictly greater than 0.
     *
     *    "multipleOf "的值必须是一个数字，严格大于0。
     *
     *    A numeric instance is only valid if division by this keyword's value results in an integer.
     *
     *    只有当除以这个关键字的值的结果是一个整数时，数字实例才有效。
     *
     * @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1"
     */
    private BigDecimal multipleOf = null;
    /**
     *  5.2.  maximum
     *
     *    The value of "maximum" MUST be a number, representing an upper limit
     *    for a numeric instance.
     *
     *   "maximum"的值必须是一个数字，代表数值的上限。
     *
     *    If the instance is a number, then this keyword validates if "exclusiveMaximum" is true
     *    and instance is less than the provided value,
     *    or else if the instance is less than or exactly equal to the provided value.
     *
     *    如果实例是一个数字，那么这个关键字就会验证是否有"exclusiveMaximum "为真，且实例小于提供的值，否则如果实例小于或等于提供了价值。
     *
     * @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2"
     */
    private BigDecimal maximum = null;
    /**
     *
     5.3.  exclusiveMaximum

     The value of "exclusiveMaximum" MUST be a boolean, representing whether the limit in "maximum" is exclusive or not.
     An undefined value is the same as false.

     "exclusiveMaximum"的值必须是一个布尔值，代表的是最大 "中的限制是否是排他性的。 未定义的值与false相同。

     If "exclusiveMaximum" is true, then a numeric instance SHOULD NOT be equal to the value specified in "maximum".
     If "exclusiveMaximum" is false (or not specified), then a numeric instance MAY be equal to the value of "maximum".

     如果 "exclusiveMaximum "为真，那么一个数值实例就不应该是等于 "maximum "中指定的值。
     如果 "exclusiveMaximum "是 false (或未指定)，那么一个数字实例可能等于"最大 "值。

     简单点说, 就是包含最大值还是不包含最大值, 开区间和闭区间的区别

     @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.3"
     */
    private Boolean exclusiveMaximum = null;

    /**
     * 5.4.  minimum
     *
     *    The value of "minimum" MUST be a number, representing a lower limit
     *    for a numeric instance.
     *
     *    If the instance is a number, then this keyword validates if
     *    "exclusiveMinimum" is true and instance is greater than the provided
     *    value, or else if the instance is greater than or exactly equal to
     *    the provided value.
     *
     * 最小值 "必须是一个数字，代表数值的下限。
     *
     * 如果实例是一个数字，那么这个关键字就会验证是否有"exclusiveMinimum "为真，且实例大于提供的值，
     * 否则，如果实例大于或完全等于提供的价值。
     *
     * @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.4"
     */
    private BigDecimal minimum = null;
    /**
     * 5.5.  exclusiveMinimum
     *
     *    The value of "exclusiveMinimum" MUST be a boolean, representing
     *    whether the limit in "minimum" is exclusive or not.  An undefined
     *    value is the same as false.
     *
     *    If "exclusiveMinimum" is true, then a numeric instance SHOULD NOT be
     *    equal to the value specified in "minimum".  If "exclusiveMinimum" is
     *    false (or not specified), then a numeric instance MAY be equal to the
     *    value of "minimum".
     *
     *
     * @see "https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.5"
     */
    private Boolean exclusiveMinimum = null;

    /**
     * 5.6.  maxLength
     *
     *    The value of this keyword MUST be a non-negative integer.
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    A string instance is valid against this keyword if its length is less
     *    than, or equal to, the value of this keyword.
     *
     *    The length of a string instance is defined as the number of its
     *    characters as defined by RFC 7159 [RFC7159].
     */
    private Integer maxLength = null;
    /**
     * 5.7.  minLength
     *
     *    A string instance is valid against this keyword if its length is
     *    greater than, or equal to, the value of this keyword.
     *
     *    The length of a string instance is defined as the number of its
     *    characters as defined by RFC 7159 [RFC7159].
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    "minLength", if absent, may be considered as being present with
     *    integer value 0.
     */
    private Integer minLength = null;
    // (This string SHOULD be a valid regular expression, according to the [Ecma-262 Edition 5.1 regular expression](https://www.ecma-international.org/ecma-262/5.1/#sec-15.10.1) dialect)
    /**
     * 5.8.  pattern
     *
     *    The value of this keyword MUST be a string.  This string SHOULD be a
     *    valid regular expression, according to the ECMA 262 regular
     *    expression dialect.
     *
     *    A string instance is considered valid if the regular expression
     *    matches the instance successfully.  Recall: regular expressions are
     *    not implicitly anchored.
     */
    private String pattern = null;

    /**
     * 5.10.  maxItems
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    An array instance is valid against "maxItems" if its size is less
     *    than, or equal to, the value of this keyword.
     */
    private Integer maxItems = null;
    /**
     * 5.11.  minItems
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    An array instance is valid against "minItems" if its size is greater
     *    than, or equal to, the value of this keyword.
     *
     *    If this keyword is not present, it may be considered present with a
     *    value of 0.
     */
    private Integer minItems = null;
    /**
     * 5.12.  uniqueItems
     *
     *    The value of this keyword MUST be a boolean.
     *
     *    If this keyword has boolean value false, the instance validates
     *    successfully.  If it has boolean value true, the instance validates
     *    successfully if all of its elements are unique.
     *
     *    If not present, this keyword may be considered present with boolean
     *    value false.
     */
    private Boolean uniqueItems = null;
    /**
     * 5.13.  maxProperties
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    An object instance is valid against "maxProperties" if its number of
     *    properties is less than, or equal to, the value of this keyword.
     */
    private Integer maxProperties = null;
    /**
     * 5.14.  minProperties
     *
     *    The value of this keyword MUST be an integer.  This integer MUST be
     *    greater than, or equal to, 0.
     *
     *    An object instance is valid against "minProperties" if its number of
     *    properties is greater than, or equal to, the value of this keyword.
     *
     *    If this keyword is not present, it may be considered present with a
     *    value of 0.
     */
    private Integer minProperties = null;
    /**
     * 5.15.  required
     *
     *    The value of this keyword MUST be an array.  This array MUST have at
     *    least one element.  Elements of this array MUST be strings, and MUST
     *    be unique.
     *
     *    An object instance is valid against this keyword if its property set
     *    contains all elements in this keyword's array value.
     */
    private List<String> required = null;
    /**
     * 5.20.  enum
     *
     *    The value of this keyword MUST be an array.  This array SHOULD have
     *    at least one element.  Elements in the array SHOULD be unique.
     *
     *    Elements in the array MAY be of any type, including null.
     *
     *    An instance validates successfully against this keyword if its value
     *    is equal to one of the elements in this keyword's array value.
     */
    protected List<T> _enum = null;

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // The following properties are taken from the JSON Schema definition but their definitions were adjusted to the OpenAPI Specification.
    /*

        Additional properties defined by the JSON Schema specification
        that are not mentioned here are strictly unsupported.

     */

    /**
     * - default -
     * The default value represents what would be assumed by the consumer of the input as the value of the schema if one is not provided.
     * Unlike JSON Schema, the value MUST conform to the defined type for the Schema Object defined at the same level.
     * For example, if `type` is `string`, then `default` can be `"foo"` but cannot be `1`.
     */
    protected T _default;

    /**
     * - type - Value MUST be a string. Multiple types via an array are not supported.
     */
    private String type = null;

    /**
     * - description - [CommonMark syntax](https://spec.commonmark.org/) MAY be used for rich text representation.
     */
    private String description = null;

    /**
     * - not - Inline or referenced schema MUST be of a [Schema Object](#schemaObject) and not a standard JSON Schema.
     */
    private Schema not = null;
    /**
     *- properties - Property definitions MUST be a [Schema Object](#schemaObject) and not a standard JSON Schema (inline or referenced).
     */
    private Map<String, Schema> properties = null;
    /**
     *- additionalProperties - Value can be boolean or object. Inline or referenced schema MUST be of a [Schema Object](#schemaObject) and not a standard JSON Schema.
     * Consistent with JSON Schema, `additionalProperties` defaults to `true`.
     */
    private Object additionalProperties = null;
    /**
     * - format - See [Data Type Formats](#dataTypeFormat) for further details.
     * While relying on JSON Schema's defined formats, the OAS offers a few additional predefined formats.
     */
    private String format = null;

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // Other than the JSON Schema subset fields, the following fields MAY be used for further schema documentation:

//##### Fixed Fields
//    Field Name | Type | Description
//---|:---:|---
//<a name="schemaNullable"></a>nullable | `boolean` |
//<a name="schemaDiscriminator"></a>discriminator | [Discriminator Object](#discriminatorObject) |
//<a name="schemaReadOnly"></a>readOnly | `boolean` |
//<a name="schemaWriteOnly"></a>writeOnly | `boolean` |
//<a name="schemaXml"></a>xml | [XML Object](#xmlObject) |
//<a name="schemaExternalDocs"></a>externalDocs | [External Documentation Object](#externalDocumentationObject) |
//<a name="schemaExample"></a>example | Any |
//<a name="schemaDeprecated"></a> deprecated | `boolean` |
//
//    This object MAY be extended with [Specification Extensions](#specificationExtensions).

    // 以下是OAS3特有的

    /**
     *
     Alternatively, any time a Schema Object can be used, a [Reference Object](#referenceObject) can be used in its place.
     This allows referencing definitions instead of defining them inline.
     */
    private String $ref = null;
    /**
     * A `true` value adds `"null"` to the allowed type specified by the `type` keyword, only if `type` is explicitly defined within the same Schema Object.
     * Other Schema Object constraints retain their defined behavior, and therefore may disallow the use of `null` as a value.
     * A `false` value leaves the specified or default `type` unmodified. The default value is `false`.
     */
    private Boolean nullable = null;
    /**
     * Relevant only for Schema `"properties"` definitions. Declares the property as "read only". This means that it MAY be sent as part of a response but SHOULD NOT be sent as part of the request. If the property is marked as `readOnly` being `true` and is in the `required` list, the `required` will take effect on the response only. A property MUST NOT be marked as both `readOnly` and `writeOnly` being `true`. Default value is `false`.
     */
    private Boolean readOnly = null;
    /**
     *Relevant only for Schema `"properties"` definitions. Declares the property as "write only". Therefore, it MAY be sent as part of a request but SHOULD NOT be sent as part of the response. If the property is marked as `writeOnly` being `true` and is in the `required` list, the `required` will take effect on the request only. A property MUST NOT be marked as both `readOnly` and `writeOnly` being `true`. Default value is `false`.
     */
    private Boolean writeOnly = null;
    /**
     *A free-form property to include an example of an instance for this schema.
     * To represent examples that cannot be naturally represented in JSON or YAML, a string value can be used to contain the example with escaping where necessary.
     */
    protected T example = null;
    /**
     * Additional external documentation for this schema.
     */
    private ExternalDocumentation externalDocs = null;
    /**
     * Specifies that a schema is deprecated and SHOULD be transitioned out of usage. Default value is `false`.
     */
    private Boolean deprecated = null;
    /**
     * This MAY be used only on properties schemas. It has no effect on root schemas.
     * Adds additional metadata to describe the XML representation of this property.
     *
     *
     The [xml](#schemaXml) property allows extra definitions when translating the JSON definition to XML.
     The [XML Object](#xmlObject) contains additional information about the available options.
     */
    private XML xml = null;
    private Map<String, Object> extensions = null;
    /**
     * Adds support for polymorphism. The discriminator is an object name that is used to differentiate between other schemas which may satisfy the payload description. See [Composition and Inheritance](#schemaComposition) for more details.
     */
    private Discriminator discriminator = null;

    private boolean exampleSetFlag;

    /*

This object MAY be extended with [Specification Extensions](#specificationExtensions).

###### <a name="schemaComposition"></a>Composition and Inheritance (Polymorphism)

The OpenAPI Specification allows combining and extending model definitions using the `allOf` property of JSON Schema, in effect offering model composition.
`allOf` takes an array of object definitions that are validated *independently* but together compose a single object.

While composition offers model extensibility, it does not imply a hierarchy between the models.
To support polymorphism, the OpenAPI Specification adds the `discriminator` field.
When used, the `discriminator` will be the name of the property that decides which schema definition validates the structure of the model.
As such, the `discriminator` field MUST be a required field.

There are two ways to define the value of a discriminator for an inheriting instance.
- Use the schema name.
- Override the schema name by overriding the property with a new value. If a new value exists, this takes precedence over the schema name.
As such, inline schema definitions, which do not have a given id, *cannot* be used in polymorphism.

     */

    public Schema() {
    }

    protected Schema(String type, String format) {
        this.type = type;
        this.format = format;
    }

    /**
     * returns the name property from a from a Schema instance. Ignored in serialization.
     *
     * @return String name
     **/
    @JsonIgnore
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schema name(String name) {
        this.setName(name);
        return this;
    }

    /**
     * returns the discriminator property from a AllOfSchema instance.
     *
     * @return Discriminator discriminator
     **/

    public Discriminator getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(Discriminator discriminator) {
        this.discriminator = discriminator;
    }

    public Schema discriminator(Discriminator discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    /**
     * returns the title property from a Schema instance.
     *
     * @return String title
     **/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Schema title(String title) {
        this.title = title;
        return this;
    }

    /**
     * returns the _default property from a StringSchema instance.
     *
     * @return String _default
     **/

    public T getDefault() {
        return _default;
    }

    public void setDefault(Object _default) {
        this._default = cast(_default);
    }

    @SuppressWarnings("unchecked")
    protected T cast(Object value) {
        return (T) value;
    }

    public List<T> getEnum() {
        return _enum;
    }

    public void setEnum(List<T> _enum) {
        this._enum = _enum;
    }

    public void addEnumItemObject(T _enumItem) {
        if (this._enum == null) {
            this._enum = new ArrayList<>();
        }
        this._enum.add(cast(_enumItem));
    }

    /**
     * returns the multipleOf property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return BigDecimal multipleOf
     **/

    public BigDecimal getMultipleOf() {
        return multipleOf;
    }

    public void setMultipleOf(BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    public Schema multipleOf(BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
        return this;
    }

    /**
     * returns the maximum property from a Schema instance.
     *
     * @return BigDecimal maximum
     **/

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public Schema maximum(BigDecimal maximum) {
        this.maximum = maximum;
        return this;
    }

    /**
     * returns the exclusiveMaximum property from a Schema instance.
     *
     * @return Boolean exclusiveMaximum
     **/

    public Boolean getExclusiveMaximum() {
        return exclusiveMaximum;
    }

    public void setExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public Schema exclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
        return this;
    }

    /**
     * returns the minimum property from a Schema instance.
     *
     * @return BigDecimal minimum
     **/

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public Schema minimum(BigDecimal minimum) {
        this.minimum = minimum;
        return this;
    }

    /**
     * returns the exclusiveMinimum property from a Schema instance.
     *
     * @return Boolean exclusiveMinimum
     **/

    public Boolean getExclusiveMinimum() {
        return exclusiveMinimum;
    }

    public void setExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public Schema exclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
        return this;
    }

    /**
     * returns the maxLength property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxLength
     **/

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Schema maxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    /**
     * returns the minLength property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minLength
     **/

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Schema minLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    /**
     * returns the pattern property from a Schema instance.
     *
     * @return String pattern
     **/

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Schema pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    /**
     * returns the maxItems property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxItems
     **/

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    public Schema maxItems(Integer maxItems) {
        this.maxItems = maxItems;
        return this;
    }

    /**
     * returns the minItems property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minItems
     **/

    public Integer getMinItems() {
        return minItems;
    }

    public void setMinItems(Integer minItems) {
        this.minItems = minItems;
    }

    public Schema minItems(Integer minItems) {
        this.minItems = minItems;
        return this;
    }

    /**
     * returns the uniqueItems property from a Schema instance.
     *
     * @return Boolean uniqueItems
     **/

    public Boolean getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Schema uniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
        return this;
    }

    /**
     * returns the maxProperties property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer maxProperties
     **/

    public Integer getMaxProperties() {
        return maxProperties;
    }

    public void setMaxProperties(Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    public Schema maxProperties(Integer maxProperties) {
        this.maxProperties = maxProperties;
        return this;
    }

    /**
     * returns the minProperties property from a Schema instance.
     * <p>
     * minimum: 0
     *
     * @return Integer minProperties
     **/

    public Integer getMinProperties() {
        return minProperties;
    }

    public void setMinProperties(Integer minProperties) {
        this.minProperties = minProperties;
    }

    public Schema minProperties(Integer minProperties) {
        this.minProperties = minProperties;
        return this;
    }

    /**
     * returns the required property from a Schema instance.
     *
     * @return List&lt;String&gt; required
     **/

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        List<String> list = new ArrayList<>();
        if (required != null) {
            for (String req : required) {
                if (this.properties == null || this.properties.containsKey(req)) {
                    list.add(req);
                }
            }
        }
        Collections.sort(list);
        if (list.isEmpty()) {
            list = null;
        }
        this.required = list;
    }

    public Schema required(List<String> required) {
        this.required = required;
        return this;
    }

    public Schema addRequiredItem(String requiredItem) {
        if (this.required == null) {
            this.required = new ArrayList<>();
        }
        this.required.add(requiredItem);
        Collections.sort(required);
        return this;
    }

    /**
     * returns the type property from a Schema instance.
     *
     * @return String type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Schema type(String type) {
        this.type = type;
        return this;
    }

    /**
     * returns the not property from a Schema instance.
     *
     * @return Schema not
     **/

    public Schema getNot() {
        return not;
    }

    public void setNot(Schema not) {
        this.not = not;
    }

    public Schema not(Schema not) {
        this.not = not;
        return this;
    }

    /**
     * returns the properties property from a Schema instance.
     *
     * @return Map&lt;String, Schema&gt; properties
     **/

    public Map<String, Schema> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Schema> properties) {
        this.properties = properties;
    }

    public Schema properties(Map<String, Schema> properties) {
        this.properties = properties;
        return this;
    }

    public Schema addProperties(String key, Schema propertiesItem) {
        if (this.properties == null) {
            this.properties = new LinkedHashMap<>();
        }
        this.properties.put(key, propertiesItem);
        return this;
    }

    /**
     * returns the additionalProperties property from a Schema instance. Can be either a Boolean or a Schema
     *
     * @return Object additionalProperties
     **/

    public Object getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Object additionalProperties) {
        if (additionalProperties != null && !(additionalProperties instanceof Boolean) && !(additionalProperties instanceof Schema)) {
            throw new IllegalArgumentException("additionalProperties must be either a Boolean or a Schema instance");
        }
        this.additionalProperties = additionalProperties;
    }

    public Schema additionalProperties(Object additionalProperties) {
        setAdditionalProperties(additionalProperties);
        return this;
    }

    /**
     * returns the description property from a Schema instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schema description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the format property from a Schema instance.
     *
     * @return String format
     **/

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Schema format(String format) {
        this.format = format;
        return this;
    }

    /**
     * returns the $ref property from a Schema instance.
     *
     * @return String $ref
     **/
    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        if ($ref != null && ($ref.indexOf('.') == -1 && $ref.indexOf('/') == -1)) {
            $ref = "#/components/schemas/" + $ref;
        }
        this.$ref = $ref;
    }

    public Schema $ref(String $ref) {

        set$ref($ref);
        return this;
    }

    /**
     * returns the nullable property from a Schema instance.
     *
     * @return Boolean nullable
     **/

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Schema nullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    /**
     * returns the readOnly property from a Schema instance.
     *
     * @return Boolean readOnly
     **/

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Schema readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * returns the writeOnly property from a Schema instance.
     *
     * @return Boolean writeOnly
     **/

    public Boolean getWriteOnly() {
        return writeOnly;
    }

    public void setWriteOnly(Boolean writeOnly) {
        this.writeOnly = writeOnly;
    }

    public Schema writeOnly(Boolean writeOnly) {
        this.writeOnly = writeOnly;
        return this;
    }

    /**
     * returns the example property from a Schema instance.
     *
     * @return String example
     **/

    public Object getExample() {
        return example;
    }

    public void setExample(Object example) {
        this.example = cast(example);
        if (!(example != null && this.example == null)) {
            exampleSetFlag = true;
        }
    }

    public Schema example(Object example) {
        setExample(example);
        return this;
    }

    /**
     * returns the externalDocs property from a Schema instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public Schema externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    /**
     * returns the deprecated property from a Schema instance.
     *
     * @return Boolean deprecated
     **/

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Schema deprecated(Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    /**
     * returns the xml property from a Schema instance.
     *
     * @return XML xml
     **/

    public XML getXml() {
        return xml;
    }

    public void setXml(XML xml) {
        this.xml = xml;
    }

    public Schema xml(XML xml) {
        this.xml = xml;
        return this;
    }

    /**
     * returns true if example setter has been invoked
     * Used to flag explicit setting to null of example (vs missing field) while deserializing from json/yaml string
     *
     * @return boolean exampleSetFlag
     **/

    public boolean getExampleSetFlag() {
        return exampleSetFlag;
    }

    public void setExampleSetFlag(boolean exampleSetFlag) {
        this.exampleSetFlag = exampleSetFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schema schema = (Schema) o;
        return Objects.equals(this.title, schema.title) &&
                Objects.equals(this.multipleOf, schema.multipleOf) &&
                Objects.equals(this.maximum, schema.maximum) &&
                Objects.equals(this.exclusiveMaximum, schema.exclusiveMaximum) &&
                Objects.equals(this.minimum, schema.minimum) &&
                Objects.equals(this.exclusiveMinimum, schema.exclusiveMinimum) &&
                Objects.equals(this.maxLength, schema.maxLength) &&
                Objects.equals(this.minLength, schema.minLength) &&
                Objects.equals(this.pattern, schema.pattern) &&
                Objects.equals(this.maxItems, schema.maxItems) &&
                Objects.equals(this.minItems, schema.minItems) &&
                Objects.equals(this.uniqueItems, schema.uniqueItems) &&
                Objects.equals(this.maxProperties, schema.maxProperties) &&
                Objects.equals(this.minProperties, schema.minProperties) &&
                Objects.equals(this.required, schema.required) &&
                Objects.equals(this.type, schema.type) &&
                Objects.equals(this.not, schema.not) &&
                Objects.equals(this.properties, schema.properties) &&
                Objects.equals(this.additionalProperties, schema.additionalProperties) &&
                Objects.equals(this.description, schema.description) &&
                Objects.equals(this.format, schema.format) &&
                Objects.equals(this.$ref, schema.$ref) &&
                Objects.equals(this.nullable, schema.nullable) &&
                Objects.equals(this.readOnly, schema.readOnly) &&
                Objects.equals(this.writeOnly, schema.writeOnly) &&
                Objects.equals(this.example, schema.example) &&
                Objects.equals(this.externalDocs, schema.externalDocs) &&
                Objects.equals(this.deprecated, schema.deprecated) &&
                Objects.equals(this.xml, schema.xml) &&
                Objects.equals(this.extensions, schema.extensions) &&
                Objects.equals(this.discriminator, schema.discriminator) &&
                Objects.equals(this._enum, schema._enum) &&
                Objects.equals(this._default, schema._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, multipleOf, maximum, exclusiveMaximum, minimum, exclusiveMinimum, maxLength, minLength, pattern, maxItems,
                minItems, uniqueItems, maxProperties, minProperties, required, type, not, properties, additionalProperties, description, format, $ref,
                nullable, readOnly, writeOnly, example, externalDocs, deprecated, xml, extensions, discriminator, _enum, _default);
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

    public Schema extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Schema {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    format: ").append(toIndentedString(format)).append("\n");
        sb.append("    $ref: ").append(toIndentedString($ref)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    multipleOf: ").append(toIndentedString(multipleOf)).append("\n");
        sb.append("    maximum: ").append(toIndentedString(maximum)).append("\n");
        sb.append("    exclusiveMaximum: ").append(toIndentedString(exclusiveMaximum)).append("\n");
        sb.append("    minimum: ").append(toIndentedString(minimum)).append("\n");
        sb.append("    exclusiveMinimum: ").append(toIndentedString(exclusiveMinimum)).append("\n");
        sb.append("    maxLength: ").append(toIndentedString(maxLength)).append("\n");
        sb.append("    minLength: ").append(toIndentedString(minLength)).append("\n");
        sb.append("    pattern: ").append(toIndentedString(pattern)).append("\n");
        sb.append("    maxItems: ").append(toIndentedString(maxItems)).append("\n");
        sb.append("    minItems: ").append(toIndentedString(minItems)).append("\n");
        sb.append("    uniqueItems: ").append(toIndentedString(uniqueItems)).append("\n");
        sb.append("    maxProperties: ").append(toIndentedString(maxProperties)).append("\n");
        sb.append("    minProperties: ").append(toIndentedString(minProperties)).append("\n");
        sb.append("    required: ").append(toIndentedString(required)).append("\n");
        sb.append("    not: ").append(toIndentedString(not)).append("\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
        sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
        sb.append("    nullable: ").append(toIndentedString(nullable)).append("\n");
        sb.append("    readOnly: ").append(toIndentedString(readOnly)).append("\n");
        sb.append("    writeOnly: ").append(toIndentedString(writeOnly)).append("\n");
        sb.append("    example: ").append(toIndentedString(example)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
        sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
        sb.append("    discriminator: ").append(toIndentedString(discriminator)).append("\n");
        sb.append("    xml: ").append(toIndentedString(xml)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    protected String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

