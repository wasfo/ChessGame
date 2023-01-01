package Game;
import Pieces.*;
import Player.*;
import java.util.ArrayList;
import java.util.List;


public class ChessGame {
    private  Board chessBoard = new Board();
    BoardController boardController= new BoardController(this.chessBoard);
    private final List<Move> movesHistory = new ArrayList<Move>();
    private Player whitePlayer, blackPlayer;

    public void SwitchTurn(Player currentPlayer){
        if(currentPlayer == this.whitePlayer)
            currentPlayer = blackPlayer;
        else
            currentPlayer = whitePlayer;
    }



    public void start() {
        whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
        King blackKing = chessBoard.getKing(Color.BLACK);

        chessBoard.setPieceOnLocation(blackKing, new Location(5, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(4, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(5, 0));

        chessBoard.DisplayCurrentPosition();
        System.out.println(blackKing.isInCheck(chessBoard));
        System.out.println(blackKing.CalculateLegalMoveLocations(chessBoard, blackPlayer));


        System.out.println();
        for (int i = 0; i < 50; i++) {
         Move currentMove =   Player.MakeMove();

            while (!boardController.isLegalMove(currentMove,)){
                System.out.println("Incorrect move, please re-enter: ");
            }

            chessBoard.UpdateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
            chessBoard.DisplayCurrentPosition();
        }
        

    }

    public static void main(String[] args) {

          new ChessGame().start();
    }

}