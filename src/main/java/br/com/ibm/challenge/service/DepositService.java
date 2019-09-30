package br.com.ibm.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ibm.challenge.exceptions.AtmException;
import br.com.ibm.challenge.model.impl.AccountModelImpl;
import br.com.ibm.challenge.model.response.AccountRest;

@Service
public class DepositService {

	@Autowired
    AccountModelImpl accountModelImpl;
	
	public AccountRest getDeposit(AccountRest storedAccountDetails, Double value) {
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(storedAccountDetails.getAccountId());
		returnValue.setFirstName(storedAccountDetails.getFirstName());
		returnValue.setLastName(storedAccountDetails.getLastName());
		if(value <= 0) {
			throw new AtmException("Value cannot be negative or zero!");
		}else {
			returnValue.setBalance(storedAccountDetails.getBalance() + value);
			returnValue.setOperationInfos("Deposit made in the amount of: R$" + value);
		}
		updateAccount(storedAccountDetails, value);
		return returnValue;
	}
	
	public void updateAccount(AccountRest accountDetails, Double value) {
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(accountDetails.getAccountId());
		returnValue.setFirstName(accountDetails.getFirstName());
		returnValue.setLastName(accountDetails.getLastName());
		returnValue.setBalance(accountDetails.getBalance() + value);
		accountModelImpl.accounts.put(returnValue.getAccountId(), returnValue);
	}
}
