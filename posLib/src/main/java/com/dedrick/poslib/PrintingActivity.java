package com.dedrick.poslib;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        String model = Build.MODEL;

        switch (model) {
            case "X990":
                posPrinter = new X990(getApplication());
                break;
            case "F20":
                posPrinter = new F20(getApplication());
                break;
            case "N950S":
                posPrinter = new Newland950(getApplication());
                break;
        }

        if (posPrinter != null) {
            posPrinter.print(intent.getStringExtra("bank"), intent.getStringExtra("bankingAgent"), intent.getStringExtra("activityPoint"), intent.getStringExtra("address"), intent.getStringExtra("transactionType"), intent.getStringExtra("account_number"), intent.getStringExtra("stan"), intent.getStringExtra("created_at"), intent.getStringExtra("currency"), intent.getStringExtra("main_amount"), intent.getStringExtra("description"), intent.getStringExtra("terminalId"));
        }else{
            System.out.println("POS Printer is null");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity is now Paused!!!!!!!!!!!!");
    }

}
