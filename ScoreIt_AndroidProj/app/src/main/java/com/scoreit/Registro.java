package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class Registro extends AppCompatActivity {
    String URLSERVIDOR = "http://85.136.47.127/registro.php";

    EditText etUsuario, etContraseña;
    RadioGroup radioGroup;
    String userMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuario = findViewById(R.id.registro_user);
        etContraseña = findViewById(R.id.registro_password);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void registrar(View view){
        StringRequest peticion = new StringRequest(Request.Method.POST, URLSERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")) {
                            Toast.makeText(Registro.this, "Registro Existoso, logueate para entrar a la app", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                            overridePendingTransition(0, 0);
                        }
                        else if(response.equals("ERR1")) {
                            Toast.makeText(Registro.this, "Faltan campos", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.equals("-1")) {
                            Toast.makeText(Registro.this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test error", ""+error.getMessage());
                Toast.makeText(Registro.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Send the params to php


                Map<String, String> params = new Hashtable<String, String>();
                params.put("user", etUsuario.getText().toString().trim());
                params.put("pass", etContraseña.getText().toString().trim());

                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton){
                    params.put("tipo", "USER");
                }else{ //id == radioButton2 (Owner)
                    params.put("tipo", "OWNER");
                }

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
        requestQueue.add(peticion);
    }
}
