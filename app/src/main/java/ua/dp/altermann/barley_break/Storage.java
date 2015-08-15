package ua.dp.altermann.barley_break;

import android.content.SharedPreferences;

public class Storage {

    public static final String KEY = "bb_save";
    private static final String KEY_BEST_TIME = "best_time";
    private static final String KEY_SOUND = "sound";

    private SharedPreferences pref;

    public Storage(SharedPreferences pref) {
        this.pref = pref;
    }

    public void setBestTime(int time) {
        pref.edit().putInt(KEY_BEST_TIME, time).apply();
    }

    public int getBestTime() {
        return pref.getInt(KEY_BEST_TIME, 0);
    }

    public boolean isSound() {
        return pref.getBoolean(KEY_SOUND, false);
    }

    public void setSound(boolean sound) {
        pref.edit().putBoolean(KEY_SOUND, sound).apply();
    }

}
