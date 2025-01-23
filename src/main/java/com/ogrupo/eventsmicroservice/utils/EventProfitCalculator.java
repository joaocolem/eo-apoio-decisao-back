package com.ogrupo.eventsmicroservice.utils;

public class EventProfitCalculator {
    private static final double WEEKEND_SURCHARGE = 1.2; // 20% aumento no preço nos fins de semana
    private static final double TAX_RATE = 0.15; // 15% de imposto sobre a receita

    public double estimateProfit(int numberOfParticipants, double pricePerParticipant, boolean isWeekday,
            double estimatedCost) {
        // Implement the validations from the assumptions
        if (numberOfParticipants < 0) {
            throw new IllegalArgumentException("Number of participants must be non-negative.");
        }

        if (!Double.isFinite(pricePerParticipant) || pricePerParticipant < 0) {
            throw new IllegalArgumentException("Price per participant must be a non-negative finite number.");
        }

        if (!Double.isFinite(estimatedCost) || estimatedCost < 0) {
            throw new IllegalArgumentException("Estimated cost must be a non-negative finite number.");
        }

        // Adjust the price based on whether it's a weekday or weekend
        double adjustedPrice = isWeekday ? pricePerParticipant : pricePerParticipant * WEEKEND_SURCHARGE;

        // Calculate total revenue
        double totalRevenue = numberOfParticipants * adjustedPrice;

        // Apply tax
        double revenueAfterTax = totalRevenue * (1 - TAX_RATE);

        // Calculate profit
        double profit = revenueAfterTax - estimatedCost;

        // Ensure profit does not exceed gross revenue (logical check for correctness)
        if (profit > totalRevenue) {
            throw new AssertionError("Profit should not exceed gross revenue.");
        }

        return profit;
    }
}