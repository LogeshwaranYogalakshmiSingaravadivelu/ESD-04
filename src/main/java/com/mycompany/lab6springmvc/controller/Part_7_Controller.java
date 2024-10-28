package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.util.Part_7;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Part_7_Controller implements Controller {

    private Part_7 part7;

    public void setPart7(Part_7 messageUtility) {
        this.part7 = messageUtility;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String message = part7.getMessage();
        System.out.println("Part_7 instance: " + part7);

        ModelAndView modelAndView = new ModelAndView("part7");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}

