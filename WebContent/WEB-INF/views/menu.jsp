<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

  <body>
    <h2>P�gina inicial da Lista de Tarefas</h2>
    <p>Bem vindo, ${usuarioLogado.login}</p> 
    <a href="listaTarefas">Clique aqui</a> para acessar a Lista de tarefas. <br />
    <a href="adicionaTarefa">Clique aqui</a> para Adicionar nova tarefa.<br />
    <a href="logout">Sair do sistema</a>
  </body>
</html>