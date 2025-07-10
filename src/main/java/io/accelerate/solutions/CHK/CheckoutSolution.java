package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    Map<Character,Map<Integer,Integer>> skuCost = new HashMap<>(
            Map.of('A',new HashMap<>(Map.of(1,50,3,130)),
                    'B',new HashMap<>(Map.of(1,30,2,45)),
                    'C',new HashMap<>(Map.of(1,20)),
                    'D',new HashMap<>(Map.of(1,15)))
    );

    public Integer checkout(String skus) {

    }
}


