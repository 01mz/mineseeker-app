package ca.cmpt276.as3.model;


public class Options {
    private static Options instance = null;
    private int rows;
    private int cols;
    private int mines;
    private int timesPlayed;

    // TODO: reset #times played, best score
    private Options(int rows, int cols, int mines, int timesPlayed){
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.timesPlayed = timesPlayed;
    }

    public static Options getInstance(){
        if(instance == null){
            // default options
            instance = new Options(4, 6, 6, 1);
        }
        return instance;
    }

    public void init(int rows, int cols, int mines, int timesPlayed){
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.timesPlayed = timesPlayed;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMines() {
        return mines;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void updateTimesPlayed() {
        timesPlayed++;
    }

    public void resetTimesPlayed() {
        timesPlayed = 0;
    }
}
