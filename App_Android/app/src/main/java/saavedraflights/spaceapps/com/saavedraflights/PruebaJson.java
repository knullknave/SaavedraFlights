package saavedraflights.spaceapps.com.saavedraflights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PruebaJson extends Activity implements OnClickListener, LocationListener {

    // URL donde se encuentra el fichero JSON con toda la información
    private static final String URL = "https://api.forecast.io/forecast/cd578856d111d2772e67f1b6a54b9892/41.6561,-0.8773";
    private ArrayList<String>listaDatos=new ArrayList<>();
    private EditText textA;
    private EditText textB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeropuerto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View view) {

        TareaDescargaDatos tarea = new TareaDescargaDatos();
        tarea.execute(URL);
    }

    private class TareaDescargaDatos extends AsyncTask<String, Void, Void> {

        private boolean error = false;

        // Este método no puede acceder a la interfaz
        @Override
        protected Void doInBackground(String... urls) {

            InputStream is = null;
            String resultado = null;
            JSONObject json = null;
            JSONArray jsonArray = null;

            try {
                // Conecta con la URL y obtenemos el fichero con los datos
                HttpClient clienteHttp = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urls[0]);
                HttpResponse respuesta = clienteHttp.execute(httpPost);
                HttpEntity entity = respuesta.getEntity();
                is = entity.getContent();

                // Lee el fichero de datos y genera una cadena de texto como resultado
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String linea = null;

                while ((linea = br.readLine()) != null) {
                    sb.append(linea + "\n");
                }

                is.close();
                resultado = sb.toString();

                // La cadena de texto resultante es un objeto JSON
                json = new JSONObject(resultado);
                // Obtiene el objeto JSON como un array de datos
                jsonArray = json.getJSONArray("currently");
                if(jsonArray != null){
                    System.out.println("VA BIEN");
                }
                else{
                    System.out.println("casca");
                }

                // Recorre el array JSON para mostrar los datos que interesen
                for (int i = 0; i < jsonArray.length(); i++) {
                    // Nombre de la gasolinera
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("time"));
                    // Posición de la gasolinera
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("sumary"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("icon"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("temperature"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("humidity"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("windSpeed"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("visibility"));
                    listaDatos.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("pressure"));
                    // CARGAR LOS DATOS EN PANTALLA
                    System.out.println(listaDatos.get(0)+"TIEMPO");

                }

            } catch (ClientProtocolException cpe) {
                cpe.printStackTrace();
                error = true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                error = true;
            } catch (JSONException jse) {
                jse.printStackTrace();
                error = true;
            }

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            listaDatos = new ArrayList<>();
        }

        @Override
        protected void onProgressUpdate(Void... progreso) {
            super.onProgressUpdate(progreso);

        }

        @Override
        protected void onPostExecute(Void resultado) {
            super.onPostExecute(resultado);

        }
    }

}
