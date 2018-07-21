package test.wd.com.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.wd.com.demo.activity.BannerClickActivity;
import test.wd.com.demo.activity.CalendarActivity;
import test.wd.com.demo.activity.ImeiInfoActivity;
import test.wd.com.demo.activity.LetterSearchActivity;
import test.wd.com.demo.activity.PermissionActivity;
import test.wd.com.demo.activity.PlayVideoActivity;
import test.wd.com.demo.activity.StatusBarActivity;
import test.wd.com.demo.activity.StepActivity;
import test.wd.com.demo.activity.UpdateApkActivity;
import test.wd.com.demo.utils.ConfigUtil;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnDateSetListener {

    private RecyclerView recycleView;
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private TimePickerDialog dialogDay;
    private Button btnChooseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext= MainActivity.this;
        initViewDateDialog(this);
        initView();
    }

    private void initView() {
        Button btnStep = (Button) findViewById(R.id.btn_step);
        Button btnUpdateApk = (Button) findViewById(R.id.btn_update_apk);
        Button btn_perimission = (Button)findViewById(R.id.btn_perimission);
        Button btnStatus = (Button) findViewById(R.id.btn_status);
        Button btnIMEI = (Button) findViewById(R.id.btn_imei);
        Button btnAndroidJS = (Button) findViewById(R.id.btn_andriod_Js);
        Button btnLetterSearch = (Button) findViewById(R.id.btn_Letter_search);
        Button btnCalendar = (Button) findViewById(R.id.btn_calendar);
        btnChooseDate = (Button) findViewById(R.id.btn_choose_date);
        Button btnPlayVideo = (Button) findViewById(R.id.btn_play_video);
        Button btnContacts = (Button) findViewById(R.id.btn_contacts);
        Button btnListViewCheckbox = (Button) findViewById(R.id.btn_listview_checkbox);
        btn_perimission.setOnClickListener(this);
        btnStep.setOnClickListener(this);
        btnUpdateApk.setOnClickListener(this);
        btnStatus.setOnClickListener(this);
        btnIMEI.setOnClickListener(this);
        btnAndroidJS.setOnClickListener(this);
        btnLetterSearch.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        btnChooseDate.setOnClickListener(this);
        btnPlayVideo.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
        btnListViewCheckbox.setOnClickListener(this);
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
            case R.id.btn_imei:
                startActivity(new Intent(mContext, ImeiInfoActivity.class));
                break;
            case R.id.btn_andriod_Js:
                startActivity(new Intent(mContext, BannerClickActivity.class));
                break;
            case R.id.btn_Letter_search:
                //字母检索
                startActivity(new Intent(mContext, LetterSearchActivity.class));
                break;
            case R.id.btn_calendar:
                startActivity(new Intent(mContext, CalendarActivity.class));
                break;
            case R.id.btn_choose_date:
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.btn_play_video:
                //播放视频
                startActivity(new Intent(mContext, PlayVideoActivity.class));
                break;
            case R.id.btn_contacts:
                //获取联系人
//                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivityForResult(intent, 1);
                sendSms(this,"恩恩恩嗯嗯你你你");
                break;
            case R.id.btn_listview_checkbox:
                startActivity(new Intent(mContext,ListViewAndCheckboxActivity.class));

                break;

        }
    }


    /**
     * 初始化时间选择器
     */
    private void initViewDateDialog(OnDateSetListener listener) {
        dialogDay = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() +   ConfigUtil.TenYears)
                .setCurrentMillseconds(System.currentTimeMillis()/* - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }

    /*** 时间选择器的事件 **/
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        btnChooseDate.setText(sf.format(d));
    }



    /**
     * 短信分享
     * @param mContext
     * @param smstext 短信分享内容
     * @return
     */
    public static Boolean sendSms(Context mContext, String smstext) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", smstext);
        mContext.startActivity(mIntent);
        return null;
    }
}
