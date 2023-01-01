package Game;

import Pieces.Piece;

public class Square {

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
        this.piece = piece;
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
            return "";
        else {
            return piece + "";
        }
    }

    public String indices() {
        return location.toString();
    }

}
