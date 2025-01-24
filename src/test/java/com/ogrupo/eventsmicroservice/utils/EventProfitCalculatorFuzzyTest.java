package com.ogrupo.eventsmicroservice.utils;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;

import org.junit.runner.RunWith;

import com.ogrupo.eventsmicroservice.generators.PositiveDoubleGenerator;
import com.pholser.junit.quickcheck.From;

import static org.junit.Assume.assumeTrue;
import static org.junit.Assert.assertTrue;

@RunWith(JQF.class)
public class EventProfitCalculatorFuzzyTest {

    private final EventProfitCalculator calculator = new EventProfitCalculator();

    @Fuzz
    public void fuzzEstimateProfit(int numberOfParticipants, 
                                   @From(PositiveDoubleGenerator.class) double pricePerParticipant, 
                                   boolean isWeekday, 
                                   @From(PositiveDoubleGenerator.class) double estimatedCost) {

        assumeTrue(numberOfParticipants >= 0);

        double profit = calculator.estimateProfit(numberOfParticipants, pricePerParticipant, isWeekday, estimatedCost);

        assertTrue("Profit should not exceed total revenue",
                profit <= numberOfParticipants * (isWeekday ? pricePerParticipant : pricePerParticipant * 1.2));
    }
}
    