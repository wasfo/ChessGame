package Game;

import Pieces.Piece;
import Player.Move;
import Player.Player;
import java.util.ArrayList;
public class BoardManager {
    Board board;

    public Board getBoard() {
        return this.board;
    }

    public BoardManager(Board board) {
        this.board = board;
    }

    public void isCheck(Board board) {


    }
    public boolean isNotSameColor(Color first, Color second) { // please ahmad check this function
        return first != second;
    }

    public boolean isLegalMove(Move currentMove, Player currentPlayer) {
        //check on location validity
        if (currentMove.getEndLocation() == null || currentMove.getStartLocation() == null) {
            System.out.println("Invalid move");
            return false;
        }

        Square startSquare = board.getSpecificSquare(currentMove.getStartLocation());
        Square targetSquare = board.getSpecificSquare(currentMove.getEndLocation());

        // please make this cleaner
        if (startSquare.getPiece() == null || isNotSameColor(startSquare.getPiece().getColor(), currentPlayer.getColor())) {
            return false;
        }

        Piece startPiece = startSquare.getPiece();
        // no moves allowed
        ArrayList<Location> legalMoveLocations = startPiece.calculateLegalMoveLocations(this.board, currentPlayer);
        if (legalMoveLocations.isEmpty()) {
            System.out.println("no moves allowed for  "+ startPiece);
            return false;
        }
        if (!legalMoveLocations.contains(currentMove.getEndLocation())){
            System.out.println("we cant move " +  startPiece + " to this " + currentMove.getEndLocation());
            return false;
        }


        /*
        to check if the piece is pinned or not, we simply do the following:
            1- if this function returns that our move is HALF correct, we have to
               check if currentPlayer's king gets checked after moving this piece.
            2- if the king gets checked, then we have to undo this move, to make this
               move FULLY correct.
            3- else, we keep things as is.
         */
//        board.UpdateBoard(currentMove.getStartLocation() , currentMove.getEndLocation());
//        // call king.scan
//        //check if king is checked, if yes return false
//        boolean check = currentPlayer.getPlayerKing().isUnderCheck() ;
//        board.UpdateBoard(currentMove.getStartLocation() , currentMove.getEndLocation());

       // return check == false;
        return true;
    }




}
