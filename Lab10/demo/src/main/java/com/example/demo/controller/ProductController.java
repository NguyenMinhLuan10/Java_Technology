package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> listProducts(){
        return productService.listProduct();
    }


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProductById(id);
    }


    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductPartially(@PathVariable Long id, @RequestBody Product partialProduct) {
        Product existingProduct = productService.findProductById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (partialProduct.getCode() != null) {
            existingProduct.setCode(partialProduct.getCode());
        }
        if (partialProduct.getName() != null) {
            existingProduct.setName(partialProduct.getName());
        }
        if (partialProduct.getPrice() != 0) {
            existingProduct.setPrice(partialProduct.getPrice());
        }
        if (partialProduct.getIllustration() != null) {
            existingProduct.setIllustration(partialProduct.getIllustration());
        }
        if (partialProduct.getDescription() != null) {
            existingProduct.setDescription(partialProduct.getDescription());
        }
        productService.addProduct(existingProduct);
        return new ResponseEntity<>(existingProduct, HttpStatus.OK);
    }


}
