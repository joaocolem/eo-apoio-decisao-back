package com.ogrupo.eventsmicroservice.utils;

import static org.junit.jupiter.api.Assertions.*;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assumptions;

@RunWith(JQF.class)
public class TicketPricePredictorFuzzyTest {

  @Fuzz
  public void testPredictOptimalTicketPrice(int expectedAttendees, int venueCapacity, double basePrice, boolean isWeekend, boolean isOpenBar, String audienceType, double historicalDemandFactor) {
      Assumptions.assumeTrue(expectedAttendees >= 0, "Expected attendees must be non-negative");
      Assumptions.assumeTrue(venueCapacity > 0, "Venue capacity must be positive");
      Assumptions.assumeTrue(Double.isFinite(basePrice) && basePrice >= 0, "Base price must be a non-negative finite number");
      Assumptions.assumeTrue(historicalDemandFactor >= 0, "Historical demand factor must be non-negative");

      double predictedPrice = TicketPricePredictor.predictOptimalTicketPrice(expectedAttendees, venueCapacity, basePrice, isWeekend, isOpenBar, audienceType, historicalDemandFactor);

      // Verificações básicas
      assertTrue(predictedPrice >= basePrice * 0.6, "Predicted price should not be less than 60% of the base price");
  }
}