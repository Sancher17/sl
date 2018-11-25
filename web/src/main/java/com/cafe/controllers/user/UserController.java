package com.cafe.controllers.user;

import com.cafe.model.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/showForm")
    private String showForm(Model model){
        User user1 = new User();
        user1.setLogin("123");
        user1.setPassword("123");
        model.addAttribute("user", user1);
        return "user/user-form";
    }

    @RequestMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult){

        System.out.println("User - " +
                user.getFirstName() + " " +
                user.getLastName() + " " +
                user.getLogin() + " " +
                user.getPassword());

        if (bindingResult.hasErrors()){
            return "user/user-form";
        }else {
            return "user/user-confirmation";
        }
    }
}
