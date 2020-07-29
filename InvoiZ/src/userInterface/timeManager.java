package userInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;



public class timeManager {

	public static String addDays(int in) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();

		//String today = sdf.format(c.getTime());
		
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, in); // Adding 5 days

		String output = sdf.format(c.getTime());
	

		return output;
	}
	public static String DateplusDays (String Date, int days) {
		LocalDate localDate = LocalDate.parse(Date);
		LocalDate date2 = localDate.plusDays(days);
		return date2.toString();
	}
	

	public static String today() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();

		String today = sdf.format(c.getTime());
		
		
		return today;
		
	}
	public static String getweek() {	
		Calendar c = GregorianCalendar.getInstance();

		System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

		// Set the calendar to monday of the current week
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

		// Print dates of the current week starting on Monday
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String startDate = "", endDate = "";

		startDate = df.format(c.getTime());
		c.add(Calendar.DATE, -6);
		endDate = df.format(c.getTime());

		System.out.println("Start Date = " + startDate);
		System.out.println("End Date = " + endDate);
		return endDate;
		
	}
	
	
}
