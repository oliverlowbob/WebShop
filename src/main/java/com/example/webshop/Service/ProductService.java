package com.example.webshop.Service;

import com.example.webshop.Model.Product;
import com.example.webshop.Repository.IProductRepo;
import com.example.webshop.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// annoter som service lag
@Service
public class ProductService {

    // dependency injection af repository
    @Autowired
    IProductRepo productRepo;
//    ProductRepo productRepo;


    // hent alle personer fra repository
    public List<Product> fetchtAllProducts(){
        List<Product> products = new ArrayList<>();
        for (Product product : productRepo.findAll())
              {
            products.add(product);
        }
        return products;
    }

//    public void addProduct(Product product){
//        productRepo.addProduct(product);
//    }
public void addProduct(Product product){
    productRepo.save(product);
}


    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }

    public Product findProductById(int id){
        Optional<Product> productOptional = productRepo.findById(id);
        if(!productOptional.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        return productOptional.get();

    }

    public void updateProduct(Product product){
        productRepo.save(product);
    }
}
