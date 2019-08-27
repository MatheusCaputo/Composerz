/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexao;
import DAO.ProdutoDAO;
import Model.Produto;
import View.CadastroProduto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ControllerFormProduto {
    
    private final CadastroProduto view;

    public ControllerFormProduto(CadastroProduto view) {
        this.view = view;
    }
    
    public void salvaUsuario(){
        String nome = view.getjNome().getText();
        int id = Integer.parseInt(view.getjId().getText());
        String medida = view.getjMedida().getText();
        int validade = Integer.parseInt(view.getjValidade().getText());
                
        Produto produto = new Produto(nome, id, medida, validade);
        
        try {
            Connection conexao = new Conexao().getConnection();
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            produtoDAO.insertP(produto);
            
            view.getjNome().setText("");
            view.getjId().setText("");
            view.getjMedida().setText("");
            view.getjValidade().setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
