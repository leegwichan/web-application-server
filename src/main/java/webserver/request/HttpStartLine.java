package webserver.request;

public class HttpStartLine {

    private static final String SPLITER = " ";

    private final String method;
    private final String path;
    private final String version;

    public HttpStartLine(String startLine) {
        String[] parsedStartLine = startLine.split(SPLITER);
        this.method = parsedStartLine[0]; // GET
        this.path = parsedStartLine[1]; // /index.html
        this.version = parsedStartLine[2]; // HTTP/1.1
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
