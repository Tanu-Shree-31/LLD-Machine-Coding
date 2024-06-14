package Entity;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this.size=size;
        this.board=new PlayingPiece[size][size];
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<Map.Entry<Integer,Integer>> getFreeCells() {
        List<Map.Entry<Integer, Integer>> freeCells = new ArrayList<>();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j]==null){
                    Map.Entry<Integer, Integer> rowColumn = new AbstractMap.SimpleEntry<>(i, j);
                    freeCells.add(rowColumn);
                }
            }
        }
        return freeCells;
    }

    public boolean addPiece(int inputRow, int inputColumn, PlayingPiece playingPiece) {
        if(board[inputRow][inputColumn]!=null){
            return false;
        }
        board[inputRow][inputColumn] = playingPiece;
        return true;
    }
}
