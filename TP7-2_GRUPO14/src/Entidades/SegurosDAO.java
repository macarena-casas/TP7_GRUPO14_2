package Entidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SegurosDAO {
    private Conexion conexion;

    public SegurosDAO() {
        conexion = new Conexion();
    }

 
    public void agregarSeguro(seguros seguro) {
        try {
            conexion.setearSp("agregarSeguro(?, ?, ?, ?, ?)");
            conexion.setearParametros(1, String.valueOf(seguro.getIdSeguro()));  
            conexion.setearParametros(2, seguro.getDescripcion());
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
}
