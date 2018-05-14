package test.wd.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.wd.com.demo.R;
import test.wd.com.demo.adapter.DateAdapter;
import test.wd.com.demo.utils.DateUtils;

/**
 * Created by 92457 on 2018/5/14.
 */

public class CalendarActivity extends FragmentActivity implements View.OnClickListener{
    private GridView record_gridView;//定义gridView
    private DateAdapter dateAdapter;//定义adapter
    private ImageView record_left;//左箭头
    private ImageView record_right;//右箭头
    private TextView record_title;//标题
    private int year;
    private int month;
    private String title;
    private int[][] days = new int[6][7];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //初始化日期数据
        initData();
        //初始化组件
        initView();
        //组件点击监听事件
        initEvent();
    }

    private void initData() {
        year = DateUtils.getYear();
        month = DateUtils.getMonth();
    }

    private void initEvent() {
        record_left.setOnClickListener(this);
        record_right.setOnClickListener(this);
    }

    private void initView() {
        /**
         * 以下是初始化GridView
         */
        record_gridView = (GridView) findViewById(R.id.record_gridView);
        days = DateUtils.getDayOfMonthFormat(2018, 5);
        dateAdapter = new DateAdapter(this, days, year, month);//传入当前月的年
        record_gridView.setAdapter(dateAdapter);
        record_gridView.setVerticalSpacing(60);
        record_gridView.setEnabled(true);
        /**
         * 以下是初始化其他组件
         */
        record_left = (ImageView) findViewById(R.id.record_left);
        record_right = (ImageView) findViewById(R.id.record_right);
        record_title = (TextView) findViewById(R.id.record_title);

        record_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int day = (int) parent.getAdapter().getItem(position);
                Toast.makeText(CalendarActivity.this,"您点击的是---》" + day,Toast.LENGTH_SHORT).show();
                dateAdapter.updateTextColor(position);
            }
        });
    }


    /**
     * 下一个月
     *
     * @return
     */
    private int[][] nextMonth() {
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        days = DateUtils.getDayOfMonthFormat(year, month);
        return days;
    }

    /**
     * 上一个月
     *
     * @return
     */
    private int[][] prevMonth() {
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        days = DateUtils.getDayOfMonthFormat(year, month);
        return days;
    }

    /**
     * 设置标题
     */
    private void setTile() {
        title = year + "年" + month + "月";
        Log.d("Dong", "title ----> " + title);
        record_title.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_left:
                days = prevMonth();
                dateAdapter = new DateAdapter(this, days, year, month);
                record_gridView.setAdapter(dateAdapter);
                dateAdapter.notifyDataSetChanged();
                setTile();
                break;
            case R.id.record_right:
                days = nextMonth();
                dateAdapter = new DateAdapter(this, days, year, month);
                record_gridView.setAdapter(dateAdapter);
                dateAdapter.notifyDataSetChanged();
                setTile();
                break;
        }
    }
}
