package br.com.ibm.challenge.model;

import br.com.ibm.challenge.model.request.AccountDetailsRequestModel;
import br.com.ibm.challenge.model.response.AccountRest;

public interface AccountModel {
	
	AccountRest createAccount(AccountDetailsRequestModel AccountDetails);
	
}