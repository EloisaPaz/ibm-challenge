package br.com.ibm.challenge.model.response;

public class AccountRest {

    private String accountId;
    private String firstName;
    private String lastName;
    private Double balance;
    private String operationInfos;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

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
    
    public String getOperationInfos() {
        return operationInfos;
    }

    public void setOperationInfos(String operationInfos) {
        this.operationInfos = operationInfos;
    }
}
