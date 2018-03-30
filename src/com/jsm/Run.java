/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moises
 */
public class Run {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        
        produtos.add(new Produto("Geladeira Dako",0.751,999.90));
        produtos.add(new Produto("IPhone",0.000089,2911.12));
        produtos.add(new Produto("TV 55' ",0.400,4346.99));
        produtos.add(new Produto("TV 50' ",0.290,3999.90));
        produtos.add(new Produto("TV 42' ",0.200,2999.00));
        produtos.add(new Produto("Notebook Dell ",0.00350,2499.90));
        produtos.add(new Produto("Ventilador Panasonic",0.496,199.90));
        produtos.add(new Produto("Microondas Eletrolux",0.0424,308.66));
        produtos.add(new Produto("Microondas LG",0.0544,429.90));
        produtos.add(new Produto("Microondas Panasonic",0.0319,299.29));
        produtos.add(new Produto("Geladeira Brastemp",0.635,849.00));
        produtos.add(new Produto("Geladeira Consul",0.870,1189.89));
        produtos.add(new Produto("Notebook Lenovo",0.498,1999.90));
        produtos.add(new Produto("Notebook Asus",0.527,3999.00));
        
        List espacos  = new ArrayList<>();
        List valores = new ArrayList<>();
        List nomes = new ArrayList<>();
        
        produtos.forEach(p ->{
            espacos.add(p.getEspaco());
            valores.add(p.getValor());
            nomes.add(p.getNome());
        });
        /*
        
        Individuo pai = new Individuo(espacos, valores, limite);
        Individuo mae = new Individuo(espacos, valores, limite);
       
        Individuo[] inds ={pai,mae};
        
        for(Individuo id:inds){
            id.print();
            id.avaliacao();
            id.printComponetsDaCarga(produtos);
            id.printNotaEspacaoUsado();
        }*/
        Double limite = 3.0;
        int populacao = 20;
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(populacao);
        ag.inicializarPopulacao(espacos, valores, limite);
        ag.individuos.forEach(i ->{
            i.avaliacao();           
        });
        ag.ordenarPopulacao();
        ag.melhorIndividuo(ag.getIndividuos().get(0));
        ag.printMelhorSolucao();
        System.out.println("Soma: "+ag.somaAvaliacoes());
        
    }
}
