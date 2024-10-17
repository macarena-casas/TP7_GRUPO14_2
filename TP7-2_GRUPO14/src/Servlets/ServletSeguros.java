package Servlets;
import Entidades.SegurosDAO;
import Entidades.seguros;
import Entidades.tipoSeguros;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSeguros
 */
@WebServlet("/ServletSeguros")
public class ServletSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    if (request.getParameter("btnAceptar") != null) {
	        try {
	            seguros s = new seguros();
	            request.setAttribute("IdProximo",s.getIdSeguro());
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
	            s.setCostoContratacion(Float.parseFloat(request.getParameter("txtCosto")));
	            s.setCostoAsegurado(Float.parseFloat(request.getParameter("txtCostoMaximo")));
	            SegurosDAO dao = new SegurosDAO();
	            dao.agregarSeguro(s);
	            
	            System.out.println("<p>Seguro agregado exitosamente</p>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("<p>Seguro agregado exitosamente</p>");
	        }
	    }
	    
	    RequestDispatcher rd=request.getRequestDispatcher("/AgregarSeguro.jsp");  
	    rd.forward(request, response);
	    
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
