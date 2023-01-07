package Game;

import Pieces.Piece;

import java.util.Objects;

public class Square implements Cloneable {
    protected Location location;
    private Color color;
    private Piece piece = null;
    public Square(Location location, Color color) {
        this.location = location;
        this.color = color;
        this.piece = null;
    }

    public Square(Location location, Color color, Piece piece) {
        this(location, color);
        this.piece = (Piece) piece.clone();
    }

    public void setPiece(Piece piece) {
        if (piece != null) {
            piece.setLocation(this.location);
            this.piece = piece;
        }
        else
            this.piece = null;
    }
    public boolean isEmpty() {
        return this.piece == null;
    }

    public void removePiece() {
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        if (this.piece == null)
            return this.location + " null";
        else {
            return this.location + " " + piece;
           // return   piece + "";
        }
    }

    public String indices() {
        return location.toString();
    }


    @Override
    public Object clone() {
        try {
            Square clone = (Square) super.clone();
            if(piece == null){
                clone.piece = null;
            }
            else {
                clone.piece = (Piece) piece.clone();
            }
            clone.color = color;
            clone.location = (Location) location.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(location, square.location) && color == square.color && Objects.equals(piece, square.piece);
    }
    @Override
    public int hashCode() {
        return Objects.hash(location, color, piece);
    }
}
