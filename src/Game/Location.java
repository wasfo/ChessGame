package Game;

import java.util.Objects;

public class Location implements Cloneable {
    private int x_coordinate;
    private int  y_coordinate;

    public void setX(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }
    public void setY(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }
    public Location(int x, int y) {
        this.x_coordinate = x;
        this.y_coordinate = y;
    }
    public int getX() {
        return x_coordinate;
    }
    public int getY() {
        return y_coordinate;
    }

    @Override
    public String toString() {
        return  x_coordinate +""+ y_coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x_coordinate == location.x_coordinate && y_coordinate == location.y_coordinate;
    }

    @Override
    public Object clone() {
        return new Location(getX(), getY());

    }

    @Override
    public int hashCode() {
        return Objects.hash(x_coordinate, y_coordinate);
    }
}
