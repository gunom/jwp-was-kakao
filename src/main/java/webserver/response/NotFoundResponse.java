package webserver.response;

public class NotFoundResponse implements Response {
    @Override
    public byte[] getHeader() {
        return ("HTTP/1.1 404 Not Found \r\n"
            + "Content-Type: application/json;charset=utf-8\r\n"
            + "Content-Length: 0\r\n"
            + "\r\n").getBytes();
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }

}
