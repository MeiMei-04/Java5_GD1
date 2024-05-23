/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AsmGD1.controller;

import com.example.AsmGD1.model.account;
import com.example.AsmGD1.model.user;
import com.example.AsmGD1.repository.accountRepository;
import com.example.AsmGD1.repository.userRepository;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hieu
 */
@Controller
@RequestMapping("/user")
public class userController {

    accountRepository acRepo;
    userRepository uRepo;
    private List<user> listUser = new ArrayList<>();
    boolean islogin = false;

    public userController() {
        acRepo = new accountRepository();
        uRepo = new userRepository();
        listUser = uRepo.fillAll();
    }

    @ModelAttribute("listUser")
    public List<user> fillAll() {
        return listUser;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("ac") account ac,Model model) {
        model.addAttribute("ac", ac);
        return "user/login.html";
    }

    @PostMapping("/login")
    public String login(Model model, @Valid account ac, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> listError = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : listError) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("ac", ac);
            return "user/login.html";
        }
        islogin = acRepo.login(ac);
        if (islogin == false) {
            String text = "Login Fail";
            model.addAttribute("ac", ac);
            model.addAttribute("textFail", text);
            return "user/login.html";
        }
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String list() {
        if (islogin == false) {
            return "redirect:/user/login";
        }
        return "user/list.html";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("u") user u,Model model) {
        if (islogin == false) {
            return "redirect:/user/login";
        }
        model.addAttribute("u", u);
        return "user/add.html";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid user u, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> listError = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : listError) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("u", u);
            return "user/add.html";
        }
        u.setId(uRepo.getId());
        uRepo.create(u);
        return "redirect:/user/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        uRepo.deleteById(id);
        return "redirect:/user/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        if (islogin == false) {
            return "redirect:/user/login";
        }
        user u = uRepo.getuserById(id);
        model.addAttribute("u", u);
        model.addAttribute("id", id);
        return "user/edit.html";
    }

    @PostMapping("update/{id}")
    public String edit(@PathVariable("id") int idupdate, Model model, @Valid user u, BindingResult bindingResult) {
        System.out.println(idupdate);
        System.out.println("12312");
        if (bindingResult.hasErrors()) {
            List<FieldError> listError = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : listError) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("u", u);
            return "user/edit.html";
        }
        u.setId(idupdate);
        uRepo.update(u);
        return "redirect:/user/list";
    }
}
