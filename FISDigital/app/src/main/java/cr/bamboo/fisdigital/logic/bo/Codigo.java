package cr.bamboo.fisdigital.logic.bo;

/**
 * Created by Lobo on 08/10/2015.
 */
public class Codigo {

    private long id;
    private String codigo;
    private String descripcion;
    private long idFamilia;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(long idFamilia) {
        this.idFamilia = idFamilia;
    }
}
