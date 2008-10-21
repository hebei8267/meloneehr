import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate {
	private final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	private Date nowDate = new Date();

	/**
	 * 取得系统当前的日期
	 * 
	 * @return
	 */
	public String getNowDate() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

		return sf.format(nowDate);
	}

	/**
	 * 取得系统当前的星期数
	 * 
	 * @return
	 */
	public String getNowWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		return dayNames[(calendar.get(Calendar.DAY_OF_WEEK)) - 1];
	}
}
