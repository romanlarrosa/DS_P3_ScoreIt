package usuarios;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Usuario {
    private int id;
    private String nombre;
    private String tipo;
    private boolean verificado;

    public Usuario(){

    }
    public Usuario(int id, String nombre, String tipo, boolean verificado){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.verificado = verificado;
    }
    public Usuario(String user_json){
        try{
            JSONArray jsonArray = new JSONArray(user_json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            //Log.d("JSON", jsonObject.getString("id_usuario"));
            this.id = Integer.parseInt(jsonObject.getString("id_usuario"));
            this.nombre = jsonObject.getString("nombre");
            this.tipo = jsonObject.getString("tipo");
            this.verificado = Boolean.parseBoolean(jsonObject.getString("verificado"));
            if(jsonObject.getString("verificado").equals("1")){
                Log.d("JSON", jsonObject.getString("id_usuario"));
                this.verificado = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String verificar() throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idUsuario", String.valueOf(this.id));

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
            String url = "http://85.136.47.127/verificarusuarios.php";
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

            Log.d("test", "result from server: " + result.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public static ArrayList<Usuario> getListaUsuarios(String response){
        final ArrayList<Usuario> users = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Log.d("JSON", jsonObject.getString("id_usuario"));
                int id = Integer.parseInt(jsonObject.getString("id_usuario"));
                String nombre = jsonObject.getString("nombre");
                String tipo = jsonObject.getString("tipo");
                Boolean verificado = Boolean.parseBoolean(jsonObject.getString("verificado"));
                if(jsonObject.getString("verificado").equals("1")){
                    verificado = true;
                }

                users.add(new Usuario(id, nombre, tipo, verificado));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }


    public String eliminar() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idUsuario", String.valueOf(this.id));

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
            String url = "http://85.136.47.127/eliminarusuarios.php";
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

            Log.d("test", "result from server: " + result.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
