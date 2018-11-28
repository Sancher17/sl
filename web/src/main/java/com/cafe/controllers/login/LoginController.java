//package com.cafe.controllers.login;
//
//import com.cafe.api.services.IUsersService;
//import com.cafe.model.Login;
//import com.cafe.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    @Autowired
//    private IUsersService usersService;
//
//    @RequestMapping("/showLoginForm")
//    public String showLoginForm(Model model) {
//        model.addAttribute("login", new Login());
//        return "login-form";
//    }
//
//    @RequestMapping("/processLogin")
//    public String processLogin(@ModelAttribute("login") Login login) {
//
//        List<User> users = usersService.getAll();
//        login.setPassword(users.get(0).getPassword());
//        login.setUsername(users.get(0).getFirstName());
//
//        System.out.println("Username" + login.getUsername() + "Password " + login.getPassword());
//        return "login-confirmation";
//    }
//}
