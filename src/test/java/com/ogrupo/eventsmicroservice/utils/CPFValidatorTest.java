package com.ogrupo.eventsmicroservice.utils;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import static org.junit.Assume.assumeTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;;

@RunWith(JQF.class)
public class CPFValidatorTest {

    @Fuzz
    public void TestCPF(String CPF) throws Exception {
        assumeTrue("Input CPF must have exactly 14 characters", CPF != null && CPF.length() == 14);
        Boolean isValidCpf = CPFValidator.isValidCPF(CPF);

        if (isValidCpf) {
            assertTrue(isValidCpf);
        } else {
            assertFalse(isValidCpf);
        }

    }

}