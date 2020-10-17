package ca.cmpt276.as3.model;

import android.widget.Button;

public class Board {
    private int NUM_ROWS;
    private int NUM_COLS;
    Button buttons[][];
    private static Board board = null;
    public Board(int cols, int rows){
        NUM_COLS = cols;
        NUM_ROWS = rows;
        buttons =  new Button[NUM_ROWS][NUM_COLS];
    }


}
