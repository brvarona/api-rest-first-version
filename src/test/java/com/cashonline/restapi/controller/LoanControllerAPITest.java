package com.cashonline.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.cashonline.controller.LoanRestController;
import com.cashonline.entity.Loan;
import com.cashonline.exception.ResourceNotFoundException;
import com.cashonline.service.impl.LoanServiceImpl;
import com.cashonline.service.impl.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;

/**
 * 
 * @author Braian Varona
 *
 */

@ContextConfiguration(classes = LoanRestController.class)
@WebMvcTest(LoanRestController.class)
public class LoanControllerAPITest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	@MockBean
	private LoanServiceImpl loanService;
	
	@Test
	public void whenGetAll_thenReturns200() throws Exception {
		mockMvc.perform(get("/loans?page=0&size=5")
			    .contentType("application/json"))
			    .andExpect(status().isOk());
	}

	@Test
	public void whenGetByValidInput_thenReturns200() throws Exception {
		given(this.loanService.getLoans(PageRequest.of(0, 5), 1L)).willReturn(new ArrayList<Loan>());

		mockMvc.perform(get("/loans?page=0&size=5&user_id=1")
			    .contentType("application/json"))
			    .andExpect(status().isOk());
	}
	
	@Test
	public void whenGetByValidInput_thenReturns400() throws Exception {
		given(this.loanService.getLoans(PageRequest.of(0, 5), 100L)).willThrow(new ResourceNotFoundException("Loan", "user_id", 100L));

		mockMvc.perform(get("/loans?page=0&size=5&user_id=100")
			    .contentType("application/json"))
			    .andExpect(status().isNotFound());
	}

}
