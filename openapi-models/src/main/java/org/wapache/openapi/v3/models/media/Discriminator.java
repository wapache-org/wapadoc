package org.wapache.openapi.v3.models.media;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 #### <a name="discriminatorObject"></a>Discriminator Object

 When request bodies or response payloads may be one of a number of different schemas, a `discriminator` object can be used to aid in serialization, deserialization, and validation.
 The discriminator is a specific object in a schema which is used to inform the consumer of the specification of an alternative schema based on the value associated with it.

 当请求体或响应有效载荷可能是若干不同模式之一时，可以使用 "判别器 "对象来帮助序列化、反序列化和验证。
 鉴别器是模式中的一个特定对象，用于根据与之相关的值向消费者通报替代模式的规格。

 When using the discriminator, _inline_ schemas will not be considered.

 当使用判别器时，将不考虑_inline_模式。

 ##### Fixed Fields

 字段名 | 类型 | 描述
 ---|:---:|---
 <a name="propertyName"></a>propertyName | `string` | **REQUIRED**. The name of the property in the payload that will hold the discriminator value.
 <a name="discriminatorMapping"></a> mapping | Map[`string`, `string`] | An object to hold mappings between payload values and schema names or references.

 字段名 | 类型 | 描述
 ---|:---:|---
 propertyName | `string` | **必填**. 有效载荷中持有判别器值的属性名称。
 mapping | Map[`string`, `string`] | 一个用于保存有效载荷值与模式名称或引用之间的映射的对象。

 The discriminator object is legal only when using one of the composite keywords `oneOf`, `anyOf`, `allOf`.

 只有在使用 "oneOf"、"anyOf"、"allOf "等复合关键词之一时，判别对象才是合法的。

 In OAS 3.0, a response payload MAY be described to be exactly one of any number of types:

 在OAS 3.0中，响应有效载荷可以被描述为以下类型之一:

 ```yaml
 MyResponseType:
 oneOf:
 - $ref: '#/components/schemas/Cat'
 - $ref: '#/components/schemas/Dog'
 - $ref: '#/components/schemas/Lizard'
 ```

 which means the payload _MUST_, by validation, match exactly one of the schemas described by `Cat`, `Dog`, or `Lizard`.
 In this case, a discriminator MAY act as a "hint" to shortcut validation and selection of the matching schema which may be a costly operation, depending on the complexity of the schema.
 We can then describe exactly which field tells us which schema to use:

 这意味着，通过验证，有效载荷必须与 "猫"、"狗 "或 "蜥蜴 "所描述的模式之一完全匹配。
 在这种情况下，判别器可以作为一个 "提示"，以缩短验证和选择匹配模式的时间，这可能是一个昂贵的操作，取决于模式的复杂性。
 然后，我们可以准确地描述哪个字段告诉我们要使用哪个模式。

 判别器的用法举例

 ```yaml
 MyResponseType:
 oneOf:
 - $ref: '#/components/schemas/Cat'
 - $ref: '#/components/schemas/Dog'
 - $ref: '#/components/schemas/Lizard'
 discriminator:
  propertyName: petType
 ```

 The expectation now is that
 a property with name `petType` _MUST_ be present in the response payload,
 and the value will correspond to the name of a schema defined in the OAS document.
 Thus the response payload:

 现在的期望是，一个名为`petType`的属性必须存在于响应有效载荷中，其值将对应于OAS文档中定义的模式名称。 因此，响应有效载荷：

 ```json
 {
 "id": 12345,
 "petType": "Cat"
 }
 ```

 Will indicate that the `Cat` schema be used in conjunction with this payload.

 将表明`猫'模式将与该有效载荷一起使用。

 In scenarios where the value of the discriminator field does not match the schema name or implicit mapping is not possible, an optional `mapping` definition MAY be used:

 在判别器字段的值与模式名称不匹配或隐式映射不可能的情况下，可以使用一个可选的 "映射 "定义。

 ```yaml
 MyResponseType:
 oneOf:
 - $ref: '#/components/schemas/Cat'
 - $ref: '#/components/schemas/Dog'
 - $ref: '#/components/schemas/Lizard'
 - $ref: 'https://gigantic-server.com/schemas/Monster/schema.json'
 discriminator:
 propertyName: petType
 mapping:
 dog: '#/components/schemas/Dog'
 monster: 'https://gigantic-server.com/schemas/Monster/schema.json'
 ```

 Here the discriminator _value_ of `dog` will map to the schema `#/components/schemas/Dog`, rather than the default (implicit) value of `Dog`.
 If the discriminator _value_ does not match an implicit or explicit mapping, no schema can be determined and validation SHOULD fail.
 Mapping keys MUST be string values, but tooling MAY convert response values to strings for comparison.

 这里，`dog` 的判别器 _值_ 将映射到模式 `#/components/schemas/Dog`，而不是默认（隐式）的 `Dog` 值。
 如果辨别符 _值_ 不匹配隐式或显式映射，则无法确定模式，验证也会失败。
 映射键必须是字符串值，但工具可以将响应值转换为字符串进行比较。

 When used in conjunction with the `anyOf` construct, the use of the discriminator can avoid ambiguity where multiple schemas may satisfy a single payload.

 当与 `anyOf` 结构一起使用时，使用判别器可以避免在多个模式可能满足单一有效载荷的情况下产生歧义。

 In both the `oneOf` and `anyOf` use cases, all possible schemas MUST be listed explicitly.
 To avoid redundancy, the discriminator MAY be added to a parent schema definition, and all schemas comprising the parent schema in an `allOf` construct may be used as an alternate schema.

 在 `oneOf` 和 `anyOf` 两种用例中，所有可能的模式都必须明确列出。
 为了避免冗余，可将判别器添加到父模式定义中，而且在`allOf`结构中，构成父模式的所有模式都可作为备选模式使用。

 简单点讲, 就是类似与Java的类继承或者JS的Mixin了

 For example:

 ```yaml
 components:
 schemas:
 Pet:
 type: object
 required:
 - petType
 properties:
 petType:
 type: string
 discriminator:
 propertyName: petType
 mapping:
 dog: Dog
 Cat:
 allOf:
 - $ref: '#/components/schemas/Pet'
 - type: object
 # all other properties specific to a `Cat`
 properties:
 name:
 type: string
 Dog:
 allOf:
 - $ref: '#/components/schemas/Pet'
 - type: object
 # all other properties specific to a `Dog`
 properties:
 bark:
 type: string
 Lizard:
 allOf:
 - $ref: '#/components/schemas/Pet'
 - type: object
 # all other properties specific to a `Lizard`
 properties:
 lovesRocks:
 type: boolean
 ```

 a payload like this:

 ```json
 {
 "petType": "Cat",
 "name": "misty"
 }
 ```

 will indicate that the `Cat` schema be used.  Likewise this schema:

 ```json
 {
 "petType": "dog",
 "bark": "soft"
 }
 ```

 will map to `Dog` because of the definition in the `mappings` element.

 * @see "https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#discriminatorObject"
 */

/**
 *
 <h4><a name="%3Ca-name=%22discriminatorobject%22%3E%3C/a%3Ediscriminator-object" class="md-header-anchor"></a><a name="discriminatorObject"></a><span>Discriminator Object</span></h4>
 <p><span>When request bodies or response payloads may be one of a number of different schemas, a </span><code>discriminator</code><span> object can be used to aid in serialization, deserialization, and validation.  The discriminator is a specific object in a schema which is used to inform the consumer of the specification of an alternative schema based on the value associated with it.</span></p>
 <p><span>When using the discriminator, </span><em><span>inline</span></em><span> schemas will not be considered.</span></p>
 <h5><a name="fixed-fields" class="md-header-anchor"></a><span>Fixed Fields</span></h5>
 <figure><table>
 <thead>
 <tr><th><span>Field Name</span></th><th style='text-align:center;' ><span>Type</span></th><th><span>Description</span></th></tr></thead>
 <tbody><tr><td><a name="propertyName"></a><span>propertyName</span></td><td style='text-align:center;' ><code>string</code></td><td><strong><span>REQUIRED</span></strong><span>. The name of the property in the payload that will hold the discriminator value.</span></td></tr><tr><td><a name="discriminatorMapping"></a><span> mapping</span></td><td style='text-align:center;' ><span>Map[</span><code>string</code><span>, </span><code>string</code><span>]</span></td><td><span>An object to hold mappings between payload values and schema names or references.</span></td></tr></tbody>
 </table></figure>
 <p><span>The discriminator attribute is legal only when using one of the composite keywords </span><code>oneOf</code><span>, </span><code>anyOf</code><span>, </span><code>allOf</code><span>.</span></p>
 <p><span>In OAS 3.0, a response payload MAY be described to be exactly one of any number of types:</span></p>
 <pre><code>MyResponseType:
 oneOf:
 - $ref: &#39;#/components/schemas/Cat&#39;
 - $ref: &#39;#/components/schemas/Dog&#39;
 - $ref: &#39;#/components/schemas/Lizard&#39;
 </code></pre>
 <p><span>which means the payload </span><em><span>MUST</span></em><span>, by validation, match exactly one of the schemas described by </span><code>Cat</code><span>, </span><code>Dog</code><span>, or </span><code>Lizard</code><span>.  In this case, a discriminator MAY act as a &quot;hint&quot; to shortcut validation and selection of the matching schema which may be a costly operation, depending on the complexity of the schema. We can then describe exactly which field tells us which schema to use:</span></p>
 <pre><code>MyResponseType:
 oneOf:
 - $ref: &#39;#/components/schemas/Cat&#39;
 - $ref: &#39;#/components/schemas/Dog&#39;
 - $ref: &#39;#/components/schemas/Lizard&#39;
 discriminator:
 propertyName: pet_type
 </code></pre>
 <p><span>The expectation now is that a property with name </span><code>pet_type</code><span> </span><em><span>MUST</span></em><span> be present in the response payload, and the value will correspond to the name of a schema defined in the OAS document.  Thus the response payload:</span></p>
 <pre><code>{
 &quot;id&quot;: 12345,
 &quot;pet_type&quot;: &quot;Cat&quot;
 }
 </code></pre>
 <p><span>Will indicate that the </span><code>Cat</code><span> schema be used in conjunction with this payload.</span></p>
 <p><span>In scenarios where the value of the discriminator field does not match the schema name or implicit mapping is not possible, an optional </span><code>mapping</code><span> definition MAY be used:</span></p>
 <pre><code>MyResponseType:
 oneOf:
 - $ref: &#39;#/components/schemas/Cat&#39;
 - $ref: &#39;#/components/schemas/Dog&#39;
 - $ref: &#39;#/components/schemas/Lizard&#39;
 - $ref: &#39;https://gigantic-server.com/schemas/Monster/schema.json&#39;
 discriminator:
 propertyName: pet_type
 mapping:
 dog: &#39;#/components/schemas/Dog&#39;
 monster: &#39;https://gigantic-server.com/schemas/Monster/schema.json&#39;
 </code></pre>
 <p><span>Here the discriminator </span><em><span>value</span></em><span> of </span><code>dog</code><span> will map to the schema </span><code>#/components/schemas/Dog</code><span>, rather than the default (implicit) value of </span><code>Dog</code><span>.  If the discriminator </span><em><span>value</span></em><span> does not match an implicit or explicit mapping, no schema can be determined and validation SHOULD fail. Mapping keys MUST be string values, but tooling MAY convert response values to strings for comparison.</span></p>
 <p><span>When used in conjunction with the </span><code>anyOf</code><span> construct, the use of the discriminator can avoid ambiguity where multiple schemas may satisfy a single payload.</span></p>
 <p><span>In both the </span><code>oneOf</code><span> and </span><code>anyOf</code><span> use cases, all possible schemas MUST be listed explicitly.  To avoid redundancy, the discriminator MAY be added to a parent schema definition, and all schemas comprising the parent schema in an </span><code>allOf</code><span> construct may be used as an alternate schema.</span></p>
 <p><span>For example:</span></p>
 <pre><code>components:
 schemas:
 Pet:
 type: object
 required:
 - pet_type
 properties:
 pet_type:
 type: string
 discriminator:
 propertyName: pet_type
 mapping:
 cachorro: Dog
 Cat:
 allOf:
 - $ref: &#39;#/components/schemas/Pet&#39;
 - type: object
 # all other properties specific to a `Cat`
 properties:
 name:
 type: string
 Dog:
 allOf:
 - $ref: &#39;#/components/schemas/Pet&#39;
 - type: object
 # all other properties specific to a `Dog`
 properties:
 bark:
 type: string
 Lizard:
 allOf:
 - $ref: &#39;#/components/schemas/Pet&#39;
 - type: object
 # all other properties specific to a `Lizard`
 properties:
 lovesRocks:
 type: boolean
 </code></pre>
 <p><span>a payload like this:</span></p>
 <pre><code>{
 &quot;pet_type&quot;: &quot;Cat&quot;,
 &quot;name&quot;: &quot;misty&quot;
 }
 </code></pre>
 <p><span>will indicate that the </span><code>Cat</code><span> schema be used.  Likewise this schema:</span></p>
 <pre><code>{
 &quot;pet_type&quot;: &quot;cachorro&quot;,
 &quot;bark&quot;: &quot;soft&quot;
 }
 </code></pre>
 <p><span>will map to </span><code>Dog</code><span> because of the definition in the </span><code>mappings</code><span> element.</span></p>

 */
public class Discriminator {

    /** 属性名, 根据这个属性的值来判断是哪一个Schema */
    private String propertyName;

    /** Map<属性值, Schema>, 这个如果没有值的话, 默认就是用Schema的名称来匹配. */
    private Map<String, String> mapping;

    public Discriminator propertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Discriminator mapping(String name, String value) {
        if (this.mapping == null) {
            this.mapping = new LinkedHashMap<>();
        }
        this.mapping.put(name, value);
        return this;
    }

    public Discriminator mapping(Map<String, String> mapping) {
        this.mapping = mapping;
        return this;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Discriminator)) {
            return false;
        }

        Discriminator that = (Discriminator) o;

        if (propertyName != null ? !propertyName.equals(that.propertyName) : that.propertyName != null) {
            return false;
        }
        return mapping != null ? mapping.equals(that.mapping) : that.mapping == null;

    }

    @Override
    public int hashCode() {
        int result = propertyName != null ? propertyName.hashCode() : 0;
        result = 31 * result + (mapping != null ? mapping.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Discriminator{" +
                "propertyName='" + propertyName + '\'' +
                ", mapping=" + mapping +
                '}';
    }
}
