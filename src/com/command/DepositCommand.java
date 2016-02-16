package com.command;

import com.CashMachine;
import com.ConsoleHelper;
import com.CurrencyManipulator;
import com.CurrencyManipulatorFactory;
import com.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String curCode = ConsoleHelper.askCurrencyCode();
        String[] str = ConsoleHelper.getValidTwoDigits(curCode);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode);
        int nominal = Integer.parseInt(str[0]);
        int amount = Integer.parseInt(str[1]);
        manipulator.addAmount(nominal, amount);
        System.out.printf(res.getString("success.format"), nominal * amount, curCode);
        System.out.println();
    }
}
