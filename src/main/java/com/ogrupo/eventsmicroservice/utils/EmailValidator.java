package com.ogrupo.eventsmicroservice.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidator {

  // Expressão regular para validar emails
  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  public static boolean isValidEmail(String email) {
      if (email == null) {
          return false; // Null não é um email válido
      }
      Matcher matcher = EMAIL_PATTERN.matcher(email);
      return matcher.matches();
  }
}