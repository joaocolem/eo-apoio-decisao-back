package com.ogrupo.eventsmicroservice.utils;

import static org.junit.jupiter.api.Assertions.*;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assumptions;

@RunWith(JQF.class)
public class EventProfitCalculatorFuzzyTest {

    @Fuzz
    public void testEstimateProfit(int numberOfParticipants, double pricePerParticipant, boolean isWeekday, double estimatedCost) {
        EventProfitCalculator calculator = new EventProfitCalculator();
    
        double profit = calculator.estimateProfit(numberOfParticipants, pricePerParticipant, isWeekday, estimatedCost);
        assertTrue(profit <= (numberOfParticipants * pricePerParticipant), "Profit should not exceed gross revenue");
    }
}
    