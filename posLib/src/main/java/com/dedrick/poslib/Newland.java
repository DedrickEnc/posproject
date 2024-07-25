package com.dedrick.poslib;

import android.app.Application;

public class Newland extends POSPrinter {
    Newland(Application a){
        super(a);
        this.app = a;
    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number, String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
        super.print(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);

        System.out.println("Printing on Newland");
    }
}
