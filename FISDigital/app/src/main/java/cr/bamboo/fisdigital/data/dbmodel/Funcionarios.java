package cr.bamboo.fisdigital.data.dbmodel;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by dianavalerin on 1/20/15.
 */
public class Funcionarios extends SugarRecord<Funcionarios> {
    private String sipp51_codigo;
    private String sipp51_apellido1;
    private String sipp51_apellido2;
    private String sipp51_nombre1;
    private String sipp51_nombre2;
    private int sipp51_entrevista;
    private int sipp51_revisa;
    private int sipp51_supervisa;
    private int sipp51_digita;
    private int sipp51_evalua;
    private int sist05_codigo;
    private Date sipp51_fecha_desde;
    private Date sipp51_fecha_hasta;
    private String sist03_user;
    private String sipp51_identificacion;

    public String getSipp51_codigo() {
        return sipp51_codigo;
    }

    public void setSipp51_codigo(String sipp51_codigo) {
        this.sipp51_codigo = sipp51_codigo;
    }

    public String getSipp51_apellido1() {
        return sipp51_apellido1;
    }

    public void setSipp51_apellido1(String sipp51_apellido1) {
        this.sipp51_apellido1 = sipp51_apellido1;
    }

    public String getSipp51_apellido2() {
        return sipp51_apellido1;
    }

    public void setSipp51_apellido2(String sipp51_apellido2) {
        this.sipp51_apellido2 = sipp51_apellido2;
    }

    public String getSipp51_nombre1() {
        return sipp51_nombre1;
    }

    public void setSipp51_nombre1(String sipp51_nombre1) {
        this.sipp51_nombre1 = sipp51_nombre1;
    }

    public String getSipp51_nombre2() {
        return sipp51_nombre2;
    }

    public void setSipp51_nombre2(String sipp51_nombre2) {
        this.sipp51_nombre1 = sipp51_nombre2;
    }

    public int getSipp51_entrevista() {
        return sipp51_entrevista;
    }

    public void setSipp51_entrevista(int sipp51_entrevista) {
        this.sipp51_entrevista = sipp51_entrevista;
    }

    public int getSipp51_revisa() {
        return sipp51_revisa;
    }

    public void setSipp51_revisa(int sipp51_revisa) {
        this.sipp51_revisa = sipp51_revisa;
    }

    public int getSipp51_supervisa() {
        return sipp51_supervisa;
    }

    public void setSipp51_supervisa(int sipp51_supervisa) {
        this.sipp51_supervisa = sipp51_supervisa;
    }

    public int getSipp51_digita() {
        return sipp51_digita;
    }

    public void setSipp51_digita(int sipp51_digita) {
        this.sipp51_digita = sipp51_digita;
    }

    public int getSipp51_evalua() {
        return sipp51_evalua;
    }

    public void setSipp51_evalua(int sipp51_evalua) {
        this.sipp51_evalua = sipp51_evalua;
    }

    public int getSist05_codigo() {
        return sist05_codigo;
    }

    public void setSist05_codigo(int sist05_codigo) {
        this.sist05_codigo = sist05_codigo;
    }

    public Date getSipp51_fecha_desde() {
        return sipp51_fecha_desde;
    }

    public void setSipp51_fecha_desde(Date sipp51_fecha_desde) {
        this.sipp51_fecha_desde = sipp51_fecha_desde;
    }
    public Date getSipp51_fecha_hasta() {
        return sipp51_fecha_hasta;
    }

    public void setSipp51_fecha_hasta(Date sipp51_fecha_hasta) {
        this.sipp51_fecha_hasta = sipp51_fecha_hasta;
    }

    public String getSist03_user() {
        return sist03_user;
    }

    public void setSist03_user(String sist03_user) {
        this.sist03_user = sist03_user;
    }
    public String getSipp51_identificacion() {
        return sipp51_identificacion;
    }

    public void setSipp51_identificacion(String sipp51_identificacion) {
        this.sipp51_identificacion = sipp51_identificacion;
    }

}


