package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.util.Part_8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Part_8_Controller {

    private final Part_8 part8;

    @Autowired
    public Part_8_Controller(Part_8 part8) {
        this.part8 = part8;
    }

    @GetMapping("/part8")
    public ModelAndView getMessage() {
        String message = part8.getMessage();
        System.out.println("Part_8 instance: " + part8);

        ModelAndView modelAndView = new ModelAndView("part8");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}

