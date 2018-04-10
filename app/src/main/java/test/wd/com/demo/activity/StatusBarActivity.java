package test.wd.com.demo.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.wd.com.demo.R;
import test.wd.com.demo.adapter.ViewPagerAdapter;

/**
 * Created by 92457 on 2018/4/5.
 */

public class StatusBarActivity extends FragmentActivity {


    private List<Integer> mList = new ArrayList();
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_status_bar);
        mList.add(R.drawable.xszy_img);
        mList.add(R.drawable.tmp_bx);
        mList.add(R.drawable.img_background);

        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(mList,mContext);
        pager.setAdapter(adapter);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
    }
}
