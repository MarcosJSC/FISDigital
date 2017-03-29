package cr.bamboo.fisdigital.data.dbmodel;

import android.util.Base64;

import com.orm.SugarRecord;

import java.util.List;

import cr.bamboo.fisdigital.data.security.CipherHelper;

/**
 * Created by jbeita on 26/07/15.
 */
public class Usuario extends SugarRecord<Usuario> {

    public String usuario;
    private String gerencia;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private int usuarioMovil;
    private String claveMovil;
    private String vector;

    public Usuario(){
        usuario = "";
        gerencia = "";
        apellido1 = "";
        nombre1 = "";
        nombre2 = "";
        usuarioMovil = 0;
        claveMovil = "";
        vector = "";
    }


    public String getVector(){return vector;}
    public void setVector(String vector){
        this.vector = vector;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apelido1) {
        this.apellido1 = apelido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public int getUsuarioMovil() {
        return usuarioMovil;
    }

    public void setUsuarioMovil(int usuarioMovil) {
        this.usuarioMovil = usuarioMovil;
    }

    public String getClaveMovil() {
        return claveMovil;
    }

    public void setClaveMovil(String claveMovil) {
        this.claveMovil = claveMovil;
    }


    public void saveOnBackground(){
        save();
    }

    public static Usuario checkUser(String username, String plainTextPass, String cryptoKey){

        Usuario user = new Usuario();

        try {
            List<Usuario> usuarios = Usuario.find(Usuario.class, "usuario = ?", new String[]{username});
            if(usuarios.size()>0) {
                user = usuarios.get(0);
                CipherHelper helper = new CipherHelper();

                byte[] encryptedPass = helper.encrypt(plainTextPass.getBytes("UTF-8"), cryptoKey.getBytes(), user.getVector().getBytes());
                String b64Pass = Base64.encodeToString(encryptedPass, 0);
                String newline = System.getProperty("line.separator");
                if(b64Pass.contains(newline)){
                    b64Pass = b64Pass.replace(newline, "");
                }
                if(user.getClaveMovil().contains(newline)){
                    user.setClaveMovil(user.getClaveMovil().replace(newline, ""));
                }


                //String plainTextActualPass = new String(helper.decrypt(user.getFdm006_Password(), cryptoKey.getBytes(), user.getFdm006_IV().getBytes()));
                if (b64Pass.equals(user.getClaveMovil())) {
                    return user;
                }
            }
        }catch(Exception e){
            Exception ex = e;
        }
        return null;
    }
}
