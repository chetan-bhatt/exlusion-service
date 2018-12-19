package com.ex.util;

/**
 * The {@code Messages} interface to accommodate all the response messages.
 * 
 * @author cbhatt
 *
 */
public interface Messages {

	public static final String REQUEST_BODY_EMPTY = "Request body is empty";

	public static final String USERNAME_INCORRECT_FORMAT = "Username must be alphanumeric without space";

	public static final String PASSWORD_INCORRECT_FORMAT = "Password must be at least four characters, at least one lower case character, at least one upper case character, at least one number";

	public static final String DATE_OF_BIRTH_INCORRECT_FORMAT = "Date of Birth must be in ISO-8601 format i.e YYYY-MM-DD";

	public static final String USER_IN_EXCLUDED_LIST = "User is in the excluded list";

	public static final String USER_ALREADY_EXIST = "User already exist";
}
