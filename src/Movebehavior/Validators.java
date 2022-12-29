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

}
