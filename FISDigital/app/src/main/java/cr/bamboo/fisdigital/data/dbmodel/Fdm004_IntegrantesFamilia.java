package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 5/2/15.
 */
public class Fdm004_IntegrantesFamilia extends SugarRecord<Fdm004_IntegrantesFamilia> {

    public int Fdm004_IdIntegrantesFamilia ;
    public String Fdm005_IdDispositivoMovil;
    public long Fdm003_IdPoblacionObjetivo;
    public long Fdm002_IdFamilia;
    public int Fdm004_NumeroOrden ;
    public String Fdm004_JefeFamilia ;
    public String Fdm004_ParentezcoJefe;
    public String Fdm004_RelacionEntreJefes ;
    public int Fdm004_NumeroHogar;
    public String Fdm004_Usuario;
    public String Fdm004_FechaHora;
    public int Fdm004_EstadoSincronizacion;

    public int getFdm004IdIntegrantesFamilia() {
        return Fdm004_IdIntegrantesFamilia;
    }

    public void setFdm004IdIntegrantesFamilia(int fdm004_IdIntegrantesFamilia) {
        Fdm004_IdIntegrantesFamilia = fdm004_IdIntegrantesFamilia;
    }

    public String getFdm005IdDispositivoMovil() {
        return Fdm005_IdDispositivoMovil;
    }

    public void setFdm005IdDispositivoMovil(String fdm005_IdDispositivoMovil) {
        Fdm005_IdDispositivoMovil = fdm005_IdDispositivoMovil;
    }

    public long getFdm002IdFamilia() {
        return Fdm002_IdFamilia;
    }

    public void setFdm002IdFamilia(long fdm002_IdFamilia) {
        Fdm002_IdFamilia = fdm002_IdFamilia;
    }

    public long getFdm003IdPoblacionObjetivo() {
        return Fdm003_IdPoblacionObjetivo;
    }

    public void setFdm003IdPoblacionObjetivo(long fdm003_IdPoblacionObjetivo) {
        Fdm003_IdPoblacionObjetivo = fdm003_IdPoblacionObjetivo;
    }

    public int getFdm004NumeroOrden() {
        return Fdm004_NumeroOrden;
    }

    public void setFdm004NumeroOrden(int fdm004_NumeroOrden) {
        Fdm004_NumeroOrden = fdm004_NumeroOrden;
    }

    public String getFdm004JefeFamilia() {
        return Fdm004_JefeFamilia;
    }

    public void setFdm004JefeFamilia(String fdm004_JefeFamilia) {
        Fdm004_JefeFamilia = fdm004_JefeFamilia;
    }

    public String getFdm004ParentezcoJefe() {
        return Fdm004_ParentezcoJefe;
    }

    public void setFdm004ParentezcoJefe(String fdm004_ParentezcoJefe) {
        Fdm004_ParentezcoJefe = fdm004_ParentezcoJefe;
    }

    public String getFdm004RelacionEntreJefes() {
        return Fdm004_RelacionEntreJefes;
    }

    public void setFdm004RelacionEntreJefes(String fdm004_RelacionEntreJefes) {
        Fdm004_RelacionEntreJefes = fdm004_RelacionEntreJefes;
    }

    public int getFdm004NumeroHogar() {
        return Fdm004_NumeroHogar;
    }

    public void setFdm004NumeroHogar(int fdm004_NumeroHogar) {
        Fdm004_NumeroHogar = fdm004_NumeroHogar;
    }

    public String getFdm004Usuario() {
        return Fdm004_Usuario;
    }

    public void setFdm004Usuario(String fdm004_Usuario) {
        Fdm004_Usuario = fdm004_Usuario;
    }

    public String getFdm004FechaHora() {
        return Fdm004_FechaHora;
    }

    public void setFdm004FechaHora(String fdm004_FechaHora) {
        Fdm004_FechaHora = fdm004_FechaHora;
    }

    public int getFdm004EstadoSincronizacion() {
        return Fdm004_EstadoSincronizacion;
    }

    public void setFdm004EstadoSincronizacion(int fdm004_EstadoSincronizacion) {
        Fdm004_EstadoSincronizacion = fdm004_EstadoSincronizacion;
    }
}
