package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.util.Part_9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Part_9_Controller {

    private final Part_9 part9;

    @Autowired
    public Part_9_Controller(Part_9 part9) {
        this.part9 = part9;
    }

    @GetMapping("/part9")
    public ModelAndView getMessage() {
        String message = part9.getMessage();
        System.out.println("Part_9 instance: " + part9);

        ModelAndView modelAndView = new ModelAndView("part9");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
