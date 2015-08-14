package ua.dp.altermann.barley_break.handler;

import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class AnimationMove implements Animation.AnimationListener {

    private Button btn;

    public AnimationMove(Button btn) {
        this.btn = btn;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
