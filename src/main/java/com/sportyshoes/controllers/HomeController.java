package com.sportyshoes.controllers;

import com.sportyshoes.models.Product;
import com.sportyshoes.services.CategoryService;
import com.sportyshoes.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RequestMapping("/home")
@Controller
public class HomeController {

    private ProductService productService;
    private CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // curl -X POST -H 'content-type:application/json' -d '{"name": "fish"}' http://localhost:8080/home/addProduct

    @PostMapping(path = "/addProduct") // Map ONLY POST Requests
    public @ResponseBody
    String addNewProduct(
            @RequestBody String name,
            @RequestBody BigDecimal price,
            @RequestBody Date dateAdded,
            @RequestBody Long categoryId) {
        // @ResponseBody means the returned String is the response, not a view name
        productService.addProduct(name, price, dateAdded, categoryId);
        return "Saved";
    }

   // curl -X POST -H 'content-type:application/json' -d '{"name": "fish"}' http://localhost:8080/home/addCategory

    @PostMapping(path = "/addCategory") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCategory(
            @RequestBody String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        categoryService.addCategory(name);
        return "Saved";
    }

    @GetMapping(path = "/allProducts")
    public @ResponseBody
    List<Product> getAllProducts() {
        // This returns a JSON or XML with the users
        return productService.getAllProducts();
    }
}
