package webserver.response;

public class Response {
    byte[] header;
    byte[] body;

    private Response(byte[] header, byte[] body) {
        this.header = header;
        this.body = body;
    }

    private Response(byte[] header, String body) {
        this.header = header;
        this.body = body.getBytes();
    }

    public static Response of(byte[] body, String contentType) {
        byte[] header = createHeader(body, contentType);
        return new Response(header, body);
    }

    public static Response redirect(String body, String location) {
        byte[] header = createHeaderWithRedirectUrl(location, body);
        return new Response(header, body);
    }

    private static byte[] createHeader(byte[] body, String contentType) {
        return ("HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + contentType + ";charset=utf-8\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "\r\n").getBytes();
    }

    private static byte[] createHeaderWithRedirectUrl(String redirectUrl, String body) {
        return ("HTTP/1.1 302 OK \r\n"
                + "Content-Type: application/json;charset=utf-8\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "Location: " + redirectUrl
                + "\r\n").getBytes();
    }

    public byte[] getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setCookie(String cookieValue) {
        String headerString = new String(header);
        String stringBuilder = headerString +
                "Set-Cookie: " + cookieValue + "\r\n";
        header = stringBuilder.getBytes();
    }
}
