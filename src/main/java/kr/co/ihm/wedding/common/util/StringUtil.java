package kr.co.ihm.wedding.common.util;

import java.util.Calendar;

/**
 * @author hdkim
 * @desc String 관련 Common Utils
 */
public class StringUtil {
	
	/**
	 * 빈문자열 검사
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input) {
        return (input == null || "".equals(input));
	}
	
    /**
     * 현재 시간을 YYYYMMDDHH24MISS 포맷으로 반환
     * @return
     */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		String nResult;

		sb.append(cal.get(Calendar.YEAR));

		if (cal.get(Calendar.MONTH) + 1 < 10) {
			sb.append(0);
			sb.append(cal.get(Calendar.MONTH) + 1);
		} else {
			sb.append(cal.get(Calendar.MONTH) + 1);
		}

		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			sb.append(0);
			sb.append(cal.get(Calendar.DAY_OF_MONTH));
		} else {
			sb.append(cal.get(Calendar.DAY_OF_MONTH));
		}

		if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
			sb.append(0);
			sb.append(cal.get(Calendar.HOUR_OF_DAY));
		} else {
			sb.append(cal.get(Calendar.HOUR_OF_DAY));
		}

		if (cal.get(Calendar.MINUTE) < 10) {
			sb.append(0);
			sb.append(cal.get(Calendar.MINUTE));
		} else {
			sb.append(cal.get(Calendar.MINUTE));
		}

		if (cal.get(Calendar.SECOND) < 10) {
			sb.append(0);
			sb.append(cal.get(Calendar.SECOND));
		} else {
			sb.append(cal.get(Calendar.SECOND));
		}
		nResult = sb.toString();

		return nResult;
	}
}
