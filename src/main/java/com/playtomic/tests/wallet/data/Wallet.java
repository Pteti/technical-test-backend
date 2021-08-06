package com.playtomic.tests.wallet.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Wallet class that stores balances separated by ids.
 */
@Entity
public class Wallet {
    @Id
    @Column(unique = true, nullable = false)
    private Long id;
    private Double balance;

    protected Wallet(){};

    public Wallet(long walletId, Double balance) {
        this.id = walletId;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }
}
