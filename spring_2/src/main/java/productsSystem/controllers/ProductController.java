package productsSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import productsSystem.models.Product;
import productsSystem.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("products", productService.getAll());
        return "productsList";
    }


    @RequestMapping(path="/showOneProduct", method= RequestMethod.GET)
    public String showOne(Model model, @RequestParam long id){
        Product product = productService.getOne(id);
        model.addAttribute("product", product);
        return "productById";
    }


    @RequestMapping(path="/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addOne(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productFromServer", new Product());
        modelAndView.setViewName("addProduct");
        return modelAndView;
    }

    @RequestMapping(value = "/addThis", method = RequestMethod.POST)
    public @ResponseBody
    String checkUser(@ModelAttribute("productFromServer") Product product) {
        productService.setOne(product);

        return product.toString();
    }

}
