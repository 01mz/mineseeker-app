package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Manages SharedPreferences
 * Source: https://stackoverflow.com/questions/4051875/what-is-the-best-way-to-use-shared-preferences-between-activities
 */
public class Prefs {
    public static final String SHARED_PREFERENCES_NAME = "Options";

    private static final String SHARED_PREFERENCES_BOARD_SIZE_KEY = "board_size";
    public static final String BOARD_SIZE_DEF_VALUE = "4 6";

    private static final String SHARED_PREFERENCES_NUM_MINES_KEY = "num_mines";
    public static final String NUM_MINES_DEF_VALUE = "6";

    private static final String SHARED_PREFERENCES_TIMES_PLAYED_KEY = "Times Played";
    public static final int TIMES_PLAYED_DEF_VALUE = 0;

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static String getBoardSizePref(Context context) {
        return getPrefs(context).getString(SHARED_PREFERENCES_BOARD_SIZE_KEY, BOARD_SIZE_DEF_VALUE);

    }

    public static void setBoardSizePref(Context context, String value) {
        // perform validation etc..
        getPrefs(context).edit().putString(SHARED_PREFERENCES_BOARD_SIZE_KEY, value).commit();
    }

    public static String getNumMinesPref(Context context) {
        return getPrefs(context).getString(SHARED_PREFERENCES_NUM_MINES_KEY, NUM_MINES_DEF_VALUE);
    }

    public static int getTimesPlayedPref(Context context) {
        return getPrefs(context).getInt(SHARED_PREFERENCES_TIMES_PLAYED_KEY, TIMES_PLAYED_DEF_VALUE);
    }

    public static void setTimesPlayedPref(Context context, int value) {
        // perform validation etc..
        getPrefs(context).edit().putInt(SHARED_PREFERENCES_TIMES_PLAYED_KEY, value).commit();
    }


}
