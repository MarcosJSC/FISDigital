package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 5/2/15.
 */
public class Fdm006_CorreoElectFamilia extends SugarRecord<Fdm006_CorreoElectFamilia>  {
    public int Fdm006_IdCorreoElectFamilia;
    public String Fdm006_IdDispositivoMovil;
    public long Fdm006_IdFamilia ;
    public String Fdm006_Correo ;
    public int Fdm006_EstadoSincronizacion;


    public int getFdm006_IdCorreoElectFamilia() {
        return Fdm006_IdCorreoElectFamilia;
    }

    public void setFdm006_IdCorreoElectFamilia(int fdm006_IdCorreoElectFamilia) {
        Fdm006_IdCorreoElectFamilia = fdm006_IdCorreoElectFamilia;
    }

    public String getFdm006_IdDispositivoMovil() {
        return Fdm006_IdDispositivoMovil;
    }

    public void setFdm006_IdDispositivoMovil(String fdm006_IdDispositivoMovil) {
        Fdm006_IdDispositivoMovil = fdm006_IdDispositivoMovil;
    }

    public long getFdm006_IdFamilia() {
        return Fdm006_IdFamilia;
    }

    public void setFdm006_IdFamilia(long fdm006_IdFamilia) {
        Fdm006_IdFamilia = fdm006_IdFamilia;
    }

    public String getFdm006_Correo() {
        return Fdm006_Correo;
    }

    public void setFdm006_Correo(String fdm006_Correo) {
        Fdm006_Correo = fdm006_Correo;
    }

    public int getFdm006_EstadoSincronizacion() {
        return Fdm006_EstadoSincronizacion;
    }

    public void setFdm006_EstadoSincronizacion(int fdm006_EstadoSincronizacion) {
        Fdm006_EstadoSincronizacion = fdm006_EstadoSincronizacion;
    }
}
