package Movebehavior;
import Game.Board;
import Game.Location;

import java.util.ArrayList;

public class HorizentalMove implements MoveBehavior {

    private final int[] directional_x = {+1, -1};
    private final int[] directional_y = {0, 0};


    @Override
    public ArrayList<Location> calculatePossibleLocations(Location location, Board board) {
        return MoveDirector.DirectMove(location, board, this.directional_x, this.directional_y);
    }
}
