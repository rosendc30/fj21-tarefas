package br.com.caelum.tarefas.modelo;

public class Usuario {
    private Long id;
    private String login;
    private String senha;
    
    public Usuario() {
		
	}
    
    public Long getId() {
            return id;
    }
    public void setId(Long id) {
            this.id = id;
    }
    public String getLogin() {
            return login;
    }
    public void setLogin(String login) {
            this.login = login;
    }
    public String getSenha() {
            return senha;
    }
    public void setSenha(String senha) {
            this.senha = senha;
    }
}