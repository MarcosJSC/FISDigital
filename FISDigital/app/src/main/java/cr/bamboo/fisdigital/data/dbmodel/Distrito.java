package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Distrito extends SugarRecord<Distrito> {
    private String sipp01CodigoProvincia;
    private String sipp02CodigoCanton;
    private String sipp03CodigoDistrito;
    private String sipp06CodigoGerencia;
    private String sipp07CodigoCedes;
    private String sipp03DescripcionDistrito;

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

    public String getSipp03_codigo_distrito(){
        return sipp03CodigoDistrito;
    }
    public void setSipp03_codigo_distrito(String sipp03_codigo_distrito) {
        this.sipp03CodigoDistrito = sipp03_codigo_distrito;
    }

    public String getSipp06_codigo_gerencia(){
        return sipp06CodigoGerencia;
    }
    public void setSipp06_codigo_gerencia(String sipp06_codigo_gerencia) {
        this.sipp06CodigoGerencia = sipp06_codigo_gerencia;
    }

    public String getSipp07_codigo_cedes(){
        return sipp07CodigoCedes;
    }
    public void setSipp07_codigo_cedes(String sipp07_codigo_cedes) {
        this.sipp07CodigoCedes = sipp07_codigo_cedes;
    }

    public String getSipp03_descripcion_distrito(){
        return sipp03DescripcionDistrito;
    }
    public void setSipp03_descripcion_distrito(String sipp03_descripcion_distrito) {
        this.sipp03DescripcionDistrito = sipp03_descripcion_distrito;
    }

    public void saveOnBackground(){
        save();
    }

}
