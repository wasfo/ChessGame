package Movebehavior;

import Game.Board;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isCollidedWithSameColor;
import static Movebehavior.Validators.isLocationOnBoard;

public class KnightMove implements MoveBehavior {

    private final int[] directional_x = {2, 2, -2, -2, 1, 1, -1, -1};
    private final int[] directional_y = {-1, 1, -1, 1, 2, -2, 2, -2};

    @Override
    public ArrayList<Location> calculatePossibleLocations(Location knightLocation, Board board) {
        ArrayList<Location> availableLocations = new ArrayList<>();
        Square currentSquare = board.getSpecificSquare(knightLocation);
        for (int i = 0; i < 8; i++) {
            Location destinationLocation = new Location(knightLocation.getX() + this.directional_x[i], knightLocation.getY() + this.directional_y[i]);
            if (isLocationOnBoard(destinationLocation)) {
                Square destSquare = board.getSpecificSquare(destinationLocation);
                if(destSquare.getPiece()!= null) {
                    CheckColor(availableLocations, currentSquare, destinationLocation, destSquare);
                }
                else availableLocations.add(destinationLocation);
            }
        }
        return availableLocations;
    }

    private static void CheckColor(ArrayList<Location> availableLocations, Square currentSquare, Location destinationLocation, Square destSquare) {
        if (!isCollidedWithSameColor(currentSquare.getPiece().getColor(), destSquare.getPiece().getColor())
        && destSquare.getPiece().getType() != PieceType.KING) {
                availableLocations.add(destinationLocation);
        }
    }
}
