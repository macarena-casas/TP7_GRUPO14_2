package Entidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
    private Connection conexion;
    private PreparedStatement comando;
    private ResultSet lector;
    private String url = "jdbc:mysql://localhost:3306/segurosgroup2";
    private String user = "root"; 
    private String password = "root";
    public static Conexion instancia;
   

    public ResultSet getLector() {
        return lector;
    }
    
    public Conexion() {
        try {  
        
                this.conexion = DriverManager.getConnection(url, user, password);
                this.conexion.setAutoCommit(false);
               
            } 
        catch (SQLException ex) {
                ex.printStackTrace();
            } 
   
        }
            
   
    public void setearSp(String sp) throws SQLException {
        comando = conexion.prepareStatement("{call " + sp + "}");
    }
    
    public void setearConsulta(String consulta) throws SQLException {
        comando = conexion.prepareStatement(consulta);
    }
    
    public ResultSet ejecutarLectura() throws SQLException {
        try {
            lector = comando.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
		return lector;
    }
    
    public void cerrarConexion() {
        try {
            if (lector != null && !lector.isClosed()) {
                lector.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setearParametros(int index, String str) throws SQLException {
        comando.setObject(index, str);
    }
    
    public int ejecutarAccion() throws SQLException {
        int filasAfectadas = 0;
        try {
            filasAfectadas = comando.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return filasAfectadas;
    }
}