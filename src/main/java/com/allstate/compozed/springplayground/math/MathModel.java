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

    public int getFibonacci(int currentIndex, int lastFibonacci, int secondToLastFibonacci) {
        if(currentIndex == number) {
            return secondToLastFibonacci;
        } else {
            return getFibonacci(currentIndex + 1, lastFibonacci + secondToLastFibonacci, lastFibonacci);
        }
    }

    public int getFibonacci() {
        return getFibonacci(0, 1, 0);
    }
}
