package com.playtomic.tests.wallet.service.payment;

import com.playtomic.tests.wallet.exception.PaymentServiceException;

import java.math.BigDecimal;

public interface PaymentService {

    /**
     * Top up wallet.
     * @param amount The amount to be added into the wallet.
     * @throws PaymentServiceException in case the amount is not exceeding the minimum value.
     */
    void topUp(BigDecimal amount) throws PaymentServiceException;
}
