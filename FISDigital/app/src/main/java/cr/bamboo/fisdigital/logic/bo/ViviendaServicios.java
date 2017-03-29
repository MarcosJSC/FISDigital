package cr.bamboo.fisdigital.logic.bo;

import java.util.ArrayList;
import java.util.List;

import cr.bamboo.fisdigital.data.dbmodel.Opcion;

/**
 * Created by JBeita on 22/03/2015.
 */
public class ViviendaServicios {

    private String idParedesExteriores;
    private String paredesExteriores;
    private String estadoParedes;
    private String idPiso;
    private String piso;
    private String estadoPiso;
    private String idTecho;
    private String techo;
    private String estadoTecho;
    private String idCieloRaso;
    private String cieloRaso;
    private String estadoCieloRaso;
    private int aposentosDormir;
    private int aposentosOtros;
    private int totalAosentos;
    private int numeroCamas;
    private String idFuenteAbastecimientoAgua;
    private String fuenteAbastecimientoAgua;
    private String montoMensualAgua;
    private String numeroNIS;
    private String idMedioAbastecimientoAgua;
    private String medioAbastecimientoAgua;
    private String idDisponibilidadLuzElectrica;
    private String disponibilidadLuzElectrica;
    private String montoMensualLuz;
    private String idDisponibilidadBano;
    private String disponibilidadBano;
    private String idSanitarioConectado;
    private String sanitarioConectado;
    private String idFuenteEnergiaCocina;
    private String fuenteEnergiaCocina;
    private String idMedioEliminacionBasura;
    private String medioEliminacionBasura;


    public ViviendaServicios() {
        idParedesExteriores = "";
        idPiso = "";
        idTecho = "";
        idCieloRaso = "";
        aposentosDormir = 0;
        aposentosOtros = 0;
        estadoParedes = "";
        estadoPiso = "";
        estadoTecho = "";
        estadoCieloRaso = "";
        numeroCamas = 0;
        idFuenteAbastecimientoAgua = "";
        idMedioAbastecimientoAgua = "";
        idDisponibilidadLuzElectrica = "";
        idDisponibilidadBano= "";
        idSanitarioConectado= "";
        idFuenteEnergiaCocina= "";
        idMedioEliminacionBasura= "";
        montoMensualAgua = "";
        montoMensualLuz = "";
        numeroNIS = "";

    }


    public String getIdParedesExteriores() {
        return idParedesExteriores;
    }

    public void setIdParedesExteriores(String idParedesExteriores) {
        this.idParedesExteriores = idParedesExteriores;
    }

    public String getParedesExteriores() {
        return paredesExteriores;
    }

    public void setParedesExteriores(String paredesExteriores) {
        this.paredesExteriores = paredesExteriores;
    }

    public void setParedesExteriores(Opcion paredes) {
        this.idParedesExteriores = paredes.getCodigo();
        this.paredesExteriores = paredes.getDescripcion();
    }

    public String getEstadoParedes() {
        return estadoParedes;
    }

    public void setEstadoParedes(String estadoParedes) {
        this.estadoParedes = estadoParedes;
    }

    public String getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(String idPiso) {
        this.idPiso = idPiso;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setPiso(Opcion piso) {
        this.idPiso = piso.getCodigo();
        this.piso = piso.getDescripcion();
    }

    public String getEstadoPiso() {
        return estadoPiso;
    }

    public void setEstadoPiso(String estadoPiso) {
        this.estadoPiso = estadoPiso;
    }

    public String getIdTecho() {
        return idTecho;
    }

    public void setIdTecho(String idTecho) {
        this.idTecho = idTecho;
    }

    public String getTecho() {
        return techo;
    }

    public void setTecho(String techo) {
        this.techo = techo;
    }

    public void setTecho(Opcion techo) {
        this.idTecho = techo.getCodigo();
        this.techo = techo.getDescripcion();
    }

    public String getEstadoTecho() {
        return estadoTecho;
    }

    public void setEstadoTecho(String estadoTecho) {
        this.estadoTecho = estadoTecho;
    }

    public String getIdCieloRaso() {
        return idCieloRaso;
    }

    public void setIdCieloRaso(String idCieloRaso) {
        this.idCieloRaso = idCieloRaso;
    }

    public String getCieloRaso() {
        return cieloRaso;
    }

    public void setCieloRaso(String cieloRaso) {
        this.cieloRaso = cieloRaso;
    }

    public void setCieloRaso(Opcion cieloRaso) {
        this.idCieloRaso = cieloRaso.getCodigo();
        this.cieloRaso = cieloRaso.getDescripcion();
    }

    public String getEstadoCieloRaso() {
        return estadoCieloRaso;
    }

    public void setEstadoCieloRaso(String estadoCieloRaso) {
        this.estadoCieloRaso = estadoCieloRaso;
    }

    public int getAposentosDormir() {
        return aposentosDormir;
    }

    public void setAposentosDormir(int aposentosDormir) {
        this.aposentosDormir = aposentosDormir;
    }

    public int getAposentosOtros() {
        return aposentosOtros;
    }

    public void setAposentosOtros(int aposentosOtros) {
        this.aposentosOtros = aposentosOtros;
    }

    public int getTotalAosentos() {
        return totalAosentos;
    }

    public void setTotalAosentos(int totalAosentos) {
        this.totalAosentos = totalAosentos;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    public String getIdFuenteAbastecimientoAgua() {
        return idFuenteAbastecimientoAgua;
    }

    public void setIdFuenteAbastecimientoAgua(String idFuenteAbastecimientoAgua) {
        this.idFuenteAbastecimientoAgua = idFuenteAbastecimientoAgua;
    }

    public String getFuenteAbastecimientoAgua() {
        return fuenteAbastecimientoAgua;
    }

    public void setFuenteAbastecimientoAgua(String fuenteAbastecimientoAgua) {
        this.fuenteAbastecimientoAgua = fuenteAbastecimientoAgua;
    }

    public void setFuenteAbastecimientoAgua(Opcion opcion) {
        this.idFuenteAbastecimientoAgua = opcion.getCodigo();
        this.fuenteAbastecimientoAgua= opcion.getDescripcion();
    }

    public String getMontoMensualAgua() {
        return montoMensualAgua;
    }

    public void setMontoMensualAgua(String montoMensualAgua) {
        this.montoMensualAgua = montoMensualAgua;
    }

    public String getNumeroNIS() {
        return numeroNIS;
    }

    public void setNumeroNIS(String numeroNIS) {
        this.numeroNIS = numeroNIS;
    }

    public String getIdMedioAbastecimientoAgua() {
        return idMedioAbastecimientoAgua;
    }

    public void setIdMedioAbastecimientoAgua(String idMedioAbastecimientoAgua) {
        this.idMedioAbastecimientoAgua = idMedioAbastecimientoAgua;
    }

    public String getMedioAbastecimientoAgua() {
        return medioAbastecimientoAgua;
    }

    public void setMedioAbastecimientoAgua(String medioAbastecimientoAgua) {
        this.medioAbastecimientoAgua = medioAbastecimientoAgua;
    }

    public void setMedioAbastecimientoAgua(Opcion opcion) {
        this.idMedioAbastecimientoAgua = opcion.getCodigo();
        this.medioAbastecimientoAgua = opcion.getDescripcion();
    }

    public String getIdDisponibilidadLuzElectrica() {
        return idDisponibilidadLuzElectrica;
    }

    public void setIdDisponibilidadLuzElectrica(String idDisponibilidadLuzElectrica) {
        this.idDisponibilidadLuzElectrica = idDisponibilidadLuzElectrica;
    }

    public String getDisponibilidadLuzElectrica() {
        return disponibilidadLuzElectrica;
    }

    public void setDisponibilidadLuzElectrica(String disponibilidadLuzElectrica) {
        this.disponibilidadLuzElectrica = disponibilidadLuzElectrica;
    }

    public void setDisponibilidadLuzElectrica(Opcion opcion) {
        this.idDisponibilidadLuzElectrica = opcion.getCodigo();
        this.disponibilidadLuzElectrica = opcion.getDescripcion();
    }

    public String getMontoMensualLuz() {
        return montoMensualLuz;
    }

    public void setMontoMensualLuz(String montoMensualLuz) {
        this.montoMensualLuz = montoMensualLuz;
    }

    public String getIdDisponibilidadBano() {
        return idDisponibilidadBano;
    }

    public void setIdDisponibilidadBano(String idDisponibilidadBano) {
        this.idDisponibilidadBano = idDisponibilidadBano;
    }

    public String getDisponibilidadBano() {
        return disponibilidadBano;
    }

    public void setDisponibilidadBano(String disponibilidadBano) {
        this.disponibilidadBano = disponibilidadBano;
    }

    public void setDisponibilidadBano(Opcion opcion) {
        this.idDisponibilidadBano = opcion.getCodigo();
        this.disponibilidadBano = opcion.getDescripcion();
    }

    public String getIdSanitarioConectado() {
        return idSanitarioConectado;
    }

    public void setIdSanitarioConectado(String idSanitarioConectado) {
        this.idSanitarioConectado = idSanitarioConectado;
    }

    public String getSanitarioConectado() {
        return sanitarioConectado;
    }

    public void setSanitarioConectado(String sanitarioConectado) {
        this.sanitarioConectado = sanitarioConectado;
    }

    public void setSanitarioConectado(Opcion opcion) {
        this.idSanitarioConectado = opcion.getCodigo();
        this.sanitarioConectado = opcion.getDescripcion();
    }

    public String getIdFuenteEnergiaCocina() {
        return idFuenteEnergiaCocina;
    }

    public void setIdFuenteEnergiaCocina(String idFuenteEnergiaCocina) {
        this.idFuenteEnergiaCocina = idFuenteEnergiaCocina;
    }

    public String getFuenteEnergiaCocina() {
        return fuenteEnergiaCocina;
    }

    public void setFuenteEnergiaCocina(String fuenteEnergiaCocina) {
        this.fuenteEnergiaCocina = fuenteEnergiaCocina;
    }

    public void setFuenteEnergiaCocina(Opcion opcion) {
        this.idFuenteEnergiaCocina= opcion.getCodigo();
        this.fuenteEnergiaCocina = opcion.getDescripcion();
    }

    public String getIdMedioEliminacionBasura() {
        return idMedioEliminacionBasura;
    }

    public void setIdMedioEliminacionBasura(String idMedioEliminacionBasura) {
        this.idMedioEliminacionBasura = idMedioEliminacionBasura;
    }

    public String getMedioEliminacionBasura() {
        return medioEliminacionBasura;
    }

    public void setMedioEliminacionBasura(String medioEliminacionBasura) {
        this.medioEliminacionBasura = medioEliminacionBasura;
    }

    public void setMedioEliminacionBasura(Opcion opcion) {
        this.idMedioEliminacionBasura = opcion.getCodigo();
        this.medioEliminacionBasura = opcion.getDescripcion();
    }

    public void linkData() {
        try {
            if(!idParedesExteriores.isEmpty()) {
                paredesExteriores = paredes[Integer.parseInt(idParedesExteriores)-1];
            }
            if(!idPiso.isEmpty()) {
                piso = pisos[Integer.parseInt(idPiso)-1];
            }
            if(!idTecho.isEmpty()) {
                techo = techos[Integer.parseInt(idTecho)-1];
            }
            if(!idFuenteAbastecimientoAgua.isEmpty()) {
                fuenteAbastecimientoAgua = fuentesAbastecimiento[Integer.parseInt(idFuenteAbastecimientoAgua)-1];
            }
            if(!idSanitarioConectado.isEmpty()) {
                sanitarioConectado = sanitariosConectado[Integer.parseInt(idSanitarioConectado)-1];
            }
            if(!idDisponibilidadBano.isEmpty()) {
                disponibilidadBano = disponibilidadesBano[Integer.parseInt(idDisponibilidadBano)-1];
            }
            if(!idCieloRaso.isEmpty()) {
                cieloRaso = cieloRasos[Integer.parseInt(idCieloRaso)-1];
            }
            if(!idMedioAbastecimientoAgua.isEmpty()) {
                medioAbastecimientoAgua = mediosAbastecimiento[Integer.parseInt(idMedioAbastecimientoAgua)-1];
            }
            if(!idDisponibilidadLuzElectrica.isEmpty()) {
                disponibilidadLuzElectrica = luzElectrica[Integer.parseInt(idDisponibilidadLuzElectrica)-1];
            }
            if(!idMedioEliminacionBasura.isEmpty()) {
                medioEliminacionBasura = mediosEliminacion[Integer.parseInt(idMedioEliminacionBasura)-1];
            }
            if(!idFuenteEnergiaCocina.isEmpty()) {
                fuenteEnergiaCocina = fuentesEnergia[Integer.parseInt(idFuenteEnergiaCocina)-1];
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static String[] paredes = new String[]{"Bloque, ladrillo, concreto","Prefabricado","Pared forrada (madera, fibrolit, etc.)","Pared sin forro (madera, fibrolit, etc.)", "Zócalo", "Adobe, bahareque, palma, bambú, caña", "Desecho", "No tiene"};
    public static List<Opcion> getOpcionesParedes() {
        return Opcion.transformStringArray(paredes);
    }

    static String[] pisos = new String[]{"Cerámica, mosaico y similares","Cemento","Madera","No tiene (tierra, caña, etc.)"};
    public static List<Opcion> getOpcionesPiso(ViviendaServicios vs) {

        if(!vs.getIdParedesExteriores().isEmpty() && Integer.parseInt(vs.getIdParedesExteriores()) >= 6) {
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("2", "Cemento"));
            result.add(new Opcion("3", "Madera"));
            result.add(new Opcion("4", "No tiene (tierra, caña, etc.)"));
            return result;
        }
        return Opcion.transformStringArray(pisos);
    }

    static String[] techos = new String[]{"Zinc u otra lámina metálica","Entrepiso (cemento, madera, otro)","Palma, caña, teja artesanal","Desecho"};
    public static List<Opcion> getOpcionesTecho(ViviendaServicios vs) {
        if(vs.getIdParedesExteriores().equals("6") || vs.getIdParedesExteriores().equals("7")) {
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("1", "Zinc u otra lámina metálica"));
            result.add(new Opcion("3", "Palma, caña, teja artesanal"));
            result.add(new Opcion("4", "Desecho"));
            return result;
        }
        else if(vs.getIdParedesExteriores().equals("8")) {
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("3", "Palma, caña, teja artesanal"));
            result.add(new Opcion("4", "Desecho"));
            return result;
        }
        else if(vs.getIdCieloRaso().equals("2")){
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("1", "Zinc u otra lámina metálica"));
            result.add(new Opcion("2", "Entrepiso (cemento, madera, otro)"));
            return result;
        }
        return Opcion.transformStringArray(techos);
    }

    static String[] cieloRasos = new String[]{"Tiene","No tiene"};
    public static List<Opcion> getOpcionesCieloRaso(ViviendaServicios vs) {
        return Opcion.transformStringArray(cieloRasos);
    }

    static String[] fuentesAbastecimiento = new String[]{"A y A", "Empresa o cooperativa","Acueducto rural, municipal","Fuente publica","Pozo con bomba","Pozo sin bomba", "Río, quebrada, naciente, lluvia"};
    public static List<Opcion> getOpcionesFuenteAbastecimientoAgua() {
        return Opcion.transformStringArray(fuentesAbastecimiento);
    }

    static String[] mediosAbastecimiento = new String[]{"Tubería dentro de la vivienda" ,"Tubería fuera de la vivienda" ,"No tiene agua por tubería"};
    public static List<Opcion> getOpcionesMedioAbastecimientoAgua() {
        return Opcion.transformStringArray(mediosAbastecimiento);
    }

    static String[] luzElectrica= new String[]{"ICE, CNFL, Empresa Cooperativa","Planta privada","Otra fuente de energía","No tiene"};
    public static List<Opcion> getOpcionesLuzEelectrica() {
        return Opcion.transformStringArray(luzElectrica);
    }

    static String[] disponibilidadesBano = new String[]{"Tiene","No tiene"};
    public static List<Opcion> getOpcionesDisponibilidadBano() {
        return Opcion.transformStringArray(disponibilidadesBano);
    }

    static String[] sanitariosConectado = new String[]{"Cloaca, alcantarillado","Tanque séptico","Letrina o pozo negro","Otros", "No tiene"};
    public static List<Opcion> getOpcionesSanitarioConectado() {
        return Opcion.transformStringArray(sanitariosConectado);
    }

    static String[] fuentesEnergia = new String[]{"Electricidad","Gas","Leña, carbón","Otro", "Ninguna (no cocina)"};
    public static List<Opcion> getOpcionesFuenteEnergiaCocina(ViviendaServicios vs) {
        if(vs.getIdDisponibilidadLuzElectrica().equals("4")) {
            ArrayList<Opcion> result = new ArrayList<Opcion>();
            result.add(new Opcion("2", "Gas"));
            result.add(new Opcion("3", "Leña, carbón"));
            result.add(new Opcion("4", "Otro"));
            result.add(new Opcion("5", "Ninguna (no cocina)"));
            return result;
        }

        return Opcion.transformStringArray(fuentesEnergia);
    }

    static String[] mediosEliminacion = new String[]{"Camión recolector","La botan en hueco y/o entierran","La queman","La botan en lote baldío", "La botan en río, quebrada, mar", "Otro"};
    public static List<Opcion> getOpcionesMedioEliminacionBasura() {
        return Opcion.transformStringArray(mediosEliminacion);
    }


}
