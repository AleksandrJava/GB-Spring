package com.geekbrains.spring_4.services;

import com.geekbrains.spring_4.entities.Product;
import com.geekbrains.spring_4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addOne(Product product){
        productRepository.save(product);
    }

    public List<Product> findAll(String sort) {
        if (sort.equals("minmax")){
            return productRepository.findAllByOrderByCostAsc();
        } else if (sort.equals("maxmin")){
            return productRepository.findAllByOrderByCostDesc();
        }
        return (List<Product>)productRepository.findAll();
    }

    public List<Product> find(){
        return productRepository.findAllByOrderByCostAsc();
    }


}
