package Servlets;

import Entidades.SegurosDAO;
import Entidades.seguros;
import Entidades.tipoSeguros;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletSeguros")
public class ServletSeguros extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletSeguros() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SegurosDAO dao = new SegurosDAO(); 

        
        if (request.getParameter("param") == null) {
            System.out.println("Parametro 'param' es nulo, redirigiendo a AgregarSeguro.jsp.");
            
            try {
                request.setAttribute("IdProximo", dao.obtenerProximoId());
                ArrayList<tipoSeguros> listaTiposSeguros = dao.listarTiposSeguros();
                request.setAttribute("listaTiposSeguros", listaTiposSeguros);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al obtener los tipos de seguros.");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
            rd.forward(request, response);
            return; 
        }

       
        if (request.getParameter("param") != null) {
            System.out.println("Parametro 'param' no es nulo, listando seguros...");
            ArrayList<seguros> lista = dao.listarSeguros();
			request.setAttribute("listaS", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
            rd.forward(request, response);
            return; 
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        if (request.getParameter("btnFiltro") != null) {
            System.out.println("Botón de filtro presionado.");
            SegurosDAO dao = new SegurosDAO();
            String tipoSeguroSeleccionado = request.getParameter("filtroTipoSeguros");
            
           
            ArrayList<seguros> lista = null;
			lista = dao.filtrarSeguros(tipoSeguroSeleccionado);
            request.setAttribute("listaS", lista);
            
            
            RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
            rd.forward(request, response);
            return;
        }

       
        System.out.println("btnAceptar: " + request.getParameter("btnAceptar"));
        System.out.println("txtDescripcion: " + request.getParameter("txtDescripcion"));
        System.out.println("seguro: " + request.getParameter("seguro"));
        System.out.println("txtCosto: " + request.getParameter("txtCosto"));
        System.out.println("txtCostoMaximo: " + request.getParameter("txtCostoMaximo"));

        
        if (request.getParameter("btnAceptar") != null) {
            System.out.println("Botón 'btnAceptar' presionado, validando datos...");
            try {
                boolean esValido = true;  
                StringBuilder errorMsg = new StringBuilder();  

                seguros s = new seguros();  

                
                String descripcion = request.getParameter("txtDescripcion");
                if (descripcion == null || descripcion.trim().isEmpty()) {
                    esValido = false;
                    errorMsg.append("Debe completar la descripción.<br>");
                } else {
                    s.setDescripcion(descripcion);
                }

               
                String tipoSeguroSeleccionado = request.getParameter("seguro");
                if (tipoSeguroSeleccionado != null && !tipoSeguroSeleccionado.isEmpty()) {
                    int idTipoSeguro = Integer.parseInt(tipoSeguroSeleccionado); 
                    tipoSeguros tipoSeguro = new tipoSeguros();
                    tipoSeguro.setIdTipo(idTipoSeguro);  
                    s.setIdTipo(tipoSeguro);  
                } else {
                    esValido = false;
                    errorMsg.append("Debe seleccionar un tipo de seguro.<br>");
                }

               
                String costo = request.getParameter("txtCosto");
                if (costo == null || !costo.matches("\\d+(\\.\\d+)?")) {
                    esValido = false;
                    errorMsg.append("El costo de contratación debe ser un número válido.<br>");
                } else {
                    s.setCostoContratacion(Float.parseFloat(costo));
                }

               
                String costoMaximo = request.getParameter("txtCostoMaximo");
                if (costoMaximo == null || !costoMaximo.matches("\\d+(\\.\\d+)?")) {
                    esValido = false;
                    errorMsg.append("El costo máximo asegurado debe ser un número válido.<br>");
                } else {
                    s.setCostoAsegurado(Float.parseFloat(costoMaximo));
                }

               
                if (esValido) {
                    System.out.println("Todos los campos son válidos, procediendo a agregar el seguro...");
                    SegurosDAO dao = new SegurosDAO();
                    dao.agregarSeguro(s);  
                    request.setAttribute("mensajeExito", "Seguro agregado exitosamente.");
                } else {
                    
                    System.out.println("Errores de validación: " + errorMsg.toString());
                    request.setAttribute("error", errorMsg.toString());
                }

            } catch (Exception e) {
                e.printStackTrace(); 
                request.setAttribute("error", "Hubo un problema al agregar el seguro.");
            }

           
            RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
            rd.forward(request, response);
            return;
        }
    }

}
