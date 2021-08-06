package com.playtomic.tests.wallet.validation;

import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonValidator {

    @Autowired
    WalletRepository walletRepository;

    public void validateWallet(long walletId) throws BasicWalletException {
        try{
            walletRepository.findById(walletId);
        } catch (Exception e) {
            throw new BasicWalletException();
        }
    }

    public void validateSufficientBalance(long walletId){

    }
}
