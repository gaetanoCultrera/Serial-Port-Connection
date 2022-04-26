package Progetto_Java;

import org.jfree.chart.ChartPanel;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graphics extends ApplicationFrame {

   public Graphics(String applicationTitle,String chartTitle) {
      super(applicationTitle);
      //creazione grafico
      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Years","T Media(Celsius)",addData(), PlotOrientation.VERTICAL,true,false,false);
         
      ChartPanel Panel = new ChartPanel(lineChart);
      Panel.setSize(new Dimension(560,367));
      setContentPane(Panel);
   }

   //inserimento dati della temperatura
   private DefaultCategoryDataset addData() {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      dataset.addValue(19,"Temperature","1973");
      dataset.addValue(16,"Temperature","1980");
      dataset.addValue(18,"Temperature","1990");
      dataset.addValue(18,"Temperature","2005");
      dataset.addValue(19,"Temperature","2015");
      dataset.addValue(20,"Temperature","2020");
      return dataset;
   }
}