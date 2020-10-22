package ca.cmpt276.as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import ca.cmpt276.as3.R;

/**
 * HelpActivity displays instructions for the game and credits for resources used in the game.
 */
public class HelpActivity extends AppCompatActivity {

    public static Intent makeGameIntent(Context c) {
        return new Intent(c, HelpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // set up hyperlinks
        TextView textViewCredits =(TextView) findViewById(R.id.textViewCredits);
        textViewCredits.setMovementMethod(LinkMovementMethod.getInstance());
    }
}