package Game;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Player.*;
import java.util.ArrayList;
import java.util.List;
public class ChessGame {
    private final Board chessBoard = new Board();
    BoardManager boardManager = new BoardManager(this.chessBoard);
    private final List<Move> movesHistory = new ArrayList<Move>();

    public void start() {

        chessBoard.whiteKing = new King(Color.WHITE);
        chessBoard.blackKing = new King(Color.BLACK);

        Player whitePlayer = new Player(chessBoard.whiteKing, Color.WHITE);
        Player blackPlayer = new Player(chessBoard.blackKing, Color.BLACK);
        chessBoard.setRooksOnBoard();

        chessBoard.whiteKing.setLocation(new Location(7,5));
        chessBoard.setPieceOnLocation(chessBoard.whiteKing, new Location( 6 ,5));
        System.out.println(chessBoard.whiteKing.calculateLegalMoveLocations(chessBoard,whitePlayer));
       System.out.println( chessBoard.blackKing.isInCheck(chessBoard));



        chessBoard.displayBoard();

//        System.out.println("chessBoard");
//        chessBoard.displayBoard();
//        System.out.println();
//        System.out.println("clonedBoard");
//        clonedBoard.displayBoard();

      //  System.out.println(chessBoard.getBlackPieces());



//        for (int i = 0; i < 50; i++) {
//            Player currentPlayer = (i % 2 == 0 ? whitePlayer : blackPlayer);
//            System.out.print(currentPlayer + " enter move:");
//            Move currentMove = (i % 2 == 0 ? whitePlayer : blackPlayer).makeMove();
//
//            while (!boardController.isLegalMove(currentMove, currentPlayer)) {
//                System.out.println(currentMove);
//                System.out.println("Incorrect move, please re-enter: ");
//                currentMove = (i % 2 == 0 ? whitePlayer : blackPlayer).makeMove();
//            }
//
//            chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
//            chessBoard.displayCurrentPosition();
//        }


    }

    public static void main(String[] args) {
       new ChessGame().start();

    }

}