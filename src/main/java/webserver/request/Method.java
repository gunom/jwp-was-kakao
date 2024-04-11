package webserver.request;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Method {

    private static final Map<String, Method> METHODS;
    private final String method;

    static {
        METHODS = new HashMap<>();
        METHODS.put("GET", new Method("GET"));
        METHODS.put("POST", new Method("POST"));
    }

    private Method(String method) {
        this.method = method.toUpperCase(Locale.ROOT);
    }

    public static Method of(String method) {
        return METHODS.get(method);
    }

    public String getMethod() {
        return method;
    }
}