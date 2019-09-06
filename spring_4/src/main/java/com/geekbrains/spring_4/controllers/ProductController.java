package com.geekbrains.spring_4.controllers;

import com.geekbrains.spring_4.entities.Product;
import com.geekbrains.spring_4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/getProducts", method = RequestMethod.GET)
    public String showProductsPage(Model model, @RequestParam String sort){
        List<Product> productList = productService.findAll(sort);
        model.addAttribute("products", productList);
        return "allProducts";
    }

}
