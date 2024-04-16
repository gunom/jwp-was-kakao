package webserver.response;

public class ApiResponse implements Response {
    private String data;
    private byte[] header;

    private ApiResponse(String data, String redirectUrl) {
        this.data = data;
        this.header = redirectUrl == null ? createHeader() : createHeaderWithRedirectUrl(redirectUrl);
    }

    public static ApiResponse of(String data) {
        return new ApiResponse(data, null);
    }

    public static ApiResponse of(String data, String redirectUrl) {
        return new ApiResponse(data, redirectUrl);
    }

    private byte[] createHeader() {
        return ("HTTP/1.1 200 OK \r\n"
            + "Content-Type: application/json;charset=utf-8\r\n"
            + "Content-Length: " + data.length() + "\r\n"
            + "\r\n").getBytes();
    }

    private byte[] createHeaderWithRedirectUrl(String redirectUrl) {
        return ("HTTP/1.1 302 OK \r\n"
            + "Content-Type: application/json;charset=utf-8\r\n"
            + "Content-Length: " + data.length() + "\r\n"
            + "Location: " + redirectUrl
            + "\r\n").getBytes();
    }

    @Override
    public byte[] getHeader() {
        return header;
    }

    @Override
    public byte[] getBody() {
        return data.getBytes();
    }
}
