package com.mcit.gradesubmission.service;

import com.mcit.gradesubmission.Constant;
import com.mcit.gradesubmission.Grade;
import com.mcit.gradesubmission.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeService {
    GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade getGrade(int index){
        return gradeRepository.getGrade(index);
    }
    public void addGrade(Grade grade){
        gradeRepository.addGrade(grade);
    }
    public void updateGrade(Grade grade, int index){
        gradeRepository.updateGrade(grade, index);
    }
    public List<Grade> getAllGrade(){
        return gradeRepository.getAllGrade();
    }
    public int getGradeIndex(String id){
        for (int i =0; i < getAllGrade().size(); i++){
            if (getAllGrade().get(i).getId().equals(id))
                return i;
        }
        return Constant.NOT_FOUND;
    }
    public Grade getGradeById(String id){
        int index = getGradeIndex(id);
        return index == Constant.NOT_FOUND ? new Grade() : getGrade(index);
    }
    public void submitGrade(Grade grade){
        int index = getGradeIndex(grade.getId());
        if (index == Constant.NOT_FOUND){
            addGrade(grade);
        }
        else {
            updateGrade(grade, index);
        }
    }
}
