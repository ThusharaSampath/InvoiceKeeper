package dataSource;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import utilities.DataUtilities;

public class DataUpdate {

	public static void add_chequeNo_to_invoice(String DealerName,String InvoiceNo , String ChequeNo , double ChequeAmount , String ChequeDate ) {
		if (DataUtilities.isInvoiceExist(DealerName, InvoiceNo)) { // check is invoice exist
			try {
				ResultSet rs1 = DataUtilities.getInvoiceDetails(DealerName, InvoiceNo);
				String oldChequeNos= rs1.getString("ChequeNO");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "You need to save this invoice first!");
		}
		
	}
	
	
}
