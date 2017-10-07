package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
import br.com.reserva.upe.modelo.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.reserva.upe.dao.IDAO_Pessoa;

/**
 *
 * @author mathe
 */
@ManagedBean(name = "CrudPessoa")
@SessionScoped
public class PessoaBean extends AbstractBeanPessoa<Pessoa>{

    @Override
    public void novo() {
        setCurrent(new Pessoa());
    }

    @Override
    public IDAO_Pessoa<Pessoa> createDao() {
        return new DAO_Pessoa();
    }

    

}
