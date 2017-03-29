package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.data.ftp.output.MyTaskSyncFIS;
import cr.bamboo.fisdigital.data.ftp.common.OnTaskListener;


public class Activity_PantallaSyncFIS extends Activity implements OnTaskListener {


    @Override
    public void TaskDone(String message) {

        if(message == "done"){
            System.out.println(message);
            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
            viewer.loadUrl("file:///android_asset/ready-icon.png");

            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
            tv.setText("Datos Sincronizados");

            ((Button) findViewById(R.id.btnFinalizarSync)).setVisibility(View.VISIBLE);
        }else{
            System.out.println(message);
            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
            viewer.loadUrl("file:///android_asset/ready-icon.png");

            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
            tv.setText("No existen datos para sincronizar");

            ((Button) findViewById(R.id.btnFinalizarSync)).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setProgressPercent(String... catalogo){
        TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
        tv.setText("Sincronizando: "+catalogo[0]);
    }

    @Override
    public void onBackPressed() {
        TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
        String currentText = tv.getText().toString();
        tv.setText(currentText +"\nNo se debe detener la sincronización.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_sync_fis);

        Button btnFinalizarSync = (Button) findViewById(R.id.btnFinalizarSync);
        btnFinalizarSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStep = new Intent(Activity_PantallaSyncFIS.this, Activity_PantallaInicial.class);
                finish();
                openStep.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(openStep, 0);
            }
        });

        WebView viewer = (WebView) findViewById(R.id.loading_viewer);
        viewer.setBackgroundColor(Color.TRANSPARENT);

        if(isOnline(this.getApplicationContext())) {
            viewer.loadUrl("file:///android_asset/ripple.gif");
            MyTaskSyncFIS myTask = new MyTaskSyncFIS(this);
            myTask.setOnTaskListener(Activity_PantallaSyncFIS.this);
            myTask.execute(1, 1);
            restoreActionBar();
        }
        else{
            viewer.loadUrl("file:///android_asset/ready-icon.png");
            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
            tv.setText("Sus datos han sido almacenados con éxito, pero no hay una conexión activa a internet. Intente sincronizar más tarde.");
            ((Button) findViewById(R.id.btnFinalizarSync)).setVisibility(View.VISIBLE);
        }
    }

    boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Exportar FIS");
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
