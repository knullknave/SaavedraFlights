package saavedraflights.spaceapps.com.saavedraflights.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import saavedraflights.spaceapps.com.saavedraflights.R;
import saavedraflights.spaceapps.com.saavedraflights.objects.Aeropuerto;

/**
 * Created by Ainoa on 14/12/2015.
 */
public class adapterAeropuerto extends ArrayAdapter<Aeropuerto> {

    //CAMBIAR EL NOMBRE DEL ADAPTER
    private Context contexto;
    private int layoutId;
    private List<Aeropuerto> datos;

    public adapterAeropuerto(Context contexto, int layoutId, List<Aeropuerto> datos) {
        super(contexto,layoutId,datos);
        this.contexto = contexto;
        this.layoutId = layoutId;
        this.datos = datos;
    }


    static class ViewHolder{
        TextView nombre;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup padre) {

        //MEJORAR ADD FOTO AEROPUERTO EN EL ADAPTER
        ViewHolder item = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            view = inflater.inflate(layoutId, padre, false);

            item = new ViewHolder();
            item.nombre = (TextView)view.findViewById(R.id.tvNombreUsuario);

            view.setTag(item);
        }
        else {
            item = (ViewHolder) view.getTag();
        }

        Aeropuerto aeropuerto = datos.get(posicion);
        item.nombre.setText(aeropuerto.getNombre());

        return view;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Aeropuerto getItem(int posicion) {

        return datos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

}
