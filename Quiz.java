/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quiz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
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
    static int numberOfQuestions;
    static String[] randomStrings;
    static String[] typeMathNumbers = {"Primary", "Odd", "Even"};
    //what is type of Current Question 
    static int[] typeOfNumberQuestion;
    //stroe user answers
    static int[] userAnswers;
    //to check user answers
    static int[] checkUserAnswers;
    //store correct answers
    static int[] correctِِِِِِِAnswers;
    static int numberOfCharacters;
    //store how many numbers in random string for each question
    static int counterNumber = 0;
    static byte counterCorrectِِِِِِِAnswers = 0;
    static byte counterWrongAnswers = 0;

    public static void main(String[] args) {
        numberOfQuestions = showNumberQuestionMessage();

        randomStrings = new String[numberOfQuestions];
        typeOfNumberQuestion = new int[numberOfQuestions];
        userAnswers = new int[numberOfQuestions];
        correctِِِِِِِAnswers = new int[numberOfQuestions];
        checkUserAnswers = new int[numberOfQuestions];
        showMargins();
        showQuestionMessage();
        getrasult();
    }

    public static int showNumberQuestionMessage() {
        System.out.println("Please enter the maximum number of questions");
        try {
            int number = reader.nextInt();
            //less than 1 throw exception
            if (number < 1) {
                throw new Exception();
            } else {
                return number;
            }
        } catch (InputMismatchException ex) {
            //this declare for clean Scanner from the wrongs input to aviod repeat forever
            reader = new Scanner(System.in);
            return showNumberQuestionMessage();
        } catch (Exception ex) {
            return showNumberQuestionMessage();
        }
    }

    public static void showMargins() {
        System.out.println("==================================");
    }

    public static int getNumberOfCharacters() {
        System.out.println("Please enter an integer value between 3 and 100 (the number of characters from which to enumerate certain (Odd/Even?Primary) numbers -- Degree of difficulty)");
        try {
            int numberOfCharacters = reader.nextInt();
            if (numberOfCharacters >= 3 && numberOfCharacters <= 100) {
                return numberOfCharacters;
            } else {
                throw new Exception();
            }
        } catch (InputMismatchException ex) {
            //this declare for clean Scanner from the wrongs input to aviod repeat forever
            reader = new Scanner(System.in);
            return getNumberOfCharacters();
        } catch (Exception ex) {
            return getNumberOfCharacters();
        }
    }

    public static String generateRandomString(int numberOfCurrentQuestion) {
        //all chars need to use
        String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        //length all char
        int allCharLength = allCharacters.length();
        //stroe random string
        String RandomString = "";
        for (int i = 1; i <= numberOfCharacters; i++) {
            //get random index from allCharacters
            int index = (int) (Math.random() * (allCharLength - 0) + 0);
            char c = allCharacters.charAt(index);
            //get char from random index and set in string 
            RandomString += c;
            if (Character.isDigit(c)) {
                calculateNumbers(c, numberOfCurrentQuestion);
            }
        }
        correctِِِِِِِAnswers[numberOfCurrentQuestion] = counterNumber;
        counterNumber = 0;
        return RandomString;
    }

    public static void showQuestionMessage() {
        int i = 0;
        int index = 0;
        while (i < numberOfQuestions) {
            //if there is no element store on else.. an exception happend so don't do that
            if (randomStrings[i] == null && typeOfNumberQuestion[i] == 0) {
                numberOfCharacters = getNumberOfCharacters();
                index = new Random().nextInt(3);
                randomStrings[i] = generateRandomString(i);
                typeOfNumberQuestion[i] = index;
            }
            System.out.printf("How many times %s numbers appear in the following characters:%n %s %n To ignore the question type ignore%n",
                    typeMathNumbers[index], randomStrings[i]);

            try {
                String userAnswer = reader.next();
                showMargins();
                //user ignored 
                if (userAnswer.equalsIgnoreCase("ignore")) {
                    //store wrong number
                    userAnswers[i] = 0;
                } else {
                    userAnswers[i] = Integer.parseInt(userAnswer);
                }
                checkAnswers(i);
            } catch (InputMismatchException ex) {
                //to avoid calls forever
                reader = new Scanner(System.in);
                continue;
            } catch (Exception ex) {
                continue;
            }
            i++;

        }
    }

    public static void calculateNumbers(char c, int numberOfCurrentQuestion) {
        int typeNumber = typeOfNumberQuestion[numberOfCurrentQuestion];
        // - '0' because we will get ascii value when we do this.. we will get the real number
        int number = (c - '0');
        //even type
        switch (typeNumber) {
            //even choice
            case 2 -> {
                if (number % 2 == 0) {
                    counterNumber++;
                }
            }
            //odd choice
            case 1 -> {
                if (number % 2 != 0) {
                    counterNumber++;
                }
            }
            //primary choice
            default -> {
                if (isPrimary(number)) {
                    counterNumber++;
                }
            }
        }
    }

    public static boolean isPrimary(int number) {
        if (number == 2 || number == 3) {
            return true;
        } //if number is even or 1 it is not primary
        else if (number % 2 == 0 || number == 1) {
            return false;
        } else {
            //if number % anyOddNumber == 0 is not primary.. 
            //if i == number or i > number that mean the number is 3 or 2 so do'nt loop
            for (int i = 3; i < 9; i += 2) {
                if (number % i == 0 && number != i) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkAnswers(int numberOfCurrentQuestion) {
        //if answer is right store 1 and plus counter
        if (userAnswers[numberOfCurrentQuestion] == correctِِِِِِِAnswers[numberOfCurrentQuestion]) {
            checkUserAnswers[numberOfCurrentQuestion] = 1;
            counterCorrectِِِِِِِAnswers++;
        } else {
            checkUserAnswers[numberOfCurrentQuestion] = 0;
            counterWrongAnswers++;
        }
    }

    public static void getrasult() {
        String input = null;
        boolean flag = true;
        //repeat for input eqauls exit
        while (flag) {
            try {
                System.out.println("""
                            To get the number of wrong answers, type 1
                            To get the number of right answers, type 2
                            To view all the questions with correct and answered responses, type 3
                            To exit, type exit""");
                input = reader.next();
                if (input.equalsIgnoreCase("exit")) {
                    flag = false;
                    break;
                } else {
                    getAnswers(Integer.parseInt(input));
                }
            } catch (Exception ex) {
                getrasult();
            }
        }
    }

    public static byte getWrongAnswers() {
        return counterWrongAnswers;

    }

    public static byte getCorrectِِِِِِِAnswers() {
        return counterCorrectِِِِِِِAnswers;
    }

    public static void getAnswers(int choice) {
        switch (choice) {
            //wrong answers choice
            case 1 -> {
                System.out.println("The number of the wrong answers is : " + getWrongAnswers());
            }
            //right answers choice
            case 2 -> {
                System.out.println("The number of the right answers is : " + getCorrectِِِِِِِAnswers());
            }
            //all answers choice
            case 3 -> {
                getAllAnswers();
            }
        }
    }

    public static void getAllAnswers() {
        System.out.println("""
                           Question     Type     User Answer     Correct Answer
                           =========================================================
                           """);
        //print all questions
        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println(randomStrings[i] + "\t" + typeOfNumberQuestion[i] + "\t\t" + userAnswers[i] + "\t\t" + correctِِِِِِِAnswers[i]);
        }
    }

}
