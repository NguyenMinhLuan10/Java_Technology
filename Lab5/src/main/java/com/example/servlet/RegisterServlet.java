package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (password != null && password.equals(confirmPassword)) {
            try {
                User user = new User(fullname, email, password);
                userDAO.add(user);
                response.sendRedirect("register-success.jsp");
            } catch (HibernateException e) {
                e.printStackTrace();
                response.sendRedirect("register.jsp?error=databaseError");
            }
        } else {
            response.sendRedirect("register.jsp?error=passwordMismatch");
        }
    }
}
