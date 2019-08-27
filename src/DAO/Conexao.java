/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    public Connection getConnection() throws SQLException{
        
        String url = "jdbc:postgresql://localhost:5432/Composerz";
        String usuario = "postgres";
        String senha = "fine";
        
        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        
        return conexao;
    }
    
}
