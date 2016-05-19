package es.projectalpha.scc.utils;

public class NumberUtils {

	public static int parseInt(String number){
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String parseString(Integer number){
		try {
			return String.valueOf(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "";
	}
}
