/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test.org.springdoc.api.app77;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.extensions.Extension;
import org.wapache.openapi.v3.annotations.extensions.ExtensionProperty;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	@ApiResponse(content = @Content(schema = @Schema(type = "string")), extensions = @Extension(properties = @ExtensionProperty(name = "x-is-file", value = "true")))
	@GetMapping(value = "/persons")
	public void persons(@Valid @NotBlank String name) {

	}


	@Operation(responses = @ApiResponse(content = @Content(schema = @Schema(type = "string")), extensions = @Extension(properties = @ExtensionProperty(name = "x-is-file", value = "true"))))
	@GetMapping(value = "/persons2")
	public void persons2(@Valid @NotBlank String name) {

	}

}
