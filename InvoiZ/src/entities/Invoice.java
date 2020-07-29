package entities;

public class Invoice {
	String Short;
	String InvoiceNo;
	long invoiceAmount;
	String DealerName;
	String InvoiceDate;
	String invoiceDeadline;
	long DealerOfInvoice;// id of dealer
	String chequeNo;

	public String getInvoiceNo() {
		return InvoiceNo;
	}

	public void setInvoiceNo(String invoiceNo2) {
		InvoiceNo = invoiceNo2;
	}
	public long getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(long amount) {
		this.invoiceAmount = amount;
	}

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String output) {
		InvoiceDate = output;
	}

	public String getInvoiceDeadline() {
		return invoiceDeadline;
	}

	public void setInvoiceDeadline(String invoiceDeadline2) {
		this.invoiceDeadline = invoiceDeadline2;
	}

	public long getDealerOfInvoice() {
		return DealerOfInvoice;
	}

	public void setDealerOfInvoice(long iD) {
		DealerOfInvoice = iD;
	}


	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo2) {
		this.chequeNo = chequeNo2;
	}
	public String getDealerName() {
		return DealerName;
	}

	public void setDealerName(String dealerName) {
		DealerName = dealerName;
	}
	@Override
	public String toString() {
		return "Invoice [InvoiceNo=" + InvoiceNo + ", invoiceAmount=" + invoiceAmount + ", InvoiceDate=" + InvoiceDate
				+ ", invoiceDeadline=" + invoiceDeadline + ", DealerOfInvoice=" + DealerOfInvoice + ", chequeNo="
				+ chequeNo + "]";
	}

	public String getShort() {
		return Short;
	}

	public void setShort(String s) {
		Short = s;
	}

	
}
