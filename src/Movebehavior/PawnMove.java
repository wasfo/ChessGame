package Movebehavior;
import Game.*;
import static Movebehavior.Validators.isLocationOnBoard;
import java.util.ArrayList;
public class PawnMove implements MoveBehavior {
    @Override
    public ArrayList<Location> calculatePossibleLocations(Location pawnLocation, Board board) {
        ArrayList<Location> possibleLocations = new ArrayList<>();
        int x = pawnLocation.getX(), y = pawnLocation.getY();
        Color pawnColor = board.getSpecificSquare(pawnLocation).getPiece().getColor();
        int[] directional_x = {pawnColor == Color.WHITE ? +1 : -1, pawnColor == Color.WHITE ? +2 : -2};
        int[] directional_y = {0, 0};
        for (int i = 0; i < 2; i++) {
            Location nextLocation = new Location(directional_x[i] + x, directional_y[i] + y);
            addLocationIfNull(board, possibleLocations, nextLocation);
        }
        if (isPawnFirstMove(pawnLocation, pawnColor))
            return possibleLocations;

        ArrayList<Location> onePossibleLocation = new ArrayList<>();
        onePossibleLocation.add(possibleLocations.get(0));
        return onePossibleLocation;


    }

    private static void addLocationIfNull(Board board, ArrayList<Location> possibleLocations, Location nextLocation) {
        if (isLocationOnBoard(nextLocation) && board.getSpecificSquare(nextLocation).getPiece()==null) {
                possibleLocations.add(nextLocation);
        }
    }

    public boolean isPawnFirstMove(Location pawnLocation, Color pawnColor) {
        return (pawnColor == Color.BLACK && pawnLocation.getX() == 6) ||
                (pawnColor == Color.WHITE && pawnLocation.getX() == 1);
    }
}
