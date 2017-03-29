package cr.bamboo.fisdigital.logic.bo;

import com.orm.StringUtil;

import java.util.List;

import cr.bamboo.fisdigital.data.dbmodel.Barrio;
import cr.bamboo.fisdigital.data.dbmodel.Cantones;
import cr.bamboo.fisdigital.data.dbmodel.Caserio;
import cr.bamboo.fisdigital.data.dbmodel.Distrito;
import cr.bamboo.fisdigital.data.dbmodel.Gerencias;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;
import cr.bamboo.fisdigital.data.dbmodel.Provincias;
import cr.bamboo.fisdigital.data.dbmodel.RegMIDEPLAN;

/**
 * Created by JBeita on 22/03/2015.
 */
public class Ubicacion {

    private int numeroFolio;
    private String idProvincia;
    private String provincia;
    private String idCanton;
    private String canton;
    private String idDistrito;
    private String distrito;
    private String idBarrio;
    private String barrio;
    private String idCaserio;
    private String caserio;
    private String idRegion;
    private String region;
    private String idGerenciaRegional;
    private String gerenciaRegional;
    private String idZona;
    private String zona;
    private String direccion;
    private String numeroVivienda;
    private String idViviendaRiesgo;
    private String viviendaRiesgo;
    private String observaciones;

    public Ubicacion() {
        numeroFolio = 0;
        idProvincia = "";
        idCanton = "";
        idDistrito = "";
        idBarrio = "";
        idCaserio = "";
        direccion = "";
        numeroVivienda = "";
        idRegion = "";
        idGerenciaRegional = "";
        idZona = "";
        idViviendaRiesgo = "";
    }


    public int getNumeroFolio() {
        return numeroFolio;
    }
    public void setNumeroFolio(int numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setProvincia(Opcion opcion) {
        this.provincia = opcion.getDescripcion();
        this.idProvincia = opcion.getCodigo();
    }

    public String getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(String idCanton) {
        this.idCanton = idCanton;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public void setCanton(Opcion opcion) {
        this.canton = opcion.getDescripcion();
        this.idCanton = opcion.getCodigo();
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setDistrito(Opcion opcion) {
        this.distrito = opcion.getDescripcion();
        this.idDistrito = opcion.getCodigo();
    }

    public String getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(String idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setBarrio(Opcion opcion) {
        this.barrio = opcion.getDescripcion();
        this.idBarrio = opcion.getCodigo();
    }

    public String getIdCaserio() {
        return idCaserio;
    }

    public void setIdCaserio(String idCaserio) {
        this.idCaserio = idCaserio;
    }

    public String getCaserio() {
        return caserio;
    }

    public void setCaserio(String caserio) {
        this.caserio = caserio;
    }

    public void setCaserio(Opcion opcion) {
        this.caserio = opcion.getDescripcion();
        this.idCaserio = opcion.getCodigo();
    }

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegion(Opcion opcion) {
        this.region = opcion.getDescripcion();
        this.idRegion = opcion.getCodigo();
    }


    public String getIdGerenciaRegional() {
        return idGerenciaRegional;
    }

    public void setIdGerenciaRegional(String idGerenciaRegional) {
        this.idGerenciaRegional = idGerenciaRegional;
    }

    public String getGerenciaRegional() {
        return gerenciaRegional;
    }

    public void setGerenciaRegional(String gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public void setGerenciaRegional(Opcion gerencia) {
        this.gerenciaRegional = gerencia.getDescripcion();
        this.idGerenciaRegional = gerencia.getCodigo();
    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setZona(Opcion opcion) {
        this.zona = opcion.getDescripcion();
        this.idZona = opcion.getCodigo();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroVivienda() {
        return numeroVivienda;
    }

    public void setNumeroVivienda(String numeroVivienda) {
        this.numeroVivienda = numeroVivienda;
    }

    public String getIdViviendaRiesgo() {
        return idViviendaRiesgo;
    }

    public void setIdViviendaRiesgo(String idViviendaRiesgo) {
        this.idViviendaRiesgo = idViviendaRiesgo;
    }

    public String getViviendaRiesgo() {
        return viviendaRiesgo;
    }

    public void setViviendaRiesgo(String viviendaRiesgo) {
        this.viviendaRiesgo = viviendaRiesgo;
    }

    public void setViviendaRiesgo(Opcion opcion) {
        this.viviendaRiesgo = opcion.getDescripcion();
        this.idViviendaRiesgo = opcion.getCodigo();
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void linkData() {
        try {
            if(!idProvincia.isEmpty()) {
                provincia = Provincias.find(Provincias.class, StringUtil.toSQLName("sipp01CodigoProvincia") + "= ?", idProvincia).get(0).getDescripcion();
            }
            if(!idCanton.isEmpty()) {
                canton = Cantones.find(Cantones.class, "sipp01_codigo_provincia = ? and " + StringUtil.toSQLName("sipp02CodigoCanton") + "= ?", idProvincia, idCanton).get(0).getSipp02_descripcion_canton();
            }
            if(!idRegion.isEmpty()) {
                region = RegMIDEPLAN.find(RegMIDEPLAN.class, StringUtil.toSQLName("sipp08CodigoRegmidep") + "= ?", idRegion).get(0).getSipp08_descripcion_midep();
            }
            if(!idDistrito.isEmpty()) {
                distrito = Distrito.find(Distrito.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp07_codigo_cedes = '000' and " + StringUtil.toSQLName("sipp03CodigoDistrito") + "= ?", idProvincia, idCanton, idDistrito).get(0).getSipp03_descripcion_distrito();
            }
            if(!idGerenciaRegional.isEmpty()) {
                gerenciaRegional = Gerencias.find(Gerencias.class, StringUtil.toSQLName("sipp06_codigo_gerencia") + "= ?", idGerenciaRegional).get(0).getSipp06_descripcion_gerencia();
            }
            if(!idBarrio.isEmpty()) {
                barrio = Barrio.find(Barrio.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp03_codigo_distrito = ? and " + StringUtil.toSQLName("sipp04CodigoBarrio") + "= ?", idProvincia, idCanton, idDistrito, idBarrio).get(0).getSipp04_descripcion_barrio();
            }
            if(!idCaserio.isEmpty()) {
                caserio = Caserio.find(Caserio.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp03_codigo_distrito = ? AND sipp04_codigo_barrio = ? and " + StringUtil.toSQLName("sipp05CodigoCaserio") + "= ?", idProvincia, idCanton, idDistrito, idBarrio, idCaserio).get(0).getSipp05_desc_caserio();
            }
            if(!idZona.isEmpty()) {
                zona = zonas[Integer.parseInt(idZona) - 1];
            }
            if(!idViviendaRiesgo.isEmpty()) {
                viviendaRiesgo = riesgos[Integer.parseInt(idViviendaRiesgo) - 1];
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static List<Opcion> getProvincias() {
        Provincias db = new Provincias();
        return Opcion.transformProvincias(db.listAll(Provincias.class));
    }

    public static List<Opcion> getCantones(String idProvincia) {
        Cantones db = new Cantones();
        List<Cantones> list = db.find(Cantones.class, "sipp01_codigo_provincia = ?", idProvincia);
        return Opcion.transformCantones(list);
    }

    public static List<Opcion> getDistritos(String idProvincia, String idCanton) {
        Distrito db = new Distrito();
        List<Distrito> list = db.find(Distrito.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp07_codigo_cedes = '000'", idProvincia, idCanton );
        return Opcion.transformDistritos(list);
    }

    public static List<Opcion> getBarrios(String idProvincia, String idCanton, String idDistrito) {
        Barrio db = new Barrio();
        List<Barrio> list = db.find(Barrio.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp03_codigo_distrito = ?",
                idProvincia, idCanton, idDistrito );
        return Opcion.transformBarrios(list);
    }

    public static List<Opcion> getCaserios(String idProvincia, String idCanton, String idDistrito, String idBarrio) {
        Caserio db = new Caserio();
        List<Caserio> list = db.find(Caserio.class, "sipp01_codigo_provincia = ? AND sipp02_codigo_canton = ? AND sipp03_codigo_distrito = ? AND sipp04_codigo_barrio = ?",
                idProvincia, idCanton, idDistrito, idBarrio );
        return Opcion.transformCaserios(list);
    }

    static String[] zonas = new String[]{"Zona Urbana","Zona Rural"};
    public static List<Opcion> getZonas() {
        return Opcion.transformStringArray(zonas);
    }
    static String[] riesgos = new String[]{"Inundaciones","Avalancha o Deslizamientos","Influencia Volcánica","Otros","Ninguno de los Anteriores"};
    public static List<Opcion> getViviendaRiesgos() {
        return Opcion.transformStringArray(riesgos);
    }

    public static Opcion getGerenciaRegional(String idProvincia, String idCanton,  String idDistrito) {
        Distrito db = new Distrito();
        Distrito distrito = db.find(Distrito.class, "sipp02_codigo_canton = ? AND sipp01_codigo_provincia = ? AND sipp03_codigo_distrito = ? AND sipp07_codigo_cedes = ?",
                idCanton ,idProvincia, idDistrito, "000").get(0);


        List<Gerencias> gerencias = db.listAll(Gerencias.class);
        Gerencias gerencia = gerencias.get(0);

        for(Gerencias item : gerencias){
            if(item.getSipp06_codigo_gerencia().trim().equals(distrito.getSipp06_codigo_gerencia().trim())){
                gerencia = item;
                break;
            }
        }

        Opcion opcion = new Opcion(gerencia.getSipp06_codigo_gerencia(), gerencia.getSipp06_descripcion_gerencia());

        return opcion;
    }

    public static Opcion getRegionMideplan(String idProvincia, String idCanton) {
        Cantones db = new Cantones();
        Gerencias db2 = new Gerencias();

        Cantones canton = db.find(Cantones.class, "sipp02_codigo_canton = ? AND sipp01_codigo_provincia = ?",
                idCanton ,idProvincia).get(0);

        RegMIDEPLAN region= db.find(RegMIDEPLAN.class, "SIPP08_CODIGO_REGMIDEP = ?",
                canton.getSipp08_codigo_regmidep()).get(0);

        Opcion opcion = new Opcion(region.getSipp08_codigo_regmidep(), region.getSipp08_descripcion_midep());

        return opcion;
    }
}
