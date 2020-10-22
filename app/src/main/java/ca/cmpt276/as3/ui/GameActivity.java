package ca.cmpt276.as3.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import ca.cmpt276.as3.R;
import ca.cmpt276.as3.model.Board;
import ca.cmpt276.as3.model.Options;
import ca.cmpt276.as3.model.Prefs;

/**
 * The GameActivity populates the game board with dynamic buttons and allows the user to play the
 * game by interacting with the buttons. Updates mine counts when mines are revealed.
 * Shows a dialog when the user wins.
 * With code from Brian Fraser
 */
public class GameActivity extends AppCompatActivity {
    private int NUM_ROWS;
    private int NUM_COLS;
    private Button[][] buttons;
    private Board board;

    private TextView textViewMinesFound;
    private TextView textViewScansUsed;
    private TextView textViewTimesPlayed;
    private TextView textViewBestScore;

    public static Intent makeGameIntent(Context c){
        return new Intent(c, GameActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewMinesFound = (TextView) findViewById(R.id.textViewMinesFound);
        textViewScansUsed = (TextView) findViewById(R.id.textViewScansUsed);
        textViewTimesPlayed = (TextView) findViewById(R.id.textViewTimesPlayed);
        textViewBestScore = (TextView) findViewById(R.id.textViewBestScore);

        setupBoard();

        populateButtons();
    }

    private void setupBoard() {
        Options options = Options.getInstance();
        board = new Board(options.getCols(), options.getRows(), options.getMines());
        NUM_COLS = board.getNUM_COLS();
        NUM_ROWS = board.getNUM_ROWS();
        buttons = new Button[NUM_ROWS][NUM_COLS];

        textViewMinesFound.setText(getString(R.string.mines_found, 0, board.getNUM_MINES()));
        textViewTimesPlayed.setText(getString(R.string.times_played, options.getTimesPlayed()));

        int bestScore = options.getBestScore();
        int NO_BEST_SCORE = -1;
        Log.i("score", ""+bestScore);
        if(bestScore != NO_BEST_SCORE){
            textViewBestScore.setText(getString(R.string.best_score, bestScore));
        }
    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));



                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(v -> gridButtonClicked(FINAL_ROW, FINAL_COL));

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void gridButtonClicked(int row, int col) {
        // Lock Button Sizes:
        lockButtonSizes();

        // if current position is not scanned, then scan
        if(!board.mineCountRevealed(row, col)){
            Button button = buttons[row][col];
            final int MINE_DETECTED = -1;
            int scan = board.scan(row, col); // -1 if mine detected else number of mines scanned

            if(scan == MINE_DETECTED){
                showMine(button);
                updateUndetectedMineCounts(row, col);

                if(board.getNumMinesFound() == board.getNUM_MINES()){
                    // win
                    if(Options.getInstance()
                            .updateBestScore(board.getNumScansUsed())){ // if bestScore updated
                        Prefs.setBestScorePref(this);
                    }
                    showWinDialog();
                }

            } else {
                button.setText(Integer.toString(scan));
                textViewScansUsed.setText(getString(R.string.scans_used, board.getNumScansUsed()));
            }


        }
    }

    private void showMine(Button button) {
        // Scale image to button: Only works in JellyBean!
        // Image from Crystal Clear icon set, under LGPL
        // http://commons.wikimedia.org/wiki/Crystal_Clear
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    @SuppressLint("SetTextI18n")
    private void updateUndetectedMineCounts(int row, int col) {
        textViewMinesFound.setText(getString(R.string.mines_found, board.getNumMinesFound(), board.getNUM_MINES()));

        // We know board.mineCountRevealed(row, c) is false (not revealed) since it is was a unrevealed mine

        for (int c = 0; c < NUM_COLS; c++){
            if (board.mineCountRevealed(row, c)){
                Button button = buttons[row][c];
                int updated = Integer.parseInt(button.getText().toString())-1;
                button.setText(Integer.toString(updated));
            }
        }
        for (int r = 0; r < NUM_ROWS; r++){
            if (board.mineCountRevealed(r, col)){
                Button button = buttons[r][col];
                int updated = Integer.parseInt(button.getText().toString())-1;
                button.setText(Integer.toString(updated));
            }
        }


    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                int height = button.getMeasuredHeight();

                button.setMinWidth(width);
                button.setMaxWidth(width);

                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void showWinDialog() {
        FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();

        dialog.show(manager, "MessageDialog");

    }
}