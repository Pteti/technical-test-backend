package com.playtomic.tests.wallet.service.payment.impl;


import com.playtomic.tests.wallet.exception.PaymentServiceException;
import org.junit.Test;

import java.math.BigDecimal;

public class ThirdPartyPaymentServiceTest {

    ThirdPartyPaymentService s = new ThirdPartyPaymentService();

    @Test(expected = PaymentServiceException.class)
    public void test_exception() throws PaymentServiceException {
        s.topUp(new BigDecimal(5));
    }

    @Test
    public void test_ok() throws PaymentServiceException {
        s.topUp(new BigDecimal(15));
    }
}