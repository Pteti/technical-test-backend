package com.playtomic.tests.wallet.data;

import javax.persistence.*;

/**
 * Wallet class that stores balances separated by ids.
 */
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Double balance;

    protected Wallet(){};

    public Wallet(Double balance) {
        this.balance = balance;
    }
    public Wallet(long walletId, Double balance) {
        this.id = walletId;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }
}
