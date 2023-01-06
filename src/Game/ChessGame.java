package Game;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
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
      //new ChessGame().start();
        quickTester();

    }

    public static void quickTester(){
        Board chessBoard = new Board();
        Player whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        Player blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);

        chessBoard.setKingsOnBoard();
        chessBoard.setRooksOnBoard();
        chessBoard.setKnightsOnBoard();
        while(true) {
            chessBoard.displayBoard();
            Move currentMove = whitePlayer.makeMove();
            chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
            System.out.println(chessBoard.getSpecificSquare(new Location(0,6))
                    .getPiece().calculateDefendMoves(chessBoard,whitePlayer));
        }
    }
}