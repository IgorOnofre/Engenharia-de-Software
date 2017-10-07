/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

import br.com.reserva.upe.modelo.Pessoa;
import java.sql.SQLException;

/**
 *
 * @author Hilton
 */
public class Teste {

    public static void main(String[] args) throws SQLException {
        DAO_Pessoa dp = new DAO_Pessoa();
        Pessoa p = new Pessoa();

        p.setEmail("abg@gmail.com");
        p.setNome("Lucas");
        p.setSenha("123");
        p.setTipo("1");

        dp.Cadastrar(p);
        
    }

}
