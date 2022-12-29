package Game;

import Pieces.*;


public class Board implements Cloneable{
    private Square[][] squares;

    private King whiteKing;
    private King blackKing;

    public King getKing(Color color) {
        if(color == Color.BLACK)
            return this.blackKing;
        else
            return this.whiteKing;

    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSpecifiedSquare(Location location) {
        return squares[location.getY()][location.getX()];
    }

    public void setPieceOnLocation(Piece piece , Location location) {

        getSpecifiedSquare(location).setPiece(piece);
    }

    public Board() {
        squares = new Square[8][8];
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);

        createEmptyBoard();
    }

    public void UpdateBoard(Location fromLocation, Location toLocation) {
        Piece pieceOnLocation = squares[fromLocation.getY()][fromLocation.getX()].getPiece();

        if (pieceOnLocation != null) {
            Square destSquare = squares[toLocation.getY()][toLocation.getX()];
            destSquare.setPiece(pieceOnLocation);
            squares[fromLocation.getY()][fromLocation.getX()].RemovePiece();
        }

    }
    private void createEmptyBoard() {
        Color color[] = Color.values();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(new Location(j, i), color[(j + i) % 2]);
            }
        }
    }


    public void ResetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.squares[i][j].RemovePiece();
            }
        }

        SetupBoard();
    }


    public void SetupBoard() {
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new Pawn(Color.WHITE));
        }
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new Pawn(Color.BLACK));
        }

        squares[0][6].setPiece(new Knight(Color.WHITE));
        squares[0][1].setPiece(new Knight(Color.WHITE));

        squares[7][1].setPiece(new Knight(Color.BLACK));
        squares[7][6].setPiece(new Knight(Color.BLACK));


        squares[0][7].setPiece(new Rook(Color.WHITE));
        squares[0][0].setPiece(new Rook(Color.WHITE));


        squares[7][0].setPiece(new Rook(Color.BLACK));
        squares[7][7].setPiece(new Rook(Color.BLACK));

        squares[0][5].setPiece(new Bishop(Color.WHITE));
        squares[0][2].setPiece(new Bishop(Color.WHITE));

        squares[7][2].setPiece(new Bishop(Color.BLACK));
        squares[7][5].setPiece(new Bishop(Color.BLACK));


        squares[0][4].setPiece(this.whiteKing);
        squares[0][3].setPiece(new Queen(Color.WHITE));


        squares[7][4].setPiece(this.blackKing);
        squares[7][3].setPiece(new Queen(Color.BLACK));

    }


    public void DisplayCurrentPosition() {
        for (int j = 7; j >= 0; j--) {
            System.out.print("\n");
            System.out.println("---------------------------------" +
                    "-----------------------------------------------------" +
                    "--------------------------------------------");
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
