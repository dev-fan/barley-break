package ua.dp.altermann.barley_break;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public final String LOG_TAG = "bb_act";

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Button> stage = new ArrayList<>(16);
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

        final LinearLayout llMain = (LinearLayout) findViewById(R.id.llMain);
        final RelativeLayout llField = (RelativeLayout) findViewById(R.id.llField);
        llField.post(new Runnable() {
            @Override
            public void run() {
                int actMargin = getResources().getDimensionPixelSize(R.dimen.activity_margin);
                int minSize = Math.min(llMain.getMeasuredWidth(), llMain.getMeasuredHeight());
                int btnSize = (int) Math.floor((minSize - (actMargin) * 2) / 4);
                for (Button btn : stage) {
                    ViewGroup.LayoutParams layoutParams = btn.getLayoutParams();
                    layoutParams.width = btnSize;
                    layoutParams.height = btnSize;
                    btn.setLayoutParams(layoutParams);
                    btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize / 2);
                }
                int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
                llField.setLayoutParams(new LinearLayout.LayoutParams(wrapContent, wrapContent));
            }
        });
        game = new Game(getBaseContext(), stage);
        game.setTvWin((TextView) findViewById(R.id.tvWin));
        game.reset();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game.restore(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        game.save(outState);
    }

    // Handlers

    public void onReset(View v) {
        game.reset();
    }

    public void onMove(View v) {
        game.move(v);
    }

}
