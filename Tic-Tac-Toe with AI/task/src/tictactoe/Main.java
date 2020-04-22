package tictactoe;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    char[][] ticTacToe = new char[3][3];
    static boolean isContinue;
    static boolean isExit;

    public Main() {
        for (char[] array : ticTacToe) {
            Arrays.fill(array, '_');
        }
        isContinue = true;
        isExit = false;
    }

    public static void main(String[] args) {


        while (!isExit) {
            Main desk = new Main();
            Opponent player1;
            Opponent player2;

            System.out.println("Input command:");
            String[] command = scanner.nextLine().split(" ");

            if (command.length == 0 || !command[0].equals("start") && !command[0].equals("exit")) {
                System.out.println("Bad parameters!");
            } else {
                if (command[0].equals("start")) {
                    if (command.length != 3) {
                        System.out.println("Bad parameters!");
                    } else {
                        switch (command[1]) {
                            case "easy":
                                player1 = new ArtificialIdiot('X', desk);
                                break;
                            case "medium":
                                player1 = new ArtificialIntellect('X', desk);
                                break;
                            case "hard":
                                player1 = new ArtificialIlluminat('X', desk);
                                break;
                            case "user":
                                player1 = new Player('X', desk);
                                break;
                            default:
                                System.out.println("Bad parameters!");
                                continue;
                        }
                        switch (command[2]) {
                            case "easy":
                                player2 = new ArtificialIdiot('O', desk);
                                break;
                            case "medium":
                                player2 = new ArtificialIntellect('O', desk);
                                break;
                            case "hard":
                                player2 = new ArtificialIlluminat('O', desk);
                                break;
                            case "user":
                                player2 = new Player('O', desk);
                                break;
                            default:
                                System.out.println("Bad parameters!");
                                continue;
                        }
                        desk.play(player1, player2);
                    }
                } else {
                    isExit = true;
                }
            }
        }
    }

    public void play(Opponent player1, Opponent player2) {
        this.printDesk();
        while (isContinue) {
            player1.walk();
            if (isContinue) {
                player2.walk();
            }
        }
    }

    public void printDesk() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTacToe[i][j] == '_' ? "  " : ticTacToe[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public Set<Pair<Integer, Integer>> getEmptyCells() {
        HashSet<Pair<Integer, Integer>> setOfEmptyCoordinates = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToe[i][j] == '_') {
                    setOfEmptyCoordinates.add(new Pair<>(i, j));
                }
            }
        }

        return setOfEmptyCoordinates;
    }

    public boolean hasEmpty() {
        Set<Pair<Integer, Integer>> emptyCells = getEmptyCells();
        if (emptyCells.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setToken(char token, Pair<Integer, Integer> coordinates) {
        int y = coordinates.getKey();
        int x = coordinates.getValue();

        ticTacToe[y][x] = token;
    }

    public void checkResult(char token) {
        HashSet<Character> d1 = new HashSet<>();
        HashSet<Character> d2 = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> line = new HashSet<>();

            for (int j = 0; j < 3; j++) {
                column.add(ticTacToe[j][i]);
                line.add(ticTacToe[i][j]);
            }

            if ((column.size() == 1 && !column.contains('_')) || (line.size() == 1 && !line.contains('_'))) {
                if (column.equals(Set.of(token)) || line.equals(Set.of(token))) {
                    System.out.println(token + " wins");
                    isContinue = false;
                    return;
                }
            }

            d1.add(ticTacToe[i][i]);
            d2.add(ticTacToe[i][2 - i]);
        }

        if ((d1.size() == 1 && !d1.contains('_')) || (d2.size() == 1 && !d2.contains('_'))) {
            if (d1.equals(Set.of(token)) || d2.equals(Set.of(token))) {
                System.out.println(token + " wins");
                isContinue = false;
                return;
            }
        }

        if (!hasEmpty()) {
            System.out.println("Draw");
            isContinue = false;
        }
    }

    public char[][] getTicTacToe() {
        return this.ticTacToe;
    }
}
