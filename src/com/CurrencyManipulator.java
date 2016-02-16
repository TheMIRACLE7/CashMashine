package com;

import com.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public boolean hasMoney(){
        return getTotalAmount() > 0;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()){
            result += pair.getKey() * pair.getValue();
        }
        return result;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        Comparator<Integer> reverse = Collections.reverseOrder();
        Map<Integer, Integer> result = new TreeMap<>(reverse);
        int debt = expectedAmount;
        int nominal = 0;
        int amountRemaining = 0;
        Map<Integer, Integer> sortedDemoninations = new TreeMap<>(denominations);
        boolean finish = false;
        while (!finish) {
            finish = true;
            for (Map.Entry<Integer, Integer> entry : sortedDemoninations.entrySet()) {

                int amount = debt / entry.getKey();
                if (amount == 0) break;
                nominal = entry.getKey();
                amountRemaining = entry.getValue();
            }
            while (debt > 0 && amountRemaining > 0 && debt >= nominal) {
                finish = false;
                debt -= nominal;
                amountRemaining--;
                if (result.containsKey(nominal))
                    result.put(nominal, result.get(nominal) + 1);
                else result.put(nominal, 1);
            }
            sortedDemoninations.put(nominal, amountRemaining);
            if (amountRemaining == 0) sortedDemoninations.remove(nominal);
        }
        if (debt == 0) {
            denominations = sortedDemoninations;
            return result;
        }
        else throw new NotEnoughMoneyException();
    }
}
