package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import ca.cmpt276.as3.model.Options;

public class OptionsActivity extends AppCompatActivity {

    private SettingsFragment settingsFragment;

    public static Intent makeGameIntent(Context c) {
        Intent intent = new Intent(c, OptionsActivity.class);
        return intent;
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
        Preference preference = settingsFragment.findPreference("times_played");
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Options options = Options.getInstance();
                options.resetTimesPlayed();
                Log.i("Stotre", ""+options.getTimesPlayed());

                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(OptionsActivity.this /* Activity context */);
                String timesPlayed = sharedPreferences.getString("times_played", "6");
                Log.i("times_played", timesPlayed);
                return true;
            }
        });
    }
}