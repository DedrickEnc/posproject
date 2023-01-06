package com.dedrick.poslib;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dedrick.utilities.PrinterUtil;
import com.dedrick.utilities.ServiceHelper;

public class POSVeri extends AppCompatActivity {
    private static final String TAG = "POSVeri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceHelper.getInstance().initServiceHelper(getApplication());
        ServiceHelper.getInstance().setOnServiceConnectedListener(new ServiceHelper.OnServiceConnectedListener() {
            @Override
            public void onConnected() {
                Log.i(TAG, "Service connected");
                PrinterUtil.printTransaction();
                PrinterUtil.printTransaction();
            }
        });
    }
}
