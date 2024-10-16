package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")   
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> users;

    @Override
    public void init() throws ServletException {
        users = new HashMap<>();
        users.put("admin", "password");
        users.put("user", "1234");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
