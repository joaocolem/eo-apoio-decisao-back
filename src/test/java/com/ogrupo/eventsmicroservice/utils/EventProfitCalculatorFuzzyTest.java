package com.ogrupo.eventsmicroservice.utils;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;

import org.junit.runner.RunWith;

import com.ogrupo.eventsmicroservice.generators.IntGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.java.lang.BooleanGenerator;
import com.pholser.junit.quickcheck.generator.java.lang.DoubleGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JQF.class)
public class EventProfitCalculatorFuzzyTest {

    private final EventProfitCalculator calculator = new EventProfitCalculator();

    @Fuzz
    public void fuzzEstimateProfit(@From(IntGenerator.class) int numberOfParticipants, 
                                   @From(DoubleGenerator.class) double pricePerParticipant, 
                                   @From(BooleanGenerator.class) boolean isWeekday, 
                                   @From(DoubleGenerator.class) double estimatedCost) {
        
        double profit = calculator.estimateProfit(numberOfParticipants, pricePerParticipant, isWeekday, estimatedCost);
        
        if (numberOfParticipants < 0 || pricePerParticipant < 0 || estimatedCost < 0) {
            assertEquals(0, profit, 0);
        } else {
            assertTrue("Profit should not exceed total revenue",
                    profit <= numberOfParticipants * (isWeekday ? pricePerParticipant : pricePerParticipant * 1.2));
        }
    }
}
    