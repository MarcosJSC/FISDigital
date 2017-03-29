package cr.bamboo.fisdigital.data.ftp.input;

import android.content.Context;
import android.util.Base64;

import com.orm.SugarRecord;

import cr.bamboo.fisdigital.data.dbmodel.Barrio;
import cr.bamboo.fisdigital.data.dbmodel.Cantones;
import cr.bamboo.fisdigital.data.dbmodel.Caserio;
import cr.bamboo.fisdigital.data.dbmodel.Cedes;
import cr.bamboo.fisdigital.data.dbmodel.CentrosEducativos;
import cr.bamboo.fisdigital.data.dbmodel.CodEspecial;
import cr.bamboo.fisdigital.data.dbmodel.Distrito;
import cr.bamboo.fisdigital.data.dbmodel.Gerencias;
import cr.bamboo.fisdigital.data.dbmodel.Oficios;
import cr.bamboo.fisdigital.data.dbmodel.Provincias;
import cr.bamboo.fisdigital.data.dbmodel.RegMIDEPLAN;
import cr.bamboo.fisdigital.data.dbmodel.RegistroCivil;
import cr.bamboo.fisdigital.data.dbmodel.TiposIdentificacion;
import cr.bamboo.fisdigital.data.dbmodel.Usuario;
import cr.bamboo.fisdigital.data.security.CipherHelper;

/**
 * Created by dianavalerin on 2/3/15.
 * Maneja la conexion al FTP e inicia la descarga de los archivos
 */
public class FTPConnect {


    Context context;

    public FTPConnect(Context _context){
        this.context = _context;
    }
    public String getSingleLastAct() throws Exception {
        String val="";
        FTPDownloader ftpDownloader = new FTPDownloader(this.context);
        val = ftpDownloader.returnFileContent("/FISmovil/Salida/sipo_fecha_act_catalogos.txt", "fecha_act_catalogos");
        ftpDownloader.disconnect();
        return val;

    }
      public void syncCatalogos(int catalogo) {

        try {
            FTPDownloader ftpDownloader = new FTPDownloader(this.context);
            String root = "/FISmovil/Salida/";
            String extension = ".txt";

            switch(catalogo){
                case 0:
                    SugarRecord.deleteAll(Provincias.class);
                    ftpDownloader.downloadFile(root+"sipp01_provincia"+extension, "provincia");
                    break;
                case 1:
                    SugarRecord.deleteAll(Cantones.class);
                    ftpDownloader.downloadFile(root +"sipp02_canton"+extension, "canton");

                    break;
                case 2:
                    SugarRecord.deleteAll(Distrito.class);
                    ftpDownloader.downloadFile(root +"sipp03_distrito"+extension, "distrito");
                    break;
                case 3:
                    SugarRecord.deleteAll(Caserio.class);
                    ftpDownloader.downloadFile(root +"sipp05_caserios"+extension, "caserio");
                    break;
                case 4:
                    SugarRecord.deleteAll(Barrio.class);
                    ftpDownloader.downloadFile(root +"sipp04_barrio"+extension, "barrio");
                    break;
                case 5:
                    SugarRecord.deleteAll(Gerencias.class);
                    ftpDownloader.downloadFile(root +"sipp06_gerencia_regional"+extension, "gerencia");
                    break;
                case 6:
                    SugarRecord.deleteAll(Cedes.class);
                    ftpDownloader.downloadFile(root +"sipp07_cedes"+extension, "cede");
                    break;
                case 7:
                    SugarRecord.deleteAll(RegMIDEPLAN.class);
                    ftpDownloader.downloadFile(root +"sipp08_region_mideplan"+extension, "regMidePlan");
                    break;
                case 8:
                    SugarRecord.deleteAll(RegistroCivil.class);
                    ftpDownloader.downloadFile(root +"sipm51_registro_civil"+extension, "registroCivil");
                    break;
                case 9:
                    SugarRecord.deleteAll(CodEspecial.class);
                    ftpDownloader.downloadFile(root +"sipp22_codesp_familias"+extension, "codigosEspeciales");
                    break;
                case 10:
                    SugarRecord.deleteAll(CentrosEducativos.class);
                    ftpDownloader.downloadFile(root +"sipp21_centros_educacion"+extension, "centrosEducacion");
                    break;
                case 11:
                    SugarRecord.deleteAll(TiposIdentificacion.class);
                    ftpDownloader.downloadFile(root +"sipp14_tipos_identif"+extension, "tiposIdentificacion");
                    break;
                case 12:
                    SugarRecord.deleteAll(Oficios.class);
                    ftpDownloader.downloadFile(root +"sipp13_oficios"+extension, "oficios");
                    break;
                case 13:
                    SugarRecord.deleteAll(Usuario.class);
                    ftpDownloader.downloadFile(root +"sipp51_v2_responsables"+extension, "usuarios");
                    break;
            }

            ftpDownloader.disconnect();


            System.out.println("FTP File downloaded successfully");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}