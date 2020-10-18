package ca.cmpt276.as3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import ca.cmpt276.as3.model.Options;
import ca.cmpt276.as3.model.Prefs;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_RESULT_PLAY_GAME = 101;
    private static final int ACTIVITY_RESULT_OPTIONS = 102;

    Options options = Options.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPlayGameButton();
        setupOptionsButton();
    }

    private void setupPlayGameButton() {
        Button buttonPlayGame = (Button) findViewById(R.id.buttonPlayGame);
        buttonPlayGame.setOnClickListener(v -> {
            updateOptions();
            options.updateTimesPlayed();

            Intent intent = GameActivity.makeGameIntent(MainActivity.this);
            startActivityForResult(intent, ACTIVITY_RESULT_PLAY_GAME);
        });
    }

    private void setupOptionsButton() {
        Button buttonPlayGame = (Button) findViewById(R.id.buttonOptions);
        buttonPlayGame.setOnClickListener(v -> {
            Intent intent = OptionsActivity.makeGameIntent(MainActivity.this);
            startActivityForResult(intent, ACTIVITY_RESULT_OPTIONS);
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

        Log.i("boardSize", boardSize);
        String numMines = Prefs.getNumMinesPref(this);
        Log.i("numMines", numMines);

        String[] dimensions = boardSize.split(" ");
        int rows = 4;   // default
        int cols = 6;   // default
        try{
            rows = Integer.parseInt(dimensions[0]);
            cols = Integer.parseInt(dimensions[1]);
        } catch(NumberFormatException e){

        }

        int mines = 6;  // default
        try {
            mines = Integer.parseInt(numMines);
        } catch (NumberFormatException e){

        }

        int timesPlayed = Prefs.getTimesPlayedPref(MainActivity.this);

        options.init(rows, cols, mines, timesPlayed);


        /*
        Gson gson = new Gson();
        Options savedOptions = gson.fromJson(json, Options.class);
        if(savedOptions != null){
            options = Options.getInstance();

        }
        */

    }

    private void storeStats() {
//        Log.i("Stotre", "" + options.getTimesPlayed());
//        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
//
//        SharedPreferences sharedPreferences =
//                PreferenceManager.getDefaultSharedPreferences(this );



//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//
//        prefsEditor.putInt(SHARED_PREFERENCES_SAVED_TIMES_PLAYED_KEY, timesPlayed);
//        prefsEditor.commit();

        Prefs.setTimesPlayedPref(this, options.getTimesPlayed());
    }


}