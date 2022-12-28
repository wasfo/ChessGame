package Game;

import Pieces.Piece;
import Player.Move;
import Player.Player;

import java.util.ArrayList;

public class BoardController {
    Board board;

    public Board getBoard() {
        return board;
    }

    public BoardController(Board board) {
        this.board = board;
    }

    public boolean isMoveAllowed(Location start, Location end) {
        Square startSquare = board.getSpecifiedSquare(start);
        Square endSquare = board.getSpecifiedSquare(end);
        return false;
    }


    public void isCheck(Board board) {


    }

    public void isCheckMate(Board board) {

    }

    public boolean isLocationValid(Location location) {
        return location.getX() >= 0 && location.getX() < 8 && location.getY() >= 0 && location.getY() < 8;
    }

    public boolean isSameColor(Color first, Color second) { // please ahmad check this function
        return first == second;
    }

    public boolean isLegalMove(Move currentMove, Player currentPlayer) {
        //check on location validity
        if (!isLocationValid(currentMove.getStartLocation()) || !isLocationValid(currentMove.getEndLocation())) {
            System.out.println("Location is invalid");
            return false;
        }

        Square startSquare = board.getSpecifiedSquare(currentMove.getStartLocation());
        Square targetSquare = board.getSpecifiedSquare(currentMove.getEndLocation());

        // please make this cleaner
        if (startSquare.getPiece() == null
                || !isSameColor(startSquare.getPiece().getColor(), currentPlayer.getColor())) {
            return false;
        }

        Piece startPiece = startSquare.getPiece();

        // no moves allowed
        ArrayList<Location> legalMoveLocations = startPiece.CalculateLegalMoveLocations(board, currentPlayer);
        if (legalMoveLocations.isEmpty()) {
            System.out.println("no moves allowed");
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
