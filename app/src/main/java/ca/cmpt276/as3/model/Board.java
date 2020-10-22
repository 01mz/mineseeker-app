package ca.cmpt276.as3.model;

import android.util.Pair;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * The Board class contains the game logic. It creates and manages the game board. It performs scans
 * and keeps track of scanned locations, mines, and the mines revealed.
 */
public class Board {
    private static final int MINE_DETECTED = -1;
    private final int NUM_ROWS;
    private final int NUM_COLS;
    private final int NUM_MINES;
    private final Set<Pair<Integer, Integer>> numMinesRevealedLocations = new LinkedHashSet<>();

    private final Mine[][] board;
    private int numMinesFound = 0;

    private enum Mine {
        NO, UNDETECTED, DETECTED

    }
    public Board(int cols, int rows, int mines){
        NUM_COLS = cols;
        NUM_ROWS = rows;
        NUM_MINES = mines;
        board = new Mine[NUM_ROWS][NUM_COLS];
        populateMines();

    }

    private void populateMines() {
        // Set board to no mines
        for (int r = 0; r < NUM_ROWS; r++){
            for (int c = 0; c < NUM_COLS; c++){
                board[r][c] = Mine.NO;
            }
        }

        // Get NUM_MINES random unique mine locations
        Set<Pair<Integer, Integer>> mineLocations = new LinkedHashSet<>();
        Random rand = new Random();
        while(mineLocations.size() != NUM_MINES){
            mineLocations.add(new Pair<>(rand.nextInt(NUM_ROWS), rand.nextInt(NUM_COLS)));
        }

        // Set the locations to Mine.UNDETECTED
        for(Pair<Integer, Integer> location: mineLocations){
            int row = location.first;
            int col = location.second;
            board[row][col] = Mine.UNDETECTED;
        }
    }

    private Mine check(int row, int col){
        return board[row][col];
    }


    // Returns -1 if the scanned position has a mine else return number of mines in col and row
    public int scan(int row, int col){
        if(check(row, col) == Mine.UNDETECTED){
            numMinesFound++;
            board[row][col] = Mine.DETECTED;
            return MINE_DETECTED; // -1
        }
        numMinesRevealedLocations.add(new Pair<>(row, col));

        // Count number of undetected mines
        int numMines = 0;
        for (int c = 0; c < NUM_COLS; c++){
            if (check(row, c) == Mine.UNDETECTED){
                numMines++;
            }
        }
        for (int r = 0; r < NUM_ROWS; r++){
            if (check(r, col) == Mine.UNDETECTED){
                numMines++;
            }
        }
        return numMines;
    }

    public int getNUM_ROWS(){
        return NUM_ROWS;
    }
    public int getNUM_COLS() {
        return NUM_COLS;
    }

    public int getNUM_MINES(){
        return NUM_MINES;
    }

    public boolean mineCountRevealed(int row, int col) {
        return numMinesRevealedLocations.contains(new Pair<>(row, col));
    }

    // A scan is not used when a mine is revealed
    public int getNumScansUsed(){
        return numMinesRevealedLocations.size();
    }

    public int getNumMinesFound() {
        return numMinesFound;
    }
}
