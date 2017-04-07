package com.ms.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



public final class DateTool {
	/**
	 * 获取当前系统日期 返回 8位 like 20050101
	 * 
	 * @return
	 */
	public static String getToday() {
		return GetToday();
	}

	/**
	 * 获取输入日期的下一天 返回 8位 like 20050101
	 * 
	 * @param today
	 * @return
	 */
	public static String getNextDay(String day) {
		return getNextDay(day, 1);
	}

	/**
	 * 获取输入日期的下 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getNextDay(String day, int n) {
		if (day == null || "".equals(day) || day.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = day.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = day.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = day.substring(6, 8);
			int dday = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, dday);
			cal.add(Calendar.DATE, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);

		}
	}

	/**
	 * 获取输入 月份的下 n 月份 返回 6位 like 200501
	 * 
	 * @param month
	 *            like 200404
	 * @param n
	 * @return
	 */
	public static String getNextMonth(String month, int n) {
		if (month == null || "".equals(month) || month.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		try {
			String sYear = month.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = month.substring(4, 6);
			int mon = Integer.parseInt(sMonth);
			Calendar cal = Calendar.getInstance();
			cal.set(year, mon - 1, 1);
			cal.add(Calendar.MARCH, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行月份运算时输入得参数不符合系统规格." + e);

		}
	}

	/**
	 * 返回星期 0 星期天 6 星期陆
	 * 
	 * @param date
	 *            20040101
	 * @return
	 */
	public static int getWeekday(String date) {
		if (date == null || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday;

	}

	/**
	 * 获取输入 月份的前 n 月份 返回 6位 like 200501
	 * 
	 * @param month
	 * @param n
	 * @return
	 */
	public static String getPreviousMonth(String month, int n) {
		return getNextMonth(month, -n);
	}

	/**
	 * 获取输入日期的嵌 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getPreviousDay(String day, int n) {
		return getNextDay(day, -n);
	}

	/**
	 * 获取当前系统时间 返回 12:12:12
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return GetCurrentTime();
	}

	/**
	 * 获取当前系统时间 返回 12:12:12
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * @param ctm
	 *            long 系统时间
	 * @param format
	 * @return
	 */
	public static String getCurrentTimeMillisAsString(long ctm, String format) {
		Date date = new Date(ctm);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 获取当前系统时间 返回 yyyyMMddHHmmssS
	 * 
	 * @return
	 */
	public static String getCurrentTimeMillisAsString() {
		long currTimeM = getCurrentTimeMillis();
		return getCurrentTimeMillisAsString(currTimeM, "yyyyMMddHHmmss");
	}

	public static String getCurrentTimePercentAsString() {
		long currTimeM = getCurrentTimeMillis();
		return getCurrentTimeMillisAsString(currTimeM, "yyyyMMddHHmmssSSS")
				.substring(0, 16);
	}

	/**
	 * 获取当前系统时间 参数 format yyyyMMddHHmmssS 其中的部分 返回与 format格式相同的时间
	 * 
	 * @return
	 */
	public static String getCurrentTimeMillisAsString(String format) {
		long currTimeM = getCurrentTimeMillis();
		return getCurrentTimeMillisAsString(currTimeM, format);
	}

	/**
	 * 得到指定时间的指定类型的n值后的时间
	 * 
	 * @param cur_date:yyyymmdd
	 * @param cur_time:hhmmss
	 * @param flag:1:second,2:minute,3:hour,4:day,5:month,6:year
	 * @param n:
	 * @return yyyymmdd hhmmss
	 */
	public static String getNextTime(String cur_date, String cur_time,
			int flag, int n) {
		if (cur_date == null || "".equals(cur_date) || cur_date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		if (cur_time == null || "".equals(cur_time) || cur_time.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = cur_date.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = cur_date.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = cur_date.substring(6, 8);
			int day = Integer.parseInt(sDay);
			String sHour = cur_time.substring(0, 2);
			int hour = Integer.parseInt(sHour);
			String sMin = cur_time.substring(2, 4);
			int min = Integer.parseInt(sMin);
			String sSec = cur_time.substring(4, 6);
			int sec = Integer.parseInt(sSec);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day, hour, min, sec);
			switch (flag) {
			case 1:
				cal.add(Calendar.SECOND, n);
				break;
			case 2:
				cal.add(Calendar.MINUTE, n);
				break;
			case 3:
				cal.add(Calendar.HOUR, n);
				break;
			case 4:
				cal.add(Calendar.DATE, n);
				break;
			case 5:
				cal.add(Calendar.MONTH, n);
				break;
			case 6:
				cal.add(Calendar.YEAR, n);
				break;
			default:
				break;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);

		}
	}

	/**
	 * 把日期转化成为另一种格式
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getDateByOtherFormatter(String date,
			String formatter_from, String formatter_to) {
		try {
			if (date != null && !date.trim().equals("")) {
				SimpleDateFormat dateFormatter_from = new SimpleDateFormat(
						formatter_from);
				Date date_from = dateFormatter_from.parse(date);
				SimpleDateFormat dateFormatter_to = new SimpleDateFormat(
						formatter_to);
				String date_to = dateFormatter_to.format(date_from);
				return date_to;
			} else {
				return "";
			}
		} 
		catch (RuntimeException e) {
			throw new RuntimeException("日期格式错误！");
		} catch (ParseException e) {
			throw new RuntimeException("日期格式错误！");
		}
	}

	/**
	 * 得到2个日期之间间隔的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differ(String date1, String date2) {
		try {
			Date date11 = dateFormatter.parse(date1);
			Date date22 = dateFormatter.parse(date2);
			int day = differ(date11, date22);
			return day;
		} catch (Exception e) {
			throw new RuntimeException("日期格式错误！");
		}
	}

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyyMMdd");

	private static int differ(Date date1, Date date2) {
		// long day = date1.getTime() / (24*60*60*1000) - date2.getTime() /
		// (24*60*60*1000);
		long day = date2.getTime() / 86400000 - date1.getTime() / 86400000; // 用立即数，减少乘法计算的开销
		return (int) day;
	}

	/**
	 * 得到2个日期之间间隔的天数、小时数、分钟数、秒数的数组
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int[] differTime(String dateTime1, String dateTime2) {
		try {
			Date dateTime11 = dateTimeFormatter.parse(dateTime1);
			Date dateTime22 = dateTimeFormatter.parse(dateTime2);
			int[] diffDateTimes = differTime(dateTime11, dateTime22);
			return diffDateTimes;
		} catch (Exception e) {
			throw new RuntimeException("日期格式错误！");
		}
	}

	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static int[] differTime(Date dateTime1, Date dateTime2) {
		int[] diffDateTimes = new int[4];
		//
		// long dateTime = date1.getTime() / (24*60*60*1000) - date2.getTime() /
		// (24*60*60*1000);
		long dateTime = dateTime2.getTime() / 1000 - dateTime1.getTime() / 1000; // 用立即数，减少乘法计算的开销
		diffDateTimes[3] = (int) (dateTime % 60);// 秒数
		dateTime = dateTime / 60;
		diffDateTimes[2] = (int) (dateTime % 60);// 分钟数
		dateTime = dateTime / 60;
		diffDateTimes[1] = (int) (dateTime % 24);// 小时数
		diffDateTimes[0] = (int) (dateTime / 24);// 天数
		//
		return diffDateTimes;
	}

	/**
	 * 得到2个日期之间的间隔是否超过timeLimited限制
	 * 
	 * @param dateTime1---20100325160342---yyyyMMddHHmmss
	 * @param dateTime2---20100325160342---yyyyMMddHHmmss
	 * @param timeLimited---25160342---ddHHmmss---天、小时、分钟、秒各占2位
	 * @return
	 */
	public static boolean checkTimeLimited(String dateTime1, String dateTime2,
			String timeLimited) {
		if (timeLimited == null || timeLimited.trim().length() < 2) {
			// 时间限制为空，或者时间限制位数小于2，说明当前检查的位数长度不够检查，已经检查完毕，此时认为是没有超过时间限制
			return false;
		} else {
			timeLimited = timeLimited.trim();
			//
			int[] diffDateTimes = DateTool.differTime(dateTime1, dateTime2);
			//
			return checkTimeLimited(diffDateTimes, timeLimited);
		}
	}

	private static boolean checkTimeLimited(int[] diffDateTimes,
			String timeLimited) {
		int limited, diffValue;
		int i = 0;
		do {
			limited = Integer.parseInt(timeLimited.substring(0, 2));
			diffValue = diffDateTimes[i];
			// log.debug("limited:"+limited+"---diffValue:"+diffValue);
			if (diffValue > limited) {
				// 接收时分秒数大于限制的时分秒数
				return true;
			} else if (diffValue < limited) {
				// 接收时分秒数大于限制的时分秒数
				return false;
			} else if (timeLimited.length() == 2) {
				// 检查完后，时间限制位数等于2，说明当前检查的位数长度不够检查，已经检查完毕，此时认为是没有超过时间限制
				return false;
			} else {
				i++;
				timeLimited = timeLimited.substring(2);
			}
		} while (timeLimited.length() >= 2);
		// 跳出循环，时间限制位数小于2，说明当前检查的位数长度不够检查，已经检查完毕，此时认为是没有超过时间限制
		return false;
	}

	/**
	 * 得到当前时间在每周的天数（1-7）、小时数、分钟数、秒数的数组
	 * 
	 * @return
	 */
	public static int[] getWeekdayAndTime4CurrentDateTime() {
		try {
			//date---20100325160342---yyyyMMddHHmmss
			String dateTime = DateTool.getCurrentTimeMillisAsString();
			//
			int[] weekdaytimes = new int[4];
			//
			weekdaytimes[0] = DateTool.getWeekday(dateTime.substring(0,8));// 日期在每周的天数
			if(weekdaytimes[0] < 1 || weekdaytimes[0] > 6){
				weekdaytimes[0] = 7;
			}
			//
			weekdaytimes[1] = Integer.parseInt(dateTime.substring(8,10));// 小时数
			weekdaytimes[2] = Integer.parseInt(dateTime.substring(10,12));// 分钟数
			weekdaytimes[3] = Integer.parseInt(dateTime.substring(12,14));// 秒数
			//
			return weekdaytimes;
		} 
		catch (RuntimeException  e) {
			throw new RuntimeException("日期格式错误！");
		}
	}

	public static String getDateByformat(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);

		return format.format(date);
	}

	public static void main(String[] args) {

	}

	/**
	 * 获得今天日期所在年的数字
	 * 
	 * @return String
	 */
	public static int getThisYear() {
		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(todayDate);
		return todayCal.get(Calendar.YEAR);
	}

	/**
	 * 获得今天日期所在月的数字
	 * 
	 * 返回值是从0开始计算的，请在使用时注意
	 * 
	 * @return String
	 */
	public static int getThisMonth() {
		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(todayDate);
		return todayCal.get(Calendar.MONTH);
	}

	/**
	 * 获得今天日期所在周的数字
	 * 
	 * @return String
	 */
	public static int getThisWeek() {
		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(todayDate);
		return todayCal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 根据年份和星期序号获取此星期7天的每天的日期，从周一开始
	 * 
	 * @param year
	 * @param week
	 * @return String[]
	 */
	public static String[] getWeeks(int year, int week) {

		Calendar cal = Calendar.getInstance();

		int day = cal.get(Calendar.DAY_OF_WEEK);

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);

		/*
		 * 转到本周一
		 */
		cal.add(Calendar.DAY_OF_MONTH, 2 - day);

		String[] weeks = new String[7];
		for (int i = 0; i < 7; i++) {
			if (i > 0) {
				cal.add(Calendar.DATE, 1);
			}
			int y = cal.get(Calendar.YEAR);
			int m = cal.get(Calendar.MONTH) + 1;
			int d = cal.get(Calendar.DATE);
			weeks[i] = String.valueOf(y)
					+ (m < 10 ? ("0" + m) : String.valueOf(m))
					+ (d < 10 ? ("0" + d) : String.valueOf(d));
			// System.out.println(weeks[i]);
		}

		return weeks;
	}

	private final static String[][]  StaticSeasonsMonth = {
		{"01","02","03"},
		{"04","05","06"},
		{"07","08","09"},
		{"10","11","12"},
	};
	/**
	 * 根据日期获取此季度3个月的每月的月份
	 * 
	 * @param date
	 * @return String[]
	 */
	public static String[] getSeasons(String date) {
		String[] seasons = new String[3];
		
		String year = date.substring(0,4);
		String month = date.substring(5,6);
		int season = (Integer.parseInt(month)-1)/3;
		for(int i = 0 ; i < 3 ; i++){
			seasons[i] = year + StaticSeasonsMonth[season][i];
		}
		
		return seasons;
	}
	
	public static String GetToday() {
	    Calendar calendar = Calendar.getInstance();
	    TimeZone timezone = TimeZone.getDefault();
	    calendar.setTimeZone(timezone);
	    String s = String.valueOf(calendar.get(1));
	    int i = calendar.get(2) + 1;
	    String s1 = "";
	    String s2 = "";
	    if (i < 10)
	      s1 = String.valueOf("0" + i);
	    else
	      s1 = String.valueOf(i);
	    int j = calendar.get(5);
	    if (j < 10)
	      s2 = String.valueOf("0" + j);
	    else
	      s2 = String.valueOf(j);
	    return s + s1 + s2;
	  }
	
	 public static String GetCurrentTime() {
		    StringBuffer result = new StringBuffer();
		    Calendar calendar = Calendar.getInstance();
		    TimeZone timezone = TimeZone.getDefault();
		    calendar.setTimeZone(timezone);
		    int h = calendar.get(11);
		    int m = calendar.get(12);
		    int s = calendar.get(13);
		    if (h < 10) {
		      result.append("0");
		    }
		    result.append(h);
		    result.append(":");
		    if (m < 10) {
		      result.append("0");
		    }
		    result.append(m);
		    result.append(":");
		    if (s < 10) {
		      result.append("0");
		    }
		    result.append(s);
		    return result.toString();
		  }

}