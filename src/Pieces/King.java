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
    public ArrayList<Location> CalculateLegalMoveLocations(Board board, Player player) {
        MoveBehavior kingMove = new KingMove();

        return kingMove.CalculateLocations(this.getLocation(), board);
    }

    public boolean isInCheck(Board board) {

//        System.out.println("isRowThreatened: " + isRowThreatened(board));
//        System.out.println("isColumnThreatened: " + isColumnThreatened(board));
//        System.out.println("isDiagonalThreatened: " + isDiagonalThreatened(board));
//        System.out.println("isAttackedByPawn: " + isAttackedByPawn(board));
//        System.out.println("isAttackedByKnight: " + isAttackedByKnight(board));
        return (isRowThreatened(board) || isColumnThreatened(board) || isDiagonalThreatened(board) ||
                isAttackedByPawn(board) || isAttackedByKnight(board));

    }

    public boolean isAttackedByPawn(Board board) {
        Location kingLocation = this.getLocation();
        int[] directional_x = {this.getColor() == Color.BLACK ? +1 : -1, this.getColor() == Color.BLACK ? +1 : -1};
        int[] directional_y = {this.getColor() == Color.BLACK ? -1 : +1, this.getColor() == Color.BLACK ? +1 : -1};

        for (int i = 0; i < 2; i++) {
            int tempX = kingLocation.getX() + directional_x[i];
            int tempY = kingLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);

            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (isNextSquareHasEnemyPieceType(nextSquare, PieceType.PAWN)) return true;
            }
        }
        return false;
    }

    public boolean isAttackedByKnight(Board board) {
        Location kingLocation = this.getLocation();
        final int[] directional_x = {2, 2, -2, -2, 1, 1, -1, -1};
        final int[] directional_y = {-1, 1, -1, 1, 2, -2, 2, -2};

        for (int i = 0; i < 8; i++) {
            int tempX = kingLocation.getX() + directional_x[i];
            int tempY = kingLocation.getY() + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);

            if (isLocationOnBoard(tempLocation)) {
                Square nextSquare = board.getSpecificSquare(tempLocation);
                if (nextSquare.isEmpty()) {
                    continue;
                }
                if (isNextSquareHasEnemyPieceType(nextSquare, PieceType.KNIGHT)) return true;
            }
        }
        return false;
    }

    public boolean isPathClearFromEnemyPiece(Board board, int[] directional_y, int[] directional_x, PieceType pieceType) {
        Location kingLocation = this.getLocation();
        int x = kingLocation.getX();
        int y = kingLocation.getY();

        for (int i = 0; i < directional_x.length; i++) {
            int tempX = x + directional_x[i];
            int tempY = y + directional_y[i];
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
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public boolean isDiagonalThreatened(Board board) {
        int[] directional_x = {+1, -1, -1, +1};
        int[] directional_y = {+1, -1, +1, -1};
        return !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.BISHOP) ||
                !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN);
    }

    public boolean isColumnThreatened(Board board) {
        int[] directional_x = {0, 0};
        int[] directional_y = {+1, -1};

        return !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.ROOK) ||
                !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN);
    }

    public boolean isRowThreatened(Board board) {
        int[] directional_x = {+1, -1};
        int[] directional_y = {0, 0};

        return !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN) ||
                !isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.ROOK);
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
        try {
            return (King) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
