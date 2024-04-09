package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParseUtils {
    public static Map<String, String> parseParam(String paramString) {
        String[] params = paramString.split("&");
        Map<String, String> paramsMap = new HashMap<>();
        Arrays.stream(params)
            .forEach(param -> {
                String[] keyValue = param.split("=");
                paramsMap.put(keyValue[0], keyValue[1]);
            });
        return Collections.unmodifiableMap(paramsMap);
    }
}