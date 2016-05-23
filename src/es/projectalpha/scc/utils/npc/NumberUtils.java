package es.projectalpha.scc.utils.npc;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public final class NumberUtils {

	public static String roundExact(double value){
		return roundExact("#.#", value);
	}

	public static String roundExact(float value){
		return roundExact("#.#", value);
	}

	public static String roundExact(int decimalPlaces, double value){
		return roundExact("#." + org.apache.commons.lang.StringUtils.repeat("#", decimalPlaces), value);
	}

	public static String roundExact(int decimalPlaces, float value){
		return roundExact("#." + org.apache.commons.lang.StringUtils.repeat("#", decimalPlaces), value);
	}

	public static String roundExact(String format, double value){
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(value);
	}

	public static String roundExact(String format, float value){
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(value);
	}

	private NumberUtils(){
	}
}
