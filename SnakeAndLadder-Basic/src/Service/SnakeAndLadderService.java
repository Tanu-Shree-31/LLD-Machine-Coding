package Service;

import Entity.Player;
import Entity.SnakeAndLadderBoard;
import Entity.Ladder;
import Entity.Snake;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Queue<Player> players;
    private static final int DEFAULT_BOARD_SIZE = 100;

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<Player>();
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>(players);
        Map<String, Integer> playerPieces = new HashMap<>();
        for (Player player : players) {
            playerPieces.put(player.getId(), 0);
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders);
    }

    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        int previousPosition;
        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd();
                }
            }
            for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd();
                }
            }
        } while (newPosition != previousPosition);
        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions;
        int boardSize = snakeAndLadderBoard.getSize();

        if (newPosition > boardSize) {
            newPosition = oldPosition;
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
    }

    private int getTotalValueAfterDiceRolls() {
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeAndLadderBoard.getSize();
        return playerPosition == winningPosition;
    }

    private boolean isGameCompleted() {
        return players.isEmpty();
    }

    public void startGame() {
        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRolls();
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }
}

