package Pieces;
import Game.Board;
import Game.Color;
import Game.Location;
import Movebehavior.DiagonalMove;
import Movebehavior.HorizentalMove;
import Movebehavior.MoveBehavior;
import Movebehavior.VerticalMove;
import Player.Player;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop( Color color) {
        super(PieceType.BISHOP, color);
    }
    @Override
    public ArrayList<Location> CalculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> availableLocations = new ArrayList<>();
        MoveBehavior Diagonal = new DiagonalMove();
        availableLocations.addAll(Diagonal.CalculateLocations(this.getLocation(), board));
        return availableLocations;
    }
}
