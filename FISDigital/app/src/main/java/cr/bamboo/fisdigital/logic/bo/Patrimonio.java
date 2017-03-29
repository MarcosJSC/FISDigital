package cr.bamboo.fisdigital.logic.bo;

import java.util.List;

import cr.bamboo.fisdigital.data.dbmodel.Opcion;

/**
            * Created by JBeita on 22/03/2015.
           */
 public class Patrimonio {

     private String idTenenciaVivienda;
     private String tenenciaVivenda;
     private float montoMensualPropiaHipoteca;
     private String estadoPropiaHpoteca;
     private float montoMensualAquilada;
     private String estadoAlquilada;
     private float alquilarPrecario;
     private String idAdquirioVivienda;
     private String adquirioVivienda;
     private boolean[] tenenciaOtrosBienes;
     private float areaM2Lote;
     private float areaM2Construcciones1;
     private float areaMConstrucciones2;
     private float fincaParcelaArea;
     private String idProduccionPara;
     private String produccionPara;
     private boolean[] otrosBienes;
     private String telefonoResidencial1;
     private float montoTelefonoResidencial1;
     private String telefonoResidencial2;
     private float montoTelefonoResidencial2;
     private String telefonoCelular1;
     private float montoTelefonoCelular1;
     private String telefonoCelular2;
     private float montoTelefonoCelular2;
     private String anoVehiculoPersonal;
     private String anoVehiculoTrabajo;
     private String anoMotocicletaTrabajo;
     private int numeroCabezasBovino;
     private int numeroCabezasPorcino;
     private int idCodigoEspecial;
    private int expedienteActual;
     private String correoElectronico;
    private int cantidadTelefonoCelular;
    private int cantidadTV;
    private int cantidadEquipoSonido;
    private int cantidadComputadoras;
    private int cantidadVehiculoPersonal;
    private String telefonoOtro;


     public Patrimonio() {

         idTenenciaVivienda = "";
         idAdquirioVivienda = "";
         areaM2Lote = 0;
         montoMensualPropiaHipoteca = 0;
         montoMensualAquilada = 0;
         alquilarPrecario = 0;
         areaM2Lote = 0;
         areaM2Construcciones1 = 0;
         fincaParcelaArea = 0;
         idProduccionPara = "";
         tenenciaOtrosBienes = new boolean[4];
         otrosBienes = new boolean[23];
         estadoPropiaHpoteca = "";
         estadoAlquilada = "";
         telefonoResidencial1= "";
         telefonoCelular1 = "";
         anoVehiculoPersonal = "";
         anoVehiculoTrabajo = "";
         anoMotocicletaTrabajo ="";
         numeroCabezasBovino = 0;
         numeroCabezasPorcino = 0;
         expedienteActual = 0;
         tenenciaVivenda = "";
         estadoPropiaHpoteca = "";
         adquirioVivienda = "";
        produccionPara="";
         telefonoResidencial1="";
         telefonoResidencial2="";
         telefonoCelular1 = "";
         telefonoCelular2="";
         telefonoOtro = "0";
     }

     public String getIdTenenciaVivienda() {
         return idTenenciaVivienda;
     }

     public void setIdTenenciaVivienda(String idTenenciaVivienda) {
         this.idTenenciaVivienda = idTenenciaVivienda;
     }

     public String getTenenciaVivenda() {
         return tenenciaVivenda;
     }

     public void setTenenciaVivenda(String tenenciaVivenda) {
         this.tenenciaVivenda = tenenciaVivenda;
     }

     public void setTenenciaVivenda(Opcion opcion) {
         this.idTenenciaVivienda = opcion.getCodigo();
         this.tenenciaVivenda = opcion.getDescripcion();
     }

     public float getMontoMensualPropiaHipoteca() {
         return montoMensualPropiaHipoteca;
     }

     public void setMontoMensualPropiaHipoteca(float montoMensualPropiaHipoteca) {
         this.montoMensualPropiaHipoteca = montoMensualPropiaHipoteca;
     }

    public int getExpedienteActual() {
        return expedienteActual;
    }

    public void setExpedienteActual(int expedienteActual) {
        this.expedienteActual= expedienteActual;
    }

     public String getEstadoPropiaHpoteca() {
         return estadoPropiaHpoteca;
     }

     public void setEstadoPropiaHpoteca(String estadoPropiaHpoteca) {
         this.estadoPropiaHpoteca = estadoPropiaHpoteca;
     }

     public float getMontoMensualAquilada() {
         return montoMensualAquilada;
     }

     public void setMontoMensualAquilada(float montoMensualAquilada) {
         this.montoMensualAquilada = montoMensualAquilada;
     }

     public String getEstadoAlquilada() {
         return estadoAlquilada;
     }

     public void setEstadoAlquilada(String estadoAlquilada) {
         this.estadoAlquilada = estadoAlquilada;
     }

     public float getAlquilarPrecario() {
         return alquilarPrecario;
     }

     public void setAlquilarPrecario(float alquilarPrecario) {
         this.alquilarPrecario = alquilarPrecario;
     }

     public String getIdAdquirioVivienda() {
         return idAdquirioVivienda;
     }

     public void setIdAdquirioVivienda(String idAdquirioVivienda) {
         this.idAdquirioVivienda = idAdquirioVivienda;
     }

     public String getAdquirioVivienda() {
         return adquirioVivienda;
     }

     public void setAdquirioVivienda(String adquirioVivienda) {
         this.adquirioVivienda = adquirioVivienda;
     }

     public void setAdquirioVivienda(Opcion opcion) {
         this.idAdquirioVivienda = opcion.getCodigo();
         this.adquirioVivienda = opcion.getDescripcion();
     }

     public boolean[] getTenenciaOtrosBienes() {
         return tenenciaOtrosBienes;
     }

     public void setTenenciaOtrosBienes(boolean[] tenenciaOtrosBienes) {
         this.tenenciaOtrosBienes = tenenciaOtrosBienes;
     }

     public float getAreaM2Lote() {
         return areaM2Lote;
     }

     public void setAreaM2Lote(float areaM2Lote) {
         this.areaM2Lote = areaM2Lote;
     }

     public float getAreaM2Construcciones1() {
         return areaM2Construcciones1;
     }

     public void setAreaM2Construcciones1(float areaM2Construcciones1) {
         this.areaM2Construcciones1 = areaM2Construcciones1;
     }

     public float getAreaMConstrucciones2() {
         return areaMConstrucciones2;
     }

     public void setAreaMConstrucciones2(float areaMConstrucciones2) {
         this.areaMConstrucciones2 = areaMConstrucciones2;
     }

     public float getFincaParcelaArea() {
         return fincaParcelaArea;
     }

     public void setFincaParcelaArea(float fincaParcelaArea) {
         this.fincaParcelaArea = fincaParcelaArea;
     }

     public String getIdProduccionPara() {
         return idProduccionPara;
     }

     public void setIdProduccionPara(String idProduccionPara) {
         this.idProduccionPara = idProduccionPara;
     }

     public String getProduccionPara() {
         return produccionPara;
     }

     public void setProduccionPara(String produccionPara) {
         this.produccionPara = produccionPara;
     }

     public void setProduccionPara(Opcion opcion) {
         this.produccionPara = opcion.getDescripcion();
         this.idProduccionPara = opcion.getCodigo();
     }

     public boolean[] getOtrosBienes() {
         return otrosBienes;
     }

     public void setOtrosBienes(boolean[] otrosBienes) {
         this.otrosBienes = otrosBienes;
     }

     public String getTelefonoResidencial1() {
         return telefonoResidencial1;
     }

     public void setTelefonoResidencial1(String telefonoResidencial1) {
         this.telefonoResidencial1 = telefonoResidencial1;
     }

     public float getMontoTelefonoResidencial1() {
         return montoTelefonoResidencial1;
     }

     public void setMontoTelefonoResidencial1(float montoTelefonoResidencial1) {
         this.montoTelefonoResidencial1 = montoTelefonoResidencial1;
     }

     public String getTelefonoResidencial2() {
         return telefonoResidencial2;
     }

     public void setTelefonoResidencial2(String telefonoResidencial2) {
         this.telefonoResidencial2 = telefonoResidencial2;
     }

     public float getMontoTelefonoResidencial2() {
         return montoTelefonoResidencial2;
     }

     public void setMontoTelefonoResidencial2(float montoTelefonoResidencial2) {
         this.montoTelefonoResidencial2 = montoTelefonoResidencial2;
     }

     public String getTelefonoCelular1() {
         return telefonoCelular1;
     }

     public void setTelefonoCelular1(String telefonoCelular1) {
         this.telefonoCelular1 = telefonoCelular1;
     }

     public float getMontoTelefonoCelular1() {
         return montoTelefonoCelular1;
     }

     public void setMontoTelefonoCelular1(float montoTelefonoCelular1) {
         this.montoTelefonoCelular1 = montoTelefonoCelular1;
     }

     public String getTelefonoCelular2() {
         return telefonoCelular2;
     }

     public void setTelefonoCelular2(String telefonoCelular2) {
         this.telefonoCelular2 = telefonoCelular2;
     }

     public float getMontoTelefonoCelular2() {
         return montoTelefonoCelular2;
     }

     public void setMontoTelefonoCelular2(float montoTelefonoCelular2) {
         this.montoTelefonoCelular2 = montoTelefonoCelular2;
     }

     public String getAnoVehiculoPersonal() {
         return anoVehiculoPersonal;
     }

     public void setAnoVehiculoPersonal(String anoVehiculoPersonal) {
         this.anoVehiculoPersonal = anoVehiculoPersonal;
     }

     public String getAnoVehiculoTrabajo() {
         return anoVehiculoTrabajo;
     }

     public void setAnoVehiculoTrabajo(String anoVehiculoTrabajo) {
         this.anoVehiculoTrabajo = anoVehiculoTrabajo;
     }

     public String getAnoMotocicletaTrabajo() {
         return anoMotocicletaTrabajo;
     }

     public void setAnoMotocicletaTrabajo(String anoMotocicletaTrabajo) {
         this.anoMotocicletaTrabajo = anoMotocicletaTrabajo;
     }

     public int getNumeroCabezasBovino() {
         return numeroCabezasBovino;
     }

     public void setNumeroCabezasBovino(int numeroCabezasBovino) {
         this.numeroCabezasBovino = numeroCabezasBovino;
     }

     public int getNumeroCabezasPorcino() {
         return numeroCabezasPorcino;
     }

     public void setNumeroCabezasPorcino(int numeroCabezasPorcino) {
         this.numeroCabezasPorcino = numeroCabezasPorcino;
     }

    public int getCantidadTV() {
        return cantidadTV;
    }

    public void setCantidadTV(int cantidadTV) {
        this.cantidadTV = cantidadTV;
    }

    public int getCantidadEquipoSonido() {
        return cantidadEquipoSonido;
    }

    public void setCantidadEquipoSonido(int cantidadEquipoSonido) {
        this.cantidadEquipoSonido = cantidadEquipoSonido;
    }

    public int getCantidadComputadoras() {
        return cantidadComputadoras;
    }

    public void setCantidadComputadoras(int cantidadComputadoras) {
        this.cantidadComputadoras = cantidadComputadoras;
    }

    public String getTelefonoOtro() {
        return telefonoOtro;
    }

    public void setTelefonoOtro(String telefonoOtro) {
        this.telefonoOtro = telefonoOtro;
    }

    public int getCantidadTelefonoCelular() {
        return cantidadTelefonoCelular;
    }

    public void setCantidadTelefonoCelular(int cantidadTelefonoCelular) {
        this.cantidadTelefonoCelular = cantidadTelefonoCelular;
    }

    public int getCantidadVehiculoPersonal() {
        return cantidadVehiculoPersonal;
    }

    public void setCantidadVehiculoPersonal(int cantidadVehiculoPersonal) {
        this.cantidadVehiculoPersonal = cantidadVehiculoPersonal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico= correoElectronico;
    }

    public void linkData() {
        try {
            if (!idTenenciaVivienda.isEmpty()) {
                tenenciaVivenda = tenenciasVivienda[Integer.parseInt(idTenenciaVivienda) - 1];
            }
            if (!idAdquirioVivienda.isEmpty()) {
                adquirioVivienda = adquirioViviendas[Integer.parseInt(idAdquirioVivienda) - 1];
            }
            if (!idProduccionPara.isEmpty()) {
                produccionPara = produccionesPara[Integer.parseInt(idProduccionPara) - 1];
            }
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }

    static String[] tenenciasVivienda= new String[]{"Propia sin gravámenes", "Propia con hipoteca", "Alquilada", "Construida en terreno prestado",
            "Prestada", "Con limitaciones", "Adjudicada", "Ubicada en precario", "Arrimados", "Otra forma de tenencia"};
     public static List<Opcion> getOpcionesTenenciaVivienda() {
         return Opcion.transformStringArray(tenenciasVivienda);
     }

    static String[] adquirioViviendas = new String[]{"Bono total","Bono parcial", "IMAS, INVU", "Financiamiento", "Donación, herencia", "Recursos propios","Otro"};
     public static List<Opcion> getOpcionesAdquirioVivienda() {
         return Opcion.transformStringArray(adquirioViviendas);
     }

    static String[] produccionesPara = new String[]{"Venta","Autoconsumo", "Forestal", "Dos o más", "No produce"};
     public static List<Opcion> getOpcionesProduccionPara() {
         return Opcion.transformStringArray(produccionesPara);
     }

}
