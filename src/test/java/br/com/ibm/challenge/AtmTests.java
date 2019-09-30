package br.com.ibm.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ibm.challenge.controller.AtmController;
import br.com.ibm.challenge.model.request.AccountDetailsRequestModel;
import br.com.ibm.challenge.model.response.AccountRest;
import br.com.ibm.challenge.model.response.TransferRest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmTests {
	
	@Autowired
	AtmController atmController;
	
	@Test
	public void createAccountTest() {
		ResponseEntity<AccountRest> response = createAccount("name", "lastName", 200.50);
		response.getStatusCode().compareTo(HttpStatus.OK);
	}
	
	@Test
	public void makeDepositTest() {
		ResponseEntity<AccountRest> account = createAccount("name", "lastName", 200.50);
		ResponseEntity<AccountRest> response = atmController.getDeposit(account.getBody().getAccountId(), 50.50);	
		response.getStatusCode().compareTo(HttpStatus.OK);
		response.getBody().getBalance().compareTo(250.50);
	}
	
	@Test
	public void makeWithdrawTest() {
		ResponseEntity<AccountRest> account = createAccount("name", "lastName", 200.00);
		ResponseEntity<AccountRest> response = atmController.getWithdraw(account.getBody().getAccountId(), 50.00);	
		response.getStatusCode().compareTo(HttpStatus.OK);
		response.getBody().getBalance().compareTo(150.00);
	}
	
	@Test
	public void makeTransferTest() {
		ResponseEntity<AccountRest> accountOrigin = createAccount("name 1", "lastName 1", 200.00);
		ResponseEntity<AccountRest> accountDestiny = createAccount("name 2", "lastName 2", 160.00);
		ResponseEntity<TransferRest> response = atmController.getTransfer(accountOrigin.getBody().getAccountId(), accountDestiny.getBody().getAccountId(), 40.00);	
		response.getStatusCode().compareTo(HttpStatus.OK);
		response.getBody().getOriginAccountBalance().compareTo(160.00);
		response.getBody().getDestinyAccountBalance().compareTo(200.00);
	}
	
	
	private ResponseEntity<AccountRest> createAccount(String name, String lastName, Double balance) {
		AccountDetailsRequestModel account = new AccountDetailsRequestModel();
		account.setFirstName("name");
		account.setLastName("lastName");
		account.setBalance(250.00);
		return atmController.createUser(account);
		
	}

}
