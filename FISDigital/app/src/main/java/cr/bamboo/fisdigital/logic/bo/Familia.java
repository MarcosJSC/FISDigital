package cr.bamboo.fisdigital.logic.bo;

import java.util.ArrayList;

/**
 * Created by JBeita on 22/03/2015.
 */
public class Familia {

    private int _numero = 0;
    private long idFamilia = 0;
    private String nombreFamilia = "" ;
    private Patrimonio _patrimonio = null;
    private ArrayList<Integrante> integrantes = null;
    private ArrayList<String> codigosEspeciales;
    private ArrayList<String> correos;

    public Familia(){
//        integrantes = new ArrayList<Integrante>();
//        codigosEspeciales = new ArrayList<String>();
//        correos=new ArrayList<String>();
    }

    public void setNombreFamilia(String nombreFamilia){
        this.nombreFamilia = nombreFamilia;
    }
    public String getNombreFamilia(){
        return nombreFamilia;
    }

    public void setIdFamilia(long idFamilia){
        this.idFamilia = idFamilia;
    }
    public long getIdFamilia(){
        return idFamilia;
    }

    public void setIntegrante(Integrante integrante){
//        if(integrantes.isEmpty()){
//            _nombre = integrante.getNombreCompleto();
//        }
//        integrantes.add(integrante);
    }

    public Integrante getIntegrante(int index){
        if(!integrantes.isEmpty()){
            return integrantes.get(index);
        }
        return null;
    }
    public ArrayList<Integrante> getIntegrantes(){
        return integrantes;
    }


    public void setNumero(int _numero){
        this._numero = _numero;
    }

    public int getNumero(){
        return this._numero;
    }

    public void setPatrimonio(Patrimonio _patrimonio){
        this._patrimonio = _patrimonio;
    }

    public Patrimonio getPatrimonio()
    {
        return this._patrimonio;
    }

    public void setCodigosEspeciales(ArrayList<String> codigosEspeciales) {
        this.codigosEspeciales = codigosEspeciales;
    }

    public ArrayList<String> getCodigosEspeciales() {
        return this.codigosEspeciales;
    }

    public ArrayList<String> getCorreos() {
        return this.correos;
    }

    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }
}