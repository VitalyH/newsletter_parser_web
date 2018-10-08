package com.vitaly.h.newsletterparserweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ParserController {

    @Autowired
    private ParserService parserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderFormsAndButtons(ModelMap model) {
        model.put("inputField", parserService.getInputField());
        model.put("outputField", parserService.getOutputField());
        return "parser";
   }

    @RequestMapping(value = "/", method= RequestMethod.POST)
    public String runParser(@RequestParam String inputField) {
        ParserModel parserModel = new ParserModel(inputField);
        parserService.runParser(parserModel);
        return "redirect:/";
    }

    @RequestMapping(value = "/clear-all", method= RequestMethod.GET)
    public String clearAll() {
        parserService.clearAll();
        return "redirect:/";
    }
}
