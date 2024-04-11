package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParseUtils {

    private final static String PARAM_DELIMITER = "&";
    private final static String KEY_VALUE_DELIMITER = "=";

    public static Map<String, String> parseParam(String paramString) {
        if (paramString == null || paramString.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, String> paramsMap = new HashMap<>();
        String[] params = paramString.split(PARAM_DELIMITER);
        for (String param : params) {
            parseSingleParam(param, paramsMap);
        }

        return Collections.unmodifiableMap(paramsMap);
    }

    private static void parseSingleParam(String param, Map<String, String> paramsMap) {
        if (param == null || param.isEmpty()) {
            return;
        }
        String[] keyValue = param.split(KEY_VALUE_DELIMITER, 2);
        String key = keyValue[0];
        String value = (keyValue.length > 1) ? keyValue[1] : "";
        paramsMap.put(key, value);
    }
}