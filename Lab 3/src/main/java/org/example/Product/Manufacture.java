package org.example.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)

    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "employee", nullable = false)
    private int employee;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    public Manufacture(String name, String location, int employee, List<Phone> phones) {
        this.name = name;
        this.location = location;
        this.employee = employee;
        if (phones != null) {
            this.phones = phones;
            for (Phone phone : phones) {
                phone.setManufacture(this);
            }
        }
    }

    public Manufacture() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones != null ? phones : new ArrayList<>();
        for (Phone phone : phones) {
            phone.setManufacture(this);
        }
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee='" + employee + '\'' +
                '}';
    }
}
