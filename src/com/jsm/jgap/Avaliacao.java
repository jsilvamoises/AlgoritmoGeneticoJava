/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm.jgap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author moises
 */
public class Avaliacao extends FitnessFunction{
    private final AlgoritmoGenetico ag;

    public Avaliacao(AlgoritmoGenetico ag) {
        this.ag = ag;
    }
    
    
    

    @Override
    protected double evaluate(IChromosome ich) {
        Double nota = 0.00;
        Double espacos = 0.00;
        
        for(int i = 0 ; i< ich.size() ; i++){
            if(ich.getGene(i).getAllele().toString().equals("1")){
                nota+= ag.produtos.get(i).getValor();
                espacos+= ag.produtos.get(i).getEspaco();
            }
        }
        if(espacos > ag.limite){
            nota = 1.0;
        }
        return nota;
    }
    
}
