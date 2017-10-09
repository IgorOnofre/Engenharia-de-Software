package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
import br.com.reserva.upe.modelo.Pessoa;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "conferePessoa")
@SessionScoped
public class ValidaLoginBean implements Serializable{

    private Pessoa pessoaLogada = null;

    public void logar(Pessoa p) {

        DAO_Pessoa dao = new DAO_Pessoa();
        Pessoa logado = dao.autenticar(p);

        if (!logado.getNome().equals("")) {
            
            if (p.getTipo().equals("1")) {
                pessoaLogada = logado;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("home2.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (p.getTipo().equals("2"))  {
                pessoaLogada = logado;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("homeAdm2.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else {
            
             try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("erro.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }  
             
        }
    }

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa p) {
        this.pessoaLogada = p;
    }

}
