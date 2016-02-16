package com.command;

import com.CashMachine;
import com.ConsoleHelper;
import com.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException{
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String input = ConsoleHelper.readString();
        if (input.equalsIgnoreCase("yes") || res.getString("yes").equalsIgnoreCase(input)) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
