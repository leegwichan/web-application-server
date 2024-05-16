package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.HttpStartLine;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            HttpStartLine startLine = readStartLine(reader);
            if (startLine != null) {
                log.trace("METHOD = {}", startLine.getMethod());
                log.trace("PATH = {}", startLine.getPath());
            }
            printHeader(reader);

            String body = "Hello World\n";
            responseToClient(out, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private HttpStartLine readStartLine(BufferedReader reader) throws IOException {
        String startLine = reader.readLine();
        if (startLine == null) {
            return null;
        }
        return new HttpStartLine(startLine);
    }

    private void printHeader(BufferedReader reader) throws IOException {
        String line;
        do {
            line = reader.readLine();
            log.trace("header : {}", line);
        } while (line != null && !line.isBlank());
    }

    private void responseToClient(OutputStream out, String body) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, body.length());
        responseBody(dos, body.getBytes());
        dos.flush();
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("""
                    HTTP/1.1 200 OK \r
                    Content-Type: text/html;charset=utf-8\r
                    Content-Length: %d\r
                    \r\n
                    """.formatted(lengthOfBodyContent));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
