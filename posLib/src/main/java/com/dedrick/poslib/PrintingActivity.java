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

        String model = intent.getStringExtra("posModel");

        switch (model) {
            case "X990":
                posPrinter = new X990(getApplication());
                break;
            case "F20":
                posPrinter = new F20(getApplication());
                break;
            case "NL":
                posPrinter = new Newland(getApplication());
                break;
        }

        if (posPrinter != null) {
            posPrinter.print(intent.getStringExtra("bank"), intent.getStringExtra("bankingAgent"), intent.getStringExtra("activityPoint"), intent.getStringExtra("address"), intent.getStringExtra("transactionType"), intent.getStringExtra("account_number"), intent.getStringExtra("stan"), intent.getStringExtra("created_at"), intent.getStringExtra("currency"), intent.getStringExtra("main_amount"), intent.getStringExtra("description"), intent.getStringExtra("terminalId"));
        }else{
            System.out.println("POS Printer not null");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity is now Paused!!!!!!!!!!!!");
    }

}
