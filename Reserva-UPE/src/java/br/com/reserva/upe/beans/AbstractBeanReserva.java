/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import br.com.reserva.upe.modelo.Pessoa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import br.com.reserva.upe.dao.IDAO_Reserva;

/**
 *
 * @author mathe
 */
public abstract class AbstractBeanReserva<T> {

    private IDAO_Reserva<T> dao;
    private T current;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    private IDAO_Reserva<T> getDAO() {
        if (dao == null) {
            dao = createDao();
        }
        return dao;
    }

    public void salvar() {
        try {
            getDAO().Cadastrar(current);
        } catch (SQLException ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        novo();
    }

    abstract void novo();

    abstract IDAO_Reserva<T> createDao();

    @PostConstruct
    public void init() {
        novo();
    }

}
