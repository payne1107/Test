package test.wd.com.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import test.wd.com.demo.activity.PermissionActivity;
import test.wd.com.demo.activity.StatusBarActivity;
import test.wd.com.demo.activity.StepActivity;
import test.wd.com.demo.activity.UpdateApkActivity;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private RecyclerView recycleView;
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext= MainActivity.this;

        initView();
    }

    private void initView() {
        Button btnStep = (Button) findViewById(R.id.btn_step);
        Button btnUpdateApk = (Button) findViewById(R.id.btn_update_apk);
        Button btn_perimission = (Button)findViewById(R.id.btn_perimission);
        Button btnStatus = (Button) findViewById(R.id.btn_status);
        btn_perimission.setOnClickListener(this);
        btnStep.setOnClickListener(this);
        btnUpdateApk.setOnClickListener(this);
        btnStatus.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_step:
                startActivity(new Intent(mContext,StepActivity.class));
                break;
            case R.id.btn_update_apk:
                startActivity(new Intent(mContext,UpdateApkActivity.class));
                break;
            case R.id.btn_perimission:
                startActivity(new Intent(mContext,PermissionActivity.class));
                break;
            case R.id.btn_status:
                startActivity(new Intent(mContext, StatusBarActivity.class));
                break;
        }
    }
}
