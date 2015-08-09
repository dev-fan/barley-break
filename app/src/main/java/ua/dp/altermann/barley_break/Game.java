package ua.dp.altermann.barley_break;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    public final String LOG_TAG = "bb_game";
    private final static String SAVE_FIELD = "field";

    private Context cnx;
    private Random rnd = new Random();
    private List<Button> stage;
    private int[] xyEmpty = new int[2];

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
        // fill()
        List<String> fill = new ArrayList<>(15);
        for (int i = 1; i <= 15; i++) {
            fill.add(String.valueOf(i));
        }
        Log.d(LOG_TAG, "Fill values: " + fill);
        // randomize()
        for (int i = 0; i < 15; i++) {
            int x = rnd.nextInt(fill.size());
            stage.get(i).setText(fill.get(x));
            fill.remove(x);
        }
        stage.get(15).setText("");
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
                replaceElement(convert(xyCurr[0], currY), convert(xyCurr[0], y));
            }
            xyEmpty[1] = xyCurr[1];
        } else if (xyCurr[1] == xyEmpty[1]) { // Check at Y
            for (int x = xyEmpty[0]; x != xyCurr[0];) {
                int currX = x;
                x += (xyEmpty[0] > xyCurr[0]) ? -1 : 1;
                Log.d(LOG_TAG, "Replace X: " + currX + "->" + x);
                replaceElement(convert(currX, xyCurr[1]), convert(x, xyCurr[1]));
            }
            xyEmpty[0] = xyCurr[0];
        }
        Log.d(LOG_TAG, "Empty position: " + Arrays.toString(xyEmpty));
    }

    protected void replaceElement(int r1, int r2) {
        Log.d(LOG_TAG, "replaceElement: " + r1 + " -> " + r2);
        Button a1 = stage.get(r1);
        Button a2 = stage.get(r2);
        CharSequence tmp = a1.getText();
        a1.setText(a2.getText());
        a2.setText(tmp);
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
            if (field[i].equals("")) {
                xyEmpty = convert(i);
            }
        }
    }

}
