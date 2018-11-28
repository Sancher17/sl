package com.cafe.controllers.order;


import com.cafe.api.services.IOrderService;
import com.cafe.model.Student;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newOrder")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/showForm")
    public String showForm(Model model){
        Student student = new Student();

        model.addAttribute("student",  student);
        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processFrom(@ModelAttribute("student") User user){
        System.out.println("Student " + user.getFirstName() +
                " " + user.getLastName());
        return "student-confirmation";
    }

}
