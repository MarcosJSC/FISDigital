package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.adapter.ListarIntegrantes_ListViewAdapter;
import cr.bamboo.fisdigital.logic.bo.Integrante;
import cr.bamboo.fisdigital.logic.global.FISController;

public class Activity_ListarIntegrantes extends FISActivity {

    FISController fisController = FISController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_integrantes);
        super.lookBackButton(3);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Integrantes");

        loadData();

        final Button btnAgregarIntegrante = (Button) findViewById(R.id.btnAgregarIntegrante);
        btnAgregarIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fisController.idIntegranteEditar = 0;
                fisController.esJefeSecundario = false;
                Intent openStep = new Intent(Activity_ListarIntegrantes.this, Activity_Integrante_1.class);
                startActivity(openStep);
            }
        });

        Button btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStep = new Intent(Activity_ListarIntegrantes.this, Activity_ListarFamilias.class);
                startActivity(openStep);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        loadData();
    }

    public void loadData(){

        ArrayList<Integrante> listaIntegrantes = fisController.getIntegrantes();

        ListarIntegrantes_ListViewAdapter adapter = new ListarIntegrantes_ListViewAdapter(listaIntegrantes, this);
        ListView miembrosLV = ((ListView) findViewById(R.id.miembrosListView));
        miembrosLV.setAdapter(adapter);

        fisController.esJefePrincipal = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                // clear instance object
                Intent openStep = new Intent(this, Activity_Login.class);
                // remove the back stack! Super important stuff, so the user wont go back
                // to this page by clicking back
                openStep.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(openStep, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
