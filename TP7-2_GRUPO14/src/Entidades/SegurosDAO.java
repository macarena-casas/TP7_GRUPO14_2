package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SegurosDAO {
    private Conexion conexion;

    public SegurosDAO() {
        conexion = new Conexion();
    }
     public ArrayList <tipoSeguros> tipos() {
    try {
        conexion.setearConsulta("select * from tipoSeguros");
        ResultSet resultSet = conexion.ejecutarLectura();
      
        ArrayList<tipoSeguros> lista = new ArrayList <tipoSeguros>();
        while (resultSet.next()) {
        int id = Integer.parseInt(resultSet.getString("idTipo"));
        String descripcion = resultSet.getString("descripcion");
     
        tipoSeguros tipo_seguro = new tipoSeguros();
        tipo_seguro.setIdTipo(id); 
        tipo_seguro.setDescripcion(descripcion);
            lista.add(tipo_seguro);
        }
        return lista; 
        
        
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    } finally {
        conexion.cerrarConexion();
    }
    }
  
    
    public void agregarSeguro(seguros seguro) {
        try {
        	conexion.setearConsulta("INSERT INTO seguros (descripcion,idTipo,costoContratacion,costoAsegurado) VALUES (?,?,?,?,?)"); 
        	
            //conexion.setearSp("agregarSeguro(?, ?, ?, ?, ?)");
            conexion.setearParametros(1, seguro.getDescripcion());  
            conexion.setearParametros(2, String.valueOf(seguro.getIdSeguro()));
            conexion.setearParametros(3, seguro.getIdTipo().getDescripcion()); 
            conexion.setearParametros(4, String.valueOf(seguro.getCostoContratacion()));
            conexion.setearParametros(5, String.valueOf(seguro.getCostoAsegurado()));
            conexion.ejecutarAccion();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }
    public ArrayList<seguros> listarSeguros() {
        ArrayList<seguros> listaSeguros = new ArrayList<>();
        try {
        	conexion.setearConsulta("SELECT s.idSeguro, s.descripcion AS descripcionSeguro,s.idTipo as IdT, t.descripcion AS descripcionTipo, s.CostoContratacion as costoContratacion, s.CostoAsegurado as costoAsegurado FROM seguros s JOIN tiposeguros t ON s.idTipo = t.idTipo;"); 
        	
            conexion.ejecutarLectura();
            while (conexion.getLector().next()) {
                seguros seguro = new seguros();
                seguro.setidSeguro(conexion.getLector().getInt("idSeguro"));             
                seguro.setDescripcion(conexion.getLector().getString("descripcionSeguro"));
                tipoSeguros tipo = new tipoSeguros();
                tipo.setDescripcion(conexion.getLector().getString("descripcionTipo"));
                tipo.setIdTipo(conexion.getLector().getInt("IdT"));
                seguro.setIdTipo(tipo);
                seguro.setCostoContratacion(conexion.getLector().getFloat("costoContratacion"));
                seguro.setCostoAsegurado(conexion.getLector().getFloat("costoAsegurado"));
                listaSeguros.add(seguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return listaSeguros;
    }
    
    public ArrayList<seguros> filtrarSeguros(String tipo1) {
        ArrayList<seguros> listaSeguros = new ArrayList<>();
        try {
        	conexion.setearConsulta("SELECT s.idSeguro , s.descripcion AS descripcionSeguro,s.idTipo as IdT, t.descripcion AS descripcionTipo, s.CostoContratacion as costoContratacion, s.CostoAsegurado as costoAsegurado FROM seguros s JOIN tiposeguros t ON s.idTipo = t.idTipo and t.descripcion ='"+ tipo1 +"'"); 
        	
            conexion.ejecutarLectura();
            while (conexion.getLector().next()) {
                seguros seguro = new seguros();
                seguro.setidSeguro(conexion.getLector().getInt("idSeguro"));
                seguro.setDescripcion(conexion.getLector().getString("descripcionSeguro"));
                tipoSeguros tipo = new tipoSeguros();
                tipo.setDescripcion(conexion.getLector().getString("descripcionTipo"));
                tipo.setIdTipo(conexion.getLector().getInt("IdT"));
                seguro.setIdTipo(tipo);
                seguro.setCostoContratacion(conexion.getLector().getFloat("costoContratacion"));
                seguro.setCostoAsegurado(conexion.getLector().getFloat("costoAsegurado"));
                listaSeguros.add(seguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return listaSeguros;
    }
    
    
}
