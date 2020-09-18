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

package test.org.springdoc.api.app29;

import java.util.List;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.media.ArraySchema;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.parameters.ApiRequestBody;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@PostMapping(value = "/post-entity")
	@Operation(description = "Post entity",
			requestBody = @ApiRequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrackerData.class))),
			responses =
					{ @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrackerData.class)))) })
	List<TrackerData> postEntity(@ApiRequestBody TrackerData postEntity) {
		return null;
	}

}
