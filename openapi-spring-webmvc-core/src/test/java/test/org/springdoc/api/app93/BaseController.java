package test.org.springdoc.api.app93;

import org.wapache.openapi.v3.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class BaseController<TClientModel extends BaseClientModel> {
	@Operation
	@GetMapping("/")
	TClientModel get(TClientModel param) {
		return null;
	}
}
