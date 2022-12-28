package Pieces;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Player.Player;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class King extends Piece {


    public King(Color color) {
        super(PieceType.KING, color);

    }


    public boolean isInCheck(Board board) {

        return (!isRowThreatened(board) && !isColumnThreatened(board) && !isDiagonalThreatened(board) &&
                !isAttackedByPawn(board));

    }


    public boolean isAttackedByPawn(Board board) {
        Location kingLocation = this.getLocation();
        int x = kingLocation.getX();
        int y = kingLocation.getY();
        Square upRightSquare = board.getSpecifiedSquare(new Location(x + 1, y + 1));
        Square upLeftSquare = board.getSpecifiedSquare(new Location(x - 1, y + 1));
        return isNextSquareHasEnemyPieceType(upRightSquare, PieceType.PAWN) ||
                isNextSquareHasEnemyPieceType(upLeftSquare, PieceType.PAWN);
    }


    public boolean isPathClearFromEnemyPiece(Board board, int directional_y[], int directional_x[], PieceType pieceType) {
        Location kingLocation = this.getLocation();
        int x = kingLocation.getX();
        int y = kingLocation.getY();

        Square nextSquare;
        for (int i = 0; i < directional_x.length; i++) {
            int tempX = x + directional_x[i];
            int tempY = y + directional_y[i];
            Location tempLocation = new Location(tempX, tempY);

            while (isLocationOnBoard(tempLocation)) {
                nextSquare = board.getSpecifiedSquare(tempLocation);
                if (isNextSquareEmpty(nextSquare))
                    continue;
                if (isNextSquareHasAllyPiece(nextSquare))
                    break;
                if (isNextSquareHasEnemyPieceType(nextSquare, pieceType))
                    return false;

                tempX += directional_x[i];
                tempY += directional_y[i];
                tempLocation = new Location(tempX, tempY);

            }

        }
        return true;
    }

    public boolean isDiagonalThreatened(Board board) {
         int directional_x[] = {+1, -1, -1, +1};
         int directional_y[] = {+1, -1, +1, -1};
        return isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.BISHOP);
    }

    public boolean isColumnThreatened(Board board) {
        int directional_x[] = {0, 0};
        int directional_y[] = {+1, -1};

        return isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN) &&
                isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.ROOK);

    }
    public boolean isRowThreatened(Board board) {

        int directional_x[] = {+1, -1};
        int directional_y[] = {0, 0};

        return isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.QUEEN) &&
                isPathClearFromEnemyPiece(board, directional_y, directional_x, PieceType.ROOK);

    }

    public boolean isNextSquareHasEnemyPieceType(Square nextSquare, PieceType pieceType) {
        if (nextSquare.getPiece() == null)
            return false;

        else return (nextSquare.getPiece() != null &&
                (nextSquare.getPiece().getType() == pieceType)
                && nextSquare.getPiece().getColor() != this.getColor());
    }

    public boolean isNextSquareHasAllyPiece(Square nextSquare) {
        return nextSquare.getPiece().getColor() == this.getColor();
    }

    public boolean isNextSquareEmpty(Square nextSquare) {
        return (nextSquare.getPiece() == null);
    }


    @Override
    public ArrayList<Location> CalculateLegalMoveLocations(Board board, Player player) {
        return null;
    }


}
