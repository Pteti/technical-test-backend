package com.playtomic.tests.wallet.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
public class WalletController {
    private Logger log = LoggerFactory.getLogger(WalletController.class);

    @RequestMapping("/")
    void log() {
        log.info("Logging from /");
    }

    @GetMapping("/wallet/{id}")
    long getBalance(@PathVariable("id") long walletId) {
        log.info("Requesting wallet balance for wallet: " + walletId);
        return 10l;
    };

    @PostMapping("/wallet/{id}/charge/{amount}")
    long chargeWallet(@PathVariable("id") long walletId, @PathVariable("amount") long amount) {
        log.info("charging wallet.");
        return 10l;
    };

    @PostMapping("/wallet/{id}/topup/{amount}")
    long topUpWallet(@PathVariable("id") long walletId, @PathVariable("amount") long amount){
        log.info("top up wallet.");
        return 10l;
    };
}
