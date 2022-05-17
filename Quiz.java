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
        int numberOfQuestions = showNumberQuestionMessage();
        showMargins();
        getNumberOfCharacters();
    }

    public static int showNumberQuestionMessage() {
        System.out.println("Please enter the maximum number of questions");
        return reader.nextInt();

    }

    public static void showMargins() {
        System.out.println("==================================");
    }
    
    public static int getNumberOfCharacters() {
            System.out.println("Please enter an integer value between 3 and 100 (the number of characters from which to enumerate certain (Odd/Even?Primary) numbers -- Degree of difficulty)");
            int numberOfCharacters = reader.nextInt();
            if(numberOfCharacters >= 3 || numberOfCharacters <= 100) {
                return numberOfCharacters;
            } else {
                return getNumberOfCharacters();
            }          
    } 

}
