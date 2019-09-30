package br.com.ibm.challenge.model.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ibm.challenge.shared.Utils;
import br.com.ibm.challenge.model.request.AccountDetailsRequestModel;
import br.com.ibm.challenge.model.AccountModel;
import br.com.ibm.challenge.model.response.AccountRest;

@Service
public class AccountModelImpl implements AccountModel{

    public Map<String, AccountRest> accounts;
    public Utils utils;

    public AccountModelImpl() {}

    @Autowired
    public AccountModelImpl(Utils utils){
        this.utils =utils;
    }

    @Override
    public AccountRest createAccount(AccountDetailsRequestModel accountDetails) {

        AccountRest returnValue = new AccountRest();
        returnValue.setFirstName(accountDetails.getFirstName());
        returnValue.setLastName(accountDetails.getLastName());
        returnValue.setBalance(accountDetails.getBalance());

        String accountId = utils.generateAccountId();
        returnValue.setAccountId(accountId);

        if(accounts == null) accounts = new HashMap<>();
        accounts.put(accountId, returnValue);

        return returnValue;
    }
}