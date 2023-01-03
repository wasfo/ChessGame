package Pieces;
import Game.*;
import Movebehavior.*;
import Player.Player;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        ArrayList<Location> possibleLocations = new ArrayList<>();

        MoveBehavior Diagonal = new DiagonalMove();
        MoveBehavior Horizental = new HorizentalMove();
        MoveBehavior Vertical = new VerticalMove();

        possibleLocations.addAll(Diagonal.calculatePossibleLocations(this.getLocation(), board));
        possibleLocations.addAll(Horizental.calculatePossibleLocations(this.getLocation(), board));
        possibleLocations.addAll(Vertical.calculatePossibleLocations(this.getLocation(), board));

        return possibleLocations;
    }
}
