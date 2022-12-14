package com.demo.Junit.ControllerClassTest;


import com.demo.Controller.PlanController;
import com.demo.Model.Plan;
import com.demo.Service.PlanService;
import com.demo.Service.PlanService;
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
import org.junit.jupiter.api.Test;

@WebMvcTest(value = PlanController.class)
public class PlanControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlanService planService;

	@Autowired
	private ObjectMapper objectMapper;

	// JUnit test for Get All plan REST API
	@Test
	public void givenListOfPlans_whenGetAllPlans_thenReturnPlansList() throws Exception {
		// given - precondition or setup

		List<Plan> list = new ArrayList<Plan>();
		Plan planOne = new Plan(1, "Health Insurance Plus", 50000.0, LocalDate.now(), LocalDate.now());
		Plan planTwo = new Plan(2, "Health Insurance Premium", 70000.0, LocalDate.now(),LocalDate.now());

		list.add(planOne);
		list.add(planTwo);

		given(planService.getAllPlans()).willReturn(list);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/plan/all"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())));

	}

	// positive scenario - valid plan id
	// JUnit test for GET plan by id REST API
	@Test
	public void givenPlanId_whenGetPlanById_thenReturnPlanObject() throws Exception {
		// given - precondition or setup
		long planId = 2L;
		Plan plan = new Plan(2, "Health Insurance Premium", 70000.0, LocalDate.now(), LocalDate.now());

		given(planService.getMemberDetailsById(planId)).willReturn(plan);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/plan/{id}", planId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.planName", is(plan.getPlanName())))
				.andExpect(jsonPath("$.insuredAmount", is(plan.getInsuredAmount())))
				.andExpect(jsonPath("$.startDate", is(plan.getStartDate())))
				.andExpect(jsonPath("$.endDate", is(plan.getEndDate())));

	}

	// JUnit test for delete plan REST API
	@Test
	public void givenPlanId_whenDeletePlan_thenReturn200() throws Exception {
		// given - precondition or setup
		long planId = 1L;
		willDoNothing().given(planService).deleteMemberById(planId);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/plan/delete/{id}", planId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void givenPlanObject_whenCreatePlan_thenReturnSavedPlan() throws Exception {

		// given - precondition or setup
		Plan plan = new Plan(1, null, 50000.0, LocalDate.now(), LocalDate.now());

		given(planService.addPlan(any(Plan.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/plan/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(plan)));

		// then - verify the result or output using assert statements
		response.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.planName", is(plan.getPlanName())))
		.andExpect(jsonPath("$.insuredAmount", is(plan.getInsuredAmount())))
		.andExpect(jsonPath("$.startDate", is(plan.getStartDate())))
		.andExpect(jsonPath("$.endDate", is(plan.getEndDate())));


	}

}
