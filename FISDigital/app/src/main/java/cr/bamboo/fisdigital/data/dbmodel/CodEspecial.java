package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class CodEspecial extends SugarRecord<CodEspecial> {
    private String sipp22CodEspecial;
    private String sipp22DesEspecial;
    private int sipp22Estado;


    public String getCodigo() {
        return sipp22CodEspecial;
    }

    public void setSipp22_cod_especial(String sipp22_cod_especial) {
        this.sipp22CodEspecial = sipp22_cod_especial;
    }

    public String getDescripcion() {
        return sipp22DesEspecial;
    }

    public void setSipp22_des_especial(String sipp22_des_especial) {
        this.sipp22DesEspecial = sipp22_des_especial;
    }

    public int getSipp22_estado() {
        return sipp22Estado;
    }

    public void setSipp22_estado(int sipp22_estado) {
        this.sipp22Estado = sipp22_estado;
    }

    public void saveOnBackground(){
        save();
    }
}
