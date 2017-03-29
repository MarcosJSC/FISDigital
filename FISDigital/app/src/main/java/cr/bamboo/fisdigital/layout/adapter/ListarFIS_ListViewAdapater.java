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
        import cr.bamboo.fisdigital.layout.activity.Activity_ListarFamilias;
        import cr.bamboo.fisdigital.layout.activity.Activity_Ubicacion;
        import cr.bamboo.fisdigital.layout.activity.Activity_ViviendaServicios_1;
        import cr.bamboo.fisdigital.logic.bo.FIS;
        import cr.bamboo.fisdigital.logic.global.FISController;

/**
 * Created by Jbeita on 26/03/2015.
 * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar las FIS
 */

public class ListarFIS_ListViewAdapater extends BaseAdapter implements ListAdapter {
    private ArrayList<FIS> list = new ArrayList<FIS>();
    private Context context;
    FISController fisController = FISController.getInstance();

    public ListarFIS_ListViewAdapater(ArrayList<FIS> list, Context context) {
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
            view = inflater.inflate(R.layout.fragment_fis_item_row, null);
        }

        //Handle TextView and display string from your list
        ((TextView)view.findViewById(R.id.fis_fecha_creacion)).setText(String.valueOf(list.get(position).getFechaCreacion()));
        ((TextView)view.findViewById(R.id.fis_numero_folio)).setText(String.valueOf(list.get(position).getNumeroFolio()));
        ((TextView)view.findViewById(R.id.fis_estado)).setText(String.valueOf(list.get(position).getEstado()));
        //listItemText.setText(list.get(position).id);

        ((Button) view.findViewById(R.id.btnUbicacion)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fisController.idFISEditar = list.get(position).getId();
                Intent openStep = new Intent(context, Activity_Ubicacion.class);
                context.startActivity(openStep);
            }
        });

        Button btnViviendaServicios = ((Button) view.findViewById(R.id.btnViviendaServicios));
        btnViviendaServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fisController.idFISEditar = list.get(position).getId();
                Intent openStep = new Intent(context, Activity_ViviendaServicios_1.class);
                context.startActivity(openStep);
            }
        });

        Button btnFamilias = ((Button) view.findViewById(R.id.btnFamilias));
        btnFamilias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fisController.idFISEditar = list.get(position).getId();
                Intent openStep = new Intent(context, Activity_ListarFamilias.class);
                context.startActivity(openStep);
            }
        });

        return view;
    }
}