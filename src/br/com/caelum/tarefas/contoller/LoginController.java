package br.com.caelum.tarefas.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.UsuarioDao;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class LoginController{
    
	 @RequestMapping("index")
	  public String pagInicial() {
	    return "index";
	  }
	
	@RequestMapping("loginForm")
  public String loginForm() {
    return "formulario-login";
  }
  
  @RequestMapping("efetuaLogin")
  public String efetuaLogin(Usuario usuario, HttpSession session) {
    if(new UsuarioDao().existeUsuario(usuario)) {
      session.setAttribute("usuarioLogado", usuario);
      return "menu";
    }
    return "redirect:loginForm";
  }
  
  @RequestMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:loginForm";
  }
}