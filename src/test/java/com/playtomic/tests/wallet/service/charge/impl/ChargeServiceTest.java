package com.playtomic.tests.wallet.service.charge.impl;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.exception.ChargeServiceException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.validation.CommonValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChargeServiceTest {

    private static long WALLET_ID = 10l;
    private static double AMOUNT = 1000.0;

    @InjectMocks
    private ChargeServiceImpl chargeService;
    @Mock
    private WalletRepository mockWalletRepository;
    @Mock
    private CommonValidator mockCommonValidator;

    private Wallet wallet;


    @Before
    public void setUp() {
        createWallet();
        when(mockWalletRepository.findById(1)).thenReturn(wallet);
    }

    @Test(expected = BasicWalletException.class)
    public void test_charging_with_non_existent_wallet() throws BasicWalletException, ChargeServiceException {
        doThrow(new BasicWalletException()).when(mockCommonValidator).validateWallet(WALLET_ID);

        chargeService.chargeWallet(WALLET_ID, AMOUNT);

        verify(mockCommonValidator, never()).validateSufficientBalance(WALLET_ID, AMOUNT);
        verify(mockWalletRepository, never()).updateBalanceById(WALLET_ID, AMOUNT);
    }

    @Test(expected = ChargeServiceException.class)
    public void test_charging_with_insufficient_amount_in_wallet() throws ChargeServiceException, BasicWalletException {
        doThrow(new ChargeServiceException()).when(mockCommonValidator).validateSufficientBalance(WALLET_ID, AMOUNT);

        chargeService.chargeWallet(WALLET_ID, AMOUNT);

        verify(mockCommonValidator, only()).validateWallet(WALLET_ID);
        verify(mockWalletRepository, never()).updateBalanceById(WALLET_ID, AMOUNT);
    }

    @Test
    public void test_charging_ok() throws BasicWalletException, ChargeServiceException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(new Wallet(WALLET_ID, 2000.0));
        when(mockWalletRepository.updateBalanceById(WALLET_ID, AMOUNT)).thenReturn(1);
        chargeService.chargeWallet(WALLET_ID, AMOUNT);
    }

    private void createWallet() {
        this.wallet = new Wallet(1,1000.0);
    }
}
