package ua.dp.altermann.barley_break.handler;

import android.media.MediaPlayer;
import android.util.Log;

public class SoundCompletion implements MediaPlayer.OnCompletionListener {

    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            mp.release();
            mp = null;
        } catch (Exception e) {
            Log.d("Exception: ", e.getMessage());
        }
    }

}
