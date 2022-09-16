package com.spring.customerproductsales.controller;

import com.spring.customerproductsales.dto.AdminDto;
import com.spring.customerproductsales.model.Admin;
import com.spring.customerproductsales.services.AdminService;
import com.spring.customerproductsales.services.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("/index")
    public String home(Model model){
        model.addAttribute("title", "Home");
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if((authentication == null || authentication instanceof AnonymousAuthenticationToken)){
             return "redirect:/login";
         }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto, BindingResult result, Model model){

        try{
//                session.removeAttribute("message");
                if(result.hasErrors()) {
                    model.addAttribute("adminDto" , adminDto);
                    result.toString();
                    return "redirect:/register";
                }

                String username = adminDto.getUsername();
                Admin admin = adminService.findByUsername(username);
                if(admin != null){
                    model.addAttribute("adminDto", adminDto);
                     model.addAttribute("emailError", "Email is already been registered" );
                    return "register";
                }

                if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                    adminServiceImpl.save(adminDto);
                    System.out.println("success");
                    model.addAttribute("adminDto", adminDto);
                    model.addAttribute("success", "Registered Successfully");
                }else{
                    model.addAttribute("adminDto", adminDto);
                    System.out.println("Password not same");
                    model.addAttribute("passwordError", "Password is not same");
                    return "register";
                }
            } catch (Exception e) {
            model.addAttribute("errors", "Error : something went wrong");
        }

        return "register";
    }

}
