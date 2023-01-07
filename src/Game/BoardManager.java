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
    public void setupBoard(){
        this.board.setupBoard();
    }
    public boolean isNotSameColor(Color first, Color second) {
        return first != second;
    }
    public boolean isLegalMove(Move currentMove, Player currentPlayer) {
        if (currentMove.getEndLocation() == null || currentMove.getStartLocation() == null) {
            System.out.println("Invalid move");
            return false;
        }
        Square startSquare = board.getSpecificSquare(currentMove.getStartLocation());
        if (startSquare.getPiece() == null || isNotSameColor(startSquare.getPiece().getColor(), currentPlayer.getColor())) {
            return false;
        }
        Piece grabbedPiece = startSquare.getPiece();
        ArrayList<Location> legalMoveLocations = grabbedPiece.calculateLegalMoveLocations(this.board, currentPlayer);
        if (legalMoveLocations.isEmpty()) {
            System.out.println("this "+ grabbedPiece + " is blocked or pinned");
            return false;
        }
        if (!legalMoveLocations.contains(currentMove.getEndLocation())){
            System.out.println("we cant move " +  grabbedPiece + " to this location" + currentMove.getStartLocation() );
            return false;
        }
       return isKingSafeAfterTestingTheMove(currentMove,currentPlayer);
    }

    public boolean isKingSafeAfterTestingTheMove(Move currentMove , Player currentPlayer){
        Square startSquare = board.getSpecificSquare(currentMove.getStartLocation());
        Square targetSquare = board.getSpecificSquare(currentMove.getEndLocation());
        this.board.updateBoard(startSquare.getLocation(),targetSquare.getLocation());
        if(currentPlayer.getPlayerKing().isInCheck(this.board)){
            this.board.updateBoard(targetSquare.getLocation(),startSquare.getLocation());
            return false;
        }
        this.board.updateBoard(targetSquare.getLocation(),startSquare.getLocation());
        return true;
    }
}
