package Pieces;
import Game.*;
import Game.Color;
import Movebehavior.KnightMove;
import Movebehavior.MoveBehavior;
import Player.Player;
import java.util.ArrayList;


public class Knight extends Piece {
    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    public ArrayList<Location> calculateLegalMoveLocations(final Board board, final Player player) {
        Location currentLocation = this.getLocation();
        MoveBehavior knightMove = addMoveBehavior(new KnightMove());
        ArrayList<Location> possibleLocationsForKnight = new ArrayList<>((knightMove.calculatePossibleLocations(currentLocation, board)));
        return possibleLocationsForKnight;
    }

}