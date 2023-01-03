package Game;
import Pieces.*;

import java.util.ArrayList;
import java.util.List;

import static Movebehavior.Validators.*;

public class Board implements Cloneable {
    private final Square[][] squares;
    private ArrayList<Piece>  blackPieces;
    private  ArrayList<Piece>  whitePieces;
    private final King whiteKing;
    private final King blackKing;

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(ArrayList<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(ArrayList<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public King getKing(Color color) {
        if (color == Color.BLACK)
            return this.blackKing;
        return this.whiteKing;
    }
    public Square getSpecificSquare(Location location) {
        return squares[location.getX()][location.getY()];
    }

    public void setPieceOnLocation(Piece piece, Location location) {
        getSpecificSquare(location).setPiece(piece);
    }

    public Board() {
        squares = new Square[8][8];
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);
        createEmptyBoard();
    }

    public void updateBoard(Location fromLocation, Location toLocation) {
        Piece pieceOnLocation = squares[fromLocation.getX()][fromLocation.getY()].getPiece();
        if (pieceOnLocation != null && isLocationOnBoard(toLocation) ) {
            squares[toLocation.getX()][toLocation.getY()].setPiece(pieceOnLocation);
            squares[fromLocation.getX()][fromLocation.getY()].removePiece();
        }
    }
    private void createEmptyBoard() {
        Color[] color = Color.values();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[j][i] = new Square(new Location(j, i), color[(j + i) % 2]);
            }
        }
    }


    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.squares[i][j].removePiece();
            }
        }
        setupBoard();
    }


    public void setupBoard() {
        setPawnsOnBoard();
        setBishopsOnBoard();
        setKnightsOnBoard();
        setQueensOnBoard();
        setRooksOnBoard();
        setKingsOnBoard();
    }
    public void setKingsOnBoard(){
        squares[0][4].setPiece(this.whiteKing);
        squares[7][4].setPiece(this.blackKing);
    }

    public void setPawnsOnBoard(){
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(Color.WHITE);
            squares[1][i].setPiece(pawn);
            whitePieces.add(pawn);
        }
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(Color.BLACK);
            squares[6][i].setPiece(pawn);
            whitePieces.add(pawn);
        }
    }
    public void setBishopsOnBoard(){
        Piece [] bishops = new Piece[2];
        bishops[0]  = new Knight(Color.WHITE);
        bishops[1] = new Knight(Color.WHITE);
        squares[0][5].setPiece(bishops[0]);
        squares[0][2].setPiece(bishops[1]);
        whitePieces.addAll(List.of(bishops));
        bishops[0]  = new Knight(Color.BLACK);
        bishops[1] = new Knight(Color.BLACK);
        squares[7][2].setPiece(bishops[0]);
        squares[7][5].setPiece(bishops[1]);
        blackPieces.addAll(List.of(bishops));
    }

    public void setKnightsOnBoard(){
        Piece [] knights = new Piece[2];
        knights[0]  = new Knight(Color.WHITE);
        knights[1] = new Knight(Color.WHITE);
        squares[0][6].setPiece(knights[0]);
        squares[0][1].setPiece(knights[1]);
        whitePieces.addAll(List.of(knights));
        knights[0]  = new Knight(Color.BLACK);
        knights[1] = new Knight(Color.BLACK);
        squares[7][1].setPiece(knights[0]);
        squares[7][6].setPiece(knights[1]);
        blackPieces.addAll(List.of(knights));
    }
    public void setRooksOnBoard(){
        Piece [] rooks = new Piece[2];
        rooks[0]  = new Knight(Color.WHITE);
        rooks[1] = new Knight(Color.WHITE);
        squares[0][7].setPiece(rooks[0]);
        squares[0][0].setPiece(rooks[1]);
        whitePieces.addAll(List.of(rooks));
        rooks[0]  = new Knight(Color.BLACK);
        rooks[1] = new Knight(Color.BLACK);
        squares[7][0].setPiece(rooks[0]);
        squares[7][7].setPiece(rooks[1]);
        blackPieces.addAll(List.of(rooks));
    }

    public void setQueensOnBoard(){
        Queen whiteQueen  = new Queen(Color.WHITE);
        squares[0][3].setPiece(whiteQueen);
        whitePieces.add(whiteQueen);
        Queen blackQueen  = new Queen(Color.BLACK);
        squares[7][3].setPiece(blackQueen);
        blackPieces.add(blackQueen);
    }



    public void displayBoard() {
        for (int j = 7; j >= 0; j--) {
            System.out.print("\n");
            System.out.println();
            for (int i = 0; i < 8; i++) {
                System.out.print(squares[j][i].indices());
                System.out.printf(" %12s ", squares[j][i]);

            }
        }
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
