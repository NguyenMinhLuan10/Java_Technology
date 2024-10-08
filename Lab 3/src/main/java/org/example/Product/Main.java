package org.example.Product;

import org.example.ProductDAO.ManufactureDAO;
import org.example.ProductDAO.PhoneDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();

//        Manufacture manufacture = new Manufacture("Apple Inc.", "Cupertino, USA", 10000, null);
        Manufacture manufacture1 = new Manufacture("Xiaomi.", "US", 15000, null);
        manufactureDAO.add(manufacture1);

//        if (manufactureDAO.add(manufacture)) {
//            System.out.println("Đã thêm Manufacture: " + manufacture);
//        } else {
//            System.out.println("Thêm Manufacture không thành công.");
//        }
////
//        Phone phone1 = new Phone("iPhone 14", 1200, "Black", "USA", 20, manufacture);
//        Phone phone2 = new Phone("Samsung Galaxy S22", 1000, "White", "South Korea", 15, manufacture);
//        Phone phone3 = new Phone("Metavertu", 81000000, "Black", "UK", 15, manufacture);
//        Phone phone4 = new Phone("Metavertu1", 82000000, "Pink", "UK", 15, manufacture);
//        if (phoneDAO.add(phone4)) {
//            System.out.println("Đã thêm các điện thoại: " + phone4 );
//        } else {
//            System.out.println("Thêm điện thoại không thành công.");
//        }
//
//        List<Manufacture> manufactures = manufactureDAO.getAll();
//        System.out.println("Danh sách Manufacture:");
//        for (Manufacture m : manufactures) {
//            System.out.println(m);
//        }
//
//        List<Phone> phones = phoneDAO.getAll();
//        System.out.println("Danh sách điện thoại:");
//        for (Phone p : phones) {
//            System.out.println(p);
//        }
//        manufacture.setName("Apple Inc. Updated");
//        if (manufactureDAO.update(manufacture)) {
//            System.out.println("Đã cập nhật Manufacture: " + manufacture);
//        } else {
//            System.out.println("Cập nhật Manufacture không thành công.");
//        }
//
//        boolean isRemoved = phoneDAO.remove(phone1.getId());
//        if (isRemoved) {
//            System.out.println("Đã xóa điện thoại: " + phone1);
//        } else {
//            System.out.println("Không tìm thấy điện thoại để xóa: " + phone1);
//        }
//
//        phones = phoneDAO.getAll();
//        System.out.println("Danh sách điện thoại sau khi xóa:");
//        for (Phone p : phones) {
//            System.out.println(p);
//        }

//        System.out.println("Điện thoại có giá cao nhất" + phoneDAO.getPhoneWithHighestPrice());

//        System.out.println("Danh sách điện thoại theo quốc gia:");
//        List<Phone> phones = phoneDAO.getPhoneCountry();
//        for(Phone phone : phones){
//            System.out.println(phone);
//        }
//        System.out.println(phoneDAO.isPhoneAbove50());

//        System.out.println("Điện thoại có màu hồng và giá trên 15 triệu: " + phoneDAO.getPhonePink());
//        System.out.println("Tất cả nhà máy có trên 100 nhân viên không? " + manufactureDAO.checkMoreThan100());
//        System.out.println("Tổng tất cả nhân viên của nhà máy " + manufactureDAO.sumOfAllEmployees());

        System.out.println(manufactureDAO.getLastManufacturerInUS());
    }
}
