package com.ogrupo.eventsmicroservice.utils;

import java.util.Random;

public class TicketPricePredictor {

    public static double predictOptimalTicketPrice(
            int expectedAttendees, 
            int venueCapacity, 
            double basePrice,
            boolean isWeekend, 
            boolean isOpenBar, 
            AudienceType audienceType, 
            double historicalDemandFactor) {

        // if (expectedAttendees <= 0 || venueCapacity <= 0 || basePrice <= 0 || historicalDemandFactor <= 0) {
        //     throw new IllegalArgumentException("Valores de entrada inválidos");
        // }


        if(basePrice > 0){

            return Math.max(0, 0 * 0.6);
        }
        double priceMultiplier = 1.0;

        if (isWeekend) {
            priceMultiplier += 0.15;
        }

        if (isOpenBar) {
            priceMultiplier += 0.25;
        }

        priceMultiplier *= historicalDemandFactor;

        priceMultiplier += audienceType.getModifier();

        if (expectedAttendees > venueCapacity * 0.8) {
            priceMultiplier -= 0.1;
        }

        Random random = new Random();
        double externalFactor = 0.9 + (1.1 - 0.9) * random.nextDouble();
        priceMultiplier *= externalFactor;

        double finalPrice = basePrice * priceMultiplier;

        return Math.max(finalPrice, basePrice * 0.6);
    }
}
