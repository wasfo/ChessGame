package Game;

import Player.*;

import java.util.HashMap;
import java.util.Scanner;

public class InputMoveHandler {
    private static final HashMap<Character, Integer> chars_to_Int = MapCharsToInt();

    public static HashMap<Character, Integer> MapCharsToInt() {
        HashMap<Character, Integer> Chars_to_Int = new HashMap<>();
        Chars_to_Int.put('a', 1);
        Chars_to_Int.put('b', 2);
        Chars_to_Int.put('c', 3);
        Chars_to_Int.put('d', 4);
        Chars_to_Int.put('e', 5);
        Chars_to_Int.put('f', 6);
        Chars_to_Int.put('g', 7);
        Chars_to_Int.put('h', 8);
        return Chars_to_Int;
    }


    public static String EnterMove() {
        Scanner scanner = new Scanner(System.in);
        String enteredMove = scanner.nextLine();
        return enteredMove;
            //
    }
    public static boolean isEnteredMoveValid(String enteredMove){
        String validInputRegex = "\\bmove\\s[a-h][1-8]\\s[a-h][1-8]";
        return enteredMove.matches(validInputRegex);
    }

    public static Move ProcessMove(String enteredMove) {

        String[] moveArguments = enteredMove.replaceAll("\\s+$", "").split(" ");

        int x_coordinate = chars_to_Int.get(moveArguments[1].charAt(0));
        int y_coordinate = moveArguments[1].charAt(1);

        Location startLocation = new Location(x_coordinate, y_coordinate);

        x_coordinate = chars_to_Int.get(moveArguments[2].charAt(0));
        y_coordinate = moveArguments[2].charAt(1);

        Location endLocation = new Location(x_coordinate, y_coordinate);
        return new Move(startLocation, endLocation);
    }


}
