package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product){
        return repository.save(product);
    }


    public Product findProductById(Long id){
        return repository.findById(id).get();
    }

    public String deleteProductById(Long id){
        repository.deleteById(id);
        return "Delete success";
    }

    public String updateProduct(Long id, Product product){
        Product newProduct = repository.findById(id).get();
        newProduct.setCode(product.getCode());
        newProduct.setDescription(product.getDescription());
        newProduct.setIllustration(product.getIllustration());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        repository.save(newProduct);
        return "Updated success";
    }


    public List<Product> listProduct(){
        return repository.findAll();
    }
}
