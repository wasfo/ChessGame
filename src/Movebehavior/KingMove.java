package Movebehavior;

import Game.Board;
import Game.Location;
import Game.Square;
import Pieces.King;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class KingMove implements MoveBehavior {
    private final int[] directional_x = {1, 1, -1, -1, 0, 0, 1, -1};
    private final int[] directional_y = {-1, 1, -1, 1, 1, -1, 0, 0};

    @Override
    public ArrayList<Location> CalculateLocations(Location location, Board board) {
        ArrayList<Location> availableLocations = new ArrayList<>();
        /**
         * to calculate legal locations for the king, I have to check on many things :
         * 1) next location is on board
         * 2) next location has enemy piece but this piece isn't protected by another enemy piece
         * 3) next location doesn't have AllyPiece
         * 4) next location isn't threatened by enemy piece
         * change king location temporarily to check if that location is threatened or not
         */
        int x = location.getX();
        int y = location.getY();
        Square currentSquare = board.getSpecificSquare(location);
        King currentKing = board.getKing(currentSquare.getPiece().getColor());


        for (int i = 0; i < 8; i++) {
            Location destinationLocation = new Location(x + this.directional_x[i], y + this.directional_y[i]);
            if (isLocationOnBoard(destinationLocation)) {
                Square nextSquare = board.getSpecificSquare(destinationLocation);
                Board clonedBoard = board.clone();
                King clonedKing = currentKing.clone();
                if (nextSquare.getPiece() == null) {
                    clonedBoard.UpdateBoard(clonedKing.getLocation(), destinationLocation);
                    if (!clonedKing.isInCheck(clonedBoard)) {
                        availableLocations.add(destinationLocation);
                        System.out.println(destinationLocation + " is king on check "+clonedKing.isInCheck(clonedBoard));
                    }

                } else if (nextSquare.getPiece() != null &&
                        nextSquare.getPiece().getColor() != currentKing.getColor()) {

                    clonedBoard.getSpecificSquare(destinationLocation).RemovePiece();
                    clonedBoard.UpdateBoard(clonedKing.getLocation(), destinationLocation);
                    // pretend that the king ate the opponent piece, will the king be in check?
                    if (!clonedKing.isInCheck(clonedBoard)) {
                        availableLocations.add(destinationLocation);}

                }
            }
        }
        return availableLocations;
    }
}
