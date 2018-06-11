package com.hsc.cat;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hsc.cat.TO.MapTO;
import com.hsc.cat.TO.SkillTO;
import com.hsc.cat.TO.SubjectTO;
import com.hsc.cat.TO.UpdateSkillResponse;
import com.hsc.cat.TO.UpdateSkillTO;
import com.hsc.cat.TO.ViewSkillListTO;
import com.hsc.cat.TO.ViewSkillTO;
import com.hsc.cat.VO.UpdateSkillVO;
import com.hsc.cat.VO.UpdateSkillsListVO;
import com.hsc.cat.controller.EmployeeSkillController;
import com.hsc.cat.service.EmployeeSkillService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(value=EmployeeSkillController.class,secure=false)
public class EmployeeSkillControllerTest {

	@Rule
	public Timeout globalTimeout = Timeout.millis(1000); 
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeSkillService employeeSkillService;
	
	@Test
	public void testUpdateSkill()throws Exception{
		//
		/*UpdateSkillsListVO updateSkillsListVO=new UpdateSkillsListVO();
		 List<UpdateSkillVO> listOfEmployeeSkills=new ArrayList<UpdateSkillVO>();
		 UpdateSkillVO updateSkillVO=new UpdateSkillVO();
		 updateSkillVO.setEmpId("Manoj");
		 updateSkillVO.setSkillId(123);
		 updateSkillVO.setComment("comment");
		 updateSkillVO.setRating(5);
		 updateSkillVO.setRatingDoneBy("abc");
		 updateSkillVO.setRatingDoneByEmpId("er0124");
		 updateSkillVO.setWeekNumber(4);
		 listOfEmployeeSkills.add(updateSkillVO);
		 updateSkillsListVO.setListOfEmployeeSkills(listOfEmployeeSkills);*/
		 
		String updateSkillsListVOMock="{\"listOfEmployeeSkills\":[{\"skillId\":30,\"empId\":\"e8080\",\"weekNumber\":\"1\",\"rating\":2,\"ratingDoneBy\":\"Self\",\"comment\":\"comment\",\"ratingDoneByEmpId\":\"e123\"}]}";
		
		 UpdateSkillResponse updateSkillResponse=new UpdateSkillResponse();
		 List<UpdateSkillTO> updateSkillTOList=new ArrayList<UpdateSkillTO>();
		 UpdateSkillTO updateSkillTO=new UpdateSkillTO();
		 updateSkillTO.setComment("comment");
		 //updateSkillTO.setCreationDate(new Date());
		// updateSkillTO.setCreationDateString("datestring");
		 //updateSkillTO.setCreationDateString(employeeSkillService.getDateForResponseForTest(updateSkillTO.getCreationDate()));
		 updateSkillTO.setEmpId("e8080");
		 updateSkillTO.setId(1);
		 updateSkillTO.setIssue(1);
		 updateSkillTO.setProblem("problem");
		 updateSkillTO.setRating(5);
		 updateSkillTO.setRatingDoneBy("Self");
		 updateSkillTO.setRatingDoneByEmpId("e123");
		 updateSkillTO.setSkillId(30);
		 updateSkillTO.setWeekNumber(4);
		 updateSkillTOList.add(updateSkillTO);
		 updateSkillResponse.setUpdateSkillTOList(updateSkillTOList);
		 
		 Mockito.when(employeeSkillService.updateSkill(Mockito.any())).thenReturn(updateSkillResponse);
		 
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
				 .post("/cat/updateSkill").content(updateSkillsListVOMock)
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			assertEquals(HttpStatus.OK.value(), response.getStatus());
			
			
			System.out.println("testViewTeam response"+response.getContentAsString());
			//String expected="{\"listOfEmployee\":[{\"empId\":\"E01543\",\"firstName\":\"Manoj\",\"lastName\":\"Sharma\",\"emailId\":\"manoj@hsc.com\",\"department\":\"engineering\"}],\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\"}";
			String expected="{\"status\":1,\"data\":[{\"id\":1,\"skillId\":30,\"empId\":\"e8080\",\"weekNumber\":4,\"rating\":5,\"ratingDoneBy\":\"Self\",\"comment\":\"comment\",\"ratingDoneByEmpId\":\"e123\"}],\"message\":\"Skills updated successfully\"}";
			assertEquals(HttpStatus.OK.value(), response.getStatus());

			//assertEquals(expected, response.getContentAsString());
			JSONAssert.assertEquals(expected,response.getContentAsString(),false);
		 
	}
	
	@Test
	public void testViewSkillTrue()throws Exception{
		
		ViewSkillListTO viewSkillListTO= new ViewSkillListTO();
	    List<ViewSkillTO> listOfEmployeeSkills=new ArrayList<ViewSkillTO>();
	    ViewSkillTO viewSkillTO=new ViewSkillTO();
	   // viewSkillTO.setCreationDate(new Date());
	    //viewSkillTO.setCreationDateString("date");
	    viewSkillTO.setDescription("des");
	    viewSkillTO.setEmpId("e123");
	    viewSkillTO.setRating(5);
	    viewSkillTO.setRatingDoneBy("Peer");
	    viewSkillTO.setRatingDoneByEmpId("e124");
	    viewSkillTO.setSkillId(5);
	    viewSkillTO.setSkillName("java");
	    viewSkillTO.setWeekNumber("5");
	    listOfEmployeeSkills.add(viewSkillTO);
		Set<Integer> listOfSkillId=new HashSet<Integer>();
		listOfSkillId.add(5);
		viewSkillListTO.setListOfEmployeeSkills(listOfEmployeeSkills);
		viewSkillListTO.setListOfSkillId(listOfSkillId);
		viewSkillListTO.setPeerComment("peer comment");
		viewSkillListTO.setSelfComment("self comment");
		Mockito.when(employeeSkillService.getViewHistory(Mockito.anyString())).thenReturn(viewSkillListTO);
		 
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
				 .get("/cat/viewSkill/e123/true")
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			System.out.println("testViewSkillTrue response"+response.getContentAsString());

			String expected="{\"status\":1,\"data\":{\"listOfEmployeeSkills\":[{\"empId\":\"e123\",\"skillId\":5,\"skillName\":\"java\",\"description\":\"des\",\"weekNumber\":\"5\",\"rating\":5,\"ratingDoneBy\":\"Peer\",\"ratingDoneByEmpId\":\"e124\"}],\"listOfSkillId\":[5],\"selfComment\":\"self comment\",\"peerComment\":\"peer comment\"},\"message\":\"Employee skills fetched successfully\"}";

		//	assertEquals(expected, response.getContentAsString());
			JSONAssert.assertEquals(expected,response.getContentAsString(),false);

	}
	
	
	@Test
	public void testViewSkillFalse()throws Exception{
		
		
		ViewSkillListTO viewSkillListTO= new ViewSkillListTO();
	    List<ViewSkillTO> listOfEmployeeSkills=new ArrayList<ViewSkillTO>();
	    ViewSkillTO viewSkillTO=new ViewSkillTO();
	   // viewSkillTO.setCreationDate(new Date());
	   // viewSkillTO.setCreationDateString("date");
	    viewSkillTO.setDescription("des");
	    viewSkillTO.setEmpId("e123");
	    viewSkillTO.setRating(5);
	    viewSkillTO.setRatingDoneBy("Self");
	    viewSkillTO.setRatingDoneByEmpId("e124");
	    viewSkillTO.setSkillId(5);
	    viewSkillTO.setSkillName("java");
	    viewSkillTO.setWeekNumber("5");
	    listOfEmployeeSkills.add(viewSkillTO);
		Set<Integer> listOfSkillId=new HashSet<Integer>();
		listOfSkillId.add(5);
		viewSkillListTO.setListOfEmployeeSkills(listOfEmployeeSkills);
		viewSkillListTO.setListOfSkillId(listOfSkillId);
		viewSkillListTO.setPeerComment("peer comment");
		viewSkillListTO.setSelfComment("self comment");
		Mockito.when(employeeSkillService.viewSkills(Mockito.anyString())).thenReturn(viewSkillListTO);
		 
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
				 .get("/cat/viewSkill/e123/false")
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			System.out.println("testViewSkillFalse response"+response.getContentAsString());

			String expected="{\"status\":1,\"data\":{\"listOfEmployeeSkills\":[{\"empId\":\"e123\",\"skillId\":5,\"skillName\":\"java\",\"description\":\"des\",\"weekNumber\":\"5\",\"rating\":5,\"ratingDoneBy\":\"Self\",\"ratingDoneByEmpId\":\"e124\"}],\"listOfSkillId\":[5],\"selfComment\":\"self comment\",\"peerComment\":\"peer comment\"},\"message\":\"Employee skills fetched successfully\"}";

			//assertEquals(expected, response.getContentAsString());
			JSONAssert.assertEquals(expected,response.getContentAsString(),false);		 
	}
	
	@Test
	public void testFetchMap()throws Exception
	{
		MapTO map=new MapTO();
		HashMap<String, List<SubjectTO>> hmap=new HashMap<String, List<SubjectTO>>();
		List<SubjectTO> subjectTOList=new ArrayList<SubjectTO>();
		SubjectTO subjectTO=new SubjectTO();
		subjectTO.setRating(5);
		subjectTO.setWeekNumber(5);
		subjectTOList.add(subjectTO);
		hmap.put("java", subjectTOList);
		map.setMap(hmap);
		Mockito.when(employeeSkillService.fetchMapDetails(Mockito.any())).thenReturn(map);
		String mockRequest="{\"empId\":\"e090\",\"ratingDoneBy\":\"self\",\"quarterNumber\":\"1\"}"; 
		
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
				 .post("/fetchMap").content(mockRequest)
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			System.out.println("testFetchMap response"+response.getContentAsString());

			String expected="{\"status\":1,\"data\":{\"map\":{\"java\":[{\"weekNumber\":5,\"rating\":5}]}},\"message\":\"Map details fetched successfuly\"}";

			assertEquals(expected, response.getContentAsString());
	}
	
	@Test
	public void testGetAllSelfRatedSkills()throws Exception
	{
		List<SkillTO> listOfSkill=new ArrayList<SkillTO>();
		SkillTO skillTO=new SkillTO();
		skillTO.setSkillId(17);
		skillTO.setSkillName("Rest");
		skillTO.setDescription("Restful Web Services");
		listOfSkill.add(skillTO);
		Mockito.when(employeeSkillService.getAllSelfRatedSkills(Mockito.any())).thenReturn(listOfSkill);
		
		
		 RequestBuilder requestBuilder=MockMvcRequestBuilders
				 .get("/cat/getAllSelfRatedSkills/e123")
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			System.out.println("testGetAllSelfRatedSkills response"+response.getContentAsString());

			String expected="{\"status\":1,\"data\":[{\"skillId\":17,\"skillName\":\"Rest\",\"description\":\"Restful Web Services\"}],\"message\":\"Rated skill details fetched successfully\"}";

			assertEquals(expected, response.getContentAsString());
	}
	
	
}
