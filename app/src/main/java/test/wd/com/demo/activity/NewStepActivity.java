package test.wd.com.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import test.wd.com.demo.R;
import test.wd.com.demo.adapter.NewStepAdapter;

/**
 * Created by 92457 on 2018/8/9.
 * 步骤指示器
 */

public class NewStepActivity extends FragmentActivity {
    @Bind(R.id.listView1)
    protected ListView listView;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_step);
        ButterKnife.bind(this);
        mContext = NewStepActivity.this;

        initView();
    }

    private void initView() {
        String[] arrayStep = getResources().getStringArray(R.array.str_array_step);
        NewStepAdapter adapter = new NewStepAdapter(mContext, arrayStep);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView1)
    void setOnItemClick(int position) {
        String strArray = (String) listView.getAdapter().getItem(position);
        switch (position) {
            case 0:
                startActivity(new Intent(mContext, StepActivity1.class));
                break;
            case 1:

                break;
        }
        Log.d("Dong", "---------------->" + strArray +position);
    }
}
