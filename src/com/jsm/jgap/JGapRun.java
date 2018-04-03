/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm.jgap;

import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

/**
 *
 * @author moises
 */
public class JGapRun {
    public static void main(String[] args) throws InvalidConfigurationException {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(10000, 100,1.0);
        ag.carregarDados();
        ag.processarDados();
        ag.procurarMelhor();
        
        IChromosome melhor = ag.melhor;
        int geracao = ag.getGeracaoMelhor();
        
        System.out.println("###### MELHOR SOLUCAO #######");
        ag.visualizaGeracacao(melhor, geracao);
        ag.printProdutosCarregamento();
        ag.showGrafico();
    }
}
