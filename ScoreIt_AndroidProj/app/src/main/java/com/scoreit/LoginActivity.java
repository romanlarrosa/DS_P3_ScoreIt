package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    String URLSERVIDOR = "http://85.136.47.127/login.php";
    EditText etUsuario, etContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.login_user);
        etContraseña = findViewById(R.id.login_password);
    }

    public void login(View view){
        StringRequest peticion = new StringRequest(Request.Method.POST, URLSERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()) {

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("active_user", response);
                            startActivity(intent);


                            overridePendingTransition(0, 0);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test error", ""+error.getMessage());
                Toast.makeText(LoginActivity.this, "Error al hacer login", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Send the params to php


                Map<String, String> params = new Hashtable<String, String>();
                params.put("user", etUsuario.getText().toString().trim());
                params.put("pass", etContraseña.getText().toString().trim());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(peticion);
    }
}
