
package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.dao.IDAO_Reserva;
import br.com.reserva.upe.modelo.Reserva;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "CrudReserva")
@SessionScoped
public class ReservaBean extends AbstractBeanReserva<Reserva>{

    @Override
    public void novo() {
        setCurrent(new Reserva());
    }

    @Override
    public IDAO_Reserva createDao() {
        return new DAO_Reserva();
    }

    

}
