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
    public void testPredictOptimalTicketPrice(Integer expectedAttendees, Integer venueCapacity, Double basePrice,
            Boolean isWeekend, Boolean isOpenBar, Integer audienceTypeIndex, Double historicalDemandFactor) {
    
        assumeTrue(expectedAttendees != null && expectedAttendees >= 0);
        assumeTrue(venueCapacity != null && venueCapacity > 0);
        assumeTrue(basePrice != null && Double.isFinite(basePrice) && basePrice >= 0);
        assumeTrue(isWeekend != null);
        assumeTrue(isOpenBar != null);
        assumeTrue(audienceTypeIndex != null && audienceTypeIndex >= 0);
        assumeTrue(historicalDemandFactor != null && historicalDemandFactor >= 0);
    
        AudienceType[] audienceTypes = AudienceType.values();
        assumeTrue(audienceTypeIndex < audienceTypes.length);
    
        AudienceType audienceType = audienceTypes[audienceTypeIndex];
    
        double predictedPrice = TicketPricePredictor.predictOptimalTicketPrice(
                expectedAttendees, venueCapacity, basePrice,
                isWeekend, isOpenBar, audienceType, historicalDemandFactor);
        
        assertTrue(predictedPrice >= basePrice * 0.6); 
    }
    
}
