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

package org.wapache.openapi.spring.core.converters;


import java.util.Iterator;

import com.fasterxml.jackson.databind.JavaType;
import org.wapache.openapi.v3.core.converter.AnnotatedType;
import org.wapache.openapi.v3.core.converter.ModelConverter;
import org.wapache.openapi.v3.core.converter.ModelConverterContext;
import org.wapache.openapi.v3.core.util.Json;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.media.StringSchema;

import static org.wapache.openapi.spring.core.converters.ConverterUtils.isResponseTypeToIgnore;
import static org.wapache.openapi.spring.core.converters.ConverterUtils.isResponseTypeWrapper;

/**
 * The type Response support converter.
 * @author bnasslahsen
 */
public class ResponseSupportConverter implements ModelConverter {

	@Override
	public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
		JavaType javaType = Json.mapper().constructType(type.getType());
		if (javaType != null) {
			Class<?> cls = javaType.getRawClass();
			if (isResponseTypeWrapper(cls)) {
				JavaType innerType = javaType.getBindings().getBoundType(0);
				if (innerType == null)
					return new StringSchema();
				else if (innerType.getBindings() != null && isResponseTypeWrapper(innerType.getRawClass())) {
					type = new AnnotatedType(innerType).jsonViewAnnotation(type.getJsonViewAnnotation()).ctxAnnotations(type.getCtxAnnotations()).resolveAsRef(true);
					return this.resolve(type, context, chain);
				}
				else
					type = new AnnotatedType(innerType).jsonViewAnnotation(type.getJsonViewAnnotation()).ctxAnnotations((type.getCtxAnnotations())).resolveAsRef(true);
			}
			else if (isResponseTypeToIgnore(cls))
				return null;
		}
		return (chain.hasNext()) ? chain.next().resolve(type, context, chain) : null;
	}

}