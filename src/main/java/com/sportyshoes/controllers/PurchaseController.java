package com.sportyshoes.controllers;

import com.sportyshoes.models.Product;
import com.sportyshoes.models.Purchase;
import com.sportyshoes.models.PurchaseItem;
import com.sportyshoes.security.UserRepositoryUserDetailsService;
import com.sportyshoes.services.ProductService;
import com.sportyshoes.services.PurchaseItemService;
import com.sportyshoes.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PurchaseController {

    private final ProductService productService;
    private final PurchaseService purchaseService;
    private final PurchaseItemService purchaseItemService;
    private final UserRepositoryUserDetailsService userRepositoryUserDetailsService;

    @Autowired
    public PurchaseController(ProductService productService, PurchaseService purchaseService, PurchaseItemService purchaseItemService, UserRepositoryUserDetailsService userRepositoryUserDetailsService) {
        this.productService = productService;
        this.purchaseService = purchaseService;
        this.purchaseItemService = purchaseItemService;
        this.userRepositoryUserDetailsService = userRepositoryUserDetailsService;
    }

    @GetMapping(path = "/memberpurchases")
    public String memberpurchases(
            Model model,
            Authentication authentication
    ) {
        if (userRepositoryUserDetailsService.isUserAuthenticated()) {
            BigDecimal total = new BigDecimal("0.0");
            Long userId = userRepositoryUserDetailsService.loadUserByUsername(authentication.getName()).getId();
            List<Purchase> purchaseList = purchaseService.getAllPurchasesByUserId(userId);
            // map purchase items to each purchase for display
            HashMap<Long, String> mapItems = new HashMap<Long, String>();
            for (Purchase purchase : purchaseList) {
                System.out.println("Purchase ID: " + purchase.getId());
                total = total.add(purchase.getTotal());
                List<PurchaseItem> purchaseItemList = purchaseItemService.getAllPurchaseItemsByPurchaseId(purchase.getId());
                StringBuilder sb = new StringBuilder("");

                for (PurchaseItem purchaseItem : purchaseItemList) {
                    Product product = productService.getProductById(purchaseItem.getProduct().getId());
                    if (product != null) {
                        sb.append(product.getName()).append(", ").append(purchaseItem.getQuantity()).append(" units @").append(purchaseItem.getRate()).append(" = ").append(purchaseItem.getPrice());
                    }
                }
                mapItems.put(purchase.getId(), sb.toString());
            }
            model.addAttribute("totalAmount", total);
            model.addAttribute("purchaseList", purchaseList);
            model.addAttribute("mapItems", mapItems);
            model.addAttribute("pageTitle", "SPORTY SHOES - YOUR ORDERS");
            return "purchases";
        }
        return "userLogin";
    }

    @GetMapping(path = "/memberpurchases2")
    public String memberpurchases2(
            Model model,
            Authentication authentication
    ) {
        if (userRepositoryUserDetailsService.isUserAuthenticated()) {
            BigDecimal total = new BigDecimal("0.0");
            Long userId = userRepositoryUserDetailsService.loadUserByUsername(authentication.getName()).getId();
            List<Purchase> purchaseList = purchaseService.getAllPurchasesByUserId(userId);
            // map purchase items to each purchase for display
            HashMap<Long, String> mapItems = new HashMap<Long, String>();
            for (Purchase purchase : purchaseList) {
                total = total.add(purchase.getTotal());
                List<PurchaseItem> purchaseItemList = purchaseItemService.getAllPurchaseItemsByPurchaseId(purchase.getId());


//                for (PurchaseItem purchaseItem : purchaseItemList) {
//                    purchaseItemList.add(purchaseItem);
//                }
                model.addAttribute("purchaseItemList", purchaseItemList);

            }
            model.addAttribute("totalAmount", total);
            model.addAttribute("purchaseList", purchaseList);
            model.addAttribute("mapItems", mapItems);
            model.addAttribute("pageTitle", "SPORTY SHOES - YOUR ORDERS");
            return "purchase2";
        }
        return "userLogin";
    }
}

