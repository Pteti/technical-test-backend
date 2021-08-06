package com.playtomic.tests.wallet.service.charge;

import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.exception.ChargeServiceException;

public interface ChargeService {
    /**
     * Make a charge on a wallet.
     * @param walletId The identifier of the wallet.
     * @param amount The amount to be subtracted.
     * @return Number of changed entries.
     * @throws ChargeServiceException in case of insufficient balance or non existent wallet.
     */
    int chargeWallet(long walletId, double amount) throws ChargeServiceException, BasicWalletException;
}
