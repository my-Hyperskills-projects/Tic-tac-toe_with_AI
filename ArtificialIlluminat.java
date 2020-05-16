package tictactoe;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArtificialIlluminat extends Opponent {

    char oppToken;
    String level;

    ArtificialIlluminat(char token, Main desk) {
        super(token, desk);
        this.level = "\"hard\"";
        if (token == 'X') {
            oppToken = 'O';
        } else {
            oppToken = 'X';
        }
    }

    @Override
    void walk() {
        Pair <Integer, Integer> coordinates = minimax(token).getValue();
        System.out.println("Making move level " + level);
        desk.setToken(token, coordinates);
        desk.printDesk();
        desk.checkResult(token);
    }

    Pair<Integer, Pair<Integer, Integer>> minimax(char token) {
        Set<Pair<Integer, Integer>> availableCells = desk.getEmptyCells();


        if (winning(this.token, desk.getTicTacToe())) {
            return new Pair<>(10, new Pair<>(0, 0));
        } else if (winning(oppToken, desk.getTicTacToe())) {
            return new Pair<>(-10, new Pair<>(0, 0));
        } else if (availableCells.size() == 0) {
            return new Pair<>(0, new Pair<>(0, 0));
        }

        HashMap<Integer, Pair<Integer, Integer>> moves = new HashMap<>();

        for (Pair<Integer, Integer> coordinates : availableCells) {
            desk.setToken(token, coordinates);
            char otherToken = token == 'X' ? 'O' : 'X';
            int score = minimax(otherToken).getKey();
            moves.put(score, coordinates);
            desk.setToken('_', coordinates);
        }

        int bestScore;
        if (token == this.token) {
            bestScore = -10000;
            for (var move : moves.entrySet()) {
                if (move.getKey() > bestScore) {
                    bestScore = move.getKey();
                }
            }
        } else {
            bestScore = 10000;
            for (var move : moves.entrySet()) {
                if (move.getKey() < bestScore) {
                    bestScore = move.getKey();
                }
            }
        }

        return new Pair<>(bestScore, moves.get(bestScore));
    }


    boolean winning(char token, char[][] ticTacToe) {
        HashSet<Character> d1 = new HashSet<>();
        HashSet<Character> d2 = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> line = new HashSet<>();

            for (int j = 0; j < 3; j++) {
                column.add(ticTacToe[j][i]);
                line.add(ticTacToe[i][j]);
            }


            if (column.equals(Set.of(token)) || line.equals(Set.of(token))) {
                return true;
            }


            d1.add(ticTacToe[i][i]);
            d2.add(ticTacToe[i][2 - i]);
        }

        if (d1.equals(Set.of(token)) || d2.equals(Set.of(token))) {
            return true;
        }

        return false;
    }
}
