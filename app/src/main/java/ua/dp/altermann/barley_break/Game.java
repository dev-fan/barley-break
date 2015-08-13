package ua.dp.altermann.barley_break;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ua.dp.altermann.barley_break.handler.AnimationMove;

public class Game {

    public final String LOG_TAG = "bb_game";
    private final static String SAVE_FIELD = "field";

    private Context cnx;
    private Random rnd = new Random();
    private List<Button> stage;
    private int[] xyEmpty = new int[2];
    private TextView tvWin;

    public Game(Context cnx, List<Button> stage) {
        this.cnx = cnx;
        this.stage = stage;
    }

    protected int convert(int... xy) {
        return xy[0] + xy[1] * 4;
    }

    protected int[] convert(int r) {
        int x = r % 4;
        int y = (r - x) / 4;
        int[] coord = {x, y};
        return coord;
    }

    public void reset() {
        tvWin.setText("");
        List<String> fill = fill();
        Log.d(LOG_TAG, "Fill values: " + fill);
        // randomize()
        for (int i = 0; i < 15; i++) {
            int x = rnd.nextInt(fill.size());
            stage.get(i).setText(fill.get(x));
            fill.remove(x);
            stage.get(i).setBackgroundResource(R.drawable.button);
        }
        stage.get(15).setText("");
        stage.get(15).setBackgroundResource(R.color.background_material_light);
        xyEmpty = convert(15);
        Log.d(LOG_TAG, "Empty position: " + Arrays.toString(xyEmpty));
    }

    public void move(View v) {
        int position = stage.indexOf(v);
        int[] xyCurr = convert(position);
        Log.d(LOG_TAG, "Move position: " + position + ": x=" + xyCurr[0] + ", y=" + xyCurr[1]);
        if (xyCurr[0] == xyEmpty[0]) { // Check at X
            for (int y = xyEmpty[1]; y != xyCurr[1];) {
                int currY = y;
                y += (xyEmpty[1] > xyCurr[1]) ? -1 : 1;
                Log.d(LOG_TAG, "Replace Y: " + currY + "->" + y);
                replace(convert(xyCurr[0], currY), convert(xyCurr[0], y));
            }
            xyEmpty[1] = xyCurr[1];
            check();
        } else if (xyCurr[1] == xyEmpty[1]) { // Check at Y
            for (int x = xyEmpty[0]; x != xyCurr[0];) {
                int currX = x;
                x += (xyEmpty[0] > xyCurr[0]) ? -1 : 1;
                Log.d(LOG_TAG, "Replace X: " + currX + "->" + x);
                replace(convert(currX, xyCurr[1]), convert(x, xyCurr[1]));
            }
            xyEmpty[0] = xyCurr[0];
            check();
        }
        Log.d(LOG_TAG, "Empty position: " + Arrays.toString(xyEmpty));
    }

    protected List<String> fill() {
        List<String> fill = new ArrayList<>(15);
        for (int i = 1; i <= 15; i++) {
            fill.add(String.valueOf(i));
        }
        return fill;
    }

    protected void replace(int r1, int r2) {
        Log.d(LOG_TAG, "replace: " + r1 + " -> " + r2);
        Button a1 = stage.get(r1);
        Button a2 = stage.get(r2);
        Log.d(LOG_TAG, "a1: " + a1.getText() + " | a2:" + a2.getText());
        a1.setText(a2.getText());
        Log.d(LOG_TAG, "a1: " + a1.getText() + " | a2:" + a2.getText());
        a1.setBackgroundResource(R.drawable.button);
        if (r2 - r1 > 1) { // num up
            Log.d(LOG_TAG, "num up");
            Animation anim1 = AnimationUtils.loadAnimation(cnx, R.anim.up_on);
            a1.startAnimation(anim1);
            Animation anim2 = AnimationUtils.loadAnimation(cnx, R.anim.up_off);
            anim2.setAnimationListener(new AnimationMove(a2));
            a2.startAnimation(anim2);
        } else if (r1 - r2 == 1) { // num right
            Log.d(LOG_TAG, "num right");
            Animation anim1 = AnimationUtils.loadAnimation(cnx, R.anim.right_on);
            a1.startAnimation(anim1);
            Animation anim2 = AnimationUtils.loadAnimation(cnx, R.anim.right_off);
            anim2.setAnimationListener(new AnimationMove(a2));
            a2.startAnimation(anim2);
        } else if (r1 - r2 > 1) { // num down
            Log.d(LOG_TAG, "num down");
            Animation anim1 = AnimationUtils.loadAnimation(cnx, R.anim.down_on);
            a1.startAnimation(anim1);
            Animation anim2 = AnimationUtils.loadAnimation(cnx, R.anim.down_off);
            anim2.setAnimationListener(new AnimationMove(a2));
            a2.startAnimation(anim2);
        } else if (r2 - r1 == 1) { // num left
            Log.d(LOG_TAG, "num left");
            Animation anim1 = AnimationUtils.loadAnimation(cnx, R.anim.left_on);
            a1.startAnimation(anim1);
            Animation anim2 = AnimationUtils.loadAnimation(cnx, R.anim.left_off);
            anim2.setAnimationListener(new AnimationMove(a2));
            a2.startAnimation(anim2);
        }
    }

    protected boolean check() {
        int count = 0;
        if (xyEmpty[0] == xyEmpty[1] && xyEmpty[0] == 3) {
            List<String> fill = fill();
            for (int i = 0; i < fill.size(); i++) {
                if (fill.get(i).equals(stage.get(i).getText())) {
                    count++;
                }
            }
        }
        Log.d(LOG_TAG, "check: " + count);
        if (count == 15) {
            tvWin.setText(R.string.you_win);
        }
        return count == 15;
    }

    public void save(Bundle bundle) {
        String[] field = new String[16];
        for (int i = 0; i < stage.size(); i++) {
            field[i] = (String) stage.get(i).getText();
        }
        bundle.putStringArray(SAVE_FIELD, field);
    }

    public void restore(Bundle bundle) {
        String[] field = bundle.getStringArray(SAVE_FIELD);
        for (int i = 0; i < stage.size(); i++) {
            stage.get(i).setText(field[i]);
            stage.get(i).setBackgroundResource(R.drawable.button);
            if (field[i].equals("")) {
                xyEmpty = convert(i);
                stage.get(i).setBackgroundResource(R.color.background_material_light);
            }
        }
        check();
    }

    // Getter/Setter

    public void setTvWin(TextView tvWin) {
        this.tvWin = tvWin;
    }

}
