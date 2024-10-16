package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIde = request.getParameterValues("favorite_ide");
        String toeic = request.getParameter("toeic");
        String message = request.getParameter("message");

        out.println("<!doctype html>");
        out.println("<html lang=\"en\">");
        out.println("<head><meta charset=\"UTF-8\"><title>Registration Result</title></head>");
        out.println("<body>");

        boolean isValid = true;

        if (name == null || name.isEmpty()) {
            out.println("<p style='color:red'>Error: Name is required.</p>");
            isValid = false;
        }
        if (email == null || email.isEmpty()) {
            out.println("<p style='color:red'>Error: Email address is required.</p>");
            isValid = false;
        }
        if (birthday == null || birthday.isEmpty()) {
            out.println("<p style='color:red'>Error: Birthday is required.</p>");
            isValid = false;
        }
        if (birthtime == null || birthtime.isEmpty()) {
            out.println("<p style='color:red'>Error: Birthtime is required.</p>");
            isValid = false;
        }
        if (gender == null) {
            out.println("<p style='color:red'>Error: Gender is required.</p>");
            isValid = false;
        }
        if (country == null || country.equals("Select a country")) {
            out.println("<p style='color:red'>Error: Country selection is required.</p>");
            isValid = false;
        }
        if (toeic == null || toeic.isEmpty()) {
            out.println("<p style='color:red'>Error: TOEIC score is required.</p>");
            isValid = false;
        }

        if (isValid) {
            out.println("<table border='1' cellpadding='10' cellspacing='0'>");
            out.println("<tr><td>Họ tên</td><td>" + name + "</td></tr>");
            out.println("<tr><td>Email</td><td>" + email + "</td></tr>");
            out.println("<tr><td>Ngày sinh</td><td>" + birthday + "</td></tr>");
            out.println("<tr><td>Giờ sinh</td><td>" + birthtime + "</td></tr>");
            out.println("<tr><td>Giới tính</td><td>" + gender + "</td></tr>");
            out.println("<tr><td>Quốc gia</td><td>" + country + "</td></tr>");

            out.println("<tr><td>Các IDE yêu thích</td><td>");
            if (favoriteIde != null) {
                out.println(String.join("<br>", favoriteIde));
            } else {
                out.println("None");
            }
            out.println("</td></tr>");

            out.println("<tr><td>Điểm TOEIC</td><td>" + toeic + "</td></tr>");
            out.println("<tr><td>Tin nhắn</td><td>" + message + "</td></tr>");
            out.println("</table>");
        } else {
            out.println("<p>Please go back and fill in the required fields.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
