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
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.scene.shape.Circle;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Ball extends Circle{
    private Color color;
    private Pane pane;
    private PathTransition animation = new PathTransition();
    private int[][] sockets;
    private static int numberOfBallsDropped;
    private int dropCount;
    private boolean isOnLastPin;
    Ball(Pane pane, int[][] sockets){
        this.sockets = sockets;
        pane.getChildren().add(this);
        this.pane = pane;
        this.setCenterX(200);
        this.setCenterY(50);
        animation.setCycleCount(1);
        animation.setDuration(Duration.seconds(1));
        animation.setNode(this);
        this.color = Color.GREENYELLOW;
        this.setRadius(5);
        PathTransition dropToMachine = new PathTransition();
        dropToMachine.setCycleCount(1);
        dropToMachine.setNode(this);
        dropToMachine.setPath(new Line(200, 50, 200, 93)); // 2 pixel above
        dropToMachine.play();
        this.setCenterY(93);
        
    }
    public void moveRight(){
        
        PathTransition animation = new PathTransition();
        animation.setCycleCount(1);
        animation.setDuration(Duration.seconds(1));
        animation.setNode(this);
        if(isOnLastPin){
            animation.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX() + 15, this.getCenterY() + 7));
            animation.play();
            this.setCenterY(this.getCenterY() + 7);
            this.setCenterX(this.getCenterX() + 15);
            enterSocket();
        }
        else {
            animation.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX() + 15, this.getCenterY() + 20));
            animation.play();
            this.setCenterY(this.getCenterY() + 20);
            this.setCenterX(this.getCenterX() + 15);
        }
        dropCount++;
        if(dropCount == 6){
            isOnLastPin = true;
        }
        /*animation.statusProperty().addListener( ov -> {
            if(animation.getStatus() == Status.STOPPED){
                this.setCenterY(this.getCenterY() + 20);
                this.setCenterX(this.getCenterX() + 15);
            }
        });*/
    }
    //if dropCount 6, is on last pin drop count starts first right or left
    public void moveLeft(){
        PathTransition animation = new PathTransition();
        animation.setCycleCount(1);
        animation.setDuration(Duration.seconds(1));
        animation.setNode(this);
        if(isOnLastPin){
            animation.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX() - 15, this.getCenterY() + 7));
            animation.play();
            this.setCenterY(this.getCenterY() + 7);
            this.setCenterX(this.getCenterX() - 15);
            enterSocket();
        }
        else{
            animation.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX() - 15, this.getCenterY() + 20));
            animation.play();
            this.setCenterY(this.getCenterY() + 20);
            this.setCenterX(this.getCenterX() - 15);
        }
        
        dropCount++;
        if(dropCount == 6){
            isOnLastPin = true;
        }
        /*animation.statusProperty().addListener( ov -> {
            if(animation.getStatus() == Status.STOPPED){
                this.setCenterY(this.getCenterY() + 20);
                this.setCenterX(this.getCenterX() - 15);
            }
        });*/
    }
    private void enterSocket(){
        int columnIndex = getColumnIndex();
        double dy = (4 - getNoOfElementsInColumn(columnIndex)) * 10; //5 45 e tamamlaması için
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setDelay(Duration.ONE);
        pt.setCycleCount(1);
        pt.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX(), this.getCenterY()  + dy));
        pt.setNode(this);
        pt.play();
        this.updateSockets();
    }
    private int getColumnIndex(){
        double x = this.getCenterX();
        int index = -1;
        if(x > 90 && x < 120){
            index = 0;
        }
        else if(x > 120 && x < 150){
            index = 1;
        }
        else if(x > 150 && x < 180){
            index = 2;
        }
        else if(x > 180 && x < 200){
            index = 3;
        }
        else if(x > 200 && x < 230){
            index = 4;
        }
        else if(x > 230 && x < 260){
            index = 5;
        }
        else if(x > 260 && x < 290){
            index = 6;
        }
        else if(x > 290 && x < 320){
            index = 7;
        }
        if(index == -1){
            System.out.println("x is " + this.getCenterX() + "\ny is" + this.getCenterY());
        }
        return index;
        /*
        double x = this.getCenterX();
        int index = -1;
        if(x > 90 && x < 110){
            index = 0;
        }
        else if(x > 110 && x < 130){
            index = 1;
        }
        else if(x > 130 && x < 150){
            index = 2;
        }
        else if(x > 150 && x < 170){
            index = 3;
        }
        else if(x > 170 && x < 190){
            index = 4;
        }
        else if(x > 190 && x < 210){
            index = 5;
        }
        else if(x > 210 && x < 230){
            index = 6;
        }
        else if(x > 230 && x < 250){
            index = 7;
        }
        if(index == -1){
            System.out.println("x is " + this.getCenterX() + "\ny is" + this.getCenterY());
        }
        return index;
        */
    }
    private int getNoOfElementsInColumn(int columnIndex){
        int count = 0;
        for(int i = 0; i < this.sockets.length; i++){
            if(this.sockets[i][columnIndex] != 0){
                count++;
            }
        }
        return count;
    }
    private void updateSockets(){
        int columnIndex = this.getColumnIndex();
        for(int i = this.sockets.length - 1; i > 0; i--){
            if(sockets[i][columnIndex] == 0){
                sockets[i][columnIndex] = 1;
                break;
            }
        }
    }
}


/*
move right
animation.setCycleCount(1);
        animation.setDuration(Duration.seconds(1));
        animation.setNode(this);
move left
this.animation.setPath(new Line(this.getCenterX(), this.getCenterY(), this.getCenterX() - 15, this.getCenterY() + 20));
        this.animation.play();
*/