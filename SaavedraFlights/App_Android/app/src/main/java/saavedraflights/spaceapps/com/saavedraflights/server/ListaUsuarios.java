package saavedraflights.spaceapps.com.saavedraflights.server;

import android.content.ContentValues;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import saavedraflights.spaceapps.com.saavedraflights.objects.Usuario;
import static saavedraflights.spaceapps.com.saavedraflights.Util.Constantes.*;

/**
 * Created by Ainoa on 12/12/2015.
 */

public class ListaUsuarios {


    /**
     * Devuelve la lista de usuarios con sus datos a un arrayList de la clase usuario
     */

    public ArrayList<Usuario>devolverAllUsers(){
        // Llamada a un servicio web y recogida de los datos que devuelve
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Usuario Usuarios = restTemplate.getForObject(SERVER_URL + "/usuarios", Usuario.class);
        ArrayList<Usuario>listaUsuarios = new ArrayList<>();
        listaUsuarios.addAll(Arrays.asList(Usuarios));
        return listaUsuarios;
    }

    public ArrayList<Usuario> devolverUsuarios(String email){
        // Llamada a un servicio web y recogida de los datos que devuelve
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Usuario[] usuario = restTemplate.getForObject(SERVER_URL + "/usuario_login?email=" + email, Usuario[].class);
        ArrayList<Usuario>listaUsuarios = new ArrayList<>();
        listaUsuarios.addAll(Arrays.asList(usuario));
        return listaUsuarios;
    }

    public void anadir_usuario(Usuario usuario){
        // Llamada a un servicio web y recogida de los datos que devuelve
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getForObject(SERVER_URL + "/add_usuario?id=nombre=" + usuario.getNombre() + "&apellidos=" +
                usuario.getApellidos() + "&email=" + usuario.getEmail() + "&password=" + usuario.getPassword() +
                        "&latitud=" + usuario.getLatitud() + "&longitud=" + usuario.getLongitud() + "&notificaciones=true", Void.class);
    }





}
