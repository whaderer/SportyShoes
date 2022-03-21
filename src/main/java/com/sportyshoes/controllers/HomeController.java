package com.sportyshoes.controllers;

import com.sportyshoes.models.Category;
import com.sportyshoes.models.Product;
import com.sportyshoes.models.User;
import com.sportyshoes.repositories.UserRepository;
import com.sportyshoes.services.CategoryService;
import com.sportyshoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RequestMapping("/home")
@Controller
public class HomeController {

    private ProductService productService;
    private CategoryService categoryService;
    private UserRepository userRepository;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService, UserRepository userRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    // curl -X GET -H 'content-type:application/json' http://localhost:8080/home/allProducts

 /*
    @GetMapping(path = "/allProducts")
    // The @ResponseBody annotation tells the dispatcher servlet that the controller’s action
    // doesn’t return a view name but the data sent directly in the HTTP response.
    // This returns a JSON or XML with products
    public @ResponseBody
    String getAllProducts() {
       // return productService.getAllProducts();
        return "home";
    }
 */

//    @ModelAttribute("allProducts")
//    public void addProductsToModel(Model model) {
//        List<Product> products = new ArrayList<>();
//        this.productService.getAllProducts().forEach(i -> products.add(i));
//        model.addAttribute(products);
//    }

    // curl -X POST -H 'content-type:application/json' -d '{"name": "fish", "price" : "100", "dateAdded" : "2019-02-03", "categoryId" : "1"}' http://localhost:8080/home/addProduct

    @PostMapping(path = "/addProduct") // Map ONLY POST Requests
    public @ResponseBody
    String addNewProduct(
            @RequestBody Product product
    ) {
        // @ResponseBody means the returned String is the response, not a view name
        productService.addProduct(product);
        return "Saved";
    }

    // curl -X POST -H 'content-type:application/json' -d '{"name": "fish"}' http://localhost:8080/home/addCategory

    @PostMapping(path = "/addCategory") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCategory(
            @RequestBody Category category) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        categoryService.addCategory(category);
        return "Saved";
    }

    @ModelAttribute("allProducts")
    public List<Product> populateFeatures() {
        return this.productService.getAllProducts();
    }

    // accept a java.security.Principal as a parameter.
    @ModelAttribute(name = "user")
    public String user(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return null;
    }

    @GetMapping(path = "/allProducts")
    String getAllProducts(javax.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "index";
    }
}
