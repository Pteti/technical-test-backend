package com.playtomic.tests.wallet.service.info.impl;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.info.BasicWalletInfoService;
import com.playtomic.tests.wallet.validation.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicWalletInfoServiceImpl implements BasicWalletInfoService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    CommonValidator commonValidator;

    @Override
    public Wallet getWalletInformation(long id) throws BasicWalletException {
        commonValidator.validateWallet(id);
        return walletRepository.findById(id);
    }
}
