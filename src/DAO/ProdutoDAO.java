/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ProdutoDAO {
    
    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    public boolean existeP(Produto produto) throws SQLException {

        String sql = "select * from produtos where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, produto.getId());
        pst.execute();

        ResultSet rs = pst.getResultSet();

        return rs.next();

    }
    
    public void insertP(Produto produto) throws SQLException {
        
        try {
            if(!existeP(produto)){
                String sql = "insert into produtos(id, nome, unidade_medida, prazo_validade) values(?, ?, ?, ?);";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, produto.getId());
                pst.setString(2, produto.getNome());
                pst.setString(3, produto.getUnidadeMedida());
                pst.setInt(4, produto.getPrazoValidade());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Já existe um produto com esse código de identificação");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: "+ex);
        }finally{
            connection.close();
        }
    }
    
    private ArrayList<Produto> pesquisarP(PreparedStatement pst) throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();

        pst.execute();
        ResultSet rs = pst.getResultSet();

        while(rs.next()){

            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String unidadeMedida = rs.getString("unidade_medida");
            int validade = rs.getInt("prazo_validade");

            Produto produtoBD = new Produto(nome, id, unidadeMedida, validade);
            produtos.add(produtoBD);

        }
        return produtos;
    }
    
    public ArrayList<Produto> selectAllP() throws SQLException{
        
        String sql = "select * from produtos";
        PreparedStatement pst = connection.prepareStatement(sql);
        
        return pesquisarP(pst);
    }

    public Produto selectIdP(Produto produto) throws SQLException{
        
        String sql = "select * from produtos where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, produto.getId());

        produto = pesquisarP(pst).get(0);
        
        return produto;
    }

}
