package br.com.ibm.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ibm.challenge.exceptions.AtmException;
import br.com.ibm.challenge.model.impl.AccountModelImpl;
import br.com.ibm.challenge.model.response.AccountRest;

@Service
public class WithdrawService {
	
	@Autowired
    AccountModelImpl accountModelImpl;
	
	public AccountRest getWithdraw(AccountRest storedAccountDetails, Double value) {
		
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(storedAccountDetails.getAccountId());
		returnValue.setFirstName(storedAccountDetails.getFirstName());
		returnValue.setLastName(storedAccountDetails.getLastName());
		returnValue.setBalance(storedAccountDetails.getBalance() - value);
		if(storedAccountDetails.getBalance() >= value) {
			returnValue.setOperationInfos(getValue(value.intValue()));
		}else {
			throw new AtmException("Insufficient balance for this operation!");
		}
		updateAccount(storedAccountDetails, value);
		return returnValue;
	}
	
	public String getValue(int value) {

        int[] moneyBills = {10,20,50,100};
        int[] amountMoneyBills = {0,0,0,0};
        StringBuilder stringBuilder = new StringBuilder();

        if(value == 0) {
            throw new AtmException("The withdrawal value cannot be 0!");
        }

        if(value < 0) {
            throw new AtmException("The withdrawal amount cannot be negative!");
        }

        if(value % 10 != 0){
            throw new AtmException("Invalid value!");
        } else{
            for (int i = moneyBills.length - 1; i >= 0; i--) {
                while (value >= moneyBills[i]) {
                    value -= moneyBills[i];
                    amountMoneyBills[i]++;
                }
                if(amountMoneyBills[i] > 0){
                    stringBuilder.append(amountMoneyBills[i]);
                    stringBuilder.append(" of R$: ");
                    stringBuilder.append(moneyBills[i]);
                    stringBuilder.append("; ");
                }
            }
        }
        return stringBuilder.toString();
    }
	
	public void updateAccount(AccountRest accountDetails, Double value) {
		AccountRest returnValue = new AccountRest();
		returnValue.setAccountId(accountDetails.getAccountId());
		returnValue.setFirstName(accountDetails.getFirstName());
		returnValue.setLastName(accountDetails.getLastName());
		returnValue.setBalance(accountDetails.getBalance() - value);
		accountModelImpl.accounts.put(returnValue.getAccountId(), returnValue);
	}

}
