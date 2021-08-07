package com.playtomic.tests.wallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WalletApplicationIT {

	@Test
	public void emptyTest() {

	}

	@Test
	public void getWalletTest(){

	}

	@Test
	public void getNonExistentWalletTest(){

	}

	@Test
	public void chargeWalletTest(){

	}

	@Test
	public void chargeNonExistentWalletTest(){

	}

	@Test
	public void chargeNotSufficientBalanceTest(){

	}

	@Test
	public void topUpWalletTest(){

	}

	@Test
	public void topUpWalletWithoutEnoughEurosTest(){

	}
}
