package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: RegularExpressionValidator - Patterns to check if input strings match what we require
 ***********************************************************************/

public class RegularExpressionValidator {

	private Pattern emailPattern;
	private Pattern alphabetPattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	private static final String ALPHABET_PATTERN = "^[A-z]+$";
	
	public RegularExpressionValidator() {
		emailPattern = Pattern.compile(EMAIL_PATTERN);
		alphabetPattern = Pattern.compile(ALPHABET_PATTERN);	
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateEmail(final String hex) {

		matcher = emailPattern.matcher(hex);
		return matcher.matches();

	}
	public boolean validateAlphabetic(final String hex) {
		
		matcher = alphabetPattern.matcher(hex);
		return matcher.matches();
	}
}