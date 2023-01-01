package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class PawnAttack {

    public ArrayList<Location> calculateLocations(Location location, Board board, Color pawnColor) {
        ArrayList<Location> availableLocations = new ArrayList<>();

        int[] directional_x = {pawnColor == Color.BLACK ? +1 : -1, pawnColor == Color.BLACK ? +1 : -1};
        int[] directional_y = {pawnColor == Color.BLACK ? -1 : +1, pawnColor == Color.BLACK ? +1 : -1};

        for (int i = 0; i < 2; i++) {
            int tempX = location.getX() + directional_x[i];
            int tempY = location.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (nextSquare.getPiece().getColor() != pawnColor &&
                        nextSquare.getPiece().getType() != PieceType.KING) {
                    availableLocations.add(tempLocation);
                }
            }
        }
        return availableLocations;
    }
}
