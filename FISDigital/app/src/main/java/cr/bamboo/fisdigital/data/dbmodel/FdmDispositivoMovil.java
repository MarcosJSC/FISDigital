package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 5/2/15.
 */
public class FdmDispositivoMovil extends SugarRecord<FdmDispositivoMovil> {
    public int Fdm005_IdDispositivoMovil ;
    public String Fdm006_Serie ;
    public int Fdm007_Consecutivo ;

    public int getFdm005_IdDispositivoMovil() {
        return Fdm005_IdDispositivoMovil;
    }

    public void setFdm005_IdDispositivoMovil(int fdm005_IdDispositivoMovil) {
        Fdm005_IdDispositivoMovil = fdm005_IdDispositivoMovil;
    }

    public String getFdm006_Serie() {
        return Fdm006_Serie;
    }

    public void setFdm006_Serie(String fdm006_Serie) {
        Fdm006_Serie = fdm006_Serie;
    }

    public int getFdm007_Consecutivo() {
        return Fdm007_Consecutivo;
    }

    public void setFdm007_Consecutivo(int fdm007_Consecutivo) {
        Fdm007_Consecutivo = fdm007_Consecutivo;
    }
}
