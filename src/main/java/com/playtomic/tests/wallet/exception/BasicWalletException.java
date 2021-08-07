package com.playtomic.tests.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle if wallet is not existing.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BasicWalletException extends Exception {
}
