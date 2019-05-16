package com.string.wordsCount.controller;

import com.string.wordsCount.form.ContentForm;
import com.string.wordsCount.service.StringOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StringOperationsController {

    @Value("${welcome.message}")
    private String message;

    @Autowired
    private StringOperationsService stringOperationsService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/wordsCount"}, method = RequestMethod.POST)
    public String wordsCount(Model model, @ModelAttribute("contentForm") ContentForm contentForm) {
        model.addAttribute("result", stringOperationsService.calculateWordsCount(contentForm.getContent()));
        model.addAttribute("content", contentForm.getContent());
        return "result";
    }
}
