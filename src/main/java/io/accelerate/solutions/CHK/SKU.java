package io.accelerate.solutions.CHK;

import java.io.FilterOutputStream;
import java.util.*;

public class SKU {
    Character Id;
    Map<Integer, Integer> offers;
    Character freeSKU;
    Integer requiredQtyForFree;
    
    SKU(Character id, HashMap<Integer,Integer> offers){
        this.Id = id;
        this.offers = offers;
    }
    SKU(Character id, HashMap<Integer,Integer> offers,Character freeSKU,Integer requiredQtyForFree){
        this.Id = id;
        this.offers = offers;
        this.freeSKU = freeSKU;
        this.requiredQtyForFree = requiredQtyForFree;
    }


    public Integer getTotal(int quantity){
        //need to start with the larger discounts
        List<Integer> offersQt = new ArrayList<>(offers.keySet());
        offersQt.sort(Collections.reverseOrder());

        var remainingToCheckout = quantity;
        var total = 0;

        for(var qt : offersQt){
            total += ((remainingToCheckout / qt) * offers.get(qt));
            remainingToCheckout %= qt;
        }

        return total;
    }



}

