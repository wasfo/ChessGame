package Pieces;
import Game.Board;
import Game.Color;
import Game.Location;
import Movebehavior.*;
import Player.Player;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }
    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> availableLocations = new ArrayList<>();

        MoveBehavior Horizental = new HorizentalMove();
        MoveBehavior Vertical = new VerticalMove();

        availableLocations.addAll(Horizental.calculatePossibleLocations(this.getLocation(), board));
        availableLocations.addAll(Vertical.calculatePossibleLocations(this.getLocation(), board));

        return availableLocations;
    }
}
