package com.ogrupo.eventsmicroservice.utils;

import java.util.Random;

public class TicketPricePredictor {

  public static double predictOptimalTicketPrice(int expectedAttendees, int venueCapacity, double basePrice, boolean isWeekend, boolean isOpenBar, String audienceType, double historicalDemandFactor) {
      if (expectedAttendees < 0 || venueCapacity <= 0 || basePrice < 0 || historicalDemandFactor < 0) {
          throw new IllegalArgumentException("Invalid input values");
      }

      double priceMultiplier = 1.0;

      // Ajuste de preço para finais de semana
      if (isWeekend) {
          priceMultiplier += 0.15; // Aumenta o preço em 15% nos finais de semana
      }

      // Ajuste de preço para eventos open bar
      if (isOpenBar) {
          priceMultiplier += 0.25; // Aumenta o preço em 25% para open bar
      }

      // Ajuste de preço baseado na demanda histórica
      priceMultiplier *= historicalDemandFactor;

      // Segmentação de mercado
      switch (audienceType.toLowerCase()) {
          case "vip":
              priceMultiplier += 0.5; // Aumenta o preço em 50% para VIPs
              break;
          case "student":
              priceMultiplier -= 0.2; // Reduz o preço em 20% para estudantes
              break;
          default:
              break;
      }

      // Descontos dinâmicos: 10% de desconto se comprado em grupos grandes
      if (expectedAttendees > venueCapacity * 0.8) {
          priceMultiplier -= 0.1;
      }

      // Fatores externos simulados (ex.: clima)
      Random random = new Random();
      double externalFactor = 0.9 + (1.1 - 0.9) * random.nextDouble(); // Simula um fator entre 0.9 e 1.1
      priceMultiplier *= externalFactor;

      // Calcula o preço final
      double finalPrice = basePrice * priceMultiplier;
      return Math.max(finalPrice, basePrice * 0.6); // Garante que o preço não caia abaixo de 60% do preço base
  }
}