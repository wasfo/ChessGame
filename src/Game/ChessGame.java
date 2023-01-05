package Game;
import Player.*;
import java.util.ArrayList;
import java.util.List;
import static Game.GameSetup.SetupGame;

public class ChessGame {
    private final Board chessBoard = new Board();
    BoardManager boardManager = new BoardManager(this.chessBoard);
    GameResult gameResult;
    private final List<Move> movesHistory = new ArrayList<Move>();
    public void start() {
         SetupGame(this.chessBoard,boardManager);
    }

    public static void main(String[] args) {
      new ChessGame().start();
//        Board chessBoard = new Board();
//        chessBoard.setKingsOnBoard();
//        Board clonedBoard =  chessBoard.CLONE();
//        System.out.println("Cloned Board: ");
//        clonedBoard.displayBoard();
//        clonedBoard.updateBoard(new Location(0, 4), new Location(0, 0));
//        clonedBoard.updateBoard(clonedBoard.getKing(Color.WHITE).getLocation(),new Location(1, 0));
//        System.out.println("king location: " + clonedBoard.getKing(Color.WHITE).getLocation());
//        System.out.println("Cloned Board After moving the king: ");
//        clonedBoard.displayBoard();
//        System.out.println("Original Board");
//        chessBoard.displayBoard();

    }
}