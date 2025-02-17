package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void buttonRegistro(View view){
        startActivity(new Intent(getApplicationContext(), Registro.class));
        overridePendingTransition(0, 0);
    }

    public void buttonLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        overridePendingTransition(0, 0);
    }

}

