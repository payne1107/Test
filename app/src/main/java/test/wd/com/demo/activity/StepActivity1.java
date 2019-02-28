package test.wd.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/8/9.
 * 指示器
 */

public class StepActivity1 extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_step_1);
    }
}
