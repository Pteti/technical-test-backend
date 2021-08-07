package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletRepositoryIT {

    private static long WALLET_ID = 1l;
    private static double BALANCE = 1000.0;

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    WalletRepository walletRepository;

    @Test
    public void testFindById() {
        Wallet wallet = new Wallet(1000.0);
        testEntityManager.persist(wallet);
        testEntityManager.flush();

        Wallet found = walletRepository.findById(WALLET_ID);

        assertThat(found.getBalance()).isEqualTo(BALANCE);
    }

    @Test
    public void testUpdateBalance() {
        Wallet wallet = new Wallet(1000.0);
        testEntityManager.persist(wallet);
        testEntityManager.flush();

        walletRepository.updateBalanceById(WALLET_ID, 500);
        Wallet found = walletRepository.findById(WALLET_ID);

        assertThat(found.getBalance()).isEqualTo(500);
    }

}
