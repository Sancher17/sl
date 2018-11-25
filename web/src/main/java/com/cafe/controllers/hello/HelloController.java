package com.cafe.controllers.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/showForm")
    private String showForm(){
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloWorld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){
        String theName = request.getParameter("studentName");
        theName = theName.toUpperCase();
        String result = "Yo! " + theName;
        model.addAttribute("message", result);
        return "helloWorld";
    }

    @RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(
            @RequestParam("studentName") String theName,
            Model model){
//        String theName = request.getParameter("studentName"); //теперь эта строка не нужна т.к. сделали автобиндинг
        theName = theName.toUpperCase();
        String result = "Hey my friend from v3! " + theName;
        model.addAttribute("message", result);
        return "helloWorld";
    }
}
