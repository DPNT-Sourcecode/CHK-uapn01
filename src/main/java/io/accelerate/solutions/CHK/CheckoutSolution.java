package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;
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
        AtomicInteger total = new AtomicInteger();
        for(char c : skus.toCharArray()){
            skuPerCheckout.put(c,(!skuPerCheckout.containsKey((Character) c)) ? 1 : skuPerCheckout.get(c) + 1);
        }

        skuPerCheckout.forEach((sku,count) -> {
            if(!skuCost.get(sku).containsKey(count)){
                total.addAndGet(skuCost.get(sku).get(1) * count);
            }else{
                total.addAndGet(skuCost.get(sku).get(count));
            }
        });

        return total.get();
    }


}




