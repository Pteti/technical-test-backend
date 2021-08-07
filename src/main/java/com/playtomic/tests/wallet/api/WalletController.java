package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.data.Wallet;
import com.playtomic.tests.wallet.exception.BasicWalletException;
import com.playtomic.tests.wallet.service.info.impl.BasicWalletInfoServiceImpl;
import com.playtomic.tests.wallet.exception.PaymentServiceException;
import com.playtomic.tests.wallet.service.payment.impl.ThirdPartyPaymentService;
import com.playtomic.tests.wallet.exception.ChargeServiceException;
import com.playtomic.tests.wallet.service.charge.impl.ChargeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Rest controller for API calls coming into the service.
 */
@RestController
public class WalletController {

    @Autowired
    BasicWalletInfoServiceImpl basicWalletInfoService;
    @Autowired
    ThirdPartyPaymentService thirdPartyPaymentService;
    @Autowired
    ChargeServiceImpl chargeService;

    private Logger log = LoggerFactory.getLogger(WalletController.class);

    /**
     * Gets basic wallet information by id.
     * @param walletId The identifier of the wallet.
     * @return The wallet object.
     * @throws BasicWalletException If the wallet is non existent.
     */
    @GetMapping("/wallet/{id}")
    Wallet getWallet(@PathVariable("id") long walletId) throws BasicWalletException {
        log.info("Requesting wallet balance for wallet: " + walletId);
        return basicWalletInfoService.getWalletInformation(walletId);
    }

    /**
     * Make a charge on the given wallet with the given amount.
     * @param walletId The identifier of the wallet.
     * @param amount The amount to be subtracted.
     * @return 1 if the entry was updated, 0 if it didn't.
     * @throws ChargeServiceException in case of insufficient balance or non existent wallet.
     */
    @PostMapping("/wallet/{id}/charge/{amount}")
    void chargeWallet(@PathVariable("id") long walletId, @PathVariable("amount") double amount) throws ChargeServiceException, BasicWalletException {
        log.info("Charging wallet with id:" + walletId + ", for " + amount + " EUR.");
        chargeService.chargeWallet(walletId, amount);
    }

    /**
     * Top up the given wallet with the given amount.
     * @param walletId The identifier of the wallet.
     * @param amount The amount to be added to the wallet. Must be greater than 10.
     * @throws PaymentServiceException in case the amount is not exceeding the minimum value.
     */
    @PostMapping("/wallet/{id}/topup/{amount}")
    void topUpWallet(@PathVariable("id") long walletId, @PathVariable("amount") long amount) throws PaymentServiceException {
        log.info("Top up wallet with id: " + walletId + ", using third party service");
        thirdPartyPaymentService.topUp(new BigDecimal(amount));
    }
}
