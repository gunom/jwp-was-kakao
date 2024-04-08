package webserver.request;

public class RequestLine {

    private Method method;
    private Path path;
    private Protocol protocol;

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = new Method(tokens[0]);
        this.path = new Path(tokens[1]);
        this.protocol = new Protocol(tokens[2]);
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
