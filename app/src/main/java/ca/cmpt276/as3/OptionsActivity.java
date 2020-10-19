package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import ca.cmpt276.as3.model.Options;
import ca.cmpt276.as3.model.Prefs;

public class OptionsActivity extends AppCompatActivity {

    private SettingsFragment settingsFragment;

    public static Intent makeGameIntent(Context c) {
        return new Intent(c, OptionsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            settingsFragment = new SettingsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, settingsFragment)
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }



    }

    // Code from: https://stackoverflow.com/questions/37492025/onclick-listener-for-preference-activity-in-android
    @Override
    protected void onResume() {
        super.onResume();
        Preference prefBestScore = settingsFragment.findPreference("score");
        Preference prefTimesPlayed = settingsFragment.findPreference("times_played");

        prefBestScore.setOnPreferenceClickListener(preference -> {
            Options options = Options.getInstance();
            options.resetBestScore();
            Prefs.resetScores(OptionsActivity.this);
            return true;
        });
        prefTimesPlayed.setOnPreferenceClickListener(preference -> {
            Options options = Options.getInstance();
            options.resetTimesPlayed();
            Prefs.setTimesPlayedPref(OptionsActivity.this);
            return true;
        });

    }

}