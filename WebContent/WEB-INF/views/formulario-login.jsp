<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link type="text/css" href="resources/css/telaLogin.css" rel="stylesheet" />
<link type="text/css" href="resources/css/ui-lightness/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
	<script>
		$(function(){
		  var $form_inputs =   $('form input');
		  var $rainbow_and_border = $('.rain, .border');
		  /* Used to provide loping animations in fallback mode */
		  $form_inputs.bind('focus', function(){
		  	$rainbow_and_border.addClass('end').removeClass('unfocus start');
		  });
		  $form_inputs.bind('blur', function(){
		  	$rainbow_and_border.addClass('unfocus start').removeClass('end');
		  });
		  $form_inputs.first().delay(800).queue(function() {
			$(this).focus();
		  });
		});
	</script>

</head>
<body id="home">
		<div class="rain">
			<div class="border start">
	    
    <form action="efetuaLogin" method="post">
    
      <label for="login">Login:</label> 
      <input type="text" name="login" placeholder=""/>
      
      <label for="senha">Senha:</label>
      <input type="password" name="senha" placeholder=""/><br /> 
      
      <input type="submit" value="ACESSAR O SISTEMA" /> 
    </form>
  



</body>
</html>