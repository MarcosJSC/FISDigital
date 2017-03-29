package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

/**
 * Created by dianavalerin on 3/10/15.
 */
public class Fdm007_Catalogo extends SugarRecord<Fdm007_Catalogo>  {

    private int Fdm007_IdCatalogo ;
    private String Fdm007_NombreCatalogo;
    private String Fdm007_Valor ;
    private String Fdm007_Descripcion;

    public int getFdm007_IdCatalogo() {
        return Fdm007_IdCatalogo;
    }

    public void setFdm007_IdCatalogo(int fdm007_IdCatalogo) {
        Fdm007_IdCatalogo = fdm007_IdCatalogo;
    }

    public String getFdm007_NombreCatalogo() {
        return Fdm007_NombreCatalogo;
    }

    public void setFdm007_NombreCatalogo(String fdm007_NombreCatalogo) {
        Fdm007_NombreCatalogo = fdm007_NombreCatalogo;
    }

    public String getFdm007_Valor() {
        return Fdm007_Valor;
    }

    public void setFdm007_Valor(String fdm007_Valor) {
        Fdm007_Valor = fdm007_Valor;
    }

    public String getFdm007_Descripcion() {
        return Fdm007_Descripcion;
    }

    public void setFdm007_Descripcion(String fdm007_Descripcion) {
        Fdm007_Descripcion = fdm007_Descripcion;
    }
}
