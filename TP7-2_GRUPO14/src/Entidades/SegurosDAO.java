package Entidades;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegurosDAO {
    private Conexion conexion;

    public SegurosDAO() {
        conexion = new Conexion();
    }

    public ArrayList<tipoSeguros> listarTiposSeguros() throws SQLException {
        ArrayList<tipoSeguros> listaTiposSeguros = new ArrayList<>();
        conexion.setearConsulta("SELECT idTipo, descripcion FROM tiposeguros");

        try (ResultSet rs = conexion.ejecutarLectura()) {
            while (rs.next()) {
                tipoSeguros tipo = new tipoSeguros();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                listaTiposSeguros.add(tipo);
            }
        }
        System.out.println("Lista de seguros: " + listaTiposSeguros);
        
        return listaTiposSeguros;
    }

    public int obtenerProximoId() throws SQLException {
        int idProximo = -1;
        conexion.setearConsulta("SELECT IFNULL(MAX(idSeguro) + 1, 1) AS proximoId FROM seguros;");

        try (ResultSet rs = conexion.ejecutarLectura()) {
            if (rs.next()) {
                idProximo = rs.getInt("proximoId");
            }
        }
        return idProximo;
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

    public void agregarSeguro(seguros seguro) {
        try {
            conexion.setearConsulta("INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)");

            
            conexion.setearParametros(1, seguro.getDescripcion());
            conexion.setearParametros(2, seguro.getIdTipo().getIdTipo()); 
            conexion.setearParametros(3, seguro.getCostoContratacion()); 
            conexion.setearParametros(4, seguro.getCostoAsegurado()); 

         
            int filasAfectadas = conexion.ejecutarAccion();

            if (filasAfectadas > 0) {
                System.out.println("Seguro agregado correctamente.");
                conexion.commit();
            } else {
                System.out.println("No se pudo agregar el seguro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conexion.rollback();
        } finally {
            conexion.cerrarConexion();
        }
    }

    
    public ArrayList<tipoSeguros> tipos() throws SQLException {
        return listarTiposSeguros();
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
