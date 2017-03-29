package cr.bamboo.fisdigital.data.ftp.output;

import com.orm.StringUtil;
import com.orm.SugarRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Fdm001_FichaInformacionSocial;
 import cr.bamboo.fisdigital.data.dbmodel.Fdm002_Familias;
 import cr.bamboo.fisdigital.data.dbmodel.Fdm003_PoblacionObjetivo;
 import cr.bamboo.fisdigital.data.dbmodel.Fdm004_IntegrantesFamilia;
import cr.bamboo.fisdigital.data.dbmodel.Fdm005_FamiliaEspecial;
import cr.bamboo.fisdigital.data.dbmodel.Fdm006_CorreoElectFamilia;
import cr.bamboo.fisdigital.data.dbmodel.Fdm_Observaciones;

/**
 * Created by walter on 03/05/15.
 * DataExporter se encarga de la creacion de los archivos CSV a partir de los datos de la base de datos
 **/
 public class DataExporter {
     public String Fdm001 = "";
     public String Fdm002 = "";
     public String Fdm003 = "";
     public String Fdm004 = "";
     public String Fdm005 = "";
     public String Fdm006 = "";

    List<Long> FISToSync;

     // Initializer
     public DataExporter() {
         Class<?> c = null;
         Field[] fields = null;
         StringBuilder sb = null;

         FISToSync = new ArrayList<Long>();
         List<Long> FamsToSync = new ArrayList<Long>();
         List<Long> PobToSync = new ArrayList<Long>();

         Boolean syncError = false;

         try {

             // Iterate inside this for to get info of every FIS in the DB
             List<Fdm001_FichaInformacionSocial> f001s = Fdm001_FichaInformacionSocial.find(Fdm001_FichaInformacionSocial.class, StringUtil.toSQLName("fdm001_EstadoSincronizacion") + "=1");
             for (int i = 0; i < f001s.size(); i++) {

                 try {
                     Fdm001_FichaInformacionSocial f001 = f001s.get(i);
                     Fdm_Observaciones fdm_observaciones = null;
                     List<Fdm_Observaciones> f_observaciones = Fdm_Observaciones.find(Fdm_Observaciones.class, StringUtil.toSQLName("fdm001IdFichaInformacionSocial") + "=?", String.valueOf(f001.getId()));
                     if(f_observaciones.size() > 0) {
                          fdm_observaciones = f_observaciones.get(0);
                     }
                     // add the fis that have to be syncd
                     FISToSync.add(f001.getId());

                     c = f001.getClass();
                     Class c2 = null;
                     if(fdm_observaciones != null) {
                          c2 = fdm_observaciones.getClass();
                     }

                     sb = new StringBuilder();
                     sb.append(f001.getId());
                     sb.append("~");
                     sb.append((c.getField("fdm005IdDispositivoMovil")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001PuntoGeoreferenciaLat")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001PuntoGeoreferenciaLong")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoGerencia")).get(f001));
                     sb.append("~");
//                     sb.append((c.getField("fdm001CodigoCedes")).get(f001));
                     sb.append("000");
                     sb.append("~");
                     sb.append((c.getField("fdm001NumeroFolio")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoEntrevistador")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoRegioMideplan")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoProvincia")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoCanton")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoDistrito")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoBarrio")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CodigoCaserio")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001Fecha")).get(f001));
                     //sb.append((c.getField("fdm001Fecha")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001Zona")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001DireccionVivienda")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001TipoParedExterior")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_EstadoParedExterior")).get(f001).equals("NT") ? "N" : (c.getField("fdm001_EstadoParedExterior")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001MaterialPiso")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_EstadoPiso")).get(f001).equals("NT") ? "N" : (c.getField("fdm001_EstadoPiso")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_MaterialTecho")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_EstadoTecho")).get(f001).equals("NT") ? "" : (c.getField("fdm001_EstadoTecho")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_CantidadAposentosDormir")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_CantidadOtrosAposentos")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_TipoAbastecimientoAgua")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_TipoEliminacionExcretas")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_DisponibilidadBano")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001_MontoSuministroElectrico")).get(f001));
                     sb.append("~");
                     if(c2 != null) {
                         sb.append((c2.getField("Observaciones")).get(fdm_observaciones));
                     }
                     else{
                         sb.append("");
                     }
                     sb.append("~");
                     sb.append((c.getField("fdm001_NumeroVivienda")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001MontoSuministroAgua")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001RiesgoVivienda")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CieloRaso")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001EstadoCieloRaso")).get(f001).equals("NT") ? "" : (c.getField("fdm001EstadoCieloRaso")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001MedioAbastecimientoAgua")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001DisponiblidadSuministroElectrico")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001MedioEliminacionBasura")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001CantidadCamas")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001FuenteEnergiaCocina")).get(f001));
                     sb.append("~");
                     sb.append((c.getField("fdm001Nis")).get(f001));
                     sb.append("~");
                     sb.append("0");
                     sb.append("##");

                 } catch (IllegalAccessException e1) {
                 } catch (NoSuchFieldException e1i) {
                     Exception exception = e1i;
                 }
                 Fdm001 += sb.toString();
             }

             // fdm002
             List<Fdm002_Familias> f002s = Fdm002_Familias.listAll(Fdm002_Familias.class);
             for (int i = 0; i < f002s.size(); i++) {
                 Fdm002_Familias f002 = f002s.get(i);
                 if (FISToSync.contains(f002.getFdm001_IdFichaInformacionSocial())) {

                     try {
                         FamsToSync.add(f002.getId());
                         c = f002.getClass();
                         sb = new StringBuilder();
                         sb.append(f002.getId());
                         sb.append("~");
                         sb.append((c.getField("Fdm005_IdDispositivoMovil")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm001_IdFichaInformacionSocial")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_ExpedienteActual")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NumeroFamilia")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_TenenciaVivienda")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_TvColor")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Refrigeradora")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Lavadora")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_LoteConstruir")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Finca")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_OtrasConstrucciones")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_VehiculoPersonal")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_VehiculoTrabajo")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Telefono")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NumeroTelefono")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MontoTelefono")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Vhs")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_EsquipoSonido")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NingunBien")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MontoDeuda")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Usuario")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_FechaHora")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_AreaFinca")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_EstadoTenenciaVivienda")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_ObtieneVivienda")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_AnoVehiculoTrabajo")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_AnoVehiculoPersonal")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_TelefonoCelular")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MontoTelefonoCelular")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NumeroTelefonoCelular")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Ducha")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_TanqueAgua")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Microondas")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_Computadora")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MotocicletaTrabajo")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_AnnoMotocicletaTrabajo")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_PangaBote")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_LanchaMotor")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MaquinariaIndustrial")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_MaquinariaAgricolaBasica")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_OtraMaquinariaAgricola")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_GanadoBovino")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CabezasBovino")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_GanadoPorcino")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CabezasPorcino")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_AreaLoteConstruccion")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_ProduccionFinca")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NingunInmueble")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_TelefonoOtro")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_NumeroTelefonoOtro")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CantidadTvColor")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CantidadTelCelular")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CantidadEquipoSonido")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CantidadComputadoras")).get(f002));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_CantVehiculoPersonal")).get(f002));
                         sb.append("~");
                         sb.append("0");
                         sb.append("##");

                     } catch (IllegalAccessException e1) {
                     } catch (NoSuchFieldException e1i) {
                         Exception exception = e1i;
                     }

                     Fdm002 += sb.toString();
                 }
             }

             // fdm004
             List<Fdm004_IntegrantesFamilia> f004s = Fdm004_IntegrantesFamilia.listAll(Fdm004_IntegrantesFamilia.class);
             for (int i = 0; i < f004s.size(); i++) {
                 Fdm004_IntegrantesFamilia f004 = f004s.get(i);
                 if (FamsToSync.contains(f004.getFdm002IdFamilia())) {
                     try {
                         PobToSync.add(f004.getId());
                         c = f004.getClass();
                         sb = new StringBuilder();
                         sb.append(f004.getId());
                         sb.append("~");
                         sb.append((c.getField("Fdm005_IdDispositivoMovil")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_IdPoblacionObjetivo")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_IdFamilia")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_NumeroOrden")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_JefeFamilia")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_ParentezcoJefe")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_RelacionEntreJefes")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_NumeroHogar")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_Usuario")).get(f004));
                         sb.append("~");
                         sb.append((c.getField("Fdm004_FechaHora")).get(f004));
                         sb.append("~");
                         sb.append("0");

                         sb.append("##");

                     } catch (IllegalAccessException e1) {
                     } catch (NoSuchFieldException e1i) {
                         Exception exception = e1i;
                     }

                     Fdm004 += sb.toString();
                 }
             }

             // fdm003
             List<Fdm003_PoblacionObjetivo> f003s = Fdm003_PoblacionObjetivo.listAll(Fdm003_PoblacionObjetivo.class);
             for (int i = 0; i < f003s.size(); i++) {
                 Fdm003_PoblacionObjetivo f003 = f003s.get(i);
                 if (FamsToSync.contains(f003.getIdFamilia())) {
                     try {

                         c = f003.getClass();
                         sb = new StringBuilder();
                         sb.append(f003.getId());
                         sb.append("~");
                         sb.append((c.getField("Fdm005_IdDispositivoMovil")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_CodigoOficio")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_TipoIdentificacion")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_IdPersonal")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_PrimerApellido")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_SegundoApellido")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_PrimerNombre")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_SegundoNombre")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_FechaNacimiento")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_Nacionalidad")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_Sexo")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_EstadoCivil")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_CondicionActividad")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_CategoriaOcupacion")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_IngresoPral")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_OtrosIngresos")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_TipoSeguro")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_TipoPension")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_CondicionSalud")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_AsistenciaCentroEducativo")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_NivelEstudio")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_UltimoAnioAprobado")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_Usuario")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_FechaHora")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_CodigoCentroEnsenanza")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_IngresoCuentaPropia")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_EnfermedadCronica")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_AspectosPsicoSociales")).get(f003));
                         sb.append("~");
                         sb.append((c.getField("Fdm003_AreaCapacitacion")).get(f003));
                         sb.append("~");
                         sb.append("0");
                         sb.append("##");

                     } catch (IllegalAccessException e1) {
                     } catch (NoSuchFieldException e1i) {
                         Exception exception = e1i;
                     }

                     Fdm003 += sb.toString();

                 }
             }


             // fdm005
             List<Fdm005_FamiliaEspecial> f005s = Fdm005_FamiliaEspecial.listAll(Fdm005_FamiliaEspecial.class);
             for (int i = 0; i < f005s.size(); i++) {
                 Fdm005_FamiliaEspecial f005 = f005s.get(i);
                 if (FamsToSync.contains(f005.getFdm002_IdFamilia())) {
                     try {

                         c = f005.getClass();
                         sb = new StringBuilder();
                         sb.append(f005.getId());
                         sb.append("~");
                         sb.append((c.getField("Fdm005_IdDispositivoMovil")).get(f005));
                         sb.append("~");
                         sb.append((c.getField("Fdm002_IdFamilia")).get(f005));
                         sb.append("~");
                         sb.append((c.getField("Fdm007_CodigoEspecial")).get(f005));
                         sb.append("~");
                         sb.append((c.getField("Fdm007_Usuario")).get(f005));
                         sb.append("~");
                         sb.append((c.getField("Fdm007_Fecha")).get(f005));
                         sb.append("~");
                         sb.append("0");
                         sb.append("##");

                     } catch (IllegalAccessException e1) {
                     } catch (NoSuchFieldException e1i) {
                         Exception exception = e1i;
                     }

                     Fdm005 += sb.toString();
                 }

             }


             // fdm006
             List<Fdm006_CorreoElectFamilia> f006s = Fdm006_CorreoElectFamilia.listAll(Fdm006_CorreoElectFamilia.class);
             for (int i = 0; i < f006s.size(); i++) {
                 Fdm006_CorreoElectFamilia f006 = f006s.get(i);
                 if (FamsToSync.contains(f006.getFdm006_IdFamilia())) {
                     try {
                         c = f006.getClass();
                         sb = new StringBuilder();
                         sb.append(f006.getId());
                         sb.append("~");
                         sb.append((c.getField("Fdm006_IdDispositivoMovil")).get(f006));
                         sb.append("~");
                         sb.append((c.getField("Fdm006_IdFamilia")).get(f006));
                         sb.append("~");
                         sb.append((c.getField("Fdm006_Correo")).get(f006));
                         sb.append("~");
                         sb.append("0");
                         sb.append("##");

                     } catch (IllegalAccessException e1) {
                     } catch (NoSuchFieldException e1i) {
                         Exception exception = e1i;
                     }

                     Fdm006 += sb.toString();
                 }
             }
         }
         catch(Exception ex){
             System.out.println(ex.getStackTrace());
             syncError = true;
         }

         if(!syncError) {
             for (int i = 0; i < FISToSync.size(); i++) {
                 FISController.getInstance().syncedFIS(FISToSync.get(i));
             }
         }
     }

    public boolean IsDataToSync(){
        return FISToSync.size() > 0;
    }

     public void clearDataBase(){
         SugarRecord.deleteAll(Fdm001_FichaInformacionSocial.class);
         SugarRecord.deleteAll(Fdm002_Familias.class);
         SugarRecord.deleteAll(Fdm003_PoblacionObjetivo.class);
         SugarRecord.deleteAll(Fdm004_IntegrantesFamilia.class);
         SugarRecord.deleteAll(Fdm005_FamiliaEspecial.class);
         SugarRecord.deleteAll(Fdm006_CorreoElectFamilia.class);
         SugarRecord.deleteAll(Fdm_Observaciones.class);
     }
 }
