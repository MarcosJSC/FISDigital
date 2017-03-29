package cr.bamboo.fisdigital.logic.global;

import com.orm.StringUtil;

import java.util.ArrayList;
import java.util.Date;import java.util.List;

import cr.bamboo.fisdigital.logic.bo.Codigo;
import cr.bamboo.fisdigital.logic.bo.Correo;
import cr.bamboo.fisdigital.logic.bo.FIS;
import cr.bamboo.fisdigital.logic.bo.Familia;
import cr.bamboo.fisdigital.logic.bo.Integrante;
import cr.bamboo.fisdigital.logic.bo.Patrimonio;
import cr.bamboo.fisdigital.logic.bo.Ubicacion;
import cr.bamboo.fisdigital.logic.bo.ViviendaServicios;
import cr.bamboo.fisdigital.data.dbmodel.CentrosEducativos;
import cr.bamboo.fisdigital.data.dbmodel.CodEspecial;
import cr.bamboo.fisdigital.data.dbmodel.Fdm001_FichaInformacionSocial;
import cr.bamboo.fisdigital.data.dbmodel.Fdm002_Familias;
import cr.bamboo.fisdigital.data.dbmodel.Fdm003_PoblacionObjetivo;
import cr.bamboo.fisdigital.data.dbmodel.Fdm004_IntegrantesFamilia;
import cr.bamboo.fisdigital.data.dbmodel.Fdm005_FamiliaEspecial;
import cr.bamboo.fisdigital.data.dbmodel.Fdm006_CorreoElectFamilia;
import cr.bamboo.fisdigital.data.dbmodel.Oficios;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;
import cr.bamboo.fisdigital.data.dbmodel.Usuario;
import cr.bamboo.fisdigital.data.dbmodel.Fdm_Observaciones;

/**
 * Created by jbeita on 12/04/2015.
 * Esta clase es el cerebro de la lógica de la aplicacion
 * se encarga de comunicar los objetos de negocio con los de
 * base de datos y viceversa.
 *
 * Además, estaá implementado con el patrón Singleton que permite
 * una memoria temporal para comunicar los objetos entre las
 * diferentes caras de la FIS.
 */
public class FISController {
    private static FISController ourInstance = new FISController();
    private ArrayList<FIS> fisList=  null;
    public long idFISEditar= 0;
    public long idFamiliaEditar = 0;
    public long idIntegranteEditar = 0;
    public boolean esJefePrincipal = true;
    public boolean esJefeSecundario = true;
    public int positionFamilia = 0;

    // current FIS
    Fdm001_FichaInformacionSocial fdm001_fichaInformacionSocial;
    Fdm003_PoblacionObjetivo fdm003_poblacionObjetivo;
    Fdm004_IntegrantesFamilia fdm004_integrantesFamilia;

    public static FISController getInstance() {
        return ourInstance;
    }
    private Ubicacion ubicacion;
    private ViviendaServicios viviendaServicios;
    private Integrante integrante;
    private Patrimonio patrimonio;
    private String observaciones;
    private Fdm_Observaciones fdm_observaciones;

    // ************ new vals to fill
    private double Latitude = 0;
    private double Longitude = 0;
    public Usuario User = new Usuario();
    String IdDispositivo = "";

    public void Initialize(Usuario user, String idDispositivo, double latitude, double longitude){
        this.User = user; this.IdDispositivo = idDispositivo; this.Latitude = latitude; this.Longitude = longitude;
    }

    private FISController() {
        observaciones = "";
        ubicacion = new Ubicacion();
        viviendaServicios = new ViviendaServicios();
    }

    public double getLatitude(){
        return Latitude;
    }

    public String getIdDispositivo(){
        return IdDispositivo;
    }

    public void setIdDispositivo(String value){
        IdDispositivo = value;
    }

    public double getLongitude(){
        return Longitude;
    }

    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
    }

    public void setObservaciones(String observaciones){
        this.observaciones = observaciones;
        saveObservaciones();
    }

    public String getObservaciones(){
        return this.observaciones;
    }

    public Patrimonio getPatrimonio(){
        return this.patrimonio;
    }

    public void setObservaciones(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
    }

    public Ubicacion getUbicacion(){
        return ubicacion;
    }

    public void setViviendaServicios(ViviendaServicios viviendaServicios){
        this.viviendaServicios= viviendaServicios;
    }

    public ViviendaServicios getViviendaServicios(){
        return viviendaServicios;
    }

    public Integrante getIntegrante(){
        return integrante;
    }

    public void init(){
        fdm001_fichaInformacionSocial = new Fdm001_FichaInformacionSocial();
        reset();
    }


    public void initIntegrante(){
        fdm003_poblacionObjetivo = new Fdm003_PoblacionObjetivo();
        fdm004_integrantesFamilia = new Fdm004_IntegrantesFamilia();
        resetIntegrante();
    }

    public void reset(){
        observaciones = "";
        ubicacion = new Ubicacion();
        viviendaServicios = new ViviendaServicios();
    }

    public void resetIntegrante(){
        integrante = new Integrante();
    }

    public void finishFIS(){
        fdm001_fichaInformacionSocial = Fdm001_FichaInformacionSocial.findById(Fdm001_FichaInformacionSocial.class, idFISEditar);
        fdm001_fichaInformacionSocial.setFdm001_EstadoSincronizacion(1);
        fdm001_fichaInformacionSocial.saveOnBackground();
    }

    public void syncedFIS(long fisId){
        fdm001_fichaInformacionSocial = Fdm001_FichaInformacionSocial.findById(Fdm001_FichaInformacionSocial.class, fisId);
        fdm001_fichaInformacionSocial.setFdm001_EstadoSincronizacion(0);
        fdm001_fichaInformacionSocial.saveOnBackground();
    }

    public boolean canFinishFIS(){
        List<Fdm002_Familias> tempList = Fdm002_Familias.find(Fdm002_Familias.class, StringUtil.toSQLName("Fdm001_IdFichaInformacionSocial") + "= ?", String.valueOf(idFISEditar));

        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                Fdm002_Familias tempFamilia = tempList.get(i);
                if(tempFamilia.getFdm002_TenenciaVivienda() == null ||tempFamilia.getFdm002_TenenciaVivienda().isEmpty()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList<FIS> getFISList() {

        List<Fdm001_FichaInformacionSocial> tempList = Fdm001_FichaInformacionSocial.find(Fdm001_FichaInformacionSocial.class, "FDM001ESTADO_SINCRONIZACION <> ?", "0");
        fisList = new ArrayList<FIS>();

        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                FIS fis = new FIS();
                Fdm001_FichaInformacionSocial tempFIS = tempList.get(i);
                fis.setId(tempFIS.getId());
//                fis.familia_nombre = "temp text";
                fis.setFechaCreacion(tempFIS.getFdm001Fecha());
                fis.setDireccion(tempFIS.getFdm001DireccionVivienda());
                fis.setNumeroFolio(tempFIS.getFdm001NumeroFolio());

                if(tempFIS.getFdm001_EstadoSincronizacion() == 0){
                    fis.setEstado("Sincronizada");
                }
                else if(tempFIS.getFdm001_EstadoSincronizacion() == 1){
                    fis.setEstado("Completa");
                }
                else if(tempFIS.getFdm001_EstadoSincronizacion() == 2){
                    fis.setEstado("Incompleta");
                }
                this.fisList.add(fis);
            }
        }
        return this.fisList;
    }

    public ArrayList<Familia> getFamilias() {

        List<Fdm002_Familias> tempList = Fdm002_Familias.find(Fdm002_Familias.class, StringUtil.toSQLName("Fdm001_IdFichaInformacionSocial") + "= ?", String.valueOf(idFISEditar));
        ArrayList<Familia> listaFamilias = new ArrayList<Familia>();

        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                Familia familia = new Familia();
                Fdm002_Familias tempFamilia = tempList.get(i);
                Fdm003_PoblacionObjetivo jefe = Fdm003_PoblacionObjetivo.find(Fdm003_PoblacionObjetivo.class, StringUtil.toSQLName("idFamilia") + "= ?", String.valueOf(tempFamilia.getId())).get(0);

                familia.setIdFamilia(tempFamilia.getId());
                familia.setNombreFamilia(jefe.getFdm003_PrimerApellido() + " " + jefe.getFdm003_SegundoApellido());
                familia.setNumero(tempFamilia.getFdm002_NumeroFamilia());
                listaFamilias.add(familia);
            }
        }
        return listaFamilias;
    }

    public ArrayList<Integrante> getIntegrantes() {

        List<Fdm003_PoblacionObjetivo> tempList = Fdm003_PoblacionObjetivo.find(Fdm003_PoblacionObjetivo.class, StringUtil.toSQLName("idFamilia") + "= ?", String.valueOf(idFamiliaEditar));
        ArrayList<Integrante> listaIntegrantes = new ArrayList<Integrante>();

        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                Integrante integrante1 = new Integrante();
                Fdm003_PoblacionObjetivo tempIntegrante = tempList.get(i);

                Fdm004_IntegrantesFamilia fdm004 = Fdm004_IntegrantesFamilia.find(Fdm004_IntegrantesFamilia.class, StringUtil.toSQLName("Fdm002_IdFamilia") + "= ? and " + StringUtil.toSQLName("Fdm003_IdPoblacionObjetivo") + "= ?", String.valueOf(idFamiliaEditar), String.valueOf(tempIntegrante.getId())).get(0);

                integrante1.setId(tempIntegrante.getId());
                integrante1.setPrimerNombre(tempIntegrante.getFdm003_PrimerNombre());
                integrante1.setSegundoNombre(tempIntegrante.getFdm003_SegundoNombre());
                integrante1.setPrimerApellido(tempIntegrante.getFdm003_PrimerApellido());
                integrante1.setSegundoApellido(tempIntegrante.getFdm003_SegundoApellido());
                integrante1.setParentezcoNumero(Integrante.linkParentesco(fdm004.getFdm004ParentezcoJefe()));
                listaIntegrantes.add(integrante1);
            }
        }
        return listaIntegrantes;
    }

    public ArrayList<Correo> loadCorreos() {

        List<Fdm006_CorreoElectFamilia> tempList = Fdm006_CorreoElectFamilia.find(Fdm006_CorreoElectFamilia.class, StringUtil.toSQLName("Fdm006_IdFamilia") + "= ?", String.valueOf(idFamiliaEditar));
        ArrayList<Correo> listaCorreos = new ArrayList<Correo>();

        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                Fdm006_CorreoElectFamilia correo = tempList.get(i);
                Correo tempCorreo = new Correo();
                tempCorreo.setCorreo(correo.getFdm006_Correo());
                tempCorreo.setIdCorreo(correo.getId());
                listaCorreos.add(tempCorreo);
            }
        }
        return listaCorreos;
    }

    public ArrayList<Codigo> getCodigosEspeciales(String codigo, String descripcion) {
        List<CodEspecial> listaTemp = null;
        String query = "SELECT * FROM " + StringUtil.toSQLName("CodEspecial");
        if (!codigo.isEmpty()) {
            query += " WHERE " + StringUtil.toSQLName("sipp22CodEspecial") + " LIKE '%" + codigo +"%'";
        }
        if (!descripcion.isEmpty()) {
            if (query.contains("WHERE")) {
                query += " OR ";
            } else {
                query += " WHERE ";
            }
            query += StringUtil.toSQLName("sipp22DesEspecial") + " LIKE '%" + descripcion + "%'";
            ;
        }
        try {
            listaTemp = CodEspecial.findWithQuery(CodEspecial.class, query);
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        ArrayList<Codigo> result = new ArrayList<Codigo>();
        ArrayList<Codigo> agregados = getCodigosPorFamilia();

        for (int i = 0; i < listaTemp.size(); i++) {
            CodEspecial codigoEspecial = listaTemp.get(i);
            Codigo codigoTemp = new Codigo();
            codigoTemp.setCodigo(codigoEspecial.getCodigo());
            codigoTemp.setDescripcion(codigoEspecial.getDescripcion());
            if (!isAgregado(agregados, codigoTemp) && !codigoEspecial.getCodigo().equals("00")) {
                result.add(codigoTemp);
            }
        }

        return result;
    }

    public List<Opcion> getOficios(String descripcion) {
        List<Oficios> listaTemp = null;
        String query = "SELECT * FROM " + StringUtil.toSQLName("Oficios");
        if (!descripcion.isEmpty()) {
                query += " WHERE ";
                query += StringUtil.toSQLName("sipp13_descrip_oficio") + " LIKE '%" + descripcion + "%'";
            }
            try {
                listaTemp = Oficios.findWithQuery(Oficios.class, query);
            } catch (Exception ex) {
                System.out.println(ex.getStackTrace());
            }
        return Opcion.transformOficios(listaTemp);
    }

    public List<Opcion> getCentrosEnsenanza(String descripcion) {
        List<CentrosEducativos> listaTemp = null;
        String query = "SELECT * FROM " + StringUtil.toSQLName("CentrosEducativos");
        if (!descripcion.isEmpty()) {
            query += " WHERE ";
            query += StringUtil.toSQLName("sipp21DesCeneduc") + " LIKE '%" + descripcion + "%'";
        }
        try {
            listaTemp = CentrosEducativos.findWithQuery(CentrosEducativos.class, query);
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return Opcion.transformCentrosEnsenanza(listaTemp);
    }

    private boolean isAgregado(ArrayList<Codigo> agregados, Codigo codigo){
        for(int i=0; i<agregados.size(); i++){
            if(agregados.get(i).getCodigo().equals(codigo.getCodigo())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Codigo>getCodigosPorFamilia(){

            List<Fdm005_FamiliaEspecial> tempList = Fdm005_FamiliaEspecial.find(Fdm005_FamiliaEspecial.class, StringUtil.toSQLName("Fdm002_IdFamilia") + "= ?", String.valueOf(idFamiliaEditar));
            ArrayList<Codigo> result = new ArrayList<Codigo>();
            for(int i=0; i<tempList .size(); i++){
                Fdm005_FamiliaEspecial familiaEspecial= tempList.get(i);
                CodEspecial codEspecial = CodEspecial.find(CodEspecial.class, StringUtil.toSQLName("sipp22CodEspecial") + "= ?", String.valueOf(familiaEspecial.getFdm007_CodigoEspecial())).get(0);

                Codigo codigoTemp = new Codigo();
                codigoTemp.setId(codEspecial.getId());
                codigoTemp.setCodigo(familiaEspecial.getFdm007_CodigoEspecial());
                codigoTemp.setDescripcion(codEspecial.getDescripcion());
                result.add(codigoTemp);
            }
        return result;
    }

    public void saveUbicacion() {

        fdm001_fichaInformacionSocial.setFdm001IdDispositivoMovil(IdDispositivo);
        fdm001_fichaInformacionSocial.setFdm001_PuntoGeoreferenciaLat(Latitude);
        fdm001_fichaInformacionSocial.setFdm001_PuntoGeoreferenciaLong(Longitude);
        fdm001_fichaInformacionSocial.setFdm001CodigoGerencia(String.valueOf(ubicacion.getIdGerenciaRegional()));
        fdm001_fichaInformacionSocial.setFdm001_CodigoCedes("000");
        fdm001_fichaInformacionSocial.setFdm001_NumeroFolio(ubicacion.getNumeroFolio());
        fdm001_fichaInformacionSocial.setFdm001_CodigoEntrevistador(User.getUsuario());
        fdm001_fichaInformacionSocial.setFdm001_CodigoRegioMideplan(String.valueOf(ubicacion.getIdRegion()));
        fdm001_fichaInformacionSocial.setFdm001CodigoProvincia(ubicacion.getIdProvincia());
        fdm001_fichaInformacionSocial.setFdm001_CodigoCanton(ubicacion.getIdCanton());
        fdm001_fichaInformacionSocial.setFdm001CodigoDistrito(ubicacion.getIdDistrito());
        fdm001_fichaInformacionSocial.setFdm001CodigoBarrio(ubicacion.getIdBarrio());
        fdm001_fichaInformacionSocial.setFdm001CodigoCaserio(ubicacion.getIdCaserio());
        fdm001_fichaInformacionSocial.setFdm001Fecha(formatDate(formatDate(new Date())));
        fdm001_fichaInformacionSocial.setFdm001Zona(String.valueOf(ubicacion.getIdZona()));
        fdm001_fichaInformacionSocial.setFdm001DireccionVivienda(ubicacion.getDireccion().toUpperCase());
        fdm001_fichaInformacionSocial.setFdm001NumeroVivienda(ubicacion.getNumeroVivienda());
        fdm001_fichaInformacionSocial.setFdm001RiesgoVivienda(ubicacion.getIdViviendaRiesgo());

        if(idFISEditar != 0){
            fdm001_fichaInformacionSocial.setId(idFISEditar);
        }
        fdm001_fichaInformacionSocial.setFdm001_EstadoSincronizacion(2);
        idFISEditar = fdm001_fichaInformacionSocial.saveOnBackground();

        saveObservaciones();
    }

    public void loadUbicacion() {
        try {
            Fdm001_FichaInformacionSocial fdm001 = Fdm001_FichaInformacionSocial.findById(Fdm001_FichaInformacionSocial.class, idFISEditar);
            fdm001_fichaInformacionSocial = fdm001;
            ubicacion = new Ubicacion();
            ubicacion.setIdGerenciaRegional(fdm001.getFdm001CodigoGerencia() != null ? fdm001.getFdm001CodigoGerencia() : "");
            ubicacion.setNumeroFolio(fdm001.getFdm001NumeroFolio());
            ubicacion.setIdRegion(fdm001.getFdm001CodigoRegioMideplan() != null ? fdm001.getFdm001CodigoRegioMideplan() : "");
            ubicacion.setIdProvincia(fdm001.getFdm001CodigoProvincia() != null ? fdm001.getFdm001CodigoProvincia() : "");
            ubicacion.setIdCanton(fdm001.getFdm001CodigoCanton() != null ? fdm001.getFdm001CodigoCanton() : "");
            ubicacion.setIdDistrito(fdm001.getFdm001CodigoDistrito() != null ? fdm001.getFdm001CodigoDistrito() : "");
            ubicacion.setIdBarrio(fdm001.getFdm001CodigoBarrio() != null ? fdm001.getFdm001CodigoBarrio() : "");
            ubicacion.setIdCaserio(fdm001.getFdm001CodigoCaserio() != null ? fdm001.getFdm001CodigoCaserio() : "");
            ubicacion.setIdZona(fdm001.getFdm001Zona() != null ? fdm001.getFdm001Zona() : "");
            ubicacion.setDireccion(fdm001.getFdm001DireccionVivienda() != null ? fdm001.getFdm001DireccionVivienda() : "");
            ubicacion.setNumeroVivienda(fdm001.getFdm001NumeroVivienda() != null ? fdm001.getFdm001NumeroVivienda() : "");
            ubicacion.setIdViviendaRiesgo(fdm001.getFdm001RiesgoVivienda() != null ? fdm001.getFdm001RiesgoVivienda() : "");
            ubicacion.linkData();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public void saveViviendaServicios() {

        fdm001_fichaInformacionSocial.setId(idFISEditar);
        fdm001_fichaInformacionSocial.setFdm001TipoParedExterior(String.valueOf(viviendaServicios.getIdParedesExteriores()));
        fdm001_fichaInformacionSocial.setFdm001EstadoParedExterior(viviendaServicios.getEstadoParedes());
        fdm001_fichaInformacionSocial.setFdm001MaterialPiso(String.valueOf(viviendaServicios.getIdPiso()));
        fdm001_fichaInformacionSocial.setFdm001EstadoPiso(viviendaServicios.getEstadoPiso());
        fdm001_fichaInformacionSocial.setFdm001MaterialTecho(String.valueOf(viviendaServicios.getIdTecho()));
        fdm001_fichaInformacionSocial.setFdm001EstadoTecho(viviendaServicios.getEstadoTecho());
        fdm001_fichaInformacionSocial.setFdm001CantidadAposentosDormir(viviendaServicios.getAposentosDormir());
        fdm001_fichaInformacionSocial.setFdm001CantidadOtrosAposentos(viviendaServicios.getAposentosOtros());
        fdm001_fichaInformacionSocial.setFdm001TipoAbastecimientoAgua(String.valueOf(viviendaServicios.getIdFuenteAbastecimientoAgua()));
        fdm001_fichaInformacionSocial.setFdm001TipoEliminacionExcretas(String.valueOf(viviendaServicios.getIdSanitarioConectado()));
        fdm001_fichaInformacionSocial.setFdm001DisponibilidadBano(String.valueOf(viviendaServicios.getIdDisponibilidadBano()));
        fdm001_fichaInformacionSocial.setFdm001MontoSuministroElectrico(viviendaServicios.getMontoMensualLuz());
        fdm001_fichaInformacionSocial.setFdm001MontoSuministroAgua(viviendaServicios.getMontoMensualAgua());
        fdm001_fichaInformacionSocial.setFdm001CieloRaso(viviendaServicios.getIdCieloRaso());
        fdm001_fichaInformacionSocial.setFdm001EstadoCieloRaso(viviendaServicios.getEstadoCieloRaso());
        fdm001_fichaInformacionSocial.setFdm001MedioAbastecimientoAgua(viviendaServicios.getIdMedioAbastecimientoAgua());
        fdm001_fichaInformacionSocial.setFdm001DisponiblidadSuministroElectrico(viviendaServicios.getIdDisponibilidadLuzElectrica());
        fdm001_fichaInformacionSocial.setFdm001MedioEliminacionBasura(viviendaServicios.getIdMedioEliminacionBasura());
        fdm001_fichaInformacionSocial.setFdm001CantidadCamas(viviendaServicios.getNumeroCamas());
        fdm001_fichaInformacionSocial.setFdm001FuenteEnergiaCocina(viviendaServicios.getIdFuenteEnergiaCocina());
        fdm001_fichaInformacionSocial.setFdm001Nis(String.valueOf(viviendaServicios.getNumeroNIS()));

        fdm001_fichaInformacionSocial.saveOnBackground();
        saveObservaciones();
    }

    public void loadViviendaServicios() {
        try {
            Fdm001_FichaInformacionSocial fdm001 = Fdm001_FichaInformacionSocial.findById(Fdm001_FichaInformacionSocial.class, idFISEditar);
            fdm001_fichaInformacionSocial = fdm001;
            viviendaServicios = new ViviendaServicios();

            viviendaServicios.setIdParedesExteriores(fdm001.getFdm001TipoParedExterior() != null ? fdm001.getFdm001TipoParedExterior() : "");
            viviendaServicios.setEstadoParedes(fdm001.getFdm001EstadoParedExterior() != null ? fdm001.getFdm001EstadoParedExterior() : "");
            viviendaServicios.setIdPiso(fdm001.getFdm001MaterialPiso() != null ? fdm001.getFdm001MaterialPiso() : "");
            viviendaServicios.setEstadoPiso(fdm001.getFdm001EstadoPiso() != null ? fdm001.getFdm001EstadoPiso() : "");
            viviendaServicios.setIdTecho(fdm001.getFdm001MaterialTecho() != null ? fdm001.getFdm001MaterialTecho() : "");
            viviendaServicios.setEstadoTecho(fdm001.getFdm001EstadoTecho() != null ? fdm001.getFdm001EstadoTecho() : "");
            viviendaServicios.setAposentosDormir(fdm001.getFdm001CantidadAposentosDormir());
            viviendaServicios.setAposentosOtros(fdm001.getFdm001CantidadOtrosAposentos());
            viviendaServicios.setIdFuenteAbastecimientoAgua(fdm001.getFdm001TipoAbastecimientoAgua() != null ? fdm001.getFdm001TipoAbastecimientoAgua() : "");
            viviendaServicios.setIdSanitarioConectado(fdm001.getFdm001TipoEliminacionExcretas() != null ? fdm001.getFdm001TipoEliminacionExcretas() : "");
            viviendaServicios.setIdDisponibilidadBano(fdm001.getFdm001DisponibilidadBano() != null ? fdm001.getFdm001DisponibilidadBano() : "");
            viviendaServicios.setMontoMensualLuz(fdm001.getFdm001MontoSuministroElectrico() != null ? fdm001.getFdm001MontoSuministroElectrico() : "");
            viviendaServicios.setMontoMensualAgua(fdm001.getFdm001MontoSuministroAgua() != null ? fdm001.getFdm001MontoSuministroAgua() : "");
            viviendaServicios.setIdCieloRaso(fdm001.getFdm001CieloRaso() != null ? fdm001.getFdm001CieloRaso() : "");
            viviendaServicios.setEstadoCieloRaso(fdm001.getFdm001EstadoCieloRaso() != null ? fdm001.getFdm001EstadoCieloRaso() : "");
            viviendaServicios.setIdMedioAbastecimientoAgua(fdm001.getFdm001MedioAbastecimientoAgua() != null ? fdm001.getFdm001MedioAbastecimientoAgua() : "");
            viviendaServicios.setIdDisponibilidadLuzElectrica(fdm001.getFdm001DisponiblidadSuministroElectrico() != null ? fdm001.getFdm001DisponiblidadSuministroElectrico() : "");
            viviendaServicios.setIdMedioEliminacionBasura(fdm001.getFdm001MedioEliminacionBasura() != null ? fdm001.getFdm001MedioEliminacionBasura() : "");
            viviendaServicios.setNumeroCamas(fdm001.getFdm001CantidadCamas());
            viviendaServicios.setIdFuenteEnergiaCocina(fdm001.getFdm001FuenteEnergiaCocina() != null ? fdm001.getFdm001FuenteEnergiaCocina() : "");
            viviendaServicios.setNumeroNIS(fdm001.getFdm001Nis() != null ? fdm001.getFdm001Nis() : "");
            viviendaServicios.linkData();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveObservaciones(){
        try {
            if(fdm_observaciones == null){
                fdm_observaciones= new Fdm_Observaciones();
            }
            fdm_observaciones.setFdm001IdFichaInformacionSocial(idFISEditar);
            fdm_observaciones.setObservaciones(this.getObservaciones());
            fdm_observaciones.save();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadObservaciones() {
        try {
            List<Fdm_Observaciones> listObservaciones = Fdm_Observaciones.find(Fdm_Observaciones.class, StringUtil.toSQLName("fdm001IdFichaInformacionSocial") + "= ?", String.valueOf(idFISEditar));
             if(listObservaciones.size() > 0){
                 fdm_observaciones = listObservaciones.get(0);
                 observaciones = fdm_observaciones.getObservaciones();
             }else{
                 fdm_observaciones = new Fdm_Observaciones();
             }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveFamilia(){

        int numeroFamilia = Fdm002_Familias.find(Fdm002_Familias.class, StringUtil.toSQLName("Fdm001_IdFichaInformacionSocial") + "= ?", String.valueOf(idFISEditar)).size()+1;

        Fdm002_Familias fdm002_familias = new Fdm002_Familias();
        fdm002_familias.setFdm005_IdDispositivoMovil(IdDispositivo);
        fdm002_familias.setFdm001_IdFichaInformacionSocial(idFISEditar);
        fdm002_familias.setFdm002_NumeroFamilia(numeroFamilia);

        fdm002_familias.saveOnBackground();
        idFamiliaEditar = fdm002_familias.getId();
    }

    public void saveIntegrante() {

        try {
            if (idFamiliaEditar == 0) {
                saveFamilia();
            }
            //Activity_Integrante_1
            fdm003_poblacionObjetivo.setFdm005_IdDispositivoMovil(IdDispositivo);
            fdm003_poblacionObjetivo.setIdFamilia(idFamiliaEditar);
            fdm003_poblacionObjetivo.setFdm003_TipoIdentificacion(String.valueOf(integrante.getIdTipoIdentificacion()));
            fdm003_poblacionObjetivo.setFdm003_IdPersonal(integrante.getNumeroIdentificacion());
            fdm003_poblacionObjetivo.setFdm003_PrimerApellido(integrante.getPrimerApellido());
            fdm003_poblacionObjetivo.setFdm003_SegundoApellido(integrante.getSegundoApellido());
            fdm003_poblacionObjetivo.setFdm003_PrimerNombre(integrante.getPrimerNombre());
            fdm003_poblacionObjetivo.setFdm003_SegundoNombre(integrante.getSegundoNombre());
            fdm003_poblacionObjetivo.setFdm003_FechaNacimiento(formatDate(integrante.getFechaNacimiento()));
            fdm003_poblacionObjetivo.setFdm003_Sexo(String.valueOf(integrante.getIdSexo()));
            fdm003_poblacionObjetivo.setFdm003_EstadoCivil(String.valueOf(integrante.getIdEstadoCivil()));
            fdm003_poblacionObjetivo.setFdm003_Nacionalidad(String.valueOf(integrante.getIdNacionalidad()));
            fdm003_poblacionObjetivo.setFdm003_Usuario(User.getUsuario());
            fdm003_poblacionObjetivo.setFdm003_FechaHora(formatDate(formatDate(new Date())));

            //Activity_Integrante_2
            fdm003_poblacionObjetivo.setFdm003_CondicionActividad(String.valueOf(integrante.getIdCondicionActividad()));
            fdm003_poblacionObjetivo.setFdm003_CodigoOficio(String.valueOf(integrante.getIdOcupacionOficio()));
            fdm003_poblacionObjetivo.setFdm003_CategoriaOcupacion(String.valueOf(integrante.getIdCategoriaOcupacional()));
            fdm003_poblacionObjetivo.setFdm003_IngresoPral(integrante.getIngresoSalario());
            fdm003_poblacionObjetivo.setFdm003_IngresoCuentaPropia(integrante.getIngresoPropio());
            fdm003_poblacionObjetivo.setFdm003_OtrosIngresos(integrante.getOtrosIngresos());

            //Activity_Integrante_3
            fdm003_poblacionObjetivo.setFdm003_CondicionSalud(String.valueOf(integrante.getIdDeficienciaFisica()));
            fdm003_poblacionObjetivo.setFdm003_EnfermedadCronica(integrante.getIdEnfermedadCronica());
            fdm003_poblacionObjetivo.setFdm003_AspectosPsicoSociales(integrante.getIdAspectosPsicosociales());
            fdm003_poblacionObjetivo.setFdm003_TipoPension(String.valueOf(integrante.getIdTipoPension()));
            fdm003_poblacionObjetivo.setFdm003_TipoSeguro(String.valueOf(integrante.getIdCondicionAseguramiento()));
            fdm003_poblacionObjetivo.setFdm003_AsistenciaCentroEducativo(String.valueOf(integrante.getIdAsistenciaCentrosEnsenanza()));
            fdm003_poblacionObjetivo.setFdm003_UltimoAnioAprobado(integrante.getUltimoAnoAprobado());
            fdm003_poblacionObjetivo.setFdm003_NivelEstudio(String.valueOf(integrante.getIdCicloEnsenanza()));
            fdm003_poblacionObjetivo.setFdm003_CodigoCentroEnsenanza(integrante.getIdCodigoCentroEnsenanza());
            fdm003_poblacionObjetivo.setFdm003_AreaCapacitacion(integrante.getIdAreaCapacitacion());

            fdm003_poblacionObjetivo.saveOnBackground();
            idIntegranteEditar = fdm003_poblacionObjetivo.getId();

            if (fdm004_integrantesFamilia.getFdm004NumeroOrden() == 0) {
                int numeroOrden = Fdm003_PoblacionObjetivo.find(Fdm003_PoblacionObjetivo.class, StringUtil.toSQLName("idFamilia") + "= ?", String.valueOf(idFamiliaEditar)).size();
                fdm004_integrantesFamilia.setFdm004NumeroOrden(numeroOrden);
            }
            fdm004_integrantesFamilia.setFdm005IdDispositivoMovil(IdDispositivo);
            fdm004_integrantesFamilia.setFdm003IdPoblacionObjetivo(idIntegranteEditar);
            fdm004_integrantesFamilia.setFdm002IdFamilia(idFamiliaEditar);
            fdm004_integrantesFamilia.setFdm004JefeFamilia(integrante.getJefeFamilia() ? "1" : "");
            fdm004_integrantesFamilia.setFdm004ParentezcoJefe(String.valueOf(integrante.getIdParentezcoNumero()));
            fdm004_integrantesFamilia.setFdm004RelacionEntreJefes(String.valueOf(integrante.getIdParentezcoJefes()));
            fdm004_integrantesFamilia.setFdm004NumeroHogar(integrante.getNumeroHogar());
            fdm004_integrantesFamilia.setFdm004Usuario(User.getUsuario());
            fdm004_integrantesFamilia.setFdm004FechaHora(formatDate(formatDate(new Date())));
            fdm004_integrantesFamilia.save();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public Integrante loadIntegrante(){

        integrante = new Integrante();
        try {
            fdm003_poblacionObjetivo = Fdm003_PoblacionObjetivo.findById(Fdm003_PoblacionObjetivo.class, idIntegranteEditar);
            fdm004_integrantesFamilia= Fdm004_IntegrantesFamilia.find(Fdm004_IntegrantesFamilia.class, StringUtil.toSQLName("Fdm002_IdFamilia") + "= ? and " + StringUtil.toSQLName("Fdm003_IdPoblacionObjetivo") + "= ?", String.valueOf(idFamiliaEditar), String.valueOf(idIntegranteEditar)).get(0);
            //Activity_Integrante_1
            integrante.setIdTipoIdentificacion(fdm003_poblacionObjetivo.getFdm003_TipoIdentificacion() != null ? fdm003_poblacionObjetivo.getFdm003_TipoIdentificacion() : "");
            integrante.setNumeroIdentificacion(fdm003_poblacionObjetivo.getFdm003_IdPersonal() != null ? fdm003_poblacionObjetivo.getFdm003_IdPersonal() : "");
            integrante.setPrimerApellido(fdm003_poblacionObjetivo.getFdm003_PrimerApellido() != null ? fdm003_poblacionObjetivo.getFdm003_PrimerApellido() : "");
            integrante.setSegundoApellido(fdm003_poblacionObjetivo.getFdm003_SegundoApellido() != null ? fdm003_poblacionObjetivo.getFdm003_SegundoApellido() : "");
            integrante.setPrimerNombre(fdm003_poblacionObjetivo.getFdm003_PrimerNombre() != null ? fdm003_poblacionObjetivo.getFdm003_PrimerNombre() : "");
            integrante.setSegundoNombre(fdm003_poblacionObjetivo.getFdm003_SegundoNombre() != null ? fdm003_poblacionObjetivo.getFdm003_SegundoNombre() : "");
            integrante.setFechaNacimiento(fdm003_poblacionObjetivo.getFdm003_FechaNacimiento() != null ? fdm003_poblacionObjetivo.getFdm003_FechaNacimiento() : "");
            integrante.setIdSexo(fdm003_poblacionObjetivo.getFdm003_Sexo() != null ? fdm003_poblacionObjetivo.getFdm003_Sexo() : "");
            integrante.setIdEstadoCivil(fdm003_poblacionObjetivo.getFdm003_EstadoCivil() != null ? fdm003_poblacionObjetivo.getFdm003_EstadoCivil() : "");
            integrante.setIdNacionalidad(fdm003_poblacionObjetivo.getFdm003_Nacionalidad() != null ? fdm003_poblacionObjetivo.getFdm003_Nacionalidad() : "");

            //Activity_Integrante_2
            integrante.setIdCondicionActividad(fdm003_poblacionObjetivo.getFdm003_CondicionActividad() != null ? fdm003_poblacionObjetivo.getFdm003_CondicionActividad() : "");
            integrante.setIdOcupacionOficio(fdm003_poblacionObjetivo.getFdm003_CodigoOficio() != null ? fdm003_poblacionObjetivo.getFdm003_CodigoOficio() : "");
            integrante.setIdCategoriaOcupacional(fdm003_poblacionObjetivo.getFdm003_CategoriaOcupacion() != null ? fdm003_poblacionObjetivo.getFdm003_CategoriaOcupacion() : "");
            integrante.setIngresoSalario(fdm003_poblacionObjetivo.getFdm003_IngresoPral());
            integrante.setIngresoPropio(fdm003_poblacionObjetivo.getFdm003_IngresoCuentaPropia());
            integrante.setOtrosIngresos(fdm003_poblacionObjetivo.getFdm003_OtrosIngresos());

            //Activity_Integrante_3
            integrante.setIdDeficienciaFisica(fdm003_poblacionObjetivo.getFdm003_CondicionSalud() != null ? fdm003_poblacionObjetivo.getFdm003_CondicionSalud() : "");
            integrante.setIdEnfermedadCronica(fdm003_poblacionObjetivo.getFdm003_EnfermedadCronica() != null ? fdm003_poblacionObjetivo.getFdm003_EnfermedadCronica() : "");
            integrante.setIdAspectosPsicosociales(fdm003_poblacionObjetivo.getFdm003_AspectosPsicoSociales() != null ? fdm003_poblacionObjetivo.getFdm003_AspectosPsicoSociales() : "");
            integrante.setIdTipoPension(fdm003_poblacionObjetivo.getFdm003_TipoPension() != null ? fdm003_poblacionObjetivo.getFdm003_TipoPension() : "");
            integrante.setIdCondicionAseguramiento(fdm003_poblacionObjetivo.getFdm003_TipoSeguro() != null ? fdm003_poblacionObjetivo.getFdm003_TipoSeguro() : "");
            integrante.setIdAsistenciaCentrosEnsenanza(fdm003_poblacionObjetivo.getFdm003_AsistenciaCentroEducativo() != null ? fdm003_poblacionObjetivo.getFdm003_AsistenciaCentroEducativo() : "");
            integrante.setUltimoAnoAprobado(fdm003_poblacionObjetivo.getFdm003_UltimoAnioAprobado());
            integrante.setIdCicloEnsenanza(fdm003_poblacionObjetivo.getFdm003_NivelEstudio() != null ? fdm003_poblacionObjetivo.getFdm003_NivelEstudio() : "");
            integrante.setIdCodigoCentroEnsenanza(fdm003_poblacionObjetivo.getFdm003_CodigoCentroEnsenanza() != null ? fdm003_poblacionObjetivo.getFdm003_CodigoCentroEnsenanza() : "");
            integrante.setIdAreaCapacitacion(fdm003_poblacionObjetivo.getFdm003_AreaCapacitacion() != null ? fdm003_poblacionObjetivo.getFdm003_AreaCapacitacion() : "");

            integrante.setJefeFamilia((fdm004_integrantesFamilia.getFdm004JefeFamilia() == null || fdm004_integrantesFamilia.getFdm004JefeFamilia().isEmpty()) ? false : true);
            integrante.setIdParentezcoNumero(fdm004_integrantesFamilia.getFdm004ParentezcoJefe() != null ? fdm004_integrantesFamilia.getFdm004ParentezcoJefe() : "");
            integrante.setIdParentezcoJefes(fdm004_integrantesFamilia.getFdm004RelacionEntreJefes() != null ? fdm004_integrantesFamilia.getFdm004RelacionEntreJefes() : "");
            integrante.setNumeroHogar(fdm004_integrantesFamilia.getFdm004NumeroHogar());

            integrante.linkData();

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return integrante;
    }

    public void savePatrimonio() {

        try {
            Fdm002_Familias fdm002_familias =  Fdm002_Familias.find(Fdm002_Familias.class, "id= ?", String.valueOf(idFamiliaEditar)).get(0);

            fdm002_familias.setFdm002_ExpedienteActual(patrimonio.getExpedienteActual());
            fdm002_familias.setFdm002_TenenciaVivienda(String.valueOf(patrimonio.getIdTenenciaVivienda()));
            fdm002_familias.setFdm002_TvColor(String.valueOf(patrimonio.getOtrosBienes()[0]?"S":"N"));
            fdm002_familias.setFdm002_Refrigeradora(String.valueOf(patrimonio.getOtrosBienes()[1]?"S":"N"));
            fdm002_familias.setFdm002_Lavadora(String.valueOf(patrimonio.getOtrosBienes()[2]?"S":"N"));
            fdm002_familias.setFdm002_LoteConstruir(String.valueOf(patrimonio.getTenenciaOtrosBienes()[0]?"S":"N"));
            fdm002_familias.setFdm002_Finca(String.valueOf(patrimonio.getTenenciaOtrosBienes()[2]?"S":"N"));
            fdm002_familias.setFdm002_OtrasConstrucciones(String.valueOf(patrimonio.getTenenciaOtrosBienes()[1]?"S":"N"));
            fdm002_familias.setFdm002_VehiculoPersonal(String.valueOf(patrimonio.getOtrosBienes()[16]?"S":"N"));
            fdm002_familias.setFdm002_VehiculoTrabajo(String.valueOf(patrimonio.getOtrosBienes()[17]?"S":"N"));
            fdm002_familias.setFdm002_Telefono(String.valueOf(patrimonio.getOtrosBienes()[14]?"S":"N"));
            fdm002_familias.setFdm002_NumeroTelefono(patrimonio.getTelefonoResidencial1());
            fdm002_familias.setFdm002_MontoTelefono(patrimonio.getMontoTelefonoResidencial1());
            fdm002_familias.setFdm002_Vhs(String.valueOf(patrimonio.getOtrosBienes()[3]?"S":"N"));
            fdm002_familias.setFdm002_EsquipoSonido(String.valueOf(patrimonio.getOtrosBienes()[4]?"S":"N"));
            fdm002_familias.setFdm002_NingunBien(String.valueOf(patrimonio.getOtrosBienes()[22]?"S":"N"));

            if(patrimonio.getIdTenenciaVivienda().equals("2")) {
                fdm002_familias.setFdm002_MontoDeuda(patrimonio.getMontoMensualPropiaHipoteca());
                fdm002_familias.setFdm002_EstadoTenenciaVivienda(patrimonio.getEstadoPropiaHpoteca().equals("Con morosidad")? "2" : "1");
            }else if(patrimonio.getIdTenenciaVivienda().equals("3")) {
                fdm002_familias.setFdm002_MontoDeuda(patrimonio.getMontoMensualAquilada());
                fdm002_familias.setFdm002_EstadoTenenciaVivienda(patrimonio.getEstadoAlquilada().equals("Con morosidad")? "2" : "1");
            }

            fdm002_familias.setFdm002_Usuario(User.getUsuario());
            fdm002_familias.setFdm002_FechaHora(formatDate(formatDate(new Date())));
            fdm002_familias.setFdm002_AreaFinca(String.valueOf(patrimonio.getFincaParcelaArea()));
            fdm002_familias.setFdm002_ObtieneVivienda(patrimonio.getIdAdquirioVivienda());
            fdm002_familias.setFdm002_AnoVehiculoTrabajo(patrimonio.getAnoVehiculoTrabajo());
            fdm002_familias.setFdm002_AnoVehiculoPersonal(patrimonio.getAnoVehiculoPersonal());
            fdm002_familias.setFdm002_TelefonoCelular(patrimonio.getOtrosBienes()[15]?"S":"N");
            fdm002_familias.setFdm002_MontoTelefonoCelular(patrimonio.getMontoTelefonoCelular1());
            fdm002_familias.setFdm002_NumeroTelefonoCelular(patrimonio.getTelefonoCelular1());
            fdm002_familias.setFdm002_Ducha(String.valueOf(patrimonio.getOtrosBienes()[5]?"S":"N"));
            fdm002_familias.setFdm002_TanqueAgua(String.valueOf(patrimonio.getOtrosBienes()[6]?"S":"N"));
            fdm002_familias.setFdm002_Microondas(String.valueOf(patrimonio.getOtrosBienes()[7]?"S":"N"));
            fdm002_familias.setFdm002_Computadora(String.valueOf(patrimonio.getOtrosBienes()[8]?"S":"N"));
            fdm002_familias.setFdm002_MotocicletaTrabajo(String.valueOf(patrimonio.getOtrosBienes()[18]?"S":"N"));
            fdm002_familias.setFdm002_AnnoMotocicletaTrabajo(patrimonio.getAnoMotocicletaTrabajo());
            fdm002_familias.setFdm002_PangaBote(String.valueOf(patrimonio.getOtrosBienes()[9]?"S":"N"));
            fdm002_familias.setFdm002_LanchaMotor(String.valueOf(patrimonio.getOtrosBienes()[10]?"S":"N"));
            fdm002_familias.setFdm002_MaquinariaIndustrial(String.valueOf(patrimonio.getOtrosBienes()[11]?"S":"N"));
            fdm002_familias.setFdm002_MaquinariaAgricolaBasica(String.valueOf(patrimonio.getOtrosBienes()[12]?"S":"N"));
            fdm002_familias.setFdm002_OtraMaquinariaAgricola(String.valueOf(patrimonio.getOtrosBienes()[13]?"S":"N"));
            fdm002_familias.setFdm002_GanadoBovino(String.valueOf(patrimonio.getOtrosBienes()[19]?"S":"N"));
            fdm002_familias.setFdm002_CabezasBovino(patrimonio.getNumeroCabezasBovino());
            fdm002_familias.setFdm002_GanadoPorcino(String.valueOf(patrimonio.getOtrosBienes()[20]?"S":"N"));
            fdm002_familias.setFdm002_CabezasPorcino(patrimonio.getNumeroCabezasPorcino());
            fdm002_familias.setFdm002_ProduccionFinca(patrimonio.getIdProduccionPara());
            fdm002_familias.setFdm002_AreaLoteConstruccion(Math.round(patrimonio.getAreaM2Lote()));
            fdm002_familias.setFdm002_NingunInmueble(String.valueOf(patrimonio.getTenenciaOtrosBienes()[3]?"S":"N"));
            fdm002_familias.setFdm002_TelefonoOtro(String.valueOf(patrimonio.getOtrosBienes()[21]?"S":"N"));
            fdm002_familias.setFdm002_NumeroTelefonoOtro(patrimonio.getTelefonoOtro());
            fdm002_familias.setFdm002_CantidadTvColor(patrimonio.getCantidadTV());
            fdm002_familias.setFdm002_CantidadTelCelular(patrimonio.getCantidadTelefonoCelular());
            fdm002_familias.setFdm002_CantidadEquipoSonido(patrimonio.getCantidadEquipoSonido());
            fdm002_familias.setFdm002_CantidadComputadoras(patrimonio.getCantidadComputadoras());
            fdm002_familias.setFdm002_CantVehiculoPersonal(patrimonio.getCantidadVehiculoPersonal());

            fdm002_familias.saveOnBackground();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadPatrimonio() {

        try {
            Fdm002_Familias fdm002_familias =  Fdm002_Familias.find(Fdm002_Familias.class, "id= ?", String.valueOf(idFamiliaEditar)).get(0);
            patrimonio = new Patrimonio();

            patrimonio.setExpedienteActual(fdm002_familias.getFdm002_ExpedienteActual());
            patrimonio.setIdTenenciaVivienda(fdm002_familias.getFdm002_TenenciaVivienda() != null ? fdm002_familias.getFdm002_TenenciaVivienda() : "");
            patrimonio.getOtrosBienes()[0] = fdm002_familias.getFdm002_TvColor().equals("S")?true:false;
            patrimonio.getOtrosBienes()[1] = fdm002_familias.getFdm002_Refrigeradora().equals("S")?true:false;;
            patrimonio.getOtrosBienes()[2] = fdm002_familias.getFdm002_Lavadora().equals("S")?true:false;
            patrimonio.getTenenciaOtrosBienes()[0] = fdm002_familias.getFdm002_LoteConstruir().equals("S")?true:false;
            patrimonio.getTenenciaOtrosBienes()[2] = fdm002_familias.getFdm002_Finca().equals("S")?true:false;
            patrimonio.getTenenciaOtrosBienes()[1] = fdm002_familias.getFdm002_OtrasConstrucciones().equals("S")?true:false;
            patrimonio.getOtrosBienes()[16] = fdm002_familias.getFdm002_VehiculoPersonal().equals("S")?true:false;
            patrimonio.getOtrosBienes()[17] = fdm002_familias.getFdm002_VehiculoTrabajo().equals("S")?true:false;
            patrimonio.getOtrosBienes()[14] = fdm002_familias.getFdm002_Telefono().equals("S")?true:false;
            patrimonio.setTelefonoResidencial1(fdm002_familias.getFdm002_NumeroTelefono() != null ? fdm002_familias.getFdm002_NumeroTelefono() : "");
            patrimonio.setMontoTelefonoResidencial1(fdm002_familias.getFdm002_MontoTelefono());
            patrimonio.getOtrosBienes()[3] = fdm002_familias.getFdm002_Vhs().equals("S")?true:false;
            patrimonio.getOtrosBienes()[4] = fdm002_familias.getFdm002_EsquipoSonido().equals("S")?true:false;
            patrimonio.getOtrosBienes()[22] = fdm002_familias.getFdm002_NingunBien().equals("S")?true:false;


            if(patrimonio.getIdTenenciaVivienda().equals("3")){
                patrimonio.setMontoMensualAquilada(fdm002_familias.getFdm002_MontoDeuda());
                patrimonio.setEstadoAlquilada(fdm002_familias.getFdm002_EstadoTenenciaVivienda());
                patrimonio.setMontoMensualPropiaHipoteca(0);
                patrimonio.setEstadoPropiaHpoteca("");
            }
            if(patrimonio.getIdTenenciaVivienda().equals("2")) {
                patrimonio.setMontoMensualPropiaHipoteca(fdm002_familias.getFdm002_MontoDeuda());
                patrimonio.setEstadoPropiaHpoteca(fdm002_familias.getFdm002_EstadoTenenciaVivienda());
                patrimonio.setMontoMensualAquilada(0);
                patrimonio.setEstadoAlquilada("");
            }

            patrimonio.setFincaParcelaArea(Float.parseFloat(!fdm002_familias.getFdm002_AreaFinca().isEmpty() ? fdm002_familias.getFdm002_AreaFinca() : "0"));
            patrimonio.setIdAdquirioVivienda(!fdm002_familias.getFdm002_ObtieneVivienda().isEmpty() ? fdm002_familias.getFdm002_ObtieneVivienda() : "");
            patrimonio.setAnoVehiculoTrabajo(!fdm002_familias.getFdm002_AnoVehiculoTrabajo().isEmpty() ? fdm002_familias.getFdm002_AnoVehiculoTrabajo() : "");
            patrimonio.setAnoVehiculoPersonal(!fdm002_familias.getFdm002_AnoVehiculoPersonal().isEmpty() ? fdm002_familias.getFdm002_AnoVehiculoPersonal() : "");
            patrimonio.getOtrosBienes()[15] = fdm002_familias.getFdm002_TelefonoCelular().equals("S")?true:false;
            patrimonio.setMontoTelefonoCelular1(fdm002_familias.getFdm002_MontoTelefonoCelular());
            patrimonio.setTelefonoCelular1(!fdm002_familias.getFdm002_NumeroTelefonoCelular().isEmpty() ? fdm002_familias.getFdm002_NumeroTelefonoCelular() : "");
            patrimonio.getOtrosBienes()[5] = fdm002_familias.getFdm002_Ducha().equals("S")?true:false;
            patrimonio.getOtrosBienes()[6] = fdm002_familias.getFdm002_TanqueAgua().equals("S")?true:false;
            patrimonio.getOtrosBienes()[7] = fdm002_familias.getFdm002_Microondas().equals("S")?true:false;
            patrimonio.getOtrosBienes()[8] = fdm002_familias.getFdm002_Computadora().equals("S")?true:false;
            patrimonio.getOtrosBienes()[18] = fdm002_familias.getFdm002_MotocicletaTrabajo().equals("S")?true:false;
            patrimonio.setAnoMotocicletaTrabajo(!fdm002_familias.getFdm002_AnnoMotocicletaTrabajo().isEmpty() ? fdm002_familias.getFdm002_AnnoMotocicletaTrabajo() : "");
            patrimonio.getOtrosBienes()[9] = fdm002_familias.getFdm002_PangaBote().equals("S")?true:false;
            patrimonio.getOtrosBienes()[10] = fdm002_familias.getFdm002_LanchaMotor().equals("S")?true:false;
            patrimonio.getOtrosBienes()[11] = fdm002_familias.getFdm002_MaquinariaIndustrial().equals("S")?true:false;
            patrimonio.getOtrosBienes()[12] = fdm002_familias.getFdm002_MaquinariaAgricolaBasica().equals("S")?true:false;
            patrimonio.getOtrosBienes()[13] = fdm002_familias.getFdm002_OtraMaquinariaAgricola().equals("S")?true:false;
            patrimonio.getOtrosBienes()[19] = fdm002_familias.getFdm002_GanadoBovino().equals("S")?true:false;
            patrimonio.setNumeroCabezasBovino(fdm002_familias.getFdm002_CabezasBovino());
            patrimonio.getOtrosBienes()[20] = fdm002_familias.getFdm002_GanadoPorcino().equals("S")?true:false;
            patrimonio.setNumeroCabezasPorcino(fdm002_familias.getFdm002_CabezasPorcino());
            patrimonio.setIdProduccionPara(!fdm002_familias.getFdm002_ProduccionFinca().isEmpty() ? fdm002_familias.getFdm002_ProduccionFinca() : "");
            patrimonio.setAreaM2Lote(fdm002_familias.getFdm002_AreaLoteConstruccion());
            patrimonio.getTenenciaOtrosBienes()[3] = fdm002_familias.getFdm002_NingunInmueble().equals("S")?true:false;
            patrimonio.getOtrosBienes()[21] = fdm002_familias.getFdm002_TelefonoOtro().equals("S")?true:false;
            patrimonio.setTelefonoOtro(!fdm002_familias.getFdm002_NumeroTelefonoOtro().isEmpty() ? fdm002_familias.getFdm002_NumeroTelefonoOtro() : "");
            patrimonio.setCantidadTV(fdm002_familias.getFdm002_CantidadTvColor());
            patrimonio.setCantidadTelefonoCelular(fdm002_familias.getFdm002_CantidadTelCelular());
            patrimonio.setCantidadEquipoSonido(fdm002_familias.getFdm002_CantidadEquipoSonido());
            patrimonio.setCantidadComputadoras(fdm002_familias.getFdm002_CantidadComputadoras());
            patrimonio.setCantidadVehiculoPersonal(fdm002_familias.getFdm002_CantVehiculoPersonal());

            patrimonio.linkData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void saveCorreo(String correo){
        try {
            Fdm006_CorreoElectFamilia fdm006 = new Fdm006_CorreoElectFamilia();
            fdm006.setFdm006_IdDispositivoMovil(IdDispositivo);
            fdm006.setFdm006_IdFamilia(idFamiliaEditar);
            fdm006.setFdm006_Correo(correo);
            fdm006.save();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void deleteCorreo(long id){
        try {
            Fdm006_CorreoElectFamilia.deleteAll(Fdm006_CorreoElectFamilia.class, "id=? and "+StringUtil.toSQLName("Fdm006_IdFamilia")+"=?", String.valueOf(id), String.valueOf(idFamiliaEditar));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveCodigo(String codigo){
        try {
            Fdm005_FamiliaEspecial fdm005_familiaEspecial = new Fdm005_FamiliaEspecial();
            fdm005_familiaEspecial.setFdm005_IdDispositivoMovil(IdDispositivo);
            fdm005_familiaEspecial.setFdm002_IdFamilia(idFamiliaEditar);
            fdm005_familiaEspecial.setFdm007_CodigoEspecial(codigo);
            fdm005_familiaEspecial.setFdm007_Usuario(User.getUsuario());
            fdm005_familiaEspecial.setFdm007_Fecha(formatDate(formatDate(new Date())));
            fdm005_familiaEspecial.save();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void deleteCodigo(String codigo){
        try {
            Fdm005_FamiliaEspecial.deleteAll(Fdm005_FamiliaEspecial.class, StringUtil.toSQLName("Fdm007_CodigoEspecial")+"=? and "+StringUtil.toSQLName("Fdm002_IdFamilia")+"=?", codigo, String.valueOf(idFamiliaEditar));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String formatDate (Date fecha) {
        return Integrante.getDateString(Integrante.formatDate(fecha));
    }

    private String formatDate (String fecha) {

        String[] fechaN = fecha.split("/");

        StringBuilder sb = new StringBuilder();
        sb.append(fechaN[2]);
        sb.append("/");
        sb.append(fechaN[1]);
        sb.append("/");
        sb.append(fechaN[0]);

        return sb.toString();
    }
}
