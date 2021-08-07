package com.playtomic.tests.wallet.validation;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.exception.ChargeServiceException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommonValidationTest {

    private static Long WALLET_ID = 10l;
    private static double AMOUNT = 1000.0;

    @InjectMocks
    CommonValidator commonValidator;
    @Mock
    WalletRepository mockWalletRepository;

    @Test(expected = BasicWalletException.class)
    public void test_validate_wallet_not_exists() throws BasicWalletException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(null);

        commonValidator.validateWallet(WALLET_ID);
    }
    @Test(expected = ChargeServiceException.class)
    public void test_validate_balance_is_not_sufficient() throws ChargeServiceException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(new Wallet(WALLET_ID,500.0));

        commonValidator.validateSufficientBalance(WALLET_ID, AMOUNT);
    }

    @Test
    public void test_validation_balance_is_ok() throws ChargeServiceException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(new Wallet(WALLET_ID,5000.0));

        commonValidator.validateSufficientBalance(WALLET_ID,AMOUNT);

        verify(mockWalletRepository,only()).findById(WALLET_ID);
    }

    @Test
    public void test_validate_wallet_is_ok() throws BasicWalletException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(new Wallet(WALLET_ID,5000.0));

        commonValidator.validateWallet(WALLET_ID);

        verify(mockWalletRepository,only()).findById(WALLET_ID);
    }
}
