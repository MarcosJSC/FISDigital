package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;
/**
 * Created by dianavalerin on 5/2/15.
 */
public class Fdm_Observaciones extends SugarRecord<Fdm_Observaciones>  {
    public long fdm001IdFichaInformacionSocial;
    public String Observaciones;

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public long getFdm001IdFichaInformacionSocial(){
        return fdm001IdFichaInformacionSocial;
    }
    public void setFdm001IdFichaInformacionSocial(long fdm001_IdFichaInformacionSocial) {
        this.fdm001IdFichaInformacionSocial = fdm001_IdFichaInformacionSocial;
    }

}
