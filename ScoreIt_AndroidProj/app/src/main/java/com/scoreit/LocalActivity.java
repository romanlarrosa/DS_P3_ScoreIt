package com.scoreit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import locales.Local;
import usuarios.Usuario;

public class LocalActivity extends AppCompatActivity {

    String JSON_active_user;
    String JSON_local_activo;

    Local local;
    Usuario usuario;

    TextView nombre, ubicacion, puntuacion, descripcion;
    ArrayList<ImageView> estrellas;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        Bundle bundle = getIntent().getExtras();
        JSON_active_user = bundle.getString("active_user");
        JSON_local_activo = bundle.getString("local_activo");

        local = new Local(JSON_local_activo);
        usuario = new Usuario(JSON_active_user);

        estrellas = new ArrayList<>();

        estrellas.add((ImageView) findViewById(R.id.star1));
        estrellas.add((ImageView) findViewById(R.id.star2));
        estrellas.add((ImageView) findViewById(R.id.star3));
        estrellas.add((ImageView) findViewById(R.id.star4));
        estrellas.add((ImageView) findViewById(R.id.star5));

        nombre = findViewById(R.id.local_name);
        ubicacion = findViewById(R.id.local_location);
        puntuacion = findViewById(R.id.puntuacion_value);
        descripcion = findViewById(R.id.descripcion);

        nombre.setText(local.getNombre());
        ubicacion.setText(local.getUbicacion());
        puntuacion.setText(String.valueOf(local.calculaPuntuacion()));
        descripcion.setText(local.getDescripcion());

    }

    public void setScore(View view){
        score = Integer.parseInt(view.getTag().toString());


        for (int i=0; i<score; i++){
            estrellas.get(i).setImageResource(R.mipmap.ic_star);
        }
        for (int i=score; i<5; i++){
            estrellas.get(i).setImageResource(R.mipmap.ic_star_vacia);
        }
    }

    public void scoreit(View view){
        Toast.makeText(LocalActivity.this, "Has votado: " + score, Toast.LENGTH_SHORT).show();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id_usuario", String.valueOf(usuario.getId()));
        params.put("id_local", String.valueOf(local.getId()));
        params.put("puntuacion", String.valueOf(score));

        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), "UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        StringBuilder result = new StringBuilder();

        try{
            String url = "http://85.136.47.127/puntuar.php";
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            String paramsString = sbParams.toString();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramsString);
            wr.flush();
            wr.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("Puntuar", result.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }

        puntuacion.setText(String.valueOf(local.calculaPuntuacion()));

    }
}
