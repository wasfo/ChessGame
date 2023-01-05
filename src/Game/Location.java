package Game;

import java.util.Objects;

public class Location implements Cloneable {
    private int x;
    private int  y;

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return  x +""+ y;
    }
    @Override
    public boolean equals(Object obj) {
        Location newObj = (Location) obj;
        return(this.x == newObj.x && this.y == newObj.y);
    }

    @Override
    public Object clone() {
        return new Location(getX(), getY());

    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
