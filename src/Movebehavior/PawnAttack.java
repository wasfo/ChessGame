package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class PawnAttack {

    public ArrayList<Location> CalculateLocations(Location location, Board board, Color PawnColor) {
        ArrayList<Location> availableLocations = new ArrayList<>();

        int[] directional_x = {PawnColor == Color.BLACK ? +1 : -1, PawnColor == Color.BLACK ? +1 : -1};
        int[] directional_y = {PawnColor == Color.BLACK ? -1 : +1, PawnColor == Color.BLACK ? +1 : -1};

        for (int i = 0; i < 2; i++) {
            int tempX = location.getX() + directional_x[i];
            int tempY = location.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (nextSquare.getPiece().getColor() != PawnColor &&
                        nextSquare.getPiece().getType()!= PieceType.KING) {
                    availableLocations.add(tempLocation);
                }
            }
        }
        return availableLocations;
    }
}
