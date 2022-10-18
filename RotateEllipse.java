/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotateellipse;

/**
 *
 * @author yigit
 */import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
public class RotateEllipse extends Application{
private double rotate = 10;
    @Override
    public void start(Stage primaryStage){
        Ellipse ellipse = new Ellipse();
        ellipse.setRadiusX(100);
        ellipse.setRadiusY(50);
        Button btRotate = new Button("Rotate");
        btRotate.setOnAction(e -> {
            ellipse.setRotate(rotate);
            rotate += 10;
        });
        VBox box = new VBox();
        box.setSpacing(150);
        StackPane pane1 = new StackPane(ellipse);
        StackPane pane2 = new StackPane(btRotate);
        box.getChildren().addAll(pane1, pane2);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
