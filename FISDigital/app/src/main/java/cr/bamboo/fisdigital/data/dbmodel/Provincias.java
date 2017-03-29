package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 1/21/15.
 */
public class Provincias extends SugarRecord<Provincias> {
   // @Column(name = "sipp01CodigoProvincia", unique = true, notNull = true);
    private String sipp01CodigoProvincia;
    private String sipp01DescripcionProvincia;

    public String getCodigo(){
        return sipp01CodigoProvincia;
    }
    public void setCodigo(String sipp01CodigoProvincia) {
        this.sipp01CodigoProvincia = sipp01CodigoProvincia;
    }

    public String getDescripcion(){
        return sipp01DescripcionProvincia;
    }
    public void setDescripcion(String sipp01DescripcionProvincia) {
        this.sipp01DescripcionProvincia = sipp01DescripcionProvincia;
    }

    public void saveOnBackground(){
        save();
    }

    /*
    public Provincias getProvinciaByContentIdFromMemoryCache(int contentId){
        Provincias find = Provincias.emptyProvincia();
        for(Provincias provincia : provincia){
            if(provincia.getSipp01_codigo_provincia() == contentId){
                find = provincia;
                break;
            }
        }
        return find;
    }
*/
}

