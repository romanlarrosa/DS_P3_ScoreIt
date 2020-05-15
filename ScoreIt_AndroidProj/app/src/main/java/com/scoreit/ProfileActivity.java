package com.scoreit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import locales.Local;
import usuarios.Usuario;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    Usuario active_user;
    String JSON_active_user;

    Button boton1, boton2, boton3;
    ImageView verificado;
    TextView nombre, tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);

        nombre = findViewById(R.id.name_profile);
        tipo = findViewById(R.id.type_profile);

        verificado = findViewById(R.id.verificado);

        Bundle bundle = getIntent().getExtras();
        JSON_active_user = bundle.getString("active_user");
        active_user = new Usuario(JSON_active_user);

        //Mostrar info del usuario
        nombre.setText(active_user.getNombre());



        //Seleccionar visibilidades
        switch (active_user.getTipo()){
            case "USER":
                verificado.setVisibility(View.INVISIBLE);
                boton1.setVisibility(View.INVISIBLE);
                boton2.setVisibility(View.INVISIBLE);
                boton3.setVisibility(View.INVISIBLE);
                tipo.setText(R.string.user);
                break;
            case "ADMIN":
                verificado.setVisibility(View.INVISIBLE);
                boton1.setVisibility(View.INVISIBLE);
                boton2.setVisibility(View.INVISIBLE);
                boton3.setVisibility(View.VISIBLE);
                tipo.setText(R.string.admin);
                break;
            case "OWNER":
                if(active_user.isVerificado()){
                    verificado.setVisibility(View.VISIBLE);
                    boton1.setVisibility(View.VISIBLE);
                    boton2.setVisibility(View.VISIBLE);
                }
                else {
                    verificado.setVisibility(View.INVISIBLE);
                    boton1.setVisibility(View.INVISIBLE);
                    boton2.setVisibility(View.INVISIBLE);
                }

                boton3.setVisibility(View.INVISIBLE);
                tipo.setText(R.string.owner);
                break;
        }



        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.menu_profile);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("active_user", JSON_active_user);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menu_profile:

                        return true;
                }

                return false;
            }
        });
    }

    public void addAnuncio(View view){
        Intent intent = new Intent(getApplicationContext(), AddLocalActivity.class);
        intent.putExtra("active_user", JSON_active_user);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void misAnuncios(View view){
        String JSON_locales = Local.getLocalesOwner(active_user.getId());


        Intent intent = new Intent(getApplicationContext(), LocalesActivity.class);
        intent.putExtra("active_user", JSON_active_user);
        intent.putExtra("locales", JSON_locales);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void gestionUsuarios(View view){
        startActivity(new Intent(getApplicationContext(), VerifyUsersActivity.class));
        overridePendingTransition(0, 0);
    }

    public void cerrarSesion(View view){
        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        overridePendingTransition(0, 0);
    }
}
