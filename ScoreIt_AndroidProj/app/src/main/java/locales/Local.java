package locales;

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

public class Local {
    private int id;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private int owner;

    public Local(int id, String nombre, String ubicacion, String descripcion, int owner) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.owner = owner;
    }

    public Local() {
    }

    public Local(String local_json){
        try{
            JSONArray jsonArray = new JSONArray(local_json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            //Log.d("JSON", jsonObject.getString("id_usuario"));
            this.id = Integer.parseInt(jsonObject.getString("id_local"));
            this.nombre = jsonObject.getString("nombre_local");
            this.ubicacion = jsonObject.getString("ubicacion");
            this.descripcion = jsonObject.getString("descripcion");
            this.owner = Integer.parseInt(jsonObject.getString("owner_local"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public static ArrayList<Local> getListaLocales(String response){
        final ArrayList<Local> locales = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //Log.d("JSON", jsonObject.getString("id_usuario"));
                int id = Integer.parseInt(jsonObject.getString("id_local"));
                String nombre = jsonObject.getString("nombre_local");
                String ubicacion = jsonObject.getString("ubicacion");
                String descripcion = jsonObject.getString("descripcion");
                int owner = Integer.parseInt(jsonObject.getString("owner_local"));

                locales.add(new Local(id, nombre, ubicacion, descripcion, owner));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return locales;
    }

    public static ArrayList<Local> getListaRankingLocales(String response){
        final ArrayList<Local> locales = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //Log.d("JSON", jsonObject.getString("id_usuario"));
                int id = Integer.parseInt(jsonObject.getString("id_local"));
                String nombre = jsonObject.getString("nombre_local");
                String ubicacion = jsonObject.getString("ubicacion");
                String descripcion = jsonObject.getString("descripcion");
                int owner = Integer.parseInt(jsonObject.getString("owner_local"));

                locales.add(new Local(id, nombre, ubicacion, descripcion, owner));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return locales;
    }

    public static String getLocalesOwner(int id) {
        //Buscar los locales cuyo dueño es id
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(id));

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
            String url = "http://85.136.47.127/getlocalesowner.php";
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


    public static String getLocalesBusqueda(String busqueda) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("search", busqueda);

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
            String url = "http://85.136.47.127/buscar.php";
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

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id_local", String.valueOf(this.id));
            jsonObject.put("nombre_local", this.nombre);
            jsonObject.put("ubicacion", this.ubicacion);
            jsonObject.put("descripcion", this.descripcion);
            jsonObject.put("owner_local", String.valueOf(this.owner));

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);


            return jsonArray.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public float calculaPuntuacion() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id_local", String.valueOf(this.id));

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
            String url = "http://85.136.47.127/getpuntuacion.php";
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

            Log.d("localtest", "result from server: " + result.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return Float.parseFloat(result.toString());
    }

    public static String getRanking() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HashMap<String, String> params = new HashMap<String, String>();

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
            String url = "http://85.136.47.127/getranking.php";
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
