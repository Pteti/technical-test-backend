package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.data.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Wallet class.
 */
@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    /**
     * Get a wallet by id.
     * @param id The identifier of the wallet.
     * @return The wallet object.
     */
    Wallet findById(long id);

    /**
     * Sets the balance of a wallet. Can be used for either top-up or charge.
     * @param id The identifier of the wallet.
     * @param balance The updated balance of the wallet.
     * @return number of updated entries.
     */
    @Modifying
    @Query("update Wallet w set w.balance = :balance where w.id = :id")
    int updateBalanceById(@Param("id") long id, @Param("balance") double balance);
}
