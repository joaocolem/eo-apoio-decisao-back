package com.ogrupo.eventsmicroservice.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class IntGenerator extends Generator<Integer> {

    public IntGenerator() {
        super(Integer.class);
    }

    @Override
    public Integer generate(SourceOfRandomness random, GenerationStatus status) {
        return random.nextInt();
    }
}
