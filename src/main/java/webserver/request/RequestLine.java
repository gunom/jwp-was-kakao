package webserver.request;

public class RequestLine {

    private final Method method;
    private final Path path;
    private final Protocol protocol;

    public RequestLine(String requestLine) {
        if (requestLine == null) {
            throw new IllegalArgumentException("RequestLine이 null입니다.");
        }
        String[] tokens = requestLine.split(" ");
        this.method = Method.of(tokens[0]);
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
