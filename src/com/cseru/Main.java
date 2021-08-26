package com.cseru;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.readInputFromFile();
        game.pickARandomMovie();
        game.constructTemporaryBlankMovie();
        while(game.isGameOver()){
            game.gameDisplay();
            Scanner scanner = new Scanner(System.in);
            char guessChar = scanner.next().charAt(0);
            game.checkInput(guessChar);
        }
    }


}
