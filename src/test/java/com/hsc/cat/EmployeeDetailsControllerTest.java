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

import com.hsc.cat.TO.EmployeeTO;
import com.hsc.cat.TO.EmployeeUnderManagerDetails;
import com.hsc.cat.TO.ViewTeamTO;
import com.hsc.cat.VO.EmployeeDetailsVO;
import com.hsc.cat.controller.EmployeeDetailsController;
import com.hsc.cat.service.EmployeeDetailService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(value=EmployeeDetailsController.class,secure=false)
public class EmployeeDetailsControllerTest {
	

		@Rule
		public Timeout globalTimeout = Timeout.millis(1000); 
		 

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeDetailService employeeDetailService;
	
	EmployeeTO employeeTOMock=new EmployeeTO("1553","manoj","sharma","engineering",new Date(),new Date(),
			"what","ans1","when","ans2","1990","manoj@hsc.com","approved","nothing");
	
	EmployeeDetailsVO employeeDetailsVOMock1=new EmployeeDetailsVO("1553","Manoj@45","employee","manoj","sharma",
			"engineering","1990","q1","a1","q2","a2","manoj@hsc.com");
	String employeeDetailsVOMock=
"{\"username\":\"1553\",\"password\":\"Manoj@45\",\"role\":\"employee\",\"firstName\":\"Manoj\","
+ "\"lastName\":\"sharma\",\"department\":\"engineering\",\"managerId\":\"123\",\"securityQues1\":\"q1\",\"securityQues2\":\"q2\",\"email\":\"Manoj\"}";
	
	
	

	@Test
	public void testSave() throws Exception{
		Mockito.when(
				employeeDetailService.save(Mockito.any(EmployeeDetailsVO.class)
						)).thenReturn(employeeTOMock);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/employees")
				.accept(MediaType.APPLICATION_JSON).content(employeeDetailsVOMock)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		
	}
	
	@Test
	public void testGetAllEmployees()throws Exception
	{
		List<EmployeeTO> fetchAllEmplList=new ArrayList<EmployeeTO>();
		fetchAllEmplList.add(employeeTOMock);
		
		Mockito.when(employeeDetailService.getAllEmployees()).thenReturn(fetchAllEmplList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/cat/employees")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		String expected="{\"status\":1,\"data\":[{\"empid\":\"1553\",\"firstName\":\"manoj\",\"lastName\":\"sharma\",\"department\":\"engineering\",\"creationDate\":1528111198881,\"updationDate\":1528111198881,\"securityQues1\":\"what\",\"securityAns1\":\"ans1\",\"securityQues2\":\"when\",\"securityAns2\":\"ans2\",\"managerId\":\"1990\",\"email\":\"manoj@hsc.com\",\"approvalStatus\":\"approved\",\"issue\":\"nothing\"}],\"message\":\"Employees fetched successfully\"}";
		
		//assertEquals(expected,result.getResponse().getContentAsString());
		System.out.println("fetchALL Employees method"+result.getResponse().getContentAsString());
	}
	
	@Test
	public void testVerifyManager()throws Exception
	{
		
		Boolean res=Boolean.TRUE;
		Mockito.when(employeeDetailService.updateApprovalStatus(Mockito.anyString(),Mockito.anyString())).thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/verifyManager/E01553/Approved")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
		/*
		String expected="{\"status\":1,\"data\":[{\"empid\":\"1553\",\"firstName\":\"manoj\",\"lastName\":\"sharma\",\"department\":\"engineering\",\"creationDate\":1528111198881,\"updationDate\":1528111198881,\"securityQues1\":\"what\",\"securityAns1\":\"ans1\",\"securityQues2\":\"when\",\"securityAns2\":\"ans2\",\"managerId\":\"1990\",\"email\":\"manoj@hsc.com\",\"approvalStatus\":\"approved\",\"issue\":\"nothing\"}],\"message\":\"Employees fetched successfully\"}";
		*/
		String expected="{\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\",\"role\":null,\"firstName\":null,\"lastName\":null,\"userName\":null}";
		assertEquals(expected,result.getResponse().getContentAsString());
		System.out.println("testVerifyManager method"+result.getResponse().getContentAsString());
	}
	
	@Test
	public void testViewTeam()throws Exception{
		
		ViewTeamTO viewTeamTOMock=new ViewTeamTO();
		viewTeamTOMock.setResponseCode("1");
		viewTeamTOMock.setResponseMessage("SUCCESS");
		List<EmployeeUnderManagerDetails> listOfEmployee=new ArrayList<EmployeeUnderManagerDetails>();
		EmployeeUnderManagerDetails employeeUnderManagerDetails=new EmployeeUnderManagerDetails();
		employeeUnderManagerDetails.setFirstName("Manoj");
		employeeUnderManagerDetails.setLastName("Sharma");
		employeeUnderManagerDetails.setEmpId("E01543");
		employeeUnderManagerDetails.setDepartment("engineering");
		employeeUnderManagerDetails.setEmailId("manoj@hsc.com");
		listOfEmployee.add(employeeUnderManagerDetails);
		viewTeamTOMock.setListOfEmployee(listOfEmployee);
		
		Mockito.when(employeeDetailService.getEmployeeUnderManager(Mockito.anyString())).thenReturn(viewTeamTOMock);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/cat/viewTeam/e0134")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		System.out.println("testViewTeam response"+response.getContentAsString());
		String expected="{\"listOfEmployee\":[{\"empId\":\"E01543\",\"firstName\":\"Manoj\",\"lastName\":\"Sharma\",\"emailId\":\"manoj@hsc.com\",\"department\":\"engineering\"}],\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\"}";
	assertEquals(expected, response.getContentAsString());
	}

}
