/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Insumos {
    
    private int id;
    private String descricao;
    private double qtdPorUnidade;
    private double custoInsumo;
    private int idProduto;

    public Insumos(String descricao, double qtdPorUnidade, double custoInsumo, int idProduto) {
        this.descricao = descricao;
        this.qtdPorUnidade = qtdPorUnidade;
        this.custoInsumo = custoInsumo;
        this.idProduto = idProduto;
    }

    public Insumos(int idProduto) {
        this.idProduto = idProduto;
    }
    
    

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtdPorUnidade() {
        return qtdPorUnidade;
    }

    public void setQtdPorUnidade(int qtdPorUnidade) {
        this.qtdPorUnidade = qtdPorUnidade;
    }

    public double getCustoInsumo() {
        return custoInsumo;
    }

    public void setCustoInsumo(double custoInsumo) {
        this.custoInsumo = custoInsumo;
    }
    
    
    
    
}
