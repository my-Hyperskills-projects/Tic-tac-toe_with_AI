package tictactoe;

import javafx.util.Pair;

public class ArtificialIntellect extends ArtificialIdiot {

    public ArtificialIntellect(char token, Main desk) {
        super(token, desk);
        this.level = "\"medium\"";
    }

    @Override
    public void walk() {
        Pair<Integer, Integer> coordinates = this.searchOpportunity();
        if (coordinates.getKey() != -1) {
            System.out.println("Making move level " + level);
            desk.setToken(token, coordinates);
            desk.printDesk();
            desk.checkResult(token);
        } else {
            super.walk();
        }
    }

    public Pair<Integer, Integer> searchOpportunity() {

        char[][] ticTacToe = desk.getTicTacToe();
        Pair<Integer, Integer> toInterfere = null;
        int thisTokenCountD1 = 0;
        int thisTokenCountD2 = 0;
        int oppTokenCountD1 = 0;
        int oppTokenCountD2 = 0;

        for (int i = 0; i < 3; i++) {
            int thisTokenCountL = 0;
            int thisTokenCountC = 0;
            int oppTokenCountL = 0;
            int oppTokenCountC = 0;

            for (int j = 0; j < 3; j++) {
                if (ticTacToe[i][j] == token) {
                    thisTokenCountL++;
                } else if (ticTacToe[i][j] != '_') {
                    oppTokenCountL++;
                }
                if (ticTacToe[j][i] == token) {
                    thisTokenCountC++;
                } else if (ticTacToe[j][i] != '_') {
                    oppTokenCountC++;
                }
            }

            if (thisTokenCountL == 2 && oppTokenCountL == 0) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[i][j] == '_') {
                        return new Pair<>(i, j);
                    }
                }
            } else if (thisTokenCountL == 0 && oppTokenCountL == 2) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[i][j] == '_') {
                        toInterfere = new Pair<>(i, j);
                    }
                }
            }

            if (thisTokenCountC== 2 && oppTokenCountC == 0) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[j][i] == '_') {
                        return new Pair<>(j, i);
                    }
                }
            } else if (thisTokenCountC == 0 && oppTokenCountC == 2) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToe[j][i] == '_') {
                        toInterfere = new Pair<>(j, i);
                    }
                }
            }

            if (ticTacToe[i][i] == token) {
                thisTokenCountD1++;
            } else if (ticTacToe[i][i] != '_') {
                oppTokenCountD1++;
            }

            if (ticTacToe[i][2 - i] == token) {
                thisTokenCountD2++;
            } else if (ticTacToe[i][2 - i] != '_') {
                oppTokenCountD2++;
            }
        }

        if (thisTokenCountD1 == 2 && oppTokenCountD1 == 0) {
            for (int i = 0; i < 3; i++) {
                if (ticTacToe[i][i] == '_') {
                    return new Pair<>(i, i);
                }
            }
        } else if (thisTokenCountD1 == 0 && oppTokenCountD1 == 2) {
            for (int i = 0; i < 3; i++) {
                if (ticTacToe[i][i] == '_') {
                    toInterfere = new Pair<>(i, i);
                }
            }
        }

        if (thisTokenCountD2 == 2 && oppTokenCountD2 == 0) {
            for (int i = 0; i < 3; i++) {
                if (ticTacToe[i][2 - i] == '_') {
                    return new Pair<>(i, 2 - i);
                }
            }
        } else if (thisTokenCountD2 == 0 && oppTokenCountD2 == 2) {
            for (int i = 0; i < 3; i++) {
                if (ticTacToe[i][2 - i] == '_') {
                    toInterfere = new Pair<>(i, 2 - i);
                }
            }
        }

        return toInterfere == null ? new Pair<>(-1, -1) : toInterfere;
    }
}
