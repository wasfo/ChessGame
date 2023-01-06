package Pieces;
import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Movebehavior.KingMove;
import Movebehavior.MoveBehavior;
import Player.Player;
import java.util.ArrayList;
import static Movebehavior.Validators.isLocationOnBoard;
public class King extends Piece implements Cloneable {

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public ArrayList<Location> calculateLegalMoveLocations(Board board, Player player) {
        MoveBehavior kingMove = new KingMove();
        return kingMove.calculatePossibleLocations(this.getLocation(), board);
    }


    public boolean hasEscapeMoves(Board board, Player player){
        return calculateLegalMoveLocations(board, player).size() > 0;
    }


    public boolean isCheckMated(Board board, Player player){
        return isInCheck(board) && !hasEscapeMoves(board, player);
    }

    public boolean isInCheck(Board board) {
        return (isRowThreatened(board) || isColumnThreatened(board) || isDiagonalThreatened(board) ||
                isAttackedByPawn(board) || isAttackedByKnight(board));
    }

    public boolean isAttackedByPawn(Board board) {
        int[] directional_x = {this.getColor() == Color.BLACK ? +1 : -1, this.getColor() == Color.BLACK ? +1 : -1};
        int[] directional_y = {this.getColor() == Color.BLACK ? -1 : +1, this.getColor() == Color.BLACK ? +1 : -1};
        return isAttackedBy(board,directional_y,directional_x,PieceType.PAWN);
    }
    public boolean isAttackedBy(Board board, int[] directional_y, int[] directional_x, PieceType AttackByPiece){
        if(this.getLocation() == null)
            return false;
        Location kingLocation = this.getLocation();
        for (int i = 0; i < directional_x.length; i++) {
            int tempX = kingLocation.getX() + directional_x[i];
            int tempY = kingLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (isNextSquareHasEnemyPieceType(nextSquare, AttackByPiece)) return true;
            }
        }
        return false;
    }

    public boolean isAttackedByKnight(Board board) {
        final int[] directional_x = {2, 2, -2, -2, 1, 1, -1, -1};
        final int[] directional_y = {-1, 1, -1, 1, 2, -2, 2, -2};
        return isAttackedBy(board,directional_y,directional_x,PieceType.KNIGHT);
    }

    public boolean isPathAttackedByEnemyPiece(Board board, int[] directional_y, int[] directional_x, PieceType pieceType) {
        if(this.getLocation() == null)
            return false;
        Location kingLocation = this.getLocation();
        for (int i = 0; i < directional_x.length; i++) {
            int tempX = kingLocation.getX() + directional_x[i];
            int tempY = kingLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);
            while (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    tempX += directional_x[i];
                    tempY += directional_y[i];
                    tempLocation = new Location(tempX, tempY);
                    continue;
                }
                if (isNextSquareHasAllyPiece(nextSquare))
                    break;
                if (isNextSquareHasEnemyPieceType(nextSquare, pieceType)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public boolean isDiagonalThreatened(Board board) {
        int[] directional_x = {+1, -1, -1, +1};
        int[] directional_y = {+1, -1, +1, -1};
        return isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.BISHOP) ||
                isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN);
    }

    public boolean isRowThreatened(Board board) {
        int[] directional_x = {0, 0};
        int[] directional_y = {+1, -1};
        return isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.ROOK) ||
                isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN);
    }

    public boolean isColumnThreatened(Board board) {
        int[] directional_x = {+1, -1};
        int[] directional_y = {0, 0};

        return isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN) ||
                isPathAttackedByEnemyPiece(board, directional_y, directional_x, PieceType.ROOK);
    }

    public boolean isNextSquareHasEnemyPieceType(Square nextSquare, PieceType pieceType) {
        if (isLocationOnBoard(nextSquare.getLocation())) {
            if (nextSquare.getPiece() == null)
                return false;
            return ((nextSquare.getPiece().getType() == pieceType) && nextSquare.getPiece().getColor() != this.getColor());
        }
        return false;
    }

    public boolean isNextSquareHasAllyPiece(Square nextSquare) {
        if (isLocationOnBoard(nextSquare.getLocation())) {
            return nextSquare.getPiece().getColor() == this.getColor();
        }
        return false;
    }

    @Override
    public King clone() {
        return (King) super.clone();
    }
}
