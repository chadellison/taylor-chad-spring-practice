package com.allstate.compozed.springplayground.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.IntStream;

import static org.springframework.expression.common.ExpressionUtils.toInt;

@RestController
final class MathController {

    @RequestMapping(path = "/math/square/{number}")
    Map<String, Integer> square(@PathVariable int number) {
        MathModel math = new MathModel(number);
        return Collections.singletonMap("square", math.getSquare());
    }

    @RequestMapping(path = "/math/factorial/{number}")
    Map<String, Integer> factorial(@PathVariable final int number) {
        MathModel math = new MathModel(number);
        return Collections.singletonMap("factorial", math.getFactorial());
    }

    @RequestMapping(path = "/math/fibonacci/{number}")
    Map<String, Integer> fibonacci(@PathVariable final int number) {
        return Collections.singletonMap("fibonacci", 2);
    }
}
