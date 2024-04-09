package webserver.response;

public class ApiResponse implements Response {
    private String data;

    private ApiResponse(String data) {
        this.data = data;
    }

    public static ApiResponse of(String data) {
        return new ApiResponse(data);
    }

    @Override
    public byte[] getHeader() {
        return ("HTTP/1.1 200 OK \r\n"
        + "Content-Type: application/json;charset=utf-8\r\n"
        + "Content-Length: " + data.length() + "\r\n"
        + "\r\n").getBytes();
    }

    @Override
    public byte[] getBody() {
        return data.getBytes();
    }
}
