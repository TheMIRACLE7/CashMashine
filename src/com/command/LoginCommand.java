package com.command;

import com.CashMachine;
import com.ConsoleHelper;
import com.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private final String BUNDLE_NAME = "com.resources.verifiedCards";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(BUNDLE_NAME);
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cardEntered;
        String pinEntered;
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardEntered = ConsoleHelper.readString();
            pinEntered = ConsoleHelper.readString();
            if (cardEntered.length() == 12 && pinEntered.length() == 4 && cardEntered.matches("\\d{12}") &&
                    pinEntered.matches("\\d{4}")) {
                if (validCreditCards.containsKey(cardEntered)) {
                    String pin = validCreditCards.getString(cardEntered);
                    if (pin.equals(pinEntered)) {
                        System.out.printf(res.getString("success.format"), cardEntered);
                        System.out.println();
                        break;
                    } else {
                        System.out.printf(res.getString("not.verified.format"), cardEntered);
                        System.out.println();
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    }
                }
                else {
                    System.out.printf(res.getString("not.verified.format"), cardEntered);
                    System.out.println();
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
            else {
                System.out.printf(res.getString("try.again.with.details"));
            }
        }
    }
}
