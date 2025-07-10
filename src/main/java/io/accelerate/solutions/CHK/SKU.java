package io.accelerate.solutions.CHK;

import java.util.*;

public class SKU {
    Character Id;
    Map<Integer, Integer> offers;


    SKU(Character id, HashMap<Integer,Integer> offers){
        this.Id = id;
        this.offers = offers;
    }

    public Integer getTotal(int quantity){
        //need to start with the larger discounts
        List<Integer> quantities = new ArrayList<>(offers.keySet());
        quantities.sort(Collections.reverseOrder());

        var remainingToCheckout = quantity;
        var total = 0;

        for(var qt : quantities){
            total += (remainingToCheckout / qt) * quantities.get(qt);
            remainingToCheckout %= qt;
        }

        return total;
    }



}
