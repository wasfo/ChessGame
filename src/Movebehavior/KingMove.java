package Movebehavior;

import Game.Board;
import Game.Location;
import Game.Square;
import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class KingMove implements MoveBehavior {
    private final int[] directional_x = {1, 1, -1, -1, 0, 0, 1, -1};
    private final int[] directional_y = {-1, 1, -1, 1, 1, -1, 0, 0};

    @Override
    public ArrayList<Location> calculatePossibleLocations(Location kingLocation, Board board) {
        ArrayList<Location> availableLocations = new ArrayList<>();
        /**
         * to calculate legal locations for the king, I have to check on many things :
         * 1) next location is on board
         * 2) next location has enemy piece but this piece isn't protected by another enemy piece
         * 3) next location doesn't have AllyPiece
         * 4) next location isn't threatened by enemy piece
         * change king location temporarily to check if that location is threatened or not
         */
        Square currentSquare = board.getSpecificSquare(kingLocation);
        King currentKing = board.getKing(currentSquare.getPiece().getColor());
        for (int i = 0; i < 8; i++) {
            Location destinationLocation = new Location(kingLocation.getX() + this.directional_x[i], kingLocation.getY() + this.directional_y[i]);
            if (isLocationOnBoard(destinationLocation)) {
                Square nextSquare = board.getSpecificSquare(destinationLocation);
                if (nextSquare.getPiece() == null) {
                    board.updateBoard(currentKing.getLocation(), destinationLocation);
                    if (!currentKing.isInCheck(board)) {
                        availableLocations.add(destinationLocation);
                    }
                    board.updateBoard(destinationLocation, kingLocation);
                } else if (nextSquare.getPiece().getColor() != currentKing.getColor()) {
                    Piece removedPiece = board.getSpecificSquare(destinationLocation).getPiece();
                    board.getSpecificSquare(destinationLocation).removePiece();
                    board.updateBoard(currentKing.getLocation(), destinationLocation);
                    if (!currentKing.isInCheck(board)) {
                        availableLocations.add(destinationLocation);
                    }
                    board.updateBoard(destinationLocation, kingLocation);
                    board.setPieceOnLocation(removedPiece, destinationLocation);
                }
            }
        }
        return availableLocations;
    }
}
