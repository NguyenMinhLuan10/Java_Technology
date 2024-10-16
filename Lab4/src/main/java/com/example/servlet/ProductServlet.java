package com.example.servlet;

import com.example.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        products.add(new Product(1, "Áo khoác", 100));
        products.add(new Product(2, "Áo polo", 200));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        resp.setContentType("application/json;charset=UTF-8");

        if(idParam == null) {
            resp.getWriter().write(gson.toJson(createResponse(0, "Danh sách sản phẩm", products)));
        } else {
            int id = Integer.parseInt(idParam);
            Product product = findProductById(id);
            if(product != null) {
                resp.getWriter().write(gson.toJson(createResponse(0, "Thông tin sản phẩm",product)));
            } else {
                resp.getWriter().write(gson.toJson(createResponse(1, "Không tìm thấy sản phẩm với id: " + id, null)));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        if (findProductById(id) != null) {
            resp.getWriter().write(gson.toJson(createResponse(1, "id đã tồn tại", null)));
            return;
        }

        Product newProduct = new Product(id, name, price);
        products.add(newProduct);
        resp.getWriter().write(gson.toJson(createResponse(0, "Thêm sản phẩm thành công", newProduct)));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = findProductById(id);
        if (product == null) {
            response.getWriter().write(gson.toJson(createResponse(1, "Không tìm thấy sản phẩm với id: " + id, null)));
            return;
        }

        products.remove(product);
        Product updatedProduct = new Product(id, name, price);
        products.add(updatedProduct);
        response.getWriter().write(gson.toJson(createResponse(0, "Cập nhật sản phẩm thành công", updatedProduct)));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        Product product = findProductById(id);
        if (product == null) {
            response.getWriter().write(gson.toJson(createResponse(1, "Không tìm thấy sản phẩm với id: " + id, null)));
            return;
        }

        products.remove(product);
        response.getWriter().write(gson.toJson(createResponse(0, "Xóa sản phẩm thành công", null)));
    }
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private Map<String, Object> createResponse(int statusCode, String message, Object data) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("statusCode", statusCode);
        responseMap.put("message", message);
        responseMap.put("data", data);
        return responseMap;
    }

}
