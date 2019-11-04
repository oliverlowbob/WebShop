package com.example.webshop.Repository;

import com.example.webshop.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

//annoter som repository - alt i databasehåndtering
@Repository
public class ProductRepo {

    // brug JdbcTemplate
    @Autowired
    JdbcTemplate template;

    public List<Product> fetchAllProducts(){

        //her skal der hentes personer fra databasen
        String sql = "SELECT * FROM product";
        // rowmapper mapper rækker i sql-query'en til en liste af Person
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        // udfør sql query
        return template.query(sql, rowMapper);

    }

    public void addProduct(Product product){
        // prepared statement
        String sql = "INSERT INTO product (id, name, price, description) VALUES (?, ?, ?, ?)";
        // udfør insert med jdbc template
        template.update(sql, product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }

    public void deleteProduct(int id){
        // delete statement
        String sql = "DELETE FROM product WHERE id=?";

        //kald update med delete statement og id
        template.update(sql, id);
    }

    public Product findProductById(int id){
        //sql query der finder person vha. id
        String sql ="SELECT * FROM product WHERE id=?";
        //instantier rowmapper til at mappe query result til person object
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        //udfør query med JdbcTemplate
        return template.queryForObject(sql, rowMapper, id);
    }

    public void updateProduct(Product product){
        //sql statement der opdaterer rækken id med person objektet
        String sql ="UPDATE product SET name=?, price=?, description=? WHERE id=?";
        //udfør update med JdbcTemplate
        template.update(sql, product.getName(), product.getPrice(), product.getDescription(), product.getId());
    }
}
