/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm.jgap;

import com.jsm.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moises
 */
public class ProdutoRepository {
    public List<Produto> produtos(){
        List<Produto> produtos = new ArrayList<>();
        try {
            
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/AG", "root", "1982");
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PRODUTO");
            
            while (rs.next()) {
                for (int i = 0; i < rs.getInt("QTD"); i++) {
                    produtos.add(new Produto(rs.getString("NOME"), rs.getDouble("ESPACO"), rs.getDouble("VLR")));
                }
                
            }

            conn.close();
            rs.close();
            
            
            
            return produtos;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }
}
