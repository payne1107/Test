package test.wd.com.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wd.com.demo.MainActivity;
import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/8/9.
 */


public class Main extends FragmentActivity{
    @Bind(R.id.layout_step)
    protected LinearLayout layoutStep;
    @Bind(R.id.layout_update)
    protected LinearLayout layoutUpdate;
    @Bind(R.id.layout_other)
    protected LinearLayout layoutOther;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_m);
        mContext = Main.this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_step, R.id.layout_update,R.id.layout_other})
    void commit(View view) {
        switch (view.getId()) {
            case R.id.layout_update:
                Toast.makeText(Main.this, "更新--------------》", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_step:
                //指示器
                startActivity(new Intent(mContext,NewStepActivity.class));
                break;
            case R.id.layout_other:
                startActivity(new Intent(mContext, MainActivity.class));
                break;
        }

    }
}

