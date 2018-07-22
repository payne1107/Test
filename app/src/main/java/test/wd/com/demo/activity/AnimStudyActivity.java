package test.wd.com.demo.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/6/5.
 * 动画学习
 */

public class AnimStudyActivity extends FragmentActivity implements View.OnClickListener {

    private Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_study);
        btn1 = (Button) findViewById(R.id.btn_1);

        btn1.setOnClickListener(this);



        startAnim(btn1);
    }

    private void startAnim(final Button btn1) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(btn1.getLayoutParams().width, 500);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                System.out.println(currentValue);
                // 输出每次变化后的属性值进行查看

                btn1.getLayoutParams().width = currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                btn1.requestLayout();

            }
        });
        valueAnimator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
//                startAnim2();
                startAnimator3();
                break;
        }
    }

    private void startAnim2() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 10000);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                通过这样一个监听事件，我们就可以获取
//                到ValueAnimator每一步所产生的值。
//                通过调用getAnimatedValue()获取到每个时间因子所产生的Value。
                Integer value = (Integer) animation.getAnimatedValue();
                btn1.setText(value + "");
            }
        });
        valueAnimator.start();
    }

    private void startAnimator3() {
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                return null;
            }
        });
        animator.start();
    }
}
