package com.sportyshoes.controllers;

import com.sportyshoes.models.Category;
import com.sportyshoes.models.Product;
import com.sportyshoes.models.ProductForm;
import com.sportyshoes.models.User;
import com.sportyshoes.security.SecurityUser;
import com.sportyshoes.security.UserRegistrationForm;
import com.sportyshoes.security.UserRepositoryUserDetailsService;
import com.sportyshoes.services.CategoryService;
import com.sportyshoes.services.ProductService;
import com.sportyshoes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {

    private final UserRepositoryUserDetailsService userRepositoryUserDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(UserRepositoryUserDetailsService userRepositoryUserDetailsService,
                           UserService userService,
                           PasswordEncoder passwordEncoder,
                           ProductService productService,
                           CategoryService categoryService) {
        this.userRepositoryUserDetailsService = userRepositoryUserDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin_dashboard")
    public String getAdminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin_edit_profile")
    public String editUserForm(Authentication authentication, Model model, javax.servlet.http.HttpServletRequest request) {
        if (userRepositoryUserDetailsService.isUserAuthenticated()) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            Long adminUserId = securityUser.getUser().getId();
            User adminUser = userService.getUserById(adminUserId);
            HttpSession session = request.getSession();
            session.setAttribute("adminUserToUpdate", adminUser);
            model.addAttribute("admin", adminUser);
            return "admin-edit-profile";
        }
        return "user-login";
    }

    // ToDo Validation
    @PostMapping("/admin_process_update")
    public String processUpdate(UserRegistrationForm form, javax.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        User adminUserToUpdate = (User) session.getAttribute("adminUserToUpdate");
        userService.updateUser(adminUserToUpdate, form.toUser(passwordEncoder));
        return "redirect:/user_edit_profile_confirm";
    }

    @GetMapping("/admin_edit_profile_confirm")
    public String editConfirm() {
        return "admin-edit-profile-confirm";
    }

    @ModelAttribute(name = "productList")
    public List<Product> populateProducts() {
        return this.productService.getAllProducts();
    }

    @ModelAttribute(name = "categoryList")
    public List<Category> populateCategories() {
        return this.categoryService.getAllCategories();
    }

    @ModelAttribute(name = "admin")
    public String mapCat(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return null;
    }

    @ModelAttribute(name = "mapCategories")
    public HashMap<Long, String> mapCategories() {
        List<Product> list = productService.getAllProducts();
        // use MAP to map the category names to product rows
        HashMap<Long, String> mapCats = new HashMap<Long, String>();
        for (Product product : list) {
            Category category = categoryService.getCategoryById(product.getCategoryId());
            if (category != null)
                mapCats.put(product.getId(), category.getName());
        }
        return mapCats;
    }

    @GetMapping(path = "/admin_list_products")
    public String getAllProducts() {
        return "admin-product-list";
    }

    @GetMapping(path = "/list_products_by_category")
    public String getProductsByCategory(Model model, HttpServletRequest request) {
        Long categoryId = Long.valueOf(request.getParameter("categoryId"));
        System.out.println("ID: " + categoryId);
        model.addAttribute("productList", productService.getProductsByCategoryId(categoryId));
        return "admin-product-list";
    }

    @GetMapping(path = "/admin_edit_product")
    public String editProduct(ModelMap map,
                              @RequestParam(value = "id", required = true) String productId,
                              javax.servlet.http.HttpServletRequest request,
                              Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.getUser().getId();
        User user = userService.getUserById(userId);
        if ((userRepositoryUserDetailsService.isUserAuthenticated()) && (user.getRole().equals("ROLE_ADMIN"))) {
            long idValue = Long.parseLong(productId);
            Product productToUpdate = new Product();
            productToUpdate.setCategoryId(1L);
            if (idValue > -1) {
                productToUpdate = productService.getProductById(idValue);
            }
            HttpSession session = request.getSession();
            session.setAttribute("productToUpdate", productToUpdate);
            map.addAttribute("productToUpdate", productToUpdate);
            return "admin-edit-product";
        }
        return "user-login";
    }

    @PostMapping("/admin_process_product_update")
    public String processProductUpdate(ProductForm form, javax.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        Product productToUpdate = (Product) session.getAttribute("productToUpdate");
        productService.updateProduct(productToUpdate, form.toProduct());
        return "redirect:/admin_list_products";
    }

    @GetMapping(path = "/admin_delete_product")
    public String deleteProduct(@RequestParam(value = "id", required = true) String productId,
                                javax.servlet.http.HttpServletRequest request,
                                Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.getUser().getId();
        User user = userService.getUserById(userId);
        if ((userRepositoryUserDetailsService.isUserAuthenticated()) && (user.getRole().equals("ROLE_ADMIN"))) {
            long idValue = Long.parseLong(productId);
            if (idValue > -1) {
                productService.deleteProduct(idValue);
            }
            return "redirect:/admin_list_products";
        }
        return "user-login";
    }

    @GetMapping(path = "/admin_list_categories")
    public String getAllCategories() {
        return "admin-category-list";
    }

}
