package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private String url = "null";
    private String user = "null";
    private String password = "null";

    public Main(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Main() {}

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid Connect URL.");
            return;
        }

        String[] urls = args[0].split("\\?");
        String url = urls[0];
        String user = null;
        String password = null;

        if (urls.length > 1) {
            String[] params = urls[1].split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    if ("user".equals(keyValue[0])) {
                        user = keyValue[1];
                    } else if ("password".equals(keyValue[0])) {
                        password = keyValue[1];
                    }
                }
            }
        }

        if (user == null || password == null) {
            System.out.println("User or password is missing.");
            return;
        }

        System.out.println("URL: " + url);
        System.out.println("User: " + user);
        System.out.println("Password: " + password);

        Main main = new Main(url, user, password);

        int choice = 0;
        while (choice != 6) {
            main.showMenu();
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    main.selectAllProduct();
                    break;
                case 2:
                    main.selectById();
                    break;
                case 3:
                    main.addProduct();
                    break;
                case 4:
                    main.updateProduct();
                    break;
                case 5:
                    main.deleteProduct();
                    break;
                default:
                    System.out.println("Bye!");
                    break;
            }
        }
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        int price = sc.nextInt();
        sc.nextLine();

        Product product = new Product(id, name, price);
        if (product != null) {
            System.out.println("Successfully!");
        } else {
            System.out.println("Error");
        }

        Connection connection = getConnection(url, user, password);
        ProductDAO dao = new ProductDAO(connection);
        dao.add(product);
    }

    public void selectAllProduct() {
        Connection connection = getConnection(url, user, password);
        ProductDAO dao = new ProductDAO(connection);
        List<Product> products = dao.readAll();
        printProduct(products);
    }

    public void printProduct(List<Product> products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void selectById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int x = sc.nextInt();
        sc.nextLine();
        Connection connection = getConnection(url, user, password);
        ProductDAO dao = new ProductDAO(connection);
        Product product = dao.read(x);
        if (product != null) {
            product.print();
        } else {
            System.out.println("Product not found");
        }
    }

    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        Connection conn = getConnection(url, user, password);
        ProductDAO dao = new ProductDAO(conn);
        System.out.print("Enter product id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        int price = sc.nextInt();
        sc.nextLine();
        Product product = new Product(id, name, price);
        if (dao.update(product)) {
            System.out.println("Successfully!");
        } else {
            System.out.println("Error");
        }
    }

    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        Connection conn = getConnection(url, user, password);
        ProductDAO dao = new ProductDAO(conn);
        System.out.print("Enter product id: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (dao.delete(id)) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete error");
        }
    }

    public void showMenu() {
        System.out.println("1.Read all products");
        System.out.println("2.Read detail of a product by id");
        System.out.println("3.Add a new product");
        System.out.println("4.Update a product");
        System.out.println("5.Delete a product by id");
        System.out.println("6.Exit");
        System.out.print("Enter your choice: ");
    }
}
