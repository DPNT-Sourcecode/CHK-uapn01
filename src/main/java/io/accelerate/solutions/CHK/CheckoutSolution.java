package io.accelerate.solutions.CHK;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutSolution {

    List<Character> availableSKUs = new ArrayList<>(List.of('A','B','C','D','E'));
    List<SKU> skuCosts = new ArrayList<>(List.of(
            new SKU('A',new HashMap<>(Map.of(1,50,3,130,5,200))),
            new SKU('B',new HashMap<>(Map.of(1,30,2,45))),
            new SKU('C',new HashMap<>(Map.of(1,20))),
            new SKU('D',new HashMap<>(Map.of(1,15)))
    ));

    public Integer checkout(String skus) {

        Map<Character, Integer> skuPerCheckout = new HashMap<>();
        var totalCheckout = new AtomicInteger();

        for(char c : skus.toCharArray()){

            if(!availableSKUs.contains(c))
                return -1;

            skuPerCheckout.put(c,(!skuPerCheckout.containsKey((Character) c)) ? 1 : skuPerCheckout.get(c) + 1);
        }
        
        skuPerCheckout.forEach((sku, count) ->{
            Map<Integer,Integer> offers = skuCost.get(sku);
            var maxDiscountQt = Collections.max(offers.keySet());

            var total = count/maxDiscountQt * offers.getOrDefault(maxDiscountQt,0) + (count % maxDiscountQt) * offers.get(1);
            totalCheckout.addAndGet(total);
        });


        return totalCheckout.get();
    }


}
