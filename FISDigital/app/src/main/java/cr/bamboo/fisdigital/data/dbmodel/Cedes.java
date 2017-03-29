package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Cedes extends SugarRecord<Cedes> {
    private String sipp06_codigo_gerencia;
    private String sipp07_codigo_cedes;
    private String sipp07_descripcion_cedes;

    public String getSipp06_codigo_gerencia(){
        return sipp06_codigo_gerencia;
    }
    public void setSipp06_codigo_gerencia(String sipp06_codigo_gerencia) {
        this.sipp06_codigo_gerencia = sipp06_codigo_gerencia;
    }

    public String getSipp07_codigo_cedes(){
        return sipp07_codigo_cedes;
    }
    public void setSipp07_codigo_cedes(String sipp07_codigo_cedes) {
        this.sipp07_codigo_cedes = sipp07_codigo_cedes;
    }

    public String getSipp07_descripcion_cedes(){
        return sipp07_descripcion_cedes;
    }
    public void setSipp07_descripcion_cedes(String sipp07_descripcion_cedes) {
        this.sipp07_descripcion_cedes = sipp07_descripcion_cedes;
    }

    public void saveOnBackground(){
        save();
    }

    private void update(Cedes cedesCheckout) {
        sipp06_codigo_gerencia = cedesCheckout.getSipp06_codigo_gerencia();
        sipp07_codigo_cedes = cedesCheckout.getSipp07_codigo_cedes();
        sipp07_descripcion_cedes = cedesCheckout.getSipp07_descripcion_cedes();
        save();
    }
}
