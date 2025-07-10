package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SKU {
    Character Id;
    Map<Integer, Integer> offers;


    SKU(Character id, HashMap<Integer,Integer> offers){
        this.Id = Id;
        this.offers = offers;
    }



    public Integer getTotal(int quantity){
        //for now I will assume sorting
        //TODO: change later
        var quantities = offers.keySet();




    }



}

