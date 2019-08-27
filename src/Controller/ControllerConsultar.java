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
import View.ConsultarProdutos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ControllerConsultar {
    
    private final ConsultarProdutos view;

    public ControllerConsultar(ConsultarProdutos view) {
        this.view = view;
    }
    
    public void preencherTabProduto(){
        
        try {
            
            Connection conexao = new Conexao().getConnection();
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            ArrayList<Produto> produtosBD = produtoDAO.selectAllP();
            
            DefaultTableModel tm = (DefaultTableModel) view.getjTProdutos().getModel();
            tm.setNumRows(0);
            
            produtosBD.forEach((produto) -> {
                tm.addRow( new Object[]{
                    
                    produto.getId(),
                    produto.getNome(),
                    produto.getUnidadeMedida(),
                    produto.getPrazoValidade()
                    
                });
            });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void preencherTabInsumos(){
        
        try {

            int idProduto = (int) view.getjTProdutos().getValueAt(view.getjTProdutos().getSelectedRow(), 0);
            
            Insumos insumos = new Insumos(idProduto);
            
            Connection conexao = new Conexao().getConnection();
            InsumosDAO insumosDAO = new InsumosDAO(conexao);
            ArrayList<Insumos> insumosBD = insumosDAO.selectInsumosId(insumos);
            
            DefaultTableModel tm = (DefaultTableModel) view.getjTInsumos().getModel();
            tm.setNumRows(0);
            
            insumosBD.forEach((insumo) -> {
                tm.addRow( new Object[]{
                    
                    insumo.getIdProduto(),
                    insumo.getDescricao(),
                    insumo.getQtdPorUnidade(),
                    insumo.getCustoInsumo()
                    
                });
            });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
