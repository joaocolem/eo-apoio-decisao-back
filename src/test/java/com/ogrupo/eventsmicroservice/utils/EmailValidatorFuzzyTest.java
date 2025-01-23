package com.ogrupo.eventsmicroservice.utils;

import static org.junit.jupiter.api.Assertions.*;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class EmailValidatorFuzzyTest {

  @Fuzz
  public void testIsValidEmail(String email) {

      boolean result = EmailValidator.isValidEmail(email);

      if (result) {
          assertTrue(email.contains("@"), "Valid email should contain '@'");
          assertTrue(email.indexOf('@') < email.lastIndexOf('.'), "Valid email should have '.' after '@'");
      }
  }
}