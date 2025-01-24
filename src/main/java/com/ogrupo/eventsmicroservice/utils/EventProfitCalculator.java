package com.ogrupo.eventsmicroservice.utils;

public class EventProfitCalculator {

    private static final double WEEKEND_SURCHARGE = 1.2;
    private static final double TAX_RATE = 0.15;

    public double estimateProfit(int numberOfParticipants, double pricePerParticipant, boolean isWeekday, double estimatedCost) {
        if (numberOfParticipants < 0 || pricePerParticipant < 0 || estimatedCost < 0) {
            return 0;
        }
        double adjustedPrice = isWeekday ? pricePerParticipant : pricePerParticipant * WEEKEND_SURCHARGE;
        double totalRevenue = numberOfParticipants * adjustedPrice;
        double revenueAfterTax = totalRevenue * (1 - TAX_RATE);
        double profit = revenueAfterTax - estimatedCost;

        if (profit > totalRevenue) {
            throw new AssertionError("Profit should not exceed gross revenue");
        }

        return profit;
    }
}