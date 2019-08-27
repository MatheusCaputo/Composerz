/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.CadastroProduto;
import View.CadastroInsumos;
import View.ConsultarCustoProduto;
import View.ConsultarProdutos;
import View.MenuPrincipal;


public class ControllerMenuPrincipal {

    private final MenuPrincipal view;

    public ControllerMenuPrincipal(MenuPrincipal view) {
        this.view = view;
    }
    
    
    public void opcCadProduto(){
        CadastroProduto cf = new CadastroProduto();
        cf.setVisible(true);
    }
    
    public void opcCadInsumos(){
        CadastroInsumos ci = new CadastroInsumos();
        ci.setVisible(true);
    }
    
    public void opcConsultProduto(){
        ConsultarProdutos cp = new ConsultarProdutos();
        cp.setVisible(true);
    }
    
    public void opcConsultCusto(){
        ConsultarCustoProduto ccp = new ConsultarCustoProduto();
        ccp.setVisible(true);
    }
}
