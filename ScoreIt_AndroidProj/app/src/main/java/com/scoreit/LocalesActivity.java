package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import locales.Local;
import locales.LocalesAdapter;

public class LocalesActivity extends AppCompatActivity implements LocalesAdapter.OnLocalListener {

    ArrayList<Local> locales = new ArrayList<>();
    String JSON_active_user, JSON_locales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locales);

        //Definir que búsqueda se hace
        Bundle bundle = getIntent().getExtras();
        JSON_active_user = bundle.getString("active_user");
        JSON_locales = bundle.getString("locales");

        locales = Local.getListaLocales(JSON_locales);
        if(locales.size() == 0){
            Toast.makeText(LocalesActivity.this, "No hay ningun local mostrar", Toast.LENGTH_LONG).show();
        }

        //Creamos el adaptador
        RecyclerView rvLocales = (RecyclerView) findViewById(R.id.recycler_locales);

        LocalesAdapter adapter = new LocalesAdapter(locales, this);
        rvLocales.setAdapter(adapter);
        rvLocales.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onLocalClick(int position) throws JSONException {

        Toast.makeText(LocalesActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();
        Local local = locales.get(position);
        String local_activo = local.toJSON();

        Log.d("JSON ", local_activo);


        Intent intent = new Intent(getApplicationContext(), LocalActivity.class);
        intent.putExtra("active_user", JSON_active_user);
        intent.putExtra("local_activo", local_activo);

        startActivity(intent);
        overridePendingTransition(0, 0);

    }
}
