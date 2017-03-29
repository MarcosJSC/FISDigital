package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class RegMIDEPLAN extends SugarRecord<RegMIDEPLAN> {
    private String sipp08CodigoRegmidep;
    private String sipp08DescripcionMidep;

    public String getSipp08_codigo_regmidep(){
        return sipp08CodigoRegmidep;
    }
    public void setSipp08_codigo_regmidep(String sipp08_codigo_regmidep) {
        this.sipp08CodigoRegmidep = sipp08_codigo_regmidep;
    }

    public String getSipp08_descripcion_midep(){
        return sipp08DescripcionMidep;
    }
    public void setSipp08_descripcion_midep(String sipp08_descripcion_midep) {
        this.sipp08DescripcionMidep = sipp08_descripcion_midep;
    }

    public void saveOnBackground(){
        save();
    }
}
