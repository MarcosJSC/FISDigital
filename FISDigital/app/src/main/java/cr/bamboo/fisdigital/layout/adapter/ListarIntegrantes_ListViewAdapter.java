package cr.bamboo.fisdigital.layout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.activity.Activity_Integrante_1;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.logic.bo.Integrante;

/**
 * Created by Jose Beita on 26/03/2015.
 * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar los integrantes
 */
public class ListarIntegrantes_ListViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Integrante> list = new ArrayList<Integrante>();
    private Context context;
    FISController fisController = FISController.getInstance();

    public ListarIntegrantes_ListViewAdapter(ArrayList<Integrante> list, Context context) {
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.fragment_integrante_row, null);
        }

        ((TextView)view.findViewById(R.id.nombre_integrante)).setText(list.get(position).getNombreCompleto());
        ((TextView)view.findViewById(R.id.numero_orden)).setText(""+(position+1));
        ((TextView)view.findViewById(R.id.parentesco_integrante)).setText(list.get(position).getParentezcoNumero());

        Button btnEditar = (Button)view.findViewById(R.id.btnEditarIntegrante);

        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fisController.idIntegranteEditar = list.get(position).getId();
                Intent openStep = new Intent(context, Activity_Integrante_1.class);
                context.startActivity(openStep);
            }
        });
        return view;
    }
}