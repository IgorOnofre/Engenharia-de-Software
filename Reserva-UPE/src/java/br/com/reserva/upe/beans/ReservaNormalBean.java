/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.FacesUtil;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mathe
 */
@ManagedBean(name = "reservaNormal")
@SessionScoped
public class ReservaNormalBean implements Serializable{

    private Reserva novaReserva;
    
    public void salvarReserva(Reserva r, int id){
        
        DAO_Reserva dao = new DAO_Reserva();
        r.setIdPessoa(id);
          
        try {
            dao.Cadastrar(r);
            novaReserva = r;
            FacesUtil.MensagemIformativa("A nova reserva foi efetuada com sucesso!");
            
                    
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível salvar a reserva! :/");
            
        }
        
    }

    public Reserva getNovaReserva() {
        return novaReserva;
    }

    public void setNovaReserva(Reserva novaReserva) {
        this.novaReserva = novaReserva;
    }
    
}
