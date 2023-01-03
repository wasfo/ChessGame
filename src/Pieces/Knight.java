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
    public ArrayList<Location> calculateLegalMoveLocations(final Board board, final Player player){

        ArrayList<Location> availableLocations = new ArrayList<>();
        Location currentLocation = this.getLocation();
        MoveBehavior knightMove = addMoveBehavior(new KnightMove());
        availableLocations.addAll(knightMove.calculatePossibleLocations(currentLocation, board));
        King king = player.getPlayerKing();
        if (!king.isInCheck(board)) { // if the king is not under check
            return availableLocations;
        } else {

            //defend moves

            return availableLocations;
        }
    }


}
