package com.geekbrains.services;


import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public void addOne(Product product){
//        productRepository.save(product);
//    }

//    public List<Product> findAll(String sort) {
//        if (sort.equals("minmax")){
//            return productRepository.findAllByOrderByCostAsc();
//        } else if (sort.equals("maxmin")){
//            return productRepository.findAllByOrderByCostDesc();
//        }
//        return (List<Product>)productRepository.findAll();
//    }

    public Page<Product> findPage(int page, int size){
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> findPagesFilterAndPaging(int page, int size, Specification<Product> specProduct){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(specProduct, pageable);

    }

    public long amountNotes(){
        return productRepository.count();
    }

//    public List<Product> find(){
//        return productRepository.findAllByOrderByCostAsc();
//    }


}
