package Driver;

import Entities.Ladder;
import Entities.Player;
import Entities.Snake;
import Service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter board size: ");
        int boardSize = scanner.nextInt();

        System.out.println("Enter number of snakes: ");
        int noOfSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < noOfSnakes; i++) {
            System.out.println("Enter the head and tail of snake " + (i + 1) + ": ");
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            snakes.add(new Snake(head, tail));
        }

        System.out.println("Enter number of ladders: ");
        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < noOfLadders; i++) {
            System.out.println("Enter the start and end of ladder " + (i + 1) + ": ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            ladders.add(new Ladder(start, end));
        }

        System.out.println("Enter number of players: ");
        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter name of player " + (i + 1) + ": ");
            String name = scanner.next();
            players.add(new Player(name));
        }

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService(boardSize);
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);

        System.out.println("Enter the number of dice: ");
        int noOfDices = scanner.nextInt();
        snakeAndLadderService.setNoOfDices(noOfDices);

        System.out.println("Should the game continue till the last player? (true/false): ");
        boolean shouldGameContinueTillLastPlayer = scanner.nextBoolean();
        snakeAndLadderService.setShouldGameContinueTillLastPlayer(shouldGameContinueTillLastPlayer);

        System.out.println("Should multiple dice rolls be allowed on rolling a 6? (true/false): ");
        boolean shouldAllowMultipleDiceRollOnSix = scanner.nextBoolean();
        snakeAndLadderService.setShouldAllowMultipleDiceRollOnSix(shouldAllowMultipleDiceRollOnSix);

        snakeAndLadderService.startGame();
    }
}