package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order addOrder(Order order) {
        double totalPrice = 0;

        for (OrderProduct orderProduct : order.getProductList()) {
            Product product = productRepository.findById(orderProduct.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + orderProduct.getProduct().getId()));
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            totalPrice += product.getPrice() * orderProduct.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);

        if (existingOrderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }

        Order existingOrder = existingOrderOpt.get();
        existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
        existingOrder.setOrderTime(LocalDateTime.now());

        double totalPrice = 0;
        for (OrderProduct orderProduct : updatedOrder.getProductList()) {
            Product product = productRepository.findById(orderProduct.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + orderProduct.getProduct().getId()));
            orderProduct.setProduct(product);
            orderProduct.setOrder(existingOrder);
            totalPrice += product.getPrice() * orderProduct.getQuantity();
        }

        existingOrder.setProductList(updatedOrder.getProductList());
        existingOrder.setTotalPrice(totalPrice);
        return orderRepository.save(existingOrder);
    }

    public String deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
        return "Order deleted successfully!";
    }
}
