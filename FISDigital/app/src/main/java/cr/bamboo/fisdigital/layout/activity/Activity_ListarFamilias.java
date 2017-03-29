package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.adapter.ListarFamilias_ListViewAdapter;
import cr.bamboo.fisdigital.logic.bo.Familia;
import cr.bamboo.fisdigital.logic.global.FISController;

public class Activity_ListarFamilias extends FISActivity {

    FISController fisController = FISController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_familias);
        super.lookBackButton(2);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Familias");

        loadData();

        final Button btnAgregarFamilia = (Button) findViewById(R.id.btnAgregarFamilia);
        btnAgregarFamilia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fisController.idFamiliaEditar = 0;
                fisController.idIntegranteEditar = 0;
                fisController.esJefeSecundario = true;
                Intent openStep = new Intent(Activity_ListarFamilias.this, Activity_Integrante_1.class);
                startActivity(openStep);
            }
        });

        Button btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canFinish = fisController.canFinishFIS();
                if(canFinish) {
                    if(fisController.getIdDispositivo().equals("")) {
                        try {
                            fisController.setIdDispositivo(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Ha ocurrido un error al obtener el número de identificación de su dispositivo. Reinicie la aplicación y contacte a un miembro de TI.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    fisController.finishFIS();
                    Intent openStep = new Intent(Activity_ListarFamilias.this, Activity_PantallaSyncFIS.class);
                    startActivity(openStep);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Antes de poder finalizar la FIS debe agregar el Patrimonio a todas las familias", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        loadData();
    }

    public void loadData(){

        ArrayList<Familia> listaFamilias = fisController.getFamilias();

        ListarFamilias_ListViewAdapter adapter = new ListarFamilias_ListViewAdapter(listaFamilias, this);
        ListView familiasLV = ((ListView)findViewById(R.id.familiasListView));
        familiasLV.setAdapter(adapter);

        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        familiasLV.setEmptyView(emptyText);

        if (listaFamilias.size() == 0) {
            findViewById(R.id.gridHeaderFamilia).setVisibility(View.GONE);
            fisController.esJefePrincipal = true;
        }
        else{
            fisController.esJefePrincipal = false;
        }

        ((Button) findViewById(R.id.btnFinalizar)).setEnabled(!listaFamilias.isEmpty());
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
