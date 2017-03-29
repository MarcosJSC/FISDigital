package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;
/**
 * Created by jbeita on 5/2/15.
 */
public class Fdm005_FamiliaEspecial extends SugarRecord<Fdm005_FamiliaEspecial>  {
    public int Fdm0067_IdFamiliaEspecial ;
    public String Fdm005_IdDispositivoMovil;
    public long Fdm002_IdFamilia ;
    public String Fdm007_CodigoEspecial;
    public String Fdm007_Usuario;
    public String Fdm007_Fecha;
    public int Fdm007_EstadoSincronizacion;

    public int getFdm0067_IdFamiliaEspecial() {
        return Fdm0067_IdFamiliaEspecial;
    }

    public void setFdm0067_IdFamiliaEspecial(int fdm0067_IdFamiliaEspecial) {
        Fdm0067_IdFamiliaEspecial = fdm0067_IdFamiliaEspecial;
    }

    public String getFdm005_IdDispositivoMovil() {
        return Fdm005_IdDispositivoMovil;
    }

    public void setFdm005_IdDispositivoMovil(String fdm005_IdDispositivoMovil) {
        Fdm005_IdDispositivoMovil = fdm005_IdDispositivoMovil;
    }

    public long getFdm002_IdFamilia() {
        return Fdm002_IdFamilia;
    }

    public void setFdm002_IdFamilia(long fdm002_IdFamilia) {
        Fdm002_IdFamilia = fdm002_IdFamilia;
    }

    public String getFdm007_CodigoEspecial() {
        return Fdm007_CodigoEspecial;
    }

    public void setFdm007_CodigoEspecial(String fdm007_CodigoEspecial) {
        Fdm007_CodigoEspecial = fdm007_CodigoEspecial;
    }

    public String getFdm007_Usuario() {
        return Fdm007_Usuario;
    }

    public void setFdm007_Usuario(String Fdm007_Usuario) {
        this.Fdm007_Usuario= Fdm007_Usuario;
    }

    public String getFdm007_Fecha() {
        return Fdm007_Fecha;
    }

    public void setFdm007_Fecha(String fdm007_Fecha) {
        Fdm007_Fecha = fdm007_Fecha;
    }

    public int getFdm007_EstadoSincronizacion() {
        return Fdm007_EstadoSincronizacion;
    }

    public void setFdm007_EstadoSincronizacion(int fdm007_EstadoSincronizacion) {
        Fdm007_EstadoSincronizacion = fdm007_EstadoSincronizacion;
    }
}
