package com.thymr.dev.repository;

import com.thymr.dev.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findCurrencyBySourceCurrencyAndTargetCurrency(String source, String target);
}
