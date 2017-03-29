package cr.bamboo.fisdigital.data.ftp.output;

import android.app.ProgressDialog;
 import android.content.Context;
import android.os.AsyncTask;

import com.orm.StringUtil;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
import java.io.OutputStreamWriter;
 import java.net.InetAddress;
 import java.net.SocketException;
 import java.net.UnknownHostException;
import java.util.List;

import cr.bamboo.fisdigital.data.ftp.common.FTPInfo;
import cr.bamboo.fisdigital.data.ftp.common.OnTaskListener;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.FdmDispositivoMovil;

/**
 * Created by walter on 04/05/15.
 * Se encarga de la conexion al FTP y subida de archivos
 * por medio de un hilo aparte
 */
public class MyTaskSyncFIS extends AsyncTask<Integer, String, String> {
    private static final String SERVER = FTPInfo.host;
    private static final String USERNAME = FTPInfo.user;
    private static final java.lang.String PASSWORD = FTPInfo.pwd;
    private static final String PATH = "/FISmovil/Entrada";
    private ProgressDialog dialog;
    private OnTaskListener onTaskListener;

    public void setOnTaskListener(OnTaskListener onTaskListener) {
        this.onTaskListener= onTaskListener;
    }

    @Override
    protected void onPostExecute(String message){
        onTaskListener.TaskDone(message);
    }

    @Override
    protected void onProgressUpdate(String... catalogo) {
        onTaskListener.setProgressPercent(catalogo);
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            DataExporter de = new DataExporter();

            if(!de.IsDataToSync())
                return "no data to sync";

            // como aun no hay datos guardados en una base de datos, entonces se crea el nombre de manera temporal
            String idDispositivo = FISController.getInstance().getIdDispositivo();
            List<FdmDispositivoMovil> listaDisp = FdmDispositivoMovil.find(FdmDispositivoMovil.class, StringUtil.toSQLName("Fdm006_Serie")+"=?", idDispositivo);
            FdmDispositivoMovil fdmDispositivoMovil;
            if(listaDisp.size() == 0){
                fdmDispositivoMovil = new FdmDispositivoMovil();
                fdmDispositivoMovil.setFdm006_Serie(idDispositivo);
                fdmDispositivoMovil.setFdm007_Consecutivo(1);
                fdmDispositivoMovil.save();
            }
            else{
                fdmDispositivoMovil = listaDisp.get(0);
                fdmDispositivoMovil.setFdm007_Consecutivo(fdmDispositivoMovil.getFdm007_Consecutivo()+1);
                fdmDispositivoMovil.save();
            }

            String file1 = "Fdm001_FichaInformacionSocial_" + idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo(),
                    file2="Fdm002_Familias_"+idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo(),
                    file3="Fdm003_PoblacionObjetivo_"+idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo(),
                    file4="Fdm004_IntegranteFamilia_" + idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo(),
                    file5="Fdm005_FamiliaEspecial_"+idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo(),
                    file6="Fdm006_CorreoElectFamilia_"+idDispositivo + "_" + fdmDispositivoMovil.getFdm007_Consecutivo();

            FileOutputStream fOut = null, fOut2=null, fOut3=null, fOut4=null, fOut5=null, fOut6=null;
            //Since you are creating a subdirectory, you need to make sure it's there first
            File directory = new File(this.context.getCacheDir(), "/output");

            if (!directory.exists()) directory.mkdirs();

            try {

                //Create the stream pointing at the file location
                fOut = new FileOutputStream(new File(directory, file1 + ".txt"));
                fOut2 = new FileOutputStream(new File(directory, file2 + ".txt"));
                fOut3 = new FileOutputStream(new File(directory, file3 + ".txt"));
                fOut4 = new FileOutputStream(new File(directory, file4 + ".txt"));
                fOut5 = new FileOutputStream(new File(directory, file5 + ".txt"));
                fOut6 = new FileOutputStream(new File(directory, file6 + ".txt"));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fOut, "8859_1");
            OutputStreamWriter osw2 = new OutputStreamWriter(fOut2, "8859_1");
            OutputStreamWriter osw3 = new OutputStreamWriter(fOut3, "8859_1");
            OutputStreamWriter osw4 = new OutputStreamWriter(fOut4, "8859_1");
            OutputStreamWriter osw5 = new OutputStreamWriter(fOut5, "8859_1");
            OutputStreamWriter osw6 = new OutputStreamWriter(fOut6, "8859_1");

            osw.write(de.Fdm001);
            osw2.write(de.Fdm002);
            osw3.write(de.Fdm003);
            osw4.write(de.Fdm004);
            osw5.write(de.Fdm005);
            osw6.write(de.Fdm006);

            osw.flush();
            osw2.flush();
            osw3.flush();
            osw4.flush();
            osw5.flush();
            osw6.flush();

            osw.close();
            osw2.close();
            osw3.close();
            osw4.close();
            osw5.close();
            osw6.close();
            osw6.close();

            FTPClient ftpClient = new FTPClient();

            try {
                ftpClient.connect(InetAddress.getByName(SERVER));
                ftpClient.login(USERNAME, PASSWORD);
                ftpClient.changeWorkingDirectory(PATH);

                if (ftpClient.getReplyString().contains("250")) {
                    ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    String[] files = ftpClient.listNames(PATH);

                    String[] fileNames = new String[]{file1, file2, file3, file4, file5, file6};

                    for(int i=0; i< fileNames.length; i++) {
                        BufferedInputStream buffIn = null;
                        buffIn = new BufferedInputStream(new FileInputStream(this.context.getCacheDir() + "/output/" + fileNames[i] + ".txt"));
                        ftpClient.storeFile(fileNames[i] + ".txt", buffIn);
                        buffIn.close();
                    }

                    ftpClient.logout();
                    ftpClient.disconnect();
//                    de.clearDataBase();
                }

            } catch (SocketException e) {
                e.printStackTrace();
                //Log.e(SorensonApplication.TAG, e.getStackTrace().toString());
            } catch (UnknownHostException e) {
                e.printStackTrace();
                //Log.e(SorensonApplication.TAG, e.getStackTrace().toString());
            } catch (IOException e) {
                e.printStackTrace();
                Exception ex = e;
                //Log.e(SorensonApplication.TAG, e.getStackTrace().toString());
            }

            /* EXECUTE ALL PROCESSES HERE */
            //  (new FTPConnect(context,"sipp02_canton","canton")).execute();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return "done";
    }
    int nextId(String[] files, String text){
        int next = 1;

        for(int i = 0; i< files.length; i++){
            String file = files[i];

            if(file.contains(text)){

                String[] splitted = file.split("_");

                if(tryParseInt( splitted[splitted.length-1].replace(".txt", ""))){
                    int currentId= Integer.parseInt(splitted[splitted.length-1].replace(".txt", ""));
                    if(currentId >= next)
                        next= currentId + 1;
                }
            }
        }

        return next;
    }
    boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }
    Context context;
    public MyTaskSyncFIS(Context context){
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait...");
    }

}
