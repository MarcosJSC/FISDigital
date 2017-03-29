package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Barrio extends SugarRecord<Barrio> {
    private int sipp01CodigoProvincia;
    private int sipp02CodigoCanton;
    private int sipp03CodigoDistrito;
    private String sipp04CodigoBarrio;
    private String sipp04DescripcionBarrio;

    public int getSipp01_codigo_provincia(){
        return sipp01CodigoProvincia;
    }
    public void setSipp01_codigo_provincia(int sipp01_codigo_provincia) {
        this.sipp01CodigoProvincia = sipp01_codigo_provincia;
    }

    public int getSipp02_codigo_canton(){
        return sipp02CodigoCanton;
    }
    public void setSipp02_codigo_canton(int sipp02CodigoCanton) {
        this.sipp02CodigoCanton = sipp02CodigoCanton;
    }

    public int getSipp03_codigo_distrito(){
        return sipp03CodigoDistrito;
    }
    public void setSipp03_codigo_distrito(int sipp03_codigo_distrito) {
        this.sipp03CodigoDistrito = sipp03_codigo_distrito;
    }

    public String getSipp04_codigo_barrio(){
        return sipp04CodigoBarrio;
    }
    public void setSipp04_codigo_barrio(String sipp04_codigo_barrio) {
        this.sipp04CodigoBarrio = sipp04_codigo_barrio;
    }

    public String getSipp04_descripcion_barrio(){
        return sipp04DescripcionBarrio;
    }
    public void setSipp04_descripcion_barrio(String sipp04_descripcion_barrio) {
        this.sipp04DescripcionBarrio = sipp04_descripcion_barrio;
    }

    public void saveOnBackground(){
        save();
    }

    private void update(Barrio barrioCheckout) {
        sipp01CodigoProvincia = barrioCheckout.getSipp01_codigo_provincia();
        sipp02CodigoCanton = barrioCheckout.getSipp02_codigo_canton();
        sipp03CodigoDistrito = barrioCheckout.getSipp03_codigo_distrito();
        sipp04CodigoBarrio = barrioCheckout.getSipp04_codigo_barrio();
        sipp04DescripcionBarrio = barrioCheckout.getSipp04_descripcion_barrio();
        save();
    }

    public static Barrio getBarrioById(String provincia, String canton , String distrito,String barrioId){
       // String contentIdStr = String.valueOf(cantonId);
        List<Barrio> array = Barrio.find(Barrio.class, "sipp01_codigo_provincia=? AND sipp02_codigo_canton=? AND sipp03_codigo_distrito=? AND sipp04_codigo_barrio = ?",provincia,canton,distrito, barrioId);
        return !array.isEmpty() ? array.get(0) : Barrio.emptyBarrio();
    }

    public static Barrio emptyBarrio(){
        Barrio barrio = new Barrio();
        barrio.sipp04CodigoBarrio = "";
        return barrio;
    }

    public boolean barrioExists(String provincia, String canton , String distrito,String barrioId){
        Barrio barrio = getBarrioById(provincia,canton,distrito,barrioId);
        return !barrio.isAnEmptyObject();
    }

    public boolean isAnEmptyObject(){
        return sipp02CodigoCanton == -1;
    }



}
