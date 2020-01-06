package com.dzg.controller;

import com.dzg.pojo.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ActuatorController {

    public static final String template = "Hello,%s";
    public final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name",required = false,defaultValue = "Future")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

}
