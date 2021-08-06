package com.playtomic.tests.wallet.service.info;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;

public interface BasicWalletInfoService {
    /**
     * Get a wallet object.
     * @param id The identifier of wanted wallet.
     * @return The Wallet object.
     */
    Wallet getWalletInformation(long id) throws BasicWalletException;
}
