package test.wd.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.wd.com.demo.R;
import test.wd.com.demo.adapter.LetterSearchAdapter;
import test.wd.com.demo.bean.Contacts;
import test.wd.com.demo.widget.SideLetterBar;

/**
 * Created by 92457 on 2018/5/9.
 */

public class LetterSearchActivity extends FragmentActivity {

    private SideLetterBar mLetterBar;
    private List<Contacts> mList = new ArrayList<>();
    private ListView mListView;
    private LetterSearchAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_search);


        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = adapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });
        initData();
    }


    private void initData() {
        Contacts contacts1 = new Contacts();
        Contacts contacts2 = new Contacts();
        Contacts contacts3 = new Contacts();
        Contacts contacts4 = new Contacts();
        Contacts contacts5 = new Contacts();
        Contacts contacts6 = new Contacts();
        Contacts contacts7 = new Contacts();
        Contacts contacts8 = new Contacts();
        Contacts contacts9 = new Contacts();
        Contacts contacts10 = new Contacts();
        Contacts contacts11 = new Contacts();
        Contacts contacts12 = new Contacts();
        contacts1.setName("张三");
        contacts1.setPinyin("zhangsan");
        contacts2.setName("李四");
        contacts2.setPinyin("lisi");
        contacts7.setName("李阿飞");
        contacts7.setPinyin("liafei");

        contacts3.setName("哈哈");
        contacts3.setPinyin("haha");

        contacts4.setName("魏东");
        contacts4.setPinyin("weidong");

        contacts5.setName("孟强");
        contacts5.setPinyin("mengqiang");

        contacts6.setName("孙东升");
        contacts6.setPinyin("sundongsheng");

        contacts8.setName("王灵嗯");
        contacts8.setPinyin("wanglingen");

        contacts9.setName("权传奇");
        contacts9.setPinyin("quanchuanqi");

        contacts10.setName("豆豆");
        contacts10.setPinyin("doudou");

        contacts11.setName("嗷嗷");
        contacts11.setPinyin("aa");

        contacts12.setName("八点");
        contacts12.setPinyin("badian");


        mList.add(contacts1);
        mList.add(contacts2);
        mList.add(contacts7);
        mList.add(contacts3);
        mList.add(contacts4);
        mList.add(contacts5);
        mList.add(contacts6);

        mList.add(contacts8);
        mList.add(contacts9);
        mList.add(contacts10);
        mList.add(contacts11);
        mList.add(contacts12);

        adapter = new LetterSearchAdapter(this,mList);
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(adapter);
    }
}
