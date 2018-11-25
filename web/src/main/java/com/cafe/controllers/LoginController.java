package com.cafe.controllers;

import com.cafe.api.services.IUsersService;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUsersService usersService;

    @RequestMapping("/showLoginForm")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login-form";
    }

    @RequestMapping("/processLogin")
    public String processLogin(@ModelAttribute("login") Login login) {

        List<User> users = usersService.getAll();
        login.setPassword(users.get(0).getPassword());
        login.setUsername(users.get(0).getFirstName());

        System.out.println("Username" + login.getUsername() + "Password " + login.getPassword());
        return "login-confirmation";
    }

//    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
//    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
////                                     @ModelAttribute("login") Login login) {
////        ModelAndView mav;
////        User user = usersService.validateUser(login);
////        if (null != user) {
////            mav = new ModelAndView("welcome");
////            mav.addObject("firstname", user.getFirstName());
////        } else {
////            mav = new ModelAndView("login");
////            mav.addObject("message", "Username or Password is wrong!!");
////        }
////        return mav;
//    }
}
