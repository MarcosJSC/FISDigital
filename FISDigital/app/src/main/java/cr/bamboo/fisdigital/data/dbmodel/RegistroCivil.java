package cr.bamboo.fisdigital.data.dbmodel;

/**
 * Created by dianavalerin on 1/18/15.
 */
import com.orm.SugarRecord;

import java.util.Date;

public class RegistroCivil extends SugarRecord<RegistroCivil>{
    private String cedula;
    private String sexo;
    private Date fSuceso;
    private String pApellido;
    private String sApellido;
    private String nombre ;
    private String nomPadre;
    private String nomMadre;
    private String muerto ;


/*
    public static RegistroCivil buildRegistroCivilFromCSV(JSONObject jsonArticles){
        RegistroCivil persona = new RegistroCivil();
        try {
            articles.md5 = jsonArticles.optString("md5", NacionConstants.EMPTY_STRING);
            //articles.save();
            JSONArray jsonArrayArticles = jsonArticles.getJSONArray("data");
            articles.articles = new Article().buildArticlesListFromJSONObject(jsonArrayArticles, articles);
        }catch(JSONException e){
            Log.d(Articles.class.getName(), e.getLocalizedMessage());
        }
        return articles;
    }
*/
public void saveOnBackground(){
    save();
 /*
    if(this.body != null) {
        for (Body body : this.body) {
            body.saveOnBackground();
        }
    }
*/
}

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getF_suceso() {
        return fSuceso;
    }

    public void setF_suceso(Date f_suceso) {
        this.fSuceso = f_suceso;
    }

    public String getP_apellido() {
        return pApellido;
    }

    public void setP_apellido(String p_apellido) {
        this.pApellido = p_apellido;
    }

    public String getS_apellido() {
        return sApellido;
    }

    public void setS_apellido(String s_apellido) {
        this.sApellido = s_apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNom_padre() {
        return nomPadre;
    }

    public void setNom_padre(String nom_padre) {
        this.nomPadre = nom_padre;
    }

    public String getNom_madre() {
        return nomMadre;
    }

    public void setNom_madre(String nom_madre) {
        this.nomMadre = nom_madre;
    }

    public String getMuerto() {
        return muerto;
    }

    public void setMuerto(String muerto) {
        this.muerto = muerto;
    }
/*
    public void updateOnBackground(RegistroCivil registroCheckout) {
        if (registroCheckout != null) {
            if (hasChanged(registroCheckout))
                update(registroCheckout);
        }
    }
*/
    /*
    private void update(RegistroCivil registroCheckout) {
        cedula = registroCheckout.getCedula();
        apellido1 = registroCheckout.getApellido1();
        apellido2 = registroCheckout.getApellido2();
        nombre = registroCheckout.getNombre();
        fechaNacimiento = registroCheckout.getFechaNacimiento();
        tipo = registroCheckout.getTipo();
        genero = registroCheckout.getGenero();
        save();
    }
    */



}
