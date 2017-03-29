package cr.bamboo.fisdigital.data.dbmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JBeita on 31/03/2015.
 */
public class Opcion {

    private String codigo;
    private String descripcion;

    public Opcion(String codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }


    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public static ArrayList<Opcion> transformProvincias(List<Provincias> provincias) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<provincias.size(); i++){
            Provincias provincia = (Provincias)provincias.get(i);
            Opcion opcion = new Opcion(provincia.getCodigo(), provincia.getDescripcion());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformCantones(List<Cantones> cantones) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<cantones.size(); i++){
            Cantones canton= (Cantones)cantones.get(i);
            Opcion opcion = new Opcion(canton.getSipp02_codigo_canton(), canton.getSipp02_descripcion_canton());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformDistritos(List<Distrito> distritos) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<distritos.size(); i++){
            Distrito distrito = (Distrito)distritos.get(i);
            Opcion opcion = new Opcion(distrito.getSipp03_codigo_distrito(), distrito.getSipp03_descripcion_distrito());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformBarrios(List<Barrio> barrios) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<barrios.size(); i++){
            Barrio barrio = (Barrio)barrios.get(i);
            Opcion opcion = new Opcion(barrio.getSipp04_codigo_barrio(), barrio.getSipp04_descripcion_barrio());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformCaserios(List<Caserio> caserios) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<caserios.size(); i++){
            Caserio caserio= (Caserio)caserios.get(i);
            Opcion opcion = new Opcion(caserio.getSipp05_codigo_caserio(), caserio.getSipp05_desc_caserio());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformOficios(List<Oficios> oficios) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<oficios.size(); i++){
            Oficios oficio= (Oficios)oficios.get(i);
            Opcion opcion = new Opcion(oficio.getSipp13_codigo_oficio(), oficio.getSipp13_descrip_oficio());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformTipoIdentificacion(List<TiposIdentificacion> tiposIdentificacion) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<tiposIdentificacion.size(); i++){
            TiposIdentificacion tipoIdentificacion= (TiposIdentificacion)tiposIdentificacion.get(i);
            Opcion opcion = new Opcion(tipoIdentificacion.getSipp14_codigo_tipoid(), tipoIdentificacion.getSipp14_descrip_tipoid());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformCentrosEnsenanza(List<CentrosEducativos> centrosEducativos) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<centrosEducativos.size(); i++){
            CentrosEducativos centroEducativo= (CentrosEducativos)centrosEducativos.get(i);
            Opcion opcion = new Opcion(centroEducativo.getSipp21_cod_ceneduc(), centroEducativo.getSipp21_des_ceneduc());
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformStringArray(String[] lista) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<lista.length; i++){
            Opcion opcion = new Opcion(String.valueOf(i+1), lista[i]);
            result.add(opcion);
        }
        return result;
    }

    public static ArrayList<Opcion> transformCodigosEspeciales(List<CodEspecial> codigosEspeciales) {
        ArrayList<Opcion> result = new ArrayList<Opcion>();
        for(int i=0; i<codigosEspeciales.size(); i++){
            CodEspecial codigoEspecial= (CodEspecial)codigosEspeciales.get(i);
            Opcion opcion = new Opcion(codigoEspecial.getCodigo(), codigoEspecial.getDescripcion());
            result.add(opcion);
        }
        return result;
    }
}
