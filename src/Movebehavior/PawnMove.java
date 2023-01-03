package Movebehavior;
import Game.*;
import java.util.ArrayList;
public class PawnMove implements MoveBehavior {
    @Override
    public ArrayList<Location> calculatePossibleLocations(Location pawnLocation, Board board) {
        ArrayList<Location> allLocations = new ArrayList<>();
        ArrayList<Location> possibleLocations = new ArrayList<>();
        Square currentSquare = board.getSpecificSquare(pawnLocation);
        Color pawnColor = currentSquare.getPiece().getColor();
        int[] directional_y = {0};
        int[] directional_x = { pawnColor == Color.BLACK ? -1 : +1 };
        allLocations.addAll(MoveDirector.DirectMove(pawnLocation, board, directional_x, directional_y));
        if(allLocations.isEmpty())
            return possibleLocations;
        for (int i = 0; i < 2; i++) {
            possibleLocations.add(allLocations.get(i));
            if(allLocations.size() == 1)
                break;
        }
        if (isPawnFirstMove(pawnLocation, pawnColor))
            return possibleLocations;
        ArrayList<Location> onePossibleLocation = new ArrayList<>();
        onePossibleLocation.add(possibleLocations.get(0));
         return onePossibleLocation;
    }

    public boolean isPawnFirstMove(Location pawnLocation, Color pawnColor) {
        return (pawnColor == Color.BLACK && pawnLocation.getX() == 6) ||
                (pawnColor == Color.WHITE && pawnLocation.getX() == 1);
    }
}
