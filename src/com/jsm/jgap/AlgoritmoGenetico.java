/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm.jgap;

import com.jsm.Grafico;
import com.jsm.Produto;
import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;

/**
 *
 * @author moises
 */
public class AlgoritmoGenetico {

    Configuration conf;
    private int numeroGeracoes;
    Double limite = 3.0;
    private int tamanhoPopulacao;
    int taxaMutacao = 100; // = 1/100 ou 0.01

    List<IChromosome> melhores = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();
    IChromosome melhor;
    
    private int geracaoMelhor = 0;

    public AlgoritmoGenetico(int numeroGeracoes, int tamanhoPopulacao,Double limite) {
        this.numeroGeracoes = numeroGeracoes;
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.limite = limite;
    }
    
    

    public void carregarDados() {
        ProdutoRepository pr = new ProdutoRepository();
        produtos = pr.produtos();
    }

    public Double somaEspacos(IChromosome ich) {
        Double sum = 0.00;
        for (int i = 0; i < ich.size(); i++) {
            if (ich.getGene(i).getAllele().toString().equals("1")) {
                Produto p = produtos.get(i);
                sum += p.getEspaco();
            }
        }
        return sum;
    }

    public void visualizaGeracacao(IChromosome ich, int geracao) {
        List lista = new ArrayList<>();
        Gene[] gene = ich.getGenes();
        for (int i = 0; i < ich.size(); i++) {
            lista.add(gene[i].getAllele().toString() + " ");
        }
        System.out.println("----------------------------------------------------");
        System.out.println(" Geração......: " + geracao);
        System.out.println(" Vlr..........: " + ich.getFitnessValue());
        System.out.println(" Espaco usado.: " + somaEspacos(ich));
        System.out.println(" Cromossomo...: " + lista);
    }
    
    
    public IChromosome createChromosome() throws InvalidConfigurationException{
        Gene[] genes = new Gene[produtos.size()];
        
        for(int i =0; i< genes.length;i++){
            genes[i] = new IntegerGene(conf, 0, 1);
            genes[i].setAllele(i);
        }
        
        IChromosome modelo = new Chromosome(conf, genes);
        return modelo;
    }
    
    public FitnessFunction fitness(){
        return new Avaliacao(this);
    }
    
    public Configuration initConfiguration() throws InvalidConfigurationException{
        Configuration config = new Configuration();        
        config.removeNaturalSelectors(true);
        config.addNaturalSelector(new BestChromosomesSelector(config, 0.4), true);
        config.setRandomGenerator(new StockRandomGenerator());
        config.addGeneticOperator(new CrossoverOperator(config));        
        config.addGeneticOperator(new SwappingMutationOperator(config,taxaMutacao));
        config.setKeepPopulationSizeConstant(true);
        config.setEventManager(new EventManager());
        config.setFitnessEvaluator(new DefaultFitnessEvaluator());
        return config;
        
        
    }
    
    
    public void processarDados() throws InvalidConfigurationException{
        conf = initConfiguration();
        FitnessFunction ff =fitness();
        conf.setFitnessFunction(ff);
        IChromosome ich = createChromosome();
        conf.setSampleChromosome(ich);
        conf.setPopulationSize(tamanhoPopulacao);
        IChromosome[] cromossomos = new IChromosome[tamanhoPopulacao];
        for(int i = 0; i< tamanhoPopulacao; i++){
            cromossomos[i]=createChromosome();
        }
        
        Genotype populacao = new Genotype(conf, new Population(conf, cromossomos));
        
        for(int j =0; j< numeroGeracoes;j++){
            visualizaGeracacao(populacao.getFittestChromosome(), j);
            melhores.add(populacao.getFittestChromosome());
            populacao.evolve();
        }
    }

    public IChromosome procurarMelhor() {
        for(int i =0; i < this.melhores.size(); i++){
            if(this.melhor == null){
                this.melhor = this.melhores.get(i);
            }else if(this.melhor.getFitnessValue() < this.melhores.get(i).getFitnessValue()){
                this.melhor = this.melhores.get(i);
                geracaoMelhor = i;
            }
        }
        
        return melhor;
    }
    
    
    public void printProdutosCarregamento(){
        System.out.println("#### PRODUTOS PARA O CARREGAMENTO ####");
        for(int i =0; i< produtos.size();i++){
            if(melhor.getGene(i).getAllele().toString().equals("1")){
                Produto p = produtos.get(i);
                System.out.println("Nome.:"+p.getNome());
            }
        }
    }

    public int getGeracaoMelhor() {
        return geracaoMelhor;
    }

    
    public void showGrafico(){
        GraficoJGap g = new GraficoJGap("Algoritimo Genético", "Melhores soluções", melhores);
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);
    }
    
    
}
