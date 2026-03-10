package ta105;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "helloApiServlet", urlPatterns = {"/api/v1/hello"})
public class HelloApiServlet extends HttpServlet {
    private static final String TITLE = "105组TA招聘系统";
    private static final String DEFAULT_EMAIL = "user@example.com";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "World";
        }

        User user = new User(name, DEFAULT_EMAIL);
        writeUserResponse(resp, user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String body = new String(req.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        String name = extractNameFromJson(body);
        if (name == null || name.isBlank()) {
            writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "INVALID_REQUEST", "Request body must contain non-empty field 'name'.");
            return;
        }

        User user = new User(name, DEFAULT_EMAIL);
        writeUserResponse(resp, user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writeError(resp, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED", "Only GET and POST are supported.");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writeError(resp, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED", "Only GET and POST are supported.");
    }

    private void writeUserResponse(HttpServletResponse resp, User user) throws IOException {
        String json = "{\"title\":\"" + escapeJson(TITLE) + "\",\"user\":{\"name\":\"" + escapeJson(user.getName())
                + "\",\"email\":\"" + escapeJson(user.getEmail()) + "\"}}";
        writeJson(resp, HttpServletResponse.SC_OK, json);
    }

    private void writeError(HttpServletResponse resp, int status, String code, String message) throws IOException {
        String json = "{\"code\":\"" + escapeJson(code) + "\",\"message\":\"" + escapeJson(message) + "\"}";
        writeJson(resp, status, json);
    }

    private void writeJson(HttpServletResponse resp, int status, String body) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(body);
    }

    private String extractNameFromJson(String body) {
        if (body == null) {
            return null;
        }

        int keyIndex = body.indexOf("\"name\"");
        if (keyIndex < 0) {
            return null;
        }

        int colonIndex = body.indexOf(':', keyIndex);
        if (colonIndex < 0) {
            return null;
        }

        int startQuote = body.indexOf('"', colonIndex + 1);
        if (startQuote < 0) {
            return null;
        }

        int endQuote = body.indexOf('"', startQuote + 1);
        if (endQuote < 0) {
            return null;
        }

        return body.substring(startQuote + 1, endQuote).trim();
    }

    private String escapeJson(String value) {
        String escaped = value.replace("\\", "\\\\");
        escaped = escaped.replace("\"", "\\\"");
        escaped = escaped.replace("\n", "\\n");
        escaped = escaped.replace("\r", "\\r");
        return escaped;
    }
}
