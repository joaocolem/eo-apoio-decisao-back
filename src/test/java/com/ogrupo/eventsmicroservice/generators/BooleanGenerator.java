package com.ogrupo.eventsmicroservice.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class BooleanGenerator extends Generator<Boolean> {

    public BooleanGenerator() {
        super(Boolean.class);
    }

    @Override
    public Boolean generate(SourceOfRandomness random, GenerationStatus status) {
        return random.nextBoolean();
    }
}
