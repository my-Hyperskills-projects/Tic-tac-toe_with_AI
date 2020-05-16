package tictactoe;

import javafx.util.Pair;
import java.util.Scanner;
import java.util.Set;

public class Player extends Opponent {
    Scanner scanner = new Scanner(System.in);

    public Player(char playersToken, Main desk) {
        super(playersToken, desk);
    }

    @Override
    public void walk() {
        Set<Pair<Integer, Integer>> emptyCoordinates = desk.getEmptyCells();

        while (true) {
            System.out.println("Enter the coordinates:");
            String[] coordinates = scanner.nextLine().split(" ");

            try {
                int x = Integer.parseInt(coordinates[0]) - 1;
                int y = 3 - Integer.parseInt(coordinates[1]);
                Pair<Integer, Integer> playersCoordinates = new Pair<>(y, x);

                if (x < 0 || x > 2 || y < 0 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (emptyCoordinates.contains(playersCoordinates)) {
                        desk.setToken(token, playersCoordinates);
                        desk.printDesk();
                        desk.checkResult(token);
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

}
