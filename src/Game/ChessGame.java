package Game;
import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;
import Player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ChessGame {

  private Board chessBoard  = new Board();
  private List<Move> movesHistory = new ArrayList<Move>();
  private Player whitePlayer, blackPlayer;

    public void start(){
        whitePlayer = new Player(new King(Color.WHITE),Color.WHITE);
        blackPlayer = new Player(new King(Color.BLACK),Color.BLACK);

        chessBoard = new Board();

        chessBoard.SetupBoard();
        chessBoard.DisplayCurrentPosition();

        BoardController boardController= new BoardController(this.chessBoard);

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of correct Moves: ");
        int numberOfMoves = scanner.nextInt();

        for (int i = 0; i < numberOfMoves; i++) {
            System.out.print("from: ");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            System.out.print(", to: ");
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            System.out.println();

            Move currentMove = new Move(new Location(x1,y1), new Location(x2, y2));

            while (!boardController.isLegalMove(currentMove, (i % 2 == 0 ? whitePlayer : blackPlayer))){
                System.out.println("Incorrect move, please re-enter: ");

                System.out.print("from: ");
                x1 = scanner.nextInt();
                y1 = scanner.nextInt();
                System.out.print(", to: ");
                x2 = scanner.nextInt();
                y2 = scanner.nextInt();
                System.out.println();
            }

            chessBoard.UpdateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
            chessBoard.DisplayCurrentPosition();
        }

    }

    public static void main(String[] args)
    {
            new ChessGame().start();
    }

}