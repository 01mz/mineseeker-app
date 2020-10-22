package ca.cmpt276.as3.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;

import ca.cmpt276.as3.R;
import ca.cmpt276.as3.model.Options;
import ca.cmpt276.as3.model.Prefs;

/**
 * The MainMenuActivity has three buttons that allows the user to navigate to the GameActivity,
 * OptionsActivity, and HelpActivity. It makes sure the Options are up to date when the game starts.
 * The user returns to MainActivity after accessing GameActivity, OptionsActivity, or HelpActivity.
 */
public class MainMenuActivity extends AppCompatActivity {

    private static final int ACTIVITY_RESULT_PLAY_GAME = 101;
    private static final int ACTIVITY_RESULT_OPTIONS = 102;
    private static final int ACTIVITY_RESULT_HELP = 103;

    Options options = Options.getInstance();

    public static Intent makeMainMenuIntent(Context c){
        return new Intent(c, MainMenuActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPlayGameButton();
        setupOptionsButton();
        setupHelpButton();
    }


    private void setupPlayGameButton() {
        Button buttonPlayGame = (Button) findViewById(R.id.buttonPlayGame);
        buttonPlayGame.setOnClickListener(v -> {
            updateOptions();
            options.updateTimesPlayed();

            Intent intent = GameActivity.makeGameIntent(MainMenuActivity.this);
            startActivityForResult(intent, ACTIVITY_RESULT_PLAY_GAME);
        });
    }

    private void setupOptionsButton() {
        Button buttonOptions = (Button) findViewById(R.id.buttonOptions);
        buttonOptions.setOnClickListener(v -> {
            Intent intent = OptionsActivity.makeGameIntent(MainMenuActivity.this);
            startActivityForResult(intent, ACTIVITY_RESULT_OPTIONS);
        });
    }

    private void setupHelpButton() {
        Button buttonHelp = (Button) findViewById(R.id.buttonHelp);
        buttonHelp.setOnClickListener(v -> {
            Intent intent = HelpActivity.makeGameIntent(MainMenuActivity.this);
            startActivityForResult(intent, ACTIVITY_RESULT_HELP);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_RESULT_PLAY_GAME:
                storeStats();
                break;
            case ACTIVITY_RESULT_OPTIONS:
                storeStats();
                break;
        }
    }

    // https://developer.android.com/guide/topics/ui/settings/use-saved-values
    private void updateOptions() {
        String boardSize = Prefs.getBoardSizePref(this); // format: rows cols

        String numMines = Prefs.getNumMinesPref(this);

        String[] dimensions = boardSize.split(" ");
        int rows = 4;   // default
        int cols = 6;   // default
        try{
            rows = Integer.parseInt(dimensions[0]);
            cols = Integer.parseInt(dimensions[1]);
        } catch(NumberFormatException ignored){

        }

        int mines = 6;  // default
        try {
            mines = Integer.parseInt(numMines);
        } catch (NumberFormatException ignored){

        }

        int timesPlayed = Prefs.getTimesPlayedPref(this);
        int bestScore = Prefs.getBestScorePref(this, rows, cols, mines);

        options.init(rows, cols, mines, timesPlayed, bestScore);
    }

    private void storeStats() {

        Prefs.setTimesPlayedPref(this);
    }


}