package Entidades;

import Entidades.tipoSeguros;

public class seguros {
    private  int contadorIdSeguro = 0;
    private int idSeguro;
    private String descripcion;
    private tipoSeguros idTipo;
    private float costoContratacion;
    private float costoAsegurado;
    

    public seguros(int id, String descripcion, tipoSeguros idTipo, float costoContratacion, float costoAsegurado) {
       
    	this.idSeguro = id; 
        this.descripcion = descripcion;
        this.idTipo = idTipo;
        this.costoContratacion = costoContratacion;
        this.costoAsegurado = costoAsegurado;
    }
    

    public seguros() {
        this.idSeguro = contadorIdSeguro++; 
        this.descripcion = "";
        this.idTipo = new tipoSeguros();
        this.costoContratacion = -1;
        this.costoAsegurado = -1;
    }

    public void setidSeguro(int id) {
        this.idSeguro = id;
    }
    public int getIdSeguro() {
        return idSeguro;
    }

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public tipoSeguros getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(tipoSeguros idTipo) {
        this.idTipo = idTipo;
    }

    public float getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(float costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    public float getCostoAsegurado() {
        return costoAsegurado;
    }

    public void setCostoAsegurado(float costoAsegurado) {
        this.costoAsegurado = costoAsegurado;
    }
    
    
}
