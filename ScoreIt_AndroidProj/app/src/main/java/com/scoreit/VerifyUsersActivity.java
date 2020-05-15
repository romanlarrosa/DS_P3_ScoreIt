package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import usuarios.UsersVerifyAdapter;
import usuarios.Usuario;

public class VerifyUsersActivity extends AppCompatActivity {

    ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_users);

        //Inicializar los contactos
        getListaUsuarios();

    }

    public void continua(String response){
        RecyclerView rvUsuarios = (RecyclerView) findViewById(R.id.recycler_verify_users);

        if(response.equals("[]"))
        Toast.makeText(VerifyUsersActivity.this, "No hay ningun usuario que verificar en este momento", Toast.LENGTH_LONG).show();

        usuarios = Usuario.getListaUsuarios(response);

        //Creamos el adaptador
        UsersVerifyAdapter adapter = new UsersVerifyAdapter(usuarios);
        rvUsuarios.setAdapter(adapter);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getListaUsuarios(){
        String URLSERVIDOR = "http://85.136.47.127/noverificados.php";
        //Conectarse a un php que devuelva una lista de usuarios
        StringRequest peticion = new StringRequest(Request.Method.GET, URLSERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()) {
                            continua(response);
                        }
                        else {
                            Toast.makeText(VerifyUsersActivity.this, "No hay usuarios que verificar!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test error", ""+error.getMessage());
                Toast.makeText(VerifyUsersActivity.this, "Error al hacer login", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(VerifyUsersActivity.this);
        requestQueue.add(peticion);

    }
}
