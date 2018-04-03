/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsm.jgap;

import com.jsm.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jgap.IChromosome;

/**
 *
 * @author moises
 */
public class GraficoJGap extends ApplicationFrame{
    private List<IChromosome> melhores = new ArrayList<>();
    int i =0;
    private DefaultCategoryDataset carregarDados(){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        
        melhores.forEach(m -> {
            dados.addValue(m.getFitnessValue(), "Melhores Soluções", ""+(i++));
        });
        
        return dados;
    }
    public GraficoJGap(String title) {
        super(title);
    }
    
    public GraficoJGap(String title,String tituloGrafico,List melhores) {
        super(title);
        this.melhores = melhores;
        JFreeChart chart = ChartFactory.createLineChart3D(tituloGrafico, "Geração", "Valor", carregarDados(),PlotOrientation.VERTICAL,true, true , false);
        
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800,600));
        setContentPane(panel);
    }
}
