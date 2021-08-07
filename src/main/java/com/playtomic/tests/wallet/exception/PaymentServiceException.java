package com.playtomic.tests.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle if there's a problem with the third-party payment.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaymentServiceException extends Exception {
}
