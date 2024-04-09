package webserver.response;

public interface Response {
    byte[] getHeader();
    byte[] getBody();
}
