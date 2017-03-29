package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by dianavalerin on 2/23/15.
 */

public class Fdm001_FichaInformacionSocial extends SugarRecord<Fdm001_FichaInformacionSocial> implements Serializable {
    public long fdm001IdFichaInformacionSocial;
    public int fdm001_EstadoSincronizacion; //2 Avtiva sin completar, 1 Activa completa sin sync, 0 Activa completa sync

    public String fdm005IdDispositivoMovil;
    public double fdm001PuntoGeoreferenciaLat;
    public double fdm001PuntoGeoreferenciaLong;
    public String fdm001CodigoGerencia;
    public String fdm001CodigoCedes;
    public int fdm001NumeroFolio;
    public String fdm001CodigoEntrevistador;
    public String fdm001CodigoRegioMideplan;
    public String fdm001CodigoProvincia;
    public String fdm001CodigoCanton;
    public String fdm001CodigoDistrito;
    public String fdm001CodigoBarrio;
    public String fdm001CodigoCaserio;
    public String fdm001Fecha;
    public String fdm001Zona;
    public String fdm001DireccionVivienda;
    public String fdm001TipoParedExterior;
    public String fdm001_EstadoParedExterior;
    public String fdm001MaterialPiso;
    public String fdm001_EstadoPiso;
    public String fdm001_MaterialTecho;
    public String fdm001_EstadoTecho;
    public int fdm001_CantidadAposentosDormir;
    public int fdm001_CantidadOtrosAposentos;
    public String fdm001_TipoAbastecimientoAgua;
    public String fdm001_TipoEliminacionExcretas;
    public String fdm001_DisponibilidadBano;
    public String fdm001_MontoSuministroElectrico;
    public String fdm001_Observaciones;
    public String fdm001_NumeroVivienda;
    public String fdm001MontoSuministroAgua;
    public String fdm001RiesgoVivienda;
    public String fdm001CieloRaso;
    public String fdm001EstadoCieloRaso;
    public String fdm001MedioAbastecimientoAgua;
    public String fdm001DisponiblidadSuministroElectrico;
    public String fdm001MedioEliminacionBasura;
    public int fdm001CantidadCamas;
    public String fdm001FuenteEnergiaCocina;
    public String fdm001Nis;

    public long getFdm001IdFichaInformacionSocial(){
        return fdm001IdFichaInformacionSocial;
    }
    public void setFdm001IdFichaInformacionSocial(long fdm001_IdFichaInformacionSocial) {
        this.fdm001IdFichaInformacionSocial = fdm001_IdFichaInformacionSocial;
    }

    public String getFdm001IdDispositivoMovil(){
        return fdm005IdDispositivoMovil;
    }
    public void setFdm001IdDispositivoMovil(String fdm001IdDispositivoMovil) {
        this.fdm005IdDispositivoMovil = fdm001IdDispositivoMovil;
    }

    public double getFdm001PuntoGeoreferenciaLat(){
        return fdm001PuntoGeoreferenciaLat;
    }
    public void setFdm001_PuntoGeoreferenciaLat(double fdm001_PuntoGeoreferencia) {
        this.fdm001PuntoGeoreferenciaLat = fdm001_PuntoGeoreferencia;
    }

    public double getFdm001PuntoGeoreferenciaLong(){
        return fdm001PuntoGeoreferenciaLong;
    }
    public void setFdm001_PuntoGeoreferenciaLong(double fdm001_PuntoGeoreferencia) {
        this.fdm001PuntoGeoreferenciaLong = fdm001_PuntoGeoreferencia;
    }

    public String getFdm001CodigoGerencia(){
        return fdm001CodigoGerencia;
    }
    public void setFdm001CodigoGerencia(String fdm001CodigoGerencia) {
        this.fdm001CodigoGerencia = fdm001CodigoGerencia;
    }

    public String getFdm001CodigoCedes(){
        return fdm001CodigoCedes;
    }
    public void setFdm001_CodigoCedes(String fdm001_CodigoCedes) {
        this.fdm001CodigoCedes = fdm001_CodigoCedes;
    }

    public int getFdm001NumeroFolio(){
        return fdm001NumeroFolio;
    }
    public void setFdm001_NumeroFolio(int fdm001_NumeroFolio) {
        this.fdm001NumeroFolio = fdm001_NumeroFolio;
    }

    public String getFdm001CodigoEntrevistador(){
        return fdm001CodigoEntrevistador;
    }
    public void setFdm001_CodigoEntrevistador(String fdm001_CodigoEntrevistador) {
        this.fdm001CodigoEntrevistador = fdm001_CodigoEntrevistador;
    }

    public String getFdm001CodigoRegioMideplan(){
        return fdm001CodigoRegioMideplan;
    }
    public void setFdm001_CodigoRegioMideplan(String fdm001_CodigoRegioMideplan) {
        this.fdm001CodigoRegioMideplan = fdm001_CodigoRegioMideplan;
    }

    public String getFdm001CodigoProvincia(){
        return fdm001CodigoProvincia;
    }
    public void setFdm001CodigoProvincia(String fdm001_CodigoProvincia) {
        this.fdm001CodigoProvincia = fdm001_CodigoProvincia;
    }

    public String getFdm001CodigoCanton(){
        return fdm001CodigoCanton;
    }
    public void setFdm001_CodigoCanton(String fdm001_CodigoCanton) {
        this.fdm001CodigoCanton = fdm001_CodigoCanton;
    }

    public String getFdm001CodigoDistrito(){
        return fdm001CodigoDistrito;
    }
    public void setFdm001CodigoDistrito(String fdm001_CodigoDistrito) {
        this.fdm001CodigoDistrito = fdm001_CodigoDistrito;
    }

    public String getFdm001CodigoBarrio(){
        return fdm001CodigoBarrio;
    }
    public void setFdm001CodigoBarrio(String fdm001CodigoBarrio) {
        this.fdm001CodigoBarrio = fdm001CodigoBarrio;
    }

    public String getFdm001CodigoCaserio(){
        return fdm001CodigoCaserio;
    }
    public void setFdm001CodigoCaserio(String fdm001CodigoCaserio) {
        this.fdm001CodigoCaserio = fdm001CodigoCaserio;
    }

    public String getFdm001Fecha(){
        return fdm001Fecha;
    }
    public void setFdm001Fecha(String fdm001Fecha) {
        this.fdm001Fecha = fdm001Fecha;
    }

    public String getFdm001Zona(){
        return fdm001Zona;
    }
    public void setFdm001Zona(String fdm001Zona) {
        this.fdm001Zona = fdm001Zona;
    }

    public String getFdm001DireccionVivienda(){
        return fdm001DireccionVivienda;
    }
    public void setFdm001DireccionVivienda(String fdm001DireccionVivienda) {
        this.fdm001DireccionVivienda = fdm001DireccionVivienda;
    }
    public String getFdm001TipoParedExterior(){
        return fdm001TipoParedExterior;
    }
    public void setFdm001TipoParedExterior(String fdm001TipoParedExterior) {
        this.fdm001TipoParedExterior = fdm001TipoParedExterior;
    }

    public String getFdm001EstadoParedExterior(){
        return fdm001_EstadoParedExterior;
    }
    public void setFdm001EstadoParedExterior(String fdm001EstadoParedExterior) {
        this.fdm001_EstadoParedExterior = fdm001EstadoParedExterior;
    }

    public String getFdm001MaterialPiso(){
        return fdm001MaterialPiso;
    }
    public void setFdm001MaterialPiso(String fdm001MaterialPiso) {
        this.fdm001MaterialPiso = fdm001MaterialPiso;
    }

    public String getFdm001EstadoPiso(){
        return fdm001_EstadoPiso;
    }
    public void setFdm001EstadoPiso(String fdm001EstadoPiso) {
        this.fdm001_EstadoPiso = fdm001EstadoPiso;
    }

    public String getFdm001MaterialTecho(){
        return fdm001_MaterialTecho;
    }
    public void setFdm001MaterialTecho(String fdm001MaterialTecho) {
        this.fdm001_MaterialTecho = fdm001MaterialTecho;
    }

    public String getFdm001EstadoTecho(){
        return fdm001_EstadoTecho;
    }
    public void setFdm001EstadoTecho(String fdm001EstadoTecho) {
        this.fdm001_EstadoTecho = fdm001EstadoTecho;
    }

    public int getFdm001CantidadAposentosDormir(){
        return fdm001_CantidadAposentosDormir;
    }
    public void setFdm001CantidadAposentosDormir(int fdm001CantidadAposentosDormir) {
        this.fdm001_CantidadAposentosDormir = fdm001CantidadAposentosDormir;
    }

    public int getFdm001CantidadOtrosAposentos(){
        return fdm001_CantidadOtrosAposentos;
    }
    public void setFdm001CantidadOtrosAposentos(int fdm001CantidadOtrosAposentos) {
        this.fdm001_CantidadOtrosAposentos = fdm001CantidadOtrosAposentos;
    }

    public String getFdm001TipoAbastecimientoAgua(){
        return fdm001_TipoAbastecimientoAgua;
    }
    public void setFdm001TipoAbastecimientoAgua(String fdm001TipoAbastecimientoAgua) {
        this.fdm001_TipoAbastecimientoAgua = fdm001TipoAbastecimientoAgua;
    }

    public String getFdm001TipoEliminacionExcretas(){
        return fdm001_TipoEliminacionExcretas;
    }
    public void setFdm001TipoEliminacionExcretas(String fdm001TipoEliminacionExcretas) {
        this.fdm001_TipoEliminacionExcretas = fdm001TipoEliminacionExcretas;
    }

    public String getFdm001DisponibilidadBano(){
        return fdm001_DisponibilidadBano;
    }
    public void setFdm001DisponibilidadBano(String fdm001DisponibilidadBano) {
        this.fdm001_DisponibilidadBano = fdm001DisponibilidadBano;
    }

    public String getFdm001MontoSuministroElectrico(){
        return fdm001_MontoSuministroElectrico;
    }
    public void setFdm001MontoSuministroElectrico(String fdm001MontoSuministroElectrico) {
        this.fdm001_MontoSuministroElectrico = fdm001MontoSuministroElectrico;
    }


    public String getFdm001Observaciones() {
        return fdm001_Observaciones;
    }

    public void setFdm001Observaciones(String fdm001Observaciones) {
        this.fdm001_Observaciones = fdm001Observaciones;
    }

    public String getFdm001NumeroVivienda() {
        return fdm001_NumeroVivienda;
    }

    public void setFdm001NumeroVivienda(String fdm001NumeroVivienda) {
        this.fdm001_NumeroVivienda = fdm001NumeroVivienda;
    }

    public String getFdm001MontoSuministroAgua() {
        return fdm001MontoSuministroAgua;
    }

    public void setFdm001MontoSuministroAgua(String fdm001MontoSuministroAgua) {
        this.fdm001MontoSuministroAgua = fdm001MontoSuministroAgua;
    }

    public String getFdm001RiesgoVivienda() {
        return fdm001RiesgoVivienda;
    }

    public void setFdm001RiesgoVivienda(String fdm001RiesgoVivienda) {
        this.fdm001RiesgoVivienda = fdm001RiesgoVivienda;
    }

    public String getFdm001CieloRaso() {
        return fdm001CieloRaso;
    }

    public void setFdm001CieloRaso(String fdm001CieloRaso) {
        this.fdm001CieloRaso = fdm001CieloRaso;
    }

    public String getFdm001EstadoCieloRaso() {
        return fdm001EstadoCieloRaso;
    }

    public void setFdm001EstadoCieloRaso(String fdm001EstadoCieloRaso) {
        this.fdm001EstadoCieloRaso = fdm001EstadoCieloRaso;
    }

    public String getFdm001MedioAbastecimientoAgua() {
        return fdm001MedioAbastecimientoAgua;
    }

    public void setFdm001MedioAbastecimientoAgua(String fdm001MedioAbastecimientoAgua) {
        this.fdm001MedioAbastecimientoAgua = fdm001MedioAbastecimientoAgua;
    }

    public String getFdm001DisponiblidadSuministroElectrico() {
        return fdm001DisponiblidadSuministroElectrico;
    }

    public void setFdm001DisponiblidadSuministroElectrico(String fdm001DisponiblidadSuministroElectrico) {
        this.fdm001DisponiblidadSuministroElectrico = fdm001DisponiblidadSuministroElectrico;
    }

    public String getFdm001MedioEliminacionBasura() {
        return fdm001MedioEliminacionBasura;
    }

    public void setFdm001MedioEliminacionBasura(String fdm001MedioEliminacionBasura) {
        this.fdm001MedioEliminacionBasura = fdm001MedioEliminacionBasura;
    }

    public int getFdm001CantidadCamas() {
        return fdm001CantidadCamas;
    }

    public void setFdm001CantidadCamas(int fdm001CantidadCamas) {
        this.fdm001CantidadCamas = fdm001CantidadCamas;
    }

    public String getFdm001FuenteEnergiaCocina() {
        return fdm001FuenteEnergiaCocina;
    }

    public void setFdm001FuenteEnergiaCocina(String fdm001FuenteEnergiaCocina) {
        this.fdm001FuenteEnergiaCocina = fdm001FuenteEnergiaCocina;
    }

    public long saveOnBackground(){
        save();
        return getId();
    }

    public String getFdm001Nis() {
        return fdm001Nis;
    }

    public void setFdm001Nis(String fdm01Nis) {
        this.fdm001Nis = fdm01Nis;
    }

    public int getFdm001_EstadoSincronizacion() {
        return fdm001_EstadoSincronizacion;
    }

    public void setFdm001_EstadoSincronizacion(int fdm001_EstadoSincronizacion) {
        this.fdm001_EstadoSincronizacion = fdm001_EstadoSincronizacion;
    }
}
