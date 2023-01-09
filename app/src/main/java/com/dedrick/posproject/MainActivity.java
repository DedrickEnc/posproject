package com.dedrick.posproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dedrick.poslib.POSVeri;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.rootContainer);
        // Create Button Dynamically
        Button btnShow = new Button(this);
        btnShow.setText("Print");
        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), POSVeri.class);
                i.putExtra("bank", "UBA DRC");
                i.putExtra("bankingAgent", "AB1");
                i.putExtra("activityPoint", "AP1");
                i.putExtra("transactionType", "Deposit");
                i.putExtra("account_number", "990810004978");
                i.putExtra("stan", "662057999505");
                i.putExtra("created_at", "2023-01-09 10:10");
                i.putExtra("currency", "CDF");
                i.putExtra("main_amount", "2000");
                i.putExtra("fee", "200");
                i.putExtra("vat_amount", "32");

                startActivity(i);
            }
        });

        // Add Button to LinearLayout
        if (linearLayout != null) {
            linearLayout.addView(btnShow);
        }
    }
}