package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.repositories.specifications.ProductSpecifications;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.utils.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

//    @GetMapping()
//    public String showProducts(Model model, HttpServletRequest request,
//                               @RequestParam(name = "word", required = false) String word,
//                               @RequestParam(name = "min", required = false) Integer min,
//                               @RequestParam(name = "max", required = false) Integer max,
//                               @RequestParam(name = "pageNumber", required = false) Integer pageNumber
//    ) {
//        ProductsFilter productsFilter = new ProductsFilter(request);
//        if (pageNumber == null || pageNumber < 1) {
//            pageNumber = 1;
//        }
//        model.addAttribute("pageNumber", pageNumber);
//        model.addAttribute("filters", productsFilter.getFiltersString());
//        Page<Product> page = productsService.findAllByPagingAndFiltering(productsFilter.getSpecification(), PageRequest.of(pageNumber - 1, 10, Sort.Direction.ASC, "id"));
//        model.addAttribute("page", page);
//        return "products";
//    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product = null;
        if (id != null) {
            product = productsService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }
}
