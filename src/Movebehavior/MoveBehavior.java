package Movebehavior;
import Game.Board;
import Game.Location;
import java.util.ArrayList;
public interface MoveBehavior {
     public ArrayList<Location> calculateLocations(Location location, Board board);
}
