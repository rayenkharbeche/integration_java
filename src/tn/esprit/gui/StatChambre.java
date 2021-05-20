/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import tn.esprit.gui.MenuCategoryChambre;
import java.util.List;
import tn.esprit.entities.Chambre;
import tn.esprit.entities.Users;
import tn.esprit.services.ServiceChambre;

/**
 *
 * @author amina
 */
public class StatChambre extends Form{
        Form current;

        public StatChambre(Users u) {
        setTitle("Liste des Chambre");
             int nbapp=0;
             int nbnonapp=0;
             
             List<Chambre> lista = ServiceChambre.getInstance().getAllchambres();;
             for (Chambre fi : lista) {
                 if(fi.getEtat().equals("Disponible"))
                 nbapp++;
                 else{
                     nbnonapp++;
                 }
             }
             //stat al nombre de chambre par cat

                 double[] values = new double[]{nbapp,nbnonapp};
                 
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(100);
    renderer.setChartTitle("Statistique");
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.CYAN);
    r.setGradientStop(0, ColorUtil.YELLOW);
    r.setHighlighted(true);
    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget",values), renderer);
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);
    add(c);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Categorys", null, e -> new MenuCategory(u).show());
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(40);
    renderer.setLegendTextSize(40);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    /*
    int k = 0;
    for (double value : values) {
        series.add("Nombre " + ++k, value);
    }
    */
    series.add("Non Disponible" ,values[0]);
    series.add("Disponible" ,values[1]);
    return series;
}

}
