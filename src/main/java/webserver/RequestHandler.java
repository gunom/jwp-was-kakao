package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.UserService;
import utils.FileIoUtils;
import webserver.request.Request;
import webserver.request.Path;
import webserver.response.Response;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
            connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            Request request = new Request(in);
            // Path path = request.getPath();
            // if (path.getPathWithoutParam().equals("/user/create")) {
            //     UserService.addUser(path.getParams());
            //     return;
            // }
            // String contentType = request.getContentType();
            // byte[] body = getBytes(path);
            DataOutputStream dos = new DataOutputStream(out);
            // response200Header(dos, body.length, contentType);
            // responseBody(dos, body);
            Response response = Controller.handle(request);
            dos.write(response.getHeader());
            dos.write(response.getBody());
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static byte[] getBytes(Path path) throws IOException, URISyntaxException {
        String filepath = FileIoUtils.convertPathToFilePath(path.getPathWithoutParam());
        return FileIoUtils.loadFileFromClasspath(filepath);
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent, String contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: "+ contentType + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
