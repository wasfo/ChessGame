package Game;
import Pieces.*;
import Player.*;
import java.util.ArrayList;
import java.util.List;


public class ChessGame {
    private Board chessBoard = new Board();
    BoardController boardController = new BoardController(this.chessBoard);
    private final List<Move> movesHistory = new ArrayList<Move>();
    private Player whitePlayer, blackPlayer;

    public void start() {
        whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
        King blackKing = chessBoard.getKing(Color.BLACK);

        chessBoard.setPieceOnLocation(blackKing, new Location(5, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(4, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(5, 0));

        chessBoard.displayCurrentPosition();
        System.out.println();
        System.out.println();

        System.out.println(blackKing.calculateLegalMoveLocations(chessBoard, blackPlayer));

        System.out.println();
//        for (int i = 0; i < 50; i++) {
//            Player currentPlayer = (i % 2 == 0 ? whitePlayer : blackPlayer);
//            Move currentMove = (i % 2 == 0 ? whitePlayer : blackPlayer).makeMove();
//
//            while (!boardController.isLegalMove(currentMove, currentPlayer)) {
//                currentMove = (i % 2 == 0 ? whitePlayer : blackPlayer).makeMove();
//                System.out.println(currentMove);
//                System.out.println("Incorrect move, please re-enter: ");
//            }
//
//            chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
//            chessBoard.displayCurrentPosition();
//        }


    }

    public static void main(String[] args) {

        new ChessGame().start();
        // InputMoveHandler.ProcessMove(InputMoveHandler.EnterMove());
    }

}