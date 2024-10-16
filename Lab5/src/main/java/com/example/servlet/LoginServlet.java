package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));
        if (userDAO.isValidUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            if (rememberMe) {
                Cookie cookieName = new Cookie("username", username);
                Cookie cookiePass = new Cookie("password", password);

                cookieName.setMaxAge(7 * 24 * 60 * 60);
                cookiePass.setMaxAge(7 * 24 * 60 * 60);

                response.addCookie(cookieName);
                response.addCookie(cookiePass);
                System.out.println("Đã sử dụng Cookie");
            }
            else {
                Cookie usernameCookie = new Cookie("username", "");
                Cookie passwordCookie = new Cookie("password", "");
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                System.out.println("Không sử dụng Cookie");
            }
            response.sendRedirect(request.getContextPath() + "/main");
        } else {
            System.out.println("Thất bại ");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
