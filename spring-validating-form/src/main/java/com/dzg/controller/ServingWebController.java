package com.dzg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServingWebController {

    /**
     * 以url传过来的接口----？name=？？？
     * @param name
     * @param model 用于处理前后台的数据传输
     * @return
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name",required = true,defaultValue = "World")String name
                   , Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

}
