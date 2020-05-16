package tictactoe;

public abstract class Opponent {
    char token;
    Main desk;

    Opponent(char token, Main desk) {
        this.token = token;
        this.desk = desk;
    }

    abstract void walk();
}
