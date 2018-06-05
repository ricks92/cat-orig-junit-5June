package com.hsc.cat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsc.cat.TO.ResponseTO;
import com.hsc.cat.VO.ChangePasswordVO;
import com.hsc.cat.VO.ForgetPasswordVO;
import com.hsc.cat.VO.ValidateSecurityQuestionVO;
import com.hsc.cat.VO.ValidateUserVO;
import com.hsc.cat.controller.UserController;
import com.hsc.cat.service.UserDetailService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserDetailService userDetailService;
	
	
	
	@Test
	public void validateUser() throws Exception{
		ResponseTO mockResponseTO=new ResponseTO("1","SUCCESS","Role_Employee","Raman","Chawla","e090");
		Mockito.when(userDetailService.checkValidUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(mockResponseTO);
		
//		Mockito.when(
//				userDetailService.checkValidUser()).thenReturn(mockResponseTO);
		String exampleCourseJson = "{\"userName\":\"e090\",\"password\":\"Gopal123\",\"role\":\"ROLE_EMPLOYEE\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/validateUser")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		
		String expected = "{\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\",\"role\":\"Role_Employee\",\"firstName\":\"Raman\",\"lastName\":\"Chawla\",\"userName\":\"e090\"}";
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), true);
		assertEquals(expected, result.getResponse().getContentAsString());

	}
	
	
	@Test
	public void changePassword() throws Exception{
		
		ResponseTO mockResponseTO=new ResponseTO("1","SUCCESS",null,null,null,null);
		Mockito.when(userDetailService.updatePassword(Mockito.any(ChangePasswordVO.class))).thenReturn(mockResponseTO);
		
		String exampleCourseJson = "{\"userName\":\"e0200\",\"password\":\"secret\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/changePassword")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		
		
		String expected = "{\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\",\"role\":null,\"firstName\":null,\"lastName\":null,\"userName\":null}";
		
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void validateSecurityQuestion() throws Exception{
		boolean mockResponse=false;
		
		Mockito.when(userDetailService.validateSecurityQuestion(Mockito.any(ValidateSecurityQuestionVO.class))).thenReturn(mockResponse);
		
		String exampleCourseJson ="{\"empId\":\"e0200\",\"securityQuestion\":\"name of the pet\",\"answer\":\"cat\"}";
		
		//String exampleCourseJson ="{\"securityQuestion\":\"name of the pet\",\"answer\":\"cat\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/validateSecurityQuestion")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		String expected=null;
		if(mockResponse==true)
		 expected ="{\"status\":1,\"data\":\"match found\",\"message\":\"Security question validated\"}";
		else
		 expected ="{\"status\":0,\"data\":\"match not found\",\"message\":\"Please enter correct answer!\"}";
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void validateSecurityQuestionForNullEmpId() throws Exception{
		
		
		String exampleCourseJson ="{\"securityQuestion\":\"name of the pet\",\"answer\":\"cat\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cat/validateSecurityQuestion")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("****"+result.getResponse().getContentAsString());
		String expected="{\"status\":0,\"data\":\"empid cannot be null\",\"message\":\"Please enter correct empid!\"}";
		
		
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	
@Test
public void forgetPassword()  throws Exception{
	ResponseTO mockResponseTO=new ResponseTO("1","SUCCESS",null,null,null,null);
	Mockito.when(userDetailService.forgetPasswordForAll(Mockito.any(ForgetPasswordVO.class))).thenReturn(mockResponseTO);
	String exampleCourseJson ="{\"empId\":\"e0200\",\"securityQuestion\":\"name of the pet\",\"answer\":\"cat\"}";
	
	
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/cat/forgetPassword")
			.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
			.contentType(MediaType.APPLICATION_JSON);
	
	
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
	System.out.println("****"+result.getResponse().getContentAsString());
	
	String expected = "{\"responseCode\":\"1\",\"responseMessage\":\"SUCCESS\",\"role\":null,\"firstName\":null,\"lastName\":null,\"userName\":null}";
	
	assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	assertEquals(expected, result.getResponse().getContentAsString());
	
	}
}
