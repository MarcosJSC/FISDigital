package cr.bamboo.fisdigital.logic.bo;

import com.orm.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cr.bamboo.fisdigital.data.dbmodel.CentrosEducativos;
import cr.bamboo.fisdigital.data.dbmodel.Oficios;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;
import cr.bamboo.fisdigital.data.dbmodel.RegistroCivil;
import cr.bamboo.fisdigital.data.dbmodel.TiposIdentificacion;

/**
      * Created by JBeita on 22/03/2015.
      */
    public class Integrante {

    private long id;
    private boolean esJefeFamilia;
    private boolean esPrimerJefeFamilia;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String numeroIdentificacion;
    private String fechaNacimiento;
    private int edad;
    private String idSexo;
    private String sexo;
    private String idEstadoCivil;
    private String estadoCivil;
    private String idNacionalidad;
    private String nacionalidad;
    private String idTipoIdentificacion;
    private String tipoIdentificacion;

    private String idParentezcoNumero;
    private String parentezcoNumero;
    private String idParentezcoJefes;
    private String parentezcoJefes;
    private int numeroHogar;
    private String idCondicionActividad;
    private String condicionActividad;
    private String idOcupacionOficio;
    private String ocupacionOficio;
    private String idCategoriaOcupacional;
    private String categoriaOcupacional;
    private float ingresoSalario;
    private float ingresoPropio;
    private float otrosIngresos;

    private String idDeficienciaFisica;
    private String deficienciaFisica;
    private String idEnfermedadCronica;
    private String enfermedadCronica;
    private String idAspectosPsicosociales;
    private String aspectosPsicosociales;
    private String idTipoPension;
    private String tipoPension;
    private String idCondicionAseguramiento;
    private String condicionAseguramiento;
    private String idAsistenciaCentrosEnsenanza;
    private String asistenciaCentrosEnsenanza;
    private String idCodigoCentroEnsenanza;
    private String codigoCentroEnsenanza;
    private int ultimoAnoAprobado;
    private String idCicloEnsenanza;
    private String cicloEnsenanza;
    private String idAreaCapacitacion;
    private String areaCapacitacion;

    public Integrante() {
        primerApellido = "";
        segundoApellido = "";
        primerNombre = "";
        segundoNombre = "";
        idSexo = "";
        idNacionalidad = "";
        idEstadoCivil = "";
        fechaNacimiento = "";
        numeroIdentificacion = "";
        idParentezcoNumero = "";
        idParentezcoJefes = "";
        numeroHogar = 0;
        idCondicionActividad = "";
        idOcupacionOficio = "";
        idCategoriaOcupacional = "";
        ingresoSalario = 0;
        ingresoPropio = 0;
        otrosIngresos = 0;
        idDeficienciaFisica ="";
        idEnfermedadCronica = "";
        idAspectosPsicosociales = "";
        idTipoPension = "";
        idCondicionAseguramiento = "";
        idAsistenciaCentrosEnsenanza = "";
        ultimoAnoAprobado = 0;
        idCicloEnsenanza = "";
        idAreaCapacitacion = "";
        idCodigoCentroEnsenanza = "";
        idTipoIdentificacion= "" ;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public boolean getJefeFamilia() {
        return esJefeFamilia;
    }

    public void setJefeFamilia(boolean esJefeFamilia) {
        this.esJefeFamilia = esJefeFamilia;
    }

    public boolean getPrimerJefeFamilia() {
        return esPrimerJefeFamilia;
    }

    public void setPrimerJefeFamilia(boolean esJefeFamilia) {
        this.esPrimerJefeFamilia = esJefeFamilia;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String _PrimerApellido) {
        this.primerApellido = _PrimerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String _SegundoApellido) {
        this.segundoApellido = _SegundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String _PrimerNombre) {
        this.primerNombre = _PrimerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String _SegundoNombre) {
        this.segundoNombre = _SegundoNombre;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(String idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String _Nacionalidad) {
        this.nacionalidad = _Nacionalidad;
    }

    public void setNacionalidad(Opcion opcion) {
        this.nacionalidad = opcion.getDescripcion();
        this.idNacionalidad = opcion.getCodigo();
    }

    public String getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(String idSexo) {
        this.idSexo = idSexo;
        if(Integer.parseInt(idSexo) >= 1) {
            this.sexo = Integrante.getOpcionesSexo().get(Integer.parseInt(idSexo) - 1).getDescripcion();
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String _Sexo) {
        this.sexo = _Sexo;
    }

    public void setSexo(Opcion opcion) {
        this.sexo = opcion.getDescripcion();
        this.idSexo = opcion.getCodigo();
    }

    public String getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(String idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String _EstadoCivil) {
        this.estadoCivil = _EstadoCivil;
    }

    public void setEstadoCivil(Opcion opcion) {
        this.estadoCivil = opcion.getDescripcion();
        this.idEstadoCivil = opcion.getCodigo();
    }

    public String getIdParentezcoNumero() {
        return idParentezcoNumero;
    }

    public void setIdParentezcoNumero(String idParentezcoNumero) {
        this.idParentezcoNumero = idParentezcoNumero;
    }

    public String getParentezcoNumero() {
        return parentezcoNumero;
    }

    public void setParentezcoNumero(String parentezcoNumero) {
        this.parentezcoNumero = parentezcoNumero;
    }

    public void setParentezcoNumero(Opcion opcion) {
        this.parentezcoNumero = opcion.getDescripcion();
        this.idParentezcoNumero = opcion.getCodigo();
    }

    public String getIdParentezcoJefes() {
        return idParentezcoJefes;
    }

    public void setIdParentezcoJefes(String idParentezcoJefes) {
        this.idParentezcoJefes = idParentezcoJefes;
    }

    public String getParentezcoJefes() {
        return parentezcoJefes;
    }

    public void setParentezcoJefes(String parentezcoJefe) {
        this.parentezcoJefes = parentezcoJefe;
    }

    public void setParentezcoJefes(Opcion opcion) {
        this.parentezcoJefes = opcion.getDescripcion();
        this.idParentezcoJefes = opcion.getCodigo();
    }

    public int getNumeroHogar() {
        return numeroHogar;
    }

    public void setNumeroHogar(int numeroHogar) {
        this.numeroHogar = numeroHogar;
    }

    public String getIdCondicionActividad() {
        return idCondicionActividad;
    }

    public void setIdCondicionActividad(String _idCondicionActividad) {
        this.idCondicionActividad = _idCondicionActividad;
    }

    public String getCondicionActividad() {
        return condicionActividad;
    }

    public void setCondicionActividad(String _CondicionActividad) {
        this.condicionActividad = _CondicionActividad;
    }

    public void setCondicionActividad(Opcion opcion) {
        this.condicionActividad = opcion.getDescripcion();
        this.idCondicionActividad = opcion.getCodigo();
    }

    public String getIdOcupacionOficio() {
        return idOcupacionOficio;
    }

    public void setIdOcupacionOficio(String idOcupacionOficio) {
        this.idOcupacionOficio = idOcupacionOficio;
    }

    public String getOcupacionOficio() {
        return ocupacionOficio;
    }

    public void setOcupacionOficio(String ocupacionOficio) {
        this.ocupacionOficio = ocupacionOficio;
    }

    public void setOcupacionOficio(Opcion opcion) {
        this.ocupacionOficio = opcion.getDescripcion();
        this.idOcupacionOficio = opcion.getCodigo();
    }

    public String getIdCategoriaOcupacional() {
        return idCategoriaOcupacional;
    }

    public void setIdCategoriaOcupacional(String idCategoriaOcupacional) {
        this.idCategoriaOcupacional = idCategoriaOcupacional;
    }

    public String getCategoriaOcupacional() {
        return categoriaOcupacional;
    }

    public void setCategoriaOcupacional(String _CategoriaOcupacional) {
        this.categoriaOcupacional = _CategoriaOcupacional;
    }

    public void setCategoriaOcupacional(Opcion opcion) {
        this.categoriaOcupacional = opcion.getDescripcion();
        this.idCategoriaOcupacional = opcion.getCodigo();
    }

    public float getIngresoSalario() {
        return ingresoSalario;
    }

    public void setIngresoSalario(float ingresoSalario) {
        this.ingresoSalario = ingresoSalario;
    }

    public float getIngresoPropio() {
        return ingresoPropio;
    }

    public void setIngresoPropio(float ingresoPropio) {
        this.ingresoPropio = ingresoPropio;
    }

    public float getOtrosIngresos() {
        return otrosIngresos;
    }

    public void setOtrosIngresos(float _OtrosIngresos) {
        this.otrosIngresos = _OtrosIngresos;
    }

    public String getIdDeficienciaFisica() {
        return idDeficienciaFisica;
    }

    public void setIdDeficienciaFisica(String idDeficienciaFisica) {
        this.idDeficienciaFisica = idDeficienciaFisica;
    }

    public String getDeficienciaFisica() {
        return deficienciaFisica;
    }

    public void setDeficienciaFisica(String deficienciaFisica) {
        this.deficienciaFisica = deficienciaFisica;
    }

    public void setDeficienciaFisica(Opcion opcion) {
        this.deficienciaFisica = opcion.getDescripcion();
        this.idDeficienciaFisica = opcion.getCodigo();
    }

    public String getIdEnfermedadCronica() {
        return idEnfermedadCronica;
    }

    public void setIdEnfermedadCronica(String idEnfermedadCronica) {
        this.idEnfermedadCronica = idEnfermedadCronica;
    }

    public String getEnfermedadCronica() {
        return enfermedadCronica;
    }

    public void setEnfermedadCronica(String enfermedadCronica) {
        this.enfermedadCronica = enfermedadCronica;
    }

    public void setEnfermedadCronica(Opcion opcion) {
        this.enfermedadCronica = opcion.getDescripcion();
        this.idEnfermedadCronica = opcion.getCodigo();
    }

    public String getIdAspectosPsicosociales() {
        return idAspectosPsicosociales;
    }

    public void setIdAspectosPsicosociales(String idAspectosPsicosociales) {
        this.idAspectosPsicosociales = idAspectosPsicosociales;
    }

    public String getAspectosPsicosociales() {
        return aspectosPsicosociales;
    }

    public void setAspectosPsicosociales(String aspectosPsicosociales) {
        this.aspectosPsicosociales = aspectosPsicosociales;
    }

    public void setAspectosPsicosociales(Opcion opcion) {
        this.aspectosPsicosociales = opcion.getDescripcion();
        this.idAspectosPsicosociales = opcion.getCodigo();
    }

    public String getIdTipoPension() {
        return idTipoPension;
    }

    public void setIdTipoPension(String idTipoPension) {
        this.idTipoPension = idTipoPension;
    }

    public String getTipoPension() {
        return tipoPension;
    }

    public void setTipoPension(String tipoPension) {
        this.tipoPension = tipoPension;
    }

    public void setTipoPension(Opcion opcion) {
        this.tipoPension = opcion.getDescripcion();
        this.idTipoPension = opcion.getCodigo();
    }

    public String getIdCondicionAseguramiento() {
        return idCondicionAseguramiento;
    }

    public void setIdCondicionAseguramiento(String idCondicionAseguramiento) {
        this.idCondicionAseguramiento = idCondicionAseguramiento;
    }

    public String getCondicionAseguramiento() {
        return condicionAseguramiento;
    }

    public void setCondicionAseguramiento(String condicionAseguramiento) {
        this.condicionAseguramiento = condicionAseguramiento;
    }

    public void setCondicionAseguramiento(Opcion opcion) {
        this.condicionAseguramiento = opcion.getDescripcion();
        this.idCondicionAseguramiento = opcion.getCodigo();
    }

    public String getIdAsistenciaCentrosEnsenanza() {
        return idAsistenciaCentrosEnsenanza;
    }

    public void setIdAsistenciaCentrosEnsenanza(String idAsistenciaCentrosEnsenanza) {
        this.idAsistenciaCentrosEnsenanza = idAsistenciaCentrosEnsenanza;
    }

    public String getAsistenciaCentrosEnsenanza() {
        return asistenciaCentrosEnsenanza;
    }

    public void setAsistenciaCentrosEnsenanza(String asistenciaCentrosEnsenanza) {
        this.asistenciaCentrosEnsenanza = asistenciaCentrosEnsenanza;
    }

    public void setAsistenciaCentrosEnsenanza(Opcion opcion) {
        this.asistenciaCentrosEnsenanza = opcion.getDescripcion();
        this.idAsistenciaCentrosEnsenanza = opcion.getCodigo();
    }

    public int getUltimoAnoAprobado() {
        return ultimoAnoAprobado;
    }

    public void setUltimoAnoAprobado(int ultimoAnoAprobado) {
        this.ultimoAnoAprobado = ultimoAnoAprobado;
    }

    public String getIdCicloEnsenanza() {
        return idCicloEnsenanza;
    }

    public void setIdCicloEnsenanza(String idCicloEnsenanza) {
        this.idCicloEnsenanza = idCicloEnsenanza;
    }

    public String getCicloEnsenanza() {
        return cicloEnsenanza;
    }

    public void setCicloEnsenanza(String cicloEnsenanza) {
        this.cicloEnsenanza = cicloEnsenanza;
    }

    public void setCicloEnsenanza(Opcion opcion) {
        this.cicloEnsenanza = opcion.getDescripcion();
        this.idCicloEnsenanza = opcion.getCodigo();
    }

    public String getIdAreaCapacitacion() {
        return idAreaCapacitacion;
    }

    public void setIdAreaCapacitacion(String idAreaCapacitacion) {
        this.idAreaCapacitacion = idAreaCapacitacion;
    }

    public String getAreaCapacitacion() {
        return areaCapacitacion;
    }

    public void setAreaCapacitacion(String areaCapacitacion) {
        this.areaCapacitacion = areaCapacitacion;
    }

    public void setAreaCapacitacion(Opcion opcion) {
        this.areaCapacitacion = opcion.getDescripcion();
        this.idAreaCapacitacion = opcion.getCodigo();
    }


    public String getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(String idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setTipoIdentificacion(Opcion opcion) {
        this.idTipoIdentificacion = opcion.getCodigo();
        this.tipoIdentificacion = opcion.getDescripcion();
    }

    public String getIdCodigoCentroEnsenanza() {
        return idCodigoCentroEnsenanza;
    }

    public void setIdCodigoCentroEnsenanza(String idCodigoCentroEnsenanza) {
        this.idCodigoCentroEnsenanza = idCodigoCentroEnsenanza;
    }

    public String getCodigoCentroEnsenanza() {
        return codigoCentroEnsenanza;
    }

    public void setCodigoCentroEnsenanza(String codigoCentroEnsenanza) {
        this.codigoCentroEnsenanza = codigoCentroEnsenanza;
    }

    public void setCodigoCentroEnsenanza(Opcion opcion) {
        this.idCodigoCentroEnsenanza = opcion.getCodigo();
        this.codigoCentroEnsenanza = opcion.getDescripcion();
    }

    public String getNombreCompleto() {
        return primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
    }

    public void linkData() {
        try {
            if(!idTipoIdentificacion.isEmpty()) {
                tipoIdentificacion = TiposIdentificacion.find(TiposIdentificacion.class, StringUtil.toSQLName("sipp14_codigo_tipoid") + "= ?", idTipoIdentificacion).get(0).getSipp14_descrip_tipoid();
            }
            if(!idSexo.isEmpty()) {
                sexo = sexos[Integer.parseInt(idSexo) - 1];
            }
            if(!idEstadoCivil.isEmpty()) {
                estadoCivil = estadosCivil[Integer.parseInt(idEstadoCivil) - 1];
            }
            if(!idNacionalidad.isEmpty()) {
                nacionalidad = nacionalidades[Integer.parseInt(idNacionalidad) - 1];
            }
            if(!fechaNacimiento.isEmpty()) {
                String[] fechaSplit = fechaNacimiento.split("/");
                fechaNacimiento = fechaSplit[2]+"/"+fechaSplit[1]+"/"+fechaSplit[0];
            }
            if(!idCondicionActividad.isEmpty()) {
                condicionActividad = condicionesActividad[Integer.parseInt(idCondicionActividad) - 1];
            }
            if(!idOcupacionOficio.isEmpty()) {
                ocupacionOficio = Oficios.find(Oficios.class, StringUtil.toSQLName("sipp13_codigo_oficio") + "= ?", idOcupacionOficio).get(0).getSipp13_descrip_oficio();
            }
            if(!idCategoriaOcupacional.isEmpty()) {
                categoriaOcupacional = categoriasOcupacion[Integer.parseInt(idCategoriaOcupacional) - 1];
            }
            if(!idDeficienciaFisica.isEmpty()) {
                deficienciaFisica = deficiencias[Integer.parseInt(idDeficienciaFisica) - 1];
            }
            if(!idEnfermedadCronica.isEmpty()) {
                enfermedadCronica = enfermedadesCronica[Integer.parseInt(idEnfermedadCronica) - 1];
            }
            if(!idAspectosPsicosociales.isEmpty()) {
                aspectosPsicosociales = listaAspectosPsicosociales[Integer.parseInt(idAspectosPsicosociales) - 1];
            }
            if(!idTipoPension.isEmpty() && !idTipoPension.equals("0") ) {
                tipoPension = tiposPension[Integer.parseInt(idTipoPension) - 1];
            }
            if(!idCondicionAseguramiento.isEmpty()) {
                condicionAseguramiento = condicionesAseguramiento[Integer.parseInt(idCondicionAseguramiento) - 1];
            }
            if(!idAsistenciaCentrosEnsenanza.isEmpty()) {
                asistenciaCentrosEnsenanza = asistenciasCentros[Integer.parseInt(idAsistenciaCentrosEnsenanza) - 1];
            }
            if(!idCicloEnsenanza.isEmpty()) {
                cicloEnsenanza = ciclosEnsenanaza[Integer.parseInt(idCicloEnsenanza) - 1];
            }
            if(!idCodigoCentroEnsenanza.isEmpty()) {
                codigoCentroEnsenanza = CentrosEducativos.find(CentrosEducativos.class, StringUtil.toSQLName("sipp21CodCeneduc") + "= ?", idCodigoCentroEnsenanza).get(0).getSipp21_des_ceneduc();
            }
            if(!idAreaCapacitacion.isEmpty() && !idAreaCapacitacion.equals("0")) {
                areaCapacitacion = areasCapacitacion[Integer.parseInt(idAreaCapacitacion) - 1];
            }
            if(!idParentezcoNumero.isEmpty()) {
                parentezcoNumero = parentezcosNumero[Integer.parseInt(idParentezcoNumero) - 1];
            }
            if(!idParentezcoJefes.isEmpty()) {
                parentezcoJefes = parentezcosJefes[Integer.parseInt(idParentezcoJefes) - 1];
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static String linkParentesco(String idParentesco){
        if(!idParentesco.isEmpty()) {
            return parentezcosNumero[Integer.parseInt(idParentesco) - 1];
        }
        return "";
    }

    static String[] sexos = new String[]{"Masculino", "Femenino"};
    public static List<Opcion> getOpcionesSexo() {
        return Opcion.transformStringArray(sexos);
    }

    static String[] estadosCivil = new String[]{"Casado (a)", "Soltero (a)", "Divorciado (a)", "Separado (a)", "Viudo (a)", "Unión Libre"};
    public static List<Opcion> getOpcionesEstadoCivil() {
        return Opcion.transformStringArray(estadosCivil);
    }

    static String[] nacionalidades= new String[]{"Costarricense", "Nicaraguense", "Otros centroamericanos", "Otros latinoamericanos", "Resto del Mundo"};
    public static List<Opcion> getOpcionesNacionalidad() {
        return Opcion.transformStringArray(nacionalidades);
    }
    static String[] parentezcosNumero = new String[]{"Jefe (a)", "Cónyuge o pareja", "Hijo (a)", "Padre, madre, suegro (a)", "Yerno, nuera", "Nieto (a)", "Abuelo (a)", "Hermano (a), cuñado (a)", "Otros Miembros"};
    public static List<Opcion> getOpcionesParentezcoNumero(boolean esMiembro) {
        if(esMiembro){
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("2", "Cónyuge o pareja"));
            result.add(new Opcion("3", "Hijo (a)"));
            result.add(new Opcion("4", "Padre, madre, suegro (a)"));
            result.add(new Opcion("5", "Yerno, nuera"));
            result.add(new Opcion("6", "Nieto (a)"));
            result.add(new Opcion("7", "Abuelo (a)"));
            result.add(new Opcion("8", "Hermano (a), cuñado (a)"));
            result.add(new Opcion("9", "Otros Miembros"));
            return result;
        }
        return Opcion.transformStringArray(parentezcosNumero);
    }

    static String[] parentezcosJefes = new String[]{"Jefe (a)", "Cónyuge o pareja", "Hijo (a)", "Padre, madre, suegro (a)", "Yerno, nuera", "Nieto (a)", "Abuelo (a)", "Hermano (a), cuñado (a)", "Otros Miembros"};
    public static List<Opcion> getOpcionesParentezcoJefes(boolean esMiembro) {
        if(esMiembro){
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("2", "Cónyuge o pareja"));
            result.add(new Opcion("3", "Hijo (a)"));
            result.add(new Opcion("4", "Padre, madre, suegro (a)"));
            result.add(new Opcion("5", "Yerno, nuera"));
            result.add(new Opcion("6", "Nieto (a)"));
            result.add(new Opcion("7", "Abuelo (a)"));
            result.add(new Opcion("8", "Hermano (a), cuñado (a)"));
            result.add(new Opcion("9", "Otros Miembros"));
            return result;
        }
        return Opcion.transformStringArray(parentezcosJefes);
    }

    static String[] condicionesActividad = new String[]{"Permanente", "Ocasional", "Estacional", "Desempleado (a)", "Oficios del hogar (ama de casa)", "Estudiante", "Pensionado (a)", "Rentista", "Otros"};
    public static List<Opcion> getOpcionesCondicionActividad() {
        return Opcion.transformStringArray(condicionesActividad);
    }

    static String[] categoriasOcupacion = new String[]{"Trabajo no remunerado", "Asalariado (a) sector público", "Asalariado (a) sector privado", "Empleada (o) Domestica (o)", "Cuenta Propia", "Patrono"};
    public static List<Opcion> getOpcionesCategoriaOcupacional() {
        return Opcion.transformStringArray(categoriasOcupacion);
    }

    static String[] deficiencias = new String[]{"Deficiencia visual", "Deficiencia auditiva", "Retraso mental", "Enfermedad mental",
            "Deficiencia en sistema músculo-esquelético y/o sistema nervioso", "Deficiencia en sistema respiratorio y/o sistema circulatorio",
            "Otras deficiencias", "Deficiencias múltiples", "No tiene"};
    public static List<Opcion> getOpcionesDeficiencia() {
        return Opcion.transformStringArray(deficiencias);
    }

    static String[] enfermedadesCronica = new String[]{"Si", "No"};
    public static List<Opcion> getOpcionesEnfermedadCronica() {
        return Opcion.transformStringArray(enfermedadesCronica);
    }

    static String[] listaAspectosPsicosociales= new String[]{"Agresión o abuso", "Abuso Sexual", "Adicción al alcohol u otras drogas", "Abandono", "Otro", "Dos o más de los anteriores", "No presenta"};
    public static List<Opcion> getOpcionesAspectosPsicosociales() {
        return Opcion.transformStringArray(listaAspectosPsicosociales);
    }


    static String[] tiposPension = new String[]{"Invalidez, Vejez y Muerte", "Magisterio, Poder Judicial, Hacienda", "Régimen No Contributivo", "Parálisis Cerebral Profunda", "Otra"};
    public static List<Opcion> getOpcionesTipoPension() {
        return Opcion.transformStringArray(tiposPension);
    }

    static String[] condicionesAseguramiento = new String[]{"Asalariado", "Cuenta propia (Seguro Voluntario)", "Pensionado Régimen IVM", "Pensionado Régimen Magisterio, Poder Judicial, Hacienda",
            "Pensionado Régimen No Contributivo", "Familiar de asegurado directo", "Familiar de pensionado", "Estado", "Por convenio", "Otras formas", "No asegurado"};
    public static List<Opcion> getOpcionesCondicionAseguramiento() {
        return Opcion.transformStringArray(condicionesAseguramiento);
    }

    static String[] asistenciasCentros = new String[]{"Educación Regular", "Educación Especial", "No Asiste"};
    public static List<Opcion> getOpcionesAsistenciaCentros(String idCondicionActividad) {
        if(idCondicionActividad.equals("6")){
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("1", "Educación Regular"));
            result.add(new Opcion("2", "Educación Especial"));
            return result;
        }
        return Opcion.transformStringArray(asistenciasCentros);
    }

    static String[] ciclosEnsenanaza = new String[]{"Ninguno", "Enseñanza Especial", "Ciclo de Transición", "Primaria", "Secundaria Académica", "Secundaria Técnica", "Para-Universitaria", "Universitaria"};
    public static List<Opcion> getOpcionesCicloEnsenanza() {
        return Opcion.transformStringArray(ciclosEnsenanaza);
    }

    static String[] areasCapacitacion = new String[]{"Agropecuario", "Artesanía", "Comercio y Servicios", "Electricidad", "Imágen y Video", "Industria Alimentaria", "Industria Gráfica", "Industria de la madera y plástico", "Mecánica de Vehículos",
            "Metalmecánica", "Náuticapesquero", "Informática", "Textil y Confección de Ropa", "Turismo", "Idiomas", "Otro", "Ninguno"};
    public static List<Opcion> getOpcionesAreaCapacitacion() {
        return Opcion.transformStringArray(areasCapacitacion);
    }

    public static List<Opcion> getOpcionesCodigoOficio() {
        Oficios db = new Oficios();
        List<Oficios> list = db.listAll(Oficios.class);
        return Opcion.transformOficios(list);
    }

    public static List<Opcion> getOpcionesTipoIdentificacion() {
        TiposIdentificacion db = new TiposIdentificacion();
        List<TiposIdentificacion> list = db.listAll(TiposIdentificacion.class);
        return Opcion.transformTipoIdentificacion(list);
    }

    public static List<Opcion> getOpcionesCentrosEnsenanza() {
        CentrosEducativos db = new CentrosEducativos();
        List<CentrosEducativos> list = db.listAll(CentrosEducativos.class);
        return Opcion.transformCentrosEnsenanza(list);
    }

    public static Integrante getPersonaRegistro(String identificacion) {
        RegistroCivil db = new RegistroCivil();
        List<RegistroCivil> list = db.find(RegistroCivil.class, "cedula = ?", identificacion);
        Integrante integrante = new Integrante();

        if(list.size() > 0) {
            RegistroCivil rc = list.get(0);

            integrante.setPrimerApellido(rc.getP_apellido());
            integrante.setSegundoApellido(rc.getS_apellido());
            String[] nombre = rc.getNombre().split(" ");
            integrante.setPrimerNombre(nombre[0]);
            if(nombre.length > 1){
                integrante.setSegundoNombre(nombre[1]);
            }
            integrante.setFechaNacimiento(getDateString(formatDate(rc.getF_suceso())));
            integrante.setEdad(getEdadPorFecha(formatDate(rc.getF_suceso())));
            integrante.setIdSexo(rc.getSexo().replace(".0",""));
        }

        return integrante;
    }

    public static int getEdadPorFecha (String fechaNacimiento) {

        if(fechaNacimiento.equals("")){
            fechaNacimiento = "12/12/1999";
        }
        String[] fecha = fechaNacimiento.split("/");

        Calendar dob = Calendar.getInstance();
        dob.set(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));

        int years = 0;
        int months = 0;
        int days = 0;
        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(dob.getTime().getTime());
        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);
        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH);
        //Get difference between months
        months = currMonth - birthMonth;
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0)
        {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
            years--;
            months = 11;
        }
        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else
        {
            days = 0;
            if (months == 12)
            {
                years++;
                months = 0;
            }
        }

        return years;

    }


    public static String getDateString (String fechaNacimiento) {

        String[] fecha = fechaNacimiento.split(" ");

        StringBuilder sb = new StringBuilder();
        sb.append(fecha[2]);
        sb.append("/");
        sb.append(fecha[1]);
        sb.append("/");
        sb.append(fecha[5]);

        return sb.toString();
    }

    public static String formatDate (Date fechaNacimiento) {

        String fecha = "";
        fecha = fechaNacimiento.toString().replace("Jan", "01");
        fecha = fecha.toString().replace("Feb", "02");
        fecha = fecha.toString().replace("Mar", "03");
        fecha = fecha.toString().replace("Apr", "04");
        fecha = fecha.toString().replace("May", "05");
        fecha = fecha.toString().replace("Jun", "06");
        fecha = fecha.toString().replace("Jul", "07");
        fecha = fecha.toString().replace("Aug", "08");
        fecha = fecha.toString().replace("Sep", "09");
        fecha = fecha.toString().replace("Oct", "10");
        fecha = fecha.toString().replace("Nov", "11");
        fecha = fecha.toString().replace("Dec", "12");

        return fecha;
    }


}
