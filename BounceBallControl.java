/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bounceballcontrol;

/**
 *
 * @author yigit
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
public class BounceBallControl extends Application {
    @Override
    public void start(Stage primaryStage){
        BallPane ballPane = new BallPane();
        
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());
        
        ballPane.setOnKeyPressed(e -> {
           if(e.getCode() == KeyCode.UP){
               ballPane.increaseSpeed();
           } 
           else if(e.getCode() ==  KeyCode.DOWN){
               ballPane.decreaseSpeed();
           }
        });
        
        Scene scene = new Scene(ballPane, 250, 150);
        primaryStage.setTitle("BounceBallControl");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ballPane.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
class BallPane extends Pane{
    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    private Circle circle = new Circle(x, y, radius);
    private Timeline animation;
    
    public BallPane(){
        circle.setFill(Color.RED);
        getChildren().add(circle);
        
        //create an animation for moving the ball
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public void play(){
        animation.play();
    }
    public void pause(){
        animation.pause();
    }
    public void increaseSpeed(){
        animation.setRate(animation.getRate() + 0.1);
    }
    public void decreaseSpeed(){
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }
    public DoubleProperty rateProperty(){
        return animation.rateProperty();
    }
    protected void moveBall(){
        if(x < radius || x > getWidth() - radius){
            dx *= -1;
        }
        if(y < radius || y > getHeight() - radius){
            dy *= -1;
        }
        
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
}
