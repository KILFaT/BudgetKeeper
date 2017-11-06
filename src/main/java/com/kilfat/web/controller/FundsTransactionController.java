package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.service.interfaces.FundsTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ServiceConstants.TRANSACTION_PATH)
public class FundsTransactionController {

    private FundsTransactionService transactionService;

    @Autowired
    public FundsTransactionController(FundsTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{transactionId}",
        method = RequestMethod.GET)
    public @ResponseBody
    FundsTransaction getFundsTransaction(
        @PathVariable("transactionId")
            Long transactionId) {
        return transactionService.getFundsTransaction(transactionId);
    }

    @RequestMapping(value = "{transactionId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putFundsTransaction(
        @PathVariable("transactionId")
            Long transactionId,
        @Valid
        @RequestBody
            FundsTransaction transaction) {
        transaction.setId(transactionId);
        transactionService.saveFundsTransaction(transaction);
    }

    @RequestMapping(value = "{transactionId}",
        method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFundsTransaction(
        @PathVariable("transactionId")
            Long transactionId) {
        transactionService.deleteFundsTransaction(transactionId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    FundsTransaction createFundsTransaction(
        @Valid
        @RequestBody
            FundsTransaction transaction) {
        return transactionService.saveFundsTransaction(transaction);
    }
}
