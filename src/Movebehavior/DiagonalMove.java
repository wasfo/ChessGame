package Movebehavior;
import Game.Board;
import Game.Location;
import java.util.ArrayList;

public class DiagonalMove implements MoveBehavior {
    private final int[] directional_x = {+1, -1, -1, +1};
    private final int[] directional_y = {+1, -1, +1, -1};

    @Override
    public ArrayList<Location> calculateLocations(Location location, Board board) {
        return MoveDirector.DirectMove(location, board, this.directional_x, this.directional_y);
    }
}
