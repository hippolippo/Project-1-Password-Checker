import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
/*
1.	Length of password is less than 6 characters (class LengthException)
Message – The password must be at least 6 characters long
2.	Password doesn’t contain an uppercase alpha character (class NoUpperAlphaException)
Message – The password must contain at least one uppercase alphabetic character
3.	Password doesn’t contain a lowercase alpha character (class NoLowerAlphaException)
Message – The password must contain at least one lowercase alphabetic character
4.	Password doesn’t contain a numeric character (class NoDigitException)
Message – The password must contain at least one digit
5.	Password doesn’t contain a special character (class NoSpecialCharacterException)
Message – The password must contain at least one special character
6.	Password contains more than 2 of the same character in sequence (class InvalidSequenceException)
Message – The password cannot contain more than two of the same character in sequence.
7.	Password contains 6 to 9 characters which are otherwise valid (class WeakPasswordException)
Message – The password is OK but weak - it contains fewer than 10 characters.
8.	For GUI – check if Password and re-typed Password are identical (class UnmatchedException)
Message – The passwords do not match
Throw this exception from the GUI, not the utility class.

 */
	ArrayList<String> inputs;
	String[] outs;
	@Before
	public void setUp() throws Exception {
		String[] ins = {"abbbe", "aB1_png", "P0g@ng)", "abbbef", "ABBBEF", "P@ssW0rD", "Abbbef", "Abbbe1", "Abbb_1"};
		outs = new String[]{"abbbe The password must be at least 6 characters long",
				"abbbef The password must contain at least one uppercase alphabetic character",
				"ABBBEF The password must contain at least one lowercase alphabetic character",
				"Abbbef The password must contain at least one digit",
				"Abbbe1 The password must contain at least one special character",
				"Abbb_1 The password cannot contain more than two of the same character in sequence"};
		inputs = new ArrayList<String>(Arrays.asList(ins));
	}

	@After
	public void tearDown() throws Exception {
		inputs = null;
		outs = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		checkException("abbbe", "abbbef", new LengthException());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		checkException("abbbef", "ABBBEF", new NoUpperAlphaException());
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		checkException("ABBBEF", "Abbbef", new NoLowerAlphaException());
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			PasswordCheckerUtility.isWeakPassword("Ab1_00");
			assertTrue("Weak Password Not Excepted", false);
		}catch(WeakPasswordException e) {
			assertTrue("Weak Password Excepted", true);
		}
		try{
			PasswordCheckerUtility.isWeakPassword("Ab1_00123422");
			assertTrue("Non-Weak Password Not Excepted", true);
		}catch(WeakPasswordException e) {
			assertTrue("Non-Weak Password Excepted", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence(){
		checkException("Abbb_1", "Abcd_1", new InvalidSequenceException());
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		checkException("Abbbef", "Abbbe1", new NoDigitException());
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			PasswordCheckerUtility.isValidPassword("ABC_123_abc");
			assertTrue("Valid Password Passes", true);
		}catch(PasswordException e){
			assertTrue("Valid Password Fails",false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */

	private void check_password(String password, PasswordException exception, boolean should_except){
		try{
			PasswordCheckerUtility.isValidPassword(password);
			assertTrue("Didn't Throw Exception \"" + exception.getMessage() + "\"", !should_except);
		} catch (InvalidPasswordException e){
			if(e.getMessage().equals(exception.getMessage())){
				assertTrue("Threw Exception \"" + exception.getMessage() + "\"" + password, should_except);
			}else{
				assertTrue("Threw Exception Other than \"" + exception.getMessage() + "\" Threw \""+ e.getMessage() + "\"", !should_except);
			}
		}
	}

	private void checkException(String fail, String pass, PasswordException exception){
		check_password(fail, exception, true);
		check_password(pass, exception, false);
	}

	@Test
	public void testInvalidPasswords() {
		ArrayList<String> output = PasswordCheckerUtility.getInvalidPasswords(inputs);
		assertEquals("Size of output doesn't match expected output", output.size(), outs.length);
		for(int i = 0; i < output.size(); i++){
			assertEquals("String " + i + " Doesn't Match", (outs[i]), output.get(i));
		}
	}
}
