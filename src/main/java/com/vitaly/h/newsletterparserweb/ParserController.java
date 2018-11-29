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
    private NewsletterParserService newsletterParserService;

    @Autowired
    private EncryptorParserService encryptorParserService;

    // Newsletter
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderFormsAndButtons(ModelMap model) {
        model.put("inputField", newsletterParserService.getInputField());
        model.put("outputField", newsletterParserService.getOutputField());
        return "parser";
   }

    @RequestMapping(value = "/", method= RequestMethod.POST)
    public String runParser(@RequestParam String inputField) {
        ParserModel parserModel = new ParserModel(inputField);
        newsletterParserService.runParser(parserModel);
        return "redirect:/";
    }

    @RequestMapping(value = "/clear-all", method= RequestMethod.GET)
    public String clearAll() {
        newsletterParserService.clearAll();
        return "redirect:/";
    }

    // Encryptor
    @RequestMapping(value = "/1060", method = RequestMethod.GET)
    public String renderEncryptionFormsAndButtons(ModelMap model) {
        model.put("inputField", encryptorParserService.getInputField());
        model.put("outputField", encryptorParserService.getOutputField());
        return "1060";
    }

    @RequestMapping(value = "/1060", method= RequestMethod.POST)
    public String runEncryption(@RequestParam String inputField) {
        ParserModel parserModel = new ParserModel(inputField);
        encryptorParserService.runParser(parserModel);
        return "redirect:/1060";
    }
    @RequestMapping(value = "/1060/wipe", method= RequestMethod.GET)
    public String wipeTheData() {
        encryptorParserService.wipeTheData();
        return "redirect:/1060";
    }
}
