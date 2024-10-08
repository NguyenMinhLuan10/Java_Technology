package org.example.Product;

import javax.persistence.*;

import javax.persistence.GenerationType;


@Entity
@Table(name = "MobilePhone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "color")
    private String color;

    @Column(name = "country")
    private String country;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manufacture_id", nullable = false)
    private Manufacture manufacture;

    public Phone() {}

    public Phone(String name, int price, String color, String country, int quantity, Manufacture manufacture) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() >= 3 && name.length() <= 128) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must be between 3 and 128 characters");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    // Phương thức toString để dễ dàng kiểm tra
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", quantity='" + quantity + '\'' +
                ", manufacture_id='" + manufacture.getId() + '\'' +
                '}';
    }
}
