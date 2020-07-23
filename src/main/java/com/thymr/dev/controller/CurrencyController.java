package com.thymr.dev.controller;

import com.thymr.dev.entity.Currency;
import com.thymr.dev.entity.CurrencyDTO;
import com.thymr.dev.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/addCurrency")
    public ResponseEntity<CurrencyDTO> addCurrency(@RequestBody Currency currency, Model model) {
        CurrencyDTO currencyDTO = currencyService.add(currency);
        return new ResponseEntity<CurrencyDTO>(currencyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCurrency/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable long id) {
        currencyService.delete(id);
        return new ResponseEntity<String>("Id " + id + " is successfully deleted.",HttpStatus.OK);
    }

    @PutMapping("/updateCurrency/{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(@RequestBody Currency currency, @PathVariable long id) {
        CurrencyDTO currencyDTO = currencyService.update(currency, id);
        return new ResponseEntity<CurrencyDTO>(currencyDTO, HttpStatus.OK);
    }

    @PostMapping("/convertCurrency")
    public ResponseEntity<CurrencyDTO> convertCurrency(@RequestBody CurrencyDTO request) {
        CurrencyDTO currencyDTO = currencyService.convert(request);
        return new ResponseEntity<CurrencyDTO>(currencyDTO, HttpStatus.OK);
    }

}
