package com.scoreit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import locales.Local;
import usuarios.Usuario;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button botonRanking;
    Usuario active_user;
    String JSON_active_user;
    ArrayList<Local> ranking;
    String JSON_ranking;

    TextView etNombre1, etNombre2, etNombre3;
    TextView etUbicacion1, etUbicacion2, etUbicacion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        JSON_active_user = bundle.getString("active_user");
        active_user = new Usuario(JSON_active_user);

        etNombre1 = findViewById(R.id.ranking1_text);
        etNombre2 = findViewById(R.id.ranking2_text);
        etNombre3 = findViewById(R.id.ranking3_text);

        etUbicacion1 = findViewById(R.id.location1_text);
        etUbicacion2 = findViewById(R.id.location2_text);
        etUbicacion3 = findViewById(R.id.location3_text);

        //Cargar los locales
        JSON_ranking = Local.getRanking();
        ranking = Local.getListaLocales(JSON_ranking);

        //Inicializar los relative layouts de rankings
        etNombre1.setText(ranking.get(0).getNombre());
        etNombre2.setText(ranking.get(1).getNombre());
        etNombre3.setText(ranking.get(2).getNombre());

        etUbicacion1.setText(ranking.get(0).getUbicacion());
        etUbicacion2.setText(ranking.get(1).getUbicacion());
        etUbicacion3.setText(ranking.get(2).getUbicacion());





        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.menu_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:

                        return true;

                    case R.id.menu_profile:
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("active_user", JSON_active_user);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }


    public void buscar(View view) {
        EditText barraBusqueda = findViewById(R.id.barra_busqueda);
        String busqueda = barraBusqueda.getText().toString();
        String JSON_locales = Local.getLocalesBusqueda(busqueda);


        Intent intent = new Intent(getApplicationContext(), LocalesActivity.class);
        intent.putExtra("active_user", JSON_active_user);
        intent.putExtra("locales", JSON_locales);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }

    public void irALocal(View view) {
        Toast.makeText(MainActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();
        int fila = Integer.parseInt(view.getTag().toString());

        String local_activo = ranking.get(fila).toJSON();

        Intent intent = new Intent(getApplicationContext(), LocalActivity.class);
        intent.putExtra("active_user", JSON_active_user);
        intent.putExtra("local_activo", local_activo);

        startActivity(intent);
        overridePendingTransition(0, 0);

    }

    public void recargar(View view) {

        Toast.makeText(MainActivity.this, "Recargando...", Toast.LENGTH_SHORT).show();
        //Cargar los locales
        JSON_ranking = Local.getRanking();
        ranking = Local.getListaLocales(JSON_ranking);

        //Inicializar los relative layouts de rankings
        etNombre1.setText(ranking.get(0).getNombre());
        etNombre2.setText(ranking.get(1).getNombre());
        etNombre3.setText(ranking.get(2).getNombre());

        etUbicacion1.setText(ranking.get(0).getUbicacion());
        etUbicacion2.setText(ranking.get(1).getUbicacion());
        etUbicacion3.setText(ranking.get(2).getUbicacion());
    }
}
