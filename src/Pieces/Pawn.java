package Pieces;

import Game.Board;
import Game.Color;
import Game.Location;
import Movebehavior.DiagonalMove;
import Movebehavior.MoveBehavior;
import Player.Player;

import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }
    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> possibleLocations = new ArrayList<>();
        MoveBehavior pawnAttack = new DiagonalMove();
        MoveBehavior pawnMove = new DiagonalMove();
        possibleLocations.addAll(pawnAttack.calculatePossibleLocations(this.getLocation(), board));
        possibleLocations.addAll(pawnMove.calculatePossibleLocations(this.getLocation(), board));
        return possibleLocations;
    }
}
