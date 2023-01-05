package Movebehavior;

import Game.Board;
import Game.Color;
import Game.Location;
import Game.Square;
import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;

import static Movebehavior.Validators.isLocationOnBoard;

public class KingMove implements MoveBehavior {
    private final int[] directional_x = {1, 1, -1, -1, 0, 0, 1, -1};
    private final int[] directional_y = {-1, 1, -1, 1, 1, -1, 0, 0};

    @Override
    public ArrayList<Location> calculatePossibleLocations(Location kingOriginalLocation, Board board) {
        ArrayList<Location> possibleLocations = new ArrayList<>();
        /**
         * to calculate legal locations for the king, I have to check on many things :
         * 1) next location is on board
         * 2) next location has enemy piece but this piece isn't protected by another enemy piece
         * 3) next location doesn't have AllyPiece
         * 4) next location isn't threatened by enemy piece
         * change king location temporarily to check if that location is threatened or not
         */
        Square currentSquare = board.getSpecificSquare(kingOriginalLocation);
        Color kingColor = currentSquare.getPiece().getColor();
        King currentKing = board.getKing(kingColor).clone();
        for (int i = 0; i < 8; i++) {
            Location destinationLocation = new Location(kingOriginalLocation.getX() + this.directional_x[i], kingOriginalLocation.getY() + this.directional_y[i]);
            if (isLocationOnBoard(destinationLocation)) {
                Square nextSquare = board.getSpecificSquare(destinationLocation);
                if (nextSquare.getPiece() == null) {
                    board.updateBoard(currentKing.getLocation(), destinationLocation);
                    if (!board.getKing(kingColor).isInCheck(board)) {
                        possibleLocations.add(destinationLocation);
                    }
                    board.updateBoard(destinationLocation, kingOriginalLocation);
                }
                else if (nextSquare.getPiece().getColor() != currentKing.getColor()) {
                    replacePieceWithKing(kingOriginalLocation, board, possibleLocations, kingColor, destinationLocation);
                }
            }
        }
        return possibleLocations;
    }
    private static void replacePieceWithKing(Location kingOriginalLocation, Board board, ArrayList<Location> possibleLocations, Color kingColor, Location destinationLocation) {
        Piece removedPiece = board.getSpecificSquare(destinationLocation).getPiece();
        board.getSpecificSquare(destinationLocation).removePiece();
        if (!board.getKing(kingColor).isInCheck(board)) {
            possibleLocations.add(destinationLocation);
        }
        board.updateBoard(destinationLocation, kingOriginalLocation);
        board.setPieceOnLocation(removedPiece, destinationLocation);

    }









}
