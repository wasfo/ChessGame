package Movebehavior;
import Game.Board;
import Game.Location;
import java.util.ArrayList;
public interface MoveBehavior {

     /**
      * calculate piece moves locations based on the behavior.
      */
     public ArrayList<Location> calculatePossibleLocations(Location location, Board board);
}
