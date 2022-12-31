package Pieces;

import Game.Board;
import Game.Color;
import Game.Location;
import Player.Player;

import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }
    @Override
    public ArrayList<Location> CalculateLegalMoveLocations(Board board, Player player) {
        return null;
    }
}
