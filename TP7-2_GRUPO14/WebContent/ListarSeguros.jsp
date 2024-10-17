<%@ page import="Entidades.seguros,Entidades.tipoSeguros,Entidades.SegurosDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Seguros</title>
</head>
<body>
	<nav>
		<a href="Inicio.jsp">Inicio</a>
		<a href="AgregarSeguro.jsp">Agregar seguro</a>
		<a href="ServletSeguros?param=1">Listar seguros</a>
	</nav>
	<h1>"Tipo de seguros de la base de datos"</h1>

  
	
	 <form action = "ServletSeguros" method="post">
     <%
	//ArrayList <seguros> listacompleta =null;
    //SegurosDAO seg1 = new SegurosDAO();
    //listacompleta = seg1.listarSeguros();
    SegurosDAO seg = new SegurosDAO();
    ArrayList <tipoSeguros> tipos = seg.tipos();
%>
	<label>Busqueda por tipo de Seguros: </label>
	<select name="filtroTipoSeguros"> 
	
	  <% if( tipos !=null)
	  for (tipoSeguros tipo : tipos) { %>
            <option value="<%= tipo %>"><%= tipo %></option>
        <% } %>
        </select>
	<input type="submit" name="btnFiltro" value="Filtrar">
		</form>
		
	<%

	ArrayList <seguros> lista = null ;
	//lista=listacompleta;
	if(request.getAttribute("listaS") != null ){
	lista = (ArrayList<seguros>)request.getAttribute("listaS");
	}
	
	%>
	<table border=1>
<tr><th>ID seguro</th> <th>Descripcion seguro</th> <th>Descripcion tipo seguro</th> <th>Costo contratacion</th> <th>Costo máximo asegurado</th></tr>


<% if( lista !=null){

for( seguros segu: lista ){%>

<tr>
<td> <%=segu.getIdSeguro()%> </td>
<td><%=segu.getDescripcion()%></td>
<td><%=segu.getIdTipo().getDescripcion()%></td>
<td> <%=segu.getCostoContratacion()%></td>
<td><%=segu.getCostoAsegurado()%></td>          
</tr>
<%}} %>
	</table>

	</body>
</html>