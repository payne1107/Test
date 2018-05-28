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
    private int day;
    private String title;
    private int[][] days = new int[6][7];
    private int[] dayList = new int[42];
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
        day = DateUtils.getCurrentDayOfMonth();
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
        convertArray();

        dateAdapter = new DateAdapter(this, dayList, year, month,day);//传入当前月的年
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
                int array = (int) parent.getAdapter().getItem(position);
                if (position < 7 && array > 20) {
                    Toast.makeText(CalendarActivity.this, "哈哈哈哈----》" + array ,Toast.LENGTH_SHORT).show();
                } else if (position > 20 && array < 15) {
                    Toast.makeText(CalendarActivity.this, "悠悠我心----》" + array ,Toast.LENGTH_SHORT).show();
                } else {
                    int clickDay = (int) parent.getAdapter().getItem(position);
                    Toast.makeText(CalendarActivity.this,"您点击的是---》" + clickDay  +"当前日期---》"  + day +"moth--->" + month,Toast.LENGTH_SHORT).show();
                    dateAdapter.updateTextColor(position);
                    dateAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /****
     * 二维数据转成一维数组
     */
    private void convertArray() {
        int dayNum = 0;
        //将二维数组转化为一维数组，方便使用
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                 this.dayList[dayNum] = days[i][j];
                 dayNum++;
            }
        }
    }

    /**
     * 下一个月
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
        title = year + "年" + month + "月" + day +"日";
        Log.d("Dong", "title ----> " + title);
        record_title.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_left:
                days = prevMonth();
                convertArray();
                dateAdapter = new DateAdapter(this, dayList, year, month,day);
                record_gridView.setAdapter(dateAdapter);
                dateAdapter.notifyDataSetChanged();
                setTile();
                break;
            case R.id.record_right:
                days = nextMonth();
                convertArray();
                dateAdapter = new DateAdapter(this, dayList, year, month,day);
                record_gridView.setAdapter(dateAdapter);
                dateAdapter.notifyDataSetChanged();
                setTile();
                break;
        }
    }
}
