/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanmachinesimulator;

/**
 *
 * @author yigit
 **/
import java.util.Scanner;
public class BeanMachineSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of the balls to be dropped:");
        int balls = input.nextInt();
        System.out.println("Enter the number of slots:");
        int slotNumber = input.nextInt();
        int[] slots = new int[slotNumber];
        while(balls > 0){
            int rightCount = 0;
            for(int count = 0; count < slotNumber - 1; count++){
                int a = (int)(Math.random() * 2);
                if (a == 1){
                    //Ball went to right
                    System.out.print("R");
                    rightCount++;
                }
                else{
                    System.out.print("L");
                }}
            System.out.println(" ");
                //Increment the slot which has the ball
               slots[rightCount]++;
        balls--; }
            //Print the chart
            int numberOfLines = getMax(slots);
            while(numberOfLines > 0){
                for(int i = 0; i < slots.length; i++){
                if(slots[i] == numberOfLines){
                    System.out.print("O");
                    slots[i]--;
                }
                else {
                    System.out.print(" ");
                }
                }
                System.out.println(" ");
                numberOfLines--;
            }
        
    }
    public static int getMax(int[] list){
        int max = list[0];
        for(int i = 1; i < list.length; i++){
            if(list[i] > max){
                int temp = max;
                max = list[i];
                list[i] = max;
            }
        }
        return max;
    }
    
}
