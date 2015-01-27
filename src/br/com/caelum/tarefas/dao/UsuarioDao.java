package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.tarefas.ConnectionFactory;
import br.com.caelum.tarefas.modelo.Usuario;

public class UsuarioDao {
        private Connection connection;
        private DaoException exception;
        
        public UsuarioDao(){
                try {
					this.connection = new ConnectionFactory().getConnection();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
        }
        
        public void adiciona(Usuario usuario) {
                String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
                try {
                        PreparedStatement stmt = this.connection.prepareStatement(sql);
                        stmt.setString(1, usuario.getLogin());
                        stmt.setString(2, usuario.getSenha());
                        stmt.execute();
                        stmt.close();
                } catch (SQLException e) {
                        this.exception.getException(e);
                }
        }

        public List<Usuario> getLista() {
                List<Usuario> usuarios = new ArrayList<Usuario>();
                try {
                        PreparedStatement stmt = this.connection
                                        .prepareStatement("SELECT * FROM usuarios ORDER BY id");
                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {
                                // Criando o objeto de tarefa
                                Usuario usuario = new Usuario();
                                usuario.setId(rs.getLong("id"));
                                usuario.setLogin(rs.getString("login"));
                                usuario.setSenha(rs.getString("senha"));                                

                                /*if(rs.getDate("datafinalizacao") != null){
                                        Calendar data = Calendar.getInstance();
                                        data.setTime(rs.getDate("datafinalizacao"));
                                        usuario.setDataFinalizacao(data);
                                }*/

                                // Adiciona o tarefas a lista
                                usuarios.add(usuario);
                        }
                        rs.close();
                        stmt.close();
                } catch (SQLException e) {
                        this.exception.getException(e);
                }
                return usuarios;
        }
        
        public void altera(Usuario usuario) {
                String sql = "UPDATE usuarios SET " + "login = ?," + "senha = ? " + "WHERE id = ?";
                try {
                        PreparedStatement stmt = this.connection.prepareStatement(sql);                 
                        stmt.setString(1, usuario.getLogin());
                        stmt.setString(2, usuario.getSenha());                  
                        stmt.setLong(3, usuario.getId());
                        stmt.execute();
                        stmt.close();
                } catch (SQLException e) {
                        this.exception.getException(e);
                }
        }

        public void remove(Long id) {
                try {
                        PreparedStatement stmt = connection.prepareStatement("DELETE FROM usuarios WHERE id=?");
                        stmt.setLong(1, id);
                        stmt.execute();
                        stmt.close();
                } catch (SQLException e) {
                        this.exception.getException(e);
                }
        }
        
        public boolean existeUsuario(Usuario usuario){
                boolean existe = false;
                try {
                        PreparedStatement stmt = this.connection
                                        .prepareStatement("SELECT * FROM usuarios WHERE login=? AND senha=?");
                        stmt.setString(1, usuario.getLogin());
                        stmt.setString(2, usuario.getSenha());
                        ResultSet rs = stmt.executeQuery();
                        if(rs.next())
                                existe = true;
                        rs.close();
                        stmt.close();
                } catch (SQLException e) {
                        this.exception.getException(e);
                }
                return existe;
        }
}