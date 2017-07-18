package com.allstate.compozed.springplayground.math;

import java.util.stream.IntStream;

public class MathModel {
    private final int number;

    public MathModel(int number) {
        this.number = number;
    }

    public int getSquare() {
        return number * number;
    }

    public int getFactorial() {
        return IntStream.rangeClosed(1, number).reduce(1, (total, currentValue) -> total * currentValue);
    }
}
