<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>Altera tarefa ${tarefa.id }</h3>

<form action="alteraTarefa" method="post">

<input type="hidden" name="id" value="${tarefa.id}" />

<label>Descrição:</label>
<textarea name="descricao" cols="100" rows="5">${tarefa.descricao}</textarea><br />

<label>Finalizado?</label>
<input type="checkbox" name="finalizado" 
value="true" ${tarefa.finalizado? 'checkd' : '' } /> <br />

<label>Data de Finalização</label>
<input type="text" name="dataFinalizacao" value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />
<br/>
 
<input type="submit" value="ALTERAR"/>

</form>

</body>
</html>