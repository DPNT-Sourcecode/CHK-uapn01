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
            new SKU('E',new HashMap<>(Map.of(1,40)),'B',2),
            new SKU('F',new HashMap<>(Map.of(1,10)),'F',2),
            new SKU('G',new HashMap<>(Map.of(1,20))),
            new SKU('H',new HashMap<>(Map.of(1,10,5,45,10,80))),
            new SKU('I',new HashMap<>(Map.of(1,35))),
            new SKU('J',new HashMap<>(Map.of(1,60))),
            new SKU('K',new HashMap<>(Map.of(1,70,2,120))),
            new SKU('L',new HashMap<>(Map.of(1,90))),
            new SKU('M',new HashMap<>(Map.of(1,15))),
            new SKU('N',new HashMap<>(Map.of(1,40)),'M',3),
            new SKU('O',new HashMap<>(Map.of(1,10))),
            new SKU('P',new HashMap<>(Map.of(1,50,5,200))),
            new SKU('Q',new HashMap<>(Map.of(1,30,3,80))),
            new SKU('R',new HashMap<>(Map.of(1,50)),'Q',3),
            new SKU('S',new HashMap<>(Map.of(1,20))),
            new SKU('T',new HashMap<>(Map.of(1,20))),
            new SKU('U',new HashMap<>(Map.of(1,40)),'U',3),
            new SKU('V',new HashMap<>(Map.of(1,50,2,90,3,130))),
            new SKU('W',new HashMap<>(Map.of(1,20))),
            new SKU('X',new HashMap<>(Map.of(1,17))),
            new SKU('Y',new HashMap<>(Map.of(1,20))),
            new SKU('Z',new HashMap<>(Map.of(1,21)))
    ));

    Map<String, Discount> groupDiscounts = new HashMap<>(
            Map.of("STXYZ",new Discount(45,3))
    );

    public Integer checkout(String skus) {

        Map<Character, Integer> skuPerCheckout = new HashMap<>();
        var totalCheckout = new AtomicInteger();
        Map<Character,Integer> availableGroups = new HashMap<>();

        for(char c : skus.toCharArray()){

            if(getSKUById(c) == null)
                return -1;

            skuPerCheckout.put(c,(!skuPerCheckout.containsKey((Character) c)) ? 1 : skuPerCheckout.get(c) + 1);

            if(groupDiscounts.keySet().stream()
                    .anyMatch(key -> key.indexOf(c) != -1)){


                var groupKey = groupDiscounts.keySet().stream()
                            .filter(key -> key.indexOf(c) != -1).findFirst();
                var discount = groupDiscounts.get(groupKey.get());

                availableGroups.put(c,availableGroups.getOrDefault(c,0) + 1);

                if(availableGroups.values().stream().mapToInt(Integer::intValue).sum() == discount.quantityNeeded){
                    totalCheckout.addAndGet(discount.price);

                    for(char groupItem : skuPerCheckout.keySet()){
                        skuPerCheckout.put(groupItem,skuPerCheckout.get(groupItem) - availableGroups.get(groupItem));
                    }

                    availableGroups.clear();
                }
            }
        }

        skuPerCheckout.forEach((sku, count) ->{
            var skuObj  = getSKUById(sku);
            if(skuObj.freeSKU == null)
                return;

            int requiredQt = (skuObj.freeSKU.equals(sku)) ? skuObj.requiredQtyForFree + 1 : skuObj.requiredQtyForFree;

            if(count >= requiredQt
            && skuPerCheckout.get(skuObj.freeSKU) != null){

                int totalToRemove;
                if(!skuObj.freeSKU.equals(sku))
                    totalToRemove = skuPerCheckout.get(skuObj.freeSKU) - (count / requiredQt);
                else
                    totalToRemove = skuPerCheckout.get(skuObj.freeSKU) - (count / requiredQt);

                skuPerCheckout.put(skuObj.freeSKU, Math.max(totalToRemove, 0));
            }
        });

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








