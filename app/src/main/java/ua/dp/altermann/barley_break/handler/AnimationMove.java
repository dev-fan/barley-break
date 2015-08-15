package ua.dp.altermann.barley_break.handler;

import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class AnimationMove implements Animation.AnimationListener {

    private Button btn1;
    private Button btn2;

    public AnimationMove(Button btn1, Button btn2) {
        this.btn1 = btn1;
        this.btn2 = btn2;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
