package utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class utils {

	public static Map<String, List<Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		Map<String, List<Object>> map = new HashMap<>(columns);

		for (int i = 1; i <= columns; ++i) {
			map.put(md.getColumnName(i), new ArrayList<>());

		}
		while (rs.next()) {
			for (int i = 1; i <= columns; ++i) {

				map.get(md.getColumnName(i)).add(rs.getObject(i));
			}
		}

		return map;
	}

	public static String deleteSelected(String s, String removeString) {

		System.out.println(s);
		s = s.replaceAll(removeString, "");
		s = s.replaceAll(",+$", "");
		s = s.replaceAll(",,", ",");
		s = s.replaceAll("^,+", "");
		System.out.println(s);
		if (s.length() > 0) {
			return s;
		} else {
			return " ";
		}
	}

	public static int MonthToInt(String S) {

		switch (S) {
		case "January":
			return 1;

		case "February":
			return 2;

		case "March":
			return 3;

		case "April":
			return 4;

		case "May":
			return 5;

		case "June":
			return 6;

		case "July":
			return 7;

		case "August":
			return 8;

		case "September":
			return 9;

		case "October":
			return 10;

		case "November":
			return 11;

		case "December":
			return 12;

		default:
			return 1;
		}

	}

	YearMonth ym = YearMonth.of(2020, Month.FEBRUARY);

	public static List<List<String>> getWeeksDates(int year, String month) {

		List<List<String>> list1;
		try {
			Month m = Month.valueOf(month);
			YearMonth ym = YearMonth.of(year, m);
			LocalDate firstOfMonth = ym.atDay(1);
			TemporalAdjuster ta = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
			LocalDate previousOrSameMonday = firstOfMonth.with(ta);
			LocalDate endOfMonth = ym.atEndOfMonth();
			LocalDate weekStart = previousOrSameMonday;
			list1 = new ArrayList<List<String>>();

			while (!weekStart.isAfter(endOfMonth)) {
				LocalDate weekStop = weekStart.plusDays(6);

				// System.out.println("Week: " + " " + weekStart + " to " + weekStop);

				List<String> list2 = new ArrayList<String>();
				list2.add(weekStart.toString());
				list2.add(weekStop.toString());
				list1.add(list2);

				// Set up the next loop.
				weekStart = weekStart.plusWeeks(1);

			}
			return list1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		

	}

	public static String concatDate(String s1, String newString) {
		if (s1 == null) {
			return newString;
		} else {
			return (s1 + " -- " + newString);
		}

	}

	public static String[] splitDate(String s, String s1) {
		if (s == null) {
			return null;
		} else {
			return s.split(s1);
		}

	}

}
