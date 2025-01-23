package com.ogrupo.eventsmicroservice.utils;

public class EventProfitCalculator {

    private static final double WEEKEND_SURCHARGE = 1.2; // 20% aumento no preço nos fins de semana
    private static final double TAX_RATE = 0.15; // 15% de imposto sobre a receita

    public double estimateProfit(int numberOfParticipants, double pricePerParticipant, boolean isWeekday, double estimatedCost) {
        if (numberOfParticipants < 0 || pricePerParticipant < 0 || estimatedCost < 0) {
            System.out.println("---A");
            throw new IllegalArgumentException("Values cannot be negative");
        }
        // Ajustar o preço com base no dia da semana
        double adjustedPrice = isWeekday ? pricePerParticipant : pricePerParticipant * WEEKEND_SURCHARGE;

        // Calcular a receita total
        double totalRevenue = numberOfParticipants * adjustedPrice;

        // Aplicar imposto
        double revenueAfterTax = totalRevenue * (1 - TAX_RATE);

        // Calcular o lucro
        double profit = revenueAfterTax - estimatedCost;

        // Garantir que o lucro não exceda a receita bruta
        if (profit > totalRevenue) {
            throw new AssertionError("Profit should not exceed gross revenue");
        }

        return profit;
    }
}