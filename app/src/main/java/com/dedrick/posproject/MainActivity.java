package com.dedrick.posproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dedrick.poslib.Calcul;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int a = new Calcul().plus(2, 5);
        System.out.println("maman et papa "+String.valueOf(a));
    }
}