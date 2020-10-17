package ca.cmpt276.as3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_RESULT_PLAY_GAME = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPlayGameButton();
    }

    private void setupPlayGameButton() {
        Button buttonPlayGame = (Button) findViewById(R.id.buttonPlayGame);
        buttonPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameActivity.makeGameIntent(MainActivity.this);
                startActivityForResult(intent, ACTIVITY_RESULT_PLAY_GAME);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_RESULT_PLAY_GAME:
                break;
        }
    }

}