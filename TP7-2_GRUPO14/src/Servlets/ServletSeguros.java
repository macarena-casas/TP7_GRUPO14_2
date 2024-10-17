package Servlets;
import Entidades.SegurosDAO;
import Entidades.seguros;
import Entidades.tipoSeguros;

import java.io.IOException;
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
		if (request.getParameter("param") != null) {
			SegurosDAO dao = new SegurosDAO();
			
		    ArrayList <seguros> lista =dao.listarSeguros();
			 
	           request.setAttribute("listaS",lista);
		 
		 RequestDispatcher rd=request.getRequestDispatcher("/ListarSeguros.jsp");  
		    rd.forward(request, response);
			
		}
			
		//////lista los seguros 
		
		
	    if (request.getParameter("btnAceptar") != null) {
	        try {
	            seguros s = new seguros();
	            request.setAttribute("IdProximo",s.getIdSeguro());
	           
	            
	           //intentos para validaciones
	           /* if (request.getParameter("txtDescripcion") != null ) {
	            	s.setDescripcion(request.getParameter("txtDescripcion"));
	            } else {
	             
	                request.setAttribute("error", "Debe completar la descripcion.");
	            }
	            */
	           s.setDescripcion(request.getParameter("txtDescripcion"));
	            
	            String tipoSeguroSeleccionado = request.getParameter("seguro");
	            tipoSeguros tipoSeguro = null;
	            if ("Salud".equals(tipoSeguroSeleccionado)) {
	                tipoSeguro = new tipoSeguros(1, "Salud");
	            } else if ("Automóvil".equals(tipoSeguroSeleccionado)) {
	                tipoSeguro = new tipoSeguros(2, "Automóvil");
	            } else if ("Hogar".equals(tipoSeguroSeleccionado)) {
	                tipoSeguro = new tipoSeguros(3, "Hogar");
	            } else if ("Vida".equals(tipoSeguroSeleccionado)) {
	                tipoSeguro = new tipoSeguros(4, "Vida");
	            }
	            s.setIdTipo(tipoSeguro);
	            //intentos para validaciones
	           /* if (request.getParameter("txtCosto") != null && request.getParameter("txtCosto").matches("\\d+(\\.\\d+)?")) {
	                s.setCostoContratacion(Float.parseFloat(request.getParameter("txtCosto")));
	            } else {
	             
	                request.setAttribute("error", "El costo debe ser un número válido.");
	            }
	            if (request.getParameter("txtCostoMaximo") != null && request.getParameter("txtCostoMaximo").matches("\\d+(\\.\\d+)?")) {
	                s.setCostoContratacion(Float.parseFloat(request.getParameter("txtCostoMaximo")));
	            } else {
	             
	                request.setAttribute("error", "El costo debe ser un número válido.");
	            }*/
	            s.setCostoContratacion(Float.parseFloat(request.getParameter("txtCosto")));
	           s.setCostoAsegurado(Float.parseFloat(request.getParameter("txtCostoMaximo")));
	            SegurosDAO dao = new SegurosDAO();
	            dao.agregarSeguro(s);
	            
	            System.out.println("<p>Seguro agregado exitosamente!</p>");
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("<p>No fue agregado</p>");
	        }
	    }
	    
	    RequestDispatcher rd=request.getRequestDispatcher("/AgregarSeguro.jsp");  
	    rd.forward(request, response);
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if (request.getParameter("btnFiltro") != null) {
			SegurosDAO dao = new SegurosDAO();

		     
		     String tipoSeguroSeleccionado = request.getParameter("filtroTipoSeguros");
	           ArrayList <seguros> lista =dao.filtrarSeguros(tipoSeguroSeleccionado);
			 
	           request.setAttribute("listaS",lista);
		 
		 RequestDispatcher rd=request.getRequestDispatcher("/ListarSeguros.jsp");  
		    rd.forward(request, response);
	}
	}
}
