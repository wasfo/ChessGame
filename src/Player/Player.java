package Player;

import Game.Color;
import Game.InputMoveHandler;
import Game.Location;
import Pieces.King;

public class Player {
    King playerKing;
    Color color;

    public Player(King playerKing, Color color) {
        this.playerKing = playerKing;
        this.color = color;
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


    public Move MakeMove() {
        String enteredMove = InputMoveHandler.EnterMove();
        if (InputMoveHandler.isEnteredMoveValid(enteredMove))
            return InputMoveHandler.ProcessMove(enteredMove);
        else
            return new Move(null, null);
    }

}
