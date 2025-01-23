package com.ogrupo.eventsmicroservice.utils;

import java.util.regex.Pattern;

public class CPFValidator {
    public static boolean processExpression(String expression) throws Exception {
        CPFValidator.isValidCpfFormat(expression);
        String numericCPF = expression.replace(".", "").replace("-", "");
        return CPFValidator.isValidCPF(numericCPF);
    }

    public static boolean isValidCPF(String cpf) throws Exception {
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int checkDigit1 = 11 - (sum1 % 11);
        if (checkDigit1 >= 10) {
            checkDigit1 = 0;
        }

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int checkDigit2 = 11 - (sum2 % 11);
        if (checkDigit2 >= 10) {
            checkDigit2 = 0;
        }

        if (!(checkDigit1 == Character.getNumericValue(cpf.charAt(9))
                && checkDigit2 == Character.getNumericValue(cpf.charAt(10)))) {
            throw new Exception();
        }
        return true;
    }

    public static boolean isValidCpfFormat(String cpf) throws Exception {
        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            throw new Exception();
        }
        Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
        if (cpf == null || cpf.isEmpty()) {
            throw new Exception();
        }
        if (!CPF_PATTERN.matcher(cpf).matches()) {
            throw new Exception();
        }
        return true;
    }
}
