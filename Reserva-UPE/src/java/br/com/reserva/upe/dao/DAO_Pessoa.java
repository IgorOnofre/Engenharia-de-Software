package br.com.reserva.upe.dao;

import br.com.reserva.upe.conexao.ConexaoBD;
import br.com.reserva.upe.modelo.Pessoa;
import br.com.reserva.upe.util.FacesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Pessoa implements IDAO_Pessoa<Pessoa>{

    ConexaoBD conect = new ConexaoBD();
   
    
    
    /**
     *
     * @param p
     * @throws SQLException
     */
    @Override
    public void Cadastrar(Pessoa p) throws SQLException {
        String sql= "INSERT INTO pessoa (nome, email, senha, tipo) VALUES (?,?,?,?);";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
                     
           
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setString(3, p.getSenha());
            pst.setString(4, p.getTipo());
            
            
            pst.execute();
            FacesUtil.MensagemIformativa("O novo usuário foi salvo com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
            
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível salvar o novo usuário!");
        } 
}
    /**
     *
     * @param p
     * @throws SQLException
     */
    @Override
    public void Atualizar(Pessoa p) throws SQLException {
        String sql = "UPDATE pessoa SET  nome= ?, email= ?, senha=?, tipo=? WHERE = '"+p.getId()+"';";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setString(3, p.getSenha());
            pst.setString(4, p.getTipo());
            
            pst.execute();
            FacesUtil.MensagemIformativa("Usuário foi atualizado com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
            
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível atualizar o usuário!");
        } 
        
    }
    /**
     *
     * @param p
     * @throws SQLException
     */
    @Override
    public void Apagar(Pessoa p) throws SQLException{
        String sql= "DELETE FROM pessoa WHERE id= ?;";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, p.getId());
            
            pst.execute();
            FacesUtil.MensagemIformativa("O usuário foi deletado com sucesso!");
            pst.close();
            ConexaoBD.Desconectar();
        }catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível deletar o usuário!");
        } 
    }
    
    public List<Pessoa> Listar(String p){
        String sql= "SELECT nome, email FROM pessoa WHERE nome like '"+p+"%' order by nome Asc;";
        List<Pessoa> lista = new ArrayList<Pessoa>();
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;
            
            pst = conn.prepareStatement(sql);                    
            rs = pst.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String Nome = rs.getString("Nome");
                String Email = rs.getString("email");              
                String Tipo = rs.getString("Tipo");
               
                Pessoa pe = new Pessoa();
                
                pe.setId(id);
                pe.setNome(Nome);
                pe.setEmail(Email);
                pe.setTipo(Tipo);
               

                lista.add(pe);
            }

            
            pst.close();
            ConexaoBD.Desconectar();
            
            return lista;
            
          
        }catch (SQLException a){
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível listar os  usuários!");
            return null;
        }
    }
    
    
    public Pessoa autenticar(Pessoa p){
        String sql = "select * from pessoa where email= '" + p.getEmail() 
                + "' and senha = '" + p.getSenha() + "';";
        
        try{
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            
            if(rs.next()){
                p.setId(rs.getInt("id"));
                p.setTipo(rs.getString("tipo"));
                p.setNome(rs.getString("nome"));
            }else{
                p.setNome("");
            }
            
            pst.close();
            ConexaoBD.Desconectar();
            
        }catch (SQLException a){
            a.printStackTrace();
        }
        
        return p;
    }
}