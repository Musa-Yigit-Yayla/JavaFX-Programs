/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveableverticedistance;

/**
 *
 * @author yigit
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
public class MoveableVerticeDistance extends Application{
    private Line line = new Line();
     private Circle circle1 = new Circle(40 , 40, 10);
        private Circle circle2 = new Circle(120 , 150, 10);
        private Text text = new Text("" + (int)Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2)) + Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2));
    @Override
    public void start(Stage primaryStage){
        Pane pane = new Pane();
       pane.getChildren().addAll(line, circle1, circle2, text);
        
        class Handler implements EventHandler<MouseEvent>{

            @Override
            public void handle(MouseEvent event) {
                Circle circle = (Circle)event.getSource();
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                updateLine(line);
            }
            
        }
        Handler handler = new Handler();
        circle1.setOnMouseDragged(handler);
        circle2.setOnMouseDragged(handler);
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    public void updateLine(Line line){
        line.setStartX(circle1.getCenterX());
        line.setStartY(circle1.getCenterY());
        line.setEndX(circle2.getCenterX());
        line.setEndY(circle2.getCenterY());
        text.setText("" + (int)Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2)) + Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2));
        text.setX(Math.abs(line.getStartX() - line.getEndX()));
         text.setY(Math.abs(line.getStartY() - line.getEndY()));
    }
    
}
