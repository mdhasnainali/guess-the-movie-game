package com.cseru;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Game {
    private final ArrayList<String> movies = new ArrayList<>();
    private final ArrayList<Character> wrongLetters = new ArrayList<>();
    private int moviesListLength;
    private String pickedMovie;
    private String temporaryBlankMovie = "";
    private int numberOfWrongGuess = 0;
    private char [] pickedMovieChars;

    public void readInputFromFile(){
        File file = new File("./src/com/cseru/movies.txt");
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String movie = scanner.nextLine();
                this.movies.add(movie);
            }

        }catch (Exception e){
            System.out.println("File not found");
        }
        this.moviesListLength = this.movies.size();
    }

    public void pickARandomMovie(){
        int randomIndex = (int) (Math.random() * moviesListLength);
        this.pickedMovie = this.movies.get(randomIndex);
        this.pickedMovieChars = this.pickedMovie.toCharArray();
//        System.out.println(this.pickedMovie + " " +this.pickedMovie.charAt(0));
    }

    public void constructTemporaryBlankMovie(){
        String underscore = "_";
        int movieNameLength = this.pickedMovie.length();
        for(int i=0;i< movieNameLength;i++){
            if(this.pickedMovie.charAt(i) == ' '){
                this.temporaryBlankMovie += " ";
            }
            else{
                this.temporaryBlankMovie += underscore;
            }
        }
    }

    public void gameDisplay(){
        System.out.println("You are guessing:" + temporaryBlankMovie);
        System.out.print("You have guessed (" + numberOfWrongGuess + ") wrong letters: ");
        for(int i = 0; i<this.numberOfWrongGuess;i++){
            System.out.print(this.wrongLetters.get(i) +" ");
        }
        System.out.println("");
        System.out.print("Guess a letter:");
    }

    public void checkInput(char guessChar){
        if(this.pickedMovie.contains(""+guessChar)){
            char [] temporaryBlankMovieChars = this.temporaryBlankMovie.toCharArray();
            int pickedMovieLength = this.pickedMovie.length();

            for(int i=0;i<pickedMovieLength;i++){
                if (pickedMovieChars[i] == guessChar){
                    temporaryBlankMovieChars[i] = guessChar;
                }
            }
            this.temporaryBlankMovie = String.valueOf(temporaryBlankMovieChars);
        }
        else{
            this.numberOfWrongGuess++;
            this.wrongLetters.add(guessChar);
        }
    }

    public boolean isGameOver() {
        if(this.numberOfWrongGuess == 10){
            System.out.println("You Lose!");
            System.out.println("You have not guessed '" + this.pickedMovie +"' correctly.");
            return false;
        }
        if(this.temporaryBlankMovie.equals(this.pickedMovie)){
            System.out.println("You Win!");
            System.out.println("You have guessed '" + this.pickedMovie +"' correctly.");
            return false;
        }
        return true;
    }
}
