package manager;

import entities.Invoice;
import dataSource.DataClass;

public class invoiceManager {

	private static invoiceManager instance = new invoiceManager();

	private invoiceManager() {
	}

	public static invoiceManager getInstance() {
		return instance;
	}

	public Invoice createInvoice(String invoiceNo, long amount,String  dateOfInvoice,  String deadline,String DealerOfInvoice,String chequeNo,String Short) {
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo(invoiceNo);
		invoice.setInvoiceAmount(amount);
		invoice.setInvoiceDate(dateOfInvoice);
		invoice.setInvoiceDeadline(deadline);
		invoice.setChequeNo(chequeNo);
		invoice.setShort(Short);
		long ID = DataClass.getDealerId(DealerOfInvoice);
		invoice.setDealerOfInvoice(ID);
		invoice.setDealerName(DealerOfInvoice);
		return invoice;
	}

	public void saveInvoice(String invoiceNo, long amount,String DateOfInvoice,  String deadline,String dealerName, String chequeNo, String Short) {
		Invoice invoice = new Invoice();
		invoice = createInvoice(invoiceNo, amount, DateOfInvoice,deadline, dealerName,chequeNo,Short);
		DataClass.saveInvoice(invoice);

	}

}