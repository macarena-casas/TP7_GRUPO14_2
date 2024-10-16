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
		<a href="ListarSeguros.jsp">Listar seguros</a>
	</nav>
	<h1>"Tipo de seguros de la base de datos"</h1>
	<form>
	<label>Busqueda por tipo de Seguros: </label>
	<select name="filtroTipoSeguros"> </select>
	<input type="submit" name="btnFiltro" value="Filtrar">
	</form>
	<table border=1>
		<tr>
		<th>ID seguro</th>
		<th>Descripcion seguro</th>
		<th>Descripcion tipo seguro</th>
		<th>Costo contratacion</th>
		<th>Costo máximo asegurado</th>
		</tr>

	</table>
	</body>
</html>