package util;

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartUtilities;

public class PieChart3D {

    

  
    boolean getPieChart3D(String ruta, double[] costos,String tipos[],String tipo) throws IOException {

        DefaultPieDataset dataset = null;

        crearDataset(dataset, costos,tipos);

        if (dataset != null) {
            crearJChart(dataset, ruta,tipo);
            return true;
        } else {
            return false;
        }
    }

    private void crearDataset(DefaultPieDataset dataset, double[] costos,String tipoCosto[]) {
        dataset = new DefaultPieDataset();
        for (int i = 0; i < costos.length; i++) {
            if (costos[i] != 0) {
                dataset.setValue(tipoCosto[i], costos[i]);
            }
        }
    }

    private void crearJChart(DefaultPieDataset dataset, String ruta, String tipo) throws IOException {

        JFreeChart chart=null;
        if(tipo.equalsIgnoreCase("hac"))
        {
            chart = ChartFactory.createPieChart3D(
                "COSTOS FIJOS", // chart title                   
                dataset, // data 
                true, // include legend                   
                true,
                false);
        }
        else{
            chart = ChartFactory.createPieChart3D(
                "COSTOS ESTABLECIMIENTO", // chart title                   
                dataset, // data 
                true, // include legend                   
                true,
                false);
        }

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(300);
        plot.setForegroundAlpha(1);
        plot.setInteriorGap(0.05);
        int width = 640; /* Width of the image */

        int height = 480; /* Height of the image */

        File pieChart3D = new File(ruta + "pieChart3D.jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart3D, chart, width, height);

    }
}
