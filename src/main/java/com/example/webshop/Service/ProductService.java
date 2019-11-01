package com.example.webshop.Service;

import com.example.webshop.Model.Product;
import com.example.webshop.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// annoter som service lag
@Service
public class ProductService {

    // dependency injection af repository
    @Autowired
    ProductRepo productRepo;

    // hent alle personer fra repository
    public List<Product> fetchtAllProducts(){

        return productRepo.fetchAllProducts();

    }

    public void addProduct(Product product){
        productRepo.addProduct(product);
    }

    public void deleteProduct(int id){
        productRepo.deleteProduct(id);
    }

    public Product findProductById(int id){
        return productRepo.findProductById(id);
    }

    public void updateProduct(Product product){
        productRepo.updateProduct(product);
    }
}
