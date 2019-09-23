package com.geekbrains.septembermarket.controllers;

import com.geekbrains.septembermarket.entities.Product;
import com.geekbrains.septembermarket.services.ProductsService;
import com.geekbrains.septembermarket.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductsService productsService;

    private Cart cart;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", cart.getProducts());
        model.addAttribute("allCost", cart.getAllCost());
        return "cart";
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productsService.findById(id);
        cart.addProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }
    @GetMapping("/delete")
    public void deleteProductToCart(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productsService.findById(id);
        cart.deleteProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }


}
