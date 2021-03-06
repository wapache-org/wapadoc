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

package org.wapache.openapi.v3.annotations.headers;

import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Encoding;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation may be used to add one or more headers to the definition of a response or as attribute of content
 * encoding by defining it as field {@link ApiResponse#headers()} or {@link Content#encoding()}.
 * <p>Please note that request headers are defined as Header {@link Parameter}.</p>
 *
 * @see <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#headerObject">Header (OpenAPI specification)</a>
 * @see ApiResponse
 * @see Parameter
 * @see Encoding
 **/
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Header {
    /**
     * Required: The name of the header. The name is only used as the key to store this header in a map.
     *
     * @return the header's name
     **/
    String name();

    /**
     * Additional description data to provide on the purpose of the header
     *
     * @return the header's description
     **/
    String description() default "";

    /**
     * The schema defining the type used for the header. Ignored if the properties content or array are specified.
     *
     * @return the schema of the header
     **/
    Schema schema() default @Schema();

    /**
     * Determines whether this header is mandatory. The property may be included and its default value is false.
     *
     * @return whether or not the header is required
     **/
    boolean required() default false;

    /**
     * Specifies that a header is deprecated and should be transitioned out of usage.
     *
     * @return whether or not the header is deprecated
     **/
    boolean deprecated() default false;

    /**
     * A reference to a header defined in components headers.
     *
     * @since 2.0.3
     * @return the reference
     **/
    String ref() default "";

}
