package cr.bamboo.fisdigital.data.ftp.input;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by walter on 09/06/15.
 *
 * Clase que permite el manejo del estado y última
 * fecha de sincronizacion de los catálogos
 */
public class EstadoCatalogos {
    static Context context;
    static String lastUpdateDateKey = "lastupdatedate";
    static String LastUpdateDate = "";

    public static boolean IsLastVersion(Context _context) throws Exception {
        boolean isLastVersion = false;
        context=_context;

        try {
            LastUpdateDate = LoadPreferences(lastUpdateDateKey);
        }catch(Exception e) {

        }
        String textFromFTP = RetrieveFromFTP();
        if(textFromFTP.equals("") || LastUpdateDate.equals("")){
            return false;
        }

        if(textFromFTP.equals(LastUpdateDate)){
            return true;
        }

        return isLastVersion;
    }
    static void SavePreferences(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    static String LoadPreferences(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = preferences.getString(key, "");
        return name;
    }
    static String RetrieveFromFTP() throws Exception {
        FTPConnect loader = new FTPConnect(context);
        try {
            String val= "";
            val = loader.getSingleLastAct();
            SavePreferences(lastUpdateDateKey, val);
            return val;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
