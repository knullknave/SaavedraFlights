package saavedraflights.spaceapps.com.saavedraflights;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import saavedraflights.spaceapps.com.saavedraflights.Util.GPSTracker;
import saavedraflights.spaceapps.com.saavedraflights.objects.Usuario;
import saavedraflights.spaceapps.com.saavedraflights.server.ListaUsuarios;

public class Registro extends Activity implements View.OnClickListener {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etCorreo;
    private EditText etPassword;
    private GPSTracker gps;
    private double coordenadas[] = new double[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText)findViewById(R.id.TextNombre);
        etApellido = (EditText)findViewById(R.id.TextApellidos);
        etCorreo = (EditText)findViewById(R.id.TextEmail);
        etPassword = (EditText)findViewById(R.id.TextContrasena);

        Button registrarse = (Button)findViewById(R.id.btRegistrar);
        registrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btRegistrar:
                Usuario usuario = new Usuario();
                usuario.setNombre(String.valueOf(etNombre.getText()));
                usuario.setApellidos(String.valueOf(etApellido.getText()));
                usuario.setEmail(String.valueOf(etCorreo.getText()));
                usuario.setPassword(String.valueOf(etPassword.getText()));
                //usuario.setNotificaciones(String.valueOf);
                //TODO HACER CHECK NOTIFICACIONES
                //GUARDAR DATOS GPS
                datosGPS();
                usuario.setLatitud(coordenadas[0]);
                usuario.setLongitud(coordenadas[1]);
                //LLAMAR AL SERVIDOR PARA GUARDAR LOS DATOS
                ListaUsuarios listaUsuarios = new ListaUsuarios();
                listaUsuarios.anadir_usuario(usuario);
                Toast mensaje = Toast.makeText(getApplicationContext(), R.string.usuario_creado, Toast.LENGTH_SHORT);
                mensaje.show();
                break;
            default:
                break;
        }
    }

    private void datosGPS() {
        gps = new GPSTracker(Registro.this);

        if (gps.canGetLocation()) {
            coordenadas[0] = gps.getLatitude();
            coordenadas[1] = gps.getLongitude();

            Toast.makeText(
                    getApplicationContext(),
                    "Your Location is -\nLat: " + coordenadas[0] + "\nLong: "
                            + coordenadas[1], Toast.LENGTH_LONG).show();
        } else {
            gps.showSettingsAlert();
        }
    }
}
