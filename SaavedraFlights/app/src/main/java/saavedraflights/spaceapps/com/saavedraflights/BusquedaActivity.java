package saavedraflights.spaceapps.com.saavedraflights;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import saavedraflights.spaceapps.com.saavedraflights.adapter.adapterAeropuerto;
import saavedraflights.spaceapps.com.saavedraflights.objects.Aeropuerto;
import saavedraflights.spaceapps.com.saavedraflights.server.ListaAeropuertos;

public class BusquedaActivity extends Fragment implements View.OnClickListener {

    private EditText textCiudad;
    private ImageButton botonB;
    private ListView listaR;
    private adapterAeropuerto adapter;
    private ArrayList<Aeropuerto> listaAeropuertos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_busqueda, container, false);
        textCiudad = (EditText) view.findViewById(R.id.textEmail);
        botonB = (ImageButton) view.findViewById(R.id.ibBuscarA);
        listaR = (ListView) view.findViewById(R.id.ltResultados);
        botonB.setOnClickListener(this);
        //MENU CONTEXTUAL DENTRO DE LA LISTA
        registerForContextMenu(listaR);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_listado, menu);
    }

    //EVENTO DEL MENU CONTEXTUAL QUE ESCUCHA QUE OPCION SE PULSA DEL MENÚ
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //EN QUE PARTE DE LA LISTA HAS PULSADO
        Aeropuerto aeropuertoSeleccionado = (Aeropuerto) listaAeropuertos.get(info.position);
        switch(item.getItemId()){
            case R.id.action_ver:
                //CAMBIAR DATOS POR LOS DEL AEROPUERTO
                //TODO LLEVAR A LA ACTIVITY DE MAPA YDATOS AEROPUERTO
                Intent intent = new Intent(getActivity(),InformacionAeropuerto.class);
                intent.putExtra("nombre", aeropuertoSeleccionado.getNombre());
                intent.putExtra("ciudad",aeropuertoSeleccionado.getCiudad());
                intent.putExtra("latitud",aeropuertoSeleccionado.getLatitud());
                intent.putExtra("longitud",aeropuertoSeleccionado.getLongitud());
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibBuscarA:
                //CAMBIAR ESTO POR TEXTCIUDAD
                String ciudad = textCiudad.getText().toString();
                //BUSCAR EN EL SERVIDOR POR PARAMETRO
                ListaAeropuertos listaA = new ListaAeropuertos();
                listaAeropuertos = new ArrayList<>();
                listaAeropuertos = listaA.getAirport(ciudad);
                adapter = new adapterAeropuerto(getActivity(),R.layout.activity_registro,listaAeropuertos);
                listaR.setAdapter(adapter);
                break;
            default:
                break;
        }
    }


}
