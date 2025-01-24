package com.ogrupo.eventsmicroservice.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class PositiveDoubleGenerator extends Generator<Double> {

    public PositiveDoubleGenerator() {
        super(Double.class);
    }

     @Override
    public Double generate(SourceOfRandomness random, GenerationStatus status) {
        return random.nextDouble(0.01, 1000.0);
    }
}
