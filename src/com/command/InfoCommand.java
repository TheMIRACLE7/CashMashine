package com.command;

import com.CashMachine;
import com.ConsoleHelper;
import com.CurrencyManipulator;
import com.CurrencyManipulatorFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info",
            CashMachine.locale);
    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage(new String(res.getString("before").getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        ArrayList<CurrencyManipulator> manipulators = new ArrayList<>(CurrencyManipulatorFactory.getAllCurrencyManipulators());
        for (CurrencyManipulator manipulator : manipulators) {
            if (manipulator.hasMoney())
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            else ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
