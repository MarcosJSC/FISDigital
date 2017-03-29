package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.adapter.ListarFIS_ListViewAdapater;
import cr.bamboo.fisdigital.logic.global.FISController;


/**
 * Created by walter on 26/04/15.
 */
public class Activity_ListarFIS extends FISActivity {


    FISController fisController = FISController.getInstance();
    String mTitle = "Listar FIS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.lookBackButton(1);
        setContentView(R.layout.activity_listar_fis);

        loadData();

        ((Button) findViewById(R.id.btnCrearFIS)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fisController.reset();
                Intent openStep = new Intent(Activity_ListarFIS.this, Activity_Ubicacion.class);
                startActivity(openStep);
                fisController.idFISEditar = 0;

            }
        });

//        ((Button) findViewById(R.id.btnHistorial)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fisController.borrarFIS();
//            }
//        });

        restoreActionBar();
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        loadData();
    }

    public void loadData(){
        ArrayList fislist = fisController.getFISList();
        ListarFIS_ListViewAdapater adapter = new ListarFIS_ListViewAdapater(fislist, this);
        ListView fisLV = ((ListView) findViewById(R.id.fisListView));
        fisLV.setAdapter(adapter);

        if (fislist.size() == 0) {
            findViewById(R.id.gridHeaderFis).setVisibility(View.GONE);
        }

        TextView emptyText = (TextView) findViewById(android.R.id.empty);
        fisLV.setEmptyView(emptyText);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
