package br.com.ibm.challenge.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountDetailsRequestModel {

	@NotNull(message = "First name cannot be null!")
    @Size(min = 2, message = "First name must be grater than 2 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    @Size(min = 2, message = "Last name must be grater than 2 characters")
    private String lastName;
    
    private Double balance;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}