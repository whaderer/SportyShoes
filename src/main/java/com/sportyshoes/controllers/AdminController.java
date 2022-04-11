package com.sportyshoes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin_dashboard", method = RequestMethod.GET)
    public String getAdminDashboard() {
        return "admin-dashboard";
    }

}
