package cr.bamboo.fisdigital.data.dbmodel;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Gerencias extends SugarRecord<Gerencias>{
    private String sipp06_codigo_gerencia;
    private String sipp06_descripcion_gerencia;

    public String getSipp06_codigo_gerencia(){
        return sipp06_codigo_gerencia;
    }
    public void setSipp06_codigo_gerencia(String sipp06_codigo_gerencia) {
        this.sipp06_codigo_gerencia = sipp06_codigo_gerencia;
    }

    public String getSipp06_descripcion_gerencia(){
        return sipp06_descripcion_gerencia;
    }
    public void setSipp06_descripcion_gerencia(String sipp06_descripcion_gerencia) {
        this.sipp06_descripcion_gerencia = sipp06_descripcion_gerencia;
    }

    public static Gerencias getGerenciaById(String gerenciaId){
        //String contentIdStr = String.valueOf(provinciaId);
        List<Gerencias> array = Gerencias.find(Gerencias.class, "sipp06codigogerencia = ?", gerenciaId);
        return !array.isEmpty() ? array.get(0) : Gerencias.emptyGerencia();
    }

    public static Gerencias emptyGerencia(){
        Gerencias gerencia = new Gerencias();
        gerencia.sipp06_codigo_gerencia = "";
        return gerencia;
    }

    public boolean gerenciaExists(String gerenciaId){
        Gerencias gerencia = getGerenciaById(gerenciaId);
        return !gerencia.isAnEmptyObject();
    }

    public boolean isAnEmptyObject(){
        return sipp06_codigo_gerencia.equals("");
    }

    public void saveOnBackground(){
        save();
    }


}
