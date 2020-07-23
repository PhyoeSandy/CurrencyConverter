package com.thymr.dev.service;

import com.thymr.dev.entity.Currency;
import com.thymr.dev.entity.CurrencyDTO;

public interface CurrencyService {

    CurrencyDTO convert(CurrencyDTO currencyDTO);
    CurrencyDTO add(Currency currency);
    CurrencyDTO update(Currency NewCurrency, long id);
    void delete(long id);
}
