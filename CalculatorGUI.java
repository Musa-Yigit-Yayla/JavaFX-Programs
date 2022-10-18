/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorgui;

/**
 *
 * @author yigit
 */
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.util.Arrays;
import java.util.ArrayList;
public class CalculatorGUI extends Application{
    static String input = "";
    @Override
    public void start(Stage primaryStage){
        VBox box = new VBox();
        FlowPane buttonPane =  new FlowPane();
        
        //Create each button
        Button bt7 = new Button("7");
        Button bt8 = new Button("8");
        Button bt9 = new Button("9");
        Button btDivide = new Button("/"); //Bölme
        Button bt4 = new Button("4");
        Button bt5 = new Button("5");
        Button bt6 = new Button("6");
        Button btX = new Button("X");
        Button bt1 = new Button("1");
        Button bt2 = new Button("2");
        Button bt3 = new Button("3");
        Button btMinus = new Button("-");
        Button bt0 = new Button("0");
        Button btDot = new Button(".");
        Button btPlus = new Button("+");
        Button btProcess = new Button("=");
        Button btClear = new Button("AC");
        Button[] array = {bt7,bt8,bt9,btDivide,bt4,bt5,bt6,btX,bt1,bt2,bt3,btMinus,bt0,btDot,btPlus,btProcess};
        btClear.setStyle("-fx-stroke: black -fx-background-color: orange");
        btProcess.setStyle("-fx-stroke: black -fx-background-color: orange");
       
        //Register each button to their handlers
        
        buttonPane.setHgap(15);
        buttonPane.setVgap(20);
        //buttonPane.getChildren().addAll(bt7,bt8,bt9,btDivide,bt4,bt5,bt6,btX,bt1,bt2,bt3,btMinus,bt0,btDot,btPlus,btProcess);
        for(Button bt: array){
            buttonPane.getChildren().add(bt);
        }
        buttonPane.setAlignment(Pos.CENTER);
        
        Label lbl = new Label("Calculator");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(50);
        hBox.getChildren().addAll(lbl,btClear);
        box.getChildren().addAll(hBox,buttonPane);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(40);
        box.setMaxWidth(170);
        
        ButtonHandler btHandler = new ButtonHandler(lbl);
        ClearHandler clearHandler = new ClearHandler(lbl);
        
        for(Button bt: array){
            bt.setOnAction(btHandler);
        }
        btClear.setOnAction(clearHandler);
        
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
class ButtonHandler implements EventHandler<ActionEvent>{
    private Label lbl;
    public ButtonHandler(Label lbl){
        this.lbl = lbl;
    }
    @Override
    public void handle(ActionEvent event){
        Button bt = (Button)event.getSource();   
        if(bt.getText().equals("=")){
            calculate();
        }
        else{
            CalculatorGUI.input += bt.getText();
            //add the button symbol to the input text in application
        }
    }
    public void calculate(){
        System.out.println("Beginning of calculate input value: " + CalculatorGUI.input);
        //Trim the operands if there are any
        int firstNumericIndex = getIndexOfNumeric(CalculatorGUI.input,0);
        int lastNumericIndex = getIndexOfNumeric(CalculatorGUI.input,1);
        if(firstNumericIndex != -1)
            CalculatorGUI.input = CalculatorGUI.input.substring(firstNumericIndex);
        
        if(lastNumericIndex != -1)
            CalculatorGUI.input = CalculatorGUI.input.substring(0,lastNumericIndex + 1 - firstNumericIndex);
        
        //get rid of consecutive operands if any
        for(int i = 1; i < CalculatorGUI.input.length()- 1; i++){ //SORUN OLABİLİR
            if(!Character.isDigit(CalculatorGUI.input.charAt(i)) && !checkNumericNeighbors(CalculatorGUI.input, i)){
                //remove
                CalculatorGUI.input = CalculatorGUI.input.substring(0,i) + (CalculatorGUI.input.substring(i + 1));
                i--;
            }
        }
        
        //Split by delimeter
        String delimiter = "[+/X-]";//BAŞA KASITLI k KOYDUM SORUN OLABİLİR
        System.out.println(CalculatorGUI.input);
        String[] numbers = CalculatorGUI.input.split(delimiter);
        String[] operands = CalculatorGUI.input.split("[1234567890.]");//REGEX'E . koy
        ArrayList<String> operandsList = new ArrayList<>();
        
        for(int i = 0; i < operands.length; i++){
            if(operands[i].equals("+") || operands[i].equals("/") || operands[i].equals("-") || operands[i].equals("X")){
                operandsList.add(operands[i]);
            }
        }
        System.out.println("Numbers: " + Arrays.toString(numbers) + "\nOperands: " + Arrays.toString(operands) + "\n operandsList: " + operandsList );
        {   
            /*String[] newArray = new String[operands.length - 1];
            System.arraycopy(operands, 1, newArray, 0, newArray.length);
            operands = newArray;*/ //SORUN OLURSA BURAYI AÇ
        }           
        double result = 0;
        /*
        for(int i = 0; i < operands.length; i++){
            double n1 = Double.parseDouble(numbers[i]);
            double n2 = Double.parseDouble(numbers[i + 1]);
            switch(operands[i]){
                case "+": result += n1 + n2; break;
                case "-": result += n1 - n2; break;
                case "/": result += n1 / n2; break;
                case "X": result += n1 * n2; break;
            }
        }
        *///SORUN OLURSA COMMENTTEKİ LOOPA DÖN BU İLK HALİ
        int count = 0;
        while(operandsList.size() > 0){//OPERANDS ARRAY KULLAN
            double n1, n2;
            if(count == 0){
                n1 = Double.parseDouble(numbers[count]);
                n2 = Double.parseDouble(numbers[count + 1]);
                switch(operandsList.get(0)){//OPERANDS ARRAY KULAN SORUN OLURSA
                    case "+": result += n1 + n2; break;
                    case "-": result += n1 - n2; break;
                    case "/": result += n1 / n2; break;
                    case "X": result += n1 * n2; break;
                }
            }
            else{
                n2 = Double.parseDouble(numbers[count + 1]);
                switch(operandsList.get(0)){//OPERANDS ARRAY KULAN SORUN OLURSA
                    case "+": result += n2; break;
                    case "-": result -= n2; break;
                    case "/": result /= n2; break;
                    case "X": result *= n2; break;
                }
            }
            count++;
            operandsList.remove(0);
        }
        //RECURSION KULLAN RECURSION
        
        CalculatorGUI.input = result + "";
        
        //Çalışmayabilir
        this.lbl.setText(CalculatorGUI.input);
        System.out.println(CalculatorGUI.input);
        
    }
    public static int getIndexOfNumeric(String value, int choice){
        //choice 0 implies first numeric others imply last numeric
        if(choice == 0){
           for(int i = 0; i < value.length(); i++){
               if(Character.isDigit(value.charAt(i))){
                   return i;
               }
           } 
        }
        else{
            for(int i = 0; i < value.length(); i++){
               if(Character.isDigit(value.charAt(value.length() - i - 1))){
                   return value.length() - i - 1;
               }
           } 
        }
        return -1;
    }
    public static boolean checkNumericNeighbors(String value, int index){
        //Checks whether the char at the given index has numeric neighbors, use when the given char is an operand
        if(Character.isDigit(value.charAt(index - 1)) && Character.isDigit(value.charAt(index + 1))){
            return true;
        }
        return false;
       
    }
}
class ClearHandler implements EventHandler<ActionEvent>{
    private Label lbl;
    public ClearHandler(Label lbl){
        this.lbl = lbl;
    }
    @Override
    public void handle(ActionEvent event){
        CalculatorGUI.input = "";
        this.lbl.setText("Enter Values");
    }
}
/**class MyButton extends Button{
   private String text;
   private Button bt;
   public MyButton(String text){
       this.text = text;
       bt = new Button(text);
   }
   public Button getButton(){
       return bt;
   }
   public String getButtonText(){
       return text;
   }
}*/
