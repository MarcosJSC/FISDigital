package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Caserio extends SugarRecord<Caserio> {
    private int sipp01CodigoProvincia;
    private int sipp02CodigoCanton;
    private int sipp03CodigoDistrito;
    private String sipp04CodigoBarrio;
    private String sipp05CodigoCaserio;
    private String sipp05DescCaserio;

    public int getSipp01_codigo_provincia(){
        return sipp01CodigoProvincia;
    }
    public void setSipp01_codigo_provincia(int sipp01_codigo_provincia) {
        this.sipp01CodigoProvincia = sipp01_codigo_provincia;
    }

    public int getSipp02_codigo_canton(){
        return sipp02CodigoCanton;
    }
    public void setSipp02_codigo_canton(int sipp02_codigo_canton) {
        this.sipp02CodigoCanton = sipp02_codigo_canton;
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

    public String getSipp05_codigo_caserio(){
        return sipp05CodigoCaserio;
    }
    public void setSipp05_codigo_caserio(String sipp05_codigo_caserio) {
        this.sipp05CodigoCaserio = sipp05_codigo_caserio;
    }

    public String getSipp05_desc_caserio(){
        return sipp05DescCaserio;
    }
    public void setSipp05_desc_caserio(String sipp05_desc_caserio) {
        this.sipp05DescCaserio = sipp05_desc_caserio;
    }

    public void saveOnBackground(){
        save();
    }

    private void update(Caserio caserioCheckout) {
        sipp01CodigoProvincia = caserioCheckout.getSipp01_codigo_provincia();
        sipp02CodigoCanton = caserioCheckout.getSipp02_codigo_canton();
        sipp03CodigoDistrito = caserioCheckout.getSipp03_codigo_distrito();
        sipp04CodigoBarrio = caserioCheckout.getSipp04_codigo_barrio();
        sipp05CodigoCaserio = caserioCheckout.getSipp05_codigo_caserio();
        sipp05DescCaserio = caserioCheckout.getSipp05_desc_caserio();
        save();
    }
    public static Caserio getCaserioById(String provincia, String canton , String distrito,String barrioId,String caserioId){
       // String contentIdStr = String.valueOf(caserioId);
        List<Caserio> array = Caserio.find(Caserio.class, "sipp01_codigo_provincia =? AND sipp02_codigo_canton=? AND sipp03_codigo_distrito=? AND sipp04_codigo_barrio = ? AND sipp05_codigo_caserio = ?",provincia,canton,distrito,barrioId, caserioId);
        return !array.isEmpty() ? array.get(0) : Caserio.emptyCaserio();
    }

    public static Caserio emptyCaserio(){
        Caserio caserio = new Caserio();
        caserio.sipp05CodigoCaserio = "";
        return caserio;
    }

    public boolean caserioExists(String provincia, String canton ,String distrito,String barrioId,String caserioId){
        Caserio caserio = getCaserioById(provincia,canton,distrito,barrioId,caserioId);
        return !caserio.isAnEmptyObject();
    }

    public boolean isAnEmptyObject(){
        return sipp03CodigoDistrito == -1;
    }


}
