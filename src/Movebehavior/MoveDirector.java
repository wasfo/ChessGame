package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class MoveDirector {

    /**
     * this function finds the legal moves for a piece based on the directional array given.
     * the directional arrays directional_x and directional_y specify where to search on.
     */
    public static ArrayList<Location> DirectMove(Location pieceLocation, Board board, int directional_x[], int directional_y[]){
        ArrayList<Location> availableLocations = new ArrayList<>();
        Square currentSquare = board.getSpecificSquare(pieceLocation);
        Color pieceColor = currentSquare.getPiece().getColor();
        for (int i = 0; i < directional_x.length; i++) {
            int tempX = pieceLocation.getX() + directional_x[i];
            int tempY = pieceLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            while (isLocationOnBoard(tempLocation)) {
                Square tempLocationSquare = board.getSpecificSquare(tempLocation);
                if (tempLocationSquare.getPiece() != null) {
                    if (tempLocationSquare.getPiece().getColor() != pieceColor &&
                            tempLocationSquare.getPiece().getType() != PieceType.KING) {
                        availableLocations.add(tempLocation);
                    }
                    break;
                }
                availableLocations.add(tempLocation);
                tempX += directional_x[i];
                tempY += directional_y[i];
                tempLocation = new Location(tempX, tempY);
            }
        }
        return availableLocations;
    }
}
