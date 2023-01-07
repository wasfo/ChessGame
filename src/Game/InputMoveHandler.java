package Game;
import Player.*;
import java.util.HashMap;
import java.util.Scanner;
public class InputMoveHandler {
    private static final HashMap < Character, Integer > chars_to_Int = mapCharsToInt();
    public static char[] map = {'a','b','c','d','e','f','g','h'};
    private static String validInputRegex = "\\bmove\\s[a-h][1-8]\\s[a-h][1-8]";

    public static HashMap<Character, Integer> mapCharsToInt() {
        HashMap<Character, Integer> chars_to_int = new HashMap<>();
        chars_to_int.put('a', 0);
        chars_to_int.put('b', 1);
        chars_to_int.put('c', 2);
        chars_to_int.put('d', 3);
        chars_to_int.put('e', 4);
        chars_to_int.put('f', 5);
        chars_to_int.put('g', 6);
        chars_to_int.put('h', 7);
        return chars_to_int;
    }
    public static String enterMove() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
    public static boolean isEnteredMoveValid(String enteredMove){
        return enteredMove.matches(validInputRegex);
    }
    public static Move processMove(String enteredMove) {
        String[] moveArguments = enteredMove.replaceAll("\\s+$", "").split(" ");
        int x_coordinate = chars_to_Int.get(moveArguments[1].charAt(0));
        int y_coordinate =  Integer.parseInt(String.valueOf(moveArguments[1].charAt(1))) ;
        System.out.println(y_coordinate);
        Location startLocation = new Location( y_coordinate - 1, x_coordinate);
        x_coordinate = chars_to_Int.get(moveArguments[2].charAt(0));
        y_coordinate = Integer.parseInt(String.valueOf(moveArguments[2].charAt(1))) ;
        Location endLocation = new Location( y_coordinate - 1, x_coordinate);
        System.out.println(startLocation + " " + endLocation);
        return new Move(startLocation, endLocation);
    }


}
