package com;

import com.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String line;
        while (true) {
            try {
                line = reader.readLine();
            }
            catch (IOException e) {
                continue;
            }
            if (line.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
            else return line;
        }
    }

    public static void printExitMessage(){
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String str = readString();
            if (str.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            } else {
                return str.toUpperCase();
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode)
            throws InterruptOperationException{
        while (true) {
            writeMessage(currencyCode);
            System.out.printf(res.getString("choose.denomination.and.count.format"), currencyCode);
            System.out.println();
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) > 0 && Integer.parseInt(strmas[1]) > 0) {
                    return strmas;
                } else {
                    writeMessage(res.getString("invalid.data"));
                }
            }catch (NumberFormatException ex) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO"));
        writeMessage(res.getString("operation.DEPOSIT"));
        writeMessage(res.getString("operation.WITHDRAW"));
        writeMessage(res.getString("operation.EXIT"));
        try{
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
        }
        catch (InterruptOperationException e){
            throw new InterruptOperationException();
        }
        catch (Exception e){
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }
}
