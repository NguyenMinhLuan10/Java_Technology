package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/image1")
public class ImageServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/images/onepiece.jpg");
        if (in != null) {
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();
        } else {
            response.setContentType("text/html");
            response.getWriter().println("File not found");
        }
    }
}
