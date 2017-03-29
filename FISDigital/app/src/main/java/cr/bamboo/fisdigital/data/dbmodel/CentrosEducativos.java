package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class CentrosEducativos extends SugarRecord<CentrosEducativos> {
    private String sipp21CodCeneduc;
    private String sipp21DesCeneduc;
    private int sipp21Estado;//validar que sea solamente 1 -  0
    private int sipp01CodigoProvincia;
    private int sipp02CodigoCanton;
    private int sipp03CodigoDistrito;

    public String getSipp21_cod_ceneduc() {
        return sipp21CodCeneduc;
    }

    public void setSipp21_cod_ceneduc(String sipp21_cod_ceneduc) {
        this.sipp21CodCeneduc = sipp21_cod_ceneduc;
    }

    public String getSipp21_des_ceneduc() {
        return sipp21DesCeneduc;
    }

    public void setSipp21_des_ceneduc(String sipp21_des_ceneduc) {
        this.sipp21DesCeneduc = sipp21_des_ceneduc;
    }

    public int getSipp21_estado() {
        return sipp21Estado;
    }

    public void setSipp21_estado(int sipp21_estado) {
        this.sipp21Estado = sipp21_estado;
    }

    public int getSipp01_codigo_provincia() {
        return sipp01CodigoProvincia;
    }

    public void setSipp01_codigo_provincia(int sipp01_codigo_provincia) {
        this.sipp01CodigoProvincia = sipp01_codigo_provincia;
    }

    public int getSipp02_codigo_canton() {
        return sipp02CodigoCanton;
    }

    public void setSipp02_codigo_canton(int sipp02_codigo_canton) {
        this.sipp02CodigoCanton = sipp02_codigo_canton;
    }

    public int getSipp03_codigo_distrito() {
        return sipp03CodigoDistrito;
    }

    public void setSipp03_codigo_distrito(int sipp03_codigo_distrito) {
        this.sipp03CodigoDistrito = sipp03_codigo_distrito;
    }

    public void saveOnBackground(){
        save();
    }
}
