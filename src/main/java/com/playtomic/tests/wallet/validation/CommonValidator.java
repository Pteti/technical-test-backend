package com.playtomic.tests.wallet.validation;

import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.exception.ChargeServiceException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonValidator {

    @Autowired
    WalletRepository walletRepository;

    private Logger log = LoggerFactory.getLogger(CommonValidator.class);

    public void validateWallet(long walletId) throws BasicWalletException {
        log.debug("Checking if wallet exists.");
        if (walletRepository.findById(walletId) == null) {
            log.error("Wallet with id " + walletId + " not found!");
            throw new BasicWalletException();
        }
    }

    public void validateSufficientBalance(long walletId, double amount) throws ChargeServiceException {
        log.debug("Checking if wallet has enough balance for the charging.");
        double balance = walletRepository.findById(walletId).getBalance();
        if (balance < amount) {
            log.error("Wallet has insufficient balance for the charge!");
            throw new ChargeServiceException();
        }
    }
}
