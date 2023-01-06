package Game;

import Player.*;

public class GameSetup {
    public static void SetupGame(Board chessBoard, BoardManager boardManager) {
        chessBoard.setupBoard();
        Player whitePlayer = new Player(chessBoard.getKing(Color.WHITE), Color.WHITE);
        Player blackPlayer = new Player(chessBoard.getKing(Color.BLACK), Color.BLACK);
        chessBoard.displayBoard();
        for (int i = 0; i < 50; i++) {
            Player currentPlayer = (i % 2 == 0 ? whitePlayer : blackPlayer);
            Player oppositePlayer = (i % 2 == 0 ?   blackPlayer: whitePlayer);
            System.out.print(currentPlayer + "enter move: ");
            Move currentMove = currentPlayer.makeMove();
            if (i == 49 || whitePlayer.isCheckedmated() || blackPlayer.isCheckedmated())
                break;
            playMove(chessBoard, boardManager, currentPlayer, oppositePlayer ,currentMove);
        }
        gameResult(whitePlayer, blackPlayer);
        chessBoard.displayBoard();
    }
    private static void playMove(Board chessBoard, BoardManager boardManager, Player currentPlayer,
                                 Player oppositePlayer, Move currentMove) {

        System.out.println(oppositePlayer.getColor() + " King is in check?  " +
                oppositePlayer.getPlayerKing().isInCheck(chessBoard));

        while (!boardManager.isLegalMove(currentMove, currentPlayer)) {


            if (oppositePlayer.getPlayerKing().isCheckMated(chessBoard, currentPlayer)) {
                currentPlayer.setCheckedmated(true);
                break;
            }
            System.out.print(currentPlayer + "enter move: ");
            currentMove = currentPlayer.makeMove();
        }
        ApplyMove(chessBoard, currentMove);
    }


    private static void ApplyMove(Board chessBoard, Move currentMove) {
        chessBoard.updateBoard(currentMove.getStartLocation(), currentMove.getEndLocation());
        chessBoard.displayBoard();
    }
    private static void gameResult(Player whitePlayer, Player blackPlayer) {
        if (whitePlayer.isCheckedmated())
            System.out.println(GameResult.BlackWins);
        else if (blackPlayer.isCheckedmated())
            System.out.println(GameResult.WhiteWins);
        else System.out.println(GameResult.Draw);
    }
}
