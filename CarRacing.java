/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carracing;

/**
 *
 * @author yigit
 */
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.PathTransition;
import javafx.scene.shape.Line;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class CarRacing extends Application{
    private double leftWheelX = 15;
    private final double leftWheelY = 190;
    private double rightWheelX = 35;
    private final double rightWheelY = 190;
    private double ortaX = 0;
    private final double ortaY = 180;
    private double dx = 1;
    private double dy = 0;
    private Pane pane = new Pane();
    @Override
    public void start(Stage primaryStage){
        
        Circle leftWheel = new Circle(leftWheelX, leftWheelY, 5);
        Circle rightWheel = new Circle(rightWheelX, rightWheelY, 5);
        
        Rectangle orta = new Rectangle();
        orta.setX(ortaX);
        orta.setY(ortaY);
        orta.setWidth(50);
        orta.setHeight(10);
        
        Polygon üst =  getÜst();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(250), e -> {
            
            if(orta.getX() == 350){
                redraw(leftWheel, rightWheel, orta, üst);
                return;
            }
            //Üstü ayarla
            for(int i = 0; i < üst.getPoints().size() - 1; i+= 2){ //SORUN OLABİLİR
                double x = üst.getPoints().remove(i);
                x += dx;
                üst.getPoints().add(i, x);
            }
            //ortayı ayarla
            orta.setX(orta.getX() + dx);
            //leftWheel ayarla
            leftWheel.setCenterX(leftWheel.getCenterX() + dx);
            rightWheel.setCenterX(rightWheel.getCenterX() + dx);
        }));
        
        pane.getChildren().addAll(üst,leftWheel,rightWheel,orta);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.setRate(3);
        tl.play();
        
        Button btResume = new Button("Resume");
        btResume.setOnAction(e -> {
            if(tl.getStatus() != Status.RUNNING)
                tl.play();
        });
        Button btPause = new Button("pause");
        btPause.setOnAction( e-> {
                tl.pause();
        }
        );
        
        BorderPane bp = new BorderPane();
        HBox box = new HBox();
        box.setSpacing(10);
        box.getChildren().addAll(btPause, btResume);
        bp.setBottom(box);
        bp.setCenter(pane);
        Scene scene = new Scene(bp);
        scene.setOnKeyPressed(e -> {
           if(e.getCode() == KeyCode.DOWN){
               tl.setRate(tl.getRate() / 1.2);
           }
           else if (e.getCode() == KeyCode.UP){
               tl.setRate(tl.getRate() * 1.2);
           }
        });
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        //Line orta =  new Line(0,180, 400, 180);
        //Line üst = new Line(10,170,390,170); //175 yap
        //Line solTeker = new Line()
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void redraw(Circle lw, Circle rw, Rectangle orta, Polygon üst){
        lw.setCenterX(leftWheelX);
        lw.setCenterY(leftWheelY);
        rw.setCenterX(rightWheelX);
        rw.setCenterY(rightWheelY);
        orta.setX(ortaX);
        orta.setY(ortaY);
        for(int i = 0; i < üst.getPoints().size(); i = 0){
            üst.getPoints().remove(0);
        }
        üst.getPoints().addAll(10.0, 175.0, 20.0, 165.0, 30.0, 165.0, 40.0, 175.0);
    }
    private Polygon getÜst(){
        Polygon üst = new Polygon(10, 180, 20, 170, 30, 170, 40, 180);
        üst.setFill(Color.LIMEGREEN);
        return üst;
    }
    
}
class Car{
    private PathTransition[] animations;
    public Car(PathTransition[] animation){
        this.animations = animation;
    }
    public void play(){
        for(PathTransition a: animations){
            a.play();
        }
    }
    public void pause(){
        for(PathTransition a: animations){
            a.pause();
        }
    }
    public void stop(){
        for(PathTransition a: animations){
            a.stop();
        }
    }
    public void accelerate(){
        for(PathTransition a: animations){
            a.setRate(a.getRate() + 0.1);
        }
    }
    public void decelerate(){
        for(PathTransition a: animations){
            
            a.setRate(a.getRate() - 0.1);
        }
    }
    public void changeDirection(){
        for(PathTransition a: animations){
            
            a.setRate(a.getRate() * -1);
        }
    }
}
