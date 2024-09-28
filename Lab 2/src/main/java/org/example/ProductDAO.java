package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final String SQL_Select = "select * from sanpham";
    private final String SQL_SelectById = "select * from sanpham where id = ?";
    private final String SQL_Insert = "insert into sanpham values(?,?,?)";
    private final String SQL_Delete = "delete from sanpham where id = ?";
    private final String SQL_Update = "update sanpham set name = ?, price = ? where id = ?";
    private Connection connection;


    public ProductDAO() {}

    public ProductDAO(Connection conn) {
        this.connection = conn;
    }

    public void setConnection(Connection conn) {
        this.connection = conn;
    }

    public Integer add(Product obj) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Insert);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setInt(3, obj.getPrice());
            preparedStatement.executeUpdate();
            return obj.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement selectStatement = connection.prepareStatement(SQL_Select);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                products.add(new Product(id, name, price));
            }
            if(products.isEmpty()) {
                throw new RuntimeException("Product list is empty");
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Product selectById(int id) {
        Product product = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SelectById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                product = new Product(id, name, price);
            }

            if (product == null) {
                throw new RuntimeException("Product not found");
            }
            return product;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Update);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Delete);
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
