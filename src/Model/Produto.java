/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Produto {
    
    private String nome;
    private int id;
    private String unidadeMedida;
    private int prazoValidade;

    public Produto(String nome, int id, String unidadeMedida, int prazoValidade) {
        this.nome = nome;
        this.id = id;
        this.unidadeMedida = unidadeMedida;
        this.prazoValidade = prazoValidade;
    }

    public Produto(int id) {
        this.id = id;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public int getPrazoValidade() {
        return prazoValidade;
    }

    public void setPrazoValidade(int prazoValidade) {
        this.prazoValidade = prazoValidade;
    }
    
    
    
    
}
