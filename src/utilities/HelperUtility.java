package utilities;

import java.util.Random;

public class HelperUtility {

	public static boolean isMissing(String s) {
		if (s == null || s.trim().length() <= 0)
			return true;
		return false;
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	public static boolean isDouble(String str) {
		double x = Double.parseDouble(str);
		if (x == (int) x)
			return false;
		return true;
	}

	public static boolean isNumber(String s) {

		if (isInteger(s) || isDouble(s))
			return true;
		return false;

	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
