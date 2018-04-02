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
    int geracao = 0;
    private Individuo melhorSolucao;
    
    private List<Individuo> melhoresCromossomos = new ArrayList<>();

    public AlgoritmoGenetico() {
    }

    public AlgoritmoGenetico(int tamanhoPopulucao) {
        this.tamanhoPopulucao = tamanhoPopulucao;
    }

    public void inicializarPopulacao(List espacos, List valores, Double limiteEspacao) {
        for (int i = 0; i < tamanhoPopulucao; i++) {
            individuos.add(new Individuo(espacos, valores, limiteEspacao));
        }

        this.melhorSolucao = individuos.get(0);
    }

    public void melhorIndividuo(Individuo indv) {
        if (indv.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
            this.melhorSolucao = indv;
        }
    }
    Double soma = 0.00;

    public Double somaAvaliacoes() {
        soma = 0.00;
        this.individuos.forEach(i -> {
            soma += i.getNotaAvaliacao();
        });

        return soma;
    }

    public int selecionaPai(Double somaAvaliacao) {
        int position = -1;
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.00;
        int i = 0;

        while (i < this.individuos.size() && soma < valorSorteado) {
            soma += this.individuos.get(i).getNotaAvaliacao();
            position += 1;
            i += 1;

        }
        return position;
    }

    public void visualizaGeracao() {
        System.out.println("====================================================");
        Individuo melhor = this.getIndividuos().get(0);
        System.out.println("Geração....:" + melhor.getGeracao());
        System.out.println("Valor......:" + melhor.getNotaAvaliacao());
        System.out.println("Espaço.....:" + melhor.getEspacoUsado());
        System.out.println("Cromossomo.:" + melhor.getCromossomo());
    }

    public List resolver(Double taxaMutacao, int numeroGeracao, List espacos, List valores, Double limitEspcos) {
        this.inicializarPopulacao(espacos, valores, limitEspcos);
        this.avaliarIndividuos();
        for (int geracao = 0; geracao < numeroGeracao; geracao++) {
            Double somaAvaliacao = this.somaAvaliacoes();
            List<Individuo> novosIndividuos = new ArrayList<>();
            for (int i = 0; i < this.individuos.size() / 2; i++) {
                int x = this.selecionaPai(somaAvaliacao);
                int y = this.selecionaPai(somaAvaliacao);

                Individuo AX = this.individuos.get(x);
                Individuo BY = this.individuos.get(y);
                List<Individuo> filhos = AX.crossover(BY);
                novosIndividuos.add(filhos.get(0).mutacao(taxaMutacao));
                novosIndividuos.add(filhos.get(1).mutacao(taxaMutacao));
            }
            
            //this.updateGeracao(geracao);
            this.setIndividuos(novosIndividuos);
            this.avaliarIndividuos();
        }
        printMelhorSolucao();
        return this.getMelhorSolucao().getCromossomo();
    }

    
//    public void updateGeracao(int geracao){
//        this.individuos.forEach(i->{i.setGeracao(geracao);});
//        this.setGeracao(geracao);
//    }
    public void avaliarIndividuos() {
        this.individuos.forEach(i -> {
            i.avaliacao();
        });
        this.ordenarPopulacao();
        this.visualizaGeracao();
        Individuo melhor = this.getIndividuos().get(0);
        
        this.setMelhorSolucao(melhor);

        
        
    }

    public void printMelhorSolucao() {
        System.out.println("####### MELHOR SOLUÇÃO #############################");
        System.out.println("Geração......: " + this.melhorSolucao.getGeracao());
        System.out.println("Nota.........: " + this.melhorSolucao.getNotaAvaliacao());
        System.out.println("Espaco Usado.: " + this.melhorSolucao.getEspacoUsado());
        System.out.println("Cromossomo...: " + this.melhorSolucao.getCromossomo());
        System.out.println("---------------------------------------------------");
    }

    public void ordenarPopulacao() {
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
        this.melhoresCromossomos.add(melhorSolucao);
        if(melhorSolucao.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()){
            this.melhorSolucao = melhorSolucao;
            
            printMelhorSolucao();
        }
        
    }

    public List<Individuo> getMelhoresCromossomos() {
        return melhoresCromossomos;
    }

    public void setMelhoresCromossomos(List<Individuo> melhoresCromossomos) {
        this.melhoresCromossomos = melhoresCromossomos;
    }

}
