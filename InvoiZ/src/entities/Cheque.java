package entities;

public class Cheque {
	private String dealerName;
	private String ChequeNo;
	private String invoiceNO;
	private long DealerID;
	private long ChequeAmount;
	
	private String ChequeDate; // date type ????
	private String ChequeExpireDate;
	
	
	private int ChequeExpireStatus;
	private int ChequePassStatus;
	
	
	public long getDealerID() {
		return DealerID;
	}

	public void setDealerID(long dealerID) {
		DealerID = dealerID;
	}



	public String  getChequeNo() {
		return ChequeNo;
	}

	public void setChequeNo(String chequeNo_C) {
		ChequeNo = chequeNo_C;
	}

	public String getChequeDate() {
		return ChequeDate;
	}

	public void setChequeDate(String chequeDate2) {
		ChequeDate = chequeDate2;
	}

	public String getChequeExpireDate() {
		return ChequeExpireDate;
	}

	public void setChequeExpireDate(String chequeExpireDate) {
		ChequeExpireDate = chequeExpireDate;
	}

	public int getChequeExpireStatus() {
		return ChequeExpireStatus;
	}

	public void setChequeExpireStatus(int chequeExpireStatus2) {
		ChequeExpireStatus = chequeExpireStatus2;
	}

	public int getChequePassStatus() {
		return ChequePassStatus;
	}

	public void setChequePassStatus(int chequePassStatus2) {
		ChequePassStatus = chequePassStatus2;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNo_C) {
		this.invoiceNO = invoiceNo_C;
	}

	public long getChequeAmount() {
		return ChequeAmount;
	}

	public void setChequeAmount(long chequeAmount) {
		ChequeAmount = chequeAmount;
	}
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	

	@Override
	public String toString() {
		return "Cheque [ChequeNo=" + ChequeNo + ", invoiceNO=" + invoiceNO + ", DealerID=" + DealerID
				+ ", ChequeAmount=" + ChequeAmount + ", ChequeDate=" + ChequeDate + ", ChequeExpireDate="
				+ ChequeExpireDate + ", ChequeExpireStatus=" + ChequeExpireStatus + ", ChequePassStatus="
				+ ChequePassStatus + "]";
	}

	
}
