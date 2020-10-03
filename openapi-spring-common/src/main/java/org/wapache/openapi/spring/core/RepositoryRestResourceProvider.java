package org.wapache.openapi.spring.core;

import java.util.List;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.spring.core.fn.RouterOperation;

/**
 * The interface Repository rest resource provider.
 * @author bnasslahsen
 */
@Deprecated // 不支持webflux和webmvc.fn
public interface RepositoryRestResourceProvider {

	/**
	 * Gets router operations.
	 *
	 * @param openAPI the open api
	 * @return the router operations
	 */
	List<RouterOperation> getRouterOperations(OpenAPI openAPI);
}
