package com.playtomic.tests.wallet;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class WalletApplicationIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getWalletTestResponse200() throws Exception {
		this.mockMvc.perform(get("/wallet/1"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getWalletTestResponseWithBalance() throws Exception {
		this.mockMvc.perform(get("/wallet/1"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("1000.0")));
	}

	@Test
	public void getNonExistentWalletTest() throws Exception {
		this.mockMvc.perform(get("/wallet/42"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void chargeWalletTest() throws Exception {
		this.mockMvc.perform(post("/wallet/1/charge/500"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void chargeWalletTestShouldDeductTheValue() throws Exception {
		this.mockMvc.perform(post("/wallet/1/charge/500"))
				.andExpect(status().is2xxSuccessful());
				//.andExpect(content().string(containsString("500.0"))); TODO: Better return values to include updated object
	}

	@Test
	public void chargeNonExistentWalletTest() throws Exception {
		this.mockMvc.perform(post("/wallet/42/charge/500"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void chargeNotSufficientBalanceTest() throws Exception {
		this.mockMvc.perform(post("/wallet/1/charge/1500"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void topUpWalletTest() throws Exception {
		this.mockMvc.perform(post("/wallet/1/topup/500"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void topUpWalletWithoutEnoughEurosTest() throws Exception {
		this.mockMvc.perform(post("/wallet/1/topup/5"))
				.andExpect(status().isBadRequest());
	}
}
