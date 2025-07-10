package io.accelerate.solutions.CHK;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutSolution {

    Map<Character,Map<Integer,Integer>> skuCost = new HashMap<>(
            Map.of('A',new HashMap<>(Map.of(1,50,3,130)),
                    'B',new HashMap<>(Map.of(1,30,2,45)),
                    'C',new HashMap<>(Map.of(1,20)),
                    'D',new HashMap<>(Map.of(1,15)))
    );

    public Integer checkout(String skus) {

        Map<Character, Integer> skuPerCheckout = new HashMap<>();
        var total = new AtomicInteger();

        for(char c : skus.toCharArray()){

            if(!skuCost.containsKey((Character) c))
                return -1;

            skuPerCheckout.put(c,(!skuPerCheckout.containsKey((Character) c)) ? 1 : skuPerCheckout.get(c) + 1);
        }

        skuPerCheckout.forEach((sku, count) ->{
            Map<Integer,Integer> offers = skuCost.get(sku);
            var maxDiscountQt = Collections.max(offers.keySet());

        });


        return total;
    }


}


