package com.dedrick.poslib;

import android.app.Application;

public class POSPrinter {
    Application app = null;
    POSPrinter(Application a){
        this.app = a;

    }
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){

    }
}
