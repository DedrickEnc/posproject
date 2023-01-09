package com.dedrick.poslib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dedrick.utilities.PrinterUtil;
import com.dedrick.utilities.ServiceHelper;

public class POSVeri extends AppCompatActivity {
    private static final String TAG = "POSVeri";
    private static Boolean isCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Activity is now Resumed !!!!!!!!!!!!");
        Intent intent = getIntent();
        ServiceHelper.getInstance().initServiceHelper(getApplication());
        ServiceHelper.getInstance().setOnServiceConnectedListener(new ServiceHelper.OnServiceConnectedListener() {
            @Override
            public void onConnected() {
                Log.i(TAG, "Service connected");
                PrinterUtil.printTransaction(intent.getStringExtra("bank"), intent.getStringExtra("bankingAgent"), intent.getStringExtra("activityPoint"), intent.getStringExtra("transactionType"), intent.getStringExtra("account_number"), intent.getStringExtra("stan"), intent.getStringExtra("created_at"), intent.getStringExtra("currency"), intent.getStringExtra("main_amount"));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity is now Paused!!!!!!!!!!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Activity is now Stoped !!!!!!!!!!!!");
    }
}
