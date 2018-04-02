/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author moises
 */
public class Grafico extends ApplicationFrame{
    private List<Individuo> melhores = new ArrayList<>();
    int i =0;
    private DefaultCategoryDataset carregarDados(){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        
        melhores.forEach(m -> {
            dados.addValue(m.getNotaAvaliacao(), "Melhores Soluções", ""+(i++));
        });
        
        return dados;
    }
    public Grafico(String title) {
        super(title);
    }
    
    public Grafico(String title,String tituloGrafico,List<Individuo> melhores) {
        super(title);
        this.melhores = melhores;
        JFreeChart chart = ChartFactory.createLineChart(tituloGrafico, "Geração", "Valor", carregarDados(),PlotOrientation.VERTICAL,true, true , false);
        
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800,600));
        setContentPane(panel);
    }
}
