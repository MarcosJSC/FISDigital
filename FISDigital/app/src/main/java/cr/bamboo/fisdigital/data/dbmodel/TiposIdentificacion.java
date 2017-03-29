package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class TiposIdentificacion  extends SugarRecord<TiposIdentificacion> {

    private String sipp14_codigo_tipoid;
    private String sipp14_descrip_tipoid;
    private String sipp14_mascara_id;


    public String getSipp14_codigo_tipoid() {
        return sipp14_codigo_tipoid;
    }

    public void setSipp14_codigo_tipoid(String sipp14_codigo_tipoid) {
        this.sipp14_codigo_tipoid = sipp14_codigo_tipoid;
    }

    public String getSipp14_descrip_tipoid() {
        return sipp14_descrip_tipoid;
    }

    public void setSipp14_descrip_tipoid(String sipp14_descrip_tipoid) {
        this.sipp14_descrip_tipoid = sipp14_descrip_tipoid;
    }

    public String getSipp14_mascara_id() {
        return sipp14_mascara_id;
    }

    public void setSipp14_mascara_id(String sipp14_mascara_id) {
        this.sipp14_mascara_id = sipp14_mascara_id;
    }
}
