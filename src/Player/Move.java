package Player;

import Game.InputMoveHandler;
import Game.Location;

public class Move {
    private Location startLocation;
    private Location endLocation;
    public Move(Location startLocation, Location endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }
    public Location getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }
    public Location getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }


    @Override
    public String toString() {
        return "Move{" + startLocation + " " + endLocation + '}';
    }
}
