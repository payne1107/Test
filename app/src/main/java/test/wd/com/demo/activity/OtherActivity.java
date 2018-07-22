package test.wd.com.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/5/31.
 */

public class OtherActivity extends FragmentActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initView();
        mContext = OtherActivity.this;
    }

    private void initView() {
        Button btnSnackBar = (Button) findViewById(R.id.btn_snackbar);
        Button btnTest = (Button) findViewById(R.id.btn_test);
        Button btnClipboard = (Button) findViewById(R.id.btn_clipboard);
        Button btnConstraintlayout = (Button) findViewById(R.id.btn_constraintlayout);
        Button btnAnimStudy = (Button) findViewById(R.id.btn_anim_study);

        btnAnimStudy.setOnClickListener(this);
        btnClipboard.setOnClickListener(this);
        btnSnackBar.setOnClickListener(this);
        btnTest.setOnClickListener(this);
        btnConstraintlayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_snackbar:
                Snackbar.make(v,"向前走看看",Snackbar.LENGTH_INDEFINITE).setAction("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Dong", "you click snackbar !!");
                    }
                }).show();
                break;
            case R.id.btn_test:
                startActivity(new Intent(mContext, TestActivity.class));
                break;
            case R.id.btn_clipboard:
                startActivity(new Intent(mContext, ClicpBoardActivity.class));
                break;
            case R.id.btn_constraintlayout:
                //约束布局 难玩
                startActivity(new Intent(mContext, ConstraintLayoutActivity.class));
                break;
            case R.id.btn_anim_study:
                startActivity(new Intent(mContext, AnimStudyActivity.class));
                break;
        }
    }
}
