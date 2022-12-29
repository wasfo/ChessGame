package Movebehavior;

import Game.Board;
import Game.Location;

import java.util.ArrayList;

public class VerticalMove implements MoveBehavior{
    private final int[] directional_x = {0, 0};
    private final int[] directional_y = {+1, -1};

    @Override
    public ArrayList<Location> CalculateLocations(Location location, Board board) {
        return MoveDirector.DirectMove(location,board, this.directional_x, this.directional_y);
    }
}
