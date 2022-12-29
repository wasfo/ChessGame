package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class MoveDirector {


    public static ArrayList<Location> DirectMove(Location location, Board board, int directional_x[], int directional_y[]){
        int x = location.getX();
        int y = location.getY();
        ArrayList<Location> availableLocations = new ArrayList<>();

        Square currentSquare = board.getSpecifiedSquare(location);
        Color pieceColor = currentSquare.getPiece().getColor();

        for (int i = 0; i < directional_x.length; i++) {
            int tempX = x + directional_x[i];
            int tempY = y + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            while (isLocationOnBoard(tempLocation)) { // && tempLocation.piece == null
                Square tempLocationSquare = board.getSpecifiedSquare(tempLocation);
                if (tempLocationSquare.getPiece() != null) {
                    if (tempLocationSquare.getPiece().getColor() != pieceColor &&
                            tempLocationSquare.getPiece().getType() != PieceType.KING) {
                        // we have to handle the king thingy (we can't eat the king)
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
