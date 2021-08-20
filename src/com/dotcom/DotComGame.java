package com.dotcom;

import java.util.ArrayList;
import java.util.Scanner;

class DotCom {
    private ArrayList<String> locationCells;
    String name = "Battle Ship " + ((int) (Math.random() * 1000));

    public String checkYourself(String guess){
        int index = locationCells.indexOf(guess);
        if (index == -1) {
            return "Miss";
        } else {
            locationCells.remove(index);
        }
        if (locationCells.isEmpty()) {
            System.out.println("You hit " + name + "!");
            return "Kill";
        } else {
            System.out.println("You hit " + name + "!");
            return "Hit";
        }
    }
    public void setLocationCells (ArrayList<String> loc){
        locationCells = loc;
    }
    public ArrayList<String> getLocationCells() {
        return locationCells;
    }
}

public class DotComGame {

    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private GameHelper helper = new GameHelper();
    private final int dotComSize = 3;
    private int numOfGuesses = 0;

    static int randomGenerator() {
        return (int) (Math.random() * 5);
    }

    private void initGame() {
        for (int i = 0; i < dotComSize; i++) {
            dotComsList.add(new DotCom());
            dotComsList.get(i).setLocationCells(helper.placeDotCom(3));
            // System.out.println(dotComsList.get(i).name + " Created");
            // System.out.println(dotComsList.get(i).getLocationCells());
        }
    }

    private void startPlaying() {
        while (dotComsList.size() != 0) {
            Scanner inputScanner = new Scanner(System.in);  // Create a Scanner object
            System.out.printf("Enter guess: ");
            String userInput = inputScanner.nextLine();  // Read user input
            userInput = userInput.toLowerCase();
            checkUserGuess(userInput);
        }
    }

    private void checkUserGuess(String guess) {
        String result = "null result";
        numOfGuesses++;
        for (int i = 0; i < dotComsList.size(); i++) {
            result = dotComsList.get(i).checkYourself(guess);
            if (result == "Kill") {
                dotComsList.remove(i);
                break;
            } else if (result == "Hit") {
                break;
            }
        }
        System.out.print(result + '\n');
    }

    private void finishGame() {
        if (numOfGuesses < 10) {
            System.out.println("You took " + numOfGuesses + " Guesses.");
            System.out.println("You won with flying colours!");
        } else {
            System.out.println("You took " + numOfGuesses + " Guesses.");
            System.out.println("Loser!");
        }
    }


    public static void main(String[] args) {
        // write your code here
        DotComGame game = new DotComGame();
        game.initGame();
        game.startPlaying();
        game.finishGame();
    }
}
