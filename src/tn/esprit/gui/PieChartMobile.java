/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import tn.esprit.entities.GetRDV;
import tn.esprit.services.ServiceRendez;


/**
 *
 * @author SBS
 */
public class PieChartMobile {
    
    ServiceRendez sr=ServiceRendez.getInstance();
     public String getName() {
    return "Budget chart";
    }

  
    public String getDesc() {
     return "(pie chart)";
    }
     
          /*   
        
               //  b = new SpanLabel("•• Ajout nouceau question ••");
        ServiceQuestion sq = new ServiceQuestion();
    
        int lenght =sq.getListQuestion().size();
       int i=0;
         double[] values  = new double[20] ;
   
             for(Question q: sq.getListQuestion() )
        {
         ServiceReponse sr = new  ServiceReponse();   
             varGlobales.setId(q.getId());
   values[i]= (sr.getDetailQuestion().size());
            i++;
   
        }
             
  */
             
             
             
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
    int k = 0;
    
       // ServiceQuestion sq = new ServiceQuestion();

    
   for (double value : values) {
       
      //  series.add(sr.getListQuestion().get(k).getTitre_question(), value);
      series.add(sr.getAllRDV().toString(),value);
        k++;
   }
  
    return series;
}
 public Form createPieChartForm() throws Exception {
     
    // Generate the values
   // double[] values = new double[]{12, 14, 11, 10, 19};
    //   ServiceRendez sq = new ServiceRendez();
    
        int lenght =13;
       int i=0;
      
         double[] values  = new double[lenght] ;
    int[] colors  = new int[lenght] ;
    /*         for(RendezVous q: sq.getAllRDV() )
        {
         ServicePlanning sr = new  ServicePlanning(); 
       
             varGlobales.setId(q.getId());
   values[i]= (sr.getAllPlanning().size());
  
   colors[i]=ColorUtil.GRAY;
            i++;
           
   
        }*/
             GetRDV getrdv= new GetRDV();
              CategorySeries series = new CategorySeries("Statistique");
             
       /*       for(RendezVous q: sq.getAllRDV() )
        {
        varGlobales.setId(q.getId());
            values[i]= (getrdv.Jan());
  
            colors[i]=ColorUtil.GRAY;
              i++;
   
        }*/
             
                for(int l=1;l<=12;l++)
        {
            if (l==1){
               // series.add("Janvier", l);
               varGlobales.setNom("Janvier");
            values[l]= (getrdv.Jan());
           //  series.add("Janvier", values[l]);
            colors[l]=ColorUtil.GRAY;
              i++; 
            }
            if (l==2){
               varGlobales.setNom("Fevrier");
            values[l]= (getrdv.Feb());
       // series.add("Fervrier", values[l]);
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.WHITE;
              i++; 
            }
            if (l==3){
               varGlobales.setNom("Mars");
            values[l]= (getrdv.Mars());
  //series.add("Janvier", values[l]);
            colors[l]=ColorUtil.MAGENTA;
            colors[l]=ColorUtil.WHITE;
              i++; 
            }
             if (l==4){
               varGlobales.setNom("Avril");
            values[l]= (getrdv.Avril());
  
            colors[l]=ColorUtil.YELLOW;
            colors[l]=ColorUtil.BLUE;
              i++; 
            }
            if (l==6){
               varGlobales.setNom("Juin");
            values[l]= (getrdv.Juin());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.BLACK;
              i++; 
            }
            if (l==5){
               varGlobales.setNom("Mai");
            values[l]= (getrdv.Mai());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.YELLOW;
              i++; 
            }
            if (l==7){
               varGlobales.setNom("Juillet");
            values[l]= (getrdv.Juillet());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.CYAN;
              i++; 
            }
            if (l==8){
               varGlobales.setNom("Aout");
            values[l]= (getrdv.Aout());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.BLUE;
            colors[l]=ColorUtil.WHITE;
              i++; 
            }
            if (l==9){
               varGlobales.setNom("Septembre");
            values[l]= (getrdv.Sept());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.WHITE;
            colors[l]=ColorUtil.GREEN;
              i++; 
            }
             if (l==10){
               varGlobales.setNom("October");
            values[l]= (getrdv.Oct());
  
            colors[l]=ColorUtil.MAGENTA;
            colors[l]=ColorUtil.BLUE;
            colors[l]=ColorUtil.WHITE;
              i++; 
            }
            if (l==11){
               varGlobales.setNom("Nouvembre");
            values[l]= (getrdv.Nouvbre());
  
            colors[l]=ColorUtil.GRAY;
            colors[l]=ColorUtil.YELLOW;
            colors[l]=ColorUtil.BLUE;
              i++; 
            }
            if (l==12){
               varGlobales.setNom("Decembre");
            values[l]= (getrdv.Dec());
  
            colors[l]=ColorUtil.LTGRAY;
              i++; 
            }
            
        }    
    
   
   
    // Set up the renderer
   // int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.YELLOW);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.

        final CategorySeries seriesSet = buildCategoryDataset("Project budget", values);
    final com.codename1.charts.views.PieChart chart = new com.codename1.charts.views.PieChart(seriesSet, renderer);
    
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("PieChart des Rendez-vous", new BorderLayout());
     
    //f.getToolbar().addCommandToRightBar("retour", null, (ev)->{Accueil h=new Accueil();
        //  h.getF().show();
      //    });
      
       
    //f.getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
    //      });
    f.getAllStyles().setBgColor(0x6586F7);
    f.add(BorderLayout.CENTER, c);
    
    
    
    return f;

}       
 
     public Form getF() throws Exception {
        return createPieChartForm();
        
    }
    
}
