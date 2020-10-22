package ca.cmpt276.as3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.as3.R;

/**
 * The WelcomeActivity is the launching Activity. The app stays on this activity for 4 seconds before
 * proceeding to MainActivity. The user can tap 'Skip' to proceed immediately.
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final int TIME_OUT = 4000;     // Welcome screen duration
    private boolean done = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupSkipButton();

        // Delayed action
        // Source https://stackoverflow.com/questions/24745546/android-change-activity-after-few-seconds
        new Handler().postDelayed(this::startMainMenu, TIME_OUT);
    }



    private void setupSkipButton() {
        Button buttonSkip = (Button) findViewById(R.id.buttonSkip);
        buttonSkip.setOnClickListener(v -> startMainMenu());
    }

    private void startMainMenu() {
        if(!done){
            Intent i = MainMenuActivity.makeMainMenuIntent(WelcomeActivity.this);
            startActivity(i);
            done = true;
        }
        finish();
    }
}