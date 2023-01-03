package Player;
import Game.Color;
import Game.InputMoveHandler;
import Pieces.King;
public class Player {
    private King playerKing;
    private boolean isChecked = false;
    private Color color;

    public Player(King playerKing, Color color) {
        this.playerKing = playerKing;
        this.color = color;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
    public King getPlayerKing() {
        return playerKing;
    }

    public void setPlayerKing(King playerKing) {
        this.playerKing = playerKing;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Move makeMove() {
        String enteredMove = InputMoveHandler.enterMove();
        if (InputMoveHandler.isEnteredMoveValid(enteredMove))
            return InputMoveHandler.processMove(enteredMove);
        return new Move(null, null);
    }

    @Override
    public String toString() {
        return color + " PLAYER";
    }
}
