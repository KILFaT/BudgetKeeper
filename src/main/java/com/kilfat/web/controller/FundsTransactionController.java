package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.service.interfaces.FundsTransactionService;
import com.kilfat.web.model.FundsTransactionDTO;
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
    FundsTransactionDTO getFundsTransaction(
        @PathVariable("transactionId")
            Long transactionId) {
        FundsTransaction fundsTransaction = transactionService.getFundsTransaction(transactionId);
        return FundsTransactionDTO.convertToDTO(fundsTransaction);
    }

    @RequestMapping(value = "{transactionId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putFundsTransaction(
        @PathVariable("transactionId")
            Long transactionId,
        @Valid
        @RequestBody
            FundsTransactionDTO fundsTransactionDTO) {
        FundsTransaction fundsTransaction = FundsTransactionDTO.convertToEntity(fundsTransactionDTO);
        fundsTransaction.setId(transactionId);
        transactionService.saveFundsTransaction(fundsTransaction);
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
    FundsTransactionDTO createFundsTransaction(
        @Valid
        @RequestBody
            FundsTransactionDTO transactionDTO) {
        FundsTransaction fundsTransaction = FundsTransactionDTO.convertToEntity(transactionDTO);
        fundsTransaction = transactionService.saveFundsTransaction(fundsTransaction);
        return FundsTransactionDTO.convertToDTO(fundsTransaction);
    }
}
