package com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();
    private CurrencyManipulatorFactory(){}
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (manipulators.containsKey(currencyCode)) {
            return manipulators.get(currencyCode);
        } else {
            CurrencyManipulator cur = new CurrencyManipulator(currencyCode);
            manipulators.put(currencyCode, cur);
            return cur;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        ArrayList<CurrencyManipulator> currencyManipulators = new ArrayList<>();
        for (Map.Entry<String, CurrencyManipulator> entry : manipulators.entrySet()){
            currencyManipulators.add(entry.getValue());
        }
        return currencyManipulators;
    }
}
