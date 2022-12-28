package Movebehavior;

import Game.Board;
import Game.Location;

import java.util.ArrayList;

public class VerticalMove implements MoveBehavior{
    private int directional_x[] = {0, 0};
    private int directional_y[] = {+1, -1};

    @Override
    public ArrayList<Location> CalculateLocations(Location location, Board board) {
        return GeneralMove.DirectMove(location,board, this.directional_x, this.directional_y);
    }
}
