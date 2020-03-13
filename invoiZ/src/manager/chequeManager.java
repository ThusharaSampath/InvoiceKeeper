package manager;

import dataSource.DataClass;
import entities.Cheque;

public class chequeManager {

	private static chequeManager instance = new chequeManager();

	private chequeManager() {
	}

	public static chequeManager getInstance() {
		return instance;
	}

//removed Expire date TODO expire date of cheque
	public static Cheque createCheque(String chequeNo_C, Long DealerID, String DealerName, String invoiceNo_C,
			long chequeAmount, String chequeDate, int chequeExpireStatus, int chequePassStatus) {
		Cheque cheque = new Cheque();
		cheque.setChequeNo(chequeNo_C);
		cheque.setDealerID(DealerID);
		cheque.setInvoiceNO(invoiceNo_C);
		cheque.setChequeAmount(chequeAmount);
		cheque.setDealerName(DealerName);
		cheque.setChequeDate(chequeDate);
		// cheque.setChequeExpireDate(chequeExpireDate);

		cheque.setChequePassStatus(chequePassStatus);
		cheque.setChequeExpireStatus(chequeExpireStatus);
		return cheque;
	}
	// TODO we need to inizialy put expire status to false and pass status to false

	public static void saveCheque(String chequeNo_C, String DealerName, String invoiceNo_C, long chequeAmount,
			String chequeDate) {
		// boolean chequeExpireStatus,boolean chequePassStatus)
		long DealerID = DataClass.getDealerId(DealerName);
		Cheque cheque = new Cheque();
		cheque = createCheque(chequeNo_C, DealerID, DealerName, invoiceNo_C, chequeAmount, chequeDate, 0, 0);
		DataClass.saveCheque(cheque);
	}
}
