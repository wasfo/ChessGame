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
        King king = player.getPlayerKing();
        if (!king.isInCheck(board)) { // if the king is not under check
            return possibleLocationsForKnight;
        } else {
            return possibleLocationsForKnight;
        }
    }

    public boolean calculateDefendMoves(final Board board, final Player player , Piece defendPiece) {
       // Location defendPieceOriginalLocation = defendPiece.getLocation();
        ArrayList<Location> possibleLocationsForDefendPiece= new ArrayList<>(defendPiece.calculateLegalMoveLocations(board,player));
        ArrayList<Location> defendMoves= new ArrayList<>();

        if(possibleLocationsForDefendPiece.isEmpty())
            return false;
        for (int i = 0; i < possibleLocationsForDefendPiece.size(); i++) {
            board.updateBoard(defendPiece.getLocation(), possibleLocationsForDefendPiece.get(i));
            if(!player.getPlayerKing().isInCheck(board)){
                defendMoves.add(possibleLocationsForDefendPiece.get(i));
            }

        }
        return true;
    }

}