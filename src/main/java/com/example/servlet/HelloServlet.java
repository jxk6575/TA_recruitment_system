package com.example.servlet;

import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "helloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "World";
        }

        User user = new User(name, "user@example.com");
        req.setAttribute("user", user);
        req.setAttribute("title", "105组TA招聘系统");

        req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        if (name == null) {
            name = "World";
        }

        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/hello?name=" + encoded);
    }
}
