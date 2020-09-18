package org.wapache.openapi.generator.languages;

import org.wapache.openapi.generator.CodegenConfig;
import org.wapache.openapi.generator.CodegenType;
import org.wapache.openapi.generator.DefaultCodegen;
import org.wapache.openapi.generator.SupportingFile;
import org.wapache.openapi.generator.meta.GeneratorMetadata;
import org.wapache.openapi.generator.meta.Stability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.wapache.openapi.generator.utils.StringUtils.escape;

public class MarkdownDocumentationCodegen extends DefaultCodegen implements CodegenConfig {
    public static final String PROJECT_NAME = "projectName";

    private static final Logger LOGGER = LoggerFactory.getLogger(MarkdownDocumentationCodegen.class);

    public CodegenType getTag() {
        return CodegenType.DOCUMENTATION;
    }

    public String getName() {
        return "markdown";
    }

    public String getHelp() {
        return "Generates a markdown documentation.";
    }

    public MarkdownDocumentationCodegen() {
        super();

        generatorMetadata = GeneratorMetadata.newBuilder(generatorMetadata)
                .stability(Stability.BETA)
                .build();

        outputFolder = "generated-code" + File.separator + "markdown";
        modelTemplateFiles.put("model.mustache", ".md");
        apiTemplateFiles.put("api.mustache", ".md");
        embeddedTemplateDir = templateDir = "markdown-documentation";
        apiPackage = File.separator + "Apis";
        modelPackage = "Models";
        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
        // TODO: Fill this out.
    }

    @Override
    protected void initalizeSpecialCharacterMapping() {
        // escape only those symbols that can mess up markdown
        specialCharReplacements.put("\\", "\\\\");
        specialCharReplacements.put("/", "\\/");
        specialCharReplacements.put("`", "\\`");
        specialCharReplacements.put("*", "\\*");
        specialCharReplacements.put("_", "\\_");
        specialCharReplacements.put("[", "\\[");
        specialCharReplacements.put("]", "\\]");

        // todo Current markdown api and model mustache templates display properties and parameters in tables. Pipe
        //  symbol in a table can be commonly escaped with a backslash (e.g. GFM supports this). However, in some cases
        //  it may be necessary to choose a different approach.
        specialCharReplacements.put("|", "\\|");
    }

    /**
     * Works identically to {@link DefaultCodegen#toParamName(String)} but doesn't camelize.
     *
     * @param name Codegen property object
     * @return the sanitized parameter name
     */
    @Override
    public String toParamName(String name) {
        if (reservedWords.contains(name)) {
            return escapeReservedWord(name);
        } else if (((CharSequence) name).chars().anyMatch(character -> specialCharReplacements.keySet().contains("" + ((char) character)))) {
            return escape(name, specialCharReplacements, null, null);
        }
        return name;
    }
}
