package ca.cmpt276.as3.model;

public class Options {
    private static Options instance = null;
    private int rows;
    private int cols;
    private int mines;
    private int timesPlayed;

    // TODO: reset #times played, best score
    private Options(int cols, int rows, int mines, int timesPlayed){
        this.cols = cols;
        this.rows = rows;
        this.mines = mines;
        this.timesPlayed = timesPlayed;
    }

    public static Options getInstance(){
        if(instance == null){
            int savedRows = 6;
            int savedCols = 10;
            int savedMines = 5;
            int savedTimesPlayed = 1;
            instance = new Options(savedCols, savedRows, savedMines, savedTimesPlayed);
        }
        return instance;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }
}
