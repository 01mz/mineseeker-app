package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.as3.model.Board;
import ca.cmpt276.as3.model.Options;

/**
 * Populate the game board with dynamic buttons.
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

    public static Intent makeGameIntent(Context c){
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewMinesFound = (TextView) findViewById(R.id.textViewMinesFound);
        textViewScansUsed = (TextView) findViewById(R.id.textViewScansUsed);
        textViewTimesPlayed = (TextView) findViewById(R.id.textViewTimesPlayed);

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

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

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
                    Toast.makeText(this, "Congrats you win!", Toast.LENGTH_SHORT).show();
                }

            } else {
                button.setText("" + scan);
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

    private void updateUndetectedMineCounts(int row, int col) {
        textViewMinesFound.setText(getString(R.string.mines_found, board.getNumMinesFound(), board.getNUM_MINES()));

        // We know board.mineCountRevealed(row, c) is false (not revealed) since it is was a unrevealed mine

        for (int c = 0; c < NUM_COLS; c++){
            if (board.mineCountRevealed(row, c)){
                Button button = buttons[row][c];
                int updated = Integer.parseInt(button.getText().toString())-1;
                button.setText("" + updated);
            }
        }
        for (int r = 0; r < NUM_ROWS; r++){
            if (board.mineCountRevealed(r, col)){
                Button button = buttons[r][col];
                int updated = Integer.parseInt(button.getText().toString())-1;
                button.setText("" + updated);
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
}