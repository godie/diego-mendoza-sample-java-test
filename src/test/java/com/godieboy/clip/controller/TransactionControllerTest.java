package com.godieboy.clip.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godieboy.clip.dto.TransactionReportDTO;
import com.godieboy.clip.dto.UserSumDTO;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.service.TransactionService;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransactionService transactionService;

	//@InjectMocks
	//private TransactionController transactionController;

	private JacksonTester<Transaction> jsonTransaction;
	private JacksonTester<UserSumDTO> jsonUserSum;
	private JacksonTester<List<Transaction>> listJsonTransaction;
	private JacksonTester<List<TransactionReportDTO>> listJsonReportTransaction;

	@Before
	void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
		// mvc = MockMvcBuilders.standaloneSetup(transactionController).setControllerAdvice(new RestErrorHandler())
				//.build();
	}

	@Test
	void testShowLongString() throws Exception {
		// given
		
		Transaction t = new Transaction();
		t.setUserId(1234);
		t.setDescription("description");
		t.setDate(new Date());
		t.setAmount(100);
		t.setId("1234567");
		
				
		given(transactionService.show(1234,"1234567")).willReturn(t);
		
		MockHttpServletResponse response = mvc.perform(
				get("/api/transactions/123/1234567").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		//assertThat(response.getContentAsString()).isEqualTo(jsonTransaction.write(t).getJson());
		
	}

	/*@Test
	void testShowLong() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	void testSum() {
		fail("Not yet implemented");
	}

	@Test
	void testReport() {
		fail("Not yet implemented");
	}*/

}
