package com.example.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServlet extends HttpServlet {

    // Thư mục upload file
    private static final String UPLOAD_DIR = "uploads";

    // Các định dạng file hợp lệ
    private static final String[] VALID_EXTENSIONS = {"txt", "doc", "docx", "img", "pdf", "rar", "zip"};
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển tiếp yêu cầu đến file upload.jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/upload.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo thư mục upload nếu chưa tồn tại
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Nhận file từ request
        Part filePart = request.getPart("file");
        String fileName = getSubmittedFileName(filePart);
        String saveFileName = request.getParameter("fileName");

        // Kiểm tra đuôi file hợp lệ
        if (!isValidExtension(fileName)) {
            response.getWriter().write("Unsupported file extension");
            return;
        }

        // Kiểm tra nếu file đã tồn tại
        File file = new File(uploadPath + File.separator + saveFileName);
        boolean fileExists = file.exists();
        boolean override = request.getParameter("override") != null;

        if (fileExists && !override) {
            response.getWriter().write("File already exists");
            return;
        }

        // Lưu file vào thư mục upload
        filePart.write(uploadPath + File.separator + saveFileName);

        // Phản hồi sau khi tải lên thành công
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (fileExists && override) {
            out.println("<h3>File has been overridden</h3>");
        } else {
            out.println("<h3>File has been uploaded</h3>");
        }
        out.println("<a href='" + request.getContextPath() + "/" + UPLOAD_DIR + "/" + saveFileName + "'>Click here to visit file</a>");
    }

    // Lấy tên file gốc từ phần file upload
    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("content-disposition");
        for (String content : header.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // Kiểm tra định dạng file hợp lệ
    private boolean isValidExtension(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        for (String validExt : VALID_EXTENSIONS) {
            if (validExt.equals(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
