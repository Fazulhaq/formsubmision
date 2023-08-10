package com.mcit.gradesubmission;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {
    List<Grade> studentGrade = new ArrayList<>();
    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades",studentGrade);
        return "grades";
    }
    @GetMapping("/")
    public String gradeForm(Model model,@RequestParam(required = false) String id){
        model.addAttribute("grade", getGradeIndex(id) == -1000 ? new Grade() : studentGrade.get(getGradeIndex(id)) );
        return "form";
    }
    @PostMapping("/handleSubmit")
    public String submitGrade(Grade grade){
        int index = getGradeIndex(grade.getId());
        if (index == -1000){
            studentGrade.add(grade);
        }
        else {
            studentGrade.set(index, grade);
        }
        return "redirect:/grades";
    }
    public Integer getGradeIndex(String id){
        for (int i =0; i < studentGrade.size(); i++){
            if (studentGrade.get(i).getId().equals(id))
                return i;
        }
        return -1000;
    }
}
