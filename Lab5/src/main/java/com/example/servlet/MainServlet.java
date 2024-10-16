package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.model.Product;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession(false);
        String username = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }

        if (username != null && password != null) {
            UserDAO userDAO = new UserDAO();
            if (userDAO.isValidUser(username, password)) {
                if (session == null) {
                    session = req.getSession();
                    session.setAttribute("username", username);
                }
            }
        }

        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        ProductDAO p = new ProductDAO();
        List<Product> list = p.getAll();
        req.setAttribute("productList", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
