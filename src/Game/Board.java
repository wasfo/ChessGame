package Game;

import Pieces.*;

import java.util.*;

import static Movebehavior.Validators.*;

public class Board implements Cloneable {
    public Square[][] squares;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    private King whiteKing = new King(Color.WHITE);
    private King blackKing = new King(Color.BLACK);

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

    private Board(Square[][] squares, ArrayList<Piece> blackPieces, ArrayList<Piece> whitePieces, King whiteKing, King blackKing) {
        this.squares = squares;
        this.blackPieces = blackPieces;
        this.whitePieces = whitePieces;
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;
    }

    public Square[][] getSquares() {
        return squares;
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
        //    whiteKing = ;
        //  blackKing = new King(Color.BLACK);
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        createEmptyBoard();
    }

    public void updateBoard(Location fromLocation, Location toLocation) {
        Piece pieceOnLocation = squares[fromLocation.getX()][fromLocation.getY()].getPiece();
        if (pieceOnLocation != null && isLocationOnBoard(toLocation)) {
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

    public void emptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.squares[i][j].removePiece();
            }
        }
    }

    public void setupBoard() {
        setPawnsOnBoard();
        setBishopsOnBoard();
        setKnightsOnBoard();
        setQueensOnBoard();
        setRooksOnBoard();
        setKingsOnBoard();
    }

    public void setKingsOnBoard() {
        squares[0][4].setPiece(this.whiteKing);
        squares[7][4].setPiece(this.blackKing);
    }

    public void setPawnsOnBoard() {
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(Color.WHITE);
            squares[1][i].setPiece(pawn);
            whitePieces.add(pawn);
        }
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(Color.BLACK);
            squares[6][i].setPiece(pawn);
            blackPieces.add(pawn);
        }
    }

    public void setBishopsOnBoard() {
        Piece[] bishops = new Piece[2];
        bishops[0] = new Bishop(Color.WHITE);
        bishops[1] = new Bishop(Color.WHITE);
        squares[0][5].setPiece(bishops[0]);
        squares[0][2].setPiece(bishops[1]);
        whitePieces.addAll(List.of(bishops));
        bishops[0] = new Bishop(Color.BLACK);
        bishops[1] = new Bishop(Color.BLACK);
        squares[7][2].setPiece(bishops[0]);
        squares[7][5].setPiece(bishops[1]);
        blackPieces.addAll(List.of(bishops));
    }

    public void setKnightsOnBoard() {
        Piece[] knights = new Piece[2];
        knights[0] = new Knight(Color.WHITE);
        knights[1] = new Knight(Color.WHITE);
        squares[0][6].setPiece(knights[0]);
        squares[0][1].setPiece(knights[1]);
        whitePieces.addAll(List.of(knights));
        knights[0] = new Knight(Color.BLACK);
        knights[1] = new Knight(Color.BLACK);
        squares[7][1].setPiece(knights[0]);
        squares[7][6].setPiece(knights[1]);
        blackPieces.addAll(List.of(knights));
    }

    public void setRooksOnBoard() {
        Piece[] rooks = new Piece[2];
        rooks[0] = new Rook(Color.WHITE);
        rooks[1] = new Rook(Color.WHITE);
        squares[0][7].setPiece(rooks[0]);
        squares[0][0].setPiece(rooks[1]);
        whitePieces.addAll(List.of(rooks));
        rooks[0] = new Rook(Color.BLACK);
        rooks[1] = new Rook(Color.BLACK);
        squares[7][0].setPiece(rooks[0]);
        squares[7][7].setPiece(rooks[1]);
        blackPieces.addAll(List.of(rooks));
    }

    public void setQueensOnBoard() {
        Queen whiteQueen = new Queen(Color.WHITE);
        squares[0][3].setPiece(whiteQueen);
        whitePieces.add(whiteQueen);
        Queen blackQueen = new Queen(Color.BLACK);
        squares[7][3].setPiece(blackQueen);
        blackPieces.add(blackQueen);
    }

    public void displayBoard() {
        for (int j = 7; j >= 0; j--) {
            System.out.print("\n");
            System.out.println();
            for (int i = 0; i < 8; i++) {
                System.out.print(InputMoveHandler.map[i] + "" + (j + 1));
                System.out.printf(" %13s ", squares[j][i]);
            }
        }
        System.out.println();
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(squares, board.squares) && Objects.equals(blackPieces, board.blackPieces)
                && Objects.equals(whitePieces, board.whitePieces) && Objects.equals(whiteKing, board.whiteKing)
                && Objects.equals(blackKing, board.blackKing);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(blackPieces, whitePieces, whiteKing, blackKing);
        result = 31 * result + Arrays.deepHashCode(squares);
        return result;
    }
    private Square[][] deepCopy() {
        Square[][] newSquares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newSquares[i][j] = new Square(squares[i][j].getLocation(), squares[i][j].getColor(), squares[i][j].getPiece());
            }
        }
        return newSquares;
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.setSquares(deepCopy());
            clone.blackKing = new King(Color.BLACK);
            clone.blackKing.setLocation(new Location(this.blackKing.getLocation().getX(),this.blackKing.getLocation().getY()));
            clone.whiteKing = new King(Color.WHITE);
            clone.whiteKing.setLocation(new Location(this.whiteKing.getLocation().getX(),this.whiteKing.getLocation().getY()));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
