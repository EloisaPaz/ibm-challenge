package br.com.ibm.challenge.model.response;

public class TransferRest {

	private String originAccountId;
    private String originAccountFirstName;
    private String originAccountLastName;
    private Double originAccountBalance;
    private String destinyAccountId;
    private String destinyAccountFirstName;
    private String destinyAccountLastName;
    private Double destinyAccountBalance;
    private String operationInfos;
    
	public String getOriginAccountId() {
		return originAccountId;
	}
	
	public void setOriginAccountId(String originAccountId) {
		this.originAccountId = originAccountId;
	}
	
	public String getOriginAccountFirstName() {
		return originAccountFirstName;
	}
	
	public void setOriginAccountFirstName(String originAccountFirstName) {
		this.originAccountFirstName = originAccountFirstName;
	}
	
	public String getOriginAccountLastName() {
		return originAccountLastName;
	}
	
	public void setOriginAccountLastName(String originAccountLastName) {
		this.originAccountLastName = originAccountLastName;
	}
	
	public Double getOriginAccountBalance() {
		return originAccountBalance;
	}
	
	public void setOriginAccountBalance(Double originAccountBalance) {
		this.originAccountBalance = originAccountBalance;
	}
	
	public String getDestinyAccountId() {
		return destinyAccountId;
	}
	
	public void setDestinyAccountId(String destinyAccountId) {
		this.destinyAccountId = destinyAccountId;
	}
	
	public String getDestinyAccountFirstName() {
		return destinyAccountFirstName;
	}
	
	public void setDestinyAccountFirstName(String destinyAccountFirstName) {
		this.destinyAccountFirstName = destinyAccountFirstName;
	}
	
	public String getDestinyAccountLastName() {
		return destinyAccountLastName;
	}
	
	public void setDestinyAccountLastName(String destinyAccountLastName) {
		this.destinyAccountLastName = destinyAccountLastName;
	}
	
	public Double getDestinyAccountBalance() {
		return destinyAccountBalance;
	}
	
	public void setDestinyAccountBalance(Double destinyAccountBalance) {
		this.destinyAccountBalance = destinyAccountBalance;
	}
	
	public String getOperationInfos() {
		return operationInfos;
	}
	
	public void setOperationInfos(String operationInfos) {
		this.operationInfos = operationInfos;
	}      
}