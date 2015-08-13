package ua.dp.altermann.barley_break.handler;

import android.view.animation.Animation;
import android.widget.Button;

import ua.dp.altermann.barley_break.R;

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
        btn.clearAnimation();
        btn.setText("");
        btn.setBackgroundResource(R.color.background_material_light);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
