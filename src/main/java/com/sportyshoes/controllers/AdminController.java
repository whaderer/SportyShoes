package com.sportyshoes.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin_login_error", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        // will return current session if current session exists. If not, it will not create a new session.
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "user-login";
    }

    @RequestMapping(value = "/admin_dashboard", method = RequestMethod.GET)
    public String getAdminDashboard() {
        return "admin-dashboard";
    }

}
