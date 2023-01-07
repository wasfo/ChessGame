package Game;
import Player.*;

public class ChessGame {

    public void start() {
        Player whitePlayer = new Player(Color.WHITE);
        Player blackPlayer = new Player(Color.BLACK);
        new GameSetup(whitePlayer,blackPlayer);
        GameSetup.startGame();
    }

    public static void main(String[] args) {

      new ChessGame().start();
     // quickTester();

    }

    public static void quickTester(){
        Board chessBoard = new Board();
        Player whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        Player blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
        chessBoard.setKingsOnBoard();
        chessBoard.setRooksOnBoard();
        chessBoard.setBishopsOnBoard();
        chessBoard.setQueensOnBoard();
        chessBoard.updateBoard(new Location(7, 3), new Location(6, 4));
        chessBoard.displayBoard();

      //  System.out.println(chessBoard.getKing(Color.WHITE).calculateDefendMovesForAllyPiece(chessBoard.getSpecificSquare(new Location(0, 5)).getPiece(),chessBoard, whitePlayer));

        while(true) {
            chessBoard.displayBoard();
            Move currentMove = whitePlayer.makeMove();
            chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
            System.out.println(chessBoard.getKing(Color.WHITE).defendMovesForKing(chessBoard,whitePlayer));

        }
    }
}