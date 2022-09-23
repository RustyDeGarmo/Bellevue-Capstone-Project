//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordUtil {

	private static final BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

	/**
	 * This method use to encrypt the plain password given by the user
	 *
	 * @param password plain String
	 * @return encrypted string
	 */

	// Encrypt password
	public static String encryptPassword(String password) {
		return encryptor.encryptPassword(password);
	}

	/**
	 * This method use to check if the given password matches with the saved
	 * password in db
	 *
	 * @param typedPass
	 * @param encryptedPass
	 * @return boolean value
	 */
	
	// Password validator
	public static boolean isValidPass(String typedPass, String encryptedPass) {
		return encryptor.checkPassword(typedPass, encryptedPass);
	}

}