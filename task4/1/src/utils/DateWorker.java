package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWorker {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");

	public static boolean isAfterDate(Date pDate, Date comparableDate) {
		return comparableDate.after(pDate);
	}

	public static boolean isBetweenDate(Date pDate, Date startComparableDate, Date endComparableDate) {
		return startComparableDate.before(pDate) && endComparableDate.after(pDate);
	}

	public static boolean isEqualsDate(Date firstDate, Date secondDate) {
		return firstDate.equals(secondDate);
	}

	public static boolean isSubInterval(Date startDateMain, Date endSateMain, Date startDateSub, Date endDateSub) {
		return startDateMain.before(startDateSub) && endSateMain.after(endDateSub);
	}
	public static Date createDate(String pStringDate) throws ParseException{
		return dateFormat.parse(pStringDate);
	}
}
