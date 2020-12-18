package org.wapache.openapi.server.configuration;

//import java.util.ArrayList;
//import java.util.List;

// ParameterBuilderPlugin 是 springfox的类, 没有引入springfox, 所以没有

//@Component
//public class ParameterAllowableValuesPlugin implements ParameterBuilderPlugin {
//
//    private static List<String> clients = new ArrayList<>();
//    private static List<String> servers = new ArrayList<>();
//
//    static {
//        List<CodegenConfig> extensions = CodegenConfigLoader.getAll();
//        for (CodegenConfig config : extensions) {
//            if (config.getTag().equals(CodegenType.CLIENT)
//                || config.getTag().equals(CodegenType.DOCUMENTATION)) {
//                clients.add(config.getName());
//            } else if (config.getTag().equals(CodegenType.SERVER)) {
//                servers.add(config.getName());
//            }
//        }
//
//        clients.sort(String.CASE_INSENSITIVE_ORDER);
//        servers.sort(String.CASE_INSENSITIVE_ORDER);
//    }
//
//    @Override
//    public void apply(ParameterContext parameterContext) {
//        String name = parameterContext.getOperationContext().getName();
//        switch (name) {
//            case "getClientOptions":
//            case "generateClient":
//                parameterContext.parameterBuilder().allowableValues(new AllowableListValues(clients, "string"));
//                break;
//            case "getServerOptions":
//            case "generateServerForLanguage":
//                parameterContext.parameterBuilder().allowableValues(new AllowableListValues(servers, "string"));
//        }
//    }
//
//    @Override
//    public boolean supports(DocumentationType documentationType) {
//        return true;
//    }
//}
