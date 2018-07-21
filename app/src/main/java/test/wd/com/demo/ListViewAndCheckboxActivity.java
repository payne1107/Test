package test.wd.com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import test.wd.com.demo.adapter.ListViewAdapter;

/**
 * Created by WD on 2018/7/21.
 */

public class ListViewAndCheckboxActivity extends Activity {
    private ListView listView;
    private ListViewAdapter adapter;
    private String[] beans = new String[] { "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13","14","15","16","17","18","19" };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_and_checkbox);
        initView();
    }

    private void initView() {
        Log.i("htp", "beans.size:" + beans.length);
        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(ListViewAndCheckboxActivity.this, beans);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}
