package com.dedrick.poslib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dedrick.utilities.PrinterUtil;
import com.dedrick.utilities.ServiceHelper;

public class PrintingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        POSPrinter posPrinter = null;

        if(intent.getStringExtra("bank").equalsIgnoreCase("UBA DRC")){
            posPrinter = new X990(getApplication());

        }else{
            posPrinter = new F20(getApplication());
        }

        posPrinter.print(intent.getStringExtra("bank"), intent.getStringExtra("bankingAgent"), intent.getStringExtra("activityPoint"),intent.getStringExtra("address"), intent.getStringExtra("transactionType"), intent.getStringExtra("account_number"), intent.getStringExtra("stan"), intent.getStringExtra("created_at"), intent.getStringExtra("currency"), intent.getStringExtra("main_amount"), intent.getStringExtra("description"), intent.getStringExtra("terminalId"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity is now Paused!!!!!!!!!!!!");
    }

}
