package org.wapache.openapi.server.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.wapache.openapi.generator.CliOption;
import org.wapache.openapi.generator.CodegenConfig;
import org.wapache.openapi.generator.CodegenConfigLoader;
import org.wapache.openapi.generator.CodegenType;
import org.wapache.openapi.server.api.GeneratorApi;
import org.wapache.openapi.server.api.schema.Generated;
import org.wapache.openapi.server.api.schema.GeneratorInput;
import org.wapache.openapi.server.api.schema.ResponseCode;
import org.wapache.openapi.server.service.Generator;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class GeneratorController implements GeneratorApi {

    private static List<String> clients = new ArrayList<>();
    private static List<String> servers = new ArrayList<>();
    private static Map<String, Generated> fileMap = new HashMap<>();

    static {
        List<CodegenConfig> extensions = CodegenConfigLoader.getAll();
        for (CodegenConfig config : extensions) {
            if (config.getTag().equals(CodegenType.CLIENT)
                || config.getTag().equals(CodegenType.DOCUMENTATION)) {
                clients.add(config.getName());
            } else if (config.getTag().equals(CodegenType.SERVER)) {
                servers.add(config.getName());
            }
        }

        clients.sort(String.CASE_INSENSITIVE_ORDER);
        servers.sort(String.CASE_INSENSITIVE_ORDER);
    }

    @Autowired
    private NativeWebRequest request;

    @Override
    public List<String> clientOptions() {
        return clients;
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileId) {
        Generated g = fileMap.get(fileId);
        System.out.println("looking for fileId " + fileId);
        System.out.println("got filename " + g.getFilename());

        File file = new File(g.getFilename());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "I/O error while reading file", e);
        }
        try {
            FileUtils.deleteDirectory(file.getParentFile());
        } catch (IOException e) {
            System.out.println("failed to delete file " + file.getAbsolutePath());
        }
        return ResponseEntity
            .ok()
            .contentType(MediaType.valueOf("application/zip"))
            .header("Content-Disposition",
                "attachment; filename=\"" + g.getFriendlyName() + "-generated.zip\"")
            .header("Accept-Range", "bytes")
            //.header("Content-Length", bytes.length)
            .body(resource);
    }

    @Override
    public ResponseEntity<ResponseCode> generateClient(String language, GeneratorInput generatorInput) {
        String filename = Generator.generateClient(language, generatorInput);
        return getResponse(filename, language + "-client");
    }

    @Override
    public ResponseEntity<ResponseCode> generateServerForLanguage(String framework, GeneratorInput generatorInput) {
        if (framework == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Framework is required");
        }
        String filename = Generator.generateServer(framework, generatorInput);
        System.out.println("generated name: " + filename);

        return getResponse(filename, framework + "-server");
    }

    @Override
    public ResponseEntity<Map<String, CliOption>> getClientOptions(String language) {
        Map<String, CliOption> opts = Generator.getOptions(language);

        if (opts != null) {
            return ResponseEntity.ok().body(opts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Map<String, CliOption>> getServerOptions(String framework) {
        Map<String, CliOption> opts = Generator.getOptions(framework);

        if (opts != null) {
            return ResponseEntity.ok().body(opts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<String> serverOptions() {
        return servers;
    }

    private ResponseEntity<ResponseCode> getResponse(String filename, String friendlyName) {
        String host = System.getenv("GENERATOR_HOST");

        UriComponentsBuilder uriBuilder;
        if (!StringUtils.isBlank(host)) {
            uriBuilder = UriComponentsBuilder.fromUriString(host);
        } else {
            uriBuilder = ServletUriComponentsBuilder.fromCurrentContextPath();
        }

        if (filename != null) {
            String code = UUID.randomUUID().toString();
            Generated g = new Generated();
            g.setFilename(filename);
            g.setFriendlyName(friendlyName);
            fileMap.put(code, g);
            System.out.println(code + ", " + filename);
            String link = uriBuilder.path("/api/gen/download/").path(code).toUriString();
            return ResponseEntity.ok().body(new ResponseCode(code, link));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
