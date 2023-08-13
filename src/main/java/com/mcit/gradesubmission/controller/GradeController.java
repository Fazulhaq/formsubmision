package com.mcit.gradesubmission.controller;


import com.mcit.gradesubmission.Constant;
import com.mcit.gradesubmission.Grade;
import com.mcit.gradesubmission.repository.GradeRepository;
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
    GradeRepository gradeRepository = new GradeRepository();
    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades",gradeRepository.getAllGrade());
        return "grades";
    }
    @GetMapping("/")
    public String gradeForm(Model model,@RequestParam(required = false) String id){
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constant.NOT_FOUND ? new Grade() : gradeRepository.getGrade(index));
        return "form";
    }
    @PostMapping("/handleSubmit")
    public String submitGrade(@Valid Grade grade, BindingResult result){
        if (result.hasErrors())
            return "form";
        int index = getGradeIndex(grade.getId());
        if (index == Constant.NOT_FOUND){
            gradeRepository.addGrade(grade);
        }
        else {
            gradeRepository.updateGrade(grade, index);
        }
        return "redirect:/grades";
    }
    public int getGradeIndex(String id){
        for (int i =0; i < gradeRepository.getAllGrade().size(); i++){
            if (gradeRepository.getAllGrade().get(i).getId().equals(id))
                return i;
        }
        return Constant.NOT_FOUND;
    }
}
