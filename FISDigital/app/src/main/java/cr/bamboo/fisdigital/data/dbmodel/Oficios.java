package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Oficios extends SugarRecord<Oficios> {
    private String sipp13_codigo_oficio;
    private String sipp13_descrip_oficio;

    public String getSipp13_codigo_oficio(){
        return sipp13_codigo_oficio;
    }
    public void setSipp13_codigo_oficio(String sipp13_codigo_oficio) {
        this.sipp13_codigo_oficio = sipp13_codigo_oficio;
    }

    public String getSipp13_descrip_oficio(){
        return sipp13_descrip_oficio;
    }
    public void setSipp13_descrip_oficio(String sipp13_descrip_oficio) {
        this.sipp13_descrip_oficio = sipp13_descrip_oficio;
    }
}
