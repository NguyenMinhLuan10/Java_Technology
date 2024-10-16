package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imagePath = getServletContext().getRealPath("/WEB-INF/images/onepiece.jpg");
        File file = new File(imagePath);

        if (file.exists()) {
            response.setContentType("image/jpeg");

            response.setHeader("Content-Disposition", "attachment; filename=\"onepiece.jpg\"");

            FileInputStream in = new FileInputStream(file);
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
            response.getWriter().println("File not found: " + imagePath);
        }
    }
}
