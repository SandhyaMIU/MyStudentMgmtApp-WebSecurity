package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicPagesController {

    @GetMapping(value = "/") //version 4.x+
    public String displayHomepage(){
        return "public/index";
    }

    @GetMapping(value ="/about")
    public String displayAboutPage(){
        return "public/about";
    }

}
