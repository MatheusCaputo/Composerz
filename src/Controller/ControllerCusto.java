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
import View.ConsultarCustoProduto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControllerCusto {
    
    private final ConsultarCustoProduto view;

    public ControllerCusto(ConsultarCustoProduto view) {
        this.view = view;
    }
    
    
    public void preencherTabInsumos(){
        
        try {

            int idProduto = Integer.parseInt(view.getjCodProduto().getText());
            
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
                preencherTabInsumos();
                JOptionPane.showMessageDialog(view, "Produto válido.");
                view.getjBCalcular().setEnabled(true);
                view.getjCustoProduto().setText("");
                
            }else{
                DefaultTableModel tm = (DefaultTableModel) view.getjTInsumos().getModel();
                tm.setNumRows(0);
                view.getjBCalcular().setEnabled(false);
                JOptionPane.showMessageDialog(view, "Não existe produto com esse código de identificação cadastrado, insira um  código de identificação válido ou cadastre este para calcular seu custo!!!");
                view.getjNomeProduto().setText("");
                view.getjUnidadeMedia().setText("");
                view.getjCustoProduto().setText("");
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(ControllerFormInsumos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public double calcularCusto(){
        
        double custoProduto = 0;
        
        try {
            int idProduto = Integer.parseInt(view.getjCodProduto().getText());
            
            Insumos insumos = new Insumos(idProduto);
            
            Connection conexao = new Conexao().getConnection();
            InsumosDAO insumosDAO = new InsumosDAO(conexao);
            ArrayList<Insumos> insumosBD = insumosDAO.selectInsumosId(insumos);
            
            
            for(Insumos insumo: insumosBD){
   
                custoProduto += insumo.getCustoInsumo() * insumo.getQtdPorUnidade();
                
            }
            
            return custoProduto;
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerFormInsumos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
        
    }
    
    public void exibeCusto(){
        
        double custoProduto = calcularCusto();
        String custoString = String.valueOf(custoProduto);

        view.getjCustoProduto().setText(custoString);
        
    }
}
