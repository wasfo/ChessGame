package Player;
import Game.Color;
import Game.InputMoveHandler;
import Pieces.King;
public class Player {
    private King playerKing;
    private boolean isCheckedmated = false;
    private Color color;

    public Player(King playerKing, Color color) {
        this.playerKing = playerKing;
        this.color = color;
    }

    public Player(Color color) {
        this.color = color;
    }

    public boolean isCheckedmated() {
        return this.isCheckedmated;
    }

    public void setCheckedmated(boolean checked) {
        this.isCheckedmated = checked;
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
        return "player" + "(" + this.color.toString().toLowerCase() + ") ";
    }
}
