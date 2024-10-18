<%@ page import="Entidades.seguros,Entidades.tipoSeguros,Entidades.SegurosDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
</head>
<body>
    <nav>
        <a href="Inicio.jsp">Inicio</a>
        <a href="ServletSeguros">Agregar seguro</a>
        <a href="ServletSeguros?param=1">Listar seguros</a>
    </nav>
    
    <b><h1>Agregar seguros</h1></b>
    <form action = "ServletSeguros" method="post">
    
    <%
    SegurosDAO seg = new SegurosDAO();
    ArrayList <tipoSeguros> tipos = seg.tipos();
    
%>
    
    
 
    <label>ID del Seguro:</label>
	<input type="text" name="txtIdSeguro" value="<%= request.getAttribute("IdProximo") %>" readonly /><br>
        Descripción: <input type="text" name="txtDescripcion" style="width: 180px; margin-top: 5px;margin-left:83px; "/> <br>
    <label for="seguro">Tipo de seguro:</label>
    <select id="seguro" name="seguro" style="width: 180px;margin-top: 5px;margin-left: 66px;">
        <option value="">Seleccione un tipo</option> 
        <%
        for (tipoSeguros tipo : tipos) {
        %>
            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option> 
        <%
        }
        %>
    </select>
        
        <br>
        Costo contratación: <input type="text" name="txtCosto" style="width: 180px; margin-top: 5px;margin-left:42px; "/> <br> 
        Costo máximo asegurado: <input type="text" name="txtCostoMaximo" style="width: 180px; margin-top: 5px;margin-left:1px;"/> <br>
        <br>
        <input type="submit" value="Aceptar" name="btnAceptar">
    </form>

    
</body>
</html>
        
        
       
    
        

