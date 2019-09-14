package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.repositories.specifications.ProductSpecifications;
import com.geekbrains.septembermarket.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String showProducts(Model model,
                               @RequestParam(name = "word", required = false) String word,
                               @RequestParam(name = "min", required = false) Integer min,
                               @RequestParam(name = "max", required = false) Integer max,
                               @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                               @RequestParam(name = "pageIn", required = false) Integer pageIn
    ) {
        Specification<Product> spec = Specification.where(null);
        StringBuilder filtersBuilder = new StringBuilder();
        if (word != null && !word.isEmpty()) {
            spec = spec.and(ProductSpecifications.titleContains(word));
            filtersBuilder.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(min));
            filtersBuilder.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(max));
            filtersBuilder.append("&max=" + max);
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if(pageIn == null){
            pageIn = 2;
        }

        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", filtersBuilder.toString());
        model.addAttribute("pageIn", pageIn);

        Page<Product> page = productsService.findAllByPagingAndFiltering(spec, PageRequest.of(pageNumber - 1, pageIn, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        Product product = productsService.findById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String addNewProduct(Model model){
        Product product = new Product();
        model.addAttribute(product);
        return "add_product";
    }

    @PostMapping("/addNew")
    public String addNewProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

}
