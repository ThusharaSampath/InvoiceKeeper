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

	public Invoice createInvoice(String invoiceNo, long amount,long cashAmount, String  dateOfInvoice,  String deadline,String DealerOfInvoice,String Short) {
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo(invoiceNo);
		invoice.setInvoiceAmount(amount);
		invoice.setInvoiceDate(dateOfInvoice);
		invoice.setInvoiceDeadline(deadline);
		invoice.setCashAmount(cashAmount);
		invoice.setShort(Short);
		long ID = DataClass.getDealerId(DealerOfInvoice);
		invoice.setDealerOfInvoice(ID);
		invoice.setDealerName(DealerOfInvoice);
		return invoice;
	}

	public void saveInvoice(String invoiceNo, long amount,long cashAmount, String DateOfInvoice,  String deadline,String dealerName, String Short) {
		Invoice invoice = new Invoice();
		invoice = createInvoice(invoiceNo, amount,cashAmount, DateOfInvoice,deadline, dealerName,Short);
		DataClass.saveInvoice(invoice);

	}

}