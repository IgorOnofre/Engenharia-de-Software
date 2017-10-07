package br.com.reserva.upe.dao;

import br.com.reserva.upe.conexao.ConexaoBD;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.FacesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hilton
 */
public class DAO_Reserva implements IDAO_Reserva<Reserva>{
    
    ConexaoBD conect = new ConexaoBD();
    Reserva pessoa = new Reserva();
    
    /**
     *
     * @param r
     * @throws SQLException
     */
    @Override
    public void Cadastrar(Reserva r)throws SQLException{
        String sql= "INSERT INTO reserva (idPessoa, dataDaReserva, turno, horario, laboratorio, descricao) VALUES (?,?,?,?,?,?);";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
                     
            pst.setInt(1, r.getIdPessoa());
            pst.setDate(2, r.getDataDaReserva());
            pst.setString(3, r.getTurno());
            pst.setString(4, r.getHorario());
            pst.setString(5, r.getLaboratorio());
            pst.setString(6, r.getDescricao());
            
            
            pst.execute();
            FacesUtil.MensagemIformativa("A nova reserva efetuada com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
            
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível salvar a reserva! :/");
        } 
    }
    
     /**
     *
     * @param r
     * @throws SQLException
     */
    @Override
    public void Atualizar(Reserva r) throws SQLException {
        String sql = "UPDATE reserva SET dataDaReserva= ?, turno= ?, horario= ? WHERE = '"+r.getId()+"';";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
            
            pst.setDate(1, r.getDataDaReserva());
            pst.setString(2, r.getTurno());
            pst.setString(3, r.getHorario());                    
            
            pst.execute();
            FacesUtil.MensagemIformativa("A reserva atualizada com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
            
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível atualizar a reserva! :/");
        } 
        
    }
    
     /**
     *
     * @param r
     * @throws SQLException
     */
    @Override
    public void Apagar(Reserva r) throws SQLException{
        String sql= "DELETE FROM reserva WHERE id= ?;";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, r.getId());
            
            pst.execute();
            FacesUtil.MensagemIformativa("A reserva foi cancelada com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível cancelar a reserva! :/");
        } 
    }
    
    public ResultSet Listar(Reserva r){
        String sql= "SELECT dataDaReserva, turno, horario FROM reserva WHERE dataDaReserva like '?%' order by nome Asc;";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;
            
            pst = conn.prepareStatement(sql);
            pst.setDate(1, r.getDataDaReserva());
            
            rs = pst.executeQuery(sql);
            pst.close();
            ConexaoBD.Desconectar();
            
            return rs;
            
          
        }catch (SQLException a){
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível listar as reservas! :/");
            return null;
        }
}
}
