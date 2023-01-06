package Pieces;

import Game.*;
import Movebehavior.MoveBehavior;
import Player.Player;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece implements Cloneable {
    protected PieceType type;
    protected Color color;
    protected Location location;

    public MoveBehavior addMoveBehavior(MoveBehavior moveBehavior) {
        return moveBehavior;
    }

    public Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;

    }

    public Piece() {
        this.type = null;
        this.color = null;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }


    public void setType(PieceType type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract ArrayList<Location> calculateLegalMoveLocations(final Board board, Player player);

    public ArrayList<Location> calculateDefendMoves(final Board board, final Player player) {
        ArrayList<Location> possibleLocationsForDefendPiece = new ArrayList<>(this.calculateLegalMoveLocations(board, player));
        ArrayList<Location> defendMoves = new ArrayList<>();
        if (possibleLocationsForDefendPiece.isEmpty())
            return defendMoves;
        for (Location defendPieceLocation : possibleLocationsForDefendPiece) {
            Piece removedPiece = board.getSpecificSquare(defendPieceLocation).getPiece();
            Square currentSquare = board.getSpecificSquare(this.getLocation());
            board.updateBoard(currentSquare.getLocation(), defendPieceLocation);
            if (!player.getPlayerKing().isInCheck(board)) {
                defendMoves.add(defendPieceLocation);
            }
            board.updateBoard(defendPieceLocation, currentSquare.getLocation());
            if (removedPiece != null)
                board.setPieceOnLocation(removedPiece, removedPiece.getLocation());
        }
        return defendMoves;
    }

    @Override
    public String toString() {
        return location + " " + color.toString().toLowerCase() + "(" + type.name() + ")";
    }

    @Override
    public Object clone() {
        try {
            Piece clone = (Piece) super.clone();
            if (this.location == null) {
                clone.location = null;
            } else {
                clone.location = (Location) location.clone();
            }
            clone.type = type;
            clone.color = color;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color && Objects.equals(location, piece.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, location);
    }
}
