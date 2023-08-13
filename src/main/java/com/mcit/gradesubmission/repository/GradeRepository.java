package com.mcit.gradesubmission.repository;

import com.mcit.gradesubmission.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeRepository {
    private List<Grade> studentGrade = new ArrayList<>();
    public Grade getGrade(int index){
        return studentGrade.get(index);
    }
    public void addGrade(Grade grade){
        studentGrade.add(grade);
    }
    public void updateGrade(Grade grade, int index){
        studentGrade.set(index, grade);
    }
    public List<Grade> getAllGrade(){
        return studentGrade;
    }
}
