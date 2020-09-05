package com.dzg.controller;

import com.dzg.pojo.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * 用于验证PersonForm的提交信息是否符合其中的规则
 */
@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm){
        return "form";
    }

    /**
     *
     * @param personForm 收集表单的信息，@Valid 标识，将对信息进行验证
     * @param bindingResults 绑定的结果信息对象
     * @return
     */
    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResults){
        if (bindingResults.hasErrors()){
            return "form";
        }
        return "redirect:/results";
    }
}
