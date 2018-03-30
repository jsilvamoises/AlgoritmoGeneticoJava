/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author moises
 */
public class AlgoritmoGenetico {
    private int tamanhoPopulucao;
    List<Individuo> individuos = new ArrayList<>();
    int geracao;
    private Individuo melhorSolucao;

    public AlgoritmoGenetico() {
    }

    public AlgoritmoGenetico(int tamanhoPopulucao) {
        this.tamanhoPopulucao = tamanhoPopulucao;
    }
    
    public void inicializarPopulacao(List espacos,List valores, Double limiteEspacao){
        for(int i =0 ;i < tamanhoPopulucao; i++){
            individuos.add(new Individuo(espacos, valores, limiteEspacao));
        }
        
        this.melhorSolucao = individuos.get(0);
    }
    
    public void melhorIndividuo(Individuo indv){
        if(indv.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()){
            this.melhorSolucao = indv;
        }
    }
    Double soma = 0.00;
    public Double somaAvaliacoes(){
        soma = 0.00;
        this.individuos.forEach(i ->{
            soma+= i.getNotaAvaliacao();
        });
        
        return soma;
    }
    
    public void printMelhorSolucao(){
        System.out.println("*** MELHOR SOLUÇÃO ***");
        System.out.println("Nota.......: "+this.melhorSolucao.getNotaAvaliacao());
        System.out.println("Cromossomo.: "+this.melhorSolucao.getCromossomo());
    }
    
    public void ordenarPopulacao(){
        Collections.sort(individuos);
    }

    public int getTamanhoPopulucao() {
        return tamanhoPopulucao;
    }

    public void setTamanhoPopulucao(int tamanhoPopulucao) {
        this.tamanhoPopulucao = tamanhoPopulucao;
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(List<Individuo> individuos) {
        this.individuos = individuos;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    
    
    
}
