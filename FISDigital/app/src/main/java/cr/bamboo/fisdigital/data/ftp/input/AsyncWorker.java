package cr.bamboo.fisdigital.data.ftp.input;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import cr.bamboo.fisdigital.data.ftp.common.OnTaskListener;

/**
 * Created by Jose Beita on 19/03/15.

 * Clase AsyncWorker hereda de AsyncTask, maneja la funcionalidad
 * de un hilo aparte en el que se descarga la informacion de los
 * catálogos
 */
public class AsyncWorker extends AsyncTask<Integer, String, String> {
    private ProgressDialog dialog;
    private OnTaskListener onTaskListener;

    private boolean ByPass = false;

    public void setOnTaskListener(OnTaskListener onTaskListener) {
        this.onTaskListener= onTaskListener;
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            if (EstadoCatalogos.IsLastVersion(context) && !ByPass) {
                return "confirm";
            } else {
                sync();
                return "done";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
    void sync(){
        try {

            FTPConnect loader = new FTPConnect(context);
            publishProgress("Provincias");
            loader.syncCatalogos(0);
            publishProgress("Cantones");
            loader.syncCatalogos(1);
            publishProgress("Distritos");
            loader.syncCatalogos(2);
            publishProgress("Caseríos");
            loader.syncCatalogos(3);
            publishProgress("Barrios");
            loader.syncCatalogos(4);
            publishProgress("Gerencias Regionales");
            loader.syncCatalogos(5);
            publishProgress("Cedes");
            loader.syncCatalogos(6);
            publishProgress("Region MidePlan");
            loader.syncCatalogos(7);
            publishProgress("Codigos Especiales");
            loader.syncCatalogos(9);
            publishProgress("Centros de Educación");
            loader.syncCatalogos(10);
            publishProgress("Tipos de Identificación");
            loader.syncCatalogos(11);
            publishProgress("Oficios");
            loader.syncCatalogos(12);
            publishProgress("Usuarios");
            loader.syncCatalogos(13);
//            publishProgress("Registro Civíl");
//            loader.syncCatalogos(8);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    Context context;
    public AsyncWorker(Context context, boolean byPass){
        ByPass = byPass;
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait...");
    }

    @Override
    protected void onPostExecute(String message){
        onTaskListener.TaskDone(message);
    }

    @Override
    protected void onProgressUpdate(String... catalogo) {
        onTaskListener.setProgressPercent(catalogo);
    }
}
