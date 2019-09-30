package br.com.ibm.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ibm.challenge.exceptions.AtmException;
import br.com.ibm.challenge.model.AccountModel;
import br.com.ibm.challenge.model.impl.AccountModelImpl;
import br.com.ibm.challenge.model.request.AccountDetailsRequestModel;
import br.com.ibm.challenge.model.response.AccountRest;
import br.com.ibm.challenge.model.response.TransferRest;
import br.com.ibm.challenge.service.DepositService;
import br.com.ibm.challenge.service.TransferService;
import br.com.ibm.challenge.service.WithdrawService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/atm")
public class AtmController {
	
    @Autowired
    WithdrawService withdrawService;
    @Autowired
    DepositService depositService;
    @Autowired
    TransferService transferService;
    @Autowired
    AccountModel accountModel;
    @Autowired
    AccountModelImpl accountModelImpl;
    
    @PostMapping(path ="/newaccount",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountRest> createUser(@Valid @RequestBody AccountDetailsRequestModel accountDetails) {
    	AccountRest returnValue = accountModel.createAccount(accountDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
    
    @GetMapping(path="/withdraw/{accountId}/{value}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<AccountRest> getWithdraw(@PathVariable String accountId, @PathVariable Double value) {

        if(accountModelImpl.accounts.containsKey(accountId)) {
        	AccountRest storedAccountDetails = accountModelImpl.accounts.get(accountId);
            return new ResponseEntity<>(withdrawService.getWithdraw(storedAccountDetails, value), HttpStatus.OK);
        } else {
        	throw new AtmException("Account cannot be found!");
        }
    }
    
    @GetMapping(path="/deposit/{accountId}/{value}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<AccountRest> getDeposit(@PathVariable String accountId, @PathVariable Double value) {

        if(accountModelImpl.accounts.containsKey(accountId)) {
        	AccountRest storedAccountDetails = accountModelImpl.accounts.get(accountId);
            return new ResponseEntity<>(depositService.getDeposit(storedAccountDetails, value), HttpStatus.OK);
        } else {
        	throw new AtmException("Account cannot be found!");
        }
    }
    
    @GetMapping(path="/transfer/{originAccountId}/{destinyAccountId}/{value}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<TransferRest> getTransfer(@PathVariable String originAccountId, @PathVariable String destinyAccountId, @PathVariable Double value) {
    	
    	AccountRest originAccountDetails;
    	AccountRest destinyAccountDetails;
    	
        if(accountModelImpl.accounts.containsKey(originAccountId)) {
        	originAccountDetails = accountModelImpl.accounts.get(originAccountId);
        } else {
        	throw new AtmException("Origin account cannot be found!");
        }
        
        if(accountModelImpl.accounts.containsKey(destinyAccountId)) {
        	destinyAccountDetails = accountModelImpl.accounts.get(destinyAccountId);
        } else {
        	throw new AtmException("Destiny account cannot be found!");
        }
        
        return new ResponseEntity<>(transferService.getTransfer(originAccountDetails, destinyAccountDetails, value), HttpStatus.OK);
    }

}
