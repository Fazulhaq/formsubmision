package com.mcit.gradesubmission;

import com.mcit.gradesubmission.repository.GradeRepository;
import com.mcit.gradesubmission.service.GradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepository;
    @InjectMocks
    private GradeService gradeService;
    @Test
    public void getGradeFromRepoTest(){
        when(gradeRepository.getAllGrade()).thenReturn(Arrays.asList(
                new Grade("Ahmad","Math","A+"),
                new Grade("Mahmood","History","B-")
        ));

        List<Grade> result = gradeService.getAllGrade();

        assertEquals("Ahmad", result.get(0).getName());
    }
    @Test
    public void GradeIndexTest(){
        Grade grade = new Grade("Ahmad","Math","A+");
        when(gradeRepository.getAllGrade()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        int valid = gradeService.getGradeIndex(grade.getId());
        int notFound = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(Constant.NOT_FOUND, notFound);
    }
    @Test
    public void returnGradeById(){
        Grade grade = new Grade("Ahmad","Math","A+");
        when(gradeRepository.getAllGrade()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);
        assertEquals(grade, result);
    }
    @Test
    public void addGradeTest(){
        Grade grade = new Grade("Ahmad","Math","A+");
        when(gradeRepository.getAllGrade()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Mohammad","History","B+");

        gradeService.addGrade(newGrade);
        verify(gradeRepository, times(1)).addGrade(newGrade);

    }
    @Test
    public void updateGradeTest(){
        Grade grade = new Grade("Ahmad","Math","A+");
        when(gradeRepository.getAllGrade()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        grade.setScore("A-");
        gradeService.submitGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(grade, 0);
    }
}
