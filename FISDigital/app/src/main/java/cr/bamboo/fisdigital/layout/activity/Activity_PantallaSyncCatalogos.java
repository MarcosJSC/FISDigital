package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.data.ftp.common.OnTaskListener;
import cr.bamboo.fisdigital.data.ftp.input.AsyncWorker;

/**
 * Created by walter on 05/03/15.
 */

public class Activity_PantallaSyncCatalogos extends Activity implements OnTaskListener {
    private boolean CanGoBack = false;
    @Override
    public void TaskDone(String message) {
        if(message.equals("done")){
            System.out.println(message);
            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
            viewer.loadUrl("file:///android_asset/ready-icon.png");

            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
            tv.setText("Datos Sincronizados");

            ((Button) findViewById(R.id.btnFinalizarSync)).setVisibility(View.VISIBLE);
        }else if(message.equals("confirm")){
            final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("Ya cuenta con la última versión del catálogo. ¿Desea actualizarlo de todas formas?");
            dlgAlert.setTitle("Atención");

            dlgAlert.setCancelable(true);


            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startAsyncWorker(true);
                        }
                    });
            dlgAlert.setNegativeButton("Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
                            viewer.loadUrl("file:///android_asset/ready-icon.png");

                            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
                            tv.setText("Datos Sincronizados");

                            ((Button) findViewById(R.id.btnFinalizarSync)).setVisibility(View.VISIBLE);
                        }
                    });
            dlgAlert.create().show();

        }
        else{
            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
            viewer.loadUrl("file:///android_asset/ready-icon.png");

            TextView tv = (TextView) findViewById(R.id.txtSyncTitle);
            tv.setText("Error. Los Datos no fueron sincronizados.");

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
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pantalla_sync_catalogos);

            Button btnFinalizarSync = (Button) findViewById(R.id.btnFinalizarSync);
            btnFinalizarSync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            WebView viewer = (WebView) findViewById(R.id.loading_viewer);
            viewer.setBackgroundColor(Color.TRANSPARENT);
            viewer.loadUrl("file:///android_asset/ripple.gif");
            startAsyncWorker(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void startAsyncWorker(boolean byPass){
        AsyncWorker asyncWorker = new AsyncWorker(this, byPass);
        asyncWorker.setOnTaskListener(Activity_PantallaSyncCatalogos.this);
        asyncWorker.execute(1, 1);
    }


}
