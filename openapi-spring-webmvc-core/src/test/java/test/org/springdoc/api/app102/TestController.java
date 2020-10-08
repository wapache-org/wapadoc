package test.org.springdoc.api.app102;

import org.wapache.openapi.spring.api.annotations.ParameterObject;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("test")
	public void getTest(
		// TODO RequestParam默认required时true, Nullable允许null, 两者冲突.
		// 需要验证下, spring最终的实现, 是不是一定要有值, 然后修改这个测试用例的期望结果, 暂时期望结果改为true
		@RequestParam @Nullable String param,
		@ParameterObject InheritedRequestParams requestParams
	) {
	}
}
