package com.demo.Junit.ControllerClassTest;

import org.junit.jupiter.api.Test;

import com.demo.Controller.MemberController;
import com.demo.Model.Member;
import com.demo.Service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(value = MemberController.class)

public class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MemberService memberService;

	@Autowired
	private ObjectMapper objectMapper;

	// JUnit test for Get All member REST API
	@Test
	public void givenListOfMembers_whenGetAllMembers_thenReturnMembersList() throws Exception {
		// given - precondition or setup

		List<Member> list = new ArrayList<Member>();
		Member memOne = new Member(1, "John", LocalDate.now(), "howtodoinjava@gmail.com", "9874563210", "Pune",
				"Maharashtra", "JohnP", "JohnA123.", null);
		Member memTwo = new Member(2, "Jonn", LocalDate.now(), "howtodoin@gmail.com", "9874553210", "Pune", "Maharashtra",
				"JonP", "Jonn123.", null);

		list.add(memOne);
		list.add(memTwo);
		given(memberService.getAllMembers()).willReturn(list);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/member/all"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())));

	}

	// positive scenario - valid member id
	// JUnit test for GET member by id REST API
	@Test
	public void givenMemberId_whenGetMemberById_thenReturnMemberObject() throws Exception {
		// given - precondition or setup
		long memberId = 1L;
		Member member = new Member(1L, "John", LocalDate.now(), "howtodoinjava@gmail.com", "9874563210", "Pune",
				"Maharashtra", "JohnP", "JohnA123.", null);
		given(memberService.getMemberDetailsById(memberId)).willReturn(member);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/member/{id}", memberId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.memberName", is(member.getMemberName())))
				.andExpect(jsonPath("$.dob", is(member.getDob())))
				.andExpect(jsonPath("$.emailId", is(member.getEmailId())))
				.andExpect(jsonPath("$.contactNo", is(member.getContactNo())))
				.andExpect(jsonPath("$.city", is(member.getCity())))
				.andExpect(jsonPath("$.state", is(member.getState())))
				.andExpect(jsonPath("$.username", is(member.getUsername())))
				.andExpect(jsonPath("$.password", is(member.getPassword())));

	}

	// JUnit test for delete member REST API
	@Test
	public void givenMemberId_whenDeleteMember_thenReturn200() throws Exception {
		// given - precondition or setup
		long memberId = 1L;
		willDoNothing().given(memberService).deleteMemberById(memberId);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/api/member/delete/{id}", memberId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}
	
	
	 @Test
	    public void givenMemberObject_whenCreateMember_thenReturnSavedMember() throws Exception{

	        // given - precondition or setup
		 Member member = new Member(1L, "John", LocalDate.now(), "howtodoinjava@gmail.com", "9874563210", "Pune",
					"Maharashtra", "JohnP", "JohnA123.", null);
	      
	        given(memberService.addMember(any(Member.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));

	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/api/member/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(member)));

	        // then - verify the result or output using assert statements
	        response.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.memberName", is(member.getMemberName())))
			.andExpect(jsonPath("$.dob", is(member.getDob())))
			.andExpect(jsonPath("$.emailId", is(member.getEmailId())))
			.andExpect(jsonPath("$.contactNo", is(member.getContactNo())))
			.andExpect(jsonPath("$.city", is(member.getCity())))
			.andExpect(jsonPath("$.state", is(member.getState())))
			.andExpect(jsonPath("$.username", is(member.getUsername())))
			.andExpect(jsonPath("$.password", is(member.getPassword())));

	    }


}
