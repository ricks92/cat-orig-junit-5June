package com.hsc.cat;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hsc.cat.TO.SearchSkillTOList;
import com.hsc.cat.TO.SkillTO;
import com.hsc.cat.VO.AddSkillVO;
import com.hsc.cat.controller.SkillController;
import com.hsc.cat.entity.Skill;
import com.hsc.cat.service.SkillService;
import com.hsc.cat.utilities.JSONOutputModel;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SkillController.class, secure = false)
public class SkillControllerTest {

	@Rule
	public Timeout globalTimeout = Timeout.millis(1000); 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SkillService skillService;
	
	
	
	@Test
	public void addSkill() throws Exception{
		
		SkillTO mockSkillTO=new SkillTO(37,"Lucene","Lucene");
		JSONOutputModel mockResponseTO=new JSONOutputModel(200,mockSkillTO,"Skills saved successfully");
		String exampleCourseJson="{\"skillName\":\"Lucene\",\"description\":\"Lucene\"}";

		Mockito.when(skillService.addSkill(Mockito.any(AddSkillVO.class))).thenReturn(mockSkillTO);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/skills")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		
		String expected="{\"status\":201,\"data\":{\"skillId\":37,\"skillName\":\"Lucene\",\"description\":\"Lucene\"},\"message\":\"Skills saved successfully\"}";
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void fetchAllSkills() throws Exception{
		SkillTO mockSkillTO=new SkillTO(1,"Python","Python");
		List<SkillTO> skillTOList=new ArrayList<>();
		skillTOList.add(mockSkillTO);
		JSONOutputModel mockResponseTO=new JSONOutputModel(200,skillTOList,"Skills fetched successfully");
		Mockito.when(skillService.fetchAllSkills()).thenReturn(skillTOList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/cat/skills").accept(
				MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("****"+result.getResponse().getContentAsString());
		
		String expected="{\"status\":1,\"data\":[{\"skillId\":1,\"skillName\":\"Python\",\"description\":\"Python\"}],\"message\":\"Skills fetched successfully\"}";
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void searchSkill() throws Exception{
		
		SearchSkillTOList searchSkillTO = new SearchSkillTOList();
		searchSkillTO.setExists(true);
		Skill skill = new Skill();
		skill.setSkillId(37);
		skill.setSkillName("Lucene");
		skill.setDescription("Lucene");
		skill.setCreationDate(new Date());
		skill.setUpdationDate(new Date());
		
		List<Skill> skills=new ArrayList<>();
		skills.add(skill);
		searchSkillTO.setSkills(skills);
		
		JSONOutputModel mockResponseTO=new JSONOutputModel(200,searchSkillTO,"Skill already exists!!!");
		
		Mockito.when(skillService.skillExists(Mockito.anyString())).thenReturn(searchSkillTO);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/searchSkill/Lucene").accept(
				MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		
		
		String expected="{\"status\":1,\"data\":[{\"skillId\":37,\"skillName\":\"Java\",\"description\":\"Java\",\"creationDate\":1528050600000,\"updationDate\":1528050600000}],\"message\":\"Skill already exists!!!\"}";
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
	}
	
	
	
	
}
