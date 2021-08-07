package com.playtomic.tests.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle if the charge of the wallet goes wrong.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ChargeServiceException extends Exception{
}
