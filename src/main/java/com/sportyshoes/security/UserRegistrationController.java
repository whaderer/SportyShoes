package com.sportyshoes.security;

import com.sportyshoes.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "userRegistration";
    }

    @PostMapping("/register")
    public String processRegistration(UserRegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/registerconfirm";
    }

    @GetMapping("/registerconfirm")
    public String registerconfirm() {
        return "register-confirm";
    }
}
