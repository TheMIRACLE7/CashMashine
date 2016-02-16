package com;

import com.command.CommandExecutor;
import com.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = "com.resources.";
    public static final Locale locale = new Locale("en", "EN");
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
//            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do{
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
