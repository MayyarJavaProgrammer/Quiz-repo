/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quiz;

import java.util.Scanner;

/**
 *
 * @author mear
 */
public class Quiz {

    /**
     * @param args the command line arguments
     */
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfQuestion;
    }

    public static int showQuestionMessage() {
        System.out.println("Please enter the maximum number of questions");
        return reader.nextInt();

    }

    public static void showMargins() {
        System.out.println("==================================");
    }

}
