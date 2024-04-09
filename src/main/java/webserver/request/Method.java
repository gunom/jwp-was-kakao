package webserver.request;

import java.util.Locale;

public class Method {

    private final String method;

    public Method(String method) {
        this.method = method.toUpperCase(Locale.ROOT);
    }
}
