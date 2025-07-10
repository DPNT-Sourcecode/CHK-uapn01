package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class SKU {
    Character Id;
    Map<Integer, Integer> offers;


    SKU(Character id, HashMap<Integer,Integer> offers){
        this.Id = Id;
        this.offers = offers;
    }
}
