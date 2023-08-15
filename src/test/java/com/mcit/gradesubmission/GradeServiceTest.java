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
import static org.mockito.Mockito.when;

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
}
