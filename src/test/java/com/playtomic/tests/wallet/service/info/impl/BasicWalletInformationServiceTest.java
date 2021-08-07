package com.playtomic.tests.wallet.service.info.impl;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.validation.CommonValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasicWalletInformationServiceTest {

    private static long WALLET_ID = 10l;

    @InjectMocks
    private BasicWalletInfoServiceImpl basicWalletInfoService;
    @Mock
    private WalletRepository mockWalletRepository;
    @Mock
    private CommonValidator mockCommonValidator;

    @Test(expected = BasicWalletException.class)
    public void test_invalid_wallet_should_throw_exception() throws BasicWalletException {
        doThrow(new BasicWalletException()).when(mockCommonValidator).validateWallet(WALLET_ID);
        basicWalletInfoService.getWalletInformation(WALLET_ID);
        verify(mockWalletRepository,never()).findById(WALLET_ID);
    }

    @Test
    public void test_ok() throws BasicWalletException {
        when(mockWalletRepository.findById(WALLET_ID)).thenReturn(new Wallet(10l, 1000.0));
        Wallet wallet = basicWalletInfoService.getWalletInformation(WALLET_ID);
        Assert.assertEquals(wallet.getBalance(),1000.0,0);
    }
}
