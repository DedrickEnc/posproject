package com.dedrick.poslib;

import android.app.Application;


import com.newland.nsdk.esc.api.ESCPrinter;
import com.newland.nsdk.esc.common.ESCPrinterException;
import com.newland.nsdk.esc.internal.ESCPrinterImpl;
import java.nio.charset.StandardCharsets;

public class Newland extends POSPrinter {
    Newland(Application a){
        super(a);
        this.app = a;
    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number, String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
        super.print(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
    }
}
