package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Prefs manages SharedPreferences for saved game settings and stats
 * Source: https://stackoverflow.com/questions/4051875/what-is-the-best-way-to-use-shared-preferences-between-activities
 */
public class Prefs {
    private static final String SHARED_PREFERENCES_BOARD_SIZE_KEY = "board_size";
    private static final String BOARD_SIZE_DEF_VALUE = "4 6";

    private static final String SHARED_PREFERENCES_NUM_MINES_KEY = "num_mines";
    private static final String NUM_MINES_DEF_VALUE = "6";

    private static final String SHARED_PREFERENCES_TIMES_PLAYED_KEY = "Times Played";
    private static final int TIMES_PLAYED_DEF_VALUE = 0;

    private static final int BEST_SCORE_DEF_VALUE = -1;

    private static final String SHARED_PREFERENCES_SAVED_SCORES_KEY = "Saved Scores";
    private static Map<String, Integer> savedScores = new HashMap<>();

    private static SharedPreferences getPrefs(Context context) {
         return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getBoardSizePref(Context context) {
        return getPrefs(context).getString(SHARED_PREFERENCES_BOARD_SIZE_KEY, BOARD_SIZE_DEF_VALUE);

    }

    public static String getNumMinesPref(Context context) {
        return getPrefs(context).getString(SHARED_PREFERENCES_NUM_MINES_KEY, NUM_MINES_DEF_VALUE);
    }

    public static int getTimesPlayedPref(Context context) {
        return getPrefs(context).getInt(SHARED_PREFERENCES_TIMES_PLAYED_KEY, TIMES_PLAYED_DEF_VALUE);
    }

    public static void setTimesPlayedPref(Context context) {
        // perform validation etc..
        Options options = Options.getInstance();
        getPrefs(context).edit().putInt(SHARED_PREFERENCES_TIMES_PLAYED_KEY, options.getTimesPlayed()).apply();
    }


    public static int getBestScorePref(Context context, int rows, int cols, int mines) {
        String key = getKey(rows, cols, mines);

        updateSavedScoresFromPref(context);

        double bestScore = Double.parseDouble(""+savedScores.getOrDefault(key, BEST_SCORE_DEF_VALUE));
        return (int)bestScore;
    }

    public static void setBestScorePref(Context context) {
        Options options = Options.getInstance();
        String key = getKey(options.getRows(), options.getCols(), options.getMines());
        int bestScore = options.getBestScore();

        updateSavedScoresFromPref(context);

        savedScores.put(key, bestScore);
        Gson gson = new Gson();
        String json = gson.toJson(savedScores);
        getPrefs(context).edit().putString(SHARED_PREFERENCES_SAVED_SCORES_KEY, json).apply();
    }

    private static void updateSavedScoresFromPref(Context context) {
        Gson gson = new Gson();
        String json = getPrefs(context).getString(SHARED_PREFERENCES_SAVED_SCORES_KEY,
                gson.toJson(new HashMap<>())); // default is empty HashMap

        savedScores = gson.fromJson(json, Map.class);
    }

    private static String getKey(int rows, int cols, int mines) {
        return String.format(Locale.getDefault(), "%d-%d-%d", rows, cols, mines);
    }

    public static void resetScores(Context context){
        savedScores = new HashMap<>();
        Gson gson = new Gson();
        String json = gson.toJson(savedScores);
        getPrefs(context).edit().putString(SHARED_PREFERENCES_SAVED_SCORES_KEY, json).apply();
    }


}
