package com.cafe.controllers;

import com.cafe.api.services.IUsersService;
import com.cafe.model.Student;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IUsersService usersService;

    @RequestMapping("/showForm")
    public String showForm(Model model){
        Student student = new Student();
        List<User> list = usersService.getAll();
        User user = list.get(0);
        model.addAttribute("student",  user);
//        return "student-form";
        return "table";
    }

    @RequestMapping("/processForm")
    public String processFrom(@ModelAttribute("student") User user){

        // log the input data
        System.out.println("Student " + user.getFirstName() +
                " " + user.getLastName());
        return "student-confirmation";
    }

}
