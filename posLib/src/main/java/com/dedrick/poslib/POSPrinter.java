package com.dedrick.poslib;

import android.app.Application;
import android.content.Context;

public class POSPrinter {
    Application app = null;
    Context c = null;

    POSPrinter(Application a){
        this.app = a;
    }

    POSPrinter(Application a, Context c){
        this.app = a;
        this.c = c;
    }

    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){

    }
}
