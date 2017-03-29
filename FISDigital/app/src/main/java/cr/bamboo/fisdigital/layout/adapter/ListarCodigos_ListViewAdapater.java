package cr.bamboo.fisdigital.layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.activity.Activity_CodigosEspeciales;
import cr.bamboo.fisdigital.logic.bo.Codigo;
import cr.bamboo.fisdigital.logic.global.FISController;

/**
 * Created by Jbeita on 26/03/2015.
 * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar los códigos especiales
 */

public class ListarCodigos_ListViewAdapater extends BaseAdapter implements ListAdapter {
    private ArrayList<Codigo> list = new ArrayList<Codigo>();
    private Context context;
    private boolean eliminar;
    FISController fisController = FISController.getInstance();
    private ArrayList<Codigo> agregados = new ArrayList<Codigo>();

    public ListarCodigos_ListViewAdapater(ArrayList<Codigo> list, Context context, boolean eliminar) {
        this.list = list;
        this.context = context;
        this.eliminar = eliminar;
        this.agregados = agregados;
    }

    public void addItem(Codigo codigo){
        this.list.add(0, codigo);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
//        return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_codigo_row, null);
        }

        //Handle TextView and display string from your list
        ((TextView)view.findViewById(R.id.fis_codigo)).setText(list.get(position).getCodigo());
        ((TextView)view.findViewById(R.id.fis_descripcion_codigo)).setText(list.get(position).getDescripcion());
        String textoBoton = "Agregar";
        if(eliminar){
            textoBoton = "Eliminar";
        }
        Button btnAccion = ((Button) view.findViewById(R.id.btnAccionCodigo));
        btnAccion.setText(textoBoton);
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eliminar){
                    fisController.deleteCodigo(list.get(position).getCodigo());
                    ((Activity_CodigosEspeciales)context).updateBusqueda(list.get(position));
                }
                else{
                    fisController.saveCodigo(list.get(position).getCodigo());
                    ((Activity_CodigosEspeciales)context).updateBusqueda();
                    list.remove(position);
                }

                ((Activity_CodigosEspeciales)context).loadData();
            }
        });
        return view;
    }
}