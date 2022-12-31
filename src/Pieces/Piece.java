package Pieces;
import Game.*;
import Movebehavior.MoveBehavior;
import Player.Player;

import java.util.ArrayList;

public abstract class Piece {
    protected PieceType type;
    protected Color color;
    protected Location location;
    public MoveBehavior AddMoveBehavior(MoveBehavior moveBehavior) {
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

    public abstract ArrayList<Location> CalculateLegalMoveLocations(final Board board, Player player);

    @Override
    public String toString() {
        return color + type.name();
    }
}
