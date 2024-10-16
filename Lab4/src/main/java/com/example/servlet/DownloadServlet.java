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

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");
        int speed = 0;

        if (fileName == null || fileName.isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().println("File not found.");
            return;
        }

        String filePath = getServletContext().getRealPath("/WEB-INF/files/" + fileName);
        File file = new File(filePath);

        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            try (FileInputStream in = new FileInputStream(file);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                if (speedParam != null && !speedParam.isEmpty()) {
                    try {
                        speed = Integer.parseInt(speedParam);
                    } catch (NumberFormatException e) {
                        speed = 0;
                    }
                }

                long startTime = System.currentTimeMillis();
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);

                    if (speed > 0) {
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        long desiredDelay = (bytesRead / 1024) * 1000 / speed;
                        if (elapsedTime < desiredDelay) {
                            try {
                                Thread.sleep(desiredDelay - elapsedTime);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        startTime = System.currentTimeMillis();
                    }
                }
            }
        } else {
            response.setContentType("text/html");
            response.getWriter().println("File not found: " + fileName);
        }
    }
}
