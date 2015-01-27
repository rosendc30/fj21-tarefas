package br.com.caelum.tarefas.dao;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
        public void getException(Exception e){
                System.out.println(e);
        }
}