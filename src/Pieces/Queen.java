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

public class Queen extends Piece {
    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> availableLocations = new ArrayList<>();

        MoveBehavior Diagonal = new DiagonalMove();
        MoveBehavior Horizental = new HorizentalMove();
        MoveBehavior Vertical = new VerticalMove();

        availableLocations.addAll(Diagonal.calculatePossibleLocations(this.getLocation(), board));
        availableLocations.addAll(Horizental.calculatePossibleLocations(this.getLocation(), board));
        availableLocations.addAll(Vertical.calculatePossibleLocations(this.getLocation(), board));

        return availableLocations;
    }
}
