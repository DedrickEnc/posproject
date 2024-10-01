package com.dedrick.poslib;

import android.app.Application;
import android.util.Log;

import com.dedrick.utilities.PrinterUtil;
import com.dedrick.utilities.ServiceHelper;

public class X990 extends POSPrinter{
     X990(Application a){
         super(a);
         this.app = a;

    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number, String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
        super.print(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
        if(ServiceHelper.getInstance().getDeviceService() != null){
            PrinterUtil.printTransaction(bank, bankingAgent,activityPoint,address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
        }else{
            ServiceHelper.getInstance().initServiceHelper(this.app);
            ServiceHelper.getInstance().setOnServiceConnectedListener(new ServiceHelper.OnServiceConnectedListener() {
                @Override
                public void onConnected() {
                    Log.i("X990", "Service connected");
                    PrinterUtil.printTransaction(bank, bankingAgent,activityPoint,address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
                }
            });
        }
    }
}
