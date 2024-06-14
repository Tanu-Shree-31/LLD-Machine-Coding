package Driver;

import Service.TicTacToeGame;

public class Main {

    public static void main(String args[]) {
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        if(game.startGame()=="tie"){
            System.out.println("Game is: " + game.startGame());
        } else {
            System.out.println("Game winner is: " + game.startGame());
        }
    }
}
