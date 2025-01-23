package com.ogrupo.eventsmicroservice.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
public class TicketPricePredictorFuzzyTest {

    @Fuzz
    public void testPredictOptimalTicketPrice(int expectedAttendees, int venueCapacity, double basePrice,
            boolean isWeekend, boolean isOpenBar, int audienceTypeIndex, double historicalDemandFactor) {

        //assumeTrue(expectedAttendees >= 0); 
        //assumeTrue(venueCapacity > 0); 
        //assumeTrue(Double.isFinite(basePrice) && basePrice >= 0); 
        //assumeTrue(historicalDemandFactor >= 0); 

        AudienceType[] audienceTypes = AudienceType.values();
        assumeTrue(audienceTypeIndex >= 0 && audienceTypeIndex < audienceTypes.length);

        AudienceType audienceType = audienceTypes[audienceTypeIndex];

        double predictedPrice = TicketPricePredictor.predictOptimalTicketPrice(
                expectedAttendees, venueCapacity, basePrice,
                isWeekend, isOpenBar, audienceType, historicalDemandFactor);

        System.out.println(predictedPrice);
        assertTrue(predictedPrice > basePrice * 0.6); 
    }
}
