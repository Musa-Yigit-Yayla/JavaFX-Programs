/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sinecosinecubicfunctions;

/**
 *
 * @author yigit
 */import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
public class SineCosineCubicFunctions extends Application {

    @Override
    public void start(Stage primaryStage){
        { Pane pane = new Pane();
        pane.setPrefSize(800,600);
        pane.setPadding(new Insets(10,10,10,10));
        Polyline x3 =  new Polyline();
      ObservableList<Double> list =  x3.getPoints();
      
      //add the points
      final double scaleFactor = 0.000125; //0.0125 is the original
      for(double x = 250; x <= 550; x++){
        if(x <= 400){
          list.addAll(x,(300 - ((Math.pow((x - 400), 3))) * scaleFactor));
        }
        else{
            list.addAll(x,(300 - ((Math.pow((x - 400), 3))) * scaleFactor));
        }
      }
      
      drawArrowLine(400,600,400,50,pane);
      drawArrowLine(0,300,750,300,pane);
      Text textX = new Text(750,255,"X");
      Text textY = new Text(445,50,"Y");
  
      /*drawArrowLine(30,500,400,30,pane);
      drawArrowLine(100,50,200,50,pane);
      drawArrowLine(500,500,300,300,pane);
      drawArrowLine(500,600,500,50,pane);
      drawArrowLine(400,30,30,500,pane);
      drawArrowLine(200,135,600,115,pane);*/
      pane.getChildren().addAll(x3,textX,textY);
      Scene scene = new Scene(pane);
      primaryStage.setTitle("x ^ 3");
      primaryStage.setScene(scene);
      primaryStage.show();
        }
      
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane){
        Line line = new Line(startX,startY,endX,endY);
        final double slope = (endY - startY) / (endX - startX);        
        final double r = 25;
          double m = Math.atan(slope * Math.PI / 180); 
        
         if(slope == 0){
            Polyline xWings = new Polyline();
            if(endX > startX){
                xWings.getPoints().addAll(endX - r * Math.sqrt(2) / 2,endY + r * Math.sqrt(2) / 2,endX,endY,endX - r * Math.sqrt(2) / 2,endY - r * Math.sqrt(2) / 2);
            }
            else{
                xWings.getPoints().addAll(endX + r * Math.sqrt(2) / 2,endY + r * Math.sqrt(2) / 2,endX,endY,endX + r * Math.sqrt(2) / 2,endY - r * Math.sqrt(2) / 2);
            }
            pane.getChildren().addAll(line,xWings);
            return;
         }
          
        /*Polyline wings = new Polyline(endX + 25, endY,endX,endY,endX,endY + 25);
        
         double m = Math.atan(slope * Math.PI / 180); 
         if(m < 0){
             m = -m;
         }
         double alpha = 90 - m;
         if(m < 45){
             alpha = m;
         }
         wings.setRotate(m - 45);
          pane.getChildren().addAll(line,wings);
                       */         
        
       
            m = 1 / m + 90; 
          System.out.println(m);
        //Left wing
        double x1 = endX - r * Math.cos(Math.PI / 180 * (m - 45));
        double y1 = endY + r * Math.sin(Math.PI / 180 * (m - 45));
        if(endY > startY){
            x1 = endX + r * Math.cos(Math.PI / 180 * (m - 45));
            y1 = endY - r * Math.sin(Math.PI / 180 * (m - 45));
        }
        Line leftWing = new Line(endX,endY,x1,y1);
        System.out.println(x1 + "," + y1);
        //Right wing
        double x2 = endX + r * Math.cos(Math.PI / 180 * (135 - m));   //endX + r * Math.cos(Math.PI / 180 * (135 - m));
        double y2 = endY + r * Math.sin(Math.PI / 180 * (45 + m));
        if(endY > startY){
            x2 = endX - r * Math.cos(Math.PI / 180 * (135 - m));   //endX + r * Math.cos(Math.PI / 180 * (135 - m));
            y2 = endY - r * Math.sin(Math.PI / 180 * (45 + m));
        }
        Line rightWing = new Line(endX,endY,x2,y2);
        System.out.println(x2 + "," + y2);
        
        pane.getChildren().addAll(line,leftWing,rightWing);
        
    }
    
}
