package cr.bamboo.fisdigital.data.ftp.input;

import android.content.Context;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cr.bamboo.fisdigital.data.dbmodel.Barrio;
import cr.bamboo.fisdigital.data.dbmodel.Cantones;
import cr.bamboo.fisdigital.data.dbmodel.Caserio;
import cr.bamboo.fisdigital.data.dbmodel.Cedes;
import cr.bamboo.fisdigital.data.dbmodel.CentrosEducativos;
import cr.bamboo.fisdigital.data.dbmodel.CodEspecial;
import cr.bamboo.fisdigital.data.dbmodel.Distrito;
import cr.bamboo.fisdigital.data.dbmodel.Gerencias;
import cr.bamboo.fisdigital.data.dbmodel.Oficios;
import cr.bamboo.fisdigital.data.dbmodel.Provincias;
import cr.bamboo.fisdigital.data.dbmodel.RegMIDEPLAN;
import cr.bamboo.fisdigital.data.dbmodel.RegistroCivil;
import cr.bamboo.fisdigital.data.dbmodel.TiposIdentificacion;
import cr.bamboo.fisdigital.data.dbmodel.Usuario;
import cr.bamboo.fisdigital.data.ftp.common.FTPInfo;

/**
 * Created by jbeita on 11/15/15.
 *
 * FTPDownloader descarga los datos de los catálogos y los almacena
 * en la base de datos
 */
public class FTPDownloader {
    FTPClient ftp = null;
    Context context = null;

    String host = FTPInfo.host;
    String user = FTPInfo.user;
    String pwd = FTPInfo.pwd;

    public FTPDownloader(Context _context) throws Exception {
        context = _context;
        ftp = new FTPClient();
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }


    InputStream inputStream = null;

    private enum Entity {
        provincia,canton,distrito,caserio,barrio,cede,gerencia,regMidePlan, registroCivil,
        codigosEspeciales, centrosEducacion, tiposIdentificacion, oficios, usuarios;
    }
    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".txt";
    public String returnFileContent(String remoteFilePath,String _entity){
        String latest= "";
        try {
            this.inputStream = this.ftp.retrieveFileStream(remoteFilePath);
            FileOutputStream fos;

            fos = context.openFileOutput(_entity, Context.MODE_PRIVATE);
            BufferedReader r = new BufferedReader(new InputStreamReader(this.inputStream));
            String line;

            try {
                while ((line = r.readLine()) != null) {
                    latest = line;
                }
            } catch (IOException e) {
                System.out.println("problem with readline in inputStreamToString function");
            }

            System.out.println("FTP File downloaded successfully2" + remoteFilePath);
            this.inputStream.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latest;
    }
    public void downloadFile(String remoteFilePath, String _entity) {

        try {
            this.inputStream = this.ftp.retrieveFileStream(remoteFilePath);
            FileOutputStream fos;



            fos = context.openFileOutput(_entity, Context.MODE_PRIVATE);
            Entity ent = Entity.valueOf(_entity);
            BufferedReader r = new BufferedReader(new InputStreamReader(this.inputStream, "ISO-8859-1"));
            String line;
            String[] data;

            try {


                while ((line = r.readLine()) != null) {
                    switch (ent) {
                        case provincia:
                            Provincias pr = new Provincias();
                            data = line.split("~");
                            pr.setCodigo(data[0].trim());
                            pr.setDescripcion(data[1]);
                            pr.saveOnBackground();
                            break;
                        case canton:
                            try {
                                Cantones canton = new Cantones();
                                data = line.split("~");
                                String codigoProvincia = data[0].trim();
                                String codigoCanton = data[1].trim();
                                String codigoReg = data[3].trim();
                                canton.setSipp01_codigo_provincia(codigoProvincia);
                                canton.setSipp02_codigo_canton(codigoCanton);
                                canton.setSipp02_descripcion_canton(data[2]);
                                canton.setSipp08_codigo_regmidep(codigoReg);
                                canton.saveOnBackground();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case distrito:
                            Distrito distrito = new Distrito();
                            data = line.split("~");
                            distrito.setSipp01_codigo_provincia(data[0].trim());
                            distrito.setSipp02_codigo_canton(data[1].trim());
                            distrito.setSipp03_codigo_distrito(data[2].trim());
                            distrito.setSipp06_codigo_gerencia(data[3].trim());
                            distrito.setSipp07_codigo_cedes(data[4].trim());
                            distrito.setSipp03_descripcion_distrito(data[5]);
                            distrito.saveOnBackground();
                            break;
                        case caserio:
                            try {
                                Caserio caserio = new Caserio();
                                data = line.split("~");
                                caserio.setSipp01_codigo_provincia(Integer.parseInt(data[0].trim()));
                                caserio.setSipp02_codigo_canton(Integer.parseInt(data[1].trim()));
                                caserio.setSipp03_codigo_distrito(Integer.parseInt(data[2].trim()));
                                caserio.setSipp04_codigo_barrio(data[3]);
                                caserio.setSipp05_codigo_caserio(data[4]);
                                caserio.setSipp05_desc_caserio(data[5]);
                                caserio.saveOnBackground();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case barrio:
                            Barrio barrio = new Barrio();
                            data = line.split("~");
                            barrio.setSipp01_codigo_provincia(Integer.parseInt(data[0].trim()));
                            barrio.setSipp02_codigo_canton(Integer.parseInt(data[1].trim()));
                            barrio.setSipp03_codigo_distrito(Integer.parseInt(data[2].trim()));
                            barrio.setSipp04_codigo_barrio(data[3]);
                            barrio.setSipp04_descripcion_barrio(data[4]);
                            barrio.saveOnBackground();

                            break;
                        case cede:
                            Cedes cede = new Cedes();
                            data = line.split("~");
                            cede.setSipp06_codigo_gerencia(data[0].trim());
                            cede.setSipp07_codigo_cedes(data[1]);
                            cede.setSipp07_descripcion_cedes(data[2]);
                            break;
                        case gerencia:
                            Gerencias gn = new Gerencias();
                            data = line.split("~");
                            gn.setSipp06_codigo_gerencia((data[0]));
                            if(data.length>1) {
                                gn.setSipp06_descripcion_gerencia(data[1]);
                            }
                            gn.saveOnBackground();
                            break;
                        case regMidePlan:
                            RegMIDEPLAN rg = new RegMIDEPLAN();
                            data = line.split("~");
                            rg.setSipp08_codigo_regmidep(data[0].trim());
                            rg.setSipp08_descripcion_midep(data[1]);
                            rg.saveOnBackground();
                            break;
                        case codigosEspeciales:
                            CodEspecial ce = new CodEspecial();
                            data = line.split("~");
                            ce.setSipp22_cod_especial(data[0].trim());
                            ce.setSipp22_des_especial(data[1].trim());
                            ce.saveOnBackground();
                            break;
                        case centrosEducacion:
                            CentrosEducativos centros = new CentrosEducativos();
                            data = line.split("~");
                            centros.setSipp21_cod_ceneduc(data[0].trim());
                            centros.setSipp21_des_ceneduc(data[1].trim());
                            if(data.length > 2) {
                                centros.setSipp01_codigo_provincia(Integer.parseInt(data[2].trim()));
                                centros.setSipp02_codigo_canton(Integer.parseInt(data[3].trim()));
                                centros.setSipp03_codigo_distrito(Integer.parseInt(data[4].trim()));
                            }
                            centros.saveOnBackground();
                            break;
                        case tiposIdentificacion:
                            TiposIdentificacion ti = new TiposIdentificacion();
                            data = line.split("~");
                            ti.setSipp14_codigo_tipoid(data[0].trim());
                            ti.setSipp14_descrip_tipoid(data[1].trim());
                            ti.save();
                            break;
                        case oficios:
                            Oficios of = new Oficios();
                            data = line.split("~");
                            of.setSipp13_codigo_oficio(data[0].trim());
                            of.setSipp13_descrip_oficio(data[1].trim());
                            of.save();
                            break;
                        case registroCivil:
                            RegistroCivil persona = new RegistroCivil();
                            data = line.split("~");
                            String cedula = data[0];
                            String ced = cedula.substring(0,(cedula.length()-2));
                            persona.setCedula("0" + ced);
                            persona.setSexo(data[1]);
                            String dateString = data[2];
                            dateString = formatDate(dateString);
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date startDate = df.parse(dateString);
                                persona.setF_suceso(startDate);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            persona.setP_apellido(data[3]);
                            persona.setS_apellido(data[4]);
                            persona.setNombre(data[5]);
                            persona.saveOnBackground();
                            break;
                        case usuarios:
                            Usuario usuario = new Usuario();
                            data = line.split("~");
                            usuario.setUsuario(data[0].trim());
                            usuario.setGerencia(data[1].trim());
                            usuario.setApellido1(data[2].trim());
                            usuario.setApellido2(data[3].trim());
                            usuario.setNombre1(data[4].trim().trim());
                            usuario.setNombre2(data[5].trim());
                            usuario.setUsuarioMovil(Integer.parseInt(data[6].trim()));
                            usuario.setClaveMovil(data[7].trim());
                            usuario.setVector("zSU4IRnri/jt43NRBDnTIA==");
                            usuario.saveOnBackground();
                            break;
                    }

                }
            } catch (IOException e) {
                System.out.println("problem with readline in inputStreamToString function");
            }

            System.out.println("FTP File downloaded successfully2" + remoteFilePath);
            this.inputStream.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String formatDate (String fechaNacimiento) {

        String fecha = "";
        fecha = fechaNacimiento.replace("Jan ", "01");
        fecha = fecha.replace("Feb ", "02");
        fecha = fecha.replace("Mar ", "03");
        fecha = fecha.replace("Apr ", "04");
        fecha = fecha.replace("May ", "05");
        fecha = fecha.replace("Jun ", "06");
        fecha = fecha.replace("Jul ", "07");
        fecha = fecha.replace("Aug ", "08");
        fecha = fecha.replace("Sep ", "09");
        fecha = fecha.replace("Oct ", "10");
        fecha = fecha.replace("Nov ", "11");
        fecha = fecha.replace("Dec ", "12");

        return fecha;
    }


    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
            }
        }
    }

}
