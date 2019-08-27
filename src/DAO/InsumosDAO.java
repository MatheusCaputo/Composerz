/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Insumos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class InsumosDAO {
    
    private final Connection connection;

    public InsumosDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insertI(Insumos insumo) throws SQLException{
        
        try{
            
            String sql = "insert into insumos(descricao, qtd_por_unidade, custo_insumo, id_produto) values(?, ?, ?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, insumo.getDescricao());
            pst.setDouble(2, insumo.getQtdPorUnidade());
            pst.setDouble(3, insumo.getCustoInsumo());
            pst.setInt(4, insumo.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Insumo cadastrado com sucesso!");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar insumo: "+ex);
        }finally{
            connection.close();
        }

    }
    
    public ArrayList<Insumos> selectInsumosId(Insumos insumo) throws SQLException{
        
        ArrayList<Insumos> insumos = new ArrayList<>();
            
        String sql = "select * from insumos where id_produto = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, insumo.getIdProduto());
        pst.execute();
        ResultSet rs = pst.getResultSet();

        while(rs.next()){
            
            String descricao = rs.getString("descricao");
            Double qntUnidade = rs.getDouble("qtd_por_unidade");
            Double custoInsumo = rs.getDouble("custo_insumo");
            int idProduto = rs.getInt("id_produto");        

            Insumos insumoBD = new Insumos(descricao, qntUnidade, custoInsumo, idProduto);
            insumos.add(insumoBD);
        }
            
        return insumos;
    }
    
    
    
    
    
}
