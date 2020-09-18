/*
 * Copyright 2018 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 * Copyright 2018 SmartBear Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wapache.openapi.generator.languages.features;

public interface BeanValidationExtendedFeatures {

    // Language (implementing Client/Server) supports automatic BeanValidation (1.1)
    public static final String USE_BEANVALIDATION_FEATURE = "useBeanValidationFeature";

    public void setUseBeanValidationFeature(boolean useBeanValidationFeature);


}
