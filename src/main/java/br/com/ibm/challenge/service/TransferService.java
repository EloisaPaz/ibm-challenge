package br.com.ibm.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ibm.challenge.exceptions.AtmException;
import br.com.ibm.challenge.model.impl.AccountModelImpl;
import br.com.ibm.challenge.model.response.AccountRest;
import br.com.ibm.challenge.model.response.TransferRest;

@Service
public class TransferService {

	@Autowired
    AccountModelImpl accountModelImpl;
	
	public TransferRest getTransfer(AccountRest originAccountDetails, AccountRest destinyAccountDetails, Double value) {
		TransferRest returnValue = new TransferRest();
		returnValue.setOriginAccountId(originAccountDetails.getAccountId());
		returnValue.setOriginAccountFirstName(originAccountDetails.getFirstName());
		returnValue.setOriginAccountLastName(originAccountDetails.getLastName());
		if(originAccountDetails.getBalance() < value) {
			throw new AtmException("Insufficient balance to complete transfer!");
		}else {
			returnValue.setOriginAccountBalance(originAccountDetails.getBalance() - value);
			updateAccountOrigin(originAccountDetails, value);
		}
		returnValue.setDestinyAccountId(destinyAccountDetails.getAccountId());
		returnValue.setDestinyAccountFirstName(destinyAccountDetails.getFirstName());
		returnValue.setDestinyAccountLastName(destinyAccountDetails.getLastName());
		returnValue.setDestinyAccountBalance(destinyAccountDetails.getBalance() + value);
		updateAccountDestiny(destinyAccountDetails, value);
		returnValue.setOperationInfos("Transfer made in the amount of: R$" + value);
		return returnValue;	
	}
	
	public void updateAccountOrigin(AccountRest accountDetails, Double value) {
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(accountDetails.getAccountId());
		returnValue.setFirstName(accountDetails.getFirstName());
		returnValue.setLastName(accountDetails.getLastName());
		returnValue.setBalance(accountDetails.getBalance() - value);
		accountModelImpl.accounts.put(returnValue.getAccountId(), returnValue);
	}
	
	public void updateAccountDestiny(AccountRest accountDetails, Double value) {
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(accountDetails.getAccountId());
		returnValue.setFirstName(accountDetails.getFirstName());
		returnValue.setLastName(accountDetails.getLastName());
		returnValue.setBalance(accountDetails.getBalance() + value);
		accountModelImpl.accounts.put(returnValue.getAccountId(), returnValue);
	}
}