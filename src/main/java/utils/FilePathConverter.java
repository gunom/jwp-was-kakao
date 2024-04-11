package utils;

public class FilePathConverter {
    private static final String TEMPLATES_DIRECTORY = "./templates";
    private static final String STATIC_DIRECTORY = "./static";

    public static String convertPathToFilePath(String uriPath) {
        if (isTemplateFile(uriPath)) {
            return TEMPLATES_DIRECTORY + uriPath;
        }
        return STATIC_DIRECTORY + uriPath;
    }

    private static boolean isTemplateFile(String uriPath) {
        return uriPath.endsWith("html") || uriPath.endsWith("ico");
    }
}
