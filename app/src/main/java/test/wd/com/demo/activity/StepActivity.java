package test.wd.com.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.wd.com.demo.widget.DividerItemDecoration;
import test.wd.com.demo.R;
import test.wd.com.demo.adapter.TGTaskContentAdapter;

/**
 * Created by 92457 on 2018/3/29.
 */

public class StepActivity extends FragmentActivity {

    private RecyclerView recycleView;
    private Context mContext;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_layout);

        mContext = this;
        addData();
        recycleView = (RecyclerView) findViewById(R.id.recycler_view);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setHasFixedSize(true);
        //用自定义分割线类设置分割线
        recycleView.addItemDecoration(new DividerItemDecoration(this));
        TGTaskContentAdapter adapter = new TGTaskContentAdapter(mContext,mList);
        recycleView.setAdapter(adapter);
    }

    /***
     * 添加数据
     */
    private void addData() {
        for (int i = 0; i < 6; i++) {
            mList.add("第" + i + "步骤步骤步");
        }
    }
}
