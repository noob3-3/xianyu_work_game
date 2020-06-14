package com.example.msi_pc.demo_619;

import android.os.Bundle;

import com.example.msi_pc.demo_619.Tools.UIOperation;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UIOperation.SetFullScreen(this);
    }
}
