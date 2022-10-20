/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanmachinesimulator;

/**
 *
 * @author yigit
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BeanMachineFX extends Application {
    private Pane pane = new Pane();
    private int[][] matrix = new int[4][8];
    public final double dx = 15;
    public final double dy = 20;//dx and dy represent the distance between two pins
    Ball ball = new Ball(pane,matrix);
    @Override
    public void start(Stage primaryStage){
        //Create polyline
        Polyline line = new Polyline(188, 65, 188, 85, 180 - 6 * dx, 100 + 6 * dy,  180 - 6 * dx,  145 + 6 * dy, 310, 145 + 6 * dy,  310, 100 + 6 * dy, 212 , 85, 212, 65);
        pane.getChildren().add(line);
        drawCircles();
        drawSticks();
        
        
        //Animations etc
        
        
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if(((int)(Math.random() * 2))  == 0)
            ball.moveLeft();
            
            else
            ball.moveRight();
            }
                ));
        tl.setCycleCount(7);
        tl.play();
        
        Timeline ballThrower = new Timeline(new KeyFrame(Duration.seconds(9), e -> {
            ball = new Ball(pane, matrix);
            tl.play();
        }));
        ballThrower.setCycleCount(9);
        ballThrower.play();
       
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        
    }
    public static void main(String[] args){
        launch(args);
    }
    private void drawCircles( ){
        double circleX = 200;
        double circleY = 100;
        double initialCircleY = 100;
        for(int i = 0; i < 7; i++){
            circleY = initialCircleY +  i * dy;
            double newCircleX = circleX - dx * i;//circleX - dx * i ;
            for(int j = 0; j < i + 1; j++){
                if(j != 0){
                    newCircleX += dx * 2 ;//+ 20;
                }
                //newCircleX += dx * j;
                Circle pin = new Circle(newCircleX, circleY, 5);
                pin.setFill(Color.DARKGREY);
                pane.getChildren().add(pin);
            }
        }
    }
    private void drawSticks(){
        double x = 200 - 6 * dx;
        final double initialY = 100 + 6 * dy;
        //stick height 45 max 4 balls in a slot
        for(int i = 0; i < 7; i++){
            
            pane.getChildren().add(new Line(x, initialY, x, initialY + 45));
            x += dx * 2;
        }
    }
    private void dropBall(){
        
    }
}
