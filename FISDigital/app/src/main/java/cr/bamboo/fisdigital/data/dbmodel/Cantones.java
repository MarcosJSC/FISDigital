package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Cantones extends SugarRecord<Cantones> {
    private String sipp01CodigoProvincia;
    private String sipp02CodigoCanton;
    private String sipp02DescripcionCanton;
    private String sipp08CodigoRegmidep;

    public String getSipp01_codigo_provincia(){
        return sipp01CodigoProvincia;
    }
    public void setSipp01_codigo_provincia(String sipp01_codigo_provincia) {
        this.sipp01CodigoProvincia = sipp01_codigo_provincia;
    }

    public String getSipp02_codigo_canton(){
        return sipp02CodigoCanton;
    }
    public void setSipp02_codigo_canton(String sipp02_codigo_canton) {
        this.sipp02CodigoCanton = sipp02_codigo_canton;
    }

    public String getSipp02_descripcion_canton(){
        return sipp02DescripcionCanton;
    }
    public void setSipp02_descripcion_canton(String sipp02_descripcion_canton) {
        this.sipp02DescripcionCanton = sipp02_descripcion_canton;
    }

    public String getSipp08_codigo_regmidep(){
        return sipp08CodigoRegmidep;
    }
    public void setSipp08_codigo_regmidep(String sipp08_codigo_regmidep) {
        this.sipp08CodigoRegmidep = sipp08_codigo_regmidep;
    }

    public void saveOnBackground(){
      //  Log.d("salvo cantones","salvo");
        save();
    }
}
