package webserver.request;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HttpStartLineTest {

    @Test
    public void getMethodTest() {
        String startLineString = "GET / HTTP/1.1";
        HttpStartLine startLine = new HttpStartLine(startLineString);
        String expected = "GET";

        String actual = startLine.getMethod();

        assertEquals(expected, actual);
    }

    @Test
    public void getPathTest() {
        String startLineString = "GET /index.html HTTP/1.1";
        HttpStartLine startLine = new HttpStartLine(startLineString);
        String expected = "/index.html";

        String actual = startLine.getPath();

        assertEquals(expected, actual);
    }
}
