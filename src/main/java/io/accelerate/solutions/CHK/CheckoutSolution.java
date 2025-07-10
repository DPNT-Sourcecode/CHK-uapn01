package io.accelerate.solutions.CHK;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutSolution {


    List<SKU> skuCosts = new ArrayList<>(List.of(
            new SKU('A',new HashMap<>(Map.of(1,50,3,130,5,200))),
            new SKU('B',new HashMap<>(Map.of(1,30,2,45))),
            new SKU('C',new HashMap<>(Map.of(1,20))),
            new SKU('D',new HashMap<>(Map.of(1,15))),
            new SKU('E',new HashMap<>(Map.of(1,40)))
    ));

    Map<Character, Map<Integer,Character>> buyXgetYFree = new HashMap<>(
        Map.of('E', new HashMap<>(2,'B'))
    );

    public Integer checkout(String skus) {

        Map<Character, Integer> skuPerCheckout = new HashMap<>();
        var totalCheckout = new AtomicInteger();

        for(char c : skus.toCharArray()){

            if(getSKUById(c) == null)
                return -1;

            skuPerCheckout.put(c,(!skuPerCheckout.containsKey((Character) c)) ? 1 : skuPerCheckout.get(c) + 1);
        }


        skuPerCheckout.forEach((sku, count) ->{
            var skuObj  = getSKUById(sku);
            totalCheckout.addAndGet(skuObj.getTotal(count));
        });


        return totalCheckout.get();
    }

    SKU getSKUById(char id){
        for (var sku : skuCosts){
            if(sku.Id == id)
                return sku;
        }
        return null;
    }
}


