package Game;
import Pieces.*;
import Player.*;
import java.util.ArrayList;
import java.util.List;


public class ChessGame {
    private final Board chessBoard = new Board();
    BoardManager boardController = new BoardManager(this.chessBoard);
    private final List<Move> movesHistory = new ArrayList<Move>();
    private Player whitePlayer, blackPlayer;

    public void start() {
        whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
      //  chessBoard.setupBoard();
        Piece [] pieces = new Piece[3];
        pieces[0] = new Pawn(Color.WHITE);
        pieces[1] = new Pawn(Color.BLACK);
        pieces[2] = new Pawn(Color.WHITE);
        chessBoard.setPieceOnLocation(pieces[0],new Location(1,6));
        chessBoard.setPieceOnLocation(pieces[1],new Location(6,6));
        chessBoard.setPieceOnLocation(pieces[2],new Location(3,3));
        for (Piece piece:pieces) {
            System.out.print(piece + " ");
            System.out.println(piece.calculateLegalMoveLocations(this.chessBoard,whitePlayer)); ;
        }

        chessBoard.displayBoard();
        System.out.println();

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