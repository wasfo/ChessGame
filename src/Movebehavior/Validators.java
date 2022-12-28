package Movebehavior;

import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

public class Validators {

    public static boolean isLocationOnBoard(Location location) {
        return location.getX() >= 0 && location.getX() < 8 && location.getY() >= 0 && location.getY() < 8;
    }

    public static boolean isCollidedWithSameColor(Color first, Color second) {
        return first == second;
    }
    public static boolean ValidateLocation(Square currentSquare, Square newSquare) {

        /**
         *   check if this location has no pieces
         *   check if this location doesn't have same piece color
         *   check if this location has enemy king
         *   check if this location has enemy piece
         */

        if (newSquare.getPiece() == null) {
            return true;
        }
        return (currentSquare.getPiece().getColor() != newSquare.getPiece().getColor()
                && newSquare.getPiece().getType() != PieceType.KING);
    }
}
