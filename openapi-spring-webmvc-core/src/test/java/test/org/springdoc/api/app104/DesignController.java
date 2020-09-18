package test.org.springdoc.api.app104;

import org.wapache.openapi.v3.annotations.tags.Tag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "design")
@Controller
@RequestMapping("/design")
public class DesignController extends CrudController<Design> {


}