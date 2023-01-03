package Pieces;
import Game.Board;
import Game.Color;
import Game.Location;
import Movebehavior.DiagonalMove;
import Movebehavior.MoveBehavior;
import Player.Player;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop( Color color) {
        super(PieceType.BISHOP, color);
    }
    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> possibleLocations = new ArrayList<>();
        MoveBehavior Diagonal = new DiagonalMove();
        possibleLocations.addAll(Diagonal.calculatePossibleLocations(this.getLocation(), board));
        return possibleLocations;
    }
}
