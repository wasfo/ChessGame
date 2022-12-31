package Game;

import Pieces.*;
import Player.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {
    private Board chessBoard = new Board();
    private List<Move> movesHistory = new ArrayList<Move>();
    private Player whitePlayer, blackPlayer;

    public static void EnterMove(Player player){
        Scanner scanner = new Scanner(System.in);
        String  enteredMove = scanner.nextLine();

        System.out.println(enteredMove.matches("[a-h][1-8]"));

        System.out.println(enteredMove.matches("\\bmove\\s[a-h][1-8]\\s[a-h][1-8]"));

        String[] moveArguments = enteredMove.replaceAll("\\s+$", "").split(" ");


    }

    public void start() {
        whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
        King blackKing = chessBoard.getKing(Color.BLACK);

        chessBoard.setPieceOnLocation(blackKing, new Location(5, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(4, 7));
        chessBoard.setPieceOnLocation(new Rook(Color.WHITE), new Location(5, 0));

       // chessBoard.UpdateBoard(new Location(4, 7), new Location(3, 7));
        chessBoard.DisplayCurrentPosition();
        System.out.println(blackKing.isInCheck(chessBoard));
        System.out.println(blackKing.CalculateLegalMoveLocations(chessBoard, blackPlayer));

        // System.out.println(blackKing.getLocation());

        //  System.out.println(blackKing.CalculateLegalMoveLocations(chessBoard, blackPlayer));

        //  chessBoard.SetupBoard();


        // BoardController boardController= new BoardController(this.chessBoard);

//        System.out.println();
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number of correct Moves: ");
        // int numberOfMoves = scanner.nextInt();

//        for (int i = 0; i < numberOfMoves; i++) {
//            System.out.print("from: ");
//            int x1 = scanner.nextInt();
//            int y1 = scanner.nextInt();
//            System.out.print(", to: ");
//            int x2 = scanner.nextInt();
//            int y2 = scanner.nextInt();
//            System.out.println();
//
//            Move currentMove = new Move(new Location(x1,y1), new Location(x2, y2));
//
//            while (!boardController.isLegalMove(currentMove, (i % 2 == 0 ? whitePlayer : blackPlayer))){
//                System.out.println("Incorrect move, please re-enter: ");
//
//                System.out.print("from: ");
//                x1 = scanner.nextInt();
//                y1 = scanner.nextInt();
//                System.out.print(", to: ");
//                x2 = scanner.nextInt();
//                y2 = scanner.nextInt();
//                System.out.println();
//            }
//
//            chessBoard.UpdateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
//            chessBoard.DisplayCurrentPosition();
//        }

    }

    public static void main(String[] args) {

        ChessGame.EnterMove(new Player(new King(Color.BLACK),Color.BLACK));
      //  new ChessGame().start();
    }

}