package com.sportyshoes.security;

import com.sportyshoes.models.Admin;
import com.sportyshoes.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminRepositoryAdminDetailService  {

 //   private final AdminRepository adminRepo;

//    @Autowired
//    public AdminRepositoryAdminDetailService(AdminRepository adminRepo) {
//        this.adminRepo = adminRepo;
//    }
//
//    @Override
//    public Admin loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        Admin admin = adminRepo.findByUsername(username);
//        if (admin != null) {
//            return admin;
//        }
//        throw new UsernameNotFoundException(
//                "User '" + username + "' not found");
//    }

//    @Override
//    public Admin loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        Admin admin = adminRepo.findByUsername(username);
//        if (admin != null) {
//            return admin;
//        }
//        throw new UsernameNotFoundException(
//                "Admin '" + username + "' not found");
//    }

    public boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }
}
