package br.com.caelum.tarefas.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.TarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {

  private final TarefaDao dao;

  @Autowired
  public TarefasController(TarefaDao dao) {
        this.dao = dao;  
    }

  @RequestMapping("novaTarefa")
  public String form() {
    return "tarefa/formulario";
  }
  
  @RequestMapping("adicionaTarefa")
  public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
    
    if(result.hasFieldErrors("descricao")) {
      return "tarefa/formulario";
    }  
   // TarefaDao dao = new TarefaDao();
    dao.adiciona(tarefa);
    return "tarefa/tarefa-adicionada";
  }
  
  @RequestMapping("listaTarefas")
  public String lista(Model model) {
  //  TarefaDao dao = new TarefaDao();
    model.addAttribute("tarefas", dao.getLista());
    return "tarefa/lista";
  }
  
  @RequestMapping("finalizaTarefa")
  public String finaliza(Long id, Model model) {
   // TarefaDao dao = new TarefaDao();
    dao.finaliza(id);
    model.addAttribute("tarefa", dao.buscaPorId(id));
    return "tarefa/finalizada";
  }
  
  @RequestMapping("removeTarefa")
  public String remove(Long id, Model model){
	  dao.remove(id);
	  return "redirect:listaTarefas";
  }
  
  @RequestMapping("mostraTarefa")
  public String mostra(Long id, Model model){
	  model.addAttribute("tarefa", dao.buscaPorId(id));
	  return "tarefa/mostra";
  }
  
  @RequestMapping("alteraTarefa")
  public String altera(Tarefa tarefa){
	  dao.altera(tarefa);
	  return "redirect:listaTarefas";
  }
}
