package com.playtomic.tests.wallet.service.charge.impl;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.charge.ChargeService;
import com.playtomic.tests.wallet.exception.ChargeServiceException;
import com.playtomic.tests.wallet.validation.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service that fulfills a wallet charge.
 */
@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    CommonValidator commonValidator;

    @Override
    @Transactional
    public int chargeWallet(long walletId, double amount) throws ChargeServiceException, BasicWalletException {
        walletRepository.save(new Wallet(1, 2000.0));
        commonValidator.validateWallet(walletId);
        double balance = walletRepository.findById(walletId).getBalance();
        //check balance-amount
        return walletRepository.updateBalanceById(walletId,balance-amount);
    }
}