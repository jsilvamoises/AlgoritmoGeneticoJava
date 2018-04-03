/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author moises
 */
public class Run {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Produto> produtos = new ArrayList<>();
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

        /*  produtos.add(new Produto("Geladeira Dako", 0.751, 999.90));
        produtos.add(new Produto("IPhone", 0.000089, 2911.12));
        produtos.add(new Produto("TV 55' ", 0.400, 4346.99));
        produtos.add(new Produto("TV 50' ", 0.290, 3999.90));
        produtos.add(new Produto("TV 42' ", 0.200, 2999.00));
        produtos.add(new Produto("Notebook Dell ", 0.00350, 2499.90));
        produtos.add(new Produto("Ventilador Panasonic", 0.496, 199.90));
        produtos.add(new Produto("Microondas Eletrolux", 0.0424, 308.66));
        produtos.add(new Produto("Microondas LG", 0.0544, 429.90));
        produtos.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        produtos.add(new Produto("Geladeira Brastemp", 0.635, 849.00));
        produtos.add(new Produto("Geladeira Consul", 0.870, 1189.89));
        produtos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        produtos.add(new Produto("Notebook Asus", 0.527, 3999.00));
         */
        List espacos = new ArrayList<>();
        List valores = new ArrayList<>();
        List nomes = new ArrayList<>();

        produtos.forEach(p -> {
            espacos.add(p.getEspaco());
            valores.add(p.getValor());
            nomes.add(p.getNome());
        });

        Double limite = 10.0;
        int populacao = 1000;
        Double taxaMutacao = 0.01;
        int numeroGeracoes = 2000;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(populacao);

        List resultado = ag.resolver(taxaMutacao, numeroGeracoes, espacos, valores, limite);

        for (int linha = 0; linha < resultado.size(); linha++) {
            if (resultado.get(linha).equals("1")) {
                Produto p = produtos.get(linha);
                System.out.println(p.getNome() + " - " + p.getValor());
            }
        }

        Grafico g = new Grafico("Grafico", "Melhores soluções", ag.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);

        System.gc();

    }
}

//        AlgoritmoGenetico ag = new AlgoritmoGenetico(populacao);
//        ag.inicializarPopulacao(espacos, valores, limite);
//        ag.individuos.forEach(i ->{
//            i.avaliacao();           
//        });
//        ag.ordenarPopulacao();
//        ag.melhorIndividuo(ag.getIndividuos().get(0));
//        ag.printMelhorSolucao();
//        Double soma = ag.somaAvaliacoes();
//        
//        Double probabilidadeMutacao = 0.01;
//        List<Individuo> novosIndividuos = new ArrayList<>();
//        
//        for(int i =0; i< ag.getIndividuos().size()/2;i++){
//            int pai = ag.selecionaPai(soma);
//            int mae = -1;
//            do{
//                mae = ag.selecionaPai(soma);
//            }while(pai == mae);
//           
//            List<Individuo> filhos = ag.getIndividuos().get(pai).crossover(ag.getIndividuos().get(mae));
//            
//            novosIndividuos.add(filhos.get(0).mutacao(probabilidadeMutacao));
//            novosIndividuos.add(filhos.get(1).mutacao(probabilidadeMutacao));
//        }
//        
//        ag.setIndividuos(novosIndividuos);
//        ag.getIndividuos().forEach(i ->{
//            i.avaliacao();
//        });
//        ag.ordenarPopulacao();
//        ag.melhorIndividuo(ag.getIndividuos().get(0));
//        soma = ag.somaAvaliacoes();
//        ag.printMelhorSolucao();
//        JFrame frame = new JFrame("Teste");
//        frame.setSize(200, 200);
//        frame.setVisible(true);
