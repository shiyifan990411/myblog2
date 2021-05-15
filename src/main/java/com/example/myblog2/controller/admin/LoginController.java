package com.example.myblog2.controller.admin;

import com.example.myblog2.pojo.User;
import com.example.myblog2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 登陸控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @GetMapping("/login")
    public String LoginPage1() {
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user!=null){
            //安全起見
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用戶名密碼錯誤");
            System.out.println(username+"---"+password);
            return "redirect:/admin/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/admin/login";

    }

}
