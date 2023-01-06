package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class PawnAttack implements MoveBehavior {

    public ArrayList<Location> calculatePossibleLocations(Location pawnLocation, Board board) {
        ArrayList<Location> possibleLocations = new ArrayList<>();
        Square currentSquare = board.getSpecificSquare(pawnLocation);
        Color pawnColor = currentSquare.getPiece().getColor();
        int[] directional_x = {pawnColor == Color.WHITE ? +1 : -1, pawnColor == Color.WHITE ? +1 : -1};
        int[] directional_y = {pawnColor == Color.WHITE ? -1 : +1, pawnColor == Color.WHITE ? +1 : -1};
        for (int i = 0; i < 2; i++) {
            int tempX = pawnLocation.getX() + directional_x[i];
            int tempY = pawnLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (nextSquare.getPiece().getColor() != pawnColor &&
                        nextSquare.getPiece().getType() != PieceType.KING) {
                    possibleLocations.add(tempLocation);
                }
            }
        }
        return possibleLocations;
    }
}
