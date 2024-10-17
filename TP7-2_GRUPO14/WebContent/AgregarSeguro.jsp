<%@ page import="Entidades.seguros,Entidades.tipoSeguros,Entidades.SegurosDAO" %>
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
        <a href="AgregarSeguro.jsp">Agregar seguro</a>
        <a href="ListarSeguros.jsp">Listar seguros</a>
    </nav>
    <b><h1>Agregar seguros</h1></b>
    <form action = "ServletSeguros" method="get">
    
    
    <!-- Lo muestro -->
    <p>ID Seguro: <strong><%= request.getAttribute("IdProximo") %></strong></p>
        Descripción: <input type="text" name="txtDescripcion" style="width: 180px; margin-top: 5px;margin-left:83px; "/> <br>
        <label for="seguro">Tipo de seguro:</label>
        <select id="seguro" name="seguro" style="width: 180px;margin-top: 5px;margin-left: 66px;">
            <option value="Salud">Salud</option>
            <option value="Automóvil">Automóvil</option>
            <option value="Hogar">Hogar</option>
            <option value="Vida">Vida</option>
        </select><br>
        Costo contratación: <input type="text" name="txtCosto" style="width: 180px; margin-top: 5px;margin-left:42px; "/> <br> 
        Costo máximo asegurado: <input type="text" name="txtCostoMaximo" style="width: 180px; margin-top: 5px;margin-left:1px; "/> <br>
        <input type="submit" value="Aceptar" name="btnAceptar">
    </form>

    
</body>
</html>
