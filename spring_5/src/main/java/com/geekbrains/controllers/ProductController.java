package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.specifications.ProductSpecs;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

//    @RequestMapping(path = "/getProducts", method = RequestMethod.GET)
//    public String showProductsPage(Model model, @RequestParam String sort){
//        List<Product> productList = productService.findAll(sort);
//        model.addAttribute("products", productList);
//        return "allProducts";
//    }


//    @RequestMapping(name = "/getPage", method = RequestMethod.GET)
//    public String showPage(Model model, @RequestParam(required = false) Integer page) {
//        int size = 5;
//        Integer needPage;
//        Integer prevPage = null;
//        Integer nextPage = null;
//        int maxPage;
//        if (page == null){
//            needPage = 1;
//        } else needPage = page;
//
//        long counts = productService.amountNotes();
//        if(counts % size == 0){
//            maxPage = (int)(counts/size);
//        } else maxPage = (int)(counts/size) + 1;
//
//        System.out.println(counts);
//
//        if(needPage != 1) prevPage = needPage - 1;
//        if (needPage != maxPage) nextPage = needPage++;
//
//
//        model.addAttribute("page", needPage);
//        model.addAttribute("prevPage", prevPage);
//        model.addAttribute("nextPage", nextPage);
//        model.addAttribute("products", productService.findPage(needPage, size));
//
//        return "pages";
//    }
    @GetMapping("/getPage")
    public String customersPage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("products", productService.findPage(page, size));
        return "productsPages";
    }

    @RequestMapping("/getFilterAndPage")
    public String filterPages(HttpServletRequest request, Model model){
        int page = 0;
        int size = 5;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        Specification<Product> specification = Specification.where(null);
        if(request.getParameter("min") != null){
            double k = Double.parseDouble(request.getParameter("min"));
            specification = specification.and(ProductSpecs.costGreaterOrEq(k));
        }
        if(request.getParameter("max") != null){
            specification = specification.and(ProductSpecs.costLesserOrEq(Integer.parseInt(request.getParameter("max"))));
        }
        Page<Product> pageProd = productService.findPagesFilterAndPaging(page, size, specification);
        model.addAttribute("products", pageProd);
        return "productsPages";
        //return "allProducts";
    }
}
