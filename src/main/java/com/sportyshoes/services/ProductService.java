package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.Product;
import com.sportyshoes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product getProductById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductNotFoundException());
    }

    @Transactional
    public int updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional
    public Product addProduct(String name, BigDecimal price, Date dateAdded, Long categoryId){
        Product p = new Product();
      p.setName(name);
      p.setPrice(price);
      p.setDateAdded(dateAdded);
      p.setCategoryId(categoryId);
       return productRepository.save(p);
    }



//    @Transactional
//    public List<Object> getAllProductsWithJoin() {
//        return productRepository.getAllProductsWithJoin();
//    }

}
