/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sinecosinecubicfunctions;

/**
 *
 * @author yigit
 */
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.animation.PathTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SineCosine extends Application{
    PathTransition ptcos = new PathTransition();
    PathTransition ptsin = new PathTransition();
    @Override
    public void start(Stage primaryStage){
        
         
      Pane pane = new Pane();
      pane.setPrefSize(800,500);
      pane.setPadding(new Insets(10));
      
      Polyline sine = new Polyline();
      Polyline cos = new Polyline();
      ObservableList<Double> sineList = sine.getPoints();
      ObservableList<Double> cosList = cos.getPoints();
      double scaleFactor = 150;
      for(int x = 40; x < 760; x++){
          double initialX = x;
          initialX = Math.abs(400 - x);
          double y;
          if(x < 400){
              y = Math.sin(initialX * Math.PI / 180) * scaleFactor + 250;
          }
          else {
              y = 250 - Math.sin(initialX * Math.PI / 180) * scaleFactor; 
          }
          sineList.addAll(x + 0.0,y);
      }
      //Cos
      for(int x = 40; x < 760; x++){
          double initialX = x;
          initialX = Math.abs(400 - x);
          double y;
         /* if(x < 400){
              y = Math.cos(initialX * Math.PI / 180) * scaleFactor + 250;
          }
          else {
              y = 250 - Math.cos(initialX * Math.PI / 180) * scaleFactor; 
          }*/
          y = 250 - Math.cos(initialX * Math.PI / 180) * scaleFactor; 
          cosList.addAll(x + 0.0,y);
      }
      
      SineCosineCubicFunctions.drawArrowLine(30, 250, 770, 250, pane);
      SineCosineCubicFunctions.drawArrowLine(400, 500, 400, 50, pane);
      
      Circle circlesin = new Circle(25);
      Circle circlecos = new Circle(25);
      
      pane.getChildren().addAll(circlesin,circlecos,sine,cos,new Text(40,280,"-2\u03c0"),new Text(220,280,"-\u03c0"),new Text(400,280,"0"),new Text(580,280,"\u03c0"),new Text(760,280,"2\u03c0"));
      Scene scene = new Scene(pane);
      
      
      circlesin.setFill(Color.RED);
      circlecos.setFill(Color.GREEN);
      
      Handler handler = new Handler();
      circlesin.setOnMouseClicked(handler);
      circlecos.setOnMouseClicked(handler);
      
      
      ptsin.setDuration(Duration.seconds(5));
      ptsin.setPath(sine);
      ptsin.setNode(circlesin);
      ptsin.setAutoReverse(true);
      ptsin.setCycleCount(Timeline.INDEFINITE);
      
      ptcos.setDuration(Duration.seconds(5));
      ptcos.setPath(cos);
      ptcos.setNode(circlecos);
      ptcos.setAutoReverse(true);
      ptcos.setCycleCount(Timeline.INDEFINITE);
      ptcos.play();
      
      primaryStage.setScene(scene);
      primaryStage.setTitle("Sine Cosine Functions");
      primaryStage.show();
      
    }
    public static void main(String[] args){
        launch(args);
    }
    class Handler implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {
         //To change body of generated methods, choose Tools | Templates.
        Circle circle = (Circle)event.getSource();
        if(circle.getFill() == Color.RED){
            //sine animation circle
            if(ptsin.getStatus() == Status.RUNNING){
                ptsin.pause();
            }
            else{
                ptsin.play();
            }
        }
        if(circle.getFill() == Color.GREEN){
            //cos animation circle
            if(ptcos.getStatus() == Status.RUNNING){
                ptcos.pause();
            }
            else{
                ptcos.play();
            }
        }
    }
    
}
}

