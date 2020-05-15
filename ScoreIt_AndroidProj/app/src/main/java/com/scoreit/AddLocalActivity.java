package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;

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

import usuarios.Usuario;

public class AddLocalActivity extends AppCompatActivity {

    Usuario active_user;
    String JSON_active_user;

    EditText etNombre, etUbicacion, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);

        Bundle bundle = getIntent().getExtras();
        JSON_active_user = bundle.getString("active_user");
        active_user = new Usuario(JSON_active_user);

        etNombre = findViewById(R.id.nombre_local_form);
        etUbicacion = findViewById(R.id.location_local_form);
        etDescripcion = findViewById(R.id.descripcion_local_forn);
    }

    public void addLocal(View view) {
        String URLSERVIDOR = "http://85.136.47.127/addLocal.php";

        StringRequest peticion = new StringRequest(Request.Method.POST, URLSERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")) {
                            Toast.makeText(AddLocalActivity.this, "Local publicado con éxito", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else if(response.equals("-1")) {
                            Toast.makeText(AddLocalActivity.this, "Faltan campos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test error", ""+error.getMessage());
                Toast.makeText(AddLocalActivity.this, "Error al publicar", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Send the params to php
                String nombre = etNombre.getText().toString().trim();
                String ubicacion = etUbicacion.getText().toString().trim();
                String descripcion = etDescripcion.getText().toString().trim();
                String propietario = String.valueOf(active_user.getId()).trim();

                Map<String, String> params = new Hashtable<String, String>();
                params.put("nombre", nombre);
                params.put("ubicacion", ubicacion);
                params.put("descripcion", descripcion);
                params.put("propietario", propietario);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddLocalActivity.this);
        requestQueue.add(peticion);


    }
}
