package ua.dp.altermann.barley_break;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public final String LOG_TAG = "bb_act";

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Button> stage = new ArrayList<>(16);
        stage.add((Button) findViewById(R.id.btn1));
        stage.add((Button) findViewById(R.id.btn2));
        stage.add((Button) findViewById(R.id.btn3));
        stage.add((Button) findViewById(R.id.btn4));
        stage.add((Button) findViewById(R.id.btn5));
        stage.add((Button) findViewById(R.id.btn6));
        stage.add((Button) findViewById(R.id.btn7));
        stage.add((Button) findViewById(R.id.btn8));
        stage.add((Button) findViewById(R.id.btn9));
        stage.add((Button) findViewById(R.id.btn10));
        stage.add((Button) findViewById(R.id.btn11));
        stage.add((Button) findViewById(R.id.btn12));
        stage.add((Button) findViewById(R.id.btn13));
        stage.add((Button) findViewById(R.id.btn14));
        stage.add((Button) findViewById(R.id.btn15));
        stage.add((Button) findViewById(R.id.btn16));
        game = new Game(getBaseContext(), stage);
        game.reset();
    }

    // Handlers

    public void onReset(View v) {
        Log.d(LOG_TAG, "onReset");
        game.reset();
    }

    public void onMove(View v) {
        Log.d(LOG_TAG, "onMove");
        game.move(v);
    }

}
