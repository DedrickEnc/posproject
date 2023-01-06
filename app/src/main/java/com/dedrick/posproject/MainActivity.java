package com.dedrick.posproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.dedrick.poslib.POSVeri;
import com.dedrick.utilities.PrinterUtil;
import com.dedrick.utilities.ServiceHelper;
import com.vfi.smartpos.deviceservice.aidl.IDeviceService;
import com.vfi.smartpos.deviceservice.aidl.IPrinter;

public class MainActivity extends AppCompatActivity {
    IPrinter printer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(getApplicationContext(), POSVeri.class);
        startActivity(i);
        //new POSVeri();
        /*ServiceHelper.getInstance().initServiceHelper(getApplication());
        ServiceHelper.getInstance().setOnServiceConnectedListener(new ServiceHelper.OnServiceConnectedListener() {

            @Override
            public void onConnected() {
                System.out.println("Service connected");
                PrinterUtil.printTransaction();

            }
        });*/




    }

}