package test;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import dataSource.DataDelete;
import utilities.DataUtilities;
import utilities.utils;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DataDelete.ChequeDelete("1212");

//		int year = Year.now().getValue();
//		System.out.println(Integer.toString(year));
//
//		List<List<String>> l = utils.getWeeksDates("2020", "JANUARY");
//		
//		for (List<String> list : l) {
//			System.out.println(list.get(0)+"-"+list.get(1));
//			
//		}
//		List<String> list2 =  new ArrayList<String>();
//		list2.add(weekStart);
//		
//		list.add(2,weekStart,weekStop);
//		i = i+i++;

		// System.out.println(DataUtilities.getInvoiceDetails("gjgh",
		// "545").getString("InvoiceNo"));

//		ResultSet rs = DataUpdate.btnupdateStart_invoice("janet", "646");
//		//System.out.println(rs.next());
//		
//		UPDATE cheque
//		SET `InvoiceNo` = REPLACE(`InvoiceNo`,'2000','') 
//		WHERE  `InvoiceNo` like '%2000%' AND `ChequeNo` = '2010';

//		System.out.println(DataUtilities.getSplited(","
//				+ "456,,6595,,5265").toString());
		// System.out.println(utils.trim("456,,6595,,5265", ","));

		// DataUtilities.isInvoiceExist(dealerName, invoiceNo)
///*		try {
//			// Connection conn = null;
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rZMON02UdD", "rZMON02UdD",
//					"MwyFAZB81a");
//			JOptionPane.showMessageDialog(null, "successfully connected");
//			// Connection conn=DriverManager.getConnection("jdbc:mysql:E://my
//			// projects//InvoiZ//InvoiZ//Resourse//invoiz.sql","thushara","123qwe");
//			// JOptionPane.showMessageDialog(null,"Connection Successful");
//			String sql = "INSERT INTO `dealer` (`DealerId`, `DealerName`, `DealerTel`, `DealerPeriod`, `DealerCode`) VALUES (NULL, 'jhahna', '546165', '45', 'jsda');";
//			Statement stmt = null;
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "successfully inserted");
//
//			
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//			JOptionPane.showMessageDialog(null,
//					"Oops, something went wrong while Connecting to Database Please check Xampp is running!");
//
//		}
//
//	}*/
	}
}