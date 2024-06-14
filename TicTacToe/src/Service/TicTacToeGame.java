package Service;

import Entity.*;

import java.util.*;

import com.sun.tools.javac.util.Pair;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    public TicTacToeGame(){
        initializeGame();
    }

    public void initializeGame() {
        // creating 2 players
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player 1", crossPiece);

        PlayingPieceO noughtsPiece = new PlayingPieceO();
        Player player2 = new Player("Player 2", noughtsPiece);

        players.add(player1);
        players.add(player2);

        //initialize board
        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;
        while (noWinner){
            // take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst(); //first player from dequeue

            //get the free space from the board
            gameBoard.printBoard();
            List<Map.Entry<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
            if(freeSpaces.isEmpty()){
                noWinner=false;
                continue;
            }

            //read the user input
            System.out.print("Player: "+playerTurn.name+" Enter row, column: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            //place the piece
            boolean pieceAddedSucessfully = gameBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece);
            if(!pieceAddedSucessfully){
                //player cannot insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.playingPiece.pieceType);
            if(winner){
                return playerTurn.name;
            }
        }
        return "tie";
    }

    private boolean isThereWinner(int inputRow, int inputColumn, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antidiagonalMatch = true;

        // need to check in row
        for(int i=0; i< gameBoard.size; i++){
            if(gameBoard.board[inputRow][i] == null || gameBoard.board[inputRow][i].pieceType!=pieceType){
                rowMatch=false;
            }
        }

        //need to check in column
        for(int i=0; i< gameBoard.size; i++){
            if(gameBoard.board[i][inputColumn] == null || gameBoard.board[i][inputColumn].pieceType!=pieceType){
                columnMatch=false;
            }
        }

        //need to check for diagnols
        for(int i=0, j=0; i< gameBoard.size; i++, j++){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType!=pieceType){
                diagonalMatch=false;
            }
        }

        //antidiagonal match
        for(int i=0, j= gameBoard.size-1; i< gameBoard.size; i++, j--){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType!=pieceType){
                antidiagonalMatch=false;
            }
        }
        return rowMatch || columnMatch || diagonalMatch || antidiagonalMatch;
    }
}
