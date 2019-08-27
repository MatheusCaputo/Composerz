/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexao;
import DAO.InsumosDAO;
import DAO.ProdutoDAO;
import Model.Insumos;
import Model.Produto;
import View.CadastroInsumos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControllerFormInsumos {
    
    private final CadastroInsumos view;

    public ControllerFormInsumos(CadastroInsumos view) {
        this.view = view;
    }
    
    public void pesquisarProduto(){
        
        int id = Integer.parseInt(view.getjCodProduto().getText());
        
        Produto produto = new Produto(id);
        
        try {
            Connection conexao;
            conexao = new Conexao().getConnection();
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            
            if(produtoDAO.existeP(produto)){
                
                Produto produtoBD = produtoDAO.selectIdP(produto);
                view.getjNomeProduto().setText(produtoBD.getNome());
                view.getjUnidadeMedia().setText(produtoBD.getUnidadeMedida());
                JOptionPane.showMessageDialog(view, "Produto válido, por favor preecha os próximos campos.");
                view.getJbCadastrar().setEnabled(true);
                
            }else{
                JOptionPane.showMessageDialog(view, "Não existe produto com esse código de identificação cadastrado, insira um  código de identificação válido ou cadastre este para preencher os campos abaixo!!!");
                view.getJbCadastrar().setEnabled(false);
                view.getjNomeProduto().setText("");
                view.getjUnidadeMedia().setText("");
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(ControllerFormInsumos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void salvarInsumo(){
        
        int idProduto = Integer.parseInt(view.getjCodProduto().getText());
        String descricao = view.getJaDescricao().getText();
        int qntPorUnidade = Integer.parseInt(view.getjQtdUnidade().getText());
        double custoInsumo = Double.parseDouble(view.getjCustoUnitario().getText());
        
        Insumos insumo = new Insumos(descricao, qntPorUnidade, custoInsumo, idProduto);

        try {
            Connection conexao = new Conexao().getConnection();
            InsumosDAO insumosDAO = new InsumosDAO(conexao);
            insumosDAO.insertI(insumo);
            
            view.getJaDescricao().setText("");
            view.getjQtdUnidade().setText("");
            view.getjCustoUnitario().setText("");
            view.getjCodProduto().setText("");
            view.getjNomeProduto().setText("");
            view.getjUnidadeMedia().setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerFormInsumos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
