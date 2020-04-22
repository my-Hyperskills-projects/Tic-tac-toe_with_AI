package tictactoe;

import javafx.util.Pair;

import java.util.Random;
import java.util.Set;

class ArtificialIdiot extends Opponent {

    String level;

    public ArtificialIdiot(char token, Main desk) {
        super(token, desk);
        this.level = "\"easy\"";
    }

    @Override
    public void walk() {
        Random random = new Random();
        Set<Pair<Integer, Integer>> emptyCells = desk.getEmptyCells();
        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            Pair<Integer, Integer> opponentsCoordinates = new Pair<>(y, x);
            if (emptyCells.contains(opponentsCoordinates)) {
                System.out.println("Making move level " + level);
                desk.setToken(token, opponentsCoordinates);
                desk.printDesk();
                desk.checkResult(token);
                break;
            }
        }
    }
}

