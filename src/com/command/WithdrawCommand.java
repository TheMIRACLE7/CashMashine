package com.command;

import com.CashMachine;
import com.exception.NotEnoughMoneyException;
import com.ConsoleHelper;
import com.CurrencyManipulator;
import com.CurrencyManipulatorFactory;
import com.exception.InterruptOperationException;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;


class WithdrawCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String line = ConsoleHelper.readString();
            int sum;
            try{
                sum = Integer.parseInt(line);
                if (sum <= 0) throw new Exception();
            }
            catch (Exception e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(sum)){
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try{
                Iterator<Map.Entry<Integer, Integer>> iterator = manipulator.withdrawAmount(sum).entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                }
                System.out.printf(res.getString("success.format"), sum, currencyCode);
                System.out.println();
                break;
            }
            catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
