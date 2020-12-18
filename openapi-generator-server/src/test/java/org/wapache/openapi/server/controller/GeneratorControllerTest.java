package org.wapache.openapi.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wapache.openapi.server.api.schema.ResponseCode;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneratorController.class)
public class GeneratorControllerTest {

    private static final String OPENAPI_URL = "https://raw.githubusercontent.com/OpenAPITools/openapi-generator/v4.3.1/modules/openapi-generator/src/test/resources/petstore.json";
    private static final String UUID_REGEX = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-4[a-fA-F0-9]{3}-[89aAbB][a-fA-F0-9]{3}-[a-fA-F0-9]{12}";

    // MockMvc其实应该叫做MockMvcRequest(模拟MVC请求), 它模拟客户端发送一个RESTful接口的请求, 然后对返回结果进行校验。
    @Autowired
    private MockMvc mockMvc;


    public void getLanguages(String type, String expected) throws Exception {
        mockMvc
        .perform(get("/api/gen/" + type)) // 模拟发起一个请求
        .andExpect(status().isOk()) // 期望返回HTTP Code是200
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // 期望返回的内容格式是JSON
        .andExpect(jsonPath("$.[*]").value(hasItem(expected))) // 期望返回的内容里包含指定内容
        ;
    }
    @Test
    public void clientLanguages() throws Exception {
        getLanguages("clients", "java");
    }
    @Test
    public void serverFrameworks() throws Exception {
        getLanguages("servers", "spring");
    }


    private void getOptions(String type, String name) throws Exception {
        mockMvc
        .perform(get("/api/gen/" + type + "/" + name))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.sortParamsByRequiredFlag.opt").value("sortParamsByRequiredFlag"))
        ;
    }
    @Test
    public void clientOptions() throws Exception {
        getOptions("clients", "java");
    }
    @Test
    public void clientOptionsUnknown() throws Exception {
        mockMvc
        .perform(get("/api/gen/clients/unknown"))
        .andExpect(status().isNotFound())
        ;
    }
    @Test
    public void serverOptions() throws Exception {
        getOptions("servers", "spring");
    }
    @Test
    public void serverOptionsUnknown() throws Exception {
        mockMvc.perform(get("/api/gen/servers/unknown"))
            .andExpect(status().isNotFound());
    }

    private void generateAndDownload(String type, String name) throws Exception {
        String result = mockMvc
            .perform(post("http://test.com:1234/api/gen/" + type + "/" + name)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"openAPIUrl\": \"" + OPENAPI_URL + "\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.code").value(matchesPattern(UUID_REGEX)))
            .andExpect(jsonPath("$.link").value(matchesPattern("http\\:\\/\\/test.com\\:1234\\/api\\/gen\\/download\\/" + UUID_REGEX)))
            .andReturn().getResponse().getContentAsString()
        ;

        String code = new ObjectMapper().readValue(result, ResponseCode.class).getCode();

        mockMvc
        .perform(get("http://test.com:1234/api/gen/download/" + code))
        .andExpect(content().contentType("application/zip"))
        .andExpect(status().isOk())
        .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, not(0)))
        ;
    }
    @Test
    public void generateClient() throws Exception {
        generateAndDownload("clients", "java");
    }
    @Test
    public void generateServer() throws Exception {
        generateAndDownload("servers", "spring");
    }


    @Test
    public void generateWIthForwardedHeaders() throws Exception {

        String result = mockMvc
            .perform(post("http://test.com:1234/api/gen/clients/java")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Forwarded-Proto", "https")
                .header("X-Forwarded-Host", "forwarded.com")
                .header("X-Forwarded-Port", "5678")
                .content("{\"openAPIUrl\": \"" + OPENAPI_URL + "\"}"))
            .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(matchesPattern(UUID_REGEX)))
                .andExpect(jsonPath("$.link").value(matchesPattern("https\\:\\/\\/forwarded.com\\:5678\\/api\\/gen\\/download\\/" + UUID_REGEX)))
            .andReturn().getResponse().getContentAsString()
        ;

        String code = new ObjectMapper().readValue(result, ResponseCode.class).getCode();

        mockMvc
        .perform(get("http://test.com:1234/api/gen/download/" + code))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/zip"))
        .andExpect(header().string(HttpHeaders.CONTENT_LENGTH, not(0)))
        ;
    }

}
