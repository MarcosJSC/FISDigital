package cr.bamboo.fisdigital.layout.adapter;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.logic.bo.Correo;
import cr.bamboo.fisdigital.logic.global.FISController;

/**
 * Created by dianavalerin on 7/13/15.
 * * Clase que maneja un adaptador de lista custom, en este caso
 * para desplegar los correos
 */
public class Correo_ListViewAdapter extends BaseAdapter implements ListAdapter{

    FISController fisController = FISController.getInstance();
    private ArrayList<Correo> list =  new ArrayList();
    private Context context;

    public Correo_ListViewAdapter(ArrayList<Correo> list, Context context) {
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
        return pos;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_correo_row, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_email_item);
        listItemText.setText(list.get(position).getCorreo());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.btn_delete_email);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                fisController.deleteCorreo(list.get(position).getIdCorreo());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
