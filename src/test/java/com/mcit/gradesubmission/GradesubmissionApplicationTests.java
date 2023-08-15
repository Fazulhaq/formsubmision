package com.mcit.gradesubmission;

import com.mcit.gradesubmission.controller.GradeController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradesubmissionApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		System.out.println(mockMvc);
	}
	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"))
				.andExpect(model().attributeExists("grade"));
	}
	@Test
	public void testSuccessfullSubmision() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
				.param("name","Ahmad")
				.param("subject","Math")
				.param("score","A-");

		mockMvc.perform(request)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/grades"));
	}
	@Test
	public void testUnsuccessfullSubmision() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
				.param("name", "    ")
				.param("subject","     ")
				.param("score", "     ");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"));
	}
	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("grades"))
				.andExpect(model().attributeExists("grades"));
	}

}
