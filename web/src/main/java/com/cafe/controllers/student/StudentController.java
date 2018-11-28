package com.cafe.controllers.student;

import com.cafe.api.services.IUsersService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.Student;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IUsersService usersService;

    @RequestMapping("/showForm")
    public String showForm(Model model){
        Student student = new Student();
//        List<User> list = usersService.getAll();
//        User user = list.get(0);
        model.addAttribute("student",  student);
        return "student-form";
    }

//    @RequestMapping(value = "/getUser", method= RequestMethod.GET)
//    public List<UserDto> ormFindAllUsers(Model model) {
//        // TODO: 27.11.2018 пример
//        List<User> users = usersService.getAll();
//        List<UserDto> usersdto = usersService.getAll()
//                .stream()
//                .map(UserDto::new)
//                .collect(Collectors.toList());
//        model.addAttribute("userAttribute", usersdto);
//        return usersdto;
//    }

    @RequestMapping(value = "/getUser", method= RequestMethod.GET)
    public String ormFindAllUsers(Model model) {
        // TODO: 27.11.2018 пример
        List<User> users = usersService.getAll();
        List<UserDto> usersdto = usersService.getAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute("userAttribute", usersdto);
        return "user/user";
    }


    @RequestMapping("/processForm")
    public String processFrom(@ModelAttribute("student") User user){
        System.out.println("Student " + user.getFirstName() +
                " " + user.getLastName());
        return "student-confirmation";
    }

}
