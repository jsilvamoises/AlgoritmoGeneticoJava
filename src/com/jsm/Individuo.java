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
public class Individuo implements Comparable<Individuo> {

    private List espacos = new ArrayList<>();
    private List valores = new ArrayList<>();
    private List cromossomo = new ArrayList<>();

    private Double limiteEspaco;
    private Double notaAvaliacao;
    private Double espacoUsado;

    private int geracao;

    public Individuo() {
    }

    public Individuo(List espacos, List valores, Double limiteEspaco) {
        this.limiteEspaco = limiteEspaco;
        this.espacos = espacos;
        this.valores = valores;
        this.notaAvaliacao = 0.00;
        this.geracao = 0;
        this.espacoUsado = 0.00;
        inicializarCromossomos();
    }

    private void inicializarCromossomos() {
        espacos.forEach(e -> {
            if (Math.random() < 0.5) {
                Individuo.this.cromossomo.add("0");
            } else {
                Individuo.this.cromossomo.add("1");
            }
        });
    }
    
    

    public void avaliacao() {
        Double nota = 0.00;
        Double somaEspacos = 0.00;
        for (int i = 0; i < cromossomo.size(); i++) {
            if (this.cromossomo.get(i).equals("1")) {
                nota += (Double) this.valores.get(i);
                somaEspacos += (Double) this.espacos.get(i);
            }
        }

        if (somaEspacos > this.limiteEspaco) {
            nota = 1.0;
        }

        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }

    public List crossover(Individuo other) {
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        List filho1 = new ArrayList<>();
        List filho2 = new ArrayList<>();
        filho1.addAll(other.getCromossomo().subList(0, corte));
        filho1.addAll(this.getCromossomo().subList(corte, this.cromossomo.size()));

        filho2.addAll(this.getCromossomo().subList(0, corte));
        filho2.addAll(other.getCromossomo().subList(corte, this.cromossomo.size()));

        List<Individuo> filhos = new ArrayList<>();

        filhos.add(new Individuo(espacos, valores, limiteEspaco));
        filhos.add(new Individuo(espacos, valores, limiteEspaco));

        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(geracao++);

        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(geracao++);

        return filhos;
    }

    public Individuo mutacao(Double taxaMutacao) {
        System.out.println("Antes da Mutacão.: " + this.cromossomo);
        for (int i = 0; i < this.cromossomo.size(); i++) {
            if (Math.random() < taxaMutacao) {
                if (cromossomo.get(i).equals("1")) {
                    cromossomo.set(i, "0");
                } else {
                    cromossomo.set(i, "1");
                }

            }
        }

        System.out.println("Depois da mutação: " + this.cromossomo);
        return this;
    }

    public void printNotaEspacaoUsado() {
        System.out.println("Nota............: " + this.notaAvaliacao);
        System.out.println("Espaço Usado....: " + this.espacoUsado);
    }

    public List getEspacos() {
        return espacos;
    }

    public void setEspacos(List espacos) {
        this.espacos = espacos;
    }

    public List getValores() {
        return valores;
    }

    public void setValores(List valores) {
        this.valores = valores;
    }

    public List getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List cromossomo) {
        this.cromossomo = cromossomo;
    }

    public Double getLimiteEspaco() {
        return limiteEspaco;
    }

    public void setLimiteEspaco(Double limiteEspaco) {
        this.limiteEspaco = limiteEspaco;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public Double getEspacoUsado() {
        return espacoUsado;
    }

    public void setEspacoUsado(Double espacoUsado) {
        this.espacoUsado = espacoUsado;
    }

    public void print() {
        System.out.println("Espaçoes...: " + this.espacos);
        System.out.println("Valores....: " + this.valores);
        System.out.println("Cromossomo.: " + this.cromossomo);
    }

    public void printComponetsDaCarga(List<Produto> produtos) {
        for (int i = 0; i < produtos.size(); i++) {
            if (this.cromossomo.get(i).equals("1")) {
                StringBuilder sb = new StringBuilder();
                sb.append("Nome.: ").append(produtos.get(i).getNome()).append(" ");
                sb.append("R$...: ").append(produtos.get(i).getValor()).append("");

                System.out.println(sb.toString());
            }
        }
    }

    @Override
    public int compareTo(Individuo o) {
        if(this.getNotaAvaliacao() > o.getNotaAvaliacao()){
            return -1;
        }else if(this.getNotaAvaliacao() > o.getNotaAvaliacao()){
            return 1;
        }
        return 0;
    }
}
