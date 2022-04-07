package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.Admin;
import com.sportyshoes.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin getUserById(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.orElseThrow(() -> new ProductNotFoundException());
    }
//    @RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
//    public String loginAction(ModelMap map, javax.servlet.http.HttpServletRequest request,
//                              @RequestParam(value="admin_id", required=true) String adminId,
//                              @RequestParam(value="admin_pwd", required=true) String adminPwd)
//    {
//
//        Admin admin = adminService.authenticate(adminId, adminPwd);
//        if (admin == null) {
//            map.addAttribute("error", "Admin login failed");
//            return "admin/login";
//        }
//        // store admin id in session
//        HttpSession session = request.getSession();
//        session.setAttribute("admin_id", admin.getID());
//
//        return "admin/dashboard";
//    }

}
