package com.ogrupo.eventsmicroservice.utils;

public enum AudienceType {
    VIP(0.5),
    STUDENT(-0.2),
    GENERAL(0.0);

    private final double modifier;

    AudienceType(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}