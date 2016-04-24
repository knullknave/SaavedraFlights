package saavedraflights.spaceapps.com.saavedraflights.server;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import saavedraflights.spaceapps.com.saavedraflights.objects.Aeropuerto;
import saavedraflights.spaceapps.com.saavedraflights.objects.Usuario;

import static saavedraflights.spaceapps.com.saavedraflights.Util.Constantes.SERVER_URL;

/**
 * Created by Ainoa on 12/12/2015.
 */

public class ListaAeropuertos {


    /**
     * Devuelve la lista de usuarios con sus datos a un arrayList de la clase usuario
     */

    public ArrayList<Aeropuerto>devolverAllAirports(){
        // Llamada a un servicio web y recogida de los datos que devuelve
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Aeropuerto listaA = restTemplate.getForObject(SERVER_URL + "/aeropuertos", Aeropuerto.class);
        ArrayList<Aeropuerto>listaAeropuertos = new ArrayList<>();
        listaAeropuertos.addAll(Arrays.asList(listaA));
        return listaAeropuertos;
    }

    public ArrayList<Aeropuerto> getAirport(String ciudad){
        // Llamada a un servicio web y recogida de los datos que devuelve
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Aeropuerto[] listaA = restTemplate.getForObject(SERVER_URL + "/get_aeropuerto?ciudad=" + ciudad, Aeropuerto[].class);
        ArrayList<Aeropuerto>listaAeropuertos = new ArrayList<>();
        listaAeropuertos.addAll(Arrays.asList(listaA));
        return listaAeropuertos;
    }



}
