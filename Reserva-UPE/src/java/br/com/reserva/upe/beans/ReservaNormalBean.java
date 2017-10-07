/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Reserva;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
            FacesContext.getCurrentInstance().getExternalContext().redirect("reservaNormalDeuCerto.xhtml");
                    
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
        } catch (IOException ex) {
            System.out.println("Erro de IO: " + ex);
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Reserva getNovaReserva() {
        return novaReserva;
    }

    public void setNovaReserva(Reserva novaReserva) {
        this.novaReserva = novaReserva;
    }
    
}
