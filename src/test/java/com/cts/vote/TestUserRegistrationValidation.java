package com.cts.vote;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cts.vote.validation.UserRegistrationValidator;

/**
 * Junit test case class for User registration process
 * 
 * @author Aniruddha Anikhindi
 *
 */
public class TestUserRegistrationValidation {

	private UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
	private final String spChars = "$#@&";
	private final String validName = "abcd";

	@Test
	public void testValidateFirstName() {
		assertFalse(userRegistrationValidator.validateFirstName(spChars));
		assertTrue(userRegistrationValidator.validateFirstName(validName));
	}

	@Test
	public void testValidateLastName() {
		assertFalse(userRegistrationValidator.validateLastName(spChars));
		assertTrue(userRegistrationValidator.validateLastName(validName));
	}

	@Test
	public void testValidateUserName() {
		assertFalse(userRegistrationValidator.validateUserName(spChars));
		assertTrue(userRegistrationValidator.validateUserName(validName));
	}

	@Test
	public void testValidateEmail() {
		assertTrue(userRegistrationValidator.validateEmail("aniruddha.anikhindi@cognizant.com"));
		assertFalse(userRegistrationValidator.validateEmail("aniruddha.anikhindicognizant.com"));
	}

}
