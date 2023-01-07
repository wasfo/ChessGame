package Game;
import Pieces.Piece;
import Player.*;
import java.util.ArrayList;
import java.util.List;

public class GameSetup {
    private static final Board chessBoard = new Board();
    private static final List<Move> movesHistory = new ArrayList<Move>();
    static BoardManager boardManager = new BoardManager(chessBoard);
    static Player whitePlayer;
    static Player blackPlayer;

    public GameSetup(Player whitePlayer, Player blackPlayer) {
        boardManager.setupBoard();
        GameSetup.whitePlayer = whitePlayer;
        GameSetup.whitePlayer.setCheckedmated(false);
        GameSetup.whitePlayer.setPlayerKing(chessBoard.getKing(Color.WHITE));
        GameSetup.blackPlayer = blackPlayer;
        GameSetup.blackPlayer.setCheckedmated(false);
        GameSetup.blackPlayer.setPlayerKing(chessBoard.getKing(Color.BLACK));
    }
    private static Move promotePlayerToMove(Player Player) {
        Move currentMove;
        System.out.print(Player + "enter move: ");
        currentMove = Player.makeMove();
        return currentMove;
    }
    public static void startGame() {
        boolean turn = true;
        chessBoard.displayBoard();

        while (gameIsNotFinished(whitePlayer, blackPlayer)) {
            Player currentPlayer = (turn ? whitePlayer : blackPlayer);
            Player oppositePlayer = (!turn ? whitePlayer : blackPlayer);
            Move currentMove = promotePlayerToMove(currentPlayer);
            testMove(currentPlayer, oppositePlayer, currentMove);
            turn = !turn;
        }
        gameResult();
        chessBoard.displayBoard();
    }
    private static boolean gameIsNotFinished(Player whitePlayer, Player blackPlayer) {
        return GameSetup.movesHistory.size() <= 49 && !whitePlayer.isCheckedmated() && !blackPlayer.isCheckedmated();
    }
    private static void testMove(Player currentPlayer, Player oppositePlayer, Move currentMove) {
        while (!boardManager.isLegalMove(currentMove, currentPlayer)) {
            currentMove = promotePlayerToMove(currentPlayer);
        }
        ApplyMove(currentMove);
        if (oppositePlayer.getPlayerKing().isCheckMated(GameSetup.chessBoard, oppositePlayer)) {
            oppositePlayer.setCheckedmated(true);
        }

        System.out.println(oppositePlayer.getColor() + " King is in check ? " +
                oppositePlayer.getPlayerKing().isInCheck(GameSetup.chessBoard));

        System.out.println(currentPlayer.getColor() + " King is in check? " +
                currentPlayer.getPlayerKing().isInCheck(GameSetup.chessBoard));

        System.out.println(oppositePlayer.getColor() + " King is checkmated? " +
                oppositePlayer.getPlayerKing().isCheckMated(GameSetup.chessBoard,oppositePlayer));

        System.out.println(currentPlayer.getColor() + " King is checkmated? " +
                currentPlayer.getPlayerKing().isCheckMated(GameSetup.chessBoard,currentPlayer));

    }
    private static void ApplyMove(Move currentMove) {
        GameSetup.chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
        movesHistory.add(currentMove);
        GameSetup.chessBoard.displayBoard();
    }
    private static void gameResult() {
        if (whitePlayer.isCheckedmated())
            System.out.println(GameResult.BlackWins);
        if (blackPlayer.isCheckedmated())
            System.out.println(GameResult.WhiteWins);
        else System.out.println(GameResult.Draw);
    }
}
