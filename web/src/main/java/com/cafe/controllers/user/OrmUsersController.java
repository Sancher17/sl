package com.cafe.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ormuser")
public class OrmUsersController {

    @RequestMapping("/showForm")
    private String showForm(Model model){
        return "user/orm";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return null;
    }
}
