package Movebehavior;
import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.PieceType;
import java.util.ArrayList;
import static Movebehavior.Validators.isLocationOnBoard;

public class DiagonalMove implements MoveBehavior {
    private final int[] directional_x = {+1, -1, -1, +1};
    private final int[] directional_y = {+1, -1, +1, -1};

    @Override
    public ArrayList<Location> CalculateLocations(Location location, Board board) {
        return GeneralMove.DirectMove(location, board, this.directional_x, this.directional_y);
    }
}
