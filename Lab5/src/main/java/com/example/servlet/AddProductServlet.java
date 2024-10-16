package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceParam = req.getParameter("price");
        HttpSession session = req.getSession(false);

        if(name.isEmpty() || priceParam.isEmpty()){
            session.setAttribute("errorMessage", "Bạn chưa nhập tên hoặc giá");

            System.out.println(req.getAttribute("errorMessage"));
            resp.sendRedirect(req.getContextPath() + "/main");
            return;
        }

        try {
            double price = Double.parseDouble(priceParam);
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);

            ProductDAO dao = new ProductDAO();
            dao.add(product);
            resp.sendRedirect(req.getContextPath() + "/main");
            session.setAttribute("successMessage", "Thêm sản phẩm thành công");

        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/main");
            session.setAttribute("errorMessage", "Lỗi thêm sản phẩm" + e.getMessage());
        }
    }
}
