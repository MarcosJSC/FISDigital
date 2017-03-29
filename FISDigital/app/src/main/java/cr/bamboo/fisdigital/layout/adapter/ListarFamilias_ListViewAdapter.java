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
import cr.bamboo.fisdigital.layout.activity.Activity_CodigosEspeciales;
import cr.bamboo.fisdigital.layout.activity.Activity_Correos;
import cr.bamboo.fisdigital.layout.activity.Activity_ListarIntegrantes;
import cr.bamboo.fisdigital.layout.activity.Activity_Patrimonio;
import cr.bamboo.fisdigital.logic.bo.Familia;
import cr.bamboo.fisdigital.logic.global.FISController;

/**
 * Created by Jbeita on 26/03/2015.
 * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar las familias
 */
public class ListarFamilias_ListViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Familia> list = new ArrayList<Familia>();
    private Context context;
    FISController fisController = FISController.getInstance();

    public ListarFamilias_ListViewAdapter(ArrayList<Familia> list, Context context) {
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

        try {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.fragment_familia_row, null);
            }

            //Handle TextView and display string from your list
            TextView tvNombreFamilia = (TextView) view.findViewById(R.id.nombre_familia);
            tvNombreFamilia.setText(list.get(position).getNombreFamilia());

            TextView tvNumeroFamilia = (TextView) view.findViewById(R.id.tvNumeroFamilia_Row);
            tvNumeroFamilia.setText(list.get(position).getNumero()+"");

            //Handle buttons and add onClickListeners
            Button btnIntegrantes = (Button) view.findViewById(R.id.btnIntegrantes);
            Button btnPatrimonio = (Button) view.findViewById(R.id.btnPatrimonio);
            Button btnCorreos = (Button) view.findViewById(R.id.btnCorreos);
            final Button btnCodigos = (Button) view.findViewById(R.id.btnCodigosEspeciales);

            btnIntegrantes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fisController.positionFamilia = position;
                    fisController.idFamiliaEditar = list.get(position).getIdFamilia();
                    Intent openStep = new Intent(context, Activity_ListarIntegrantes.class);
                    context.startActivity(openStep);

                }
            });
            btnPatrimonio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fisController.idFamiliaEditar = list.get(position).getIdFamilia();
                    Intent openStep = new Intent(context, Activity_Patrimonio.class);
                    context.startActivity(openStep);
                }
            });

            btnCorreos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fisController.idFamiliaEditar = list.get(position).getIdFamilia();
                    Intent openStep = new Intent(context, Activity_Correos.class);
                    context.startActivity(openStep);
                }
            });

            btnCodigos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fisController.idFamiliaEditar = list.get(position).getIdFamilia();
                    Intent openStep = new Intent(context, Activity_CodigosEspeciales.class);
                    context.startActivity(openStep);
                }
            });
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return view;
    }
}