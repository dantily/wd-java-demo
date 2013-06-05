import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 */

/**
 * @author wudan_js
 *
 */
public class DateUtil {
	static DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	// 20130516144813
	public static boolean compareTime(String submitTime,String operateTime){
		try {
			Date submitDate = format.parse(submitTime);
			Date operateDate = format.parse(operateTime);
			
			Calendar calendarS = Calendar.getInstance();
			calendarS.setTime(submitDate);
			
			calendarS.add(Calendar.MINUTE, -5);
			
			long millisS=calendarS.getTimeInMillis();
			long millisO=operateDate.getTime();
			
			if(millisO>millisS){
				return true;
			}else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
