package com.cafe.webapp.controllers;

import com.cafe.api.services.IGoodsService;
import com.cafe.model.Goods;
import com.cafe.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/111")
    public String welcome(HttpServletRequest request, Model model){
        List<Goods> list = goodsService.getAll();
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){
        String theName = request.getParameter("studentName");
        theName = theName.toUpperCase();
        String result = "Yo! " + theName;
        model.addAttribute("message", result);
        return "helloWorld";
    }

}
