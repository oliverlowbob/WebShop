package com.example.webshop.Repository;

import com.example.webshop.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends CrudRepository<Product, Integer> {

}
