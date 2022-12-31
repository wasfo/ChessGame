package Player;
import Game.Color;
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
    public Move move(Location From, Location To) {
        return new Move(From, To);
    }

}
