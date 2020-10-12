/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.wapache.openapi.spring.core.converters.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.media.ArraySchema;
import org.wapache.openapi.v3.annotations.media.Schema;

/**
 * The interface Pageable as query param.
 *
 * <code>
 *    @GetMapping("/page")
 *    @PageableAsQueryParam
 *    public Page<Account> findAll(@Parameter(hidden=true) Pageable pageable) {
 * 		return accountRepository.findAll(pageable);
 *    }
 * </code>
 *
 * @author bnasslahsen
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY
		, name = "page"
		, schema = @Schema(type = "integer", title="第几页", description = "取值范围:(0..N), 第一页传0", defaultValue = "0"))
@Parameter(in = ParameterIn.QUERY
		, name = "size"
		, schema = @Schema(type = "integer", title="每页记录数", description = "", defaultValue = "20"))
@Parameter(in = ParameterIn.QUERY
		, name = "sort"
		, array = @ArraySchema(schema = @Schema(type = "string", title="排序规则"
	, description = "格式: 属性名[,asc|desc]"
	+ "默认的排序方式是asc升序"
	+ "支持多个排序规则, 譬如 http://localhost/xxx?sort=a,desc&sort=b,asc")))
public @interface PageableAsQueryParam {

}