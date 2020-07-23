package com.thymr.dev.service.impls;

import com.thymr.dev.entity.Currency;
import com.thymr.dev.entity.CurrencyDTO;
import com.thymr.dev.repository.CurrencyRepository;
import com.thymr.dev.service.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    private CurrencyDTO toDTO(Currency currency) {
        return modelMapper.map(currency, CurrencyDTO.class);
    }

    private Currency toEntity(CurrencyDTO currencyDTO) {
        return modelMapper.map(currencyDTO, Currency.class);
    }

    @Override
    public CurrencyDTO convert(CurrencyDTO currencyDTO) {
        Currency currency = currencyRepository.findCurrencyBySourceCurrencyAndTargetCurrency(
                            currencyDTO.getSourceCurrency(),currencyDTO.getTargetCurrency());

        CurrencyDTO result = toDTO(currency);
        result.setConvertResult(currencyDTO.getAmount() * result.getExchangeRate());
        return result;
    }

    @Override
    public CurrencyDTO add(Currency currency) {
        return toDTO(currencyRepository.save(currency));
    }

    @Override
    @Transactional
    public CurrencyDTO update(Currency NewCurrency, long id) {
        Currency currency = currencyRepository.getOne(id);
        currency.setSourceCurrency(NewCurrency.getSourceCurrency());
        currency.setTargetCurrency(currency.getTargetCurrency());
        currency.setExchangeRate(NewCurrency.getExchangeRate());
        return toDTO(currency);
    }

    @Override
    public void delete(long id) {
        currencyRepository.deleteById(id);
    }
}
