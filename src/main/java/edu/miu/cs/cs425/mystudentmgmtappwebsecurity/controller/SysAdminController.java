package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysAdminController {

    @GetMapping(value = "/secured/systemadmin")
    public String displaySysAdminPage(){
        return "secured/systemadmin";
    }

}
