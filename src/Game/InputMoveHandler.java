package Game;

import Player.*;

import java.util.HashMap;
import java.util.Scanner;

public class InputMoveHandler {
    private static final HashMap<Character, Integer> chars_to_Int = mapCharsToInt();

    public static HashMap<Character, Integer> mapCharsToInt() {
        HashMap<Character, Integer> Chars_to_Int = new HashMap<>();
        Chars_to_Int.put('a', 0);
        Chars_to_Int.put('b', 1);
        Chars_to_Int.put('c', 2);
        Chars_to_Int.put('d', 3);
        Chars_to_Int.put('e', 4);
        Chars_to_Int.put('f', 5);
        Chars_to_Int.put('g', 6);
        Chars_to_Int.put('h', 7);
        return Chars_to_Int;
    }


    public static String enterMove() {
        Scanner scanner = new Scanner(System.in);
        String enteredMove = scanner.nextLine();
        return enteredMove;
            //
    }
    public static boolean isEnteredMoveValid(String enteredMove){
        //
        String validInputRegex = "\\bmove\\s[a-h][1-8]\\s[a-h][1-8]";
        return enteredMove.matches(validInputRegex);
    }

    public static Move processMove(String enteredMove) {

        String[] moveArguments = enteredMove.replaceAll("\\s+$", "").split(" ");
        int x_coordinate = chars_to_Int.get(moveArguments[1].charAt(0));
        int y_coordinate =  Integer.parseInt(String.valueOf(moveArguments[1].charAt(1))) ;
        System.out.println(y_coordinate);
        Location startLocation = new Location(x_coordinate , y_coordinate - 1);
        x_coordinate = chars_to_Int.get(moveArguments[2].charAt(0));
        y_coordinate = Integer.parseInt(String.valueOf(moveArguments[2].charAt(1))) ;
        Location endLocation = new Location(x_coordinate , y_coordinate - 1);
        System.out.println(startLocation + " " + endLocation);
        return new Move(startLocation, endLocation);
    }


}
