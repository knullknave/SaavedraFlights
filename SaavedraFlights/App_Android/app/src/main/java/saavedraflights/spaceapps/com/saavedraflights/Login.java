package saavedraflights.spaceapps.com.saavedraflights;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import saavedraflights.spaceapps.com.saavedraflights.objects.Usuario;
import saavedraflights.spaceapps.com.saavedraflights.server.ListaUsuarios;

public class Login extends Activity implements View.OnClickListener {

    private Button btLogin;
    private Button btRegistrar;
    private EditText email;
    private EditText password;
    private String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_login);

        crearListeners();
    }

    private void crearListeners() {
        btLogin = (Button) findViewById(R.id.btEntrar);
        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        email = (EditText) findViewById(R.id.textEmail);
        password = (EditText) findViewById(R.id.textPassword);
        btLogin.setOnClickListener(this);
        btRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btEntrar:
                String email2 = email.getText().toString();
                String passU = password.getText().toString();
                //CLASE QUE LLAMA AL SERVIDOR
                ListaUsuarios listaU = new ListaUsuarios();
                //EL ARRAY QUE RECOGE LOS DATOS DEL SERVIDOR
                ArrayList<Usuario>listaUsuarios = new ArrayList<>();
                listaUsuarios= listaU.devolverUsuarios(email2);
                if(listaUsuarios.size()>0) {
                    if (!listaUsuarios.get(0).getPassword().equals(passU)) {
                        Toast.makeText(this, R.string.user_pass_incorrecto, Toast.LENGTH_SHORT).show();
                        email.setText("");
                        password.setText("");
                    } else {
                        //TODO CAMBIAR POR NOMBRE
                        nombreUsuario = listaUsuarios.get(0).getNombre();
                        Toast.makeText(this, nombreUsuario, Toast.LENGTH_SHORT).show();
                        //pasariamos a la activiy de aeropuertos
                        intent = new Intent(this, Login.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.btRegistrar:
                intent = new Intent(this,Registro.class);
                startActivity(intent);
                break;
        }
    }
}
