package com.dzg.controller;

import com.dzg.pojo.Submission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HandleSubmissionController {

    /**
     * 处理表单的提交
     * @param model
     * @return
     */
    @GetMapping("/handle")
    public String subForm(Model model){
        model.addAttribute("submission",new Submission());
        return "submissionForm";
    }

    /**
     * 返回处理过得数据
     *
     * @ModelAttribute 将方法参数或者方法返回值绑定到一个具体的对象模型上，传送到视图层view
     * @param submission
     * @return
     */
    @PostMapping("/handle")
    public String resSubmission(@ModelAttribute Submission submission){
        return "submissionResult";
    }
}
