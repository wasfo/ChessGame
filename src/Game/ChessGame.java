package Game;
import Pieces.*;
import Player.*;
import java.util.ArrayList;
import java.util.List;


public class ChessGame {
    private final Board chessBoard = new Board();
    BoardManager boardManager = new BoardManager(this.chessBoard);
    private final List<Move> movesHistory = new ArrayList<Move>();
    private Player whitePlayer, blackPlayer;

    public void start() {
        whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);

        Board newBoard = new Board();

        newBoard.setPieceOnLocation(new Rook(Color.BLACK) , new Location(6,5));
        newBoard.setPieceOnLocation(new Rook(Color.BLACK) , new Location(7,6));
    //    newBoard.setKingsOnBoard();
        King whiteKing = new King(Color.WHITE);

        newBoard.setPieceOnLocation(whiteKing, new Location(3,7));

        newBoard.updateBoard(new Location(0,0), new Location(1,1));

       // newBoard.updateBoard(new Location(6,5), new Location(6,7));





        newBoard.displayBoard();
        System.out.println();

        BoardManager boardManager = new BoardManager(newBoard);

        Player whitePlayer1 = new Player(whiteKing,Color.WHITE);

        System.out.println(whitePlayer1.getPlayerKing().isCheckMated(newBoard,whitePlayer1));

        Move newMove = whitePlayer1.makeMove();
        System.out.println("is legal move: " + boardManager.isLegalMove(newMove,whitePlayer1));
       // boardManager.ApplyMove(newMove,whitePlayer1);
        newBoard.displayBoard();

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