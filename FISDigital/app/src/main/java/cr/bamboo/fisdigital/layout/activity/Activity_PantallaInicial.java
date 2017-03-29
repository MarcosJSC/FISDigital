package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Usuario;


public class Activity_PantallaInicial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
        final Context context = this;
        Button btnIniciarFIS = (Button) findViewById(R.id.btnIniciarFIS);

        btnIniciarFIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FISController fisController = FISController.getInstance();
//                fisController.reset();

//                Bundle b = new Bundle();
//                b.putString("title", "THIS IS TITLE"); //Your id
//                b.putString("btnTitle", "THIS IS BUTTON"); //Your id
                //                openStep.putExtras(b);

                Intent openStep = new Intent(Activity_PantallaInicial.this, Activity_ListarFIS.class);
                startActivity(openStep);
            }
        });

        findViewById(R.id.btnSincronizarObtener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnline(context)) {
                    Toast.makeText(getApplicationContext(), "Para poder sincronizar con el servidor requiere de una conexión a internet activa", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent openStep = new Intent(Activity_PantallaInicial.this, Activity_PantallaSyncCatalogos.class);
                startActivity(openStep);
            }
        });
        findViewById(R.id.btnSincronizarEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnline(context)) {
                    Toast.makeText(getApplicationContext(), "Para poder sincronizar con el servidor requiere de una conexión a internet activa", Toast.LENGTH_LONG).show();
                    return;
                }
            Intent openStep = new Intent(Activity_PantallaInicial.this, Activity_PantallaSyncFIS.class);
            startActivity(openStep);
        }
        });

        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear instance object
                FISController.getInstance().Initialize(new Usuario(), "", 0, 0);
                Intent openStep = new Intent(Activity_PantallaInicial.this, Activity_Login.class);
                // remove the back stack! Super important stuff, so the user wont go back
                // to this page by clicking back
                openStep.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(openStep, 0);
            }
        });
    }
    boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_inicial, menu);
        return true;
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
