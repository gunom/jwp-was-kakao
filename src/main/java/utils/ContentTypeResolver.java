package utils;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeResolver {
    private static final Map<String, String> CONTENT_TYPES = new HashMap<>();
    
    static {
        // Add mappings for content types
        CONTENT_TYPES.put("html", "text/html");
        CONTENT_TYPES.put("css", "text/css");
        CONTENT_TYPES.put("js", "text/javascript");
        CONTENT_TYPES.put("png", "image/png");
        CONTENT_TYPES.put("woff", "font/woff");
        CONTENT_TYPES.put("woff2", "font/woff2");
        CONTENT_TYPES.put("ttf", "font/ttf");
    }
    
    public static String getContentType(String path) {
        String extension = getFileExtension(path);
        return CONTENT_TYPES.getOrDefault(extension, "text/plain");
    }
    
    private static String getFileExtension(String path) {
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < path.length() - 1) {
            return path.substring(dotIndex + 1);
        }
        return "";
    }
}
