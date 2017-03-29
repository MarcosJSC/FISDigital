package cr.bamboo.fisdigital.layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

/**
 * Created by Jbeita on 26/03/2015.
 * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar los catálgoos
 */
public class Catalogo_ListViewAdapter extends BaseAdapter implements ListAdapter {
    private List<Opcion> list;
    private Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    public Catalogo_ListViewAdapter(List<Opcion> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
//        return 0;
        return Long.parseLong(list.get(pos).getCodigo().trim());
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_catalogos_row, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        String opcion = list.get(position).getCodigo() + " - " + list.get(position).getDescripcion();
        listItemText.setText(opcion);
//        listItemText.setTextColor(Color.WHITE);

        return view;
    }
}