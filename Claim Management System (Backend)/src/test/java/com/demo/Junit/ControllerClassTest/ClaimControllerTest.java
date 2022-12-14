package com.demo.Junit.ControllerClassTest;

import com.demo.Controller.ClaimController;
import com.demo.Model.Claim;

import com.demo.Service.ClaimService;

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

@WebMvcTest(value = ClaimController.class)
public class ClaimControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClaimService claimService;

	@Autowired
	private ObjectMapper objectMapper;

	// JUnit test for Get All claim REST API
	@Test
	public void givenListOfClaims_whenGetAllClaims_thenReturnClaimsList() throws Exception {
		// given - precondition or setup

		List<Claim> list = new ArrayList<Claim>();
		Claim claimOne = new Claim(1, null, 50000.0, LocalDate.now(), "Pending");
		Claim claimTwo = new Claim(2, null, 50000.0, LocalDate.now(), "Claimed");
		list.add(claimOne);
		list.add(claimTwo);

		given(claimService.getAllClaims()).willReturn(list);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/claim/all"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())));

	}

	// positive scenario - valid claim id
	// JUnit test for GET claim by id REST API
	@Test
	public void givenClaimId_whenGetClaimById_thenReturnClaimObject() throws Exception {
		// given - precondition or setup
		long claimId = 1L;
		Claim claim = new Claim(1, null, 50000.0, LocalDate.now(), "Pending");
		given(claimService.getClaimDetailsById(claimId)).willReturn(claim);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/claim/{id}", claimId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.claimAmount", is(claim.getClaimAmount())))
				.andExpect(jsonPath("$.requestDate", is(claim.getRequestDate())))
				.andExpect(jsonPath("$.claimStatus", is(claim.getClaimStatus())));

	}

	// JUnit test for delete claim REST API
	@Test
	public void givenClaimId_whenDeleteClaim_thenReturn200() throws Exception {
		// given - precondition or setup
		long claimId = 1L;
		willDoNothing().given(claimService).deleteClaimById(claimId);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/claim/delete/{id}", claimId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void givenClaimObject_whenCreateClaim_thenReturnSavedClaim() throws Exception {

		// given - precondition or setup
		Claim claim = new Claim(1, null, 50000.0, LocalDate.now(), "Pending");

		given(claimService.addClaim(any(Claim.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/claim/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(claim)));

		// then - verify the result or output using assert statements
		response.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.claimAmount", is(claim.getClaimAmount())))
		.andExpect(jsonPath("$.requestDate", is(claim.getRequestDate())))
		.andExpect(jsonPath("$.claimStatus", is(claim.getClaimStatus())));

	}

}
