package com.mcit.gradesubmission.controller;


import com.mcit.gradesubmission.Constant;
import com.mcit.gradesubmission.Grade;
import com.mcit.gradesubmission.repository.GradeRepository;
import com.mcit.gradesubmission.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {
    GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades",gradeService.getAllGrade());
        return "grades";
    }
    @GetMapping("/")
    public String gradeForm(Model model,@RequestParam(required = false) String id){
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }
    @PostMapping("/handleSubmit")
    public String submitGrade(@Valid Grade grade, BindingResult result){
        if (result.hasErrors())
            return "form";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

}
